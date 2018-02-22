package com.tools.generalCalculation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.soap.SOAPException;

import com.connectors.PippaDb.VanDelVeldeDBConnection;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.utils.DateUtils;

public class StockAvailabilityCalculations {

	public static List<ProductDetailedModel> temporarlyNotAvailableProducts = new ArrayList<ProductDetailedModel>();
	public static List<ProductDetailedModel> notLogngerAvailableProduct = new ArrayList<ProductDetailedModel>();
	public static List<ProductDetailedModel> lessThanTwentyAvailableProducts = new ArrayList<ProductDetailedModel>();
	public static List<ProductDetailedModel> backInStockProducts = new ArrayList<ProductDetailedModel>();
	public static List<ProductDetailedModel> stockReportProducts = new ArrayList<ProductDetailedModel>();

	public static void wipe() {
		temporarlyNotAvailableProducts = new ArrayList<ProductDetailedModel>();
		notLogngerAvailableProduct = new ArrayList<ProductDetailedModel>();
		lessThanTwentyAvailableProducts = new ArrayList<ProductDetailedModel>();
	}

	public static void main(String[] args) throws SOAPException, IOException, SQLException, ParseException {
		calculateStockAvailabilityData("0", "10000");

		for (ProductDetailedModel productDetailedModel : temporarlyNotAvailableProducts) {
			System.out.println(productDetailedModel.getProductId());
		}

		System.out.println("temporarlyNotAvailableProducts: " + temporarlyNotAvailableProducts.size());
		System.out.println("notLogngerAvailableProduct: " + notLogngerAvailableProduct.size());
		System.out.println("lessThanTwentyAvailableProducts: " + lessThanTwentyAvailableProducts.size());
		System.out.println("backInStockProducts: " + backInStockProducts.size());
		System.out.println("stockReportProducts: " + stockReportProducts.size());

	}


	public static List<ProductDetailedModel> calculateStockAvailabilityData(String fromProductId, String toProductId)
			throws SOAPException, IOException, SQLException, ParseException {

		List<ProductDetailedModel> getAllProducts = VanDelVeldeDBConnection.productList(fromProductId, toProductId);

		for (ProductDetailedModel product : getAllProducts) {

			if (isTemporarlyNotAvailableProduct(product)) {
				product.setEarliestAvDate("X.X.");
				product.setStatus("Delivery date will be announced");
				temporarlyNotAvailableProducts.add(product);
			}

			if (isTemporarlyNotAvailableProductCondition2(product)) {
				product.setEarliestAvDate(DateUtils.parseDate(
						DateUtils.getFirstWednesdayAfterDate(product.getEarliestAvDate(), "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss", "dd.MM.") + "*");
				product.setStatus("Available from " + product.getEarliestAvDate());
				temporarlyNotAvailableProducts.add(product);
			}

			if (isNotLogngerAvailableProduct(product)) {
				product.setStatus("Sold out");
				notLogngerAvailableProduct.add(product);
			}

			if (isLessThan20AvailableProducts(product)) {
				int quatity = (int) Double.parseDouble(product.getQuantity());
				String qty = Integer.toString(quatity);
				product.setStatus(qty.contentEquals("1") ? "Only " + qty + " item available"
						: "Only " + qty + " items available");
				lessThanTwentyAvailableProducts.add(product);
			}

		}
		backInStockProducts = VanDelVeldeDBConnection.productBackInStockList();
		stockReportProducts = joinLists(temporarlyNotAvailableProducts, notLogngerAvailableProduct,
				lessThanTwentyAvailableProducts, backInStockProducts);

		return null;

	}


	private static boolean isTemporarlyNotAvailableProduct(ProductDetailedModel product) throws ParseException {
		// System.out.println(product);
		BigDecimal zero = new BigDecimal(0);
		List<String> productCategories = listRemoveBlanks(product.getCategoriesArray());
		List<String> restrictedCategories = Arrays.asList("23", "104", "13");

		return (getValue(product.getQuantity()).compareTo(zero) == 0
				&& getValue(product.getMinQty()).compareTo(zero) == 0 && product.getIsInStock().contentEquals("0")
				&& product.getIsDiscountinued().contentEquals("0"))

				|| (getValue(product.getQuantity()).compareTo(zero) < 0
						&& getValue(product.getMinQty()).compareTo(zero) <= 0
						&& product.getIsInStock().contentEquals("0") && product.getIsDiscountinued().contentEquals("0"))

						&& (isAcceptedCategory(restrictedCategories, productCategories));

	}

	private static boolean isTemporarlyNotAvailableProductCondition2(ProductDetailedModel product)
			throws ParseException {
		BigDecimal zero = new BigDecimal(0);
		String date = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		List<String> productCategories = listRemoveBlanks(product.getCategoriesArray());
		List<String> restrictedCategories = Arrays.asList("23", "104", "13");

		return (getValue(product.getQuantity()).compareTo(zero) <= 0
				&& getValue(product.getMinQty()).compareTo(getValue(product.getQuantity())) < 0
				&& product.getIsInStock().contentEquals("1") && product.getIsDiscountinued().contentEquals("0")
				&& DateUtils.isDateAfter(product.getEarliestAvDate(), date, "yyyy-MM-dd HH:mm:ss")

		) && (isAcceptedCategory(restrictedCategories, productCategories));

	}

	private static boolean isNotLogngerAvailableProduct(ProductDetailedModel product) {
		BigDecimal zero = new BigDecimal(0);
		List<String> productCategories = listRemoveBlanks(product.getCategoriesArray());
		List<String> restrictedCategories = Arrays.asList("23", "104", "68");
		return (isAcceptedCategory(restrictedCategories, productCategories))
				&& (product.getQuantity().contentEquals("0.0000") && product.getIsInStock().contentEquals("0")
						&& product.getIsDiscountinued().contentEquals("1"))
				|| (getValue(product.getQuantity()).compareTo(zero) < 0

						&& product.getIsInStock().contentEquals("0")
						&& product.getIsDiscountinued().contentEquals("1"));

	}

	private static boolean isLessThan20AvailableProducts(ProductDetailedModel product) {
		BigDecimal zero = new BigDecimal(0);
		BigDecimal twenty = new BigDecimal(20);
		List<String> productCategories = listRemoveBlanks(product.getCategoriesArray());
		List<String> restrictedCategories = Arrays.asList("23", "104", "68", "52");

		return getValue(product.getQuantity()).compareTo(zero) == 1
				&& getValue(product.getQuantity()).compareTo(twenty) < 0 && product.getIsInStock().contentEquals("1")
				&& isAcceptedCategory(restrictedCategories, productCategories);
	}

	public static boolean isAcceptedCategory(List<String> restrictList, List<String> categoryList) {

		boolean isAccepted = false;

		// Prepare a union
		List<String> union = new ArrayList<String>(restrictList);
		union.addAll(categoryList);
		// Prepare an intersection
		List<String> intersection = new ArrayList<String>(restrictList);
		intersection.retainAll(categoryList);
		// Subtract the intersection from the union
		union.removeAll(intersection);
		union.retainAll(categoryList);

		if (categoryList.isEmpty()) {
			isAccepted = true;
		} else {
			union.removeAll(Arrays.asList("", null));
			if (!union.isEmpty()) {

				isAccepted = true;
			}
		}

		return isAccepted;
	}
	
	public static List<String> listRemoveBlanks(List<String> list) {

		List<String> result = new ArrayList<String>();

		for (String str : list) {
			if (str != null && !str.isEmpty() && !str.contentEquals(" ")) {
				result.add(str);
			}
		}

		return result;
	}
	

	private static List<ProductDetailedModel> joinLists(List<ProductDetailedModel> x, List<ProductDetailedModel> y,
			List<ProductDetailedModel> z, List<ProductDetailedModel> w) {
		List<ProductDetailedModel> allProducts = new ArrayList<ProductDetailedModel>();

		allProducts.addAll(x);
		allProducts.addAll(y);
		allProducts.addAll(z);
		allProducts.addAll(w);

		return allProducts;

	}

	private static BigDecimal getValue(String productValue) {
		BigDecimal value = BigDecimal.valueOf(Double.parseDouble(productValue));
		return value;

	}

}

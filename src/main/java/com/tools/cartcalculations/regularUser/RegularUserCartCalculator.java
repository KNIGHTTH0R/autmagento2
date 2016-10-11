package com.tools.cartcalculations.regularUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tools.constants.ContextConstants;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;

public class RegularUserCartCalculator {

	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListTp0 = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListTp1 = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListTp2 = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListTp3 = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListWithRegularPrice = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListwithVoucher = new ArrayList<RegularBasicProductModel>();
	public static RegularCartCalcDetailsModel calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp1 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp2 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp3 = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<RegularBasicProductModel>();
		allProductsListTp0 = new ArrayList<RegularBasicProductModel>();
		allProductsListTp1 = new ArrayList<RegularBasicProductModel>();
		allProductsListTp2 = new ArrayList<RegularBasicProductModel>();
		allProductsListTp3 = new ArrayList<RegularBasicProductModel>();
		allProductsListWithRegularPrice = new ArrayList<RegularBasicProductModel>();
		calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
		allProductsListwithVoucher = new ArrayList<RegularBasicProductModel>();
		shippingCalculatedModel = new ShippingModel();
		shippingCalculatedModelTp1 = new ShippingModel();
		shippingCalculatedModelTp2 = new ShippingModel();
		
	}

	public static List<RegularBasicProductModel> getProductswithRegularPriceFromList(
			List<RegularBasicProductModel> productsList) {
		List<RegularBasicProductModel> newProductsList = new ArrayList<RegularBasicProductModel>();
		for (RegularBasicProductModel product : productsList) {
			if (product.getBonusType().contentEquals(ContextConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static void calculateCartAndShippingTotals(List<RegularBasicProductModel> prodList, String discountClass,
			String shippingValue, String voucherValue) {
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotals(allProductsList, discountClass,
				voucherValue, shippingValue);
		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);
	}

	public static void calculateCartTotals(List<RegularBasicProductModel> prodList, String discountClass,
			String shippingValue, String voucherValue) {
		allProductsListwithVoucher = RegularCartTotalsCalculation.calculateProductsWithVoucherApplied(allProductsList,
				voucherValue);
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotals(allProductsList, discountClass,
				voucherValue, shippingValue);
	}

	public static void calculateShippingTotals(String shippingValue, String voucherValue, String taxClass) {

		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(allProductsList,
				allProductsListTp0, voucherValue, shippingValue, taxClass, true);
		shippingCalculatedModelTp1 = RegularCartTotalsCalculation.calculateShippingTotals(allProductsList,
				allProductsListTp1, voucherValue, shippingValue, taxClass, false);
		shippingCalculatedModelTp2 = RegularCartTotalsCalculation.calculateShippingTotals(allProductsList,
				allProductsListTp2, voucherValue, shippingValue, taxClass, true);
	}

	public static void calculateCartBuy3Get1CartAndShippingTotals(List<RegularBasicProductModel> prodList,
			String discountClass, String shippingValue, String voucherValue) {
		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(allProductsList,
				allProductsListWithRegularPrice, discountClass, voucherValue, shippingValue);
		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);

	}
	
	public static String calculateAskingPrice(String unitPrice, String quantity) {

		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(unitPrice));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));

		return String.valueOf(price.multiply(qty));

	}

}

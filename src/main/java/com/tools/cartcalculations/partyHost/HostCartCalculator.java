package com.tools.cartcalculations.partyHost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tools.constants.ContextConstants;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.generalCalculation.OrderForCustomerDiscountsCalculation;
import com.tools.utils.PrintUtils;

public class HostCartCalculator {

	public static List<HostBasicProductModel> allProductsList = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp0 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp1 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp2 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp3 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp4 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp5 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp6 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp7 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListTp8 = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListwithVoucher = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithoutRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithBuy3Get1Applied = new ArrayList<HostBasicProductModel>();
	public static HostCartCalcDetailsModel calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp1 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp2 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp3 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp4 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp5 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp6 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp7 = new ShippingModel();
	public static ShippingModel shippingCalculatedModelTp8 = new ShippingModel();
	
	


	public static void wipe() {
		allProductsList = new ArrayList<HostBasicProductModel>();
		allProductsListTp0 = new ArrayList<HostBasicProductModel>();
		allProductsListTp1 = new ArrayList<HostBasicProductModel>();
		allProductsListTp2 = new ArrayList<HostBasicProductModel>();
		allProductsListTp3 = new ArrayList<HostBasicProductModel>();
		allProductsListTp4 = new ArrayList<HostBasicProductModel>();
		allProductsListTp5 = new ArrayList<HostBasicProductModel>();
		allProductsListTp6 = new ArrayList<HostBasicProductModel>();
		allProductsListTp7 = new ArrayList<HostBasicProductModel>();
		allProductsListTp8= new ArrayList<HostBasicProductModel>();
		allProductsListwithVoucher = new ArrayList<HostBasicProductModel>();
		allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
		allProductsListWithoutRegularPrice = new ArrayList<HostBasicProductModel>();
		allProductsListWithBuy3Get1Applied = new ArrayList<HostBasicProductModel>();
		calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
		shippingCalculatedModelTp1 = new ShippingModel();
		shippingCalculatedModelTp2 = new ShippingModel();
		shippingCalculatedModelTp3 = new ShippingModel();
		shippingCalculatedModelTp4 = new ShippingModel();
		shippingCalculatedModelTp5 = new ShippingModel();
		shippingCalculatedModelTp6 = new ShippingModel();
		shippingCalculatedModelTp7 = new ShippingModel();
		shippingCalculatedModelTp8 = new ShippingModel();
	}

	public static List<HostBasicProductModel> getProductswithRegularPriceFromList(
			List<HostBasicProductModel> productsList) {
		List<HostBasicProductModel> newProductsList = new ArrayList<HostBasicProductModel>();
		for (HostBasicProductModel product : productsList) {
			if (product.getBonusType().contentEquals(ContextConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static List<HostBasicProductModel> getProductsWithoutRegularPriceFromList(
			List<HostBasicProductModel> productsList) {
		List<HostBasicProductModel> newProductsList = new ArrayList<HostBasicProductModel>();
		for (HostBasicProductModel product : productsList) {
			if (!product.getBonusType().contentEquals(ContextConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static void calculateCartAndShippingTotals(String discountClass, String shippingValue) {
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotals(allProductsList, discountClass);
		PrintUtils.printHostCartCalcDetailsModel(calculatedTotalsDiscounts);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);
		PrintUtils.printShippingTotals(shippingCalculatedModel);
	}

	public static void calculateHostCartTotals(String discountClass, String shippingValue) {
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotals(allProductsList, discountClass);
		PrintUtils.printHostCartCalcDetailsModel(calculatedTotalsDiscounts);
	}

	public static void calculateOrderForCustomerCartAndShippingTotals(String discountClass, String shippingValue,
			String voucherValue) {
		
		allProductsListwithVoucher = OrderForCustomerDiscountsCalculation
				.calculateProductsWithVoucherApplied(allProductsList, voucherValue);
	//	allProductsList=OrderForCustomerDiscountsCalculation.recalculateProducts(allProductsList,voucherValue);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateOrderForCustomerTotals(
				allProductsListwithVoucher, discountClass, voucherValue, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);
	}

	public static void calculateOrderForCustomerTotals(String discountClass, String shippingValue,
			String voucherValue) {
		allProductsListwithVoucher = OrderForCustomerDiscountsCalculation
				.calculateProductsWithVoucherApplied(allProductsList, voucherValue);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateOrderForCustomerTotals(
				allProductsListwithVoucher, discountClass, voucherValue, shippingValue);
	}

	public static void calculateShippingTotals(String shippingValue, String voucherValue, String taxClass) {

		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(allProductsList, allProductsListTp0,
				voucherValue, shippingValue, taxClass, true);
		shippingCalculatedModelTp1 = HostCartTotalsCalculation.calculateShippingTotals(allProductsList,
				allProductsListTp1, voucherValue, shippingValue, taxClass, false);
		shippingCalculatedModelTp2 = HostCartTotalsCalculation.calculateShippingTotals(allProductsList,
				allProductsListTp2, voucherValue, shippingValue, taxClass, true);
	}

	public static void calculateCartBuy3Get1CartAndShippingTotals(List<HostBasicProductModel> prodList,
			String discountClass, String shippingValue) {

		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
		allProductsListWithoutRegularPrice = getProductsWithoutRegularPriceFromList(allProductsList);
		allProductsListWithBuy3Get1Applied = HostCartBuy3Get1Calculation
				.applyBuy3Get1OnTheCart(allProductsListWithRegularPrice);
		allProductsListWithBuy3Get1Applied.addAll(allProductsListWithoutRegularPrice);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(
				allProductsListWithBuy3Get1Applied, allProductsListWithRegularPrice, discountClass, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);

	}

	public static void calculateCartBuy3Get1OrderForCustomerCartAndShippingTotals(String discountClass,
			String shippingValue, String voucherValue) {

		allProductsListwithVoucher = OrderForCustomerDiscountsCalculation
				.calculateProductsWithVoucherApplied(allProductsList, voucherValue);
		System.out.println("here:allProductsListwithVoucher");
		PrintUtils.printListHostBasicProductModel(allProductsListwithVoucher);

		allProductsListWithBuy3Get1Applied = HostCartBuy3Get1Calculation
				.applyBuy3Get1OnTheCart(allProductsListwithVoucher);
		System.out.println("here:allProductsListWithBuy3Get1Applied");
		PrintUtils.printListHostBasicProductModel(allProductsListWithBuy3Get1Applied);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(
				allProductsListWithBuy3Get1Applied, allProductsListwithVoucher, discountClass, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts,
				shippingValue);

	}

	public static List<HostBasicProductModel> calculateCMpartial(List<HostBasicProductModel> products,
			String discountPrice) {
		
		
		System.out.println("in metoda size "+products.size() );
		System.out.println("in metoda discount "+discountPrice );

		List<HostBasicProductModel> recalculatedProducts =new ArrayList<HostBasicProductModel>();
		 
		for (HostBasicProductModel product : products) {
			HostBasicProductModel rowProduct=new HostBasicProductModel();
			rowProduct.setRowTotal(OrderForCustomerDiscountsCalculation.calculateRowTotal(BigDecimal.valueOf(Double.valueOf(product.getFinalPrice())),BigDecimal.valueOf(Double.valueOf(product.getDiscountValue()))).toString());
			rowProduct.setProductTaxAmount(OrderForCustomerDiscountsCalculation.calculateProductTaxAmount(BigDecimal.valueOf(Double.valueOf(rowProduct.getRowTotal()))).toString());
		
			System.out.println("rowProduct.getTax: "+ rowProduct.getProductTaxAmount());
			
			
			rowProduct.setQuantity(product.getQuantity());
			rowProduct.setName(product.getName());
			rowProduct.setProdCode(product.getProdCode());
			rowProduct.setFinalPrice(product.getFinalPrice());
			rowProduct.setIpPoints(product.getIpPoints());
			rowProduct.setDiscountValue(product.getDiscountValue());
			
			System.out.println("hai:" +rowProduct);
			recalculatedProducts.add(rowProduct);
		}
		
		
		return recalculatedProducts;
	}
	
	
	
	
	public static void main(String[] args) {
	//	System.out.println(productDiscount("2","3","25"));
		System.out.println(OrderForCustomerDiscountsCalculation.calculateIpValue("85.95","8.33"));
	}

	public static OrderTotalsModel updateTotals(HostBasicProductModel product, OrderTotalsModel shopTotalsModel) {
		BigDecimal totalAmount=BigDecimal.ZERO;
		
		OrderTotalsModel model= new OrderTotalsModel();
		
		model.setSubtotal(product.getFinalPrice());
		//model.setShipping(shippingModelList.get(0).getShippingPrice());
		model.setDiscount(product.getDiscountValue());
		model.setTax(product.getProductTaxAmount());
	
		
		
		model.setTotalMarketingBonus("0");
		model.setTotalBonusJeverly("0");
		model.setTotalFortyDiscounts("0.00");
		
		
		
		model.setTotalIpRefunded(product.getIpPoints());
		totalAmount=totalAmount.add(BigDecimal.valueOf(Double.valueOf(product.getRowTotal())));
	//	totalAmount=totalAmount.add(BigDecimal.valueOf(Double.valueOf(shopTotalsModel.getShipping())));
		model.setTotalAmount(totalAmount.toString());
		model.setTotalIP(shopTotalsModel.getTotalIP());
		// from calcDetails model calculations
		//model.setRefundToStoreCredit(product.getRowTotal());
		
		
		
		
	//	model.setTotalPaid("0.00");
	//	model.setTotalRefunded("0.00");
	//	model.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		
		
		return model;
	}
}

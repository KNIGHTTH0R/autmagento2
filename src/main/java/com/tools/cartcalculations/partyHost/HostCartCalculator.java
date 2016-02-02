package com.tools.cartcalculations.partyHost;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.ContextConstants;
import com.tools.generalCalculation.OrderForCustomerDiscountsCalculation;
import com.tools.utils.PrintUtils;

public class HostCartCalculator {

	public static List<HostBasicProductModel> allProductsList = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListwithVoucher = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithoutRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithBuy3Get1Applied = new ArrayList<HostBasicProductModel>();
	public static HostCartCalcDetailsModel calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<HostBasicProductModel>();
		allProductsListwithVoucher = new ArrayList<HostBasicProductModel>();
		allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
		allProductsListWithoutRegularPrice = new ArrayList<HostBasicProductModel>();
		allProductsListWithBuy3Get1Applied = new ArrayList<HostBasicProductModel>();
		calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
	}

	public static List<HostBasicProductModel> getProductswithRegularPriceFromList(List<HostBasicProductModel> productsList) {
		List<HostBasicProductModel> newProductsList = new ArrayList<HostBasicProductModel>();
		for (HostBasicProductModel product : productsList) {
			if (product.getBonusType().contentEquals(ContextConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static List<HostBasicProductModel> getProductsWithoutRegularPriceFromList(List<HostBasicProductModel> productsList) {
		List<HostBasicProductModel> newProductsList = new ArrayList<HostBasicProductModel>();
		for (HostBasicProductModel product : productsList) {
			if (!product.getBonusType().contentEquals(ContextConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static void calculateCartAndShippingTotals(List<HostBasicProductModel> prodList, String discountClass, String shippingValue) {
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotals(allProductsList, discountClass);
		PrintUtils.printHostCartCalcDetailsModel(calculatedTotalsDiscounts);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
		PrintUtils.printShippingTotals(shippingCalculatedModel);
	}

	public static void calculateOrderForCustomerCartAndShippingTotals(String discountClass, String shippingValue, String voucherValue) {
		allProductsListwithVoucher = OrderForCustomerDiscountsCalculation.calculateProductsWithVoucherApplied(allProductsList, voucherValue);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateOrderForCustomerTotals(allProductsListwithVoucher, discountClass, voucherValue, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculateCartBuy3Get1CartAndShippingTotals(List<HostBasicProductModel> prodList, String discountClass, String shippingValue) {

		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
		allProductsListWithoutRegularPrice = getProductsWithoutRegularPriceFromList(allProductsList);
		allProductsListWithBuy3Get1Applied = HostCartBuy3Get1Calculation.applyBuy3Get1OnTheCart(allProductsListWithRegularPrice);
		allProductsListWithBuy3Get1Applied.addAll(allProductsListWithoutRegularPrice);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(allProductsListWithBuy3Get1Applied, allProductsListWithRegularPrice, discountClass, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);

	}
	
	public static void calculateCartBuy3Get1OrderForCustomerCartAndShippingTotals(String discountClass, String shippingValue,String voucherValue) {
		
		allProductsListwithVoucher = OrderForCustomerDiscountsCalculation.calculateProductsWithVoucherApplied(allProductsList, voucherValue);
		System.out.println("here:allProductsListwithVoucher");
		PrintUtils.printListHostBasicProductModel(allProductsListwithVoucher);		
		
		allProductsListWithBuy3Get1Applied = HostCartBuy3Get1Calculation.applyBuy3Get1OnTheCart(allProductsListwithVoucher);		
		System.out.println("here:allProductsListWithBuy3Get1Applied");
		PrintUtils.printListHostBasicProductModel(allProductsListWithBuy3Get1Applied);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(allProductsListWithBuy3Get1Applied, allProductsListwithVoucher, discountClass, shippingValue);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
		
	}

}

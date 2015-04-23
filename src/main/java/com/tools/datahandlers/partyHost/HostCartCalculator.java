package com.tools.datahandlers.partyHost;

import java.util.ArrayList;
import java.util.List;

import com.tools.calculation.HostCartBuy3Get1Calculation;
import com.tools.calculation.HostCartTotalsCalculation;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.variables.ContextConstants;

public class HostCartCalculator {

	public static List<HostBasicProductModel> allProductsList = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithoutRegularPrice = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithBuy3Get1Applied = new ArrayList<HostBasicProductModel>();
	public static HostCartCalcDetailsModel calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<HostBasicProductModel>();
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
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculateCartBuy3Get1CartAndShippingTotals(List<HostBasicProductModel> prodList, String discountClass, String shippingValue) {

		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
		allProductsListWithoutRegularPrice = getProductsWithoutRegularPriceFromList(allProductsList);
		allProductsListWithBuy3Get1Applied = HostCartBuy3Get1Calculation.applyBuy3Get1OnTheCart(allProductsListWithRegularPrice);
		allProductsListWithBuy3Get1Applied.addAll(allProductsListWithoutRegularPrice);
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(allProductsListWithBuy3Get1Applied, allProductsListWithRegularPrice, discountClass);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);

	}

}

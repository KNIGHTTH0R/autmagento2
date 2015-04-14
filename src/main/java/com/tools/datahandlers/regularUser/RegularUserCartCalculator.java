package com.tools.datahandlers.regularUser;

import java.util.ArrayList;
import java.util.List;

import com.tools.calculation.RegularCartTotalsCalculation;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.ConfigConstants;

public class RegularUserCartCalculator {

	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsListWithRegularPrice = new ArrayList<RegularBasicProductModel>();
	public static RegularCartCalcDetailsModel calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<RegularBasicProductModel>();
		allProductsListWithRegularPrice = new ArrayList<RegularBasicProductModel>();
		calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
	}

	public static List<RegularBasicProductModel> getProductswithRegularPriceFromList(List<RegularBasicProductModel> productsList) {
		List<RegularBasicProductModel> newProductsList = new ArrayList<RegularBasicProductModel>();
		for (RegularBasicProductModel product : productsList) {
			if (product.getBonusType().contentEquals(ConfigConstants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static void calculateCartAndShippingTotals(List<RegularBasicProductModel> prodList, String discountClass, String shippingValue, String voucherValue) {
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotals(allProductsList, discountClass, voucherValue);
		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculateCartBuy3Get1CartAndShippingTotals(List<RegularBasicProductModel> prodList, String discountClass, String shippingValue, String voucherValue) {
		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotalsWithBuy3Get1Active(allProductsList, allProductsListWithRegularPrice, discountClass, voucherValue);
		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);

	}

}

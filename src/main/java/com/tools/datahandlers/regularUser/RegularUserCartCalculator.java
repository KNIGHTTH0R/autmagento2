package com.tools.datahandlers.regularUser;

import java.util.ArrayList;
import java.util.List;

import com.tools.calculation.RegularCartTotalsCalculation;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;

public class RegularUserCartCalculator {

	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	public static RegularCartCalcDetailsModel calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<RegularBasicProductModel>();
		calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
	}

	public static void calculateCartAndShippingTotals(List<RegularBasicProductModel> prodList, String discountClass, String shippingValue, String voucherValue) {
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotals(allProductsList, discountClass, voucherValue);
		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

}

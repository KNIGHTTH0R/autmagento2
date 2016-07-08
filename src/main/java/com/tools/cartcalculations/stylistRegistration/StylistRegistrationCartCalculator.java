package com.tools.cartcalculations.stylistRegistration;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.StylistRegistrationCartCalcDetailsModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.frontend.StarterSetProductModel;

public class StylistRegistrationCartCalculator {

	public static List<StarterSetProductModel> allProductsList = new ArrayList<StarterSetProductModel>();
	public static StylistRegistrationCartCalcDetailsModel cartCalcDetailsModel = new StylistRegistrationCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {

		allProductsList = new ArrayList<StarterSetProductModel>();
		cartCalcDetailsModel = new StylistRegistrationCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();

	}

	public static void calculateCartAndShippingTotals(String taxClass, String shippingValue, String voucherValue, boolean isVoucherFixSum) {
		cartCalcDetailsModel = StylistRegistrationCartTotalsCalculation.calculateTotals(allProductsList, taxClass, shippingValue, voucherValue, isVoucherFixSum);
		shippingCalculatedModel = StylistRegistrationCartTotalsCalculation.calculateShippingTotals(cartCalcDetailsModel, shippingValue);

	}

}

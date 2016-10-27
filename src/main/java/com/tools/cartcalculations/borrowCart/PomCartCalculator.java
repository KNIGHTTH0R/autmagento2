package com.tools.cartcalculations.borrowCart;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.PomCartCalcDetailsModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.frontend.ShippingModel;

public class PomCartCalculator {

	public static List<PomProductModel> allBorrowedProductsList = new ArrayList<PomProductModel>();
	public static PomCartCalcDetailsModel borrowCartCalcDetailsModel = new PomCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {

		allBorrowedProductsList = new ArrayList<PomProductModel>();
		borrowCartCalcDetailsModel = new PomCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
		
	}

	public static void calculateCartAndShippingTotals(String discountClass, String shippingValue) {
		borrowCartCalcDetailsModel = PomCartTotalsCalculation.calculateTotals(allBorrowedProductsList, discountClass, shippingValue);
		shippingCalculatedModel = PomCartTotalsCalculation.calculateShippingTotals(borrowCartCalcDetailsModel, shippingValue);
	}

}

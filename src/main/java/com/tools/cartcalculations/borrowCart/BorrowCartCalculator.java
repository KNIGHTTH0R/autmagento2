package com.tools.cartcalculations.borrowCart;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.ShippingModel;

public class BorrowCartCalculator {

	public static List<BorrowProductModel> allBorrowedProductsList = new ArrayList<BorrowProductModel>();
	public static BorrowCartCalcDetailsModel borrowCartCalcDetailsModel = new BorrowCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {

		allBorrowedProductsList = new ArrayList<BorrowProductModel>();
		borrowCartCalcDetailsModel = new BorrowCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();

	}

	public static void calculateCartAndShippingTotals(String discountClass, String shippingValue) {
		borrowCartCalcDetailsModel = BorrowCartTotalsCalculation.calculateTotals(allBorrowedProductsList, discountClass, shippingValue);
		shippingCalculatedModel = BorrowCartTotalsCalculation.calculateShippingTotals(borrowCartCalcDetailsModel, shippingValue);
	}
	
	public static void calculateCartAndShippingTotalsDiscountRuleActive(String discountClass,String ruleDiscount, String shippingValue) {
		borrowCartCalcDetailsModel = BorrowCartTotalsCalculation.calculateTotalsDiscountRuleActive(allBorrowedProductsList, discountClass,ruleDiscount, shippingValue);
		shippingCalculatedModel = BorrowCartTotalsCalculation.calculateShippingTotals(borrowCartCalcDetailsModel, shippingValue);
	}

}

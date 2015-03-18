package com.tools.datahandlers.regularUser;

import java.util.ArrayList;
import java.util.List;

import com.tools.calculation.RegularCartTotalsCalculation;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;

public class RegularUserCartCalculator {
	
	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	public static RegularCartCalcDetailsModel calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
	
	public static void wipe(){
		allProductsList = new ArrayList<RegularBasicProductModel>();
		calculatedTotalsDiscounts = new RegularCartCalcDetailsModel();
	}
	
	public static void calculateCartTotals(List<RegularBasicProductModel> prodList,String discountClass){
		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotals(allProductsList, discountClass);
	}

}

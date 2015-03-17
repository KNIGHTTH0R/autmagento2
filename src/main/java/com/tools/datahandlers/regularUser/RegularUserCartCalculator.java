package com.tools.datahandlers.regularUser;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;

public class RegularUserCartCalculator {
	
	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	public static RegularUserCartTotalsModel calculatedTotalsDiscounts = new RegularUserCartTotalsModel();
	
	public static void wipe(){
		allProductsList = new ArrayList<RegularBasicProductModel>();
		calculatedTotalsDiscounts = new RegularUserCartTotalsModel();
	}
	
	public static void calculateCartTtotals(){}

}

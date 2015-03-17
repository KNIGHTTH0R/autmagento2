package com.tools.datahandlers.regularUser;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;

public class RegularUserDataGrabber {


	public static RegularUserCartTotalsModel regularUserCartTotals = new RegularUserCartTotalsModel();
	public static List<RegularUserCartProductModel> productsList = new ArrayList<RegularUserCartProductModel>();

	public static void wipe(){
		
		regularUserCartTotals = new RegularUserCartTotalsModel();

	
	}
	
	
	
}

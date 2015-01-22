package com.tools.calculation;


import java.util.List;

import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;

public class CartTotalCalculation {
	
	public static CalculationModel calculateTableProducts(List<CartProductModel> cartList) {
		CalculationModel result = new CalculationModel();
		double askingPriceSum = 0;
		double finalPriceSum = 0;
		int ipSum = 0;

		if(cartList.size() > 0 ){
			
			result.setTableType(cartList.get(0).getDiscountClass());
			System.out.println(cartList.get(0).getDiscountClass());
	
			for (CartProductModel cartProductModel : cartList) {
				askingPriceSum += PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity());
				ipSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
			}
	
			int calcValue = checkCalculusType(result.getTableType());
			System.out.println("calculation value is: " + calcValue);
			finalPriceSum = askingPriceSum - (askingPriceSum * calcValue / 100);
	
			result.setAskingPrice(askingPriceSum);
			result.setFinalPrice(finalPriceSum);
			result.setIpPoints(ipSum);
		
			System.out.println("----------------------------");
			System.out.println("CALC: askingPriceSum: " + result.getAskingPrice());
			System.out.println("CALC: finalPriceSum: " + result.getFinalPrice());
			System.out.println("CALC: ipSum: " + result.getIpPoints());
			
		}else{
			System.out.println("Failure: Product list is empty!!! - see Calculus Steps");
		}
		return result;

	}	

	public static CalculationModel calculateTotalSum(List<CalculationModel> totalsList) {

		CalculationModel result = new CalculationModel();	
		
		 double askingPrice = 0;
		 double finalPrice = 0;
		 int ipPoints = 0;

		for (CalculationModel total : totalsList) {
			askingPrice += total.getAskingPrice();
			finalPrice += total.getFinalPrice();
			ipPoints += total.getIpPoints();
			
		}
		result.setAskingPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setIpPoints(ipPoints);

		
		return result;
	}

	private static int checkCalculusType(String tableType) {
		int result = 0;

		if (tableType.contentEquals(Constants.DISCOUNT_25)) {
			result = 25;
		}
		if (tableType.contentEquals(Constants.DISCOUNT_50)) {
			result = 50;
		}
		if (tableType.contentEquals(Constants.DISCOUNT_0)) {
			result = 0;
		}
		return result;
	}

}

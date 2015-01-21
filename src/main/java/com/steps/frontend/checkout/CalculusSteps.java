package com.steps.frontend.checkout;


import java.util.List;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.CartProductModel;

public class CalculusSteps extends AbstractSteps {

	private static final long serialVersionUID = -2988085683745584124L;

	public CalculationModel calculateTableProducts(List<CartProductModel> cartList) {
		CalculationModel result = new CalculationModel();
		double askingPriceSum = 0;
		double finalPriceSum = 0;
		int ipSum = 0;

		if(cartList.size() > 0 ){
			
			result.setTableType(cartList.get(0).getDiscountClass());
	
			for (CartProductModel cartProductModel : cartList) {
				askingPriceSum += PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity());
				ipSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
			}
	
	
			int calcValue = checkCalculusType(result.getTableType());
	
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


	private int checkCalculusType(String tableType) {
		int result = 0;

		if (tableType.contains("25")) {
			result = 25;
		}
		if (tableType.contains("50")) {
			result = 50;
		}
		if (tableType.contains("0")) {
			result = 0;
		}
		return result;
	}
	
	


}

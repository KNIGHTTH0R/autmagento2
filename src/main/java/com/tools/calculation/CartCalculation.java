package com.tools.calculation;


import java.math.BigDecimal;
import java.util.List;

import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class CartCalculation {
	
	
	
	/**
	 * Return product price based on (price - quantity - discount). Discount may
	 * be 25 or 50.
	 * 
	 * @param product
	 * @return
	 */
	// TODO move this to calculation - No sense to be in steps
	public static BigDecimal calculateCartProductsByDiscount(CartProductModel product) {

		BigDecimal productPrice = BigDecimal.valueOf(0);

		if (product.getDiscountClass().contentEquals("25")) {
			
			productPrice = productPrice.add(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
//			System.out.println(productPrice);
			productPrice = productPrice.multiply(PrintUtils.cleanNumberToBigDecimal(product.getQuantity()));
//			System.out.println(productPrice);
			productPrice = productPrice.multiply(BigDecimal.valueOf(25));
//			System.out.println(productPrice);
			productPrice = productPrice.divide(BigDecimal.valueOf(100));
//			System.out.println("Final: " + productPrice);
			//TODO old formula
//			productPrice. += (PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()) * PrintUtils.cleanNumberToBigDecimal(product.getQuantity())) * 25 / 100;
		}
		if (product.getDiscountClass().contentEquals("50")) {
			
			productPrice = productPrice.add(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
//			System.out.println(productPrice);
			productPrice = productPrice.multiply(PrintUtils.cleanNumberToBigDecimal(product.getQuantity()));
//			System.out.println(productPrice);
			productPrice = productPrice.multiply(BigDecimal.valueOf(50));
//			System.out.println(productPrice);
			productPrice = productPrice.divide(BigDecimal.valueOf(100));
//			System.out.println("Final: " + productPrice);
		}
		return productPrice;
	}


	
	public static CalculationModel calculateTableProducts(List<CartProductModel> cartList) {
		CalculationModel result = new CalculationModel();
		BigDecimal askingPriceSum = BigDecimal.valueOf(0);
		BigDecimal finalPriceSum = BigDecimal.valueOf(0);
		int ipSum = 0;

		if(cartList.size() > 0 ){
			
			result.setTableType(cartList.get(0).getDiscountClass());
				
			for (CartProductModel cartProductModel : cartList) {
				BigDecimal transport = BigDecimal.valueOf(0);
				transport = transport.add(PrintUtils.cleanNumberToBigDecimal(cartProductModel.getUnitPrice()));
				transport = transport.multiply(PrintUtils.cleanNumberToBigDecimal(cartProductModel.getQuantity()));
				askingPriceSum = askingPriceSum.add(transport);
				
				ipSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
			
			}
	
			int calcValue = checkCalculusType(result.getTableType());
			System.out.println("calculation value is: " + calcValue);
			
			BigDecimal partTwo = BigDecimal.valueOf(0);
			
			partTwo = partTwo.add(askingPriceSum);
			partTwo = partTwo.multiply(BigDecimal.valueOf(calcValue));
			partTwo = partTwo.divide(BigDecimal.valueOf(100));
			
			finalPriceSum = finalPriceSum.add(askingPriceSum);
			finalPriceSum = finalPriceSum.subtract(partTwo);
			
			//TODO old formulas
			//= askingPriceSum - (askingPriceSum * calcValue / 100);
	
			result.setAskingPrice(askingPriceSum);
			result.setFinalPrice(finalPriceSum);
			result.setIpPoints(ipSum);
			
			PrintUtils.printCalculationModel(result);
		
		}else{
			System.out.println("Failure: Product list is empty!!! - see Calculus Steps");
		}
		return result;

	}	

	public static CalculationModel calculateTotalSum(List<CalculationModel> totalsList) {

		CalculationModel result = new CalculationModel();	
		
		 BigDecimal askingPrice = BigDecimal.valueOf(0);
		 BigDecimal finalPrice = BigDecimal.valueOf(0);
		 int ipPoints = 0;
		 

		for (CalculationModel total : totalsList) {

			 System.out.println("**" + total.getAskingPrice());
			 System.out.println("**" + total.getFinalPrice());
			
			if (total.getAskingPrice() != null) {
				askingPrice = askingPrice.add(total.getAskingPrice());
			}
			
			if (total.getFinalPrice() != null) {
				finalPrice = finalPrice.add(total.getFinalPrice());
				
			}
			
			ipPoints += total.getIpPoints();
			
		}
		finalPrice = finalPrice.divide(BigDecimal.valueOf(100));
		askingPrice = askingPrice.divide(BigDecimal.valueOf(100));
		
		
		result.setAskingPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setIpPoints(ipPoints);

		
		return result;
	}
	//TODO hardcoded discount - fix this
	public static CalculationModel recalculateTotalsAfterDiscounts(CalculationModel calculationModel){
		CalculationModel result = new CalculationModel();
		System.out.println(calculationModel.getAskingPrice());
		System.out.println(calculationModel.getFinalPrice());
			result.setAskingPrice(calculationModel.getAskingPrice());
			result.setFinalPrice(calculationModel.getFinalPrice().subtract(BigDecimal.valueOf(250)));
			result.setIpPoints(calculationModel.getIpPoints());
		
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
	
//	public static CalculationModel calculateShippingTotalsWith0Vat(CalculationModel model){
//		
//		CalculationModel result = new CalculationModel();	
//		
//		 BigDecimal askingPrice = model.getAskingPrice();
//		// askingPrice = askingPrice.divide(BigDecimal.valueOf(119)); 
//		 result.setAskingPrice(askingPrice);
//		 
//		 BigDecimal finalPrice = model.getFinalPrice();
//		// askingPrice = askingPrice.divide(BigDecimal.valueOf(119)); 
//		 result.setFinalPrice(finalPrice);
//		 
//		 return result;
//	}

}

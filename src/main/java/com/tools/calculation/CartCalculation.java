package com.tools.calculation;


import java.util.List;

import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;

public class CartCalculation {
	
	/**
	 * Return product price based on (price - quantity - discount). Discount may
	 * be 25 or 50.
	 * 
	 * @param product
	 * @return
	 */

	// TODO move this to calculation - No sense to be in steps
	public static double calculateCartProductsByDiscount(CartProductModel product) {

		double productPrice = 0;

		if (product.getDiscountClass().contentEquals("25")) {
			productPrice += (PrintUtils.cleanNumberToDouble(product.getUnitPrice()) * PrintUtils.cleanNumberToDouble(product.getQuantity())) * 25 / 100;
		}
		if (product.getDiscountClass().contentEquals("50")) {
			productPrice += (PrintUtils.cleanNumberToDouble(product.getUnitPrice()) * PrintUtils.cleanNumberToDouble(product.getQuantity())) * 50 / 100;
		}
		return productPrice;
	}

	/**
	 * Calculate Totals based on a product list
	 * TODO  - change to make only basic calculation - NO DISCOUNT AND SUCH
	 * 
	 * @param productList
	 * @return
	 */
	// TODO move this to calculation - No sense to be in steps
//	public static CartTotalsModel calculateCartProducts(List<CartProductModel> productList) {
//
//		double totalPrice = 0;
//		double discountSum = 0;
//		double totalAmount = 0;
//		int ipPointsSum = 0;
//		double taxSum = 0;
//		int jeverlyBonus = 0;
//		double shiping = 0;
//
//		DecimalFormat df = new DecimalFormat("0.00");
//
//		for (CartProductModel cartProductModel : productList) {
//			double productPrice = 0;
//			productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
//			totalPrice += productPrice;
//			double discount = 0;
//			if (cartProductModel.getDiscountClass() == "25" || cartProductModel.getDiscountClass() == "50") {
//				discount = calculateCartProductsByDiscount(cartProductModel);
//				discountSum += calculateCartProductsByDiscount(cartProductModel);
//			}
//			totalAmount += (PrintUtils.cleanNumberToDouble(cartProductModel.getProductsPrice()) - discount);
//			ipPointsSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
//
//		}
//		taxSum = PrintUtils.getDoubleWithTwoDigits(((totalAmount * 19) / 119));
//
//		CartTotalsModel result = new CartTotalsModel();
//
//		result.setSubtotal(df.format((totalPrice)));
//		result.setDiscount(df.format(discountSum));
//		result.setTotalAmount(df.format(totalAmount));
//		result.setIpPoints(String.valueOf((ipPointsSum)));
//		result.setTax(df.format(taxSum));
//		result.setShipping(df.format(shiping));
//		result.setJewelryBonus(String.valueOf((jeverlyBonus)));
//
//
//		return result;
//	}
	
	public static CalculationModel calculateTableProducts(List<CartProductModel> cartList) {
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
			System.out.println("calculation value is: " + calcValue);
			finalPriceSum = askingPriceSum - (askingPriceSum * calcValue / 100);
	
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

package com.tools.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.tools.Constants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.frontend.BasicProductModel;

public class CartTotalsCalculation {
	
	public static CalcDetailsModel calculateCartProductsTotals(List<BasicProductModel> productsList, String jewerlyDiscount, String marketingDiscount, String taxClass) {
		CalcDetailsModel result = new CalcDetailsModel();

		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList, Constants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}
		
		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
				rabatt25, rabattBuy3Get1);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(Constants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(Constants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));

		return result;
	}
	
	private static BigDecimal calculate50Discount(List<BasicProductModel> productsList) {

		BigDecimal discountSum = BigDecimal.ZERO;

		for (BasicProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(Constants.DISCOUNT_50)) {
				discountSum = discountSum.add(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getFinalPrice())));
			}
		}
		return discountSum;

	}

	private static BigDecimal calculate25Discount(List<BasicProductModel> productsList, BigDecimal jewelryDiscount, BigDecimal sum25Section) {

		BigDecimal discountSum = BigDecimal.ZERO;

		for (BasicProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(Constants.DISCOUNT_25)) {
				discountSum = discountSum.add(calculate25DiscountForEachProduct(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getProductsPrice())), jewelryDiscount,
						sum25Section));	
			}

		}

		return discountSum;

	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount, BigDecimal marketingDiscount, BigDecimal sum50Discount,
			BigDecimal sum25Discount, BigDecimal buy3Get1) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		result = result.subtract(sum25Discount);
		result = result.subtract(sum50Discount);
		result = result.subtract(buy3Get1);

		return result;
	}
	
	private static BigDecimal calculate25DiscountForEachProduct(BigDecimal askingPrice, BigDecimal jB, BigDecimal sum25Section) {

		BigDecimal result = BigDecimal.ZERO;
		if (sum25Section.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;
		} else {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);
			result = result.multiply(BigDecimal.valueOf(25));
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		}
		return result;
	}
	
	public static CalcDetailsModel calculateCartProductsTotalsBuy3GetOneRuleApplied(List<BasicProductModel> productsList, String jewerlyDiscount, String marketingDiscount,
			String taxClass) {
		CalcDetailsModel result = new CalcDetailsModel();

		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList, Constants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList);
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
	

		for (BasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
				rabatt25, rabattBuy3Get1);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(Constants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(Constants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList)));

		return result;
	}
	
	

}
//=======
//package com.tools.calculation;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import com.tools.Constants;
//import com.tools.data.CalcDetailsModel;
//import com.tools.data.frontend.BasicProductModel;
//import com.tools.data.frontend.CartProductModel;
//
//public class CartTotalsCalculation {
//	
//	public static CalcDetailsModel calculateCartProductsTotals(List<BasicProductModel> productsList, String jewerlyDiscount, String marketingDiscount, String taxClass) {
//		CalcDetailsModel result = new CalcDetailsModel();
//
//		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList, Constants.DISCOUNT_25);
//
//		BigDecimal subtotal = BigDecimal.ZERO;
//		BigDecimal rabatt50 = BigDecimal.ZERO;
//		BigDecimal rabatt25 = BigDecimal.ZERO;
//		BigDecimal rabattBuy3Get1 = BigDecimal.ZERO;
//		BigDecimal tax = BigDecimal.ZERO;
//		BigDecimal totalAmount = BigDecimal.ZERO;
//		BigDecimal ipPoints = BigDecimal.ZERO;
//
//		for (BasicProductModel product : productsList) {
//			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
//			rabatt50 = calculate50Discount(productsList);
//			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
//			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
//		}
//
//		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
//				rabatt25, rabattBuy3Get1);
//
//		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
//		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
//
//		result.setSubTotal(String.valueOf(subtotal));
//		result.setJewelryBonus(jewerlyDiscount);
//		result.setMarketingBonus(marketingDiscount);
//		result.setTotalAmount(String.valueOf(totalAmount));
//		result.setIpPoints(String.valueOf(ipPoints.intValue()));
//		result.setTax(String.valueOf(tax));
//		result.addSegment(Constants.DISCOUNT_50, String.valueOf(rabatt50));
//		result.addSegment(Constants.DISCOUNT_25, String.valueOf(rabatt25));
//		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));
//
//		return result;
//	}
//	
//	private static BigDecimal calculate50Discount(List<BasicProductModel> productsList) {
//
//		BigDecimal discountSum = BigDecimal.ZERO;
//
//		for (BasicProductModel cartProductModel : productsList) {
//			if (cartProductModel.getDiscountClass().contains(Constants.DISCOUNT_50)) {
//				discountSum = discountSum.add(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getFinalPrice())));
//			}
//		}
//		return discountSum;
//
//	}
//
//	private static BigDecimal calculate25Discount(List<BasicProductModel> productsList, BigDecimal jewelryDiscount, BigDecimal sum25Section) {
//
//		BigDecimal discountSum = BigDecimal.ZERO;
//
//		for (BasicProductModel cartProductModel : productsList) {
//			if (cartProductModel.getDiscountClass().contains(Constants.DISCOUNT_25)) {
//				discountSum = discountSum.add(calculate25DiscountForEachProduct(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getProductsPrice())), jewelryDiscount,
//						sum25Section));
//			}
//
//		}
//		return discountSum;
//
//	}
//
//	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount, BigDecimal marketingDiscount, BigDecimal sum50Discount,
//			BigDecimal sum25Discount, BigDecimal buy3Get1) {
//
//		BigDecimal result = BigDecimal.ZERO;
//
//		result = result.add(subtotal);
//		result = result.subtract(jewelryDiscount);
//		result = result.subtract(marketingDiscount);
//		result = result.subtract(sum25Discount);
//		result = result.subtract(sum50Discount);
//		result = result.subtract(buy3Get1);
//
//		return result;
//	}
//	
//	private static BigDecimal calculate25DiscountForEachProduct(BigDecimal askingPrice, BigDecimal jB, BigDecimal sum25Section) {
//
//		BigDecimal result = BigDecimal.ZERO;
//		if (askingPrice.compareTo(jB) < 0) {
//			result = BigDecimal.ZERO;
//		} else {
//
//			result = result.add(askingPrice);
//			result = result.multiply(BigDecimal.valueOf(100));
//			result = result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
//			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
//			result = result.multiply(jB);
//			result = askingPrice.subtract(result);
//			result = result.multiply(BigDecimal.valueOf(25));
//			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
//		}
//		return result;
//	}
//	
//	public static CalcDetailsModel calculateCartProductsTotalsBuy3GetOneRuleApplied(List<BasicProductModel> productsList, String jewerlyDiscount, String marketingDiscount,
//			String taxClass) {
//		CalcDetailsModel result = new CalcDetailsModel();
//
//		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList, Constants.DISCOUNT_25);
//
//		BigDecimal subtotal = BigDecimal.ZERO;
//		BigDecimal rabatt50 = BigDecimal.ZERO;
//		BigDecimal rabatt25 = BigDecimal.ZERO;
//		BigDecimal rabattBuy3Get1 = CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList);
//		BigDecimal tax = BigDecimal.ZERO;
//		BigDecimal totalAmount = BigDecimal.ZERO;
//		BigDecimal ipPoints = BigDecimal.ZERO;
//	
//
//		for (BasicProductModel product : productsList) {
//			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
//			rabatt50 = calculate50Discount(productsList);
//			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
//			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
//		}
//
//		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
//				rabatt25, rabattBuy3Get1);
//
//		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
//		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
//
//		result.setSubTotal(String.valueOf(subtotal));
//		result.setJewelryBonus(jewerlyDiscount);
//		result.setMarketingBonus(marketingDiscount);
//		result.setTotalAmount(String.valueOf(totalAmount));
//		result.setIpPoints(String.valueOf(ipPoints.intValue()));
//		result.setTax(String.valueOf(tax));
//		result.addSegment(Constants.DISCOUNT_50, String.valueOf(rabatt50));
//		result.addSegment(Constants.DISCOUNT_25, String.valueOf(rabatt25));
//		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList)));
//
//		return result;
//	}
//	
//	
//
//}


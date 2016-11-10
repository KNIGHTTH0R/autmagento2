package com.tools.cartcalculations.smf;

import java.math.BigDecimal;
import java.util.List;

import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.constants.ConfigConstants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.frontend.BasicProductModel;

public class CartTotalsCalculation {

	public static CalcDetailsModel calculateCartProductsTotals(List<BasicProductModel> productsList,
			String jewerlyDiscount, String marketingDiscount, String taxClass, String shipping,
			String shippingForLessThan150) {
		CalcDetailsModel result = new CalcDetailsModel();
		String shippingValue;
		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList,
				ConfigConstants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherDiscount = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
					sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
				BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50, rabatt25, rabattBuy3Get1);

		shippingValue = Double.parseDouble(String.valueOf(totalAmount)) >= 150 ? shipping : shippingForLessThan150;

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(ConfigConstants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherDiscount));

		return result;
	}

	public static CalcDetailsModel calculateCartProductsTotalsWithDiscountRuleActive(
			List<BasicProductModel> productsList, String discountRule, String jewerlyDiscount, String marketingDiscount,
			String taxClass, String shipping) {
		CalcDetailsModel result = new CalcDetailsModel();
		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList,
				ConfigConstants.DISCOUNT_25);

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
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
					sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmountWithDiscountRuleActive(subtotal,
				BigDecimal.valueOf(Double.parseDouble(discountRule)),
				BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
				BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50, rabatt25, rabattBuy3Get1);

		shipping = GeneralCartCalculations.calculateNewShipping(subtotal,
				BigDecimal.valueOf(Double.parseDouble(discountRule)), BigDecimal.valueOf(Double.parseDouble(shipping)));

		System.out.println(shipping);

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shipping)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(ConfigConstants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(discountRule));

		return result;
	}

	private static BigDecimal calculate50Discount(List<BasicProductModel> productsList) {

		BigDecimal discountSum = BigDecimal.ZERO;

		for (BasicProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(ConfigConstants.DISCOUNT_50)) {
				discountSum = discountSum.add(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getFinalPrice())));
			}
		}
		return discountSum;

	}

	private static BigDecimal calculate25Discount(List<BasicProductModel> productsList, BigDecimal jewelryDiscount,
			BigDecimal sum25Section) {

		BigDecimal discountSum = BigDecimal.ZERO;
		BigDecimal delta = BigDecimal.ZERO;

		for (BasicProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(ConfigConstants.DISCOUNT_25)) {
				BigDecimal[] discountAndRemainder = calculate25DiscountForEachProduct(
						BigDecimal.valueOf(Double.parseDouble(cartProductModel.getProductsPrice())), jewelryDiscount,
						sum25Section, delta);
				discountSum = discountSum.add(discountAndRemainder[0]);
				delta = discountAndRemainder[1];
			}
		}

		return discountSum;

	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount,
			BigDecimal marketingDiscount, BigDecimal sum50Discount, BigDecimal sum25Discount, BigDecimal buy3Get1) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		result = result.subtract(sum25Discount);
		result = result.subtract(sum50Discount);
		result = result.subtract(buy3Get1);

		result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;

		return result;
	}

	private static BigDecimal calculateTotalAmountWithDiscountRuleActive(BigDecimal subtotal, BigDecimal ruleDiscount,
			BigDecimal jewelryDiscount, BigDecimal marketingDiscount, BigDecimal sum50Discount,
			BigDecimal sum25Discount, BigDecimal buy3Get1) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(ruleDiscount);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		result = result.subtract(sum25Discount);
		result = result.subtract(sum50Discount);
		result = result.subtract(buy3Get1);

		result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;

		return result;
	}

	private static BigDecimal[] calculate25DiscountForEachProduct(BigDecimal askingPrice, BigDecimal jB,
			BigDecimal sum25Section, BigDecimal deltaDiscount) {

		// BigDecimal result = BigDecimal.ZERO;
		// if (sum25Section.compareTo(jB) < 0) {
		// result = BigDecimal.ZERO;
		// } else {
		//
		// result = result.add(askingPrice);
		// result = result.multiply(BigDecimal.valueOf(100));
		// result = result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
		// result = result.divide(BigDecimal.valueOf(100), 10,
		// BigDecimal.ROUND_HALF_UP);
		// result = result.multiply(jB);
		// result = askingPrice.subtract(result);
		// result = result.multiply(BigDecimal.valueOf(25));
		// result = result.divide(BigDecimal.valueOf(100), 2,
		// BigDecimal.ROUND_HALF_UP);
		// }
		// return result;

		BigDecimal[] discountAndRemainder = new BigDecimal[2];

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;

		if (sum25Section.compareTo(jB) > 0) {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 4, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);

			result = result.multiply(BigDecimal.valueOf(25));
			// the 25% disc is calculated with 5 decimals precision (we don't
			// want the 4th decimal rounded)
			diff = result.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
			diff = diff.add(deltaDiscount);// add delta discount from previous
											// product
			// the calculated discount is rounded to 2 decimals- actual discount
			result = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
			// the calculated discount is rounded to 4 decimals (expected)
			diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
			// we calculate the remaining discount difference to be applied on
			// the next product
			diff = diff.subtract(result);

		}
		discountAndRemainder[0] = result.setScale(2, BigDecimal.ROUND_HALF_UP);
		discountAndRemainder[1] = diff.setScale(4, BigDecimal.ROUND_HALF_UP);

		return discountAndRemainder;
	}

	public static CalcDetailsModel calculateCartProductsTotalsBuy3GetOneRuleApplied(
			List<BasicProductModel> productsList, String jewerlyDiscount, String marketingDiscount, String taxClass,
			String shipping, String shippingForLessThan150) {
		CalcDetailsModel result = new CalcDetailsModel();
		String shippingValue;

		BigDecimal sum25 = CartDiscountsCalculation.calculateDiscountAskingPriceSum(productsList,
				ConfigConstants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal voucherDiscount = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList);
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
					sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)),
				BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50, rabatt25, rabattBuy3Get1);

		shippingValue = Double.parseDouble(String.valueOf(totalAmount)) >= 150 ? shipping : shippingForLessThan150;

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(ConfigConstants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1,
				String.valueOf(CartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsList)));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherDiscount));

		return result;
	}

}
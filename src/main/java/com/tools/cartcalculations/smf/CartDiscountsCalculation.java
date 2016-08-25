package com.tools.cartcalculations.smf;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.constants.ConfigConstants;
import com.tools.data.frontend.BasicProductModel;

/**
 * @author mihai
 *
 */
public class CartDiscountsCalculation {

	public static String calculateAskingPrice(String unitPrice, String quantity) {

		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(unitPrice));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));

		return String.valueOf(price.multiply(qty));

	}

	public static String calculateIpPoints(String ip, String quantity) {

		BigDecimal IP = BigDecimal.valueOf(Double.parseDouble(ip));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));

		return String.valueOf(IP.multiply(qty).intValue());
	}

	/**
	 * @param askingPrice
	 * @param discount
	 * @param delta
	 * @return  Returns the final price based on delta which remains from the previous product
	 */
	public static String calculateFinalPrice(String askingPrice, String discount, String delta) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		BigDecimal disc = BigDecimal.valueOf(Double.parseDouble(discount));
		BigDecimal deltaAmount = BigDecimal.valueOf(Double.parseDouble(delta));

		//delta is 0 for sample and marketing material products
		if (discount != ConfigConstants.DISCOUNT_25)
			deltaAmount = BigDecimal.ZERO;

		discountValue = askPrice.multiply(disc);
		//we calculate the result with 5 decimals in order to have the four decimals unmodified
		diff = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
		//the delta is added to the discount
		diff = diff.add(deltaAmount);
		// the discount after delta is added must have 2 decimals
		discountValue = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
		// the difference (the remaining delta for the next product must have 4 decimals)
		diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
		//the diff will be the result after substracting the 2 decimals result from the 4 decimals rto haesult (Ex: 13.4550 - 13.45 = -0.0050)
		diff = diff.subtract(discountValue);
		//we set the value of diff for the static variable CartCalculator.delta
		CartCalculator.delta = String.valueOf(diff);
		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2));

	}

	public static String calculateFinalPriceRegularCart(String askingPrice) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);

		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static String calculateFinalPriceRegular(String askingPrice) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);

		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static List<BasicProductModel> calculateProductsforMarketingMaterial(List<BasicProductModel> productsList,
			String marketingDiscount) {

		BigDecimal sumMarketingMaterial = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_0);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculateMarketingMaterialCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), sumMarketingMaterial));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor50Discount(List<BasicProductModel> productsList,
			List<BasicProductModel> list25DiscountProducts, String jewelryDiscount) {

		BigDecimal sum50 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_50);
		BigDecimal jewelryUsed = calculateUsedJewelryBonus(list25DiscountProducts, jewelryDiscount);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculate50DiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), jewelryUsed,
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum50));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor25Discount(List<BasicProductModel> productsList,
			String jewelryDiscount) {

		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_25);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		BigDecimal delta = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct
					.setPriceIP(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())),
							BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25));

			String[] discounts = calculate25DiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25, delta);

			newProduct.setFinalPrice(discounts[0]);

			delta = BigDecimal.valueOf(Double.parseDouble(discounts[1]));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateAskingPriceWithActiveDiscountRule(
			List<BasicProductModel> productsList, String ruleDiscount, BigDecimal productsSum) {

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculateRuleDiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())),
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(ruleDiscount)), productsSum));
			product.getFinalPrice();
			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static BigDecimal calculateUsedJewelryBonus(List<BasicProductModel> productsList, String jewelryDiscount) {

		BigDecimal jBRegularItems = BigDecimal.ZERO;
		BigDecimal sum25 = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {

			if (product.getDiscountClass().contains(ConfigConstants.DISCOUNT_25)) {
				sum25 = sum25.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		if (sum25.compareTo(BigDecimal.valueOf(Double.parseDouble(jewelryDiscount))) < 0) {
			jBRegularItems = sum25;
		} else {
			jBRegularItems = BigDecimal.valueOf(Double.parseDouble(jewelryDiscount));
		}

		return jBRegularItems;

	}

	public static BigDecimal calculateDiscountAskingPriceSum(List<BasicProductModel> productsList,
			String discountType) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BasicProductModel product : productsList) {

			if (product.getDiscountClass().contains(discountType)) {
				sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		return sum;
	}

	public static BigDecimal calculateSubtotal(List<BasicProductModel> productsList) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BasicProductModel product : productsList) {

			sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
		}
		return sum;
	}

	public static String calculateIpForEachProduct(BigDecimal initialIpNumber, BigDecimal jB, BigDecimal sum25) {

		BigDecimal result = BigDecimal.ZERO;
		if (sum25.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(jB);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25, 4);
			result = result.multiply(initialIpNumber);
			result = result.divide(BigDecimal.valueOf(100), 2);
			result = initialIpNumber.subtract(result);
			if (result.compareTo(BigDecimal.ZERO) < 0) {
				result = BigDecimal.ZERO;
			}
		}
		return result.setScale(0, RoundingMode.HALF_UP).toString();

	}

	public static String calculate50DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jBUsedForRegular,
			BigDecimal jB, BigDecimal sampleAskingSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = jB.subtract(jBUsedForRegular);
		result = result.multiply(askingPrice);
		result = result.divide(sampleAskingSum, 4, BigDecimal.ROUND_DOWN);
		result = askingPrice.subtract(result);
		result = result.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
		return String.valueOf(result.setScale(2));
	}

	/**
	 * @param askingPrice
	 * @param jB
	 * @param sum25Section
	 * 
	 * @return Returns the discount applied for a product and the remaining
	 *         discount to be applied on the next product
	 */
	public static String[] calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB,
			BigDecimal sum25Section, BigDecimal deltaDiscount) {

		String[] discountAndRemainder = new String[2];

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;

		if (sum25Section.compareTo(jB) > 0) {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 4, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);
			BigDecimal temp = result;

			result = result.multiply(BigDecimal.valueOf(25));
			// the 25% disc is calculated with 5 decimals precision (we don't
			// want the 4th decimal rounded)
			diff = result.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
			diff = diff.add(deltaDiscount);// add delta discount from previous
											// product
			System.out.println("fucking diff: " + diff);
			// the calculated discount is rounded to 2 decimals- actual discount
			result = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
			// the calculated discount is rounded to 4 decimals (expected)
			diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
			// we calculate the remaining discount difference to be applied on
			// the next product
			diff = diff.subtract(result);
			result = temp.subtract(result);

		}
		discountAndRemainder[0] = String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[1] = String.valueOf(diff.setScale(4, BigDecimal.ROUND_HALF_UP));

		return discountAndRemainder;
	}

	public static String calculateRuleDiscountCartProductFinalPrice(BigDecimal finalPrice, BigDecimal askingPrice,
			BigDecimal ruleDiscount, BigDecimal totalAmount) {

		BigDecimal result = BigDecimal.ZERO;
		if (totalAmount.compareTo(ruleDiscount) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(askingPrice);
			result = result.multiply(ruleDiscount);
			result = result.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP);
			result = finalPrice.subtract(result);
			result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
		}

		return String.valueOf(result.setScale(2));
	}

	public static String calculateMarketingMaterialCartProductFinalPrice(BigDecimal askingPrice,
			BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (sumMarketingMaterial.compareTo(marketingDiscount) < 0) {
			result = BigDecimal.ZERO;
		} else {
			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sumMarketingMaterial, 2, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(marketingDiscount);

			result = askingPrice.subtract(result);
		}
		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

}

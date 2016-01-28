package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.BasicProductModel;
import com.tools.env.constants.ConfigConstants;

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

	public static String calculateFinalPrice(String askingPrice, String discount) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		BigDecimal disc = BigDecimal.valueOf(Double.parseDouble(discount));

		discountValue = askPrice.multiply(disc);
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);

		result = askPrice.subtract(discountValue);
		System.out.println(result.setScale(4, BigDecimal.ROUND_HALF_UP));

		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	public static List<BasicProductModel> calculateProductsforMarketingMaterial(List<BasicProductModel> productsList, String marketingDiscount) {

		BigDecimal sumMarketingMaterial = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_0);

		System.out.println(sumMarketingMaterial);

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
			newProduct.setFinalPrice(calculateMarketingMaterialCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), sumMarketingMaterial));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor50Discount(List<BasicProductModel> productsList, List<BasicProductModel> list25DiscountProducts,
			String jewelryDiscount) {

		BigDecimal sum50 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_50);
		BigDecimal jewelryUsed = calculateUsedJewelryBonus(list25DiscountProducts, jewelryDiscount);

		System.out.println(sum50);
		System.out.println(jewelryDiscount);
		System.out.println(jewelryUsed);

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
			newProduct.setFinalPrice(calculate50DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), jewelryUsed,
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum50));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor25Discount(List<BasicProductModel> productsList, String jewelryDiscount) {

		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_25);

		System.out.println(sum25);
		System.out.println(jewelryDiscount);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())), BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)),
					sum25));
			newProduct.setFinalPrice(calculate25DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateAskingPriceWithActiveDiscountRule(List<BasicProductModel> productsList, String ruleDiscount) {

		BigDecimal sum = calculateSubtotal(productsList);

		System.out.println(sum);
		System.out.println(ruleDiscount);

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
			newProduct.setFinalPrice(calculateRuleDiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())),
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), BigDecimal.valueOf(Double.parseDouble(ruleDiscount)), sum));
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

	public static BigDecimal calculateDiscountAskingPriceSum(List<BasicProductModel> productsList, String discountType) {
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

	public static String calculate50DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jBUsedForRegular, BigDecimal jB, BigDecimal sampleAskingSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = jB.subtract(jBUsedForRegular);
		result = result.multiply(askingPrice);
		result = result.divide(sampleAskingSum, 5, BigDecimal.ROUND_DOWN);
		result = askingPrice.subtract(result);
		result = result.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
		System.out.println("REZ-----------------------------------------------------------------------------------------------------------" + result.setScale(3));
		return String.valueOf(result.setScale(2));
	}

	public static String calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB, BigDecimal sum25Section) {

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
			result = result.multiply(BigDecimal.valueOf(75));
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

		}

		return String.valueOf(result.setScale(2));
	}

	public static String calculateRuleDiscountCartProductFinalPrice(BigDecimal finalPrice, BigDecimal askingPrice, BigDecimal ruleDiscount, BigDecimal totalAmount) {

		BigDecimal result = BigDecimal.ZERO;
		if (totalAmount.compareTo(ruleDiscount) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP);
			result = finalPrice.subtract(result);

		}

		return String.valueOf(result.setScale(2));
	}

	public static String calculateMarketingMaterialCartProductFinalPrice(BigDecimal askingPrice, BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (askingPrice.compareTo(marketingDiscount) < 0) {
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

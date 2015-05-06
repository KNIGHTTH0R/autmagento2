package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.env.constants.ConfigConstants;

public class OrderForCustomerDiscountsCalculation {

	public static List<HostBasicProductModel> calculateProductsWithVoucherApplied(List<HostBasicProductModel> productsList, String voucherValue) {

		BigDecimal sum = calculateProductsSum(productsList);

		List<HostBasicProductModel> cartProducts = new ArrayList<HostBasicProductModel>();

		for (HostBasicProductModel product : productsList) {

			HostBasicProductModel newProduct = new HostBasicProductModel();

			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());

			newProduct.setIpPoints(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())), BigDecimal.valueOf(Double.parseDouble(voucherValue)),
					sum));
			newProduct.setFinalPrice(product.getFinalPrice());
			newProduct.setBonusType(product.getBonusType());
			newProduct.setBunosValue(product.getBunosValue());

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

	public static BigDecimal calculateProductsSum(List<HostBasicProductModel> productsList) {
		BigDecimal sum = BigDecimal.ZERO;
		for (HostBasicProductModel product : productsList) {
			sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
		}
		return sum;
	}

	public static String calculateIpForEachProduct(BigDecimal initialIpNumber, BigDecimal voucherValue, BigDecimal sum) {

		BigDecimal result = BigDecimal.ZERO;
		if (sum.compareTo(voucherValue) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(voucherValue);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum, 4);
			result = result.multiply(initialIpNumber);
			result = result.divide(BigDecimal.valueOf(100), 2);
			result = initialIpNumber.subtract(result);
			if (result.compareTo(BigDecimal.ZERO) < 0) {
				result = BigDecimal.ZERO;
			}
		}
		return result.setScale(0, RoundingMode.HALF_UP).toString();

	}

}

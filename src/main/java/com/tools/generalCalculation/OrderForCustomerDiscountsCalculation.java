package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.constants.ConfigConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.HostBasicProductModel;

public class OrderForCustomerDiscountsCalculation {

	public static List<HostBasicProductModel> calculateProductsWithVoucherApplied(List<HostBasicProductModel> productsList, String voucherValue) {

		BigDecimal sum = calculateProductsSum(productsList);
		BigDecimal totalQty = calculateTotalProductQty(productsList);
		List<HostBasicProductModel> cartProducts = new ArrayList<HostBasicProductModel>();

		for (HostBasicProductModel product : productsList) {

			HostBasicProductModel newProduct = new HostBasicProductModel();

			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setDiscountValue(calculateDiscountValue(totalQty,BigDecimal.valueOf(Double.parseDouble(product.getQuantity())),BigDecimal.valueOf(Double.parseDouble(voucherValue))));
			newProduct.setIpPoints(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())), BigDecimal.valueOf(Double.parseDouble(voucherValue)),
					sum));
			newProduct.setFinalPrice(product.getFinalPrice());
			newProduct.setBonusType(product.getBonusType());
			newProduct.setBunosValue(product.getBunosValue());
			System.out.println("setBunosValue" + newProduct.getBunosValue());
			newProduct.setDeliveryDate(product.getDeliveryDate());

			cartProducts.add(newProduct);
			
			
		}
		System.out.println(cartProducts.toString());
		
		return cartProducts;

	}
	
	private static BigDecimal calculateTotalProductQty(List<HostBasicProductModel> productsList) {
		BigDecimal totalQty = BigDecimal.ZERO;
		
		
		for (HostBasicProductModel product : productsList) {
			totalQty=totalQty.add(BigDecimal.valueOf(Double.parseDouble(product.getQuantity())));
		}
		
		return totalQty;
	}

	public static BigDecimal calculateUsedJewelryBonus(List<BasicProductModel> productsList, String jewelryDiscount) {

		BigDecimal jBRegularItems = BigDecimal.ZERO;
		BigDecimal sum25 = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {

			if (product.getDiscountClass().contains(ConfigConstants.DISCOUNT_20)) {
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
		System.out.println(result);
		return result.setScale(0, RoundingMode.HALF_UP).toString();

	}
	
	public static String calculateDiscountValue(BigDecimal productTotalQty,BigDecimal productQty, BigDecimal voucherValue) {

		BigDecimal result = BigDecimal.ZERO;
		if (result.compareTo(voucherValue) == 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(voucherValue);
			result = result.divide(productTotalQty,4, RoundingMode.HALF_UP);
			result=result.multiply(productQty);
			if (result.compareTo(BigDecimal.ZERO) < 0) {
				result = BigDecimal.ZERO;
			}
		}
		return result.setScale(4, RoundingMode.HALF_UP).toString();

	}
	
	
	public static BigDecimal calculateIpValue(String finalPrice, String  productDiscount) {
		Double calculateItemValue=Double.valueOf(finalPrice)-Double.valueOf(productDiscount);
		Double ipNewValue=calculateItemValue*0.84;
		return BigDecimal.valueOf(ipNewValue).setScale(0,RoundingMode.HALF_UP);
	}

	public static BigDecimal productDiscount(String productQty, String totalProductQty, String discountPrice){
		Double discountPerItem= Double.valueOf(discountPrice)/Double.valueOf(totalProductQty);
		Double productDiscount= Double.valueOf(discountPerItem)*Double.valueOf(productQty);
		return BigDecimal.valueOf(productDiscount).setScale(2, RoundingMode.HALF_UP);
		
	}
	
	

	
	public static void main(String[] args) {
		BigDecimal initialIpNumbe1r=BigDecimal.valueOf(155.2300);
		//BigDecimal voucherValue1=BigDecimal.valueOf(16.67);
		//BigDecimal sum1=BigDecimal.valueOf(25);
					
		
		System.out.println(calculateProductTaxAmount(initialIpNumbe1r));
		//System.out.println(calculateIpForEachProduct(initialIpNumbe1r,voucherValue1,sum1));
	}

	public static List<HostBasicProductModel> calculateProductsForCM(List<HostBasicProductModel> products,
			String discountPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<HostBasicProductModel> recalculateProducts(List<HostBasicProductModel> allProductsList,
			String voucherValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public static BigDecimal calculateRowTotal(BigDecimal finalPrice, BigDecimal discountValue) {
		BigDecimal rowTotal=BigDecimal.ZERO;
		
		if (rowTotal.compareTo(finalPrice) == 0) {
			rowTotal = BigDecimal.ZERO;

		}else{
			rowTotal=rowTotal.add(finalPrice);
			rowTotal=rowTotal.subtract(discountValue);
		}
		
		return rowTotal.setScale(4, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculateProductTaxAmount(BigDecimal rowTotal) {
		BigDecimal taxAmount=BigDecimal.ZERO;
		BigDecimal taxAmountValue=BigDecimal.valueOf(1.19);
		
		
		if (taxAmount.compareTo(rowTotal) == 0) {
			taxAmount = BigDecimal.ZERO;

		} else{
			taxAmount=taxAmount.add(rowTotal);
			taxAmount=taxAmount.divide(taxAmountValue,6, RoundingMode.HALF_UP);
			taxAmount=rowTotal.subtract(taxAmount);
		}
		
		return taxAmount.setScale(2, RoundingMode.HALF_UP);
	}
}

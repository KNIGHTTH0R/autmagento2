package com.tools.cartcalculations.regularUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.utils.DateUtils;
import com.tools.utils.PrintUtils;

public class RegularCartTotalsCalculation {

	public static List<RegularBasicProductModel> calculateProductsWithVoucherApplied(
			List<RegularBasicProductModel> productsList, String voucherValue) {

		BigDecimal sum = calculateProductsSum(productsList);

		List<RegularBasicProductModel> cartProducts = new ArrayList<RegularBasicProductModel>();

		for (RegularBasicProductModel product : productsList) {

			RegularBasicProductModel newProduct = new RegularBasicProductModel();

			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setIpPoints(
					calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())),
							BigDecimal.valueOf(Double.parseDouble(voucherValue)), sum));
			newProduct.setFinalPrice(product.getFinalPrice());
			newProduct.setBonusType(product.getBonusType());
			newProduct.setBunosValue(product.getBunosValue());
			newProduct.setDeliveryDate(product.getDeliveryDate());

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static BigDecimal calculateProductsSum(List<RegularBasicProductModel> productsList) {
		BigDecimal sum = BigDecimal.ZERO;
		for (RegularBasicProductModel product : productsList) {
			sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
		}
		return sum;
	}

	public static String calculateIpForEachProduct(BigDecimal initialIpNumber, BigDecimal voucherValue,
			BigDecimal sum) {

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

	public static TermPurchaseIpModel calculateTermPurchaseIpPoints(List<RegularBasicProductModel> productsList)
			throws NumberFormatException, ParseException {

		TermPurchaseIpModel ipModel = new TermPurchaseIpModel();
		BigDecimal currentMonthIp = BigDecimal.ZERO;
		BigDecimal nextMonthIp = BigDecimal.ZERO;

		for (RegularBasicProductModel product : productsList) {
			if (product.getDeliveryDate() != null) {
				if (DateUtils.isDateInCurrentMonth(product.getDeliveryDate(), "yyyy-MM-dd")) {
					currentMonthIp = currentMonthIp.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
				} else if (DateUtils.isDateInNextMonth(product.getDeliveryDate(), "yyyy-MM-dd")) {
					nextMonthIp = nextMonthIp.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
				}
			}
		}
		ipModel.setCurrentMonthIp(String.valueOf(currentMonthIp));
		ipModel.setNextMonthIp(String.valueOf(nextMonthIp));

		return ipModel;

	}

	public static BigDecimal calculate40DiscountForIp(String ipPoints) {

		BigDecimal ip = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		BigDecimal reducedIp = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		reducedIp = reducedIp.multiply(BigDecimal.valueOf(30));
		reducedIp = reducedIp.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);

		return ip.subtract(reducedIp);
	}

	public static RegularCartCalcDetailsModel calculateTotals(List<RegularBasicProductModel> productsList,
			String taxClass, String voucherValue, String shippingValue) {
		RegularCartCalcDetailsModel result = new RegularCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));

		for (RegularBasicProductModel product : productsList) {
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				System.out.println("30% no rounding "+forthyDiscount );
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
				System.out.println("30% no rounding "+forthyDiscount);
			}
		}

		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherPrice);
		System.out.println("totalAmount-> " + totalAmount);
		shippingValue = GeneralCartCalculations.calculateNewShipping(subtotal,
				BigDecimal.valueOf(Double.parseDouble(voucherValue)),
				BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		System.out.println("shippingValue-> " + shippingValue);

		if (totalAmount.compareTo(BigDecimal.valueOf(800)) > 0) {
			shippingValue = "0";
		}
		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);
		System.out.println("tax-> " + tax);
		//System.out.println("ip Points-> " + String.valueOf(ipPoints.setScale(0)));
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints.setScale(0)));
		System.out.println("Ippp points: "+result.getIpPoints());
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount.setScale(2, RoundingMode.HALF_UP)));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_SHIPPING, String.valueOf(voucherValue));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherValue));

		if (voucherPrice.compareTo(subtotal) > 0) {
			result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(subtotal));
		}
		return result;
	}

	public static void main(String[] args) {
		String shipping="3.9";
		BigDecimal totalAmount=BigDecimal.valueOf(297.6);
		if (totalAmount.compareTo(BigDecimal.valueOf(800)) > 0) {
			shipping = "0";
		}
		System.out.println(shipping);
	}

	public static RegularCartCalcDetailsModel calculateTotalsWithBuy3Get1Active(
			List<RegularBasicProductModel> productsList, List<RegularBasicProductModel> productsListForBuy3Get1,
			String taxClass, String voucherValue, String shippingValue) {

		RegularCartCalcDetailsModel result = new RegularCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = RegularCartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsListForBuy3Get1);
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));

		for (RegularBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherPrice);

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_SHIPPING, String.valueOf(voucherValue));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherValue));

		if (voucherPrice.compareTo(subtotal) > 0) {
			result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(subtotal));
		}

		return result;
	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount,
			BigDecimal forthyDiscount, BigDecimal buy3Get1, BigDecimal voucherPrice) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(forthyDiscount);
		result = result.subtract(buy3Get1);
		result = result.subtract(voucherPrice);

		result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;

		return result.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculateDiscountTotal(BigDecimal... args) {
		BigDecimal result = BigDecimal.ZERO;

		for (BigDecimal arg : args) {
			result = result.add(arg);
		}
		return result;
	}

	public static BigDecimal calculateTax(BigDecimal totalAmount, BigDecimal taxClass) {

		BigDecimal tax = BigDecimal.ZERO;

		tax = tax.add(totalAmount);
		tax = tax.multiply(taxClass);
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100")).add(taxClass), 2, BigDecimal.ROUND_HALF_UP);

		return tax;

	}

	private static BigDecimal calculateVoucherRatio(BigDecimal cartSubtotal, BigDecimal shippingSectionSubtotal,
			BigDecimal voucherValue) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(shippingSectionSubtotal);
		result = result.multiply(voucherValue);
		result = result.divide(cartSubtotal, 2, BigDecimal.ROUND_HALF_UP);

		return result.setScale(2, RoundingMode.HALF_UP);
	}

	public static ShippingModel calculateShippingTotals(RegularCartCalcDetailsModel discountCalculationModel,
			String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());

		BigDecimal discountCalculation = calculateDiscountTotal(
				BigDecimal.valueOf(
						Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.JEWELRY_BONUS))),
				BigDecimal.valueOf(Double
						.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_40_BONUS))),
				BigDecimal.valueOf(Double
						.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_BUY_3_GET_1))),
				BigDecimal.valueOf(Double
						.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.VOUCHER_SHIPPING))));

		System.out.println("discountCalculation:" +discountCalculation);
		
		String newShippingValue = GeneralCartCalculations
				.calculateNewShipping1(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSubTotal())),
						BigDecimal.valueOf(Double.parseDouble(
								discountCalculationModel.getSegments().get(ConfigConstants.VOUCHER_SHIPPING))),
				BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		result.setDiscountPrice(discountCalculation.toString());
		// result.setShippingPrice(shippingValue);
		result.setShippingPrice(newShippingValue);

		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation
				.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(newShippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

	public static ShippingModel calculateShippingTotals(List<RegularBasicProductModel> allProdList,
			List<RegularBasicProductModel> prodList, String voucherValue, String shippingValue, String taxClass,
			boolean hasShipping) {
		ShippingModel result = new ShippingModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));
		BigDecimal wholeSubtotal = BigDecimal.ZERO;

		for (RegularBasicProductModel product : allProdList) {
			wholeSubtotal = wholeSubtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
		}

		for (RegularBasicProductModel product : prodList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		if (hasShipping)
			shippingValue = GeneralCartCalculations.calculateNewShipping(subtotal,
					BigDecimal.valueOf(Double.parseDouble(voucherValue)),
					BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		else
			shippingValue = "0.00";

		voucherPrice = calculateVoucherRatio(wholeSubtotal, subtotal, voucherPrice);

		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherPrice);
		totalAmount = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		BigDecimal tax = calculateTax(totalAmount, BigDecimal.valueOf(Double.parseDouble(taxClass)));
		BigDecimal discountTotal = calculateDiscountTotal(jewerlyDiscount, forthyDiscount, buy3Get1, voucherPrice);

		result.setSubTotal(String.valueOf(subtotal));
		result.setShippingPrice(shippingValue);
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setDiscountPrice(String.valueOf(discountTotal));
		result.setTax(String.valueOf(tax));

		PrintUtils.printShippingTotals(result);

		return result;
	}

	public static String calculateEndPrice(String finalPrice, String bunosValue) {
		BigDecimal endPrice =BigDecimal.ZERO;
		endPrice=endPrice.add(BigDecimal.valueOf(Double.valueOf(finalPrice)));
		endPrice=endPrice.subtract(BigDecimal.valueOf(Double.valueOf(bunosValue)));
		
		return endPrice.setScale(2,RoundingMode.HALF_UP).toString();
	
		
	}

}

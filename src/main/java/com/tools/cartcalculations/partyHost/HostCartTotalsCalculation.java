package com.tools.cartcalculations.partyHost;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.utils.PrintUtils;

public class HostCartTotalsCalculation {

	public static HostCartCalcDetailsModel calculateTotals(List<HostBasicProductModel> productsList, String taxClass) {
		HostCartCalcDetailsModel result = new HostCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherDiscount = BigDecimal.ZERO;

		for (HostBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherDiscount);
		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_SHIPPING, String.valueOf(voucherDiscount));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherDiscount));

		return result;
	}

	public static HostCartCalcDetailsModel calculateOrderForCustomerTotals(List<HostBasicProductModel> productsList,
			String taxClass, String voucherValue, String shippingValue) {
		HostCartCalcDetailsModel result = new HostCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherVal = BigDecimal.valueOf(Double.parseDouble(voucherValue));

		for (HostBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}

		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherVal);

		shippingValue = GeneralCartCalculations.calculateNewShipping(subtotal,
				BigDecimal.valueOf(Double.parseDouble(voucherValue)),
				BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_SHIPPING, String.valueOf(voucherValue));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherValue));

		if (voucherVal.compareTo(subtotal) > 0) {
			result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(subtotal));
		}

		return result;
	}

	public static HostCartCalcDetailsModel calculateTotalsWithBuy3Get1Active(List<HostBasicProductModel> productsList,
			List<HostBasicProductModel> productsListForBuy3Get1, String taxClass, String shippingValue) {
		HostCartCalcDetailsModel result = new HostCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = HostCartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsListForBuy3Get1);
		BigDecimal voucherDiscount = BigDecimal.ZERO;

		for (HostBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			if (product.getBonusType().contentEquals(ContextConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1, voucherDiscount);

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2,
				BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(ConfigConstants.VOUCHER_SHIPPING, String.valueOf(voucherDiscount));
		result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(voucherDiscount));

		if (voucherDiscount.compareTo(subtotal) > 0) {
			result.addSegment(ConfigConstants.VOUCHER_DISCOUNT, String.valueOf(subtotal));
		}

		return result;
	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount,
			BigDecimal forthyDiscount, BigDecimal buy3Get1, BigDecimal voucherValue) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(forthyDiscount);
		result = result.subtract(buy3Get1);
		result = result.subtract(voucherValue);

		result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;

		return result.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculate40DiscountForIp(String ipPoints) {

		BigDecimal ip = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		BigDecimal reducedIp = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		reducedIp = reducedIp.multiply(BigDecimal.valueOf(40));
		reducedIp = reducedIp.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);

		return ip.subtract(reducedIp);
	}

	public static ShippingModel calculateShippingTotals(HostCartCalcDetailsModel discountCalculationModel,
			String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(
				Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.JEWELRY_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(
				Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_40_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(
				Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_BUY_3_GET_1))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(
				Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.VOUCHER_SHIPPING))));

		String newShippingValue = GeneralCartCalculations
				.calculateNewShipping(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSubTotal())),
						BigDecimal.valueOf(Double.parseDouble(
								discountCalculationModel.getSegments().get(ConfigConstants.VOUCHER_SHIPPING))),
						BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		result.setDiscountPrice(discountCalculation.toString());
		result.setShippingPrice(shippingValue);

		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation
				.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(newShippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
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

	public static ShippingModel calculateShippingTotals(List<HostBasicProductModel> allProdList,
			List<HostBasicProductModel> prodList, String voucherValue, String shippingValue, String taxClass,
			boolean hasShipping) {
		ShippingModel result = new ShippingModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));
		BigDecimal wholeSubtotal = BigDecimal.ZERO;

		for (HostBasicProductModel product : allProdList) {
			wholeSubtotal = wholeSubtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
		}

		for (HostBasicProductModel product : prodList) {
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

}
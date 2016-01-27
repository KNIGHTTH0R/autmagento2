package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.StylistRegistrationCartCalcDetailsModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.frontend.StarterSetProductModel;
import com.tools.utils.PrintUtils;

public class StylistRegistrationCartTotalsCalculation {

	public static StylistRegistrationCartCalcDetailsModel calculateTotals(List<StarterSetProductModel> productsList, String taxClass, String shippingValue, String voucherValue,
			boolean isVoucherFixPrice) {

		StylistRegistrationCartCalcDetailsModel result = new StylistRegistrationCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));

		for (StarterSetProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())));
		}

		tax = subtotal.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		totalAmount = calculateTotalAmount(subtotal, voucherPrice, isVoucherFixPrice);

		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setVoucherDiscount(voucherValue);
		result.setShipping(shippingValue);
		
		PrintUtils.printStarterSetCalcDetailsTotals(result);

		return result;
	}

	public static ShippingModel calculateShippingTotals(StylistRegistrationCartCalcDetailsModel cartCalcDetailsModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(cartCalcDetailsModel.getSubTotal());
		result.setDiscountPrice("0");
		result.setShippingPrice(shippingValue);
		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(cartCalcDetailsModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		PrintUtils.printShippingTotals(result);

		return result;
	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal voucherValue, boolean isFixSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);

		if (isFixSum) {
			result = result.subtract(voucherValue);
		} else {
			result = result.multiply(voucherValue);
			result = result.divide(BigDecimal.valueOf(Double.parseDouble("100")), 2, BigDecimal.ROUND_HALF_UP);
		}
		return result.setScale(2, RoundingMode.HALF_UP);
	}
}

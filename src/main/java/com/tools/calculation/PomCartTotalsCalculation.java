package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.PomCartCalcDetailsModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.utils.PrintUtils;

public class PomCartTotalsCalculation {

	public static PomCartCalcDetailsModel calculateTotals(List<PomProductModel> productsList, String taxClass, String shippingValue) {
		PomCartCalcDetailsModel result = new PomCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal discount = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;

		for (PomProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));

		}
		// the discount is equal to the first product added into the cart price
		discount = discount.add(BigDecimal.valueOf(Double.parseDouble(productsList.get(0).getUnitPrice())));
		subtotal = subtotal.subtract(discount);

		tax = subtotal.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTotalAmount(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTax(String.valueOf(tax));
		result.setPomDiscount(String.valueOf(discount));

		PrintUtils.printPomCartCalcDetailsModel(result);

		return result;
	}

	public static ShippingModel calculateShippingTotals(PomCartCalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());
		result.setDiscountPrice("0");
		result.setShippingPrice(shippingValue);
		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		PrintUtils.printShippingTotals(result);

		return result;
	}

}

package com.tools.cartcalculations.borrowCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.PomCartCalcDetailsModel;
import com.tools.data.frontend.PomProductModel;
import com.tools.data.frontend.ShippingModel;

public class PomCartTotalsCalculation {

	public static PomCartCalcDetailsModel calculateTotals(List<PomProductModel> productsList, String taxClass, String shippingValue) {
		PomCartCalcDetailsModel result = new PomCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal discount = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;

		for (PomProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())));

		}
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		// the discount is equal to the first product added into the cart price
		discount = discount.add(BigDecimal.valueOf(Double.parseDouble(productsList.get(0).getUnitPrice())));
		result.setPomDiscount(String.valueOf(discount.setScale(2, RoundingMode.HALF_UP)));
		
		totalAmount = subtotal.subtract(discount);
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));

		tax = totalAmount.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		tax = tax.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		result.setTax(String.valueOf(tax.setScale(2, RoundingMode.HALF_UP)));
	
		return result;
	}

	public static ShippingModel calculateShippingTotals(PomCartCalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());
		result.setDiscountPrice(discountCalculationModel.getPomDiscount());
		result.setShippingPrice(shippingValue);
		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

}

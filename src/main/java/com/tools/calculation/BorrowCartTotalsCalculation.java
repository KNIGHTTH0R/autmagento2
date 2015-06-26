package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.ConfigConstants;

public class BorrowCartTotalsCalculation {

	public static BorrowCartCalcDetailsModel calculateTotals(List<BorrowProductModel> productsList, String taxClass) {
		BorrowCartCalcDetailsModel result = new BorrowCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;

		for (BorrowProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));

		}
		// total amount variable is not needed if total amount is equal with
		// subtotal,but i
		// prefferd to reflect that the tax is calculated from total amount and
		// not from subtotal

		totalAmount = subtotal;

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));

		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints));

		return result;
	}

	public static ShippingModel calculateShippingTotals(BorrowCartCalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());
		result.setDiscountPrice("0");
		result.setShippingPrice(shippingValue);
		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

}

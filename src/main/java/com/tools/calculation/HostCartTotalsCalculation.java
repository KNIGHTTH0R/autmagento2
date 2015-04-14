package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.ConfigConstants;

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

		for (HostBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			if (product.getBonusType().contentEquals(ConfigConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ConfigConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1);
		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));
		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));

		return result;
	}

	public static HostCartCalcDetailsModel calculateTotalsWithBuy3Get1Active(List<HostBasicProductModel> productsList, List<HostBasicProductModel> productsListForBuy3Get1, String taxClass) {
		HostCartCalcDetailsModel result = new HostCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = HostCartBuy3Get1Calculation.calculateTotalBuy3Get1Discount(productsListForBuy3Get1);

		for (HostBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())));
			if (product.getBonusType().contentEquals(ConfigConstants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(ConfigConstants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));
		result.setTax(String.valueOf(tax));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.addSegment(ConfigConstants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));

		return result;
	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount, BigDecimal forthyDiscount, BigDecimal buy3Get1) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(forthyDiscount);
		result = result.subtract(buy3Get1);

		return result.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculate40DiscountForIp(String ipPoints) {

		BigDecimal ip = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		BigDecimal reducedIp = BigDecimal.valueOf(Double.parseDouble(ipPoints));
		reducedIp = reducedIp.multiply(BigDecimal.valueOf(40));
		reducedIp = reducedIp.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);

		return ip.subtract(reducedIp);
	}

	public static ShippingModel calculateShippingTotals(HostCartCalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.JEWELRY_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_40_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_BUY_3_GET_1))));

		result.setDiscountPrice(discountCalculation.toString());
		result.setShippingPrice(shippingValue);

		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

}

package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.Constants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;

public class RegularCartTotalsCalculation {

	public static RegularCartCalcDetailsModel calculateTotals(List<RegularBasicProductModel> productsList, String taxClass, String voucherValue) {
		RegularCartCalcDetailsModel result = new RegularCartCalcDetailsModel();

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
		BigDecimal forthyDiscount = BigDecimal.ZERO;
		BigDecimal buy3Get1 = BigDecimal.ZERO;
		BigDecimal voucherPrice = BigDecimal.valueOf(Double.parseDouble(voucherValue));

		for (RegularBasicProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
			if (product.getBonusType().contentEquals(Constants.JEWELRY_BONUS)) {
				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				jewerlyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
			if (product.getBonusType().contentEquals(Constants.DISCOUNT_40_BONUS)) {
				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
				forthyDiscount.setScale(2, RoundingMode.HALF_UP);
			}
		}
		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1 ,voucherPrice);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
		result.setSubTotal(String.valueOf(subtotal.setScale(2, RoundingMode.HALF_UP)));

		result.setTotalAmount(String.valueOf(totalAmount.setScale(2, RoundingMode.HALF_UP)));

		result.setTax(String.valueOf(tax));
		result.addSegment(Constants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
		result.addSegment(Constants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
		result.addSegment(Constants.VOUCHER_DISCOUNT, String.valueOf(voucherValue));

		return result;
	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount, BigDecimal forthyDiscount, BigDecimal buy3Get1,BigDecimal voucherPrice) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(forthyDiscount);
		result = result.subtract(buy3Get1);
		result = result.subtract(voucherPrice);

		return result.setScale(2, RoundingMode.HALF_UP);
	}

	public static ShippingModel calculateShippingTotals(RegularCartCalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.JEWELRY_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_40_BONUS))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_BUY_3_GET_1))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.VOUCHER_DISCOUNT))));

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
//=======
//package com.tools.calculation;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import com.tools.Constants;
//import com.tools.data.RegularCartCalcDetailsModel;
//import com.tools.data.frontend.RegularBasicProductModel;
//
//public class RegularCartTotalsCalculation {
//	
//	public static RegularCartCalcDetailsModel calculateTotals(List<RegularBasicProductModel> productsList, String taxClass) {
//		RegularCartCalcDetailsModel result = new RegularCartCalcDetailsModel();
//
//		BigDecimal subtotal = BigDecimal.ZERO;
//		BigDecimal tax = BigDecimal.ZERO;
//		BigDecimal totalAmount = BigDecimal.ZERO;
//		BigDecimal jewerlyDiscount = BigDecimal.ZERO;
//		BigDecimal forthyDiscount = BigDecimal.ZERO;
//		BigDecimal buy3Get1 = BigDecimal.ZERO;
//
//		for (RegularBasicProductModel product : productsList) {
//			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())));
//			if (product.getBonusType().contentEquals(Constants.JEWELRY_BONUS)) {
//				jewerlyDiscount = jewerlyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
//				jewerlyDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
//			}
//			if (product.getBonusType().contentEquals(Constants.DISCOUNT_40_BONUS)) {
//				forthyDiscount = forthyDiscount.add(BigDecimal.valueOf(Double.parseDouble(product.getBunosValue())));
//				forthyDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
//			}
//		}
//		totalAmount = calculateTotalAmount(subtotal, jewerlyDiscount, forthyDiscount, buy3Get1);
//		
//		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
//		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);
//		result.setSubTotal(String.valueOf(subtotal));
//	
//		result.setTotalAmount(String.valueOf(totalAmount));
//	
//		result.setTax(String.valueOf(tax));
//		result.addSegment(Constants.JEWELRY_BONUS, String.valueOf(jewerlyDiscount));
//		result.addSegment(Constants.DISCOUNT_40_BONUS, String.valueOf(forthyDiscount));
//		result.addSegment(Constants.DISCOUNT_BUY_3_GET_1, String.valueOf(buy3Get1));
//
//	
//
//		return result;
//	}
//	
//	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount,BigDecimal forthyDiscount, BigDecimal buy3Get1) {
//
//		BigDecimal result = BigDecimal.ZERO;
//
//		result = result.add(subtotal);
//		result = result.subtract(jewelryDiscount);
//		result = result.subtract(forthyDiscount);
//		result = result.subtract(buy3Get1);
//
//		return result;
//	}
//	
//
//
//}
//>>>>>>> branch 'master' of git@evogit.evozon.com:pippajeanautotester/pippajeanautotester.git

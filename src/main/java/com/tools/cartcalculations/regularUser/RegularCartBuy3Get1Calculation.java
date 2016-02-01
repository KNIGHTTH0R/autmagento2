package com.tools.cartcalculations.regularUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.RegularBasicProductModel;

public class RegularCartBuy3Get1Calculation {

	public static BigDecimal calculateBuyThreeGetOneDiscountForEachSegment(List<BasicProductModel> productsList, String discountType) {

		BigDecimal discount = BigDecimal.ZERO;

		List<BasicProductModel> newList = getSublistFromList(productsList, discountType);

		BasicProductModel cheepest = getCheapestProduct(newList);

		for (BasicProductModel product : newList) {

			if (product == cheepest) {
				discount = BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
			}
		}
		return discount;
	}

	public static List<BasicProductModel> getSublistFromList(List<BasicProductModel> productsList, String discountType) {

		List<BasicProductModel> newList = new ArrayList<BasicProductModel>();
		for (BasicProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contentEquals(discountType))
				newList.add(cartProductModel);
		}

		return newList;
	}

	public static BigDecimal calculateTotalBuy3Get1Discount(List<RegularBasicProductModel> productsList) {
		BigDecimal totalDiscount = BigDecimal.ZERO;

		BigDecimal remainder = BigDecimal.ZERO;

		for (int i = 0; i < productsList.size(); i++) {

			BigDecimal discount = BigDecimal.ZERO;
			RegularBasicProductModel nextproduct;
			RegularBasicProductModel product = productsList.get(i);
			if (i != productsList.size() - 1) {
				nextproduct = productsList.get(i + 1);
			} else {
				nextproduct = productsList.get(i);
			}
			BigDecimal[] result = RegularCartBuy3Get1Calculation.determineHowManyTimesAProductsIsWithHalfPrice(product, remainder);
			BigDecimal noOfDiscounts = result[0].setScale(0);
			BigDecimal productRemainder = result[1].setScale(0);
			remainder = productRemainder;

			discount = BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP);
			discount = discount.multiply(noOfDiscounts);

			if (BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).compareTo(BigDecimal.valueOf(Double.parseDouble(nextproduct.getUnitPrice()))) == -1
					|| BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).compareTo(BigDecimal.valueOf(Double.parseDouble(nextproduct.getUnitPrice()))) == -1) {

				if (productRemainder.intValue() == 1 && Integer.parseInt(nextproduct.getQuantity()) >= 2) {
					discount = discount.add(BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP));
					nextproduct.setQuantity(String.valueOf(BigDecimal.valueOf(Double.parseDouble(nextproduct.getQuantity())).subtract(BigDecimal.valueOf(2)).intValue()));
					remainder = BigDecimal.ZERO;
				}
				if (productRemainder.intValue() == 2 && Integer.parseInt(nextproduct.getQuantity()) >= 1) {
					discount = discount.add(BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP));
					nextproduct.setQuantity(String.valueOf(BigDecimal.valueOf(Double.parseDouble(nextproduct.getQuantity())).subtract(BigDecimal.valueOf(1)).intValue()));
					remainder = BigDecimal.ZERO;
				}
			}
			totalDiscount = totalDiscount.add(discount);

		}
		return totalDiscount.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the cheapest product from a list.
	 * 
	 * @param productsList
	 * @return result
	 */
	public static BasicProductModel getCheapestProduct(List<BasicProductModel> productsList) {

		BasicProductModel result = new BasicProductModel();
		if (productsList.size() != 0) {
			result = productsList.get(0);
			for (BasicProductModel cartProductModel : productsList) {
				if (Double.parseDouble(cartProductModel.getUnitPrice()) <= Double.parseDouble(result.getUnitPrice())) {
					result = cartProductModel;
				}
			}
		}

		return result;
	}

	public static boolean isBuy3Get1Applicable(List<BasicProductModel> productList) {
		int quantity = 0;
		for (BasicProductModel cartProductModel : productList) {
			quantity += Integer.parseInt(cartProductModel.getQuantity());
		}
		if (quantity >= 3) {
			return true;
		} else
			return false;
	}

	// TODO find a better name for this
	public static BigDecimal[] determineNoOfProductsBuy3Get1IsAppliedOnAndRemainder(List<BasicProductModel> productsList, BigDecimal remainder) {

		BigDecimal batchSize = BigDecimal.valueOf(3);
		BigDecimal quantity = BigDecimal.ZERO;

		for (BasicProductModel cartProductModel : productsList) {
			quantity = quantity.add(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getQuantity())));
		}
		quantity = quantity.add(remainder);
		BigDecimal result[] = quantity.divideAndRemainder(batchSize);

		return result;
	}

	public static BigDecimal[] determineHowManyTimesAProductsIsWithHalfPrice(RegularBasicProductModel product, BigDecimal remainder) {

		BigDecimal batchSize = BigDecimal.valueOf(3);
		BigDecimal quantity = BigDecimal.valueOf(Double.parseDouble(product.getQuantity()));

		quantity = quantity.add(remainder);
		BigDecimal result[] = quantity.divideAndRemainder(batchSize);

		return result;
	}

}

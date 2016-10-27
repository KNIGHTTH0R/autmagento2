package com.tools.cartcalculations.partyHost;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.HostBasicProductModel;

public class HostCartBuy3Get1Calculation {

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

	public static List<HostBasicProductModel> applyBuy3Get1OnTheCart(List<HostBasicProductModel> productsList) {

		BigDecimal remainder = BigDecimal.ZERO;

		List<HostBasicProductModel> cartProducts = new ArrayList<HostBasicProductModel>();

		BigDecimal ipDiscount = BigDecimal.ZERO;
		BigDecimal ip = BigDecimal.ZERO;

		for (int i = 0; i < productsList.size(); i++) {

			HostBasicProductModel nextproduct;
			HostBasicProductModel product = productsList.get(i);
			if (i != productsList.size() - 1) {
				nextproduct = productsList.get(i + 1);
			} else {
				nextproduct = productsList.get(i);
			}
			BigDecimal[] result = HostCartBuy3Get1Calculation.determineHowManyTimesAProductsIsWithHalfPrice(product, remainder);
			BigDecimal noOfDiscounts = result[0].setScale(0);
			BigDecimal productRemainder = result[1].setScale(0);
			remainder = productRemainder;

			HostBasicProductModel newProduct = new HostBasicProductModel();

			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setBonusType(product.getBonusType());
			newProduct.setBunosValue(product.getBunosValue());
			newProduct.setFinalPrice(product.getFinalPrice());
			newProduct.setIpPoints(product.getIpPoints());
			
			System.out.println(product.getIpPoints() + " / " + product.getQuantity());
			ipDiscount = BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())).divide(BigDecimal.valueOf(Double.parseDouble(product.getQuantity())), 5, BigDecimal.ROUND_HALF_UP);
	
			ipDiscount = ipDiscount.divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP);
			ipDiscount = ipDiscount.multiply(noOfDiscounts);

			if (BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).compareTo(BigDecimal.valueOf(Double.parseDouble(nextproduct.getUnitPrice()))) == -1
					|| BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).compareTo(BigDecimal.valueOf(Double.parseDouble(nextproduct.getUnitPrice()))) == -1) {

				BigDecimal extraIpDiscount = BigDecimal.ZERO;

				if (productRemainder.intValue() == 1 && Integer.parseInt(nextproduct.getQuantity()) >= 2) {
					extraIpDiscount = BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())).divide(BigDecimal.valueOf(Double.parseDouble(product.getQuantity())), 5, BigDecimal.ROUND_HALF_UP);
					extraIpDiscount = extraIpDiscount.divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP);
					ipDiscount = ipDiscount.add(extraIpDiscount);
					nextproduct.setQuantity(String.valueOf(BigDecimal.valueOf(Double.parseDouble(nextproduct.getQuantity())).subtract(BigDecimal.valueOf(2)).intValue()));
					remainder = BigDecimal.ZERO;
				}
				if (productRemainder.intValue() == 2 && Integer.parseInt(nextproduct.getQuantity()) >= 1) {
					extraIpDiscount = BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())).divide(BigDecimal.valueOf(Double.parseDouble(product.getQuantity())), 5, BigDecimal.ROUND_HALF_UP);
					extraIpDiscount = extraIpDiscount.divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP);
					ipDiscount = ipDiscount.add(extraIpDiscount);
					nextproduct.setQuantity(String.valueOf(BigDecimal.valueOf(Double.parseDouble(nextproduct.getQuantity())).subtract(BigDecimal.valueOf(1)).intValue()));
					remainder = BigDecimal.ZERO;
				}
			}
			ip = BigDecimal.valueOf(Double.parseDouble(product.getIpPoints())).subtract(ipDiscount);
			newProduct.setIpPoints(String.valueOf(ip.setScale(0, BigDecimal.ROUND_HALF_UP)));

			cartProducts.add(newProduct);

		}
		return cartProducts;
	}

	public static BigDecimal calculateTotalBuy3Get1Discount(List<HostBasicProductModel> productsList) {
		BigDecimal totalDiscount = BigDecimal.ZERO;

		BigDecimal remainder = BigDecimal.ZERO;

		for (int i = 0; i < productsList.size(); i++) {

			BigDecimal discount = BigDecimal.ZERO;
			HostBasicProductModel nextproduct;
			HostBasicProductModel product = productsList.get(i);
			if (i != productsList.size() - 1) {
				nextproduct = productsList.get(i + 1);
			} else {
				nextproduct = productsList.get(i);
			}
			BigDecimal[] result = HostCartBuy3Get1Calculation.determineHowManyTimesAProductsIsWithHalfPrice(product, remainder);
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

	public static BigDecimal[] determineHowManyTimesAProductsIsWithHalfPrice(HostBasicProductModel product, BigDecimal remainder) {

		BigDecimal batchSize = BigDecimal.valueOf(3);
		BigDecimal quantity = BigDecimal.valueOf(Double.parseDouble(product.getQuantity()));

		quantity = quantity.add(remainder);
		BigDecimal result[] = quantity.divideAndRemainder(batchSize);

		return result;
	}

}

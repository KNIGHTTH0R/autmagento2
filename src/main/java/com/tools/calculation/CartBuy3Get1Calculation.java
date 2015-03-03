package com.tools.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;

public class CartBuy3Get1Calculation {
	
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

	public static List<BasicProductModel> applyBuyThreeGetOneRule(List<BasicProductModel> productsList) {

		BigDecimal discount = BigDecimal.ZERO;
		BigDecimal finalPrice = BigDecimal.ZERO;

		BasicProductModel cheepest = getCheapestProduct(productsList);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setFinalPrice(product.getFinalPrice());
			newProduct.setPriceIP(product.getPriceIP());
			//TODO verify expression
			if (product.getProdCode().equals(cheepest.getProdCode())) {
				discount = BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP);
				finalPrice = BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())).multiply(BigDecimal.valueOf(Double.parseDouble(product.getDiscountClass())));
				finalPrice = finalPrice.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
				finalPrice = BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())).subtract(finalPrice);
				finalPrice = finalPrice.subtract(discount);

				newProduct.setFinalPrice(String.valueOf(finalPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));

			}
			cartProducts.add(newProduct);
		}

		return cartProducts;
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

	// public static void
	// removeItemsFromListIfListSizeBiggerThan3(List<CartProductModel>
	// productsList) {
	// if (productsList.size() > 3) {
	// for (int i = 3; i < productsList.size(); i++) {
	// productsList.remove(i);
	// }
	// }
	// }

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

}

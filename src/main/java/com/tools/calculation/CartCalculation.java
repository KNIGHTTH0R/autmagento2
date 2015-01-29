package com.tools.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class CartCalculation {

	private static BigDecimal remainder25 = BigDecimal.valueOf(0);
	private static BigDecimal remainder50 = BigDecimal.valueOf(0);
	private static BigDecimal remainder00 = BigDecimal.valueOf(0);

	/**
	 * Return product price based on (price - quantity - discount). Discount may
	 * be 25 or 50.
	 * 
	 * @param product
	 * @return
	 */
	public static BigDecimal calculateCartProductsByDiscount(CartProductModel product) {

		BigDecimal productPrice = BigDecimal.valueOf(0);

		if (product.getDiscountClass().contentEquals("25")) {
			productPrice = productPrice.add(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
			productPrice = productPrice.multiply(PrintUtils.cleanNumberToBigDecimal(product.getQuantity()));
			productPrice = productPrice.multiply(BigDecimal.valueOf(25));
			productPrice = productPrice.divide(BigDecimal.valueOf(100));
		}
		if (product.getDiscountClass().contentEquals("50")) {
			productPrice = productPrice.add(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
			productPrice = productPrice.multiply(PrintUtils.cleanNumberToBigDecimal(product.getQuantity()));
			productPrice = productPrice.multiply(BigDecimal.valueOf(50));
			productPrice = productPrice.divide(BigDecimal.valueOf(100));
		}
		return productPrice;
	}

	public static CalculationModel calculateTableProducts(List<CartProductModel> cartList) {
		CalculationModel result = new CalculationModel();
		BigDecimal askingPriceSum = BigDecimal.valueOf(0);
		BigDecimal finalPriceSum = BigDecimal.valueOf(0);
		int ipSum = 0;

		if (cartList.size() > 0) {

			result.setTableType(cartList.get(0).getDiscountClass());

			for (CartProductModel cartProductModel : cartList) {
				BigDecimal transport = BigDecimal.valueOf(0);
				transport = transport.add(PrintUtils.cleanNumberToBigDecimal(cartProductModel.getUnitPrice()));
				transport = transport.multiply(PrintUtils.cleanNumberToBigDecimal(cartProductModel.getQuantity()));
				askingPriceSum = askingPriceSum.add(transport);

				ipSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
			}

			int calcValue = checkCalculusType(result.getTableType());

			BigDecimal partTwo = BigDecimal.valueOf(0);

			partTwo = partTwo.add(askingPriceSum);
			partTwo = partTwo.multiply(BigDecimal.valueOf(calcValue));
			partTwo = partTwo.divide(BigDecimal.valueOf(100));

			finalPriceSum = finalPriceSum.add(askingPriceSum);
			finalPriceSum = finalPriceSum.subtract(partTwo);

			result.setAskingPrice(askingPriceSum);
			result.setFinalPrice(finalPriceSum);
			result.setIpPoints(ipSum);

			PrintUtils.printCalculationModel(result);

		} else {
			System.out.println("Failure: Product list is empty!!! - see Calculus Steps");
		}
		return result;

	}

	public static CalculationModel calculateTotalSum(List<CalculationModel> totalsList) {
		CalculationModel result = new CalculationModel();
		BigDecimal askingPrice = BigDecimal.valueOf(0);
		BigDecimal finalPrice = BigDecimal.valueOf(0);
		int ipPoints = 0;

		for (CalculationModel total : totalsList) {

			if (total.getAskingPrice() != null) {
				askingPrice = askingPrice.add(total.getAskingPrice());
			}

			if (total.getFinalPrice() != null) {
				finalPrice = finalPrice.add(total.getFinalPrice());
			}

			ipPoints += total.getIpPoints();
		}

		finalPrice = finalPrice.divide(BigDecimal.valueOf(100));
		askingPrice = askingPrice.divide(BigDecimal.valueOf(100));

		result.setAskingPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setIpPoints(ipPoints);

		return result;
	}

	private static int checkCalculusType(String tableType) {
		int result = 0;

		if (tableType.contentEquals(Constants.DISCOUNT_25)) {
			result = 25;
		}
		if (tableType.contentEquals(Constants.DISCOUNT_50)) {
			result = 50;
		}
		if (tableType.contentEquals(Constants.DISCOUNT_0)) {
			result = 0;
		}
		return result;
	}

	public CartTotalsModel calculateDiscountTotals(List<CalculationModel> totalsList, String jewelryDiscount, String marketingMaterial) {

		jewelryDiscount = formatDiscount(jewelryDiscount);
		marketingMaterial = formatDiscount(marketingMaterial);

		CartTotalsModel result = new CartTotalsModel();
		result.setJewelryBonus(jewelryDiscount);

		calculateJewelryDiscounts(totalsList, jewelryDiscount);
		calculateMarketingDiscount(totalsList, marketingMaterial);

		remainder25 = remainder25.divide(BigDecimal.valueOf(4)).divide(BigDecimal.valueOf(100));
		remainder50 = remainder50.divide(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(100));
		remainder00 = remainder00.divide(BigDecimal.valueOf(100));

		result.setSubtotal(calculateTotalSum(totalsList).getAskingPrice().toString());
		result.setIpPoints(String.valueOf(calculateTotalSum(totalsList).getIpPoints()));

		// CORE FORMULA - calculate total amount
		BigDecimal totalAmount = BigDecimal.valueOf(Double.parseDouble(calculateTotalSum(totalsList).getAskingPrice().toString()));
		totalAmount = totalAmount.subtract(remainder25);
		totalAmount = totalAmount.subtract(remainder50);
		totalAmount = totalAmount.subtract(BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)).divide(BigDecimal.valueOf(100)));
		if (applyMarketingDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(marketingMaterial))).compareTo(BigDecimal.valueOf(0)) > 0) {
			totalAmount = totalAmount.subtract(BigDecimal.valueOf(Double.parseDouble(marketingMaterial)).divide(BigDecimal.valueOf(100)));
		}

		result.setTotalAmount(totalAmount.toString());

		result.addDiscount(Constants.DISCOUNT_50, remainder50.toString());
		result.addDiscount(Constants.DISCOUNT_25, remainder25.toString());
		result.addDiscount(Constants.DISCOUNT_0, remainder00.toString());

		System.out.println("SUBTOTAL: " + BigDecimal.valueOf(Double.parseDouble(calculateTotalSum(totalsList).getAskingPrice().toString())));
		System.out.println("TOTAL AMOUNT: " + result.getTotalAmount());
		System.out.println("IP: " + result.getIpPoints());
		System.out.println("Remainder after 25%: " + remainder25.toString());
		System.out.println("Remainder after 50%: " + remainder50.toString());
		System.out.println("Remainder after 0%: " + remainder00.toString());

		result.setSubtotal(calculateTotalSum(totalsList).getAskingPrice().toString());
		result.setIpPoints(String.valueOf(calculateTotalSum(totalsList).getIpPoints()));

		return result;
	}

	public void calculateJewelryDiscounts(List<CalculationModel> totalsList, String jewelryDiscount) {

		if (applyDiscount(totalsList, BigDecimal.valueOf(0), Constants.DISCOUNT_25).compareTo(BigDecimal.valueOf(0)) > 0) {
			// If 25% section has total over 0
			remainder25 = applyDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), Constants.DISCOUNT_25);
			System.out.println(remainder25.toString());
		} else {
			System.out.println("TOTAL for 25% section is not greater than 0 !!!");
		}

		if (applyDiscount(totalsList, BigDecimal.valueOf(0), Constants.DISCOUNT_50).compareTo(BigDecimal.valueOf(0)) > 0) {
			// If 25% section has negative total and 50% section has total over
			// 0
			if (remainder25.compareTo(BigDecimal.valueOf(0)) < 0) {
				remainder50 = applyDiscount(totalsList, remainder25.abs(), Constants.DISCOUNT_50);
				remainder25 = BigDecimal.valueOf(0);
			} else {
				// if 25%discount is positive there is no more discount value -
				// 50% is not discounted
				if (remainder25.compareTo(BigDecimal.valueOf(0)) > 0) {
					remainder50 = applyDiscount(totalsList, BigDecimal.valueOf(0), Constants.DISCOUNT_50);
				} else {
					// if there is no 25% section only the 50% section
					remainder50 = applyDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), Constants.DISCOUNT_50);
				}
			}
		} else {
			System.out.println("TOTAL for 50% section is not greater than 0 !!!");
		}

	}

	// clean decimals to number
	private String formatDiscount(String jewelryDiscount) {
		// modify jewelry discount formatting
		// From 10 To 1000
		// From 10.00 to 1000
		if (jewelryDiscount.contains(".")) {

			jewelryDiscount = jewelryDiscount.replace(".", "");

		} else {
			jewelryDiscount += "00";
		}
		return jewelryDiscount;
	}

	/**
	 * Return calculation of totalProducts by discount class.
	 * 
	 * @param totalsList
	 * @param jewelryDiscount
	 * @param discountClass
	 * @return
	 */
	private BigDecimal applyDiscount(List<CalculationModel> totalsList, BigDecimal jewelryDiscount, String discountClass) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal productSum = BigDecimal.valueOf(0);

		for (CalculationModel cartProductModel : totalsList) {
			System.out.println(cartProductModel.getTableType());
			if (cartProductModel.getTableType() != null && cartProductModel.getTableType().contentEquals(discountClass)) {
				if (cartProductModel.getAskingPrice() != null) {
					if (productSum.compareTo(BigDecimal.valueOf(0)) == 0) {
						System.out.println("ERROR: TOTAL IS EMPTY");
					}
					productSum = productSum.add(cartProductModel.getAskingPrice());
				}
			}
		}

		result = productSum.subtract(jewelryDiscount);

		return result;
	}

	public BigDecimal applyMarketingDiscount(List<CalculationModel> totalsList, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal productSum = BigDecimal.valueOf(0);

		for (CalculationModel calculationModel : totalsList) {
			if (calculationModel.getTableType() != null && calculationModel.getTableType() == Constants.DISCOUNT_0) {
				if (calculationModel.getAskingPrice() != null) {
					if (calculationModel.getAskingPrice().compareTo(BigDecimal.valueOf(0)) == 0) {
						System.out.println("ERROR: TOTAL IS EMPTY");
					}
					productSum = calculationModel.getAskingPrice();
				}
			}
		}
		result = productSum.subtract(marketingDiscount);

		return result;

	}

	public void calculateMarketingDiscount(List<CalculationModel> totalsList, String marketingMaterial) {
		BigDecimal marketingRemainder = BigDecimal.valueOf(0);

		if (applyMarketingDiscount(totalsList, BigDecimal.valueOf(0)).compareTo(BigDecimal.valueOf(0)) > 0) {
			// If marketing material section has total over 0
			marketingRemainder = applyMarketingDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(marketingMaterial)));
		} else {
			System.out.println("TOTAL for marketing material section is less than 0 !!!");
		}

		remainder00 = marketingRemainder;

	}

}

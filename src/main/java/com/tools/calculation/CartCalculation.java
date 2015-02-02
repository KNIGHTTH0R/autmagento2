package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.Constants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

public class CartCalculation {

	private static BigDecimal remainder25 = BigDecimal.valueOf(0);
	private static BigDecimal remainder50 = BigDecimal.valueOf(0);
	private static BigDecimal remainder00 = BigDecimal.valueOf(0);
	private static BigDecimal tax = BigDecimal.valueOf(0);

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
			productPrice = productPrice.add(FormatterUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
			productPrice = productPrice.multiply(FormatterUtils.cleanNumberToBigDecimal(product.getQuantity()));
			productPrice = productPrice.multiply(BigDecimal.valueOf(25));
			productPrice = productPrice.divide(BigDecimal.valueOf(100));
		}
		if (product.getDiscountClass().contentEquals("50")) {
			productPrice = productPrice.add(FormatterUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
			productPrice = productPrice.multiply(FormatterUtils.cleanNumberToBigDecimal(product.getQuantity()));
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
				transport = transport.add(FormatterUtils.cleanNumberToBigDecimal(cartProductModel.getUnitPrice()));
				transport = transport.multiply(FormatterUtils.cleanNumberToBigDecimal(cartProductModel.getQuantity()));
				askingPriceSum = askingPriceSum.add(transport);

				ipSum += FormatterUtils.cleanNumberToInt(cartProductModel.getPriceIP());
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

		finalPrice = finalPrice.divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_DOWN);
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

	public CalcDetailsModel calculateDiscountTotals(List<CalculationModel> totalsList, String jewelryDiscount, String marketingMaterial) {

		CalcDetailsModel result = new CalcDetailsModel();
		result.setJewelryBonus(jewelryDiscount);
		result.setMarketingBonus(marketingMaterial);
		
		
		jewelryDiscount = formatDiscount(jewelryDiscount);
		marketingMaterial = formatDiscount(marketingMaterial);
		BigDecimal totalAmount = BigDecimal.valueOf(Double.parseDouble(calculateTotalSum(totalsList).getAskingPrice().toString()));

		


		// Call methods to calculate Discounts and IP points
		calculateJewelryDiscounts(totalsList, jewelryDiscount);
		calculateMarketingDiscount(totalsList, marketingMaterial);
		calculateIpDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)));

		remainder25 = remainder25.divide(BigDecimal.valueOf(4)).divide(BigDecimal.valueOf(100));
		remainder50 = remainder50.divide(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(100));
		remainder00 = remainder00.divide(BigDecimal.valueOf(100));

		// CORE FORMULA - calculate total amount - save to model all
		// calculations
		result.addCalculation("P1-Total", totalAmount.toString());
		totalAmount = totalAmount.subtract(remainder25);
		result.addCalculation("P2-Substract25", totalAmount.toString());
		totalAmount = totalAmount.subtract(remainder50);
		result.addCalculation("P3-Substract50", totalAmount.toString());
		totalAmount = totalAmount.subtract(BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)).divide(BigDecimal.valueOf(100)));
		result.addCalculation("P4-SubstractJD-divide100", totalAmount.toString());

		// If there is marketing bonus apply it to the Core formula
		if (applyMarketingDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(marketingMaterial))).compareTo(BigDecimal.valueOf(0)) > 0) {
			result.addCalculation("P5-SubstractMM-divide100", totalAmount.toString());
			totalAmount = totalAmount.subtract(BigDecimal.valueOf(Double.parseDouble(marketingMaterial)).divide(BigDecimal.valueOf(100)));
		}

		result.addSegment(Constants.DISCOUNT_50, remainder50.toString());
		result.addSegment(Constants.DISCOUNT_25, remainder25.toString());
		result.addSegment(Constants.DISCOUNT_0, remainder00.toString());

		result.setTotalAmount(totalAmount.toString());
		result.setSubTotal(calculateTotalSum(totalsList).getAskingPrice().toString());
		result.setIpPoints(String.valueOf(calculateIpDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)))));

		calculateTax(calculateTotalSum(totalsList).getAskingPrice(), remainder50, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)).divide(BigDecimal.valueOf(100)),
				BigDecimal.valueOf(Double.parseDouble(marketingMaterial)).divide(BigDecimal.valueOf(100)));

		result.setTax(tax.toString());

		System.out.println(" ---- Calculation Results ---- ");
		System.out.println("SUBTOTAL: " + BigDecimal.valueOf(Double.parseDouble(calculateTotalSum(totalsList).getAskingPrice().toString())));
		System.out.println("TOTAL AMOUNT: " + result.getTotalAmount());
		System.out.println("IP: " + result.getIpPoints());
		System.out.println("Tax: " + tax);
		System.out.println("Remainder after 25%: " + remainder25.toString());
		System.out.println("Remainder after 50%: " + remainder50.toString());
		System.out.println("Remainder after 0%: " + remainder00.toString());
		System.out.println(" ----------------------------- ");

		return result;
	}

	/**
	 * FORMULA: (Zwischensumme – 50% Muster Rabatt – Genutzer Schmuck Bonus-
	 * Genutzer Marketing Bonus) - (Zwischensumme – 50% Muster Rabatt – Genutzer
	 * Schmuck Bonus- Genutzer Marketing Bonus)/1.19
	 * 
	 * @param totalAmount
	 * @param discount50
	 * @param jewelryDiscount
	 * @param marketingDiscount
	 */
	private void calculateTax(BigDecimal totalAmount, BigDecimal discount50, BigDecimal jewelryDiscount, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal partial = BigDecimal.ZERO;

		result = result.add(totalAmount);
		result = result.subtract(discount50);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		partial = partial.add(result);

		// TODO line that breaks
		result = result.divide(BigDecimal.valueOf(Double.parseDouble("1.19")), 0, BigDecimal.ROUND_DOWN);
		tax = partial.subtract(result).setScale(0, BigDecimal.ROUND_DOWN);
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

	public BigDecimal calculateIpDiscount(List<CalculationModel> totalsList, BigDecimal jewelryBonus) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal regularPrice = BigDecimal.valueOf(0);
		BigDecimal ipTotal = BigDecimal.valueOf(0);

		CalculationModel cModel = selectCalcModel(totalsList, Constants.DISCOUNT_25);
		regularPrice = cModel.getAskingPrice();
		ipTotal = BigDecimal.valueOf(cModel.getIpPoints());

		result = result.add(jewelryBonus);
		result = result.multiply(BigDecimal.valueOf(100));
		result = result.divide(regularPrice, RoundingMode.HALF_UP);
		result = result.multiply(ipTotal);
		result = result.divide(BigDecimal.valueOf(100));
		result = ipTotal.subtract(result);

		// if less than 0 make the result = 0
		if (result.compareTo(BigDecimal.ZERO) < 0) {
			result = BigDecimal.ZERO;
		}

		System.out.println("IP CALCULATION: " + result.setScale(0, BigDecimal.ROUND_HALF_UP));

		return result.setScale(0, BigDecimal.ROUND_HALF_UP);
	}

	// /**
	// * TODO Modify this to apply the jewelryBonus correctly
	// * @param cartList
	// * @param jewelryBonus
	// * @return
	// */
	// public BigDecimal calcIp(List<CartProductModel> cartList,BigDecimal
	// jewelryBonus){
	// System.out.println("size " + cartList.size());
	// BigDecimal result = BigDecimal.valueOf(0);
	// BigDecimal totalPrice = BigDecimal.valueOf(0);
	// BigDecimal ipProduct = BigDecimal.valueOf(0);
	//
	// @SuppressWarnings("unused")
	// BigDecimal listProductsPrice =
	// CartCalculation.calculateTableProducts(cartList).getAskingPrice();
	//
	// for( CartProductModel item : cartList) {
	//
	// if(BigDecimal.valueOf(Double.parseDouble(item.getPriceIP())).compareTo(BigDecimal.ZERO)
	// > 0){
	//
	// BigDecimal productResult = BigDecimal.valueOf(0);
	//
	// totalPrice =
	// BigDecimal.valueOf(Double.parseDouble(item.getProductsPrice()));
	// ipProduct = BigDecimal.valueOf(Double.parseDouble(item.getPriceIP()));
	// System.out.println(item.getProductsPrice());
	// System.out.println(item.getPriceIP());
	// productResult = productResult.add(jewelryBonus);
	// productResult = productResult.multiply(BigDecimal.valueOf(100));
	// productResult = productResult.divide(totalPrice,RoundingMode.HALF_UP);
	// productResult = productResult.multiply(ipProduct);
	// productResult = productResult.divide(BigDecimal.valueOf(100));
	// System.out.println("=> " + productResult);
	// productResult = ipProduct.subtract(productResult);
	// if (productResult.compareTo(BigDecimal.ZERO) < 0)
	// productResult = BigDecimal.valueOf(0);
	// System.out.println("IP CALCULATION: " + productResult.setScale(0,
	// BigDecimal.ROUND_HALF_UP));
	// result = result.add(productResult.setScale(0, BigDecimal.ROUND_HALF_UP));
	// }
	// }
	//
	// System.out.println("TOTAL IP CALCULATION: " + result.setScale(0,
	// BigDecimal.ROUND_HALF_UP));
	// return result;
	//
	// }

	// modify jewelry discount formatting
	// From 10 To 1000
	// From 10.00 to 1000
	// clean decimals to number
	private String formatDiscount(String jewelryDiscount) {
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

		CalculationModel cModel = selectCalcModel(totalsList, discountClass);
		productSum = cModel.getAskingPrice();

		result = productSum.subtract(jewelryDiscount);

		return result;
	}

	public BigDecimal applyMarketingDiscount(List<CalculationModel> totalsList, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal productSum = BigDecimal.valueOf(0);

		productSum = selectCalcModel(totalsList, Constants.DISCOUNT_0).getAskingPrice();

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

	private CalculationModel selectCalcModel(List<CalculationModel> totalsList, String mode) {

		CalculationModel result = new CalculationModel();

		// TODO refactor this in a private method with mode
		for (CalculationModel calculationModel : totalsList) {

			if (calculationModel.getTableType() != null && calculationModel.getTableType().contentEquals(mode)) {
				if (calculationModel.getAskingPrice() != null) {
					if (calculationModel.getAskingPrice().compareTo(BigDecimal.valueOf(0)) == 0) {
						System.out.println("ERROR: TOTAL IS EMPTY");
					}
					result = calculationModel;
				}
			}
		}

		return result;
	}
}

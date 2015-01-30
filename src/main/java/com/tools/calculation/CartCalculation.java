package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.Constants;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

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
		calculateIpDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)));

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
		result.setIpPoints(String.valueOf(calculateIpDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)))));

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

	public BigDecimal calculateIpDiscount(List<CalculationModel> totalsList, BigDecimal jewelryBonus) {

		System.out.println("IP CALCULATION---------------------------------------------------s");
		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal regularPrice = BigDecimal.valueOf(0);
		BigDecimal ipTotal = BigDecimal.valueOf(0);

		CalculationModel cModel = selectCalcModel(totalsList, Constants.DISCOUNT_25);
		regularPrice = cModel.getAskingPrice();
		ipTotal = BigDecimal.valueOf(cModel.getIpPoints());

		System.out.println("RP = " + regularPrice);
		System.out.println("IP TOTAL = " + ipTotal);
		System.out.println("JEWELRY BONUS = " + jewelryBonus);		
//		System.out.println(result + " add " + jewelryBonus);
		result = result.add(jewelryBonus);
		System.out.println("1=> " + result);
//		System.out.println(result + " multiply " + BigDecimal.valueOf(100));
		result = result.multiply(BigDecimal.valueOf(100));
		System.out.println("2=> " + result);
//		System.out.println(result + " divide " + regularPrice);	
		result = result.divide(regularPrice, RoundingMode.HALF_UP);
//		result = result.divide(regularPrice);
		System.out.println("3=> " + result);
//		System.out.println(result + " multiply " + ipTotal);	
		result = result.multiply(ipTotal);
		System.out.println("4=> " + result);
//		System.out.println(result + " divide " + BigDecimal.valueOf(100));
		result = result.divide(BigDecimal.valueOf(100));
		System.out.println("=> " + result);
//		System.out.println(ipTotal + " subtract " + result);
		result = ipTotal.subtract(result);
//		System.out.println("Grand Result => " + result);
//		result = result.divide(BigDecimal.valueOf(Double.valueOf(1)), RoundingMode.HALF_UP);
		
		
		System.out.println("IP CALCULATION: " + result.setScale(0, BigDecimal.ROUND_HALF_UP));
//		System.out.println("IP CALCULATION: " + Integer.valueOf(result.toString()));
		
		return result.setScale(0, BigDecimal.ROUND_HALF_UP);

	}
	
	
	public BigDecimal calcIp(List<CartProductModel> cartList,BigDecimal jewelryBonus){
		System.out.println("size " + cartList.size());
		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		BigDecimal ipProduct = BigDecimal.valueOf(0);
		
		BigDecimal listProductsPrice = CartCalculation.calculateTableProducts(cartList).getAskingPrice();
		
		for( CartProductModel item : cartList) {	
			
			if(BigDecimal.valueOf(Double.parseDouble(item.getPriceIP())).compareTo(BigDecimal.ZERO) > 0){
			
			BigDecimal productResult = BigDecimal.valueOf(0);
			
			totalPrice = BigDecimal.valueOf(Double.parseDouble(item.getProductsPrice()));
			ipProduct = BigDecimal.valueOf(Double.parseDouble(item.getPriceIP()));
			System.out.println(item.getProductsPrice());
			System.out.println(item.getPriceIP());
			productResult = productResult.add(jewelryBonus);
			productResult = productResult.multiply(BigDecimal.valueOf(100));
			productResult = productResult.divide(totalPrice,RoundingMode.HALF_UP);
			productResult = productResult.multiply(ipProduct);
			productResult = productResult.divide(BigDecimal.valueOf(100));
			System.out.println("=> " + productResult);
			productResult = ipProduct.subtract(productResult);			
			if (productResult.compareTo(BigDecimal.ZERO) < 0)
				productResult = BigDecimal.valueOf(0);
			System.out.println("IP CALCULATION: " + productResult.setScale(0, BigDecimal.ROUND_HALF_UP));
			result = result.add(productResult.setScale(0, BigDecimal.ROUND_HALF_UP));
			}
		}
		
		System.out.println("TOTAL IP CALCULATION: " + result.setScale(0, BigDecimal.ROUND_HALF_UP));
		return result;
		
	}



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

		// for (CalculationModel cartProductModel : totalsList) {
		// System.out.println(cartProductModel.getTableType());
		// if (cartProductModel.getTableType() != null &&
		// cartProductModel.getTableType().contentEquals(discountClass)) {
		// if (cartProductModel.getAskingPrice() != null) {
		// if (productSum.compareTo(BigDecimal.valueOf(0)) == 0) {
		// System.out.println("ERROR: TOTAL IS EMPTY");
		// }
		// productSum = productSum.add(cartProductModel.getAskingPrice());
		// }
		// }
		// }

		result = productSum.subtract(jewelryDiscount);

		return result;
	}

	public BigDecimal applyMarketingDiscount(List<CalculationModel> totalsList, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal productSum = BigDecimal.valueOf(0);

		productSum = selectCalcModel(totalsList, Constants.DISCOUNT_0).getAskingPrice();

		// // TODO refactor this in a private method with mode
		// for (CalculationModel calculationModel : totalsList) {
		// if (calculationModel.getTableType() != null &&
		// calculationModel.getTableType() == Constants.DISCOUNT_0) {
		// if (calculationModel.getAskingPrice() != null) {
		// if
		// (calculationModel.getAskingPrice().compareTo(BigDecimal.valueOf(0))
		// == 0) {
		// System.out.println("ERROR: TOTAL IS EMPTY");
		// }
		// productSum = calculationModel.getAskingPrice();
		// }
		// }
		// }
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

//			System.out.println("*/*/*/*//**11**1*1*1*1*1*1*1*1*1*1*");
//			System.out.println("TableType: " + calculationModel.getTableType());
//			System.out.println("Mode: " + mode);
//			System.out.println(" contains compare" + calculationModel.getTableType().contains(mode));
//			System.out.println(" contentEquals compare" + calculationModel.getTableType().contentEquals(mode));
//			System.out.println(" = compare" + calculationModel.getTableType() == mode);
//			System.out.println("*/*/*/*//**11**1*1*1*1*1*1*1*1*1*1*");

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

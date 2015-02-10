package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.Constants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

public class CartCalculation {

	private static BigDecimal remainder25 = BigDecimal.valueOf(0);
	private static BigDecimal remainder50 = BigDecimal.valueOf(0);
	private static BigDecimal remainder00 = BigDecimal.valueOf(0);
	private static BigDecimal tax = BigDecimal.valueOf(0);

	// private static BigDecimal jBRegularItems = BigDecimal.valueOf(0);
	// private static BigDecimal sampleRetailSum = BigDecimal.valueOf(0);

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
			totalAmount = totalAmount.subtract(BigDecimal.valueOf(Double.parseDouble(marketingMaterial)).divide(BigDecimal.valueOf(100)));
			result.addCalculation("P5-SubstractMM-divide100", totalAmount.toString());
		}

		// TODO TOTAL_AMOUNT_CALCULATION ROUNDING
		// totalAmount = totalAmount.setScale(2, RoundingMode.DOWN);
		totalAmount = totalAmount.setScale(2, RoundingMode.HALF_DOWN);

		result.addSegment(Constants.DISCOUNT_50, remainder50.setScale(2, RoundingMode.HALF_UP).toString());
		result.addSegment(Constants.DISCOUNT_25, remainder25.setScale(2, RoundingMode.HALF_UP).toString());
		result.addSegment(Constants.DISCOUNT_0, remainder00.setScale(2, RoundingMode.HALF_UP).toString());

		result.setTotalAmount(totalAmount.toString());
		result.setSubTotal(calculateTotalSum(totalsList).getAskingPrice().toString());
		result.setIpPoints(String.valueOf(calculateIpDiscount(totalsList, BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)))));

		calculateTax(calculateTotalSum(totalsList).getAskingPrice(), remainder25, remainder50,
				BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)).divide(BigDecimal.valueOf(100)),
				BigDecimal.valueOf(Double.parseDouble(marketingMaterial)).divide(BigDecimal.valueOf(100)));

		result.setTax(tax.toString());

		System.out.println(" ---- Calculation Results ---- ");
		System.out.println("SUBTOTAL: " + BigDecimal.valueOf(Double.parseDouble(calculateTotalSum(totalsList).getAskingPrice().toString())));
		System.out.println("TOTAL AMOUNT: " + result.getTotalAmount());
		System.out.println("IP : " + result.getIpPoints());
		System.out.println("Tax: " + tax);
		System.out.println("Remainder after 25%: " + remainder25.toString());
		System.out.println("Remainder after 50%: " + remainder50.toString());
		System.out.println("Remainder after 0% : " + remainder00.toString());
		System.out.println(" ----------------------------- ");

		return result;
	}

	public static BigDecimal calculateUsedJewelryBonus(List<CartProductModel> productsList, String jewelryDiscount) {

		BigDecimal jBRegularItems = BigDecimal.ZERO;
		BigDecimal sum25 = BigDecimal.ZERO;
		String formatedJewelryDiscount = formatDiscount(jewelryDiscount);

		for (CartProductModel product : productsList) {

			if (product.getDiscountClass().contains(Constants.DISCOUNT_25)) {
				sum25 = sum25.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		if (sum25.compareTo(BigDecimal.valueOf(Double.parseDouble(jewelryDiscount))) < 0) {
			jBRegularItems = sum25;
		} else {
			jBRegularItems = BigDecimal.valueOf(Double.parseDouble(jewelryDiscount));
		}

		return jBRegularItems;

	}

	public static BigDecimal calculateDiscountAskingPriceSum(List<CartProductModel> productsList,String discountType) {
		BigDecimal sum = BigDecimal.ZERO;
		for (CartProductModel product : productsList) {

			if (product.getDiscountClass().contains(discountType)) {
				sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		return sum;
	}


	public static String calculate50DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jBUsedForRegular, BigDecimal jB, BigDecimal sampleAskingSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = jB.subtract(jBUsedForRegular);
		result = result.multiply(askingPrice);
		result = result.divide(sampleAskingSum, 5, BigDecimal.ROUND_DOWN);
		result = askingPrice.subtract(result);
		result = result.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
		
		System.out.println("calculate50DiscountCartProductFinalPrice  result" + result);
		return String.valueOf(result);
	}

	public static String calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB ,BigDecimal sum25Section) {

		BigDecimal result = BigDecimal.ZERO;	
		if (askingPrice.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;
		} else {
			
			result = result.add(askingPrice);		
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section,10,BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100),10,BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);		
			result = result.multiply(BigDecimal.valueOf(75));
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
		}
		return String.valueOf(result);
	}

	public static String calculateMarketingMaterialCartProductFinalPrice(BigDecimal askingPrice, BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (askingPrice.compareTo(marketingDiscount) < 0) {
			result = BigDecimal.ZERO;
		} else {
			result = result.add(askingPrice);
			System.out.println("1 " + result);
			result = result.multiply(BigDecimal.valueOf(100));
			System.out.println("2 " + result);
			result = result.divide(sumMarketingMaterial,10,BigDecimal.ROUND_HALF_UP);
			System.out.println("3 " + result);
			result = result.divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP);
			System.out.println("4 " +result);
			result = result.multiply(marketingDiscount);
			System.out.println("5 "+ result);
			result = askingPrice.subtract(result);
		}
		return String.valueOf(result);
	}


	public static List<CartProductModel> calculateProducts(List<CartProductModel> productsList, String jewelryDiscount, String marketingDiscount) {

		
		BigDecimal sumMarketingMaterial = calculateDiscountAskingPriceSum(productsList,Constants.DISCOUNT_0);
		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList,Constants.DISCOUNT_25);
		BigDecimal sum50 = calculateDiscountAskingPriceSum(productsList,Constants.DISCOUNT_50);
		BigDecimal jewelryUsed = calculateUsedJewelryBonus(productsList, jewelryDiscount);
		
		System.out.println("sum25 "+ sum25);
		System.out.println("sum50 "+ sum50);
		System.out.println("sumMarketingMaterial "+ sumMarketingMaterial);
		System.out.println("jewelryUsed "+ jewelryUsed);
		
		// TODO fix finalPrice for marketing material section
		List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

		for (CartProductModel product : productsList) {

			CartProductModel newProduct = new CartProductModel();

			if (product.getDiscountClass().contains(Constants.DISCOUNT_50)) {
				newProduct.setDiscountClass(product.getDiscountClass());
				newProduct.setName(product.getName());
				newProduct.setUnitPrice(product.getUnitPrice());
				newProduct.setProdCode(product.getProdCode());
				newProduct.setQuantity(product.getQuantity());
				newProduct.setProductsPrice(product.getProductsPrice());
				newProduct.setPriceIP(product.getPriceIP());
				newProduct.setFinalPrice(calculate50DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), jewelryUsed,
						BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum50));
			}
			if (product.getDiscountClass().contains(Constants.DISCOUNT_25)) {
				newProduct.setDiscountClass(product.getDiscountClass());
				newProduct.setName(product.getName());
				newProduct.setUnitPrice(product.getUnitPrice());
				newProduct.setProdCode(product.getProdCode());
				newProduct.setQuantity(product.getQuantity());
				newProduct.setProductsPrice(product.getProductsPrice());
				newProduct.setPriceIP(product.getPriceIP());
				newProduct.setFinalPrice(calculate25DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
						BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)),sum25));
			}
			if (product.getDiscountClass().contains(Constants.DISCOUNT_0)) {
				newProduct.setDiscountClass(product.getDiscountClass());
				newProduct.setName(product.getName());
				newProduct.setUnitPrice(product.getUnitPrice());
				newProduct.setProdCode(product.getProdCode());
				newProduct.setQuantity(product.getQuantity());
				newProduct.setProductsPrice(product.getProductsPrice());
				newProduct.setPriceIP(product.getPriceIP());
				newProduct.setFinalPrice(calculateMarketingMaterialCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
						BigDecimal.valueOf(Double.parseDouble(marketingDiscount)),sumMarketingMaterial));
			}
			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	// private static BigDecimal discountTotal = BigDecimal.ZERO;

	// new sample
	// public List<CartProductModel> calculateSegments(List<CartProductModel>
	// totalsList, String jewelryDiscount, String marketingMaterial){
	//
	// BigDecimal sum25 = BigDecimal.ZERO;
	// BigDecimal sum50 = BigDecimal.ZERO;
	//
	// //add products totals by segment
	// for (CartProductModel calculationModel : totalsList) {
	// if(calculationModel.getDiscountClass().contains(Constants.DISCOUNT_25)){
	// sum25 =
	// sum25.add(BigDecimal.valueOf(Double.parseDouble(calculationModel.getProductsPrice())));
	// }
	// if(calculationModel.getDiscountClass().contains(Constants.DISCOUNT_50)){
	// sum50 =
	// sum50.add(BigDecimal.valueOf(Double.parseDouble(calculationModel.getProductsPrice())));
	// }
	// }
	//
	// //apply discount on 25 segment
	// sum25 =
	// sum25.subtract(BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)));
	//
	// //validate case of 25 segment
	// if(sum25.compareTo(BigDecimal.ZERO) < 0 ){
	// //formula
	// //calculate individual prices for each product in 25\
	//
	// //remainder should be passed to 50
	// }
	//
	// //apply remaining discount on 50
	// sum50 = sum50.subtract(sum25.abs());
	//
	// //validate case of 50 segment
	// if(sum50.compareTo(BigDecimal.ZERO) > 0 ){
	// //error
	// }else{ //less or equal to 0
	// //formula calculate for each product in 50
	// //[Preis - Preisx(JB – JB used for regular items)/Sum of prices for
	// Sample Products]/2
	// for (CalculationModel calculationModel : totalsList) {
	// if(calculationModel.getTableType().contains(Constants.DISCOUNT_50)){
	// // calculationModel
	//
	//
	//
	// }
	//
	// }
	//
	// }
	//
	//
	//
	// }

	// private static BigDecimal calculateDiscount(CalculationModel productNow,
	// BigDecimal jewelryDiscount){
	// BigDecimal result = BigDecimal.ZERO;
	// BigDecimal termOne = BigDecimal.ZERO;
	//
	// if(discountTotal.compareTo(jewelryDiscount) > 0){
	// termOne = termOne.add(jewelryDiscount);
	// termOne = termOne.subtract(discountTotal);
	//
	// result = result.add(productNow.getAskingPrice());
	// result = result.add(productNow.getAskingPrice());
	//
	// }
	// }

	/**
	 * FORMULA: (Zwischensumme – 50% Muster Rabatt – Genutzer Schmuck Bonus-
	 * Genutzer Marketing Bonus) - (Zwischensumme – 50% Muster Rabatt – Genutzer
	 * Schmuck Bonus- Genutzer Marketing Bonus)/1.19 ADDED QF: - discount25
	 * 
	 * @param totalAmount
	 * @param discount50
	 * @param jewelryDiscount
	 * @param marketingDiscount
	 */
	private void calculateTax(BigDecimal totalAmount, BigDecimal discount25, BigDecimal discount50, BigDecimal jewelryDiscount, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal partial = BigDecimal.ZERO;

		result = result.add(totalAmount);
		result = result.subtract(discount25);
		result = result.subtract(discount50);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		partial = partial.add(result);
		result = result.divide(BigDecimal.valueOf(Double.parseDouble("1.19")), 2, BigDecimal.ROUND_DOWN);
		tax = partial.subtract(result).setScale(1, BigDecimal.ROUND_DOWN);
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
		result = result.divide(regularPrice,10, RoundingMode.HALF_UP);
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

	// modify jewelry discount formatting
	// From 10 To 1000
	// From 10.00 to 1000
	// clean decimals to number
	private static String formatDiscount(String jewelryDiscount) {
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

	/**
	 * Calculate the value of the value without VAT. Eg. result =
	 * value.divide(1.19) result - ROUND_DOWN result - 2 decimal precision
	 * 
	 * @param value
	 *            roundMode - eg BigDecimal.ROUND_HALF_EVEN
	 * @return
	 */
	public BigDecimal apply119VAT(BigDecimal value, int precision, int roundMode) {
		BigDecimal result = BigDecimal.ZERO;
		if (value.compareTo(BigDecimal.ZERO) > 0) {
			// result =
			// value.divide(BigDecimal.valueOf(Double.parseDouble("1.19")), 2,
			// BigDecimal.ROUND_HALF_EVEN);
			result = value.divide(BigDecimal.valueOf(Double.parseDouble("1.19")), precision, roundMode);
		} else {
			System.out.println("Error: Cannot apply 119 VAT value is not greater than 0. " + value.toString());
		}

		return result;
	}

	/**
	 * This method will recalculate the totals without the 19% tax. Will return
	 * a {@link ShippingModel}
	 * 
	 * @param discountCalculationModel
	 * @return ShippingModel
	 */
	public ShippingModel remove119VAT(CalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();
		result.setSubTotal(apply119VAT(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSubTotal())), 2, BigDecimal.ROUND_HALF_EVEN).toString());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_25))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_50))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getMarketingBonus())));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getJewelryBonus())));
		discountCalculation = apply119VAT(discountCalculation, 2, BigDecimal.ROUND_HALF_UP);

		result.setDiscountPrice(discountCalculation.toString());
		result.setShippingPrice(shippingValue);

		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(apply119VAT(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())), 2,
				BigDecimal.ROUND_HALF_DOWN));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

	public ShippingModel calculateShippingTotals(CalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(discountCalculationModel.getSubTotal());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_25))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(Constants.DISCOUNT_50))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getMarketingBonus())));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getJewelryBonus())));
		result.setDiscountPrice(discountCalculation.toString());
		result.setShippingPrice(shippingValue);

		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

	public List<ProductBasicModel> remove119VAT(List<ProductBasicModel> productsList) {

		List<ProductBasicModel> result = new ArrayList<ProductBasicModel>();

		for (ProductBasicModel productBasicModel : productsList) {
			ProductBasicModel now = new ProductBasicModel();

			now.setName(productBasicModel.getName());
			now.setQuantity(productBasicModel.getQuantity());
			now.setType(productBasicModel.getType());
			now.setPrice(apply119VAT(BigDecimal.valueOf(Double.parseDouble(productBasicModel.getPrice())), 2, BigDecimal.ROUND_HALF_DOWN).toString());

			result.add(now);
		}
		return result;
	}
}

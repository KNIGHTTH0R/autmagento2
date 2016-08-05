package com.tools.cartcalculations.smf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.constants.ConfigConstants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.utils.FormatterUtils;

public class CartCalculation {

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

//			PrintUtils.printCalculationModel(result);

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

		if (tableType.contentEquals(ConfigConstants.DISCOUNT_25)) {
			result = 25;
		}
		if (tableType.contentEquals(ConfigConstants.DISCOUNT_50)) {
			result = 50;
		}
		if (tableType.contentEquals(ConfigConstants.DISCOUNT_0)) {
			result = 0;
		}
		return result;
	}

	private static BigDecimal calculateUsedJewelryBonus(List<CartProductModel> productsList, String jewelryDiscount) {

		BigDecimal jBRegularItems = BigDecimal.ZERO;
		BigDecimal sum25 = BigDecimal.ZERO;

		for (CartProductModel product : productsList) {

			if (product.getDiscountClass().contains(ConfigConstants.DISCOUNT_25)) {
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

	private static BigDecimal calculateDiscountAskingPriceSum(List<CartProductModel> productsList, String discountType) {
		BigDecimal sum = BigDecimal.ZERO;
		for (CartProductModel product : productsList) {

			if (product.getDiscountClass().contains(discountType)) {
				sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		return sum;
	}

	public static String calculateIpForEachProduct(BigDecimal initialIpNumber, BigDecimal jB, BigDecimal sum25) {

		BigDecimal result = BigDecimal.ZERO;
		if (sum25.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(jB);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25, 4);
			result = result.multiply(initialIpNumber);
			result = result.divide(BigDecimal.valueOf(100), 2);
			result = initialIpNumber.subtract(result);
			if (result.compareTo(BigDecimal.ZERO) < 0) {
				result = BigDecimal.ZERO;
			}
		}
		return result.setScale(0, RoundingMode.HALF_UP).toString();

	}

	private static String calculate50DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jBUsedForRegular, BigDecimal jB, BigDecimal sampleAskingSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = jB.subtract(jBUsedForRegular);
		result = result.multiply(askingPrice);
		result = result.divide(sampleAskingSum, 5, BigDecimal.ROUND_DOWN);
		result = askingPrice.subtract(result);
		result = result.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
		return String.valueOf(result.setScale(2));
	}

	private static String calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB, BigDecimal sum25Section) {

		BigDecimal result = BigDecimal.ZERO;
		if (sum25Section.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);
			result = result.multiply(BigDecimal.valueOf(75));
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

		}

		return String.valueOf(result.setScale(2));
	}

	private static BigDecimal calculate25DiscountForEachProduct(BigDecimal askingPrice, BigDecimal jB, BigDecimal sum25Section) {

		BigDecimal result = BigDecimal.ZERO;
		if (askingPrice.compareTo(jB) < 0) {
			result = BigDecimal.ZERO;
		} else {

			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(jB);
			result = askingPrice.subtract(result);
			result = result.multiply(BigDecimal.valueOf(25));
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
		}
		return result;
	}

	private static String calculateMarketingMaterialCartProductFinalPrice(BigDecimal askingPrice, BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (askingPrice.compareTo(marketingDiscount) < 0) {
			result = BigDecimal.ZERO;
		} else {
			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sumMarketingMaterial, 2, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(marketingDiscount);

			result = askingPrice.subtract(result);
		}
		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static List<CartProductModel> calculateProductsforMarketingMaterial(List<CartProductModel> productsList, String marketingDiscount) {

		BigDecimal sumMarketingMaterial = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_0);

		System.out.println(sumMarketingMaterial);

		List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

		for (CartProductModel product : productsList) {

			CartProductModel newProduct = new CartProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculateMarketingMaterialCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), sumMarketingMaterial));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<CartProductModel> calculateProductsfor50Discount(List<CartProductModel> productsList, List<CartProductModel> list25DiscountProducts, String jewelryDiscount) {

		BigDecimal sum50 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_50);
		BigDecimal jewelryUsed = calculateUsedJewelryBonus(list25DiscountProducts, jewelryDiscount);

		List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

		for (CartProductModel product : productsList) {

			CartProductModel newProduct = new CartProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculate50DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), jewelryUsed,
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum50));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<CartProductModel> calculateProductsfor25Discount(List<CartProductModel> productsList, String jewelryDiscount) {

		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_25);

		List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

		for (CartProductModel product : productsList) {

			CartProductModel newProduct = new CartProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())), BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)),
					sum25));
			newProduct.setFinalPrice(calculate25DiscountCartProductFinalPrice(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static BigDecimal calculateBuyThreeGetOneDiscountForEachSegment(List<CartProductModel> productsList, String discountType) {

		BigDecimal discount = BigDecimal.ZERO;

		List<CartProductModel> newList = getSublistFromList(productsList, discountType);

		CartProductModel cheepest = getCheapestProduct(newList);

		for (CartProductModel product : newList) {

			if (product == cheepest) {
				discount = BigDecimal.valueOf(Double.parseDouble(product.getUnitPrice())).divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
			}
		}
		return discount;
	}

	public static List<CartProductModel> getSublistFromList(List<CartProductModel> productsList, String discountType) {

		List<CartProductModel> newList = new ArrayList<CartProductModel>();
		for (CartProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contentEquals(discountType))
				newList.add(cartProductModel);
		}

		return newList;
	}

	/**
	 * Returns the cheapest product from a list.
	 * 
	 * @param productsList
	 * @return result
	 */
	public static CartProductModel getCheapestProduct(List<CartProductModel> productsList) {

		CartProductModel result = new CartProductModel();
		if (productsList.size() != 0) {
			result = productsList.get(0);
			for (CartProductModel cartProductModel : productsList) {
				if (Double.parseDouble(cartProductModel.getUnitPrice()) <= Double.parseDouble(result.getUnitPrice())) {
					result = cartProductModel;
				}
			}
		}
		return result;
	}

	public static boolean isBuy3Get1Applicable(List<CartProductModel> productList) {
		int quantity = 0;
		for (CartProductModel cartProductModel : productList) {
			quantity += Integer.parseInt(cartProductModel.getQuantity());
		}
		if (quantity >= 3) {
			return true;
		} else
			return false;
	}

	/**
	 * This method will recalculate the cart totals based on a produsct list
	 * given . Will return a CalcDetailModel
	 * 
	 * @param productsList
	 * @param jewerlyDiscount
	 * @param marketingDiscount
	 * @return result
	 */
	public static CalcDetailsModel calculateCartProductsTotals(List<CartProductModel> productsList, String jewerlyDiscount, String marketingDiscount, String taxClass) {
		CalcDetailsModel result = new CalcDetailsModel();

		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;

		for (CartProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
				rabatt25, rabattBuy3Get1);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(ConfigConstants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));

		return result;
	}

	public static CalcDetailsModel calculateCartProductsTotalsBuy3GetOneRuleApplied(List<CartProductModel> productsList, String jewerlyDiscount, String marketingDiscount,
			String taxClass) {
		CalcDetailsModel result = new CalcDetailsModel();

		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_25);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal rabatt50 = BigDecimal.ZERO;
		BigDecimal rabatt25 = BigDecimal.ZERO;
		BigDecimal rabattBuy3Get1 = BigDecimal.ZERO;
		BigDecimal tax = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal ipPoints = BigDecimal.ZERO;
		BigDecimal buy3get1discount25Section = BigDecimal.ZERO;
		BigDecimal buy3get1discount50Section = BigDecimal.ZERO;
		BigDecimal buy3get1discountMMSection = BigDecimal.ZERO;

		if (isBuy3Get1Applicable(getSublistFromList(productsList, ConfigConstants.DISCOUNT_25))) {
			buy3get1discount25Section = calculateBuyThreeGetOneDiscountForEachSegment(productsList, ConfigConstants.DISCOUNT_25);
		}
		if (isBuy3Get1Applicable(getSublistFromList(productsList, ConfigConstants.DISCOUNT_0))) {
			buy3get1discountMMSection = calculateBuyThreeGetOneDiscountForEachSegment(productsList, ConfigConstants.DISCOUNT_0);
		}
		if (isBuy3Get1Applicable(getSublistFromList(productsList, ConfigConstants.DISCOUNT_50))) {
			buy3get1discount50Section = calculateBuyThreeGetOneDiscountForEachSegment(productsList, ConfigConstants.DISCOUNT_0);
		}
		rabattBuy3Get1 = buy3get1discount25Section.add(buy3get1discountMMSection);
		rabattBuy3Get1 = rabattBuy3Get1.add(buy3get1discount50Section);

		for (CartProductModel product : productsList) {
			subtotal = subtotal.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			rabatt50 = calculate50Discount(productsList);
			rabatt25 = calculate25Discount(productsList, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), sum25);
			ipPoints = ipPoints.add(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())));
		}

		totalAmount = calculateTotalAmount(subtotal, BigDecimal.valueOf(Double.parseDouble(jewerlyDiscount)), BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), rabatt50,
				rabatt25, rabattBuy3Get1);

		tax = totalAmount.multiply(BigDecimal.valueOf(Double.parseDouble(taxClass)));
		tax = tax.divide(BigDecimal.valueOf(Double.parseDouble("100") + Double.parseDouble(taxClass)), 2, BigDecimal.ROUND_HALF_UP);

		result.setSubTotal(String.valueOf(subtotal));
		result.setJewelryBonus(jewerlyDiscount);
		result.setMarketingBonus(marketingDiscount);
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPoints.intValue()));
		result.setTax(String.valueOf(tax));
		result.addSegment(ConfigConstants.DISCOUNT_50, String.valueOf(rabatt50));
		result.addSegment(ConfigConstants.DISCOUNT_25, String.valueOf(rabatt25));
		result.addSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1, String.valueOf(rabattBuy3Get1));

		return result;
	}

	private static BigDecimal calculate50Discount(List<CartProductModel> productsList) {

		BigDecimal discountSum = BigDecimal.ZERO;

		for (CartProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(ConfigConstants.DISCOUNT_50)) {
				discountSum = discountSum.add(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getFinalPrice())));
			}
		}
		return discountSum;

	}

	private static BigDecimal calculate25Discount(List<CartProductModel> productsList, BigDecimal jewelryDiscount, BigDecimal sum25Section) {

		BigDecimal discountSum = BigDecimal.ZERO;

		for (CartProductModel cartProductModel : productsList) {
			if (cartProductModel.getDiscountClass().contains(ConfigConstants.DISCOUNT_25)) {
				discountSum = discountSum.add(calculate25DiscountForEachProduct(BigDecimal.valueOf(Double.parseDouble(cartProductModel.getProductsPrice())), jewelryDiscount,
						sum25Section));
			}

		}
		return discountSum;

	}

	private static BigDecimal calculateTotalAmount(BigDecimal subtotal, BigDecimal jewelryDiscount, BigDecimal marketingDiscount, BigDecimal sum50Discount,
			BigDecimal sum25Discount, BigDecimal buy3Get1) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(subtotal);
		result = result.subtract(jewelryDiscount);
		result = result.subtract(marketingDiscount);
		result = result.subtract(sum25Discount);
		result = result.subtract(sum50Discount);
		result = result.subtract(buy3Get1);

		return result;
	}

	public static BigDecimal applyMarketingDiscount(List<CalculationModel> totalsList, BigDecimal marketingDiscount) {

		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal productSum = BigDecimal.valueOf(0);

		productSum = selectCalcModel(totalsList, ConfigConstants.DISCOUNT_0).getAskingPrice();

		result = productSum.subtract(marketingDiscount);

		return result;

	}

	private static CalculationModel selectCalcModel(List<CalculationModel> totalsList, String mode) {

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
	public static BigDecimal apply119VAT(BigDecimal value, int precision, int roundMode) {
		BigDecimal result = BigDecimal.ZERO;
		if (value.compareTo(BigDecimal.ZERO) > 0) {
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
	public static ShippingModel remove119VAT(CalcDetailsModel discountCalculationModel, String shippingValue) {
		ShippingModel result = new ShippingModel();
		result.setSubTotal(apply119VAT(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSubTotal())), 2, BigDecimal.ROUND_HALF_EVEN).toString());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_25))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(discountCalculationModel.getSegments().get(ConfigConstants.DISCOUNT_50))));
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

	public static ShippingModel calculateShippingTotals(CalcDetailsModel calcDetailsModel, String shippingValue) {
		ShippingModel result = new ShippingModel();

		result.setSubTotal(calcDetailsModel.getSubTotal());

		// discount calculation
		BigDecimal discountCalculation = BigDecimal.ZERO;
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getSegments().get(ConfigConstants.DISCOUNT_25))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getSegments().get(ConfigConstants.DISCOUNT_50))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getSegments().get(ConfigConstants.DISCOUNT_BUY_3_GET_1))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getSegments().get(ConfigConstants.VOUCHER_DISCOUNT))));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getMarketingBonus())));
		discountCalculation = discountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getJewelryBonus())));

		shippingValue = GeneralCartCalculations
				.calculateNewShipping(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getTotalAmount())),
						BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getSegments().get(ConfigConstants.VOUCHER_DISCOUNT))),
						BigDecimal.valueOf(Double.parseDouble(shippingValue)));

		result.setDiscountPrice(discountCalculation.toString());
		result.setShippingPrice(shippingValue);

		// totals calculation
		BigDecimal totalAmountCalculation = BigDecimal.ZERO;
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(calcDetailsModel.getTotalAmount())));
		totalAmountCalculation = totalAmountCalculation.add(BigDecimal.valueOf(Double.parseDouble(shippingValue)));
		result.setTotalAmount(totalAmountCalculation.toString());

		return result;
	}

	public static List<ProductBasicModel> remove119VAT(List<ProductBasicModel> productsList) {

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

	public static List<BasicProductModel> remove19(List<BasicProductModel> productsList) {

		List<BasicProductModel> result = new ArrayList<BasicProductModel>();

		for (BasicProductModel productBasicModel : productsList) {
			BasicProductModel now = new BasicProductModel();

			now.setName(productBasicModel.getName());
			now.setQuantity(productBasicModel.getQuantity());
			now.setProdCode(productBasicModel.getProdCode());
			now.setUnitPrice(apply119VAT(BigDecimal.valueOf(Double.parseDouble(productBasicModel.getUnitPrice())), 2, BigDecimal.ROUND_HALF_DOWN).toString());

			result.add(now);
		}
		return result;
	}
}

package com.tools.cartcalculations.smf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.tools.constants.ConfigConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.workflows.frontend.AddProductsWorkflow;

import net.thucydides.core.annotations.Steps;

/**
 * @author mihai
 *
 */


public class CartDiscountsCalculation {
	
	@Steps
	public static AddProductsWorkflow addProductsWorkflow;

	public static String calculateAskingPrice(String unitPrice, String quantity) {

		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(unitPrice));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));
		return String.valueOf(price.multiply(qty).setScale(2, RoundingMode.CEILING));

	}

	public static String calculateIpPoints(String ip, String quantity) {

		BigDecimal IP = BigDecimal.valueOf(Double.parseDouble(ip));
		BigDecimal qty = BigDecimal.valueOf(Double.parseDouble(quantity));

		return String.valueOf(IP.multiply(qty).intValue());
	}

	/**
	 * @param askingPrice
	 * @param discount
	 * @param delta
	 * @return  Returns the final price based on delta which remains from the previous product
	 */
	public static String calculateFinalPrice(String askingPrice, String discount, String delta) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		BigDecimal disc = BigDecimal.valueOf(Double.parseDouble(discount));
		BigDecimal deltaAmount = BigDecimal.valueOf(Double.parseDouble(delta));

		//delta is 0 for sample and marketing material products
		if (discount != ConfigConstants.DISCOUNT_20)
			deltaAmount = BigDecimal.ZERO;

		discountValue = askPrice.multiply(disc);
		//we calculate the result with 5 decimals in order to have the four decimals unmodified
		diff = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
		//the delta is added to the discount
		diff = diff.add(deltaAmount);
		// the discount after delta is added must have 2 decimals
		discountValue = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
		// the difference (the remaining delta for the next product must have 4 decimals)
		diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
		//the diff will be the result after substracting the 2 decimals result from the 4 decimals rto haesult (Ex: 13.4550 - 13.45 = -0.0050)
		diff = diff.subtract(discountValue);
		//we set the value of diff for the static variable CartCalculator.delta
		CartCalculator.delta = String.valueOf(diff);
		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2));

	}

	public static String calculateFinalPriceRegularCart(String askingPrice) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);

		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static String calculateFinalPriceRegular(String askingPrice) {

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal discountValue = BigDecimal.ZERO;

		BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(askingPrice));
		discountValue = discountValue.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);

		result = askPrice.subtract(discountValue);

		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public static List<BasicProductModel> calculateProductsforMarketingMaterial(List<BasicProductModel> productsList,
			String marketingDiscount) {

		BigDecimal sumMarketingMaterial = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_0);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setDiscountMarketing(calculateMarketingMaterialDiscountApplied(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), sumMarketingMaterial));
			newProduct.setFinalPrice(calculateMarketingMaterialCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(marketingDiscount)), sumMarketingMaterial));
			System.out.println("marketing final Price" +newProduct.getFinalPrice());
			newProduct.setTax(calculateProductTaxAmount(BigDecimal.valueOf(Double.valueOf(newProduct.getFinalPrice()))).toString());
			System.out.println("marketing tax Price" +newProduct.getTax());
			newProduct.setTotalDiscount(calculateTotalDiscount(newProduct.getDiscount20(),newProduct.getDiscountJb(),newProduct.getDiscountMarketing()));


			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor50Discount(List<BasicProductModel> productsList,
			List<BasicProductModel> list25DiscountProducts, String jewelryDiscount) {

		BigDecimal sum50 = calculateDiscountAskingPriceSum(productsList, ConfigConstants.DISCOUNT_50);
		BigDecimal jewelryUsed = calculateUsedJewelryBonus(list25DiscountProducts, jewelryDiscount);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculate50DiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())), jewelryUsed,
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum50));

			cartProducts.add(newProduct);
		}

		return cartProducts;

	}
	
	public static List<BasicProductModel> productsPriceWithBuy3Get1(List<BasicProductModel> productsList,List<BasicProductModel> productsList2
			) {

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		
		for (BasicProductModel product : productsList) {
		//	Optional<BasicProductModel> element=productsList2.stream().filter(o -> o.getName().equals(product.get)).findFirst();
			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static List<BasicProductModel> calculateProductsfor25Discount(List<BasicProductModel> products,
			String jewelryDiscount) {

		List<BasicProductModel> productList=getOnly25Products(products);
		BigDecimal sum25 = calculateDiscountAskingPriceSum(productList, ConfigConstants.DISCOUNT_20);
		//BigDecimal sum20= calculateSumWithout20Percent(sum25);

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		BigDecimal delta = BigDecimal.ZERO;

		
		for (BasicProductModel product : productList) {
			
			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setColour(product.getColour());
			newProduct.setSize(product.getSize());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setFinalPriceWithBuy3(product.getFinalPriceWithBuy3());
			/*newProduct
					.setPriceIP(calculateIpForEachProduct(BigDecimal.valueOf(Double.parseDouble(product.getPriceIP())),
							BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25));*/
			newProduct
			.setPriceIP("0");
			String[] discounts = calculate25DiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(jewelryDiscount)), sum25, delta);

			
			newProduct.setFinalPrice(discounts[0]);
			System.out.println("20 section: "+newProduct.getFinalPrice());
			newProduct.setTax(calculateProductTaxAmount(BigDecimal.valueOf(Double.valueOf(discounts[0]))).toString());
			
			
			
			delta = BigDecimal.valueOf(Double.parseDouble(discounts[1]));
			
			newProduct.setDiscount20(calculateDiscount20(BigDecimal.valueOf(Double.valueOf(product.getProductsPrice()))).toString());
			newProduct.setDiscountJb(discounts[3]);
			newProduct.setTotalDiscount(calculateTotalDiscount(newProduct.getDiscount20(),newProduct.getDiscountJb(),newProduct.getDiscountMarketing()));
			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	private static String calculateTotalDiscount(String discount20, String discountJb,String discountMk) {
		BigDecimal totalDiscounts=BigDecimal.ZERO;
		totalDiscounts=totalDiscounts.add(BigDecimal.valueOf(Double.valueOf(discount20)));
		totalDiscounts=totalDiscounts.add(BigDecimal.valueOf(Double.valueOf(discountJb)));
		totalDiscounts=totalDiscounts.add(BigDecimal.valueOf(Double.valueOf(discountMk)));
		return totalDiscounts.setScale(2,RoundingMode.HALF_UP).toString();
	}

	private static BigDecimal calculateDiscount20(BigDecimal sum25) {
		// TODO Auto-generated method stub
		return sum25.multiply(BigDecimal.valueOf(0.2)).setScale(2, RoundingMode.HALF_UP);	}

	private static BigDecimal calculateSumWithout20Percent(BigDecimal sum25) {
		
		return sum25.multiply(BigDecimal.valueOf(0.8).setScale(2, RoundingMode.HALF_UP));
		// TODO Auto-generated method stub
		
	}
	
	

	private static List<BasicProductModel> getOnly25Products(List<BasicProductModel> products) {
		List<BasicProductModel> productList=new ArrayList<BasicProductModel>();
		for (BasicProductModel product : products) {
			if(product.getDiscountClass().contentEquals(ConfigConstants.DISCOUNT_20)){
				productList.add(product);
			}
		}
		return productList;
	}

	public static List<BasicProductModel> calculateAskingPriceWithActiveDiscountRule(
			List<BasicProductModel> productsList, String ruleDiscount, BigDecimal productsSum) {

		List<BasicProductModel> cartProducts = new ArrayList<BasicProductModel>();

		for (BasicProductModel product : productsList) {

			BasicProductModel newProduct = new BasicProductModel();

			newProduct.setDiscountClass(product.getDiscountClass());
			newProduct.setName(product.getName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setProdCode(product.getProdCode());
			newProduct.setQuantity(product.getQuantity());
			newProduct.setProductsPrice(product.getProductsPrice());
			newProduct.setPriceIP(product.getPriceIP());
			newProduct.setFinalPrice(calculateRuleDiscountCartProductFinalPrice(
					BigDecimal.valueOf(Double.parseDouble(product.getFinalPrice())),
					BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())),
					BigDecimal.valueOf(Double.parseDouble(ruleDiscount)), productsSum));
			product.getFinalPrice();
			cartProducts.add(newProduct);
		}

		return cartProducts;

	}

	public static BigDecimal calculateUsedJewelryBonus(List<BasicProductModel> productsList, String jewelryDiscount) {

		BigDecimal jBRegularItems = BigDecimal.ZERO;
		BigDecimal sum25 = BigDecimal.ZERO;

		for (BasicProductModel product : productsList) {

			if (product.getDiscountClass().contains(ConfigConstants.DISCOUNT_20)) {
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

	public static BigDecimal calculateDiscountAskingPriceSum(List<BasicProductModel> productsList,
			String discountType) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BasicProductModel product : productsList) {

			if (product.getDiscountClass().contains(discountType)) {
				sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
			}
		}
		return sum;
	}

	
	
	public static BigDecimal calculateSubtotal(List<BasicProductModel> productsList) {
		BigDecimal sum = BigDecimal.ZERO;
		for (BasicProductModel product : productsList) {

			sum = sum.add(BigDecimal.valueOf(Double.parseDouble(product.getProductsPrice())));
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

	public static String calculate50DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jBUsedForRegular,
			BigDecimal jB, BigDecimal sampleAskingSum) {

		BigDecimal result = BigDecimal.ZERO;

		result = jB.subtract(jBUsedForRegular);
		result = result.multiply(askingPrice);
		result = result.divide(sampleAskingSum, 4, BigDecimal.ROUND_DOWN);
		result = askingPrice.subtract(result);
		result = result.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
		return String.valueOf(result.setScale(2));
	}

	/**
	 * @param askingPrice
	 * @param jB
	 * @param sum25Section
	 * 
	 * @return Returns the discount applied for a product and the remaining
	 *         discount to be applied on the next product
	 */
	
	
	public static BigDecimal calculateProductTaxAmount(BigDecimal rowTotal) {
		BigDecimal taxAmount=BigDecimal.ZERO;
		BigDecimal taxAmountValue=BigDecimal.valueOf(1.19);
		
		
		if (taxAmount.compareTo(rowTotal) == 0) {
			taxAmount = BigDecimal.ZERO;

		} else{
			taxAmount=taxAmount.add(rowTotal);
			taxAmount=taxAmount.divide(taxAmountValue,6, RoundingMode.HALF_UP);
			taxAmount=rowTotal.subtract(taxAmount);
		}
		
		return taxAmount.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	
	/*public static String[] calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB,
			BigDecimal sum25Section, BigDecimal deltaDiscount) {

		System.out.println("sum25Section: "+sum25Section);
		System.out.println("askingPrice: "+askingPrice);
		System.out.println("jB"+jB);
		
		String[] discountAndRemainder = new String[4];

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;
		BigDecimal discount25 = BigDecimal.ZERO;
		BigDecimal discountJB = BigDecimal.ZERO;

		System.out.println(sum25Section.compareTo(jB) > 0);
		if (sum25Section.compareTo(jB) > 0) {

			result = result.add(askingPrice);
			System.out.println("result 0: "+result);

			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section, 4, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			System.out.println("result 1: "+result);
			
			result = result.multiply(jB);
			result = askingPrice.subtract(result);
			BigDecimal temp = result;
			result = result.multiply(BigDecimal.valueOf(Double.valueOf(ConfigConstants.DISCOUNT_20)));
			// the 25% disc is calculated with 5 decimals precision (we don't
			// want the 4th decimal rounded)
			diff = result.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
			diff = diff.add(deltaDiscount);// add delta discount from previous
											// product
			System.out.println("that diff: " + diff);
			// the calculated discount is rounded to 2 decimals- actual discount
			result = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
			// the calculated discount is rounded to 4 decimals (expected)
			diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
			// we calculate the remaining discount difference to be applied on
			// the next product
			diff = diff.subtract(result);
			
			System.out.println("DIF "+diff);
			result = temp.subtract(result);


		}
		discountAndRemainder[0] = String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[1] = String.valueOf(diff.setScale(4, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[2] = String.valueOf(discount25.setScale(2, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[3] = String.valueOf(discountJB.setScale(0, BigDecimal.ROUND_HALF_UP));
		
		System.out.println("result: "+ result);
		System.out.println("diff: "+diff);
		System.out.println("discount25: "+discount25);
		System.out.println("discountJB: "+ discountJB);
		
		return discountAndRemainder;
	}*/
	
	
	public static String[] calculate25DiscountCartProductFinalPrice(BigDecimal askingPrice, BigDecimal jB,
			BigDecimal sum25Section, BigDecimal deltaDiscount) {

		System.out.println("sum25Section: "+sum25Section);
		System.out.println("askingPrice: "+askingPrice);
		System.out.println("jB"+jB);
		
		String[] discountAndRemainder = new String[4];

		BigDecimal result = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;
		BigDecimal discount25 = BigDecimal.ZERO;
		BigDecimal discountJB = BigDecimal.ZERO;
		BigDecimal askingPrice20 = BigDecimal.ZERO;
		BigDecimal sum25Section20 = BigDecimal.ZERO;

		askingPrice20=askingPrice.multiply(BigDecimal.valueOf(0.8));
		sum25Section20=sum25Section.multiply(BigDecimal.valueOf(0.8));

		System.out.println(sum25Section20.compareTo(jB) > 0);
		if (sum25Section20.compareTo(jB) > 0) {

		/*	result=result.add(jB);
			System.out.println("1: "+result);
			result=result.divide(sum25Section, 10, BigDecimal.ROUND_HALF_UP);
			System.out.println("2: "+result);
			askingPrice20=askingPrice.multiply(BigDecimal.valueOf(0.8));
			System.out.println("3: "+askingPrice20);
			discount25=askingPrice.subtract(askingPrice20);
			System.out.println("3.1: "+discount25);
			result=result.multiply(askingPrice20);
			System.out.println("4: "+result);

			finalValue=finalValue.add(result);
			System.out.println("5: "+finalValue);

			result=askingPrice.subtract(finalValue);
			System.out.println("6: "+result);

			
			System.out.println("result"+result);
			*/
					
					
	
			
			result = result.add(askingPrice20);

			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sum25Section20, 4, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
			
			result = result.multiply(jB);
			discountJB=discountJB.add(result);
			result = askingPrice20.subtract(result);
			discount25=askingPrice.multiply(BigDecimal.valueOf(0.2));
			BigDecimal temp = result;
			System.out.println("temp: "+temp);
			//result = result.multiply(BigDecimal.valueOf(Double.valueOf(ConfigConstants.DISCOUNT_20)));
			// the 25% disc is calculated with 5 decimals precision (we don't
			// want the 4th decimal rounded)
		//	diff = result.divide(BigDecimal.valueOf(100), 5, BigDecimal.ROUND_HALF_UP);
			diff=result.setScale(5,BigDecimal.ROUND_HALF_UP);
			diff = diff.add(deltaDiscount);// add delta discount from previous
											// product
			System.out.println("that diff: " + diff);
			// the calculated discount is rounded to 2 decimals- actual discount
			result = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			
			// the calculated discount is rounded to 4 decimals (expected)
			diff = diff.setScale(4, BigDecimal.ROUND_HALF_UP);
			// we calculate the remaining discount difference to be applied on
			// the next product
			diff = diff.subtract(result);
			
		//	System.out.println("DIF "+diff);
		//	System.out.println("that temp: "+temp);
		//	System.out.println("that result: "+result);
		//	result = temp.setScale(2).subtract(result);
		//	System.out.println("acest result"+result);


		}
		discountAndRemainder[0] = String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[1] = String.valueOf(diff.setScale(4, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[2] = String.valueOf(discount25.setScale(2, BigDecimal.ROUND_HALF_UP));
		discountAndRemainder[3] = String.valueOf(discountJB.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		System.out.println("result: "+ result);
		System.out.println("diff: "+diff);
		System.out.println("discount25: "+discount25);
		System.out.println("discountJB: "+ discountJB);
		
		return discountAndRemainder;
	}

	public static String calculateRuleDiscountCartProductFinalPrice(BigDecimal finalPrice, BigDecimal askingPrice,
			BigDecimal ruleDiscount, BigDecimal totalAmount) {

		BigDecimal result = BigDecimal.ZERO;
		if (totalAmount.compareTo(ruleDiscount) < 0) {
			result = BigDecimal.ZERO;

		} else {

			result = result.add(askingPrice);
			result = result.multiply(ruleDiscount);
			result = result.divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP);
			result = finalPrice.subtract(result);
			result = result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
		}

		return String.valueOf(result.setScale(2));
	}

	public static String calculateMarketingMaterialCartProductFinalPrice(BigDecimal askingPrice,
			BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (sumMarketingMaterial.compareTo(marketingDiscount) < 0) {
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
	
	
	public static String calculateMarketingMaterialDiscountApplied(BigDecimal askingPrice,
			BigDecimal marketingDiscount, BigDecimal sumMarketingMaterial) {

		BigDecimal result = BigDecimal.ZERO;
		if (sumMarketingMaterial.compareTo(marketingDiscount) < 0) {
			result = BigDecimal.ZERO;
		} else {
			result = result.add(askingPrice);
			result = result.multiply(BigDecimal.valueOf(100));
			result = result.divide(sumMarketingMaterial, 2, BigDecimal.ROUND_HALF_UP);
			result = result.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(marketingDiscount);
			System.out.println(result);
		}
		return String.valueOf(result.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	
	
	public static BasicProductModel addInCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		BasicProductModel result = new BasicProductModel();
		
		System.out.println(discountclass);
		System.out.println(askingPrice);
		System.out.println(finalPrice);
		System.out.println(ipPoints);

		result.setDiscountClass(discountclass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ipPoints);	
		
		return result;
	}
	
	/*private static ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private static ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private static ProductDetailedModel genProduct3 = new ProductDetailedModel();
//	private static List<BasicProductModel> productsList =new ArrayList<BasicProductModel>();
	private static BasicProductModel productData;
//	private static BasicProductModel productData2;
//	private static BasicProductModel productData3;
	public static List<BasicProductModel> productsList25Beta = new ArrayList<BasicProductModel>();*/
	
	
	public static void main(String[] args) {
		/*genProduct1 = MagentoProductCalls.createProductModelBeta();
		genProduct1.setIp("0");
		genProduct1.setPrice("49.90");
		
		genProduct2 = MagentoProductCalls.createProductModelBeta();
		genProduct2.setIp("0");
		genProduct2.setPrice("89.90");
		
		genProduct3 = MagentoProductCalls.createProductModelBeta();
		genProduct3.setIp("25");
		genProduct3.setPrice("49.90");
		
		productData = addInCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_20);
	//	productData2=addInCart(genProduct2, "2", "0", ConfigConstants.DISCOUNT_20);
//		productData3=addInCart(genProduct3, "1", "0", ConfigConstants.DISCOUNT_20);
		productsList25Beta.add(productData);
	//	productsList25Beta.add(productData2);
//		productsList25Beta.add(productData3);
		
		System.out.println(productsList25Beta.get(0).getFinalPrice());
//		
		BigDecimal sum25 = calculateDiscountAskingPriceSum(productsList25Beta, ConfigConstants.DISCOUNT_20);
		System.out.println("sumaaaa "+sum25);
	
		List<BasicProductModel> cartProductsBeta=calculateProductsfor25Discount(productsList25Beta,"150");
		
		System.out.println(""+cartProductsBeta.get(1).getFinalPrice());
		
		CartTotalsCalculation.calculateCartProductsTotals(cartProductsBeta, "150", "0", "19", "3.9",
				"3.9");*/
		BigDecimal askingPrice=BigDecimal.valueOf(55.90);
		/*BigDecimal jB=BigDecimal.valueOf(30);
		BigDecimal sumMarketingMaterial=BigDecimal.valueOf(55.90);
		BigDecimal deltaDiscount=BigDecimal.valueOf(0);*/
		//System.out.println(calculate25DiscountCartProductFinalPrice(askingPrice, jB, sumMarketingMaterial, deltaDiscount));
		System.out.println(calculateSumWithout20Percent(askingPrice));
	}
}

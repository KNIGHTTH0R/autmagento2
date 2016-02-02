package com.tools.cartcalculations.smf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.CalcDetailsModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.utils.PrintUtils;

public class CartCalculator {

	public static List<BasicProductModel> allProductsList = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> allProductsListDiscountRule = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsList25 = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsList50 = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsListMarketing = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsList25DiscountRule = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsList50DiscountRule = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productsListMarketingDiscountRule = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productListForBuy3Get1 = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> productList25AndMmWithBuy3Get1Applied = new ArrayList<BasicProductModel>();

	public static List<BasicProductModel> calculatedProductsList25 = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> calculatedProductsList50 = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> calculatedProductsListMarketing = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> allProductsListRecalculated = new ArrayList<BasicProductModel>();
	public static List<BasicProductModel> shippingProductsList = new ArrayList<BasicProductModel>();

	public static CalcDetailsModel calculatedTotalsDiscounts = new CalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<BasicProductModel>();
		allProductsListDiscountRule = new ArrayList<BasicProductModel>();
		productsList25 = new ArrayList<BasicProductModel>();
		productsList50 = new ArrayList<BasicProductModel>();
		productsListMarketing = new ArrayList<BasicProductModel>();
		productsList25DiscountRule = new ArrayList<BasicProductModel>();
		productsList50DiscountRule = new ArrayList<BasicProductModel>();
		productsListMarketingDiscountRule = new ArrayList<BasicProductModel>();
		productListForBuy3Get1 = new ArrayList<BasicProductModel>();
		productList25AndMmWithBuy3Get1Applied = new ArrayList<BasicProductModel>();
		shippingProductsList = new ArrayList<BasicProductModel>();
		calculatedProductsList25 = new ArrayList<BasicProductModel>();
		calculatedProductsList50 = new ArrayList<BasicProductModel>();
		calculatedProductsListMarketing = new ArrayList<BasicProductModel>();
		allProductsListRecalculated = new ArrayList<BasicProductModel>();
		calculatedTotalsDiscounts = new CalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();

	}

	public static void calculateShippingWith19PercentRemoved(String shippingValue) {

		shippingCalculatedModel = CartCalculation.remove119VAT(calculatedTotalsDiscounts, shippingValue);
		shippingProductsList = CartCalculation.remove19(allProductsList);

	}

	public static void calculateJMDiscounts(String jewelryDiscount, String marketingDiscount, String taxClass, String shippingValue) {
		allProductsList.addAll(productsList25);
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productsListMarketing);

		calculatedProductsList25 = CartDiscountsCalculation.calculateProductsfor25Discount(productsList25, jewelryDiscount);

		calculatedProductsList50 = CartDiscountsCalculation.calculateProductsfor50Discount(productsList50, productsList25, jewelryDiscount);

		calculatedProductsListMarketing = CartDiscountsCalculation.calculateProductsforMarketingMaterial(productsListMarketing, marketingDiscount);

		allProductsListRecalculated.addAll(calculatedProductsList50);
		allProductsListRecalculated.addAll(calculatedProductsList25);
		allProductsListRecalculated.addAll(calculatedProductsListMarketing);

		calculatedTotalsDiscounts = CartTotalsCalculation.calculateCartProductsTotals(allProductsListRecalculated, jewelryDiscount, marketingDiscount, taxClass, shippingValue,
				shippingValue);
		shippingCalculatedModel = CartCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculateJMDiscountsWithActiveDiscountVoucher(String ruleDiscount, String jewelryDiscount, String marketingDiscount, String taxClass, String shippingValue) {

		allProductsList.addAll(productsList25);
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productsListMarketing);

		BigDecimal productsSum = CartDiscountsCalculation.calculateSubtotal(allProductsList);

		productsList25DiscountRule = CartDiscountsCalculation.calculateAskingPriceWithActiveDiscountRule(productsList25, ruleDiscount, productsSum);
		productsList50DiscountRule = CartDiscountsCalculation.calculateAskingPriceWithActiveDiscountRule(productsList50, ruleDiscount, productsSum);
		productsListMarketingDiscountRule = CartDiscountsCalculation.calculateAskingPriceWithActiveDiscountRule(productsListMarketing, ruleDiscount, productsSum);

		calculatedProductsList25 = CartDiscountsCalculation.calculateProductsfor25Discount(productsList25DiscountRule, jewelryDiscount);

		calculatedProductsList50 = CartDiscountsCalculation.calculateProductsfor50Discount(productsList50DiscountRule, productsList25DiscountRule, jewelryDiscount);

		calculatedProductsListMarketing = CartDiscountsCalculation.calculateProductsforMarketingMaterial(productsListMarketingDiscountRule, marketingDiscount);

		allProductsListRecalculated.addAll(calculatedProductsList50);
		allProductsListRecalculated.addAll(calculatedProductsList25);
		allProductsListRecalculated.addAll(calculatedProductsListMarketing);

		PrintUtils.printListBasicProductModel(allProductsListRecalculated);

		calculatedTotalsDiscounts = CartTotalsCalculation.calculateCartProductsTotalsWithDiscountRuleActive(allProductsListRecalculated, ruleDiscount, jewelryDiscount,
				marketingDiscount, taxClass, shippingValue);
		PrintUtils.printCalcDetailsModel(calculatedTotalsDiscounts);
		shippingCalculatedModel = CartCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculateKoboSubscriptionTotals(String jewelryDiscount, String marketingDiscount, String taxClass, String shippingValue) {

		calculatedTotalsDiscounts = CartTotalsCalculation.calculateCartProductsTotals(allProductsList, jewelryDiscount, marketingDiscount, taxClass, shippingValue, shippingValue);
		PrintUtils.printCalcDetailsModel(calculatedTotalsDiscounts);
		shippingCalculatedModel = CartCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
		PrintUtils.printShippingTotals(shippingCalculatedModel);
	}

	public static void calculateJMDiscountsForBuy3Get1Rule(String jewelryDiscount, String marketingDiscount, String taxClass, String shippingValue,
			String shippingValueForLessThan150) {
		allProductsList.clear();
		allProductsList.addAll(productsList25);
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productsListMarketing);

		calculatedProductsList25 = CartDiscountsCalculation.calculateProductsfor25Discount(productsList25, jewelryDiscount);

		calculatedProductsList50 = CartDiscountsCalculation.calculateProductsfor50Discount(productsList50, productsList25, jewelryDiscount);

		calculatedProductsListMarketing = CartDiscountsCalculation.calculateProductsforMarketingMaterial(productsListMarketing, marketingDiscount);

		allProductsListRecalculated.clear();
		allProductsListRecalculated.addAll(calculatedProductsList50);
		allProductsListRecalculated.addAll(calculatedProductsList25);
		allProductsListRecalculated.addAll(calculatedProductsListMarketing);

		calculatedTotalsDiscounts = CartTotalsCalculation.calculateCartProductsTotals(allProductsListRecalculated, jewelryDiscount, marketingDiscount, taxClass, shippingValue,
				shippingValueForLessThan150);
		shippingValue = Double.parseDouble(calculatedTotalsDiscounts.getTotalAmount()) >= 150 ? shippingValue : shippingValueForLessThan150;
		shippingCalculatedModel = CartCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}

	public static void calculate3P1Rule(String jewelryDiscount, String marketingDiscount, String taxClass, String shippingValue, String shippingValueForLessThan150) {
		// 3+1 rule
		productListForBuy3Get1.addAll(productsList25);
		productListForBuy3Get1.addAll(productsListMarketing);
		productList25AndMmWithBuy3Get1Applied = CartBuy3Get1Calculation.applyBuy3Get1OnTheCart(productListForBuy3Get1);

		allProductsList.addAll(productsList50);
		allProductsList.addAll(productList25AndMmWithBuy3Get1Applied);

		calculatedProductsList25 = CartDiscountsCalculation.calculateProductsfor25Discount(productsList25, jewelryDiscount);

		calculatedProductsList50 = CartDiscountsCalculation.calculateProductsfor50Discount(productsList50, productsList25, jewelryDiscount);

		calculatedProductsListMarketing = CartDiscountsCalculation.calculateProductsforMarketingMaterial(productsListMarketing, marketingDiscount);

		allProductsListRecalculated.addAll(calculatedProductsList50);
		allProductsListRecalculated.addAll(calculatedProductsList25);
		allProductsListRecalculated.addAll(calculatedProductsListMarketing);

		calculatedTotalsDiscounts = CartTotalsCalculation.calculateCartProductsTotalsBuy3GetOneRuleApplied(allProductsListRecalculated, jewelryDiscount, marketingDiscount,
				taxClass, shippingValue, shippingValueForLessThan150);
		shippingValue = Double.parseDouble(calculatedTotalsDiscounts.getTotalAmount()) >= 150 ? shippingValue : shippingValueForLessThan150;
		shippingCalculatedModel = CartCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}
}

package com.steps.frontend.checkout;

import java.text.DecimalFormat;
import java.util.List;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;

public class CheckoutValidationSteps extends AbstractSteps {

	private DecimalFormat df = new DecimalFormat("0.00");

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void verifySuccessMessage() {
		successPage().verifySuccessMessage();
	}

	public double calculateCartProductsByDiscount(CartProductModel product) {

		double productPrice = 0;

		if (product.getDiscountClass().contentEquals("25")) {
			productPrice += (PrintUtils.cleanNumberToDouble(product.getUnitPrice()) * PrintUtils.cleanNumberToDouble(product.getQuantity())) * 25 / 100;
		}
		if (product.getDiscountClass().contentEquals("50")) {
			productPrice += (PrintUtils.cleanNumberToDouble(product.getUnitPrice()) * PrintUtils.cleanNumberToDouble(product.getQuantity())) * 50 / 100;
		}
		return productPrice;
	}

	@Step
	public CartTotalsModel calculateCartProducts(List<CartProductModel> productList) {

		double totalPrice = 0;
		double discountSum = 0;
		double totalAmount = 0;
		int ipPointsSum = 0;
		double taxSum = 0;
		int jeverlyBonus = 0;
		double shiping = 0;

		for (CartProductModel cartProductModel : productList) {
			double productPrice = 0;
			productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
			totalPrice += productPrice;
			double discount = 0;
			if (cartProductModel.getDiscountClass() == "25" || cartProductModel.getDiscountClass() == "50") {
				discount = calculateCartProductsByDiscount(cartProductModel);
				discountSum += calculateCartProductsByDiscount(cartProductModel);

			}
			totalAmount += (PrintUtils.cleanNumberToDouble(cartProductModel.getProductsPrice()) - discount);
			ipPointsSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());

		}
		taxSum = PrintUtils.getDoubleWithTwoDigits(((totalAmount * 19) / 119));

		CartTotalsModel result = new CartTotalsModel();

		result.setSubtotal(df.format((totalPrice)));
		result.setDiscount(df.format(discountSum));
		result.setTotalAmount(df.format(totalAmount));
		result.setIpPoints(String.valueOf((ipPointsSum)));
		result.setTax(df.format(taxSum));
		result.setShipping(df.format(shiping));
		result.setJewelryBonus(String.valueOf((jeverlyBonus)));

		System.out.println("------------------------------");
		System.out.println("Subtotal: " + result.getSubtotal());
		System.out.println("Discount: " + result.getDiscount());
		System.out.println("TotalAmount: " + result.getTotalAmount());
		System.out.println("IpPoints " + result.getIpPoints());
		System.out.println("Tax: " + result.getTax());
		System.out.println("JewelryBonus: " + result.getJewelryBonus());
		System.out.println("Shipping: " + result.getShipping());

		return result;
	}

	

	@Step
	public void checkTotalsInCart(CartTotalsModel cartTotalsModel1, CartTotalsModel cartTotalsModel2) {

		System.out.println("----------SUBTOTAL: " + cartTotalsModel2.getSubtotal() + " : " + cartTotalsModel1.getSubtotal());
		Assert.assertTrue("The subtotal should be " + cartTotalsModel2.getSubtotal() + " and it is " + cartTotalsModel1.getSubtotal() + "!",
				cartTotalsModel2.getSubtotal().equals(cartTotalsModel1.getSubtotal()));

		System.out.println("----------DISCOUNT: " + cartTotalsModel2.getDiscount() + " : " + cartTotalsModel1.getDiscount());
		Assert.assertTrue("The discount should be " + cartTotalsModel2.getDiscount() + " and it is " + cartTotalsModel1.getDiscount() + "!",
				cartTotalsModel2.getDiscount().equals(cartTotalsModel1.getDiscount()));

		System.out.println("----------TOTAL AMOUNT: " + cartTotalsModel2.getTotalAmount() + " : " + cartTotalsModel1.getTotalAmount());
		Assert.assertTrue("The total amount should be " + cartTotalsModel2.getTotalAmount() + " and it is " + cartTotalsModel1.getTotalAmount() + "!",
				cartTotalsModel2.getTotalAmount().equals(cartTotalsModel1.getTotalAmount()));

		System.out.println("----------TAX: " + cartTotalsModel2.getTax() + " : " + cartTotalsModel1.getTax());
		Assert.assertTrue("The tax should be " + cartTotalsModel2.getTax() + " and it is " + cartTotalsModel1.getTax() + "!", cartTotalsModel2.getTax().equals(cartTotalsModel1.getTax()));

		System.out.println("----------SHIPPING: " + cartTotalsModel2.getShipping() + " : " + cartTotalsModel1.getShipping());
		Assert.assertTrue("The shipping  should be " + cartTotalsModel2.getShipping() + " and it is " + cartTotalsModel1.getShipping() + "!",
				cartTotalsModel2.getShipping().equals(cartTotalsModel1.getShipping()));

		System.out.println("----------JEWERLY BONUS: " + cartTotalsModel2.getJewelryBonus() + " : " + cartTotalsModel1.getJewelryBonus());
		Assert.assertTrue("The jewerly bouns should be " + cartTotalsModel2.getJewelryBonus() + " and it is " + cartTotalsModel1.getJewelryBonus() + "!",
				cartTotalsModel2.getJewelryBonus().equals(cartTotalsModel1.getJewelryBonus()));

		System.out.println("----------IP POINTS: " + cartTotalsModel2.getIpPoints() + " : " + cartTotalsModel1.getIpPoints());
		Assert.assertTrue("The ip points should be " + cartTotalsModel2.getIpPoints() + " and it is " + cartTotalsModel1.getIpPoints() + "!",
				cartTotalsModel2.getIpPoints().equals(cartTotalsModel1.getIpPoints()));

	}

	@Step
	public void checkTotals(CalculationModel calculationModel, CartTotalsModel cartTotalModel) {

		System.out.println("----------SUBTOTAL: " + cartTotalModel.getSubtotal() + " : " + calculationModel.formatDouble(calculationModel.getAskingPrice()));
		Assert.assertTrue("The subtotal should be " + cartTotalModel.getSubtotal() + " and it is " + calculationModel.formatDouble(calculationModel.getAskingPrice()) + "!", cartTotalModel
				.getSubtotal().equals(calculationModel.formatDouble(calculationModel.getAskingPrice())));

		System.out.println("----------FINAL: " + cartTotalModel.getTotalAmount() + " : " + calculationModel.formatDouble(calculationModel.getFinalPrice()));
		Assert.assertTrue("The discount should be " + cartTotalModel.getTotalAmount() + " and it is " + calculationModel.formatDouble(calculationModel.getFinalPrice()) + "!", cartTotalModel
				.getTotalAmount().equals(calculationModel.formatDouble(calculationModel.getFinalPrice())));

		System.out.println("----------IP POINTS: " + cartTotalModel.getIpPoints() + " : " + calculationModel.getIpPoints());
		Assert.assertTrue("The total ip points should be " + cartTotalModel.getIpPoints() + " and it is " + calculationModel.getIpPoints() + "!",
				cartTotalModel.getIpPoints().equals(String.valueOf(calculationModel.getIpPoints())));

	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void validateProducts(List<ProductBasicModel> productsList, List<CartProductModel> cartProducts) {

		for (ProductBasicModel productNow : productsList) {
			CartProductModel compare = findProduct(productNow.getType(), cartProducts);

			PrintUtils.printProductsCompare(productNow, compare);
			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
				validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchPrice(String productNow, String compare) {
		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchQuantity(String productNow, String compare) {
		Assert.assertTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	private static CartProductModel findProduct(String productCode, List<CartProductModel> cartProducts) {
		CartProductModel result = new CartProductModel();

		theFor: for (CartProductModel cartProductModel : cartProducts) {
			System.out.println(productCode + " - " + cartProductModel.getProdCode());
			if (cartProductModel.getProdCode().contains(productCode)) {
				result = cartProductModel;
				System.out.println("Got One!!!!!");
				break theFor;
			}
		}

		return result;
	}

}

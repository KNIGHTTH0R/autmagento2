package com.steps.frontend.checkout;

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

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void verifySuccessMessage() {
		successPage().verifySuccessMessage();
	}

	// @Step
	// public void checkTotalsInCart(CartTotalsModel cartTotalsModel1,
	// CartTotalsModel cartTotalsModel2) {
	// printTotalsModel("INITIAL", cartTotalsModel1.getSubtotal(),
	// cartTotalsModel1.getDiscount(), cartTotalsModel1.getTotalAmount(),
	// cartTotalsModel1.getTax(), cartTotalsModel1.getShipping(),
	// cartTotalsModel1.getJewelryBonus(), cartTotalsModel1.getIpPoints());
	// printTotalsModel("FINAL", cartTotalsModel2.getSubtotal(),
	// cartTotalsModel2.getDiscount(), cartTotalsModel2.getTotalAmount(),
	// cartTotalsModel2.getTax(), cartTotalsModel2.getShipping(),
	// cartTotalsModel2.getJewelryBonus(), cartTotalsModel2.getIpPoints());
	//
	//
	// Assert.assertTrue("The subtotal should be " +
	// cartTotalsModel2.getSubtotal() + " and it is " +
	// cartTotalsModel1.getSubtotal() + "!",
	// cartTotalsModel2.getSubtotal().equals(cartTotalsModel1.getSubtotal()));
	//
	// Assert.assertTrue("The discount should be " +
	// cartTotalsModel2.getDiscount() + " and it is " +
	// cartTotalsModel1.getDiscount() + "!",
	// cartTotalsModel2.getDiscount().equals(cartTotalsModel1.getDiscount()));
	//
	// Assert.assertTrue("The total amount should be " +
	// cartTotalsModel2.getTotalAmount() + " and it is " +
	// cartTotalsModel1.getTotalAmount() + "!",
	// cartTotalsModel2.getTotalAmount().equals(cartTotalsModel1.getTotalAmount()));
	//
	// Assert.assertTrue("The tax should be " + cartTotalsModel2.getTax() +
	// " and it is " + cartTotalsModel1.getTax() + "!",
	// cartTotalsModel2.getTax().equals(cartTotalsModel1.getTax()));
	//
	// Assert.assertTrue("The shipping  should be " +
	// cartTotalsModel2.getShipping() + " and it is " +
	// cartTotalsModel1.getShipping() + "!",
	// cartTotalsModel2.getShipping().equals(cartTotalsModel1.getShipping()));
	//
	// Assert.assertTrue("The jewerly bouns should be " +
	// cartTotalsModel2.getJewelryBonus() + " and it is " +
	// cartTotalsModel1.getJewelryBonus() + "!",
	// cartTotalsModel2.getJewelryBonus().equals(cartTotalsModel1.getJewelryBonus()));
	//
	// Assert.assertTrue("The ip points should be " +
	// cartTotalsModel2.getIpPoints() + " and it is " +
	// cartTotalsModel1.getIpPoints() + "!",
	// cartTotalsModel2.getIpPoints().equals(cartTotalsModel1.getIpPoints()));
	// }

	@StepGroup
	public void checkCalculationTotals(String message, CalculationModel calculationModel, CartTotalsModel cartTotalModel) {
		
		printCalculationModel("Calculated Values", String.valueOf(calculationModel.getAskingPrice()), String.valueOf(calculationModel.getFinalPrice()), String.valueOf(calculationModel.getIpPoints()));
		printTotalsModel("Cart Totals", cartTotalModel.getSubtotal(), cartTotalModel.getDiscount(), cartTotalModel.getTotalAmount(), cartTotalModel.getTax(), cartTotalModel.getShipping(),
				cartTotalModel.getJewelryBonus(), cartTotalModel.getIpPoints());
		
		Assert.assertTrue("The subtotal should be " + cartTotalModel.getSubtotal() + " and it is " + calculationModel.formatDouble(calculationModel.getAskingPrice()) + "!", cartTotalModel
				.getSubtotal().equals(calculationModel.formatDouble(calculationModel.getAskingPrice())));

		Assert.assertTrue("The discount should be " + cartTotalModel.getTotalAmount() + " and it is " + calculationModel.formatDouble(calculationModel.getFinalPrice()) + "!", cartTotalModel
				.getTotalAmount().equals(calculationModel.formatDouble(calculationModel.getFinalPrice())));

		Assert.assertTrue("The total ip points should be " + cartTotalModel.getIpPoints() + " and it is " + calculationModel.getIpPoints() + "!",
				cartTotalModel.getIpPoints().equals(String.valueOf(calculationModel.getIpPoints())));
	}

	@Step
	public void printTotalsModel(String message, String subtotal, String discount, String totalAmount, String tax, String shipping, String jewelryBonus, String ip) {
		System.out.println(" -- Print Totals - " + message);
		System.out.println("SUBTOTAL: " + subtotal);
		System.out.println("DISCOUNT: " + discount);
		System.out.println("TOTAL AMOUNT: " + totalAmount);
		System.out.println("TAX: " + tax);
		System.out.println("SHIPPING: " + shipping);
		System.out.println("JEWERLY BONUS: " + jewelryBonus);
		System.out.println("IP POINTS: " + ip);
		getDriver().getCurrentUrl();
	}

	@Step
	public void printCalculationModel(String message, String subtotal, String totalAmount, String ip) {
		System.out.println("-- Calculation Model - " + message);
		System.out.println("SUBTOTAL: " + subtotal);
		System.out.println("FINAL: " + totalAmount);
		System.out.println("IP POINTS: " + ip);
		getDriver().getCurrentUrl();
	}

	@StepGroup
	public void validateProducts(String message, List<ProductBasicModel> productsList, List<CartProductModel> cartProducts) {

		for (ProductBasicModel productNow : productsList) {
			CartProductModel compare = findProduct(productNow.getType(), cartProducts);

			PrintUtils.printProductsCompare(productNow, compare);
			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				matchName(productNow.getName(), compare.getName());
				validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
				validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
	}

	@Step
	public void validateMatchPrice(String productNow, String compare) {
		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, compare.contentEquals(productNow));
	}
	
	@Step
	public void matchName(String productNow, String compare) {
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		Assert.assertTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	// TODO might need to move this
	private static CartProductModel findProduct(String productCode, List<CartProductModel> cartProducts) {
		CartProductModel result = new CartProductModel();
		theFor: for (CartProductModel cartProductModel : cartProducts) {
			System.out.println(productCode + " - " + cartProductModel.getProdCode());
			if (cartProductModel.getProdCode().contains(productCode)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}

}

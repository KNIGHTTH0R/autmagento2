package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class CheckoutValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void verifySuccessMessage() {
		successPage().verifySuccessMessage();
	}

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
	public CartProductModel findProduct(String productCode, List<CartProductModel> cartProducts) {
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

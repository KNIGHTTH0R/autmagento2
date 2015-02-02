package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.data.CalcDetailsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.requirements.AbstractSteps;

public class CheckoutValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	/**
	 * Validate that the message from the succcess screen, on order process is
	 * displayd.
	 */
	@Step
	public void verifySuccessMessage() {
		successPage().verifySuccessMessage();
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
	}

	@Step
	public void printCalculationModel(String message, String subtotal, String totalAmount, String ip) {
		System.out.println("-- Calculation Model - " + message);
		System.out.println("SUBTOTAL: " + subtotal);
		System.out.println("FINAL: " + totalAmount);
		System.out.println("IP POINTS: " + ip);
	}

	@Step
	public void validateMatchPrice(String productNow, String compare) {
		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, compare.contentEquals(productNow));
	}

	@Step
	public void matchName(String productNow, String compare) {
		// Name is validated on element match - Only for print purposes hence
		// match not validate
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		Assert.assertTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	public void checkTotalAmountFromUrl(String url, String totalAmount) {
		Assert.assertTrue("Failure: The total amount from URL is incorrect", url.contains(totalAmount));

	}

	@Step
	public void verifyTotalsDiscount(CartTotalsModel discountTotals, CalcDetailsModel discountCalculationModel) {
		Assert.assertTrue("Failure: IP points dont match Expected" + discountCalculationModel.getIpPoints() + " Actual: " + discountTotals.getIpPoints(),
				discountTotals.getIpPoints().contains(discountCalculationModel.getIpPoints()));
		Assert.assertTrue("Failure: Sub Totals dont match Expected" + discountCalculationModel.getSubTotal() + " Actual: " + discountTotals.getSubtotal(),
				discountTotals.getSubtotal().contains(discountCalculationModel.getSubTotal()));
		Assert.assertTrue("Failure: Total Amount dont match Expected" + discountCalculationModel.getTotalAmount() + " Actual: " + discountTotals.getTotalAmount(), discountTotals.getTotalAmount()
				.contains(discountCalculationModel.getTotalAmount()));
		Assert.assertTrue("Failure: TAX dont match Expected" + discountCalculationModel.getTax() + " Actual: " + discountTotals.getTax(),
				discountTotals.getTax().contains(discountCalculationModel.getTax()));
		Assert.assertTrue("Failure: Jewelry Bonus dont match Expected" + discountCalculationModel.getJewelryBonus() + " Actual: " + discountTotals.getJewelryBonus(), discountTotals.getJewelryBonus()
				.contains(discountCalculationModel.getJewelryBonus()));
		Assert.assertTrue("Failure: Marketing Bonus dont match Expected" + discountCalculationModel.getMarketingBonus() + " Actual: " + discountTotals.getMarketingBonus(), discountTotals
				.getMarketingBonus().contains(discountCalculationModel.getMarketingBonus()));
		Assert.assertTrue("Failure: IP points dont match Expected" + discountCalculationModel.getIpPoints() + " Actual: " + discountTotals.getIpPoints(),
				discountTotals.getIpPoints().contains(discountCalculationModel.getIpPoints()));

	}

}

package com.steps.frontend.checkout.cart.styleCoachCart;

import java.util.List;

import org.junit.Assert;

import com.tools.constants.TimeConstants;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class CartSteps extends AbstractSteps {

	private static final long serialVersionUID = 4077671481867589798L;

	/**
	 * Grab all cart product sections from the Cart Page in a single list.
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsData() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return cartPage().grabProductsData();
	}

	/**
	 * Grab 25% Discount section from the Cart Page
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith25PercentDiscount() {

		return cartPage().grabProductsDataWith25Discount();
	}

	/**
	 * Grab 50% Discount section from the Cart Page
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith50PercentDiscount() {
		return cartPage().grabProductsDataWith50Discount();
	}

	/**
	 * Grab Marketing Material section from the Cart Page
	 * 
	 * @return
	 */
	public List<CartProductModel> grabMarketingMaterialProductsData() {
		return cartPage().grabMarketingMaterialProductsData();
	}

	/**
	 * Grab Cart Totals section from the Cart Page Note sets ->
	 * DataGrabber.cartTotals;
	 * 
	 * @return
	 */
	public CartTotalsModel grabTotals() {
		return cartPage().grabTotals();
	}

	@Step
	public void goToShipping() {
		cartPage().clickToShipping();
		abstractPage().waitForPageToLoad();
	}

	@Step
	public void updateCart() {
		cartPage().clickUpdateCart();
		getDriver().navigate().refresh();
	}

	@Step
	public void updateJewerlyBonus() {
		cartPage().clickUpdateJewerlyBonus();
	}

	@Step
	public void updateMarketingBonus() {
		cartPage().clickUpdateMarketingBonus();
	}

	@Step
	public void updateProductQuantityIn50DiscountArea(String quantity, String... terms) {
		cartPage().updateProductQuantityIn50DiscountArea(quantity, terms);
	}

	@Step
	public void typeJewerlyBonus(String jevewrlyBonus) {
		cartPage().typeJewerlyBonus(jevewrlyBonus);
	}
	
	@Step
	public void typeJewerlyBonusAndEnter(String jevewrlyBonus) {
		cartPage().typeJewerlyBonusAndEnter(jevewrlyBonus);
	}

	@Step
	public void typeMarketingBonus(String marketingBonus) {
		cartPage().typeMarketingBonus(marketingBonus);
	}

	@Step
	public void wipeCart(String URL) {
		URL = URL.replace("stylist/lounge/", "checkout/cart/clearAllItems/");
		getDriver().get(URL);
		cartPage().verifyWipeCart();
	}

	@Step
	public void verifyStockMessageForProduct(String productName, String stockInfo) {
		cartPage().verifyStockMessageForProduct(productName, stockInfo);
	}

	@Step
	public void verifyErrorMessageInCart(String message) {
		Assert.assertTrue("The error message is not found !!!", cartPage().getCartErrorMessage().contains(message));
	}
	
	@Step
	public void verifyPresenceOfGoToCheckoutButton(boolean shouldBePresent){
		cartPage().verifyPresenceOfGoToCheckoutButton(shouldBePresent);
	}

	@Step
	public void verifyJBErrorMessage(boolean shouldBePresent, String message) {
		cartPage().verifyJBErrorMessage(shouldBePresent, message);
	}
}

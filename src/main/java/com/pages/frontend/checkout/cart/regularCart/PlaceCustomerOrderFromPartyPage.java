package com.pages.frontend.checkout.cart.regularCart;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class PlaceCustomerOrderFromPartyPage extends AbstractPage {

	@FindBy(id = "contact")
	private WebElement contactInput;

	@FindBy(id = "create-order")
	private WebElement createOrder;

	@FindBy(css = "ul li.ui-menu-item a")
	private WebElement selectContact;

	@FindBy(css = "li.error-msg ul li span")
	private WebElement errorMessageContainer;

	public void typeContactName(String name) {
		element(contactInput).waitUntilVisible();
		contactInput.sendKeys(name);
		element(selectContact).waitUntilVisible();
		element(selectContact).click();
	}

	public void startOrderForCustomer() {
		element(createOrder).waitUntilVisible();
		createOrder.click();
	}

	public void verifyCustomerIsNotSuitableForTheOrderErrorMessage() {
		Assert.assertTrue("The error message should be present !!!", errorMessageContainer.getText().contains(ContextConstants.ORDER_FOR_WRONG_CUSTOMER_ERROR_MESSAGE));
	}
}

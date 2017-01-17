package com.pages.frontend.checkout.cart.regularCart;

import net.serenitybdd.core.annotations.findby.FindBy;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
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
		waitABit(TimeConstants.TIME_CONSTANT);
		element(selectContact).waitUntilVisible();
		if (selectContact.getText().contentEquals(name)) {
			element(selectContact).click();
			waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
					ContextConstants.LOADING_MESSAGE));
			waitABit(TimeConstants.WAIT_TIME_SMALL);
		} else {
			Assert.fail("The contact was not found");
		}

	}

	public void startOrderForCustomer() {
		element(createOrder).waitUntilVisible();
		createOrder.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void verifyCustomerIsNotSuitableForTheOrderErrorMessage() {
		Assert.assertTrue("The error message should be present !!!",
				errorMessageContainer.getText().contains(ContextConstants.ORDER_FOR_WRONG_CUSTOMER_ERROR_MESSAGE));
	}
}

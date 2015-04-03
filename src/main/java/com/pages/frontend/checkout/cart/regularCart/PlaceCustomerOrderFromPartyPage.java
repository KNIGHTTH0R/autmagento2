package com.pages.frontend.checkout.cart.regularCart;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class PlaceCustomerOrderFromPartyPage extends AbstractPage {

	@FindBy(id = "contact")
	private WebElement contactInput;

	@FindBy(id = "create-order")
	private WebElement createOrder;
	
	@FindBy(css = "ul li.ui-menu-item a")
	private WebElement selectContact;

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
}

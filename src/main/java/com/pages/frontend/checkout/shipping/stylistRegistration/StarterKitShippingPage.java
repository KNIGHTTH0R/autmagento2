package com.pages.frontend.checkout.shipping.stylistRegistration;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class StarterKitShippingPage extends AbstractPage {

	@FindBy(css = "input[name='shipping[house_number]']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[name='shipping[postcode]']")
	private WebElement postcodeInput;

	@FindBy(css = "input[name='shipping[city]']")
	private WebElement cityInput;

	@FindBy(css = "input#same_as_billing")
	private WebElement sameAsBilling;

	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;

	public void clickGoToPaymentMethod() {
		element(toPaymentButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(toPaymentButton));
		toPaymentButton.click();
	}

	public void inputStreetNumber(String streetNumber) {
		element(houseNumberInput).waitUntilVisible();
		houseNumberInput.clear();
		houseNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		element(postcodeInput).waitUntilVisible();
		postcodeInput.clear();
		postcodeInput.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		element(cityInput).waitUntilVisible();
		cityInput.clear();
		cityInput.sendKeys(homeTown);
	}

	public void setSameAsBilling(boolean checked) {

		boolean isSelected = sameAsBilling.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			sameAsBilling.click();

		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

}

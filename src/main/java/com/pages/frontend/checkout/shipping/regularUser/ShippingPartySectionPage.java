package com.pages.frontend.checkout.shipping.regularUser;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class ShippingPartySectionPage extends AbstractPage {

	@FindBy(id = "style_party_no")
	private WebElement noPartyOption;

	@FindBy(id = "style_party_yes")
	private WebElement yesPartyOption;

	@FindBy(css = "div#preshipped-prod input[value*='0']")
	private WebElement itemNotReceived;

	@FindBy(css = "select[id='billing:country_id']")
	private WebElement countrySelect;
	
	@FindBy(css = "input[name='billing[postcode]']")
	private WebElement plzInput;

	@FindBy(id = "to_hostess")
	private WebElement shipToHostessButton;

	@FindBy(id = "to_stylecoach")
	private WebElement shipToStylecoachButton;

	@FindBy(id = "shipping-hostess-address-select")
	private WebElement shipHostessAddressSelect;

	@FindBy(id = "shipping-stylecoach-address-select")
	private WebElement shipStylecoachAddressSelect;

	public void checkItemNotReceivedYet() {
		element(itemNotReceived).waitUntilVisible();
		itemNotReceived.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickPartyYesOption() {
		element(yesPartyOption).waitUntilVisible();
		yesPartyOption.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickPartyNoOption() {
		element(noPartyOption).waitUntilVisible();
		noPartyOption.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickShipToHostessButton() {
		element(shipToHostessButton).waitUntilVisible();
		shipToHostessButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickShipToStylecoach() {
		element(shipToStylecoachButton).waitUntilVisible();
		shipToStylecoachButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void selectCountry(String country) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(country);
	}
	
	public void enterPLZ(String plz) {
		element(plzInput).waitUntilVisible();
		element(plzInput).clear();
		element(plzInput).sendKeys(plz);;
	}

	public void selectShipToHostessAddress(String address) {
		element(shipHostessAddressSelect).waitUntilVisible();
		element(shipHostessAddressSelect).selectByVisibleText(address);
	}

	public void selectShipToStylecoachAddress(String address) {
		element(shipStylecoachAddressSelect).waitUntilVisible();
		element(shipStylecoachAddressSelect).selectByVisibleText(address);
	}

}

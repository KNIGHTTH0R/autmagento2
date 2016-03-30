package com.pages.frontend.checkout.shipping.regularUser;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

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
	}

	public void clickPartyYesOption() {
		element(yesPartyOption).waitUntilVisible();
		yesPartyOption.click();
	}

	public void clickPartyNoOption() {
		element(noPartyOption).waitUntilVisible();
		noPartyOption.click();
	}

	public void clickShipToHostessButton() {
		element(shipToHostessButton).waitUntilVisible();
		shipToHostessButton.click();
	}

	public void clickShipToStylecoach() {
		element(shipToStylecoachButton).waitUntilVisible();
		shipToStylecoachButton.click();
	}

	public void selectCountry(String country) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(country);
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

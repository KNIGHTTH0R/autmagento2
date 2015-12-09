package com.pages.frontend.checkout.shipping.regularUser;

import net.thucydides.core.annotations.findby.FindBy;

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

	public void selectCountry(String country) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(country);
	}

}

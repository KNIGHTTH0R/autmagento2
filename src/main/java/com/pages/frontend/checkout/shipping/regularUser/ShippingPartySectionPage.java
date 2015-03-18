package com.pages.frontend.checkout.shipping.regularUser;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ShippingPartySectionPage extends AbstractPage {

	@FindBy(id = "style_party_no")
	private WebElement noPartyOption;

	@FindBy(id = "style_party_yes")
	private WebElement yesPartyOption;

	public void clickPartyYesOption() {
		element(yesPartyOption).waitUntilVisible();
		yesPartyOption.click();
	}

	public void clickPartyNoOption() {
		element(noPartyOption).waitUntilVisible();
		noPartyOption.click();
	}

}

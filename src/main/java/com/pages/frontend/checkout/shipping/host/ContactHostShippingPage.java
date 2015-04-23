package com.pages.frontend.checkout.shipping.host;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class ContactHostShippingPage extends AbstractPage {

	@FindBy(css = "div#preshipped-prod input[value*='0']")
	private WebElement itemNotReceived;

	@FindBy(css = "div#co-stylist-form")
	private WebElement detailsContainer;

	@FindBy(css = "select[id*='billing:country_id']")
	private WebElement countryDdl;

	public void verifyThatRestrictedCountriesAreNotAvailable() {
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE)
				|| !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
		System.out.println(countryDdl.getText());
	}

	public void checkItemNotReceivedYet() {
		element(itemNotReceived).waitUntilVisible();
		itemNotReceived.click();
	}

	public void verifyStyleCoachAndOrderForDetails(String details) {
		element(detailsContainer).waitUntilVisible();
		Assert.assertTrue("style coach and order for don't match", detailsContainer.getText().contains(details));
	}

}

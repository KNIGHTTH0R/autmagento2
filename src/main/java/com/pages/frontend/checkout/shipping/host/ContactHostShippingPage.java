package com.pages.frontend.checkout.shipping.host;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class ContactHostShippingPage extends AbstractPage {

	@FindBy(css = "div#preshipped-prod input[value*='0']")
	private WebElement itemNotReceived;

	@FindBy(css = "div#co-stylist-form")
	private WebElement detailsContainer;

	@FindBy(css = "select[id*='billing:country_id']")
	private WebElement countryDdl;
	
	@FindBy(css = "input[name='billing[postcode]']")
	private WebElement plzInput;

	public void verifyThatRestrictedCountriesAreNotAvailable() {
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE)
				|| !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
	}

	public void checkItemNotReceivedYet() {
		element(itemNotReceived).waitUntilVisible();
		itemNotReceived.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void verifyStyleCoachAndOrderForDetails(String details) {
		element(detailsContainer).waitUntilVisible();
		Assert.assertTrue("style coach and order for don't match", detailsContainer.getText().contains(details));
	}
	
	public void selectCountry(String country) {
		element(countryDdl).waitUntilVisible();
		element(countryDdl).selectByVisibleText(country);
	}
	
	public void enterPLZ(String plz) {
		element(plzInput).waitUntilVisible();
		element(plzInput).clear();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		element(plzInput).sendKeys(plz);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

}

package com.pages.frontend;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class KoboSuccesFormPage extends AbstractPage {

	@FindBy(css = "div.pom-success")
	private WebElement textContainer;

	public void verifyKoboFormIsSuccsesfullyFilledIn() {
		element(textContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textContainer.getText().contains(ContextConstants.SUCCES_KOBO_FORM));
		waitABit(30000);
	}

	public void verifyThatTheWebsiteHasChanged() {
		//here EnvironmentConstants.SOAP_URL was uses instead of baseURL because the url does not contain any website
		Assert.assertTrue("The url does not reflect the website change !!", getDriver().getCurrentUrl().contains(EnvironmentConstants.SOAP_URL + ContextConstants.NOT_PREFERED_WEBSITE));
	}
}

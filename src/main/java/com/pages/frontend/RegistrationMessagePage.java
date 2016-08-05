package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class RegistrationMessagePage extends AbstractPage {

//	@FindBy(css = "div.col-main")
//	private WebElement textContainer;
	
	@FindBy(css = "section.col-main")
	private WebElement textContainer;

	public void verifyLink() {
		Assert.assertTrue("Failure: URL not redirected to success page. ",getDriver().getCurrentUrl().contains("registersuccess"));
	}

	public void verifyText() {
		waitForPageToLoad();
		element(textContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textContainer.getText().contains(ContextConstants.CREATE_ACCOUNT_SUCCESS_MESSAGE));
	}
}

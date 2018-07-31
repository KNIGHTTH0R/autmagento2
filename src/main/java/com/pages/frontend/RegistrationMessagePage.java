package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class RegistrationMessagePage extends AbstractPage {

	@FindBy(css = "#maincontent > div.page.messages > div:nth-child(2) > div > div > div")
	private WebElement registrationMessage;

	public void verifyLink() {
		CustomVerification.verifyTrue("Failure: URL not redirected to success page. ",
				getDriver().getCurrentUrl().contains("customer/account"));
	}

	public void verifyText() {
		element(registrationMessage).waitUntilVisible();
		Assert.assertTrue("Failure: Message success notification text was not found. ",
				registrationMessage.getText().contains(ContextConstants.CREATE_ACCOUNT_SUCCESS_MESSAGE));
	}
}

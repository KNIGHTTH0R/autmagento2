package com.pages.frontend;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.env.stagingaut.UrlConstants;
import com.tools.requirements.AbstractPage;

public class RegistrationMessagePage extends AbstractPage {

	@FindBy(css = "div.col-main")
	private WebElement textContainer;

	public void verifyLink() {
		Assert.assertTrue("Failure: URL not redirected to success page. ",getDriver().getCurrentUrl().contains("registersuccess"));
	}

	public void verifyText() {
		element(textContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textContainer.getText().contains(UrlConstants.SUCCESFULL_REGISTRATION));
	}
}

package com.pages.frontend;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class RegistrationMessagePage extends AbstractPage {

	@FindBy(css = "div.col-main")
	private WebElement textContainer;

	public void verifyLink() {
		Assert.assertTrue(getDriver().getCurrentUrl().contains("registersuccess"));
	}

	public void verifyText() {
		element(textContainer).waitUntilVisible();
		Assert.assertTrue(textContainer.getText().contains("Email geschickt"));
	}
}

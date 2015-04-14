package com.pages.frontend.checkout;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.stagingaut.UrlConstants;
import com.tools.requirements.AbstractPage;

@DefaultUrl(UrlConstants.URL_CART_SUCCESS)
public class SuccessPage extends AbstractPage {

	@FindBy(className = "success-step-msg")
	private WebElement messageContainer;

	public void verifySuccessMessage() {
		element(messageContainer).waitUntilVisible();
		String pageText = messageContainer.getText();
		Assert.assertTrue("Failure: Success message has not been found.", pageText.contains(UrlConstants.SUCCES_MESSAGE));
	}

}

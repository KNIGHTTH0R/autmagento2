package com.pages.frontend.checkout;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

//@DefaultUrl(UrlConstants.URL_CART_SUCCESS)
public class SuccessPage extends AbstractPage {

	@FindBy(className = "success-step-msg")
	private WebElement messageContainer;

	public void verifySuccessMessage() {
		waitABit(TimeConstants.TIME_CONSTANT);
		element(messageContainer).waitUntilVisible();
		String pageText = messageContainer.getText();
		Assert.assertTrue("Failure: Success message has not been found.", pageText.contains(ContextConstants.SUCCES_MESSAGE));
	}

}

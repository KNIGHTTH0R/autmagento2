package com.pages.frontend.checkout;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.Constants;


@DefaultUrl(Constants.URL_CART_SUCCESS)
public class SuccessPage extends AbstractPage{
	
	@FindBy(className = "success-step-msg")
	private WebElement messageContainer;
	
	public void verifySuccessMessage(){
		element(messageContainer).waitUntilVisible();
		String pageText = messageContainer.getText();
		Assert.assertTrue("Failure: Success message has not been found.", pageText.contains("Wir haben dir eine eMail mit mit den Details zur Bestellung geschickt."));
	}

}

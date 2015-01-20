package com.pages.frontend.checkout;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class SuccessPage extends AbstractPage{
	
	@FindBy(className = "success-step-msg")
	private WebElement messageContainer;
	
	
	
	public void verifySuccessMessage(){
		element(messageContainer).waitUntilVisible();
		String pageText = messageContainer.getText();
		
		System.out.println("Success Message: " + pageText);
		
		Assert.assertTrue("Failure: Success message has not been found.", pageText.contains("Wir haben dir eine eMail mit mit den Details zur Bestellung geschickt."));
	}

}

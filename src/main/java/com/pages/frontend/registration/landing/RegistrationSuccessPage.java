package com.pages.frontend.registration.landing;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class RegistrationSuccessPage extends AbstractPage{
	
	@FindBy(name = "page-title")
	private WebElement messageContainer;
	
	public String getSuccessMessageTitle(){
		element(messageContainer).waitUntilVisible();
		return messageContainer.getText();
	}
}

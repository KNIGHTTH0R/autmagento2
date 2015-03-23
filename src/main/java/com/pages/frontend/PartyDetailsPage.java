package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class PartyDetailsPage extends AbstractPage {

	@FindBy(css = "a.button.blue-button.btn-fix")
	private WebElement orderForHostessButton;
	
	public void clickOrderForHostess(){
		element(orderForHostessButton).waitUntilVisible();
		orderForHostessButton.click();
	}

}

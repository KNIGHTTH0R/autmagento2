package com.pages.external.facebook;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class FacebookEMBLoginConfirmPage extends AbstractPage {

	@FindBy(css = "button[type='submit']")
	private WebElement submitButton;

	/**
	 * Confirm if you accept Pippa Jean to access your public profile friends
	 * list etc. Second screen after perform login.
	 */
	public void clickOnSubmmit() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
}

package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class KoboValidationPage extends AbstractPage {

	@FindBy(id = "cb-code")
	private WebElement koboCodeInput;

	@FindBy(id = "submit-form-booster")
	private WebElement submitFormBooster;

	public void enterKoboCode(String code) {
		element(koboCodeInput).waitUntilVisible();
		koboCodeInput.sendKeys(code);
	}

	public void submitFormBooster() {
		element(submitFormBooster).waitUntilVisible();
		submitFormBooster.click();
	}
	

}

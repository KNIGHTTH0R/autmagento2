package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
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

	public void verifyKoboCodeInModal(String code) {
		element(koboCodeInput).waitUntilVisible();
		String koboInputValue=koboCodeInput.getAttribute("value");
		CustomVerification.verifyTrue("Stylist kobo Code is not prefield in modal", koboInputValue.contentEquals(code));
		
	}
	

}

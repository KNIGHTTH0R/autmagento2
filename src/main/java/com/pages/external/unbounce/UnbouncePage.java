package com.pages.external.unbounce;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class UnbouncePage extends AbstractPage {

	@FindBy(id = "vorname")
	private WebElement firstnameInput;

	@FindBy(id = "nachname")
	private WebElement lastnameInput;

	@FindBy(id = "telefon")
	private WebElement telefonInput;

	@FindBy(id = "plz")
	private WebElement plzInput;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "terms_and_conditions_")
	private WebElement termsConditionsInput;

	@FindBy(id = "lp-pom-button-11")
	private WebElement submitButton;

	public void enterFirstname(String name) {
		element(firstnameInput).waitUntilVisible();
		element(firstnameInput).sendKeys(name);
	}

	public void enterLastname(String name) {
		element(lastnameInput).waitUntilVisible();
		element(lastnameInput).sendKeys(name);
	}

	public void enterPhone(String phone) {
		element(telefonInput).waitUntilVisible();
		element(telefonInput).sendKeys(phone);
	}

	public void enterPlz(String plz) {
		element(plzInput).waitUntilVisible();
		element(plzInput).sendKeys(plz);
	}

	public void enterEmail(String email) {
		element(emailInput).waitUntilVisible();
		element(emailInput).sendKeys(email);
	}

	public void acceptTerms() {
//		element(termsConditionsInput).waitUntilVisible();
//		termsConditionsInput.click();
		
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.document.getElementById('terms_and_conditions_').click()");
	}

	public void submitForm() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}

}

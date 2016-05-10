package com.pages.external.unbounce;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

/**
 * @author mihai
 *
 */
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
	private WebElement terms;

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

	/**
	 * js used to work on Android too
	 */
	public void acceptTerms() {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.document.getElementById('terms_and_conditions_').click()");
	}

	/**
	 * js used to work on Android also
	 */
	public void submitForm() {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.document.getElementById('lp-pom-button-11').click()");
	}

}

package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class SepaPaymentPage extends AbstractPage {

	@FindBy(id = "sepadirectdebit.ownerName")
	private WebElement bankNameInput;

	@FindBy(id = "sepadirectdebit.bankAccountNumber")
	private WebElement bankAccountInput;

	@FindBy(id = "sepadirectdebit.bankCountryCode")
	private WebElement countrySelect;

	@FindBy(css = "input[id*='acceptDirectDebit']")
	private WebElement acceptDirectDebitCheckbox;

	@FindBy(css = "input.paySubmit.paySubmitsepadirectdebit")
	private WebElement submitButton;

	public void bankNameInput(String bankName) {
		element(bankNameInput).waitUntilVisible();
		bankNameInput.sendKeys(bankName);
	}

	public void bankAccountNumberInput(String bankAccountNumber) {
		element(bankAccountInput).waitUntilVisible();
		bankAccountInput.sendKeys(bankAccountNumber);
	}

	public void selectCountry(String country) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByValue(country);
	}

	public void clickIAgree() {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.document.getElementById('sepadirectdebit.acceptDirectDebit').click()");
	}

	public void clickOnConfirm() {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.document.querySelector('input.paySubmit.paySubmitsepadirectdebit').click()");
	}

}

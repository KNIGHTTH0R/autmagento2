package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class SepaPaymentPage extends AbstractPage{

	@FindBy(id = "sepadirectdebit.ownerName")
	private WebElement bankNameInput;
	
	@FindBy(id = "sepadirectdebit.bankAccountNumber")
	private WebElement bankAccountInput;
	
	@FindBy(id = "sepadirectdebit.bankCountryCode")
	private WebElement countrySelect;
	
	@FindBy(id = "sepadirectdebit.acceptDirectDebit")
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
		selectFromDropdown(countrySelect, country);
	}
	
	public void clickIAgree() {
		element(acceptDirectDebitCheckbox).waitUntilVisible();
		acceptDirectDebitCheckbox.click();
	}
	public void clickOnConfirm() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
	
	
	
}

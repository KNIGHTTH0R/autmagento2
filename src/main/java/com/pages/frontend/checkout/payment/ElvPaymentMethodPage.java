package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ElvPaymentMethodPage extends AbstractPage{

	@FindBy(id = "bankAccountNumber")
	private WebElement bankAccountNumberInput;
	
	@FindBy(id = "bankLocationId")
	private WebElement bankIdInput;
	
	@FindBy(id = "bankName")
	private WebElement bankNameInput;
	
	@FindBy(id = "bankLocation")
	private WebElement bankLocationInput;
	
	@FindBy(id = "accountHolderName")
	private WebElement bankAccountHolderNameInput;
	
	
	@FindBy(css = "input.paySubmit.paySubmitelv")
	private WebElement submitButton;

	public void bankAccountNumberInput(String bankAccountNumber) {
		element(bankAccountNumberInput).waitUntilVisible();
		bankAccountNumberInput.sendKeys(bankAccountNumber);
	}

	public void bankIdInput(String bankId) {
		element(bankIdInput).waitUntilVisible();
		bankIdInput.sendKeys(bankId);
	}
	
	public void bankNameInput(String bankName) {
		element(bankNameInput).waitUntilVisible();
		bankNameInput.sendKeys(bankName);
	}
	
	public void bankLocationInput(String bankLocation) {
		element(bankLocationInput).waitUntilVisible();
		bankLocationInput.sendKeys(bankLocation);
	}
	
	public void accountHolderNameInput(String bankHolderName) {
		element(bankAccountHolderNameInput).waitUntilVisible();
		bankAccountHolderNameInput.sendKeys(bankHolderName);
	}
	
	public void clickOnConfirm() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
	
	
	
	
}

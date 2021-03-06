package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class CreditCardFormPage extends AbstractPage {

	@FindBy(id = "card.cardNumber")
	private WebElement cardNumberInput;

	@FindBy(id = "card.cardHolderName")
	private WebElement cardHolderInput;

	@FindBy(id = "card.expiryMonth")
	private WebElement expiryMonthSelect;

	@FindBy(id = "card.expiryYear")
	private WebElement expiryYearSelect;

	@FindBy(id = "card.cvcCode")
	private WebElement cvcCodeInput;

	@FindBy(css = "input.paySubmit.paySubmitcard")
	private WebElement submitButton;

	public void cardNumberInput(String cardNumber) {
		element(cardNumberInput).waitUntilVisible();
	//	clickElement(cardNumberInput);
		cardNumberInput.sendKeys(cardNumber);
	}

	public void cardHolderInput(String cardHolder) {
		element(cardHolderInput).waitUntilVisible();
		//clickElement(cardHolderInput);
		cardHolderInput.sendKeys(cardHolder);
	}

	public void selectMonthExpiry(String month) {
		
		selectFromDropdown(expiryMonthSelect, month);
	}

	public void selectYearExpiry(String year) {
		selectFromDropdown(expiryYearSelect, year);
	}

	public void cvcCodeInput(String cvc) {
		element(cvcCodeInput).waitUntilVisible();
		cvcCodeInput.sendKeys(cvc);
	}

	public void clickOnConfirm() {
		element(submitButton).waitUntilVisible();
	//	clickElement(submitButton);
		submitButton.click();
	}
}

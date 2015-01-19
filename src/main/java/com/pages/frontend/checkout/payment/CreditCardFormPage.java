package com.pages.frontend.checkout.payment;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class CreditCardFormPage extends AbstractPage {

	@FindBy(id = "card.cardNumber")
	private WebElement cardNumberInput;

	@FindBy(id = "card.cardHolderName")
	private WebElement cardHolderInput;

	@FindBy(css = "select#card.expiryMonth")
	private WebElement expiryMonthSelect;

	@FindBy(css = "select#card.expiryYear")
	private WebElement expiryYearSelect;

	@FindBy(id = "card.cvcCode")
	private WebElement cvcCodeInput;

	public void cardNumberInput(String cardNumber) {
		element(cardNumberInput).waitUntilVisible();
		cardNumberInput.sendKeys(cardNumber);
	}

	public void cardHolderInput(String cardHolder) {
		element(cardHolderInput).waitUntilVisible();
		cardHolderInput.sendKeys(cardHolder);
	}

	public void selectMonthExpiry(String month) {
		element(expiryMonthSelect).waitUntilVisible();
		element(expiryMonthSelect).selectByVisibleText(month);
	}

	public void selectYearExpiry(String year) {
		element(expiryYearSelect).waitUntilVisible();
		element(expiryYearSelect).selectByVisibleText(year);
	}

	public void cvcCodeInput(String cvc) {
		element(cvcCodeInput).waitUntilVisible();
		cvcCodeInput.sendKeys(cvc);
	}
}

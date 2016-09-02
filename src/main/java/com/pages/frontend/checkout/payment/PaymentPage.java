package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

/**
 * Main page from payment package
 * 
 * @author voicu.vac
 *
 */
public class PaymentPage extends AbstractPage {

	@FindBy(css = "input.imgB.pmB.pmBcard")
	private WebElement creditCardContainer;

	@FindBy(css = "#paymentMethods li input.imgB.pmB.pmBbankTransfer_DE")
	private WebElement bankTransfer;

	@FindBy(css = "#paymentMethods li input.imgB.pmB.pmBbankTransfer_IBAN")
	private WebElement bankTransferEs;

	@FindBy(css = "input.paySubmit.paySubmitbankTransfer_DE")
	private WebElement confirmPayBankTransfer;

	@FindBy(css = "input.paySubmit.paySubmitbankTransfer_IBAN")
	private WebElement confirmPayBankTransferEs;

	@FindBy(id = "card.cardNumber")
	private WebElement cardNumberInput;
	
	@FindBy(id = "mainBack")
	private WebElement backButton;

	public void expandCreditCardForm() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(creditCardContainer).waitUntilVisible();
		creditCardContainer.click();
	}

	public boolean isCreditCardFormExpended() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		return element(cardNumberInput).isCurrentlyVisible();
	}
	
	public void goBack() {
		element(backButton).waitUntilVisible();
		backButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
	}

	public void expandBankTransferForm() {
		element(bankTransfer).waitUntilVisible();
		bankTransfer.click();
	}

	public void expandBankTransferFormEs() {
		element(bankTransferEs).waitUntilVisible();
		bankTransferEs.click();
	}

	public void confirmPayBankTransfer() {
		element(confirmPayBankTransfer).waitUntilVisible();
		confirmPayBankTransfer.click();
	}

	public void confirmPayBankTransferEs() {
		element(confirmPayBankTransferEs).waitUntilVisible();
		confirmPayBankTransferEs.click();
	}

}

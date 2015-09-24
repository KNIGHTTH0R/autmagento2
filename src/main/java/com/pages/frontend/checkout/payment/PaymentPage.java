package com.pages.frontend.checkout.payment;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

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

	public void expandCreditCardForm() {
		element(creditCardContainer).waitUntilVisible();
		creditCardContainer.click();
		waitABit(2000);
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

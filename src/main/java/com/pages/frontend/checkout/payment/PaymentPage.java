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

	@FindBy(css = "input.paySubmit.paySubmitbankTransfer_DE")
	private WebElement confirmPayBankTransfer;

	public void expandCreditCardForm() {
		element(creditCardContainer).waitUntilVisible();
		creditCardContainer.click();
	}

	public void expandBankTransferForm() {
		element(bankTransfer).waitUntilVisible();
		bankTransfer.click();
	}

	public void confirmPayBankTransfer() {
		element(confirmPayBankTransfer).waitUntilVisible();
		confirmPayBankTransfer.click();
	}

}

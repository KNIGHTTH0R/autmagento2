package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.CreditCardModel;
import com.tools.requirements.AbstractSteps;

public class PaymentSteps extends AbstractSteps {

	private static final long serialVersionUID = -828262912117558718L;

	@Step
	public void expandCreditCardForm() {
		paymentPage().expandCreditCardForm();
	}

	@Step
	public void expandBankTransferForm() {
		paymentPage().expandBankTransferForm();
	}

	@Step
	@Title("Fill credit card form")
	public void fillCreditCardForm(CreditCardModel creditCardData) {
		creditCardFormPage().cardNumberInput(creditCardData.getCardNumber());
		creditCardFormPage().cardHolderInput(creditCardData.getCardName());
		creditCardFormPage().selectMonthExpiry(creditCardData.getMonthExpiration());
		creditCardFormPage().selectYearExpiry(creditCardData.getYearExpiration());
		creditCardFormPage().cvcCodeInput(creditCardData.getCvcNumber());
		creditCardFormPage().clickOnConfirm();
		waitABit(2000);
	}

	@Step
	@Title("Pay With Bank transfer")
	public void payWithBankTransfer() {
		paymentPage().expandBankTransferForm();
		paymentPage().confirmPayBankTransfer();
		waitABit(2000);
	}

}

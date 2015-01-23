package com.steps.frontend.checkout;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.frontend.CreditCardModel;

public class PaymentSteps extends AbstractSteps{

	private static final long serialVersionUID = -828262912117558718L;

	@Step
	public void expandCreditCardForm(){
		paymentPage().expandCreditCardForm();
	}
	
	@Step
	public void fillCreditCardForm(CreditCardModel creditCardData){
		creditCardFormPage().cardNumberInput(creditCardData.getCardNumber());
		creditCardFormPage().cardHolderInput(creditCardData.getCardName());
		creditCardFormPage().selectMonthExpiry(creditCardData.getMonthExpiration());
		creditCardFormPage().selectYearExpiry(creditCardData.getYearExpiration());
		creditCardFormPage().cvcCodeInput(creditCardData.getCvcNumber());
		creditCardFormPage().clickOnConfirm();
	}
	
}

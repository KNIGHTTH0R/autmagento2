package com.steps.frontend.checkout;

import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ElvPaymentMethodModel;
import com.tools.data.frontend.SepaPaymentMethodModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class PaymentSteps extends AbstractSteps {

	private static final long serialVersionUID = -828262912117558718L;

	@Step
	public void expandCreditCardForm() {
		if (!paymentPage().isCreditCardFormExpended())
			paymentPage().expandCreditCardForm();
	}

	@Step
	public void expandElvForm() {
		if (!paymentPage().isElvFormExpended())
			paymentPage().expandElvForm();
	}

	@Step
	public void expandSepaForm() {
		if (!paymentPage().isSepaFormExpended())
			paymentPage().expandSepaForm();
	}

	@Step
	public boolean isKlarnaAvailable() {
		return paymentPage().isKlarnaAvailable();
	}

	@Step
	public void expandKlarnaForm() {
		if (!paymentPage().isKlarnaFormExpended())
			paymentPage().expandKlarnaForm();
	}

	@Step
	public void goBack() {
		paymentPage().goBack();
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
	@Title("Fill elv form")
	public void fillElvForm(ElvPaymentMethodModel elvPaymentMethodModel) {
		elvPaymentMethodPage().bankAccountNumberInput(elvPaymentMethodModel.getBankAcckountNumber());
		elvPaymentMethodPage().bankIdInput(elvPaymentMethodModel.getBankId());
		elvPaymentMethodPage().bankNameInput(elvPaymentMethodModel.getBankName());
		elvPaymentMethodPage().bankLocationInput(elvPaymentMethodModel.getBankLocation());
		elvPaymentMethodPage().accountHolderNameInput(elvPaymentMethodModel.getBankAccountHolderName());
		elvPaymentMethodPage().clickOnConfirm();
		waitABit(2000);
	}

	@Step
	@Title("Fill sepa form")
	public void fillSepaForm(SepaPaymentMethodModel sepaPaymentMethodModel) {
		sepaPaymentPage().bankNameInput(sepaPaymentMethodModel.getBankAccountName());
		sepaPaymentPage().selectCountry(sepaPaymentMethodModel.getCountry());
		sepaPaymentPage().bankAccountNumberInput(sepaPaymentMethodModel.getBankAccountNumber());
		sepaPaymentPage().clickIAgree();
		sepaPaymentPage().clickOnConfirm();
		waitABit(2000);
	}

	@Step
	@Title("Fill Klarna form")
	public void fillKlarnaForm() {
		// klarnaPage().FirstNameInput(klarnaPaymentMethodModel.getFirstName());
		// klarnaPage().LastNameInput(klarnaPaymentMethodModel.getLastName());
		// klarnaPage().selectGender(klarnaPaymentMethodModel.getGender());
		// klarnaPage().DateOfBirthDayInput(klarnaPaymentMethodModel.getDateOfBirthDay());
		// klarnaPage().DateOfBirthMonthInput(klarnaPaymentMethodModel.getDateOfBirthMonth());
		// klarnaPage().DateOfBirthYearInput(klarnaPaymentMethodModel.getDateOfBirthYear());
		// klarnaPage().mobilePhoneInput(klarnaPaymentMethodModel.getMobileNumber());
		// klarnaPage().streetBillingInput(klarnaPaymentMethodModel.getStreetBilling());
		// klarnaPage().houseNumberBillingInput(klarnaPaymentMethodModel.getHouseNumberBilling());
		// klarnaPage().postalCodeBillingInput(klarnaPaymentMethodModel.getPlzBilling());
		// klarnaPage().cityBillingInput(klarnaPaymentMethodModel.getCityBilling());
		// klarnaPage().countryBillingInput(klarnaPaymentMethodModel.getCountryBilling());
		// klarnaPage().separateAddressCheckbox();
		// klarnaPage().streetShippingInput(klarnaPaymentMethodModel.getStreetShipping());
		// klarnaPage().houseNumberShippingInput(klarnaPaymentMethodModel.getHouseNumberShipping());
		// klarnaPage().postalCodeShippingInput(klarnaPaymentMethodModel.getPlzShipping());
		// klarnaPage().cityShippingInput(klarnaPaymentMethodModel.getCountryShipping());
		klarnaPage().clickOnTermAndConditions();
		klarnaPage().clickOnConfirm();
		waitABit(2000);
	}

	@Step
	@Title("Pay With Bank transfer")
	public void payWithBankTransfer() {
		paymentPage().expandBankTransferForm();
		paymentPage().confirmPayBankTransfer();
		paymentPage().clickOnWeiterBtn();
		waitABit(5000);
	}

	@Step
	@Title("Pay With Bank transfer for Es website")
	public void payWithBankTransferEs() {
		paymentPage().expandBankTransferFormEs();
		paymentPage().confirmPayBankTransferEs();
		waitABit(3000);
	}

}

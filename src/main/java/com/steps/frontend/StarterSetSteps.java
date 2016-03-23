package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.tools.data.frontend.AddressModel;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class StarterSetSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickOnJetztStyleCoachWerdenButton() {
		starterSetPage().clickOnJetztStyleCoachWerdenButton();
	}

	@Step
	public void clickOnJetztStartenFromStarterSet() {
		starterSetPage().clickOnJetztStartenFromStarterSet();
	}

	@Title("Fill new address for shipping")
	@Step
	public void fillNewAddressForShipping(AddressModel addressModel) {
		waitABit(TimeConstants.TIME_CONSTANT);
		inputShippingStreetNumber(addressModel.getStreetNumber());
		inputShippingPostCode(addressModel.getPostCode());
		inputShippingHomeTown(addressModel.getHomeTown());
	}

	@Step
	public void inputShippingStreetNumber(String streetNumber) {
		starterSetPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputShippingPostCode(String postCode) {
		starterSetPage().inputPostCode(postCode);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void inputShippingHomeTown(String homeTown) {
		starterSetPage().inputHomeTown(homeTown);
	}

	@Step
	public void setSameAsBilling(boolean checked) {
		shippingFormPage().setSameAsBilling(checked);
	}

	@Step
	public void goToPaymentMethod() {
		surveyPage().clickGoToPaymentMethod();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void submitStarterSetStep() {
		starterSetPage().submitStep();
	}

	@Step
	public void selectStarterKit() {
		starterSetPage().selectStarterKit();
	}

	@Step
	public void applyVoucher(String code) {
		starterSetPage().inputVoucherCode(code);
		starterSetPage().submitVoucherCode();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public StylistRegistrationCartTotalModel grabCartTotal(boolean isVoucherApplied) {
		return starterSetPage().grabCartTotal(isVoucherApplied);
	}

}

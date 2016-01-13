package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class ShippingSteps extends AbstractSteps {

	private static final long serialVersionUID = 8727875042758615102L;

	public ShippingModel grabSurveyData() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyData();
	}

	@Step
	public void goToPaymentMethod() {
		surveyPage().clickGoToPaymentMethod();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public List<CartProductModel> grabProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabProductsList();
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsList();
	}

	public List<HostCartProductModel> grabHostProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabHostProductsList();
	}

	public List<BorrowedCartModel> grabBorrowedProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabBorrowedProductsList();
	}

	@Step
	public void selectAddress(String address) {
		billingFormPage().selectAdressDropdown(address);
		// billingFormPage().verifyThatYouCannotBillOnRestrictedCountries();
	}

	@Title("Fill new address for billing")
	@Step
	public void addNewAddressForBilling(AddressModel addressModel) {

		billingFormPage().clickAddNewAddress();

		waitABit(TimeConstants.TIME_CONSTANT);
		inputBillingStreetAddress(addressModel.getStreetAddress());
		inputBillingStreetNumber(addressModel.getStreetNumber());
		inputBillingPostCode(addressModel.getPostCode());
		inputBillingHomeTown(addressModel.getHomeTown());
		selectBillingCountryName(addressModel.getCountryName());
		inputBillingPhoneNumber(addressModel.getPhoneNumber());
	}

	@Title("Fill new address for billing without plz")
	@Step
	public void addNewAddressForBillingWithoutPlz(AddressModel addressModel) {

		billingFormPage().clickAddNewAddress();

		waitABit(TimeConstants.TIME_CONSTANT);
		inputBillingStreetAddress(addressModel.getStreetAddress());
		inputBillingStreetNumber(addressModel.getStreetNumber());
		inputBillingHomeTown(addressModel.getHomeTown());
		selectBillingCountryName(addressModel.getCountryName());
		inputBillingPhoneNumber(addressModel.getPhoneNumber());
	}

	@Title("Fill new address for shipping")
	@Step
	public void addNewAddressForShipping(AddressModel addressModel) {
		shippingFormPage().clickAddNewAddress();
		waitABit(TimeConstants.TIME_CONSTANT);
		inputShippingStreetAddress(addressModel.getStreetAddress());
		inputShippingStreetNumber(addressModel.getStreetNumber());
		inputShippingPostCode(addressModel.getPostCode());
		inputShippingHomeTown(addressModel.getHomeTown());
		selectShippingCountryName(addressModel.getCountryName());
		inputShippingPhoneNumber(addressModel.getPhoneNumber());
	}
	
	@Title("Fill new address for shipping")
	@Step
	public void fillNewAddressForShipping(AddressModel addressModel) {
		waitABit(TimeConstants.TIME_CONSTANT);
		inputShippingStreetAddress(addressModel.getStreetAddress());
		inputShippingStreetNumber(addressModel.getStreetNumber());
		inputShippingPostCode(addressModel.getPostCode());
		inputShippingHomeTown(addressModel.getHomeTown());
		selectShippingCountryName(addressModel.getCountryName());
		inputShippingPhoneNumber(addressModel.getPhoneNumber());
	}

	@Step
	public void setSameAsBilling(boolean checked) {
		shippingFormPage().setSameAsBilling(checked);
	}

	@Step
	public void selectKnowStylistNoOption() {
		shippingFormPage().selectKnowStylistNoOption();
	}

	@Step
	public void checkTermsCheckbox() {
		shippingFormPage().checkTermsCheckbox();
	}

	@Title("Get order id and total from url")
	@Step
	public String grabUrl() {
		return getDriver().getCurrentUrl();
	}

	@Step
	public void selectShippingAddress(String value) {
		// shippingFormPage().verifyThatYouCannotShipOnRestrictedCountries();
		shippingFormPage().selectShippingAddress(value);
		waitABit(1000);
	}

	@Step
	public void inputBillingStreetAddress(String streetAddress) {
		billingFormPage().inputStreet1Address(streetAddress);
	}

	@Step
	public void inputBillingStreetNumber(String streetNumber) {
		billingFormPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputBillingPostCode(String postCode) {
		billingFormPage().inputPostCode(postCode);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void clearAndInputNewPostCode(String postCode) {
		billingFormPage().clearAndInputNewPostCode(postCode);
	}

	@Step
	public void clearAndInputNewHomeTown(String homeTown) {
		billingFormPage().clearAndInputNewHomeTown(homeTown);
	}

	@Step
	public void inputBillingHomeTown(String homeTown) {
		billingFormPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectBillingCountryName(String countryName) {
		billingFormPage().selectCountryName(countryName);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void inputBillingPhoneNumber(String phoneNumber) {
		billingFormPage().inputPhoneNumber(phoneNumber);
	}

	@Step
	public void inputShippingStreetAddress(String streetAddress) {
		shippingFormPage().inputStreet1Address(streetAddress);
	}

	@Step
	public void inputShippingStreetNumber(String streetNumber) {
		shippingFormPage().inputStreetNumber(streetNumber);
	}

	@Step
	public void inputShippingPostCode(String postCode) {
		shippingFormPage().inputPostCode(postCode);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void inputShippingHomeTown(String homeTown) {
		shippingFormPage().inputHomeTown(homeTown);
	}

	@Step
	public void selectShippingCountryName(String countryName) {
		shippingFormPage().selectCountryName(countryName);
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	@Step
	public void inputShippingPhoneNumber(String phoneNumber) {
		shippingFormPage().inputPhoneNumber(phoneNumber);
	}

}

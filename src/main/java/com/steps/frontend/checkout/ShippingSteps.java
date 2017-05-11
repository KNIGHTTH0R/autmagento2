package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.constants.TimeConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.requirements.AbstractSteps;

public class ShippingSteps extends AbstractSteps {

	private static final long serialVersionUID = 8727875042758615102L;

	public ShippingModel grabSurveyData() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyData();
	}

	public ShippingModel grabSurveyDataTp0() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyDataTp0();
	}

	public ShippingModel grabSurveyDataTp1() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyDataTp1();
	}

	public ShippingModel grabSurveyDataTp2() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyDataTp2();
	}
	
	public ShippingModel grabSurveyDataTp3() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabSurveyDataTp3();
	}

	@Step
	public void goToPaymentMethod() {
		surveyPage().clickGoToPaymentMethod();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public List<CartProductModel> grabProductsList() {
		waitABit(TimeConstants.TIME_MEDIUM);
		return surveyPage().grabProductsList();
	}

	public List<CartProductModel> grabSFMProductsListTp0() {
		waitABit(TimeConstants.TIME_MEDIUM);
		return surveyPage().grabSFMProductsListTp0();
	}
	
	public List<CartProductModel> grabSFMProductsListTp1() {
		waitABit(TimeConstants.TIME_MEDIUM);
		return surveyPage().grabSFMProductsListTp1();
	}
	
	public List<CartProductModel> grabSFMProductsListTp2() {
		waitABit(TimeConstants.TIME_MEDIUM);
		return surveyPage().grabSFMProductsListTp2();
	}
	
	public List<RegularUserCartProductModel> grabRegularProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsList();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp0() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsListTp0();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp1() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsListTp1();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp2() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsListTp2();
	}
	
	public List<RegularUserCartProductModel> grabRegularProductsListTp3() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabRegularProductsListTp3();
	}

	public List<HostCartProductModel> grabHostProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabHostProductsList();
	}

	public List<HostCartProductModel> grabHostProductsListTp0() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabHostProductsListTp0();
	}

	public List<HostCartProductModel> grabHostProductsListTp1() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabHostProductsListTp1();
	}

	public List<HostCartProductModel> grabHostProductsListTp2() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabHostProductsListTp2();
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
	public void fillNewAddressForBilling(AddressModel addressModel) {

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
	public void fillNewAddressForBillingWithoutPlz(AddressModel addressModel) {

		waitABit(TimeConstants.TIME_CONSTANT);
		inputBillingStreetAddress(addressModel.getStreetAddress());
		inputBillingStreetNumber(addressModel.getStreetNumber());
		inputBillingHomeTown(addressModel.getHomeTown());
		selectBillingCountryName(addressModel.getCountryName());
		inputBillingPhoneNumber(addressModel.getPhoneNumber());
	}

	@Title("Add new address for billing")
	@Step
	public void addNewAddressForBilling() {
		billingFormPage().clickAddNewAddress();

	}

	@Title("Add new address for shipping")
	@Step
	public void addNewAddressForShipping() {
		shippingFormPage().clickAddNewAddress();

	}

	@Title("Fill new address for shipping")
	@Step
	public void fillNewAddressForShipping(AddressModel addressModel) {

		waitABit(TimeConstants.TIME_CONSTANT);
		inputShippingStreetAddress(addressModel.getStreetAddress());
		inputShippingStreetNumber(addressModel.getStreetNumber());
		inputShippingPostCode(addressModel.getPostCode());
		clearAndInputNewHomeTown(addressModel.getHomeTown());
		selectShippingCountryName(addressModel.getCountryName());
		inputShippingPhoneNumber(addressModel.getPhoneNumber());
	}

	@Step
	public void setSameAsBilling(boolean checked) {
		shippingFormPage().setSameAsBilling(checked);
	}

	@Step
	public void goBack() {
		shippingFormPage().goBack();
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
	
	@Step
	public void selectPartyNoOptionIfPresent() {
		shippingFormPage().selectPartyNoOptionIfPresent();
	}

	public void checkIsPreshippedFlag() {
		shippingFormPage().checkIsPreshippedFlag();
		
	}
	

}

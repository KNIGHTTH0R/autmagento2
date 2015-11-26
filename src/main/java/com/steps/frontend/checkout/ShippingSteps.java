package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CustomerFormModel;
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

	@Step
	public void addNewAddressForBilling(AddressModel addressModel) {
		billingFormPage().clickAddNewAddress();
		waitABit(3000);
		billingFormPage().inputStreet1Address(addressModel.getStreetAddress());
		billingFormPage().inputStreetNumber(addressModel.getStreetNumber());
		billingFormPage().inputPostCode(addressModel.getPostCode());
		shippingFormPage().inputHomeTown(addressModel.getHomeTown());
		billingFormPage().selectCountryName(addressModel.getCountryName());
		billingFormPage().inputPhoneNumber(addressModel.getPhoneNumber());
	}

	@Step
	public void addNewAddressForShipping(AddressModel addressModel) {
		shippingFormPage().clickAddNewAddress();
		waitABit(3000);
		shippingFormPage().inputStreet1Address(addressModel.getStreetAddress());
		shippingFormPage().inputStreetNumber(addressModel.getStreetNumber());
		shippingFormPage().inputPostCode(addressModel.getPostCode());
		shippingFormPage().inputHomeTown(addressModel.getHomeTown());
		shippingFormPage().selectCountryName(addressModel.getCountryName());
		shippingFormPage().inputPhoneNumber(addressModel.getPhoneNumber());
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

}

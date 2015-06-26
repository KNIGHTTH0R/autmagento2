package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.BorrowProductModel;
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
	public void clickGoToPaymentMethod() {
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
	public List<BorrowProductModel> grabBorrowedProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return surveyPage().grabBorrowedProductsList();
	}

	@Step
	public void selectAddress(String address) {
		billingFormPage().selectAdressDropdown(address);
		// billingFormPage().verifyThatYouCannotBillOnRestrictedCountries();
	}

	@Step
	public void setSameAsBilling(boolean checked) {
		shippingFormPage().setSameAsBilling(checked);
		waitABit(1000);
	}

	@Step
	public void selectKnowStylistNoOption() {
		shippingFormPage().selectKnowStylistNoOption();
	}

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

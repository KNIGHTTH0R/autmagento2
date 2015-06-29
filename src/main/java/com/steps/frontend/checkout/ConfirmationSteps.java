package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractSteps;

public class ConfirmationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4739618000222382968L;

	public AddressModel grabSippingData() {
		return confirmationPage().grabShippingData();
	}

	public AddressModel grabBillingData() {
		return confirmationPage().grabBillingData();
	}

	public List<CartProductModel> grabProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabProductsList();
	}

	public List<BorrowedCartModel> grabBorrowedProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabBorrowedProductsList();
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		return confirmationPage().grabRegularProductsList();
	}

	public List<HostCartProductModel> grabHostProductsList() {
		return confirmationPage().grabHostProductsList();
	}

	public ShippingModel grabConfirmationTotals() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotals();
	}

	@Step
	public void agreeAndCheckout() {
		confirmationPage().clickIAgree();
		confirmationPage().clickOnSubmit();
		waitABit(TimeConstants.WAIT_TIME_LONG);
	}

	@Step
	public void validateBillingAddress(String billingAddress) {
		AddressModel address = confirmationPage().grabBillingData();
		CustomVerification.verifyTrue("Billing address is incorrect", billingAddress.contains(address.getCountryName()));

	}

	@Step
	public void validateShippingAddress(String shippingAddress) {
		AddressModel address = confirmationPage().grabShippingData();
		CustomVerification.verifyTrue("Shipping address is incorrect", shippingAddress.contentEquals(address.getCountryName()));

	}

}

package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.requirements.AbstractSteps;

public class ConfirmationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4739618000222382968L;

	public AddressModel grabSippingData() {
		return confirmationPage().grabShippingData();
	}

	@Step
	public void clickOnChangeShippingAddress() {
		confirmationPage().changeShippingAddress();
	}

	@Step
	public void clickOnChangeBillingAddress() {
		confirmationPage().changeBillingAddress();
	}

	public AddressModel grabBillingData() {
		return confirmationPage().grabBillingData();
	}

	public List<CartProductModel> grabProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabProductsList();
	}
	
	public List<CartProductModel> grabSFMProductsListTp0() {
		return confirmationPage().grabSFMProductsListTp0();
	}

	public List<CartProductModel> grabSFMProductsListTp1() {
		return confirmationPage().grabSFMProductsListTp1();
	}

	public List<CartProductModel> grabSFMProductsListTp2() {
		return confirmationPage().grabSFMProductsListTp2();
	}
	
	public List<CartProductModel> grabSFMProductsListTp3() {
		return confirmationPage().grabSFMProductsListTp3();
	}

	public List<BorrowedCartModel> grabBorrowedProductsList() {
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabBorrowedProductsList();
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		return confirmationPage().grabRegularProductsList();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp0() {
		return confirmationPage().grabRegularProductsListTp0();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp1() {
		return confirmationPage().grabRegularProductsListTp1();
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp2() {
		return confirmationPage().grabRegularProductsListTp2();
	}
	
	public List<RegularUserCartProductModel> grabRegularProductsListTp3() {
		return confirmationPage().grabRegularProductsListTp3();
	}

	public List<HostCartProductModel> grabHostProductsList() {
		return confirmationPage().grabHostProductsList();
	}
	public List<HostCartProductModel> grabHostProductsListTp0() {
		return confirmationPage().grabHostProductsListTp0();
	}
	public List<HostCartProductModel> grabHostProductsListTp1() {
		return confirmationPage().grabHostProductsListTp1();
	}
	public List<HostCartProductModel> grabHostProductsListTp2() {
		return confirmationPage().grabHostProductsListTp2();
	}

	public ShippingModel grabConfirmationTotals() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotals();
	}

	public ShippingModel grabConfirmationTotalsTp0() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotalsTp0();
	}

	public ShippingModel grabConfirmationTotalsTp1() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotalsTp1();
	}

	public ShippingModel grabConfirmationTotalsTp2() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotalsTp2();
	}
	
	public ShippingModel grabConfirmationTotalsTp3() {
		waitABit(TimeConstants.TIME_CONSTANT);
		getDriver().navigate().to(getDriver().getCurrentUrl());
		waitABit(TimeConstants.TIME_CONSTANT);
		return confirmationPage().grabConfirmationTotalsTp3();
	}

	@Step
	public void agreeAndCheckout() {
		confirmationPage().clickIAgree();
		confirmationPage().clickOnSubmit();
		waitABit(TimeConstants.WAIT_TIME_EXTRA_LONG);
	}

	@Step
	public void validateBillingAddress(String billingAddress) {
		AddressModel address = confirmationPage().grabBillingData();
		CustomVerification.verifyTrue("Billing address is incorrect",
				billingAddress.contains(address.getCountryName()));

	}

	@Step
	public void validateShippingAddress(String shippingAddress) {
		AddressModel address = confirmationPage().grabShippingData();
		CustomVerification.verifyTrue("Shipping address is incorrect",
				shippingAddress.contentEquals(address.getCountryName()));

	}

}

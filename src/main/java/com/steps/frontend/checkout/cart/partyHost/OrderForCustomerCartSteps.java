package com.steps.frontend.checkout.cart.partyHost;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.requirements.AbstractSteps;

public class OrderForCustomerCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public List<HostCartProductModel> grabProductsData() {
		return orderForCustomerCartPage().grabProductsData();
	}

	@Step
	public HostCartTotalsModel grabTotals(String voucherLabel) {
		return orderForCustomerCartPage().grabTotals(voucherLabel);
	}

	@Step
	public void clickGoToShipping() {
		orderForCustomerCartPage().clickToShipping();
	}

	@Step
	public void updateCart() {
		orderForCustomerCartPage().clickUpdateCart();
		getDriver().navigate().refresh();
	}

	@Step
	public void typeCouponCode(String code) {
		orderForCustomerCartPage().typeCouponCode(code);
		orderForCustomerCartPage().submitVoucherCode();
	}

//	@Step
//	public void submitVoucherCode() {
//		orderForCustomerCartPage().submitVoucherCode();
//	}

	@Step
	public void openSearchProductsModal() {
		orderForCustomerCartPage().openSearchProductsModal();
	}

}

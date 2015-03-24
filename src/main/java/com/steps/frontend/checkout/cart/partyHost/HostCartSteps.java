package com.steps.frontend.checkout.cart.partyHost;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.requirements.AbstractSteps;

public class HostCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectProductDiscountType(String productCode, String discountType) {
		hostCartPage().selectProductDiscountType(productCode, discountType);
	}

	@Step
	@Title("Update product list")
	public void updateProductList(List<HostBasicProductModel> productsList, String productCode, String discountType) {
		hostCartPage().updateProductList(productsList, productCode, discountType);
	}

	@Step
	public List<HostCartProductModel> grabProductsData() {
		return hostCartPage().grabProductsData();
	}

	@Step
	public HostCartTotalsModel grabTotals() {
		return hostCartPage().grabTotals();
	}

	@Step
	public void selectShippingOption(String option) {
		hostCartPage().selectShippingOption(option);
	}

	@Step
	public void clickGoToShipping() {
		hostCartPage().clickToShipping();
	}

	@Step
	public void updateCart() {
		hostCartPage().clickUpdateCart();
		getDriver().navigate().refresh();
	}

	@Step
	public void wipeCart(String URL) {
		// modify URL to wipe cart
		URL = URL.replace("stylist/lounge/", "checkout/cart/clearAllItems/");
		// call cart wipe
		getDriver().get(URL);
		hostCartPage().verifyWipeCart();
	}
}

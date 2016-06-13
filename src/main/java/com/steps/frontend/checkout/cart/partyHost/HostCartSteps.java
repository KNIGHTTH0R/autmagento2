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

	public void updateProductList(List<HostBasicProductModel> productsList, String productCode, String discountType) {
		hostCartPage().updateProductList(productsList, productCode, discountType);
	}

	@Step
	public List<HostCartProductModel> grabProductsData() {
		return hostCartPage().grabProductsData();
	}

	@Step
	public List<HostCartProductModel> grabProductsDataWhenThereIsNoBonus() {
		return hostCartPage().grabProductsDataWhenNoBonus();
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
	public void clickDeliverOnVariousDate() {
		hostCartPage().clickDeliverOnVariousDate();
	}

	@Step
	public void clickAllOnThisDate() {
		hostCartPage().clickAllOnThisDate();
	}

	@Step
	public void updateCart() {
		hostCartPage().clickUpdateCart();
		getDriver().navigate().refresh();
	}

	@Step
	public void acceptInfoPopupForNotConsumedBonus() {
		hostCartPage().acceptInfoPopupForNotConsumedBonus();
	}

}

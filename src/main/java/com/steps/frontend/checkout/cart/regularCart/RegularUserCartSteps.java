
package com.steps.frontend.checkout.cart.regularCart;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;

import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class RegularUserCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	@Title("Update product list type")
	public void selectProductDiscountType(String productCode, String discountType) {
		regularUserCartPage().selectProductDiscountType(productCode, discountType);
	}

	@Step
	public String selectDeliveryDate(String productCode, Locale locale) throws ParseException {
		return regularUserCartPage().selectDeliveryDate(productCode, locale);
	}

	@Step
	public void selectDeliveryDate(String productCode, String date) throws ParseException {
		regularUserCartPage().selectDeliveryDate(productCode, date);
	}

	@Step
	public String getDeliveryDate(String productCode, Locale locale) throws ParseException {
		return regularUserCartPage().getDeliveryDate(productCode, locale);
	}

	@Step
	public void validateThatVoucherCannotBeAppliedMessage() {
		regularUserCartPage().validateThatVoucherCannotBeAppliedMessage();
	}

	@Step
	public void validateNotPrefferedShopAndGoToPreferredOne() {
		regularUserCartPage().validateNotPrefferedShopAndGoToPreferredOne();
	}

	@Step
	public void validateThatShippingOnSelectedCountryIsNotAllowed() {
		regularUserCartPage().validateThatShippingOnSelectedCountryIsNotAllowed();
	}

	public void updateProductList(List<RegularBasicProductModel> productsList, String productCode,
			String discountType) {
		regularUserCartPage().updateProductList(productsList, productCode, discountType);
	}

	@Step
	public List<RegularUserCartProductModel> grabProductsData() {
		return regularUserCartPage().grabProductsData();
	}

	@Step
	public RegularUserCartTotalsModel grabTotals(String voucherCodeLabel) {
		return regularUserCartPage().grabTotals(voucherCodeLabel);
	}

	@Step
	public void typeCouponCode(String code) {
		regularUserCartPage().typeCouponCode(code);
		regularUserCartPage().submitVoucherCode();

	}

	// @Step
	// public void submitVoucherCode() {
	// regularUserCartPage().submitVoucherCode();
	// }

	@Step
	public void clickDeliverAllAtOnce() {
		regularUserCartPage().clickDeliverAllAtOnce();
	}

	@Step
	public void verifyThatDeliveryDateDropdownIsDisabled(String productCode) {
		regularUserCartPage().verifyThatDeliveryDateDropdownIsDisabled(productCode);
	}
	@Step
	public void verifyThatTermPurchaseIsNotAvailable(String productCode) {
		regularUserCartPage().verifyThatTermPurchaseIsNotAvailable(productCode);
	}

	@Step
	public void selectShippingOption(String option) {
		regularUserCartPage().selectShippingOption(option);
	}
	
	

	@Step
	public void verifyThatMultipleDeliveryOptionIsChecked() {
		regularUserCartPage().verifyThatMultipleDeliveryOptionIsChecked();
	}

	@Step
	public void verifyMultipleDeliveryOptionIsEnabled() {
		regularUserCartPage().verifyMultipleDeliveryOptionIsEnabled();
	}

	@Step
	public void verifyMultipleDeliveryOptionIsDisabled() {
		regularUserCartPage().verifyMultipleDeliveryOptionIsDisabled();
	}

	@Step
	public void verifyDeliverAllImediatlyIsChecked() {
		regularUserCartPage().verifyDeliverAllImediatlyIsChecked();
	}

	@Step
	public void verifyDeliverAllImediatlyIsEnabled() {
		regularUserCartPage().verifyDeliverAllImediatlyIsEnabled();
	}

	@Step
	public void verifyDeliverAllImediatlyIsDisabled() {
		regularUserCartPage().verifyDeliverAllImediatlyIsDisabled();
	}

	@Step
	public void verifyDeliverAllOnThisDateIsChecked() {
		regularUserCartPage().verifyDeliverAllOnThisDateIsChecked();
	}

	@Step
	public void verifyDeliverAllOnThisDateIsEnabled() {
		regularUserCartPage().verifyDeliverAllOnThisDateIsEnabled();
	}

	@Step
	public void verifyDeliverAllOnThisDateIsDisabled() {
		regularUserCartPage().verifyDeliverAllOnThisDateIsDisabled();
	}
	

	@Step
	public void clickGoToShipping() {
		regularUserCartPage().clickToShipping();
	}

	@Step
	public void updateCart() {
		regularUserCartPage().clickUpdateCart();
		getDriver().navigate().refresh();
	}

	@Step
	public void searchProductsModal() {
		regularUserCartPage().searchProductsModal();
		getDriver().navigate().refresh();
	}

	public List<String> getAllDeliveryDates(String productCode, Locale locale) throws ParseException {
		return regularUserCartPage().getAllDeliveryDates(productCode, locale);
	}
	
	public List<String> grabbDeliverAllAtOnceDates(Locale locale) throws ParseException {
		return regularUserCartPage().grabbDeliverAllAtOnceDates(locale);
	}

	// sku added only for reporting
	@Step
	public void validateDeliveryDates(String sku, List<String> grabbedDates, List<String> expectedDates) {
		Assert.assertTrue(
				"The delivery dates for " + sku + " are not correct: Expected list : " + expectedDates
						+ " Actual list: " + grabbedDates,
				CollectionUtils.isEqualCollection(grabbedDates, expectedDates));
	}

	@Step
	public void validateDeliverAllAtOnceDates(List<String> grabbedDates, List<String> expectedDates) {
		Assert.assertTrue(
				"The delivery dates for deliver all at once are not correct: Expected list : " + expectedDates
						+ " Actual list: " + grabbedDates,
				CollectionUtils.isEqualCollection(grabbedDates, expectedDates));
	}
	
	@Step
	public void verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable() {
		regularUserCartPage().verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable();
		
	}

	

}

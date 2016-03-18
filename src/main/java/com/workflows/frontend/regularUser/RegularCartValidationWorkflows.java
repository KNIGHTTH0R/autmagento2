package com.workflows.frontend.regularUser;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.AdyenWorkflows;

public class RegularCartValidationWorkflows {

	@Steps
	public RegularUserCartWorkflows regularUserCartWorkflows;
	@Steps
	public RegularUserShippingAndConfirmationWorkflows regularUserShippingAndConfirmationWorkflows;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public AdyenWorkflows adyenWorkflows;

	public static String billingAddress;
	public static String shippingAddress;

	public void setBillingShippingAddress(String addressB, String addressS) {
		billingAddress = addressB;
		shippingAddress = addressS;
	}

	/**
	 * Note need to set billingAddress of this class. call setBillingAddress
	 */

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWith40DiscountAndJb() {

		checkoutValidationSteps.verifySuccessMessage();

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows
				.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel, RegularUserCartCalculator.shippingCalculatedModel);
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWith40DiscountAndJbAndBuy3Get1() {

		checkoutValidationSteps.verifySuccessMessage();

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40JbAndBuy3Get1Discount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows
				.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel, RegularUserCartCalculator.shippingCalculatedModel);
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWithVoucherApplied(boolean shouldVoucherBeVisible) {

		checkoutValidationSteps.verifySuccessMessage();

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS WITH VOUCHER APPLIED", shouldVoucherBeVisible);

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows
				.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel, RegularUserCartCalculator.shippingCalculatedModel);
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

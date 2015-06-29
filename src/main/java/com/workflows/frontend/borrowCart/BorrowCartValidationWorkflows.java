package com.workflows.frontend.borrowCart;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.borrowCart.BorrowCartCalculator;
import com.tools.datahandlers.borrowCart.BorrowDataGrabber;
import com.workflows.frontend.AddressWorkflows;

public class BorrowCartValidationWorkflows {

	@Steps
	public BorrowCartWorkflows borrowCartWorkflows;
	@Steps
	public BorrowCartShippingAndConfirmationWorkflows borrowCartShippingAndConfirmationWorkflows;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

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
	public void performBorrowCartValidations() {

		checkoutValidationSteps.verifySuccessMessage();

		borrowCartWorkflows.setValidateProductsModels(BorrowCartCalculator.allBorrowedProductsList, BorrowDataGrabber.grabbedBorrowCartProductsList);
		borrowCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		borrowCartShippingAndConfirmationWorkflows.setValidateProductsModels(BorrowCartCalculator.allBorrowedProductsList, BorrowDataGrabber.grabbedBorrowShippingProductsList);
		borrowCartShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		borrowCartShippingAndConfirmationWorkflows.setValidateProductsModels(BorrowCartCalculator.allBorrowedProductsList, BorrowDataGrabber.grabbedBorrowConfirmationProductsList);
		borrowCartShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		borrowCartWorkflows.setVerifyTotalsDiscount(BorrowDataGrabber.borrowCartGrabbedCartTotals, BorrowCartCalculator.borrowCartCalcDetailsModel);
		borrowCartWorkflows.verifyTotals("CART TOTALS");

		borrowCartShippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, BorrowCartCalculator.shippingCalculatedModel);
		borrowCartShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		borrowCartShippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, BorrowCartCalculator.shippingCalculatedModel);
		borrowCartShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}



}

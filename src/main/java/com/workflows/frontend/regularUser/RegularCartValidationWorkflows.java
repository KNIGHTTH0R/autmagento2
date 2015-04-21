package com.workflows.frontend.regularUser;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.regularUser.RegularUserCartCalculator;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.AddressWorkflows;

public class RegularCartValidationWorkflows {

	@Steps
	public RegularUserCartWorkflows regularUserCartWorkflows;
	@Steps
	public RegularUserShippingAndConfirmationWorkflows regularUserShippingAndConfirmationWorkflows;
	@Steps
	public AddressWorkflows addressWorkflows;
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
	public void performCartValidationsWith40DiscountAndJb() {

		checkoutValidationSteps.verifySuccessMessage();
		System.out.println("CartCalculator.productsList50: " + RegularUserCartCalculator.allProductsList.size());
		System.out.println("DataGrabber.cartProductsWith50Discount: " + RegularUserDataGrabber.grabbedRegularCartProductsList.size());
		System.out.println("------------------");
		PrintUtils.printListRegularBasicProductModel(RegularUserCartCalculator.allProductsList);
		System.out.println("------------------");
		PrintUtils.printListRegularCartProductModel(RegularUserDataGrabber.grabbedRegularCartProductsList);
		System.out.println("------------------");

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		addressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");

		addressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWith40DiscountAndJbAndBuy3Get1() {

		checkoutValidationSteps.verifySuccessMessage();
	
		System.out.println("CartCalculator.productsList50: " + CartCalculator.productsList50.size());
		System.out.println("DataGrabber.cartProductsWith50Discount: " + DataGrabber.cartProductsWith50Discount.size());

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40JbAndBuy3Get1Discount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		addressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");

		addressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWithVoucherApplied() {

		checkoutValidationSteps.verifySuccessMessage();
		System.out.println("CartCalculator.productsList50: " + CartCalculator.productsList50.size());
		System.out.println("DataGrabber.cartProductsWith50Discount: " + DataGrabber.cartProductsWith50Discount.size());

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS WITH VOUCHER APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		addressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");

		addressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

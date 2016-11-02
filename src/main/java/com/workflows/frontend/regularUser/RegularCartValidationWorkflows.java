package com.workflows.frontend.regularUser;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.AdyenWorkflows;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

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

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals,
				RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotals,
				RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel,
				RegularUserCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	
	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidations() {

		checkoutValidationSteps.verifySuccessMessage();

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotals,
				RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel,
				RegularUserCartCalculator.shippingCalculatedModel.getTotalAmount());
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

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals,
				RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40JbAndBuy3Get1Discount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotals,
				RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel,
				RegularUserCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsSplittedQuote() {

		checkoutValidationSteps.verifySuccessMessage();

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp0,
				RegularUserDataGrabber.grabbedRegularShippingProductsListTp0);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp1,
				RegularUserDataGrabber.grabbedRegularShippingProductsListTp1);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP1");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp2,
				RegularUserDataGrabber.grabbedRegularShippingProductsListTp2);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP2");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp0,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp0);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp1,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp1);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP1");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(
				RegularUserCartCalculator.allProductsListTp2,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp2);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP2");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals,
				RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS WITH 40% AND JB APPLIED");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotalsTp0, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotalsTp1,
				RegularUserCartCalculator.shippingCalculatedModelTp1);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP1");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotalsTp2,
				RegularUserCartCalculator.shippingCalculatedModelTp2);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP2");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotalsTp0,
				RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotalsTp1,
				RegularUserCartCalculator.shippingCalculatedModelTp1);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP1");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotalsTp2,
				RegularUserCartCalculator.shippingCalculatedModelTp2);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP2");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel,
				GeneralCartCalculations.calculateAdyenTotal(RegularUserCartCalculator.shippingCalculatedModel,
						RegularUserCartCalculator.shippingCalculatedModelTp1, RegularUserCartCalculator.shippingCalculatedModelTp2));
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

		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularCartProductsList);
		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularShippingProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList,
				RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals,
				RegularUserCartCalculator.calculatedTotalsDiscounts);
		regularUserCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS WITH VOUCHER APPLIED",
				shouldVoucherBeVisible);

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(
				RegularUserDataGrabber.regularUserConfirmationTotals,
				RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(RegularUserDataGrabber.orderModel,
				RegularUserCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

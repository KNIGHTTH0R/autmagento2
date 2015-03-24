package com.workflows.frontend.partyHost;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.partyHost.HostCartCalculator;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.workflows.frontend.AddressWorkflows;

public class HostCartValidationWorkflows {
	
	@Steps
	public HostCartWorkflows hostCartWorkflows;
	@Steps
	public HostShippingAndConfirmationWorkflows hostShippingAndConfirmationWorkflows;
	@Steps
	public AddressWorkflows addressWorkflows;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	
	public static String billingAddress;
	public static String shippingAddress;
	
	
	public void setBillingShippingAddress(String addressB,String addressS){
		billingAddress = addressB;
		shippingAddress = addressS;
	}
	
	/**
	 * Note need to set billingAddress of this class. call setBillingAddress
	 */

	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsWith40DiscountAndJb(){
		
//		checkoutValidationSteps.verifySuccessMessage();
		
		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");
		
		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals, HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS WITH 40% AND JB APPLIED");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsWith40DiscountAndJbAndBuy3Get1(){
		
//		checkoutValidationSteps.verifySuccessMessage();
		
		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListWithBuy3Get1Applied, HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");
		
		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");
		
		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals, HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40JbAndBuy3Get1Discount("CART TOTALS WITH 40% AND JB APPLIED");
		
		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
//
//	@StepGroup
//	@Screenshots(onlyOnFailures=true)
//	public void performCartValidationsWithVoucherApplied(){
//		
//		checkoutValidationSteps.verifySuccessMessage();
//		System.out.println("CartCalculator.productsList50: " + CartCalculator.productsList50.size());
//		System.out.println("DataGrabber.cartProductsWith50Discount: " + DataGrabber.cartProductsWith50Discount.size());
//		
//		regularUserCartWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularCartProductsList);
//		regularUserCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");
//		
//		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularShippingProductsList);
//		regularUserShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");
//		
//		regularUserShippingAndConfirmationWorkflows.setValidateProductsModels(RegularUserCartCalculator.allProductsList, RegularUserDataGrabber.grabbedRegularConfirmationProductsList);
//		regularUserShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");
//		
//		regularUserCartWorkflows.setVerifyTotalsDiscount(RegularUserDataGrabber.regularUserGrabbedCartTotals, RegularUserCartCalculator.calculatedTotalsDiscounts);
//		regularUserCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS WITH VOUCHER APPLIED");
//		
//		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
//		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");
//		
//		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
//		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
//		
//		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
//		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
//		
//		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
//		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
//	}
	


}

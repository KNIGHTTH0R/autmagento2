package com.workflows.frontend.partyHost;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.partyHost.HostCartCalculator;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.AddressWorkflows;

public class HostCartValidationWorkflows {	
	@Steps
	public HostCartWorkflows hostCartWorkflows;
	@Steps
	public HostShippingAndConfirmationWorkflows hostShippingAndConfirmationWorkflows;
//	@Steps
//	public AddressWorkflows addressWorkflows;
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
		
		checkoutValidationSteps.verifySuccessMessage();
		
		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");
		
		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals, HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		AddressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		AddressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsWithVoucherDiscount(){
		
		checkoutValidationSteps.verifySuccessMessage();
		
		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListwithVoucher, HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");
		
		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");
		
		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList, HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");
		
		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals, HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS");
		
		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");
		
		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals, HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		AddressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		AddressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsWith40DiscountAndJbAndBuy3Get1(){
		
		checkoutValidationSteps.verifySuccessMessage();
		
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
		
		AddressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		AddressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

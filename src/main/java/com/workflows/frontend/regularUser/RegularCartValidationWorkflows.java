package com.workflows.frontend.regularUser;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.Constants;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.regularUser.RegularUserCartCalculator;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.CartWorkflows2;
import com.workflows.frontend.ShippingAndConfirmationWorkflows;

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
	
	
	public void setBillingShippingAddress(String addressB,String addressS){
		billingAddress = addressB;
		shippingAddress = addressS;
	}
	
	/**
	 * Note need to set billingAddress of this class. call setBillingAddress
	 */

	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidations(){
		
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
		regularUserCartWorkflows.verifyTotalsDiscount("CART TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserShippingTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		regularUserShippingAndConfirmationWorkflows.setVerifyShippingTotals(RegularUserDataGrabber.regularUserConfirmationTotals, RegularUserCartCalculator.shippingCalculatedModel);
		regularUserShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	


}

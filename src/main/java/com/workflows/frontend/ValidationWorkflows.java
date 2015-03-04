package com.workflows.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;

public class ValidationWorkflows {
	
	@Steps
	public CartWorkflows2 cartWorkflows2;
	@Steps
	public ShippingAndConfirmationWorkflows shippingAndConfirmationWorkflows;
	@Steps
	public AddressWorkflows addressWorkflows;
	
	public static String billingAddress;
	
	
	public void setBillingAddress(String address){
		billingAddress = address;
	}
	
	/**
	 * Note need to set billingAddress of this class. call setBillingAddress
	 */
	@Step
	public void performCartValidations(){
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith25Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketing, DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");
	
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList50,DataGrabber.cartProductsWith50DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION -RECALCULATED");
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList25, DataGrabber.cartProductsWith25DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION -RECALCULATED");
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsListMarketing, DataGrabber.cartMarketingMaterialsProductsDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION -RECALCULATED");

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.allProductsList, DataGrabber.shippingProducts);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.allProductsList, DataGrabber.confirmationProducts);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");


		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscount("CART TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		addressWorkflows.setShippingAddressModels(billingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

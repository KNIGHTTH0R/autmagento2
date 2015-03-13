package com.workflows.frontend;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;

public class ValidationWorkflows {
	
	@Steps
	public CartWorkflows2 cartWorkflows2;
	@Steps
	public CartWorkflows cartWorkflows;
	@Steps
	public ShippingAndConfirmationWorkflows shippingAndConfirmationWorkflows;
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
	public void performCartValidations119Vat(){
		
		checkoutValidationSteps.verifySuccessMessage();	
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith25Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketing, DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList25, DataGrabber.cartProductsWith25DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION -RECALCULATED");
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsListMarketing, DataGrabber.cartMarketingMaterialsProductsDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION -RECALCULATED");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.shippingProductsList, DataGrabber.shippingProducts);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.shippingProductsList, DataGrabber.confirmationProducts);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");		
		
		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscount("CART TOTALS");
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		addressWorkflows.setBillingAddressModels(billingAddress,DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidations(){
		
		checkoutValidationSteps.verifySuccessMessage();
		System.out.println("CartCalculator.productsList50: " + CartCalculator.productsList50.size());
		System.out.println("DataGrabber.cartProductsWith50Discount: " + DataGrabber.cartProductsWith50Discount.size());
		
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
		
		addressWorkflows.setShippingAddressModels(shippingAddress,DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsBu3Get1Rule(){
		
		checkoutValidationSteps.verifySuccessMessage();
		
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productList25AndMmWithBuy3Get1Applied,DataGrabber.cartProductsList25AndMm);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

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

		addressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		addressWorkflows.validateBillingAddress("BILLING ADDRESS");

		addressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		addressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}
	@StepGroup
	@Screenshots(onlyOnFailures=true)
	public void performCartValidationsBuy3Get1RuleJbAndMbApplied(){
		
		checkoutValidationSteps.verifySuccessMessage();
		
		//validations before JB and MM Discounts - in this phase ,on products is applied buy3Get1 rule, so we are validating those prices 
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productList25AndMmWithBuy3Get1Applied, DataGrabber.cartProductsList25AndMm);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");
		
		//validations products after discount is applied - in this phase the buy3get1 rule is considered null , so we are validating the prices with JB and MM
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

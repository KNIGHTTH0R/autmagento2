package com.workflows.frontend;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.HostDataGrabber;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class ValidationWorkflows {

	@Steps
	public CartWorkflows2 cartWorkflows2;
	@Steps
	public ShippingAndConfirmationWorkflows shippingAndConfirmationWorkflows;
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
	public void performCartValidations119Vat() {

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 20 SECTION");

		/*cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList25, DataGrabber.cartProductsWith25DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 20 SECTION -RECALCULATED");*/

		

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

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
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


		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList25, DataGrabber.cartProductsWith25DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION -RECALCULATED");

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

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsTotals() {

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscount("CART TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");
	}
	
	
	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCheckoutValidationsTotals() {

		checkoutValidationSteps.verifySuccessMessage();

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWithDiscountRuleActive() {

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50DiscountRule, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25DiscountRule, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketingDiscountRule, DataGrabber.cartMarketingMaterialsProducts);
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

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performSimpleCartValidations() {

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketing, DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.allProductsList, DataGrabber.shippingProducts);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.allProductsList, DataGrabber.confirmationProducts);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscountNoMarketing("CART TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.shippingTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotals, CartCalculator.shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsBu3Get1Rule() {

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productList25AndMmWithBuy3Get1Applied, DataGrabber.cartProductsList25AndMm);
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

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsBuy3Get1RuleJbAndMbApplied() {

//		checkoutValidationSteps.verifySuccessMessage();

		// validations before JB and MM Discounts - in this phase ,on products
		// is applied buy3Get1 rule, so we are validating those prices
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows2.setValidateProductsModels(CartCalculator.productList25AndMmWithBuy3Get1Applied, DataGrabber.cartProductsList25AndMm);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

		// validations products after discount is applied - in this phase the
		// buy3get1 rule is considered null , so we are validating the prices
		// with JB and MM
		cartWorkflows2.setValidateProductsModels(CartCalculator.calculatedProductsList50, DataGrabber.cartProductsWith50DiscountDiscounted);
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

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(billingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");

		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel, CartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");
	}

	public void performCartValidationsTP() {

		checkoutValidationSteps.verifySuccessMessage();
//      cart  validation 
		System.out.println("lista produse "+CartCalculator.productsList50);
		System.out.println("lista grabbed produse " +DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");
		
		

		System.out.println("lista produse 25 "+CartCalculator.productsList25);
		System.out.println("cartProductsWith25Discount " +DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");
//
		
		
		System.out.println("productsListMarketing "+CartCalculator.productsListMarketing);
		System.out.println("cartMarketingMaterialsProducts " +DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketing, DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

		
		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscount("CART TOTALS");
		

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp0,DataGrabber.grabbedSFMShippingProductsListTp0);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP0");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp1,DataGrabber.grabbedSFMShippingProductsListTp1);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP1");
		
		if(!CartCalculator.productsListTp2.isEmpty()){
			shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp2,DataGrabber.grabbedSFMShippingProductsListTp2);
			shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP2");
		}
		
		
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp0,DataGrabber.grabbedSFMConfirmationProductsListTp0);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP0");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp1,DataGrabber.grabbedSFMConfirmationProductsListTp1);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP1");
		
		if(!CartCalculator.productsListTp2.isEmpty()){
			shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp2,DataGrabber.grabbedSFMConfirmationProductsListTp2);
			shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP2");
		}
	
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp0, CartCalculator.shippingCalculatedModeTP0);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP0");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp1,
				CartCalculator.shippingCalculatedModeTP1);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP1");
		
		
		if(!CartCalculator.productsListTp2.isEmpty()){
			shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp2,
					CartCalculator.shippingCalculatedModeTP2);
			shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP2");
		}
		
		
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp0, CartCalculator.shippingCalculatedModeTP0);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP0");
		
		
		System.out.println("Step 1 confirmationTotalsTp1"+ DataGrabber.confirmationTotalsTp1);
		System.out.println("Step 2 shippingCalculatedModeTP1"+ CartCalculator.shippingCalculatedModeTP1);
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp1, CartCalculator.shippingCalculatedModeTP1);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP1");
		
		if(!CartCalculator.productsListTp2.isEmpty()){
			shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp2, CartCalculator.shippingCalculatedModeTP2);
			shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP2");
		}
		
		
		if(!CartCalculator.productsListTp2.isEmpty()){
			adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel,
					GeneralCartCalculations.calculateAdyenTotal(CartCalculator.shippingCalculatedModeTP0,
							CartCalculator.shippingCalculatedModeTP1, CartCalculator.shippingCalculatedModeTP2));
		}else{
			adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel,
					GeneralCartCalculations.calculateAdyenTotal(CartCalculator.shippingCalculatedModeTP0,
							CartCalculator.shippingCalculatedModeTP1));
		}
	
		
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
		

	}

	public void performCartValidationsZeroAmountTP() {
		// TODO Auto-generated method stub
		checkoutValidationSteps.verifySuccessMessage();
//      cart  validation 
		System.out.println("lista produse "+CartCalculator.productsList50);
		System.out.println("lista grabbed produse " +DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList50, DataGrabber.cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");
		
		

		System.out.println("lista produse 25 "+CartCalculator.productsList25);
		System.out.println("cartProductsWith25Discount " +DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsList25, DataGrabber.cartProductsWith20Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");
//
		
		
		System.out.println("productsListMarketing "+CartCalculator.productsListMarketing);
		System.out.println("cartMarketingMaterialsProducts " +DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.setValidateProductsModels(CartCalculator.productsListMarketing, DataGrabber.cartMarketingMaterialsProducts);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

		
		cartWorkflows2.setVerifyTotalsDiscount(DataGrabber.cartTotals, CartCalculator.calculatedTotalsDiscounts);
		cartWorkflows2.verifyTotalsDiscount("CART TOTALS");
		

		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp0,DataGrabber.grabbedSFMShippingProductsListTp0);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP0");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp1,DataGrabber.grabbedSFMShippingProductsListTp1);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP1");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp2,DataGrabber.grabbedSFMShippingProductsListTp2);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP2");
		
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp0,DataGrabber.grabbedSFMConfirmationProductsListTp0);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP0");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp1,DataGrabber.grabbedSFMConfirmationProductsListTp1);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP1");
		
		shippingAndConfirmationWorkflows.setValidateProductsModels(CartCalculator.productsListTp2,DataGrabber.grabbedSFMConfirmationProductsListTp2);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP2");
		
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp0, CartCalculator.shippingCalculatedModeTP0);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP0");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp1,
				CartCalculator.shippingCalculatedModeTP1);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP1");
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.sfmShippingTotalsTp2,
				CartCalculator.shippingCalculatedModeTP2);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP2");
		
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp0, CartCalculator.shippingCalculatedModeTP0);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP0");
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp1, CartCalculator.shippingCalculatedModeTP1);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP1");
		
		shippingAndConfirmationWorkflows.setVerifyShippingTotals(DataGrabber.confirmationTotalsTp2, CartCalculator.shippingCalculatedModeTP2);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP2");
		
		
//		adyenWorkflows.setVerifyAdyenTotals(DataGrabber.orderModel,
//				GeneralCartCalculations.calculateAdyenTotal(CartCalculator.shippingCalculatedModeTP0,
//						CartCalculator.shippingCalculatedModeTP1, CartCalculator.shippingCalculatedModeTP2));
//		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

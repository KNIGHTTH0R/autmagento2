package com.workflows.frontend.partyHost;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.HostDataGrabber;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.AdyenWorkflows;

public class HostCartValidationWorkflows {
	@Steps
	public HostCartWorkflows hostCartWorkflows;
	@Steps
	public HostShippingAndConfirmationWorkflows hostShippingAndConfirmationWorkflows;
	@Steps
	public AdyenWorkflows adyenWorkflows;
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

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
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

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyCartTotals("CART TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCheckoutValidations() {

		checkoutValidationSteps.verifySuccessMessage();

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

	}
	
	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performHostCheckoutValidations() {

		checkoutValidationSteps.verifySuccessMessage();

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWithVoucherDiscount(boolean shouldBeVisible) {

//		checkoutValidationSteps.verifySuccessMessage();

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListwithVoucher,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS", shouldBeVisible);

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWith40DiscountAndJbAndBuy3Get1() {

//		checkoutValidationSteps.verifySuccessMessage();

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListWithBuy3Get1Applied,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40JbAndBuy3Get1Discount("CART TOTALS WITH 40% AND JB APPLIED");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performCartValidationsWithBuy3Get1() {

//		checkoutValidationSteps.verifySuccessMessage();

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListWithBuy3Get1Applied,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostShippingProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostConfirmationProductsList);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsWithBuy3Get1Discount("CART TOTALS WITH 3+1 ACTIVE");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotals,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				HostCartCalculator.shippingCalculatedModel.getTotalAmount());
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performTpCartValidationsWithVoucher() {

//		checkoutValidationSteps.verifySuccessMessage();

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListwithVoucher,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp0,
				HostDataGrabber.grabbedHostShippingProductsListTp0);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp1,
				HostDataGrabber.grabbedHostShippingProductsListTp1);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP1");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp2,
				HostDataGrabber.grabbedHostShippingProductsListTp2);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP2");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp0,
				HostDataGrabber.grabbedHostConfirmationProductsListTp0);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp1,
				HostDataGrabber.grabbedHostConfirmationProductsListTp1);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP1");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp2,
				HostDataGrabber.grabbedHostConfirmationProductsListTp2);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP2");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWithVoucher("CART TOTALS", true);

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp0,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp1,
				HostCartCalculator.shippingCalculatedModelTp1);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP1");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp2,
				HostCartCalculator.shippingCalculatedModelTp2);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP2");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp0,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp1,
				HostCartCalculator.shippingCalculatedModelTp1);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP1");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp2,
				HostCartCalculator.shippingCalculatedModelTp2);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP2");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				GeneralCartCalculations.calculateAdyenTotal(HostCartCalculator.shippingCalculatedModel,
						HostCartCalculator.shippingCalculatedModelTp1, HostCartCalculator.shippingCalculatedModelTp2));
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

	@StepGroup
	@Screenshots(onlyOnFailures = true)
	public void performTpCartValidationsWithJbAndForthyDiscount() {

//		checkoutValidationSteps.verifySuccessMessage();

		hostCartWorkflows.setValidateProductsModels(HostCartCalculator.allProductsList,
				HostDataGrabber.grabbedHostCartProductsList);
		hostCartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp0,
				HostDataGrabber.grabbedHostShippingProductsListTp0);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp1,
				HostDataGrabber.grabbedHostShippingProductsListTp1);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP1");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp2,
				HostDataGrabber.grabbedHostShippingProductsListTp2);
		hostShippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION TP2");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp0,
				HostDataGrabber.grabbedHostConfirmationProductsListTp0);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp1,
				HostDataGrabber.grabbedHostConfirmationProductsListTp1);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP1");

		hostShippingAndConfirmationWorkflows.setValidateProductsModels(HostCartCalculator.allProductsListTp2,
				HostDataGrabber.grabbedHostConfirmationProductsListTp2);
		hostShippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION TP2");

		hostCartWorkflows.setVerifyTotalsDiscount(HostDataGrabber.hostGrabbedCartTotals,
				HostCartCalculator.calculatedTotalsDiscounts);
		hostCartWorkflows.verifyTotalsDiscountWith40AndJbDiscount("CART TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp0,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp1,
				HostCartCalculator.shippingCalculatedModelTp1);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS TP1");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostShippingTotalsTp2,
				HostCartCalculator.shippingCalculatedModelTp2);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALSTP2");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp0,
				HostCartCalculator.shippingCalculatedModel);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp1,
				HostCartCalculator.shippingCalculatedModelTp1);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP1");

		hostShippingAndConfirmationWorkflows.setVerifyShippingTotals(HostDataGrabber.hostConfirmationTotalsTp2,
				HostCartCalculator.shippingCalculatedModelTp2);
		hostShippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS TP2");

		adyenWorkflows.setVerifyAdyenTotals(HostDataGrabber.orderModel,
				GeneralCartCalculations.calculateAdyenTotal(HostCartCalculator.shippingCalculatedModel,
						HostCartCalculator.shippingCalculatedModelTp1, HostCartCalculator.shippingCalculatedModelTp2));
		adyenWorkflows.veryfyAdyenTotals("ADYEN TOTAL");

		AddressWorkflows.setBillingAddressModels(billingAddress, DataGrabber.grabbedBillingAddress);
		AddressWorkflows.validateBillingAddress("BILLING ADDRESS");

		AddressWorkflows.setShippingAddressModels(shippingAddress, DataGrabber.grabbedShippingAddress);
		AddressWorkflows.validateShippingAddress("SHIPPING ADDRESS");
	}

}

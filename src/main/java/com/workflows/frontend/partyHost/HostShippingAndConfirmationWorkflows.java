package com.workflows.frontend.partyHost;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.ShippingModel;

public class HostShippingAndConfirmationWorkflows {
	
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	
	private static List<HostBasicProductModel> basicProductsList = new ArrayList<HostBasicProductModel>();
	private static List<HostCartProductModel> cartProductsList = new ArrayList<HostCartProductModel>();
	
	public void setValidateProductsModels(List<HostBasicProductModel> basicProductsList, List<HostCartProductModel> cartProductsList) {
		HostShippingAndConfirmationWorkflows.basicProductsList = basicProductsList;
		HostShippingAndConfirmationWorkflows.cartProductsList = cartProductsList;
	}
	
	@Step
	public void validateProducts(String message) {
		System.out.println(" ");
		System.out.println(message);
		
		
		for (HostBasicProductModel productNow : basicProductsList) {
			System.out.println("productNow: "+productNow);
			HostCartProductModel compare = findProduct(productNow.getProdCode(),productNow.getQuantity(), cartProductsList);
			System.out.println("compare: "+compare);
			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getUnitPrice(), compare.getUnitPrice());
				checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
				
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = cartProductsList.indexOf(compare);
			if (index > -1) {
				cartProductsList.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(cartProductsList.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", basicProductsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", cartProductsList.size() == 0);

	}
	
	public HostCartProductModel findProduct(String productCode,String quantity,  List<HostCartProductModel> cartProducts) {
		HostCartProductModel result = new HostCartProductModel();
		theFor: for (HostCartProductModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode) && cartProductModel.getQuantity().contentEquals(quantity)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}
	
	private ShippingModel shippingGrabbedModel = new ShippingModel();;
	private ShippingModel shippingCalculatedModel = new ShippingModel();

	public void setVerifyShippingTotals(ShippingModel shippingTotals, ShippingModel shippingCalculatedModel) {
		this.shippingCalculatedModel = shippingCalculatedModel;
		this.shippingGrabbedModel = shippingTotals;
	}
	
	@Step
	public void verifyShippingTotals(String string) {
		System.out.println(" ");
		System.out.println(string);
		verifyTotalAmount(shippingGrabbedModel.getTotalAmount(), shippingCalculatedModel.getTotalAmount());
		verifyShippingPrice(shippingGrabbedModel.getShippingPrice(), shippingCalculatedModel.getShippingPrice());
		verifyDiscountsPrice(shippingGrabbedModel.getDiscountPrice(), shippingCalculatedModel.getDiscountPrice());
		verifySubTotals(shippingGrabbedModel.getSubTotal(), shippingCalculatedModel.getSubTotal());
	}
	


	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}


	@Step
	public void verifySubTotals(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Sub Totals dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));

	}


	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Shipping Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyDiscountsPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Discounts Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));

	}

}

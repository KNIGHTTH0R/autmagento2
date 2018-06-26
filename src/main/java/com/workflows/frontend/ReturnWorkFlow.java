package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.data.frontend.ReturnProductModel;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ReturnWorkFlow{

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private List<ReturnProductModel> returnProductsModelGrabbedList = new ArrayList<ReturnProductModel>();
	private List<ReturnProductModel> returnProductsModelCalculatedList = new ArrayList<ReturnProductModel>();
	
	

	public void setValidateReturnedProductsModels(List<ReturnProductModel> productsListCalculated, List<ReturnProductModel> productsListGrabbed) {
		this.returnProductsModelCalculatedList = productsListCalculated;
		this.returnProductsModelGrabbedList = productsListGrabbed;
	}

	@Step
	public void validateReturnedProducts(String message) {

		for (ReturnProductModel productNow : returnProductsModelCalculatedList) {
			ReturnProductModel compare = findReturnedProduct(productNow.getProdCode(),returnProductsModelGrabbedList);


			if (compare.getName() != null) {
				//checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateSize(productNow.getSize(), compare.getSize());
				checkoutValidationSteps.validateColour(productNow.getColour(), compare.getColour());
				checkoutValidationSteps.validateProductSku(productNow.getProdCode(), compare.getProdCode());
				checkoutValidationSteps.validateReason(productNow.getReason(), compare.getReason());
				checkoutValidationSteps.validateReturnedQty(productNow.getReturnedQty(), compare.getReturnedQty());
				checkoutValidationSteps.validateStatus(productNow.getStatus(), compare.getStatus());
				checkoutValidationSteps.validatePaidPrice(productNow.getPaidPrice(), compare.getPaidPrice());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = returnProductsModelGrabbedList.indexOf(compare);
			if (index > -1) {

				returnProductsModelGrabbedList.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(returnProductsModelGrabbedList.size() + " items remained");

			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", returnProductsModelCalculatedList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", returnProductsModelGrabbedList.size() == 0);

	}






	
	public ReturnProductModel findReturnedProduct(String productCode, List<ReturnProductModel> grabbedProducts) {
		ReturnProductModel result = new ReturnProductModel();
		theFor: for (ReturnProductModel product : grabbedProducts) {
			if (product.getProdCode().contains(productCode) ) {
				result = product;
				break theFor;
			}
		}
		return result;
	}

	
}

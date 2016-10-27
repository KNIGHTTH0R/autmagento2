package com.workflows.frontend.borrowCart;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.ShippingModel;

public class BorrowCartShippingAndConfirmationWorkflows {

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	@Steps
	public CustomVerification customVerification;

	private List<BorrowProductModel> borrowedBasicProductsList = new ArrayList<BorrowProductModel>();
	private List<BorrowedCartModel> borrowedCartProductsList = new ArrayList<BorrowedCartModel>();

	public void setValidateProductsModels(List<BorrowProductModel> borrowedBasicProductsList, List<BorrowedCartModel> borrowedCartProductsList) {
		this.borrowedBasicProductsList = borrowedBasicProductsList;
		this.borrowedCartProductsList = borrowedCartProductsList;
	}

	@Step
	public void validateProducts(String message) {

		for (BorrowProductModel productNow : borrowedBasicProductsList) {
			BorrowedCartModel compare = findProduct(productNow.getProdCode(), borrowedCartProductsList);

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getUnitPrice(), compare.getUnitPrice());

			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = borrowedCartProductsList.indexOf(compare);
			if (index > -1) {
				borrowedCartProductsList.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(borrowedCartProductsList.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", borrowedBasicProductsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", borrowedCartProductsList.size() == 0);

	}

	public BorrowedCartModel findProduct(String productCode, List<BorrowedCartModel> cartProducts) {
		BorrowedCartModel result = new BorrowedCartModel();
		theFor: for (BorrowedCartModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}

	private ShippingModel shippingGrabbedModel = new ShippingModel();
	private ShippingModel shippingCalculatedModel = new ShippingModel();

	public void setVerifyShippingTotals(ShippingModel shippingTotals, ShippingModel shippingCalculatedModel) {
		this.shippingCalculatedModel = shippingCalculatedModel;
		this.shippingGrabbedModel = shippingTotals;
	}

	@Step
	public void verifyShippingTotals(String string) {
		verifyTotalAmount(shippingGrabbedModel.getTotalAmount(), shippingCalculatedModel.getTotalAmount());
		verifyShippingPrice(shippingGrabbedModel.getShippingPrice(), shippingCalculatedModel.getShippingPrice());
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

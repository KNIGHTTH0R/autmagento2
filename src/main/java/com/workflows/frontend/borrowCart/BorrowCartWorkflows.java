package com.workflows.frontend.borrowCart;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.BorrowedCartModel;

public class BorrowCartWorkflows {

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
				checkoutValidationSteps.validateMatchFinalPrice(productNow.getFinalPrice(), compare.getFinalPrice());
				checkoutValidationSteps.validateIpPoints(productNow.getIpPoints(), compare.getIpPoints());

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

	private BorrowCartTotalsModel grabbedTotals = new BorrowCartTotalsModel();
	private BorrowCartCalcDetailsModel calculatedTotals = new BorrowCartCalcDetailsModel();

	public void setVerifyTotalsDiscount(BorrowCartTotalsModel discountTotals, BorrowCartCalcDetailsModel discountCalculationModel) {
		this.calculatedTotals = discountCalculationModel;
		this.grabbedTotals = discountTotals;

	}

	@StepGroup
	public void verifyTotals(String message) {
		verifySubTotals(grabbedTotals.getSubtotal(), calculatedTotals.getSubTotal());
		verifyTotalAmount(grabbedTotals.getTotalAmount(), calculatedTotals.getTotalAmount());
		verifyIP(grabbedTotals.getIpPoints(), calculatedTotals.getIpPoints());
	}

	@Step
	public void verifyIP(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: IP points dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}


	@Step
	public void verifySubTotals(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Sub Totals dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));

	}



}
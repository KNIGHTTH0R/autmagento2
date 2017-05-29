package com.workflows.backend.TermPurchase;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.tools.CustomVerification;
import com.tools.data.backend.TermPurchaseOrderModel;

public class TermPurcaseOrderValidationWorkflows {
	
	@Title("Validate order details in TP grid")
	@StepGroup
	public void verifyTermPurchaseOrderDetails(TermPurchaseOrderModel grabbedModel, TermPurchaseOrderModel expectedModel) {

		verifyIncrementId(grabbedModel.getIncrementId(), expectedModel.getIncrementId());
		verifyExecutionDate(grabbedModel.getExecutionDate(), expectedModel.getExecutionDate());
		verifyProductSku(grabbedModel.getProductSku(), expectedModel.getProductSku());
	
		verifyBoughtQty(grabbedModel.getBoughtQty(), expectedModel.getBoughtQty());
		verifyInStock(grabbedModel.getInStock(), expectedModel.getInStock());
		verifyIsDiscontinued(grabbedModel.getIsDiscontinued(), expectedModel.getIsDiscontinued());
		verifyProductQty(grabbedModel.getProductQty(), expectedModel.getProductQty());
		verifyMinimumQty(grabbedModel.getMinimumQty(), expectedModel.getMinimumQty());
		verifyEarliestAvailability(grabbedModel.getEarliestAv(), expectedModel.getEarliestAv());
		verifyScheduledPaymentStatus(grabbedModel.getScheduledPaymentStatus(), expectedModel.getScheduledPaymentStatus());
		verifyOrderStatus(grabbedModel.getOrderStatus(), expectedModel.getOrderStatus());
		verifyRecommandation(grabbedModel.getRecomandation(), expectedModel.getRecomandation());
		verifyReason(grabbedModel.getReason(), expectedModel.getReason());
	}

	@Step
	public void verifyIncrementId(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Increment id doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyExecutionDate(String grabbed, String expected) {
		CustomVerification.verifyTrue(
				"Failure: Execution date doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyProductSku(String grabbed, String expected) {
		System.out.println("sku grabbed "+grabbed);
		System.out.println("sku expected "+expected);
		CustomVerification.verifyTrue(
				"Failure: Product SKU doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyBoughtQty(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Bought qty doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contains(expected));
	}

	@Step
	public void verifyInStock(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: In stock value doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyIsDiscontinued(String grabbed, String expected) {
		CustomVerification.verifyTrue(
				"Failure: Is discontinued value doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyEarliestAvailability(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: earliest availability doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyMinimumQty(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Minimum qty doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}

	@Step
	public void verifyScheduledPaymentStatus(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Scheduled Payment status doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyProductQty(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Product qty doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyOrderStatus(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Order status doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyRecommandation(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Recommandation  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyReason(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Reason  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
}

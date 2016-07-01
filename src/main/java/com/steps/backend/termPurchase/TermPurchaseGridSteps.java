package com.steps.backend.termPurchase;

import java.text.ParseException;

import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class TermPurchaseGridSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void searchForOrder(String incrementId) {
		termPurchaseGridPage().inputOderId(incrementId);
		termPurchaseGridPage().clickOnSearch();
	}

	@Step
	public void checkOrderIsNotPresentInGrid(String incrementId) {
		termPurchaseGridPage().verifyOrderIsNotInTheGrid(incrementId);
	}

	@Step
	public void selectOrder(String incrementId) {
		termPurchaseGridPage().selectOrder(incrementId);
	}

	@Step
	public TermPurchaseOrderModel grabOrderDetails(String incrementId) throws ParseException {
		return termPurchaseGridPage().grabOrderDetails(incrementId);
	}

	@Step
	public TermPurchaseOrderModel grabOrderDetailsAutomatedCron(String incrementId) throws ParseException {
		return termPurchaseGridPage().grabOrderDetailsAutomatedCron(incrementId);
	}

	@Step
	public void selectAction(String action) {
		termPurchaseGridPage().selectAction(action);
	}

	@Step
	public void validateMessage(String expectedMessage) {
		termPurchaseGridPage().validateMessage(expectedMessage);
	}

	@Step
	public void postponeOrder(String incrementId) {
		termPurchaseGridPage().selectOrder(incrementId);
		termPurchaseGridPage().selectAction(ConfigConstants.POSTPONE);
		termPurchaseGridPage().selectPostponePeriod(ConfigConstants.POSTPONE_ONE_WEEK);
		termPurchaseGridPage().submitMassAction();
	}

	@Step
	public void cancelOrder(String incrementId) {
		termPurchaseGridPage().selectOrder(incrementId);
		termPurchaseGridPage().selectAction(ConfigConstants.CANCEL);
		termPurchaseGridPage().submitMassAction();
	}

	@Step
	public void releaseOrder(String incrementId) {
		termPurchaseGridPage().selectOrder(incrementId);
		termPurchaseGridPage().selectAction(ConfigConstants.RELEASE);
		termPurchaseGridPage().submitMassAction();
	}

}

package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.ConfigConstants;
import com.tools.requirements.AbstractSteps;

public class StylecoachListBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void reassignCustomersToAnotherStylecoach(String initialStylecoachName, String finalStylecoachName) {
		inputEmailFilter(initialStylecoachName);
		searchForStylist();
		checkDesiredStylecoach(initialStylecoachName);
		selectAction();
		selectStylecoachToReassignContactsTo(finalStylecoachName);
		submitReassignStylecoach();
	}

	@Step
	public void reassignCustomers(String initialStylecoachName) {
		inputEmailFilter(initialStylecoachName);
		searchForStylist();
		checkDesiredStylecoach(initialStylecoachName);
		selectAction();
		submitReassignStylecoach();
	}

	@Step
	public void verifyStylecoachEmailAndStatus(String stylecoachEmail) {
		stylecoachListBackendPage().inputEmailFilter(stylecoachEmail);
		stylecoachListBackendPage().clickOnSearch();
		stylecoachListBackendPage().verifyStylecoachEmailAndStatus(ConfigConstants.REASSIGNED_PREFIX + stylecoachEmail);
	}

	@Step
	public void openContactDetails(String stylecoachEmail) {
		contactListBackendPage().inputEmailFilter(stylecoachEmail);
		contactListBackendPage().clickOnSearch();
		contactListBackendPage().openContactDetails();
	}

	@Step
	public void searchForStylist(String stylecoachEmail) {
		stylecoachListBackendPage().inputEmailFilter(stylecoachEmail);
		stylecoachListBackendPage().clickOnSearch();
	}

	@Step
	public void openStylistDetails() {
		stylecoachListBackendPage().openStylistDetails();
	}

	@Step
	public void inputEmailFilter(String email) {
		stylecoachListBackendPage().inputEmailFilter(email);
	}

	@Step
	public void searchForStylist() {
		stylecoachListBackendPage().clickOnSearch();
	}

	@Step
	public void checkDesiredStylecoach(String email) {
		stylecoachListBackendPage().checkDesiredStylecoach(email);
	}

	@Step
	public void selectAction() {
		stylecoachListBackendPage().selectActionList();
	}

	@Step
	public void selectStylecoachToReassignContactsTo(String email) {
		stylecoachListBackendPage().selectStylecoachToReassignContactsTo(email);
	}

	@Step
	public void submitReassignStylecoach() {
		stylecoachListBackendPage().clickOnsubmitReassignStylecoach();
	}
}

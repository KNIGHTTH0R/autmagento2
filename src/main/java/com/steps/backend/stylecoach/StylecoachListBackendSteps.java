package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.ConfigConstants;
import com.tools.requirements.AbstractSteps;

public class StylecoachListBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void reassignCustomersToAnotherStylecoach(String initialStylecoachName, String finalStylecoachName) {
		stylecoachListBackendPage().inputEmailFilter(initialStylecoachName);
		stylecoachListBackendPage().clickOnSearch();
		stylecoachListBackendPage().checkDesiredStylecoach(initialStylecoachName);
		stylecoachListBackendPage().selectActionList();
		stylecoachListBackendPage().selectStylecoachToReassignContactsTo(finalStylecoachName);
		stylecoachListBackendPage().clickOnsubmitReassignStylecoach();
	}

	@Step
	public void verifyStylecoachEmailAndStatus(String stylecoachEmail) {
		stylecoachListBackendPage().inputEmailFilter(stylecoachEmail);
		stylecoachListBackendPage().clickOnSearch();
		stylecoachListBackendPage().verifyStylecoachEmailAndStatus(ConfigConstants.REASSIGNED_PREFIX + stylecoachEmail);
	}
}

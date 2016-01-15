package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class StylecoachDetailsBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void addNewActivatedAtDate(String date) {
		stylecoachDetailsBackendPage().inputActivatedAtDate(date);
		stylecoachDetailsBackendPage().saveStylecoach();
	}

	@Step
	public void saveStylecoach() {
		stylecoachDetailsBackendPage().saveStylecoach();
	}

}

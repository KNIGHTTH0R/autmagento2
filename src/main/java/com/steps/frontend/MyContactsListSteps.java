package com.steps.frontend;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MyContactsListSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void verifyThatContactIsInTheList(String contactName) {
		myContactsListPage().verifyThatContactIsInTheList(contactName);
	}

}

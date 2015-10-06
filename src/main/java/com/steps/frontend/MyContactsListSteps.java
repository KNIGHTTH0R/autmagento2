package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class MyContactsListSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

//	@Step
//	public void verifyThatContactIsInTheList(String contactName) {
//		myContactsListPage().verifyThatContactIsInTheList(contactName);
//	}

//	@Step
//	public void verifyThatContactMatchesAllTerms(String... terms) {
//		myContactsListPage().verifyThatContactMatchesAllTerms(terms);
//	}
	@Step
	public void verifyUnicAndOpenContactDetails(String... terms) {
		myContactsListPage().verifyUnicAndOpenContactDetails(terms);
	}

//	@Step
//	public void verifyThatContactIsUniqueInStylecoachList(String contactName) {
//		myContactsListPage().inputSearchTerm(contactName);
//		myContactsListPage().submitContactSearch();
//		myContactsListPage().verifyThatContactIsUniqueInStylecoachList();
//
//	}

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class MyBusinessSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void verifyThatNumberOfLinksAreEqualTo(int expectedNoOfLinks) {
		myBusinessPage().verifyThatNumberOfLinksAreEqualTo(expectedNoOfLinks);
	}

	@Step
	public void accessKoboCart() {
		myBusinessPage().accessKoboCart();
	}

	@Step
	public void cancelSubstription() {
		myBusinessPage().cancelSubstription();
	}

	@Step
	public void verifyThatKoboCodeWasGeneratetInLoungePage() {
		myBusinessPage().verifyThatKoboCodeWasGeneratetInLoungePage();
	}

	@Step
	public void verifyKoboSectionContainsText(String text) {
		myBusinessPage().verifyKoboSectionContainsText(text);
	}

}

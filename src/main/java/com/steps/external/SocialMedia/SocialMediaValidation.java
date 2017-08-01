package com.steps.external.SocialMedia;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class SocialMediaValidation extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void validateName(String name, String expected) {
		CustomVerification.verifyTrue("The name is not displayed correctly", name.contentEquals(expected));
	}

	@Step
	public void validateFriendName(String friendName, String expected) {
		CustomVerification.verifyTrue("The Frind name is not displayed correctly", friendName.contentEquals(expected));
	}



}

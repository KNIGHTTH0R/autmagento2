package com.steps.external.unbounce;

import org.junit.Assert;

import com.tools.env.constants.ConfigConstants;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class UnbounceRegSuccessSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void verifySuccessMessage() {
		Assert.assertTrue("The message is not the expected one !!!",
				unbounceRegSuccesPage().getSuccessMessage().contentEquals(ConfigConstants.UNBOUNCE_SUCCESS_MESSAGE));
	}

}

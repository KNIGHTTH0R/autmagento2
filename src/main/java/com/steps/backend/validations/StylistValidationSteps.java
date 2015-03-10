package com.steps.backend.validations;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.requirements.AbstractSteps;

public class StylistValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	@Step
	public void validateProducts(String confirmationstatus, String expectedconfirmationstatus) {
		Assert.assertTrue("The confirmation status is not the expected one ", confirmationstatus.contentEquals(expectedconfirmationstatus));
	}

}

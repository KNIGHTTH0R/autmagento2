package com.steps;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;

public class EmailSteps extends AbstractSteps {

	private static final long serialVersionUID = 7847714736572013908L;

	@Step
	public void printEmailContent(String email) {
		System.out.println("message: " + email);
	}

	@Step
	public void validateEmailContent(String orderId, String message) {
		Assert.assertTrue("Failure: Message does not contain the orderID", message.contains(orderId));
	}
}

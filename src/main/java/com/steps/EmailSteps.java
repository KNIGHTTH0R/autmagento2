package com.steps;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;

public class EmailSteps extends AbstractSteps{

	private static final long serialVersionUID = 7847714736572013908L;

	@Step
	public void printEmailContent(String email){
		System.out.println("message: " + email);
	}
}

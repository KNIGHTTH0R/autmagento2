package com.steps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class EmailSteps extends AbstractSteps {

	private static final long serialVersionUID = 7847714736572013908L;

	@Step
	public void printEmailContent(String email) {
		System.out.println("message: " + email);
	}

	@Step
	public void validateEmailContent(String orderId, String message) {
		Assert.assertTrue("Failure: Message does not contain the total price", message.contains(orderId));
	}
	@Step
	public void validateEmail(String expected, String message) {
		Assert.assertTrue("Failure: Message does not contain the expected value: " + expected, message.contains(expected));
	}
	
	@Step
	public void validateURL(String URL, String context){
		Assert.assertTrue("Failure: URL does not contain the context provided", URL.contains(context));
	}

	public String grabConfirmationLink(String message) {
		String resultURL = null; 
		String[] lines = message.split("\n");
		
		for (String stringNow : lines) {
			if(stringNow.contains("Bestätigungslink")){
				String[] subLines = stringNow.split("\"");
				resultURL = subLines[3];
				break;
			}
		}
		
		return resultURL;
	}
}

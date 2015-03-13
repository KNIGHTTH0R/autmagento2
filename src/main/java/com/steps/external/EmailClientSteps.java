package com.steps.external;

import net.thucydides.core.annotations.Step;

import com.tools.Constants;
import com.tools.requirements.AbstractSteps;

public class EmailClientSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void openMailinator() {
		waitABit(2000);
		getDriver().get(Constants.URL_WEB_MAIL);
	}

	@Step
	public String grabEmail(String email) {

//		waitABit(5000);
		String url = Constants.URL_WEB_MAIL + "inbox.jsp?to=" + email;

		System.out.println("URL : " + url);
		getDriver().get(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail();

		if (welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();

			waitABit(2000);
			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}

		waitABit(6000);
		return welcomeMessage;

	}
	@Step
	public String grabEmail(String email,String title) {
		
//		waitABit(5000);
		String url = Constants.URL_WEB_MAIL + "inbox.jsp?to=" + email;
		
		System.out.println("URL : " + url);
		getDriver().get(url);
		
		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);
		
		if (welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();
			
			waitABit(2000);
			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}
		
		waitABit(6000);
		return welcomeMessage;
		
	}
	@Step
	public void validateThatEmailIsReceived(String email,String title) {
		
//		waitABit(5000);
		String url = Constants.URL_WEB_MAIL + "inbox.jsp?to=" + email;
		
		System.out.println("URL : " + url);
		getDriver().get(url);
		
		waitABit(5000);
		mailinatorPage().grabEmail(title);
		
//		if (welcomeMessage.isEmpty()) {
//			String confirmLink = mailinatorPage().confirmEmail();
//			
//			waitABit(2000);
//			getDriver().get(confirmLink);
//			welcomeMessage = confirmLink;
//		}
//		
//		waitABit(6000);
//		return welcomeMessage;
		
	}
}

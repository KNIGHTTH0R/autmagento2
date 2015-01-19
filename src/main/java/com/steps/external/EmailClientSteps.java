package com.steps.external;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.Constants;

public class EmailClientSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void openMailinator() {
		waitABit(10000);

		mailinatorHomePage().open();
	}

	@Step
	public String grabEmail(String email) {

		waitABit(5000);

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
}

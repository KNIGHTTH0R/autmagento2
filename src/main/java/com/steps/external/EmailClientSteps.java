package com.steps.external;

import com.pages.external.YopmailPage;
import com.tools.env.constants.UrlConstants;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

import net.thucydides.core.annotations.Step;

public class EmailClientSteps extends AbstractSteps {
	
//	MailinatorPage mailPage;
	YopmailPage mailPage;

	private static final long serialVersionUID = 1L;

	@Step
	public String grabEmail(String email, String title) {

		mailPage.openEmail(email,title);
		navigate(mailPage.getConfirmationEmail());

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}

	@Step
	public String grabConfirmationLinkFromEmail(String email, String title) {
		
		mailPage.openEmail(email,title);
		
		return mailPage.getConfirmationEmail();
	}

	@Step
	public String grabEmailCoupon(String email, String title) {

		mailPage.openEmail(email,title);

		return mailPage.grabCouponCode();
	}

	@Step
	public void validateThatEmailIsReceived(String email, String title) {

		mailPage.openEmail(email,title);

	}

}

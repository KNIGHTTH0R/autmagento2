package com.steps.external;

import com.pages.external.YopmailPage;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

import net.thucydides.core.annotations.Step;

public class EmailClientSteps extends AbstractSteps {

//	MailinatorPage mailPage;
	 YopmailPage mailPage;

	private static final long serialVersionUID = 1L;

	@Step
	public String confirmAccount(String email, String title) {

		mailPage.openEmail(email, title);
		navigate(mailPage.getConfirmationLink());

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}

	@Step
	public String registerFromLink(String email, String title) {

		mailPage.openEmail(email, title);
		navigate(mailPage.getRegisterLink());

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}

	@Step
	public String grabConfirmationLinkFromEmail(String email, String title) {

		mailPage.openEmail(email, title);
		System.out.println("foo -thay");
		return mailPage.getConfirmationLink();
	}

	@Step
	public String grabEmailCoupon(String email, String title) {

		mailPage.openEmail(email, title);

		return mailPage.grabCouponCode();
	}

	@Step
	public void validateThatEmailIsReceived(String email, String title) {

		mailPage.openEmail(email, title);

	}

}

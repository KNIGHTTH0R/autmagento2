package com.steps.external;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.UrlConstants;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

public class EmailClientSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void openMailinator() {
		waitABit(2000);
		navigate(UrlConstants.URL_WEB_MAIL);
//		getDriver().get(UrlConstants.URL_WEB_MAIL);
	}

	@Step
	public String grabEmail(String email) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail();

		if (welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();

			waitABit(2000);
			navigate(confirmLink);
//			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}

		waitABit(6000);
		return welcomeMessage;

	}

	@Step
	public String grabEmail(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);

		if (welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();
			waitABit(2000);
			navigate(confirmLink);
//			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}
		waitABit(6000);

		return DateUtils.getCurrentDate("MM/dd/YYYY");

	}

	@Step
	public String grabConfirmationLinkFromEmail(String email, String title) {
		waitABit(5000);
		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);

		if (welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();
			waitABit(2000);
			welcomeMessage = confirmLink;
		}

		waitABit(6000);
		return welcomeMessage;

	}

	@Step
	public String confirmEmail(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);

		if (welcomeMessage.isEmpty()) {
			mailinatorPage().clickConfirmEmail();
		}

		waitABit(6000);
		return welcomeMessage;

	}

	@Step
	public String grabEmailCoupon(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;

		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String couponCode = mailinatorPage().grabEmail(title);

		couponCode = mailinatorPage().grabCouponCode();
		System.out.println(couponCode);
		waitABit(2000);

		return couponCode;

	}

	@Step
	public void validateThatEmailIsReceived(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		mailinatorPage().grabEmail(title);

	}

	@Step
	public String validateEmailContent(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		return mailinatorPage().grabEmail(title);

	}

	@Step
	public String validateThatEmailIsReceivedAndConfirm(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);

		if (!welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().confirmEmail();

			waitABit(2000);
			navigate(confirmLink);
//			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}

		waitABit(6000);
		return welcomeMessage;
	}

	@Step
	public String validateThatEmailIsReceivedAndClickRegister(String email, String title) {

		String url = UrlConstants.URL_WEB_MAIL + "inbox2.jsp?to=" + email;
		System.out.println("URL : " + url);
		navigate(url);

		waitABit(5000);
		String welcomeMessage = mailinatorPage().grabEmail(title);

		if (!welcomeMessage.isEmpty()) {
			String confirmLink = mailinatorPage().registerFromEmail();

			waitABit(2000);
			navigate(confirmLink);
//			getDriver().get(confirmLink);
			welcomeMessage = confirmLink;
		}

		waitABit(6000);

		return welcomeMessage;
	}
}

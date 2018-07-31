package com.pages.frontend.profile;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class MyAccountPage extends AbstractPage {

	@FindBy(css = "#maincontent > div.page.messages > div:nth-child(2) > div > div > div")
	private WebElement registrationMessage;
	
	@FindBy(css = ".box.box-newsletter p")
	private WebElement newsletterSubscriptionText;
	
	
	@FindBy(css = ".box.box-information p ")
	private WebElement contactinformationSection;
	
	
	public void checkNewsletterSubscriptionText(){
		element(newsletterSubscriptionText).waitUntilVisible();
		Assert.assertTrue("Failure: Message newsletter subscription text was not found. ",
				newsletterSubscriptionText.getText().contains(ContextConstants.NEWSLETTER_SUBSCRIPTION_INFORMATION_TEXT));
	}

	public void verifyLink() {
		CustomVerification.verifyTrue("Failure: URL not redirected to success page. ",
				getDriver().getCurrentUrl().contains("customer/account"));
	}

	public void verifyText() {
		element(registrationMessage).waitUntilVisible();
		Assert.assertTrue("Failure: Message success notification text was not found. ",
				registrationMessage.getText().contains(ContextConstants.CREATE_ACCOUNT_SUCCESS_MESSAGE));
	}

	public void verifyFirstName(String firstname) {
		element(contactinformationSection).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: Contact information section does not contains correct first name, expected:" + firstname,
				contactinformationSection.getText().contains(firstname));
	}

	public void verifyLastName(String lastName) {
		element(contactinformationSection).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: Contact information section does not contains correct last name, expected:" + lastName,
				contactinformationSection.getText().contains(lastName));

	}

	public void verifyEmailAddress(String emailAddress) {
		element(contactinformationSection).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: Contact information section does not contains correct last name, expected:" + emailAddress,
				contactinformationSection.getText().contains(emailAddress));
	}

}

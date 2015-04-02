package com.tests.uss10.us10004;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.persistance.MongoReader;

@WithTag(name = "US10004", type = "external")
//@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class US10004CheckInviteEmailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;
	
	public String stylistEmail;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US10004InviteRegisteredGuestUpdateAndDeletePartyTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US10004InviteRegisteredGuestUpdateAndDeletePartyTest").get(0).getEmailName();
			System.out.println(stylistEmail);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us10004CheckInviteEmailTest() {
		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + Constants.WEB_MAIL, ""), Constants.INVITE_EMAIL_SUBJECT);

	}

}
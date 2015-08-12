package com.tests.uss12.uss12001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "external")
@Story(Application.KoboSubscription.class)
@RunWith(ThucydidesRunner.class)
public class US12001VoucherRunOutNotificationEmailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	public String stylistEmail;
	public CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us12001VoucherRunOutNotificationEmailTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistRegistrationData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""), "");

	}

}

package com.tests.us7.us7008;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.8 Kobo Registration on Master Not Pref Country Test ", type = "Scenarios")
@Story(Application.KoboRegistration.US7_8.class)
@RunWith(SerenityRunner.class)
public class US7008CheckReceivedEmailsTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		stylistEmail = MongoReader.grabCustomerFormModels("US7008KoboRegOnMasterNotPrefCountryTest").get(0).getEmailName();
		orderModel = MongoReader.grabOrderModels("US7008KoboRegOnMasterNotPrefCountryTest").get(0);

	}

	@Test
	public void us7008CheckReceivedEmailsTest() {

		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.WELCOME_MAIL_SUBJECT);
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.NEWSLETTER_MAIL_SUBJECT);
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), orderModel.getOrderId());

	}

}

package com.tests.us7.uss70010;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.10 Kobo Registration On Voucher Owner Context Test ", type = "Scenarios")
@Story(Application.KoboRegistration.US7_10.class)
@RunWith(ThucydidesRunner.class)
public class US70010CheckReceivedEmailsTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {

		stylistEmail = MongoReader.grabCustomerFormModels("US70010KoboRegOnVoucherOwnerContextTest").get(0).getEmailName();
		orderModel = MongoReader.grabOrderModels("US70010KoboRegOnVoucherOwnerContextTest").get(0);

	}

	@Test
	public void us70010CheckReceivedEmailsTest() {

		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), orderModel.getOrderId());

		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.WELCOME_MAIL_SUBJECT);

		emailClientSteps.openMailinator();
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.NEWSLETTER_MAIL_SUBJECT);

	}

}

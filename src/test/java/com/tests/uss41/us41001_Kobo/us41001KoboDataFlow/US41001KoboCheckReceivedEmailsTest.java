package com.tests.uss41.us41001_Kobo.us41001KoboDataFlow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US41001KoboCheckReceivedEmailsTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
//	private OrderModel orderModel;
	@Before
	public void setUp() throws Exception {
		
		//orderModel = MongoReader.getOrderModel("US41001InitialKoboSubscriptionTest"+SoapKeys.GRAB).get(0);
		stylistEmail = MongoReader.grabCustomerFormModels("US41001StyleCoachRegistrationTest").get(0).getEmailName();
		
		
	}

	@Test
	public void us41001KoboCheckReceivedEmailsTest() {
		//emailClientSteps.checkReceivedOriginalDocuments(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),"Related orde",orderModel.getOrderId());
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), "Versand");

	}

}

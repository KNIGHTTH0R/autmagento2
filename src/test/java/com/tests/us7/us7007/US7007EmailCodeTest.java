package com.tests.us7.us7007;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.env.stagingaut.Constants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US7", type = "external")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7007EmailCodeTest extends BaseTest{

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public CustomVerification customVerifications;
	
	public String clientName;
	public String validateMessageMail;
	
	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US7007UserRegistrationWidgetRegistrationTest").size();
		if (size > 0) {
			clientName = MongoReader.grabCustomerFormModels("US7007UserRegistrationWidgetRegistrationTest").get(0).getEmailName();
			System.out.println(clientName);
		} else
			System.out.println("The database has no entries");
	}
	
	@Test
	public void us7006EmailActivationTest() {
		validateMessageMail = emailClientSteps.grabEmailCoupon(clientName.replace("@" + Constants.WEB_MAIL, ""), "Willkommen");
		System.out.println(validateMessageMail);
		emailSteps.validateEmail("123aa11", validateMessageMail);
		
		customVerifications.printErrors();
	}
	
	
}

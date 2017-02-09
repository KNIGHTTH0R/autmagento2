package com.tests.uss16.us16003DataPreparation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US10.6 Order for Customer as Party host and Validate Party Wishlist", type = "Scenarios")
@Story(Application.StyleParty.US10_6.class)
@RunWith(SerenityRunner.class)
public class US16003ChechEmailAndAcceptInvitationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailClientSteps emailSteps;
	private String email;
	

	@Before
	public void setUp() throws Exception {

	
		int size = MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").size();
		if (size > 0) {
			email = MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0).getEmailName();
		//	password=MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0).getPassword();
//			emailPassword=MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us7001ConfirmCustomerTest() {
		//frontEndSteps.performLogin(email, password);
		
		//Dich ein zur PIPPA&JEAN Style Party
		emailSteps.confirmPartyInvitation(email.replace("@" + ConfigConstants.WEB_MAIL, ""), "Dich ein zur PIPPA&JEAN Style Party");

	}


}

package com.tests.uss40;

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
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US40001CheckAcademyReceivedEmailsTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;

	@Before
	public void setUp() throws Exception {

		
			stylistEmail = "emailemial";
		

	}

	@Test
	public void us40001CheckAcademyReceivedEmailsTest() {
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), "OnlineSc");
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), "CourseComplete");

	}

}

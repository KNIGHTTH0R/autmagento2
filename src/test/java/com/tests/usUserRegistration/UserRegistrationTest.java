package com.tests.usUserRegistration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.UserRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class UserRegistrationTest extends BaseTest {

	@Steps
	public UserRegistrationSteps userRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;

	@Before
	public void setUp() throws Exception {
		dataModel = new CustomerFormModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void usUserRegistrationTest() {

		userRegistrationSteps.fillUserRegistrationForm(dataModel);
		userRegistrationSteps.verifyUserCreation(dataModel);
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());

	}

}
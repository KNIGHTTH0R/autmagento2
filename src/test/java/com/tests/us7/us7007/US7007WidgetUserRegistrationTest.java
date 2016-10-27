package com.tests.us7.us7007;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7.7 Widget Registration Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_7.class)
@RunWith(SerenityRunner.class)
public class US7007WidgetUserRegistrationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;
	private String memberCode;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
		memberCode = "123aa11";
	}

	@Test
	public void us7007WidgetUserRegistrationTest() {

		customerRegistrationSteps.fillWidgetRegistrationForm(memberCode, dataModel);

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}

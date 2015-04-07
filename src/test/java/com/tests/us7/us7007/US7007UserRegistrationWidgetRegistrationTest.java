package com.tests.us7.us7007;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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


@WithTag(name = "US7", type = "fontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7007UserRegistrationWidgetRegistrationTest extends BaseTest{
	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private CustomerFormModel dataModel;
	private String memberCode;
	

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
		memberCode = "123aa11";
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7007UserRegistrationWidgetRegistrationTest() {

		customerRegistrationSteps.fillWidgetRegistrationForm(memberCode, dataModel);
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}
	
	
}

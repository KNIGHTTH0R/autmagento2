package com.tests.us6.us6001b;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US6.1b Sc Registration New Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)
public class US6001bCheckStylistPreferedWebsiteAndLanguage extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US6001bScRegistrationNewCustForbiddenCountryTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US6001bScRegistrationNewCustForbiddenCountryTest").get(0);
		} else
			System.out.println("The database has no entries");
	}

	@Test
	public void us6001bCheckStylistPreferedWebsiteAndLanguage() {

		customerRegistrationSteps.performLoginAndVerifyWebsiteAndLanguage(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword(),MongoReader.getContext(),ContextConstants.NOT_PREFERED_WEBSITE);

	}
}

package com.tests.uss17.us17002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.tests.BaseTest;
import com.tools.constants.Separators;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17.2 Check reassigned contacts on customer's preffered SC hierarchy if customer's preffered is quit", type = "Scenarios")
@Story(Application.MassAction.US17_2.class)
@RunWith(SerenityRunner.class)
public class US17002ChangeCustomersContextTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public FooterSteps footerSteps;

	private CustomerFormModel customerModel;
	private CustomerFormModel newStylistData;

	@Before
	public void setUp() throws Exception {
		customerModel = MongoReader.grabCustomerFormModels("US17002RegularCustomerRegistrationTest").get(0);
		newStylistData = MongoReader.grabCustomerFormModels("US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest").get(0);
	}

	@Test
	public void us17002ChangeCustomersContextTest() {
		customerRegistrationSteps.performLoginUnderContext(customerModel.getEmailName(), customerModel.getPassword(), Separators.SLASH + newStylistData.getFirstName()
				+ newStylistData.getLastName());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickChangeShop();

	}

}

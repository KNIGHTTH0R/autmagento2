package com.tests.uss17.us17001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "frontend")
@Story(Application.Shop.RegularCart.class)
@RunWith(ThucydidesRunner.class)
public class US17001ChangeFirstCustomersContextTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public FooterSteps footerSteps;

	public CustomerFormModel customerModel;

	@Before
	public void setUp() throws Exception {
		customerModel = MongoReader.grabCustomerFormModels("US17001RegularCustomerRegistrationTest").get(0);

	}

	@Test
	public void us17001ChangeFirstCustomersContextTest() {
		customerRegistrationSteps.performLoginUnderContext(customerModel.getEmailName(), customerModel.getPassword(),"TvKrmoYdhsCMEAPV");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickChangeShop();

	}

}

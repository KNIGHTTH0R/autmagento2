package com.tests.uss12;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.MyBusinessSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12", type = "frontend")
@Story(Application.KoboSubscription.class)
@RunWith(ThucydidesRunner.class)
public class US12001VerifyStylistKoboStatusAfterCancelCmOnSubscriptionTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;

	@Steps
	public FooterSteps footerSteps;
	@Steps
	public MyBusinessSteps myBusinessSteps;

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;

	public CustomerFormModel stylistRegistrationData = new CustomerFormModel("");

	@Before
	public void setUp() {

		DataGrabber.wipe();

		int size = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US12001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us12001VerifyStylistKoboStatusAfterCmOnSubscriptionTest() {
		customerRegistrationSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		myBusinessSteps.verifyKoboVoucherIsActive();

	}

}

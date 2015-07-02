package com.tests.uss17.us17001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyContactsListSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US17", type = "backend")
@Story(Application.Registration.Stylist.class)
@RunWith(ThucydidesRunner.class)
public class US17001VerifyThatContactsWhereRedistributedToStylecoachTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public MyContactsListSteps myContactsListSteps;

	public CustomerFormModel stylistRegistrationData;

	public CustomerFormModel customerModel;
	public CustomerFormModel contactModel;

	@Before
	public void setUp() throws Exception {

		customerModel = MongoReader.grabCustomerFormModels("US17001RegularCustomerRegistrationTest").get(0);
		contactModel = MongoReader.grabCustomerFormModels("US17001AddNewContactToStyleCoachTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void usO17001VerifyThatContactsWhereRedistributedToStylecoachTest() {

		customerRegistrationSteps.performLogin("mihaialexandrubarta@gmail.com", "mihai1234");
		loungeSteps.goToContactsList();
		myContactsListSteps.verifyThatContactIsInTheList(customerModel.getEmailName());
		myContactsListSteps.verifyThatContactIsInTheList(contactModel.getEmailName());
		

	}

}

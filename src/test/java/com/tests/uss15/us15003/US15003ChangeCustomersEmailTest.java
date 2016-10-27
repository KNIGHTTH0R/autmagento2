package com.tests.uss15.us15003;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(SerenityRunner.class)
public class US15003ChangeCustomersEmailTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	private CustomerFormModel dataModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();

		dataModel = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void usO15003ChangeCustomersEmailTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(dataModel.getEmailName());
		backEndSteps.openCustomerDetails(dataModel.getEmailName());
		backEndSteps.editEmail(dataModel);

	}

	@After
	public void saveData() {
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : compatibleStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}

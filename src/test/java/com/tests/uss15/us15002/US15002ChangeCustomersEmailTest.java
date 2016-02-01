package com.tests.uss15.us15002;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(ThucydidesRunner.class)
public class US15002ChangeCustomersEmailTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	private CustomerFormModel dataModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();

		dataModel = MongoReader.grabCustomerFormModels("US15002KoboRegistrationNewsletterSubscribeTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us15002ChangeCustomersEmailTest() {

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

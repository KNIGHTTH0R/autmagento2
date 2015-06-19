package com.tests.uss15.us15003;

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

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.SoapConstants;
import com.tools.env.variables.Credentials;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;

@WithTag(name = "US14", type = "backend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US15003ChangeCustomersEmailTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	public String stylistEmail;
	private CustomerFormModel dataModel;

	public AddressModel addressModel;
	CoordinatesModel coordinatesModel = new CoordinatesModel();
	RandomAddress randomAddress;
	List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();

		dataModel = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us14001ChangeCustomerAddressTest() {

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

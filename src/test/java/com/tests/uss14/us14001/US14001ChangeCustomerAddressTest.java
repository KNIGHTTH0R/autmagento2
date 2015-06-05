package com.tests.uss14.us14001;

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
import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
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
public class US14001ChangeCustomerAddressTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	public String stylistEmail;

	public AddressModel addressModel;
	CoordinatesModel coordinatesModel = new CoordinatesModel();
	RandomAddress randomAddress;
	List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabCustomerFormModels("US14001StyleCoachLeadDistributionTest").size();
		if (size > 0) {
			stylistEmail = MongoReader.grabCustomerFormModels("US14001StyleCoachLeadDistributionTest").get(0).getEmailName();
		} else
			System.out.println("The database has no entries");

		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		while (coordinatesModel.getLattitude() == null) {

			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddress(addressModel);

		}
		compatibleStylistList = ApiCalls.getCompatibleStylistsInRangeFromList(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE, SoapConstants.SOAP_STYLIST_FILTER,
				SoapConstants.SOAP_STYLIST_OPERAND, SoapConstants.SOAP_STYLIST_FILTER_VALUE, 3);
		PrintUtils.printListDbStylists(compatibleStylistList);

	}

	@Test
	public void us14001ChangeCustomerAddressTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		backEndSteps.clickOnAddressesTab();
		backEndSteps.editAddress(addressModel);

	}
	
	@After
	public void saveData() {
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : compatibleStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}

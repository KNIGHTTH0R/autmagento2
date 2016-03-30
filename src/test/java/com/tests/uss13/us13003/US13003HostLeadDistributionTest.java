package com.tests.uss13.us13003;

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
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.SoapConstants;
import com.tools.generalCalculation.StylistListCalculation;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;

@WithTag(name = "US13.3 Distribution to party host lead qualified SC", type = "Scenarios")
@Story(Application.Distribution.US13_3.class)
@RunWith(SerenityRunner.class)
public class US13003HostLeadDistributionTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private RandomAddress randomAddress;
	private List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		while (coordinatesModel.getLattitude() == null) {

			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddress(addressModel);
			System.out.println(coordinatesModel.getLattitude());
			System.out.println(coordinatesModel.getLongitude());

		}
		compatibleStylistList = StylistListCalculation.getCompatibleStylistsInRangeFromList(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE, SoapConstants.STYLIST_ID_FILTER,
				SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN, SoapConstants.STYLIST_ID_2000, 2);
		System.out.println(compatibleStylistList.size());
		PrintUtils.printListDbStylists(compatibleStylistList);

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us13003HostLeadDistributionTest() {

		customerRegistrationSteps.fillCreateCustomerFormNoMemberFlagAndNoStylistSelected(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : compatibleStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}
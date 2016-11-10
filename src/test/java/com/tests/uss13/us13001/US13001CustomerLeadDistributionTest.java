package com.tests.uss13.us13001;

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
import com.tools.constants.SoapConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.generalCalculation.StylistListCalculation;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;

@WithTag(name = "US13.1 Distribution to customer lead qualified SC", type = "Scenarios")
@Story(Application.Distribution.US13_1.class)
@RunWith(SerenityRunner.class)
public class US13001CustomerLeadDistributionTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CoordinatesModel coordinatesModel;
	private RandomAddress randomAddress;
	private List<DBStylistModel> compatibleStylistListForDistribution = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		randomAddress = new RandomAddress();
		coordinatesModel = new CoordinatesModel();

		while (coordinatesModel.getLattitude() == null) {

			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddressWithComponent(addressModel);
			System.out.println(coordinatesModel.getLattitude());
			System.out.println(coordinatesModel.getLongitude());

		}
		compatibleStylistListForDistribution = StylistListCalculation.getCompatibleStylistsInRangeFromList(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE, SoapConstants.STYLIST_ID_FILTER,
				SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN, SoapConstants.STYLIST_ID_2000, 1);
		PrintUtils.printListDbStylists(compatibleStylistListForDistribution);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us13001CustomerLeadDistributionTest() {

		customerRegistrationSteps.fillCreateCustomerFormWithNoStylePartyAndStyleCoachCheckedNoStylistSelected(dataModel, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : compatibleStylistListForDistribution) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}
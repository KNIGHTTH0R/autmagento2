package com.tests.uss13.us13005;

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
import com.tools.data.frontend.DykscSeachModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.SoapConstants;
import com.tools.generalCalculation.StylistListCalculation;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.RandomAddress;
import com.workflows.frontend.DysksWorkflows;

@WithTag(name = "US13.5 DYKSC Assignation to SC qualified lead SC", type = "Scenarios")
@Story(Application.Distribution.US13_5.class)
@RunWith(SerenityRunner.class)
public class US13005StyleCoachLeadDykscPlzAndCountryTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public DysksWorkflows dysksWorkflows;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private RandomAddress randomAddress;
	private List<DBStylistModel> searchByPlzAndCountryStylistList = new ArrayList<DBStylistModel>();
	private List<DykscSeachModel> dysksStylecoachesList = new ArrayList<DykscSeachModel>();

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		while (coordinatesModel.getLattitude() == null) {
			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddressWithComponent(addressModel);
		}

		searchByPlzAndCountryStylistList = StylistListCalculation.getCompatibleStylistsForDysks(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE,
				SoapConstants.STYLIST_ID_FILTER, SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN, SoapConstants.STYLIST_ID_2000, 3);

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us13005StyleCoachLeadDykscPlzAndCountryTest() {

		dysksStylecoachesList = customerRegistrationSteps.fillCreateCustomerFormAndReturnFoundStylecoaches(dataModel, addressModel);

		customerRegistrationSteps.verifyCustomerCreation();
		dysksWorkflows.setValidateStylistsModels(searchByPlzAndCountryStylistList, dysksStylecoachesList);
		dysksWorkflows.validateStylists("VALIDATE THAT SEARCH BY PLZ AND COUNTRY RETURNS THE CORRECT LIST");
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : searchByPlzAndCountryStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}
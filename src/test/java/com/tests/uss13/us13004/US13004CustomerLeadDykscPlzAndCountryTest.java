package com.tests.uss13.us13004;

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
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;
import com.workflows.frontend.DysksWorkflows;

@WithTag(name = "US13.4 DYKSC Assignation to customer lead SC", type = "Scenarios")
@Story(Application.Distribution.US13_4.class)
@RunWith(ThucydidesRunner.class)
public class US13004CustomerLeadDykscPlzAndCountryTest extends BaseTest {

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
			System.out.println(coordinatesModel.getLattitude());
			System.out.println(coordinatesModel.getLongitude());

		}

		searchByPlzAndCountryStylistList = StylistListCalculation.getCompatibleStylistsForDysks(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE, SoapConstants.STYLIST_ID_FILTER,
				SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN, SoapConstants.STYLIST_ID_2000, 1);

		System.out.println("--calculated dysks---------");
		PrintUtils.printListDbStylists(searchByPlzAndCountryStylistList);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us13004CustomerLeadDykscPlzAndCountryTest() {

		dysksStylecoachesList = customerRegistrationSteps.fillCreateCustomerFormWithNoStylePartyAndStyleCoachCheckedAndReturnAutosearchFoundStylecoaches(dataModel, addressModel);
		System.out.println("--grabbed dysks---------");
		PrintUtils.printListDykscSeachModel(dysksStylecoachesList);

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
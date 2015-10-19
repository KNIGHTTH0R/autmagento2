package com.tests.uss13.us13007;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.SoapConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;
import com.workflows.frontend.DysksWorkflows;

@WithTag(name = "US13.7 DYKSC By Name Assignation", type = "Scenarios")
@Story(Application.Distribution.US13_7.class)
@RunWith(ThucydidesRunner.class)
public class US13007DykscSearchByNameTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public DysksWorkflows dysksWorkflows;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private RandomAddress randomAddress;
	private List<DBStylistModel> searchByNameStylistList = new ArrayList<DBStylistModel>();
	private List<DykscSeachModel> dysksStylecoachesList = new ArrayList<DykscSeachModel>();
	private String firstName;
	private String lastName;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss13" + File.separator + "us13007.properties");
			prop.load(input);

			firstName = prop.getProperty("firstName");
			lastName = prop.getProperty("lastName");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		while (coordinatesModel.getLattitude() == null) {

			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddress(addressModel);

		}
		searchByNameStylistList = ApiCalls.getDykscStylistByName(firstName, lastName, SoapConstants.STYLIST_ID_FILTER, SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN,
				SoapConstants.STYLIST_ID_2000, 2);

		System.out.println("--dysks---------");
		PrintUtils.printListDbStylists(searchByNameStylistList);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us13007DykscSearchByNameTest() {

		dysksStylecoachesList = customerRegistrationSteps.fillCreateCustomerFormAnReturnFoundByNameStylecoaches(dataModel, addressModel, firstName, lastName);
		System.out.println("--grabbed dysks---------");
		PrintUtils.printListDykscSeachModel(dysksStylecoachesList);

		customerRegistrationSteps.verifyCustomerCreation();
		dysksWorkflows.setValidateStylistsModels(searchByNameStylistList, dysksStylecoachesList);
		dysksWorkflows.validateStylists("VALIDATE THAT SEARCH BY NAME RETURNS A CORRECT STYLIST");
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveCoordinatesModel(coordinatesModel, getClass().getSimpleName());
		for (DBStylistModel stylist : searchByNameStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}
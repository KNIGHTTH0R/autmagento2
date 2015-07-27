package com.tests.us7.uss70014;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import com.steps.frontend.HomeSteps;
import com.steps.frontend.registration.connectWithMe.ConnectWithMeRegistrationSteps;
import com.tests.BaseTest;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.KoboCampaign.class)
@RunWith(ThucydidesRunner.class)
public class US70014UnregisteredConnectWithMeOnContextTest extends BaseTest {

	@Steps
	public ConnectWithMeRegistrationSteps connectWithMeRegistrationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HomeSteps homeSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	private String context;
	private String expectedStyleCoachName;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us70014.properties");
			prop.load(input);

			context = prop.getProperty("context");
			expectedStyleCoachName = prop.getProperty("expectedStyleCoachName");

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
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us70014UnregisteredConnectWithMeOnContextTest() {

		connectWithMeRegistrationSteps.navigateToConnectWithMeUnderContext(context);
		connectWithMeRegistrationSteps.fillConnectWithMeForm(dataModel, addressModel);
		connectWithMeRegistrationSteps.verifySuccessLinkUnderContext();
		connectWithMeRegistrationSteps.verifyConnectWithMeRegistrationSuccesMessage();
		connectWithMeRegistrationSteps.verifyConnectWithMeSuccesRegistrationContainsScName(expectedStyleCoachName);

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
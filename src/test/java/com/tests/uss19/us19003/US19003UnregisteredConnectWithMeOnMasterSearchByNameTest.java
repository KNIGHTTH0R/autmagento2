package com.tests.uss19.us19003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.pages.frontend.registration.connectWithMe.ConnectWithMeAllocationPage.StyleMode;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.registration.connectWithMe.ConnectWithMeRegistrationSteps;
import com.tests.BaseTest;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.KoboCampaign.class)
@RunWith(SerenityRunner.class)
public class US19003UnregisteredConnectWithMeOnMasterSearchByNameTest extends BaseTest {

	@Steps
	public ConnectWithMeRegistrationSteps connectWithMeRegistrationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	private String firstName, lastName;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss19" + File.separator + "us19003.properties");
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
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us19003UnregisteredConnectWithMeOnMasterSearchByNameTest() {

		connectWithMeRegistrationSteps.navigateToConnectWithMeUnderMasterShop();
		connectWithMeRegistrationSteps.fillConnectWithMeForm(dataModel, addressModel);
		connectWithMeRegistrationSteps.selectStylistOption(StyleMode.CustomStylist, firstName, lastName, addressModel);
		connectWithMeRegistrationSteps.submitStylistSelection();
		connectWithMeRegistrationSteps.verifySuccessLink();
		connectWithMeRegistrationSteps.verifyConnectWithMeRegistrationSuccesMessage();// zumbaZumbaPippa     spainStylist@mailinator.com

	}
	
	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
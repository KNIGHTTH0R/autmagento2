package com.tests.us7.us7006;

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
import com.pages.frontend.registration.landing.LandingCustomerAllocationPage.StyleMode;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7.6 Landing Page Registration Selected SC Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_6.class)
@RunWith(ThucydidesRunner.class)
public class US7006LandingPageRegistrationSelectedScTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private String styleCoachFN;
	private String styleCoachLN;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7006.properties");
			prop.load(input);
			styleCoachFN = prop.getProperty("styleCoachFN");
			styleCoachLN = prop.getProperty("styleCoachLN");

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

		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void us7006LandingPageRegistrationSelectedScTest() {

		customerRegistrationSteps.fillLandingPageForm(dataModel, addressModel);
		customerRegistrationSteps.selectStylistOption(StyleMode.CustomStylist, styleCoachFN, styleCoachLN, addressModel);
		customerRegistrationSteps.submitStylistSelection();
		String email = customerRegistrationSteps.fillThankYouForm(dataModel.getPassword());
		customerRegistrationSteps.verifyCustomerEmail(dataModel.getEmailName(), email);
		customerRegistrationSteps.verifySuccessLink();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}

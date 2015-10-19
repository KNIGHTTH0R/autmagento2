package com.tests.uss17.us17003;

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
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US17.3 Check reassigned contacts up on canceled SC hierarchy when no new Sc is selected", type = "Scenarios")
@Story(Application.MassAction.US17_3.class)
@RunWith(ThucydidesRunner.class)
public class US17003StyleCoachRegistrationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;

	private CustomerFormModel customerFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private String context;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17003.properties");
			prop.load(input);

			context = prop.getProperty("context");

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

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17003StyleCoachRegistrationTest() {
		headerSteps.navigateToStylecoachRegisterFormUnderContext(context);
		stylistRegistrationSteps.fillCreateStylecoachFormWithKnownSponsorPayWithVisa(customerFormData, customerFormAddress, birthDate.getDate());
		customVerification.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}

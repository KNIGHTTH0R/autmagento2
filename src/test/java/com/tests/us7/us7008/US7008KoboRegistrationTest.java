package com.tests.us7.us7008;

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
import com.steps.external.EmailClientSteps;
import com.steps.frontend.ContactBoosterRegistrationSteps;
import com.steps.frontend.FancyBoxSteps;
import com.steps.frontend.KoboSuccesFormSteps;
import com.steps.frontend.KoboValidationSteps;
import com.steps.frontend.PomProductDetailsSteps;
import com.tests.BaseTest;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.Registration.Customer.class)
@RunWith(ThucydidesRunner.class)
public class US7008KoboRegistrationTest extends BaseTest {

	@Steps
	public ContactBoosterRegistrationSteps contactBoosterRegistrationSteps;
	@Steps
	public KoboValidationSteps koboValidationSteps;
	@Steps
	public KoboSuccesFormSteps koboSuccesFormSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public PomProductDetailsSteps pomProductDetailsSteps;
	@Steps
	public FancyBoxSteps fancyBoxSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;
	private String koboCode;
	private String url;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us7" + File.separator + "us7008.properties");
			prop.load(input);
			koboCode = prop.getProperty("koboCode");

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

	@Test
	public void us70081KoboRegistrationTest() {
		koboValidationSteps.enterKoboCodeAndGoToRegistrationProcess(koboCode);
		contactBoosterRegistrationSteps.fillContactBoosterRegistrationForm(dataModel, addressModel);
		koboSuccesFormSteps.verifyKoboFormIsSuccsesfullyFilledIn();
		emailClientSteps.openMailinator();
		url = emailClientSteps.grabConfirmationLinkFromEmail(dataModel.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.CONFIRM_ACCOUNT_MAIL_SUBJECT);	
		contactBoosterRegistrationSteps.navigate(url);
		fancyBoxSteps.closeFancyBox();
		pomProductDetailsSteps.findProductAndClick("CORALIE RING (GREY)");
		fancyBoxSteps.selectValueFromDropDown("17");
		

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}
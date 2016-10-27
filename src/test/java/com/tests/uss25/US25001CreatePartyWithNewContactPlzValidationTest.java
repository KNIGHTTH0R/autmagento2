package com.tests.uss25;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.steps.frontend.registration.party.CreateNewContactStepsWithCsv;
import com.tests.BaseTest;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(SerenityRunner.class)
public class US25001CreatePartyWithNewContactPlzValidationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;
	@Steps
	public CreateNewContactStepsWithCsv createNewContactStepsWithCsv;

	private String username, password;
	private CustomerFormModel customerData;
	private AddressModel addressData;

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();
		addressData = new AddressModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

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

	}

	@Test
	public void us25001CreatePartyWithNewContactPlzValidationTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyWithNewContactPage();

		createNewContactSteps.fillCreateNewContactWithoutPlz(customerData, addressData);

		try {
			withTestDataFrom("resources/invalidPlzTestData.csv").run(createNewContactStepsWithCsv).inputPostCodeCsv();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed !!!");
		}
	}
}

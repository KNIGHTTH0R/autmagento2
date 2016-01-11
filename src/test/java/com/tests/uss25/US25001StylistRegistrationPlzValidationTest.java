package com.tests.uss25;

import static net.thucydides.core.steps.StepData.withTestDataFrom;

import java.io.IOException;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.StylistRegistrationStepsWithCsv;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesRunner.class)
public class US25001StylistRegistrationPlzValidationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public StylistRegistrationStepsWithCsv stylistRegistrationStepsWithCsv;
	@Steps
	public CustomVerification customVerification;
	private CustomerFormModel customerFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;

	@Before
	public void setUp() throws Exception {

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
	}

	@Test
	public void us25001StylistRegistrationPlzValidationTest() {
		headerSteps.navigateToRegisterForm();
		stylistRegistrationSteps.fillCreateCustomerFormWithoutPlz(customerFormData, customerFormAddress, birthDate.getDate());
		try {
			withTestDataFrom("resources/invalidPlzTestData.csv").run(stylistRegistrationStepsWithCsv).inputPostCodeCsv();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed !!!");
		}
		customVerification.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}

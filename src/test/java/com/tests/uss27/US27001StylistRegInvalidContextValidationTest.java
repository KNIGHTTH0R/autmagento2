package com.tests.uss27;

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

import com.connectors.http.ApacheHttpHelper;
import com.connectors.http.DeleteCategory;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.StylistRegistrationStepsCsvContext;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.soap.CategoryModel;
import com.tools.env.constants.JenkinsConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesRunner.class)
public class US27001StylistRegInvalidContextValidationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public StylistRegistrationStepsCsvContext stylistRegistrationStepsCsvContext;
	@Steps
	public CustomVerification customVerification;

	private CustomerFormModel customerFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	CategoryModel categoryModel;
	String categoryId;

	@Before
	public void setUp() throws Exception {

		categoryId = MongoReader.grabIncrementId("US27001CreateCategoryAndProductTest");

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
	}

	@Test
	public void us27001StylistRegInvalidContextValidationTest() {
		headerSteps.navigateToRegisterForm();
		stylistRegistrationSteps.fillCreateCustomerFormWithoutContext(customerFormData, customerFormAddress, birthDate.getDate());
		try {
			withTestDataFrom("resources/invalidContextData.csv").run(stylistRegistrationStepsCsvContext).inputContextCsv();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed !!!");
		}
		customVerification.printErrors();

	}

	@After
	public void tearDown() throws Exception {
		DeleteCategory.deleteApiCategory(categoryId);
		ApacheHttpHelper.sendGet(JenkinsConstants.REINDEX_SC_CONTEXT_JOB);
	}

}

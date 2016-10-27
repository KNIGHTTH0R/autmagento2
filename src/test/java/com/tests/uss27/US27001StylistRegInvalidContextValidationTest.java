package com.tests.uss27;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.connectors.http.DeleteCategory;
import com.connectors.http.MagentoCategoriesCalls;
import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistContextStepsCsv;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EnvironmentConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.soap.CategoryModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.requirements.Application;
import com.workflows.file.FileWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US.27.1 Check that categories and product url keys are not allowed as SC context", type = "Scenarios")
@Story(Application.StylecoachContextValidation.US27_1.class)
@RunWith(SerenityRunner.class)
public class US27001StylistRegInvalidContextValidationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public StylistContextStepsCsv stylistContextStepsCsv;
	@Steps
	public CustomVerification customVerification;

	private CustomerFormModel customerFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private CategoryModel categoryModel;
	private ProductDetailedModel genProduct;
	private List<String> lines;
	private String categoryId;

	@Before
	public void setUp() throws Exception {

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");

		categoryModel = MagentoCategoriesCalls.createCategoryModel();
		categoryId = MagentoCategoriesCalls.createApiCategory(categoryModel, "52");

		genProduct = MagentoProductCalls.createProductModel();
		MagentoProductCalls.createApiProduct(genProduct);

		lines = new ArrayList<String>(Arrays.asList("context", categoryModel.getUrlKey(), genProduct.getUrlKey()));
		FileWorkflows.writeLinesToInvalidContextCsv("invalidContextData.csv", lines);

	}

	@Test
	public void us27001StylistRegInvalidContextValidationTest() {
		headerSteps.navigateToRegisterForm();
		stylistRegistrationSteps.fillCreateCustomerFormWithoutContext(customerFormData, customerFormAddress,
				birthDate.getDate());
		try {
			withTestDataFrom("resources/invalidContextData.csv").run(stylistContextStepsCsv).inputContextCsv();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Failed !!!");
		}
		customVerification.printErrors();

	}

	@After
	public void tearDown() throws Exception {
		DeleteCategory.deleteApiCategory(categoryId);
		ApacheHttpHelper.sendGet(EnvironmentConstants.REINDEX_SC_CONTEXT_JOB, EnvironmentConstants.USERNAME,
				EnvironmentConstants.PASSWORD);
	}

}

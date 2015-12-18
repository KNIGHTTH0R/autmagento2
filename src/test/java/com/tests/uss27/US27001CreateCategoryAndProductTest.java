package com.tests.uss27;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoCategoriesCalls;
import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.StylistRegistrationStepsCsvContext;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.soap.CategoryModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FileUtils;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesRunner.class)
public class US27001CreateCategoryAndProductTest extends BaseTest {

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

	private ProductDetailedModel genProduct;
	CategoryModel categoryModel;
	List<String> lines = new ArrayList<String>();

	@Test
	public void us27001CreateCategoryAndProductTest() {

		categoryModel = MagentoCategoriesCalls.createCategoryModel();
		categoryModel.setId(MagentoCategoriesCalls.createApiCategory(categoryModel, "52"));

		genProduct = MagentoProductCalls.createProductModel();
		MagentoProductCalls.createApiProduct(genProduct);

		lines.add("context");
		lines.add(categoryModel.getUrlKey());
		lines.add(genProduct.getUrlKey());

		String basedir = System.getProperty("basedir");
		FileUtils.writeToFile(basedir + "/resources/invalidContextData", lines);

	}

	@After
	public void saveData() {
		MongoWriter.saveCategoryModel(categoryModel, getClass().getSimpleName());
		MongoWriter.saveProductDetailedModel(genProduct, getClass().getSimpleName());
	}

}

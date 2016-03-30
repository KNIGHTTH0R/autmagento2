package com.tests.uss10.us10006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.data.UrlModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US10.6 Order for Customer as Party host and Validate Party Wishlist", type = "Scenarios")
@Story(Application.StyleParty.US10_6.class)
@RunWith(SerenityRunner.class)
public class US10006CustomerAddProductIntoWishlistTest extends BaseTest {

	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;

	private String username, password;
	private static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private static UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("49.00");
		MagentoProductCalls.createApiProduct(genProduct2);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10006.properties");
			prop.load(input);
			username = prop.getProperty("customerUsername");
			password = prop.getProperty("customerPassword");

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);

		urlModel = MongoReader.grabUrlModels("US10006ChechEmailAndAcceptInvitationTest").get(0);
		System.out.println(urlModel.getUrl());
	}

	@Test
	public void us10006CustomerAddProductIntoWishlist() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.clickOnWishlistButton();
		headerSteps.wipeWishlist();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
		allProductsList.add(productData);

		footerSteps.selectWebsiteFromFooter(ContextConstants.NOT_PREFERED_WEBSITE);
		if (!headerSteps.succesfullLogin()) {
			customerRegistrationSteps.performLoginAfterChangingWebsite(username, password);
		}
		headerSteps.clickOnWishlistButton();
		headerSteps.wipeWishlist();

		productData = addRegularProductsWorkflow.setBasicProductToWishlist(genProduct2, "1", "0");

	}

	@After
	public void saveData() {
		// the invited guest can have products in both wishllists( from de and
		// from es website)
		// only the expected product to be found in party wishlist will be
		// added.the second one should not appear,but we will check that only
		// the product from the preferred website appears in the party wishlist
		for (RegularBasicProductModel product : allProductsList) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}

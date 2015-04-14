package com.tests.uss10.us10006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.CreateProduct;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.UrlModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.regularUser.RegularUserCartCalculator;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US10", type = "frontend")
@Story(Application.Shop.RegularCart.class)
@RunWith(ThucydidesRunner.class)
public class US10006CustomerAddProductIntoWishlistTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;	

	private String username, password;
	public static List<RegularBasicProductModel> allProductsList = new ArrayList<RegularBasicProductModel>();
	private ProductDetailedModel genProduct1;
	public static UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = CreateProduct.createProductModel();
		genProduct1.setPrice("89.00");
		CreateProduct.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
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
	}

	@Test
	public void us10006CustomerAddProductIntoWishlist() {
		customerRegistrationSteps.navigate(urlModel.getUrl());
		customerRegistrationSteps.performLogin(username, password);
		headerSteps.redirectToWishlist();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
		allProductsList.add(productData);

	}

	@After
	public void saveData() {
		for (RegularBasicProductModel product : allProductsList) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName() + SoapKeys.CALC);
		}
	}
}

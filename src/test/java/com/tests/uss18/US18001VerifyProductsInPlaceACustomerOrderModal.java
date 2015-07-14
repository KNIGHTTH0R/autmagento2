package com.tests.uss18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.cart.partyHost.AddProductsModalSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.tests.BaseTest;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US15", type = "frontend")
@Story(Application.Newsletter.class)
@RunWith(ThucydidesRunner.class)
public class US18001VerifyProductsInPlaceACustomerOrderModal extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsModalSteps addProductsModalSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;

	private String username, password, customerName;

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;
	private ProductDetailedModel genProduct5;
	private ProductDetailedModel genProduct6;
	private ProductDetailedModel genProduct7;
	private ProductDetailedModel genProduct8;
	private ProductDetailedModel genProduct9;

	@Before
	public void setUp() throws Exception {

//		genProduct1 = ApiCalls.createProductModel();
//		genProduct1.setPrice("89.00");
//		genProduct1.setIp("75");
//		ApiCalls.createApiProduct(genProduct1);
//		
//		genProduct2 = ApiCalls.createBundleProductModel();
//		genProduct2.setPrice("89.00");
//		genProduct2.setIp("75");
//		ApiCalls.createApiProduct(genProduct2);
//		
//		genProduct3 = ApiCalls.createGroupedProductModel();
//		genProduct3.setPrice("89.00");
//		genProduct3.setIp("75");
//		ApiCalls.createApiProduct(genProduct3);
//		
//		genProduct4 = ApiCalls.createConfigurableProductModel();
//		genProduct4.setPrice("89.00");
//		genProduct4.setIp("75");
//		ApiCalls.createApiProduct(genProduct4);
//		
//		genProduct5 = ApiCalls.createVirtualProductModel();
//		genProduct5.setPrice("89.00");
//		genProduct5.setIp("75");
//		ApiCalls.createApiProduct(genProduct5);
//		
//		genProduct6 = ApiCalls.createGiftProductModel();
//		genProduct6.setPrice("89.00");
//		genProduct6.setIp("75");
//		ApiCalls.createApiProduct(genProduct6);
//		
//		genProduct7 = ApiCalls.createDownloadableProductModel();
//		genProduct7.setPrice("89.00");
//		genProduct7.setIp("75");
//		ApiCalls.createApiProduct(genProduct7);
		
		genProduct8 = ApiCalls.createZzzProductModel();
		genProduct8.setPrice("89.00");
		genProduct8.setIp("75");
		ApiCalls.createApiProduct(genProduct8);
		
//		genProduct9 = ApiCalls.createStarterKitProductModel();
//		genProduct9.setPrice("89.00");
//		genProduct9.setIp("75");
//		ApiCalls.createApiProduct(genProduct9);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			customerName = prop.getProperty("customerName");

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

		MongoConnector.cleanCollection(getClass().getSimpleName());
		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us18001OrderForCustomerTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickOrderForCustomer();
		loungeSteps.selectCustomerToOrderFor(customerName);
		customerRegistrationSteps.wipeHostCart();		
		orderForCustomerCartSteps.openSearchProductsModal();
		
//		addProductsModalSteps.searchForProduct(genProduct1.getSku());
//		addProductsModalSteps.verifyProductPropertiesInModalWindow(genProduct1.getSku(), genProduct1.getName());
//		
//		addProductsModalSteps.searchForProduct(genProduct2.getSku());
//		addProductsModalSteps.verifyProductPropertiesInModalWindow(genProduct2.getSku(), genProduct2.getName());
		
	}

}

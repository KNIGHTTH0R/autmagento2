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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.BorrowedProductModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;

@WithTag(name = "US10", type = "frontend")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10006CheckPartyWishlistAndBorrowProductTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;

	public static UrlModel urlModel = new UrlModel();
	public static DateModel dateModel = new DateModel();
	private String username, password;
	public static List<RegularBasicProductModel> productsList = new ArrayList<RegularBasicProductModel>();
	public static List<BorrowedProductModel> borrowCartProductsList = new ArrayList<BorrowedProductModel>();
	String productName, productCode;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName());

		urlModel = MongoReader.grabUrlModels("US10006CreatePartyWithStylistHostTest" + Constants.GRAB).get(0);
		productsList = MongoReader.grabRegularBasicProductModel("US10006CustomerAddProductIntoWishlistTest" + Constants.CALC);
		productName = productsList.get(0).getName();
		productCode = productsList.get(0).getProdCode();

	}

	@Test
	public void us10006CheckPartyWishlistAndBorrowProductTest() {

		customerRegistrationSteps.performLogin(username, password);
		customerRegistrationSteps.wipeHostCart();
		customerRegistrationSteps.navigate(urlModel.getUrl());

		partyDetailsSteps.selectWishlistProductAndAddItToBorrowCart(productName);
		BorrowedProductModel borrowedProductModel;
		borrowedProductModel = addBorrowedProductsWorkflow.setBorrowedProductToCart(productName, "0", "0", "0");
		borrowCartProductsList.add(borrowedProductModel);
		borrowedProductModel = addBorrowedProductsWorkflow.setBorrowedProductToCart("Leihgebühr Z999", "0", "0", "0");
		borrowCartProductsList.add(borrowedProductModel);

	}
}

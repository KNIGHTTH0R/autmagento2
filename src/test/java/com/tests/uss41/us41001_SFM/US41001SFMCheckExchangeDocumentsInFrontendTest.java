package com.tests.uss41.us41001_SFM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US41.1 Regular Cart Check Nav Documents", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001SFMCheckExchangeDocumentsInFrontendTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public RegularUserOrderProductsWorkflows regularUserOrderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProfileNavSteps profileNavSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private static OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_41_FOLDER + File.separator + "us41001SFM.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
		orderModel = MongoReader.grabOrderModels("US41001ShopForMyselfOrderTest" + SoapKeys.GRAB).get(0);

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us41001SFMCheckOriginalDocumentsInFrontendTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.goToProfile();
		profileNavSteps.selectMenu(ContextConstants.MEINE_BESTELLUNGEN);
		profileSteps.clickOnOrder(orderModel.getOrderId());
		profileSteps.clickOnDokumenteTab();
		profileSteps.valdateExchangeDocIsReceived(true);
		profileSteps.valdateExchangeReturnIsReceived(true);
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
	}
}

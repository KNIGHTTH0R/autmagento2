package com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerNavTab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
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
public class US41001PlaceCustomerSendDocumentsViaNavTabToContactTest extends BaseTest {

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
	public CustomVerification customVerifications;

	private String orderId,contactEmail,username;
	private OrderModel orderModel;
	@Before
	public void setUp() {
		orderId = MongoReader.getOrderModel("US41001OrderOnlyZzzProductsForCustomerTest").get(0).getOrderId();

		contactEmail = MongoReader.grabCustomerFormModels("US41001OrderOnlyZzzProductsForCustomerTest").get(0)
				.getEmailName();
		System.out.println(contactEmail);
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss41" + File.separator + "us41001PlaceCust.properties");
			prop.load(input);
			username = prop.getProperty("username");

			

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

	

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us41001RegularSendDocumentsViaNavTabToAddressTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		ordersSteps.selectMenu("Dokumente aus NAV");
		ordersSteps.openDocumentsSection();
		
		ordersSteps.sendIndividualDocumentToReceiver("Alle auswählen",contactEmail);
		ordersSteps.openDocumentsSection();
		ordersSteps.sendIndividualDocumentToReceiver("RETURN_FORM_"+orderId,username);
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
	}
}

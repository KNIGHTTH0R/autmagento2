package com.tests.uss41.us41001_Borrow;

import java.util.List;

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
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US41.1 Regular Cart Check Nav Documents", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001BorrowCheckExchangeDocumentsInNavTabTest extends BaseTest {

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

	private String orderId;

	@Before
	public void setUp() {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US41001StyleCoachBorrowsProductsTest" + SoapKeys.GRAB);
		

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);

		// Setup Data from all models in first test
		// from Shipping calculations
	

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us41001BorrowCheckExchangeDocumentsInNavTabTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);


		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		ordersSteps.validateCompleteStatus();
		ordersSteps.selectMenu("Dokumente aus NAV");
		ordersSteps.openDocumentsSection();
		ordersSteps.validateEchangeDocIsReceived(orderId,true);
		ordersSteps.validateSecoundReturnIsReceived(orderId,true,4);
		//add here validation
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
	}
}

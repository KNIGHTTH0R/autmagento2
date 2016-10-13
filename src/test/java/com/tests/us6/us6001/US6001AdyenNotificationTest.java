package com.tests.us6.us6001;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US6.1 Sc Registration New Customer Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)

public class US6001AdyenNotificationTest extends BaseTest{

	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public CustomVerification customVerifications;

	private static OrderInfoModel orderInfoModel = new OrderInfoModel();

	private String orderId;
	//private String pspReference;

	@Before
	public void setUp() {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US6001ScRegistrationNewCustomerTest");
//		List<OrderInfoModel> orderInfoModel = MongoReader.grabAdminOrderModels("US6001ValidateStarterSetOrderInBackendTest");


		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}



	}

	@Test
	public void us6001AdyenNotificationTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		ordersSteps.selectMenu(ConfigConstants.ADYEN_NOTIFICATION_TAB);
		
		ordersSteps.verifyAuthorization(orderInfoModel.getPspReference());
		ordersSteps.verifyCapture(orderInfoModel.getPspReference());
		

		customVerifications.printErrors();
	}
	
	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

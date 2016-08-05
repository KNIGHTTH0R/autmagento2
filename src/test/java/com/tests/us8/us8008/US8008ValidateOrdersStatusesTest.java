package com.tests.us8.us8008;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US8.8 Customer Buy With 0 Amount Immediate and Tp products", type = "Scenarios")
@Story(Application.RegularCart.US8_8.class)
@RunWith(SerenityRunner.class)
public class US8008ValidateOrdersStatusesTest extends BaseTest {

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

	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderInfoModel orderInfoModelTp1 = new OrderInfoModel();
	private static OrderInfoModel orderInfoModelTp2 = new OrderInfoModel();

	List<OrderModel> orderModelList = new ArrayList<OrderModel>();
	List<OrderModel> orderModelListTp1 = new ArrayList<OrderModel>();
	List<OrderModel> orderModelListTp2 = new ArrayList<OrderModel>();

	@Before
	public void setUp() {
		orderModelList = MongoReader.getOrderModel("US8008CustomerBuyWithTpAndZeroAmountTest" + "TP0");
		orderModelListTp1 = MongoReader.getOrderModel("US8008CustomerBuyWithTpAndZeroAmountTest" + "TP1");
		orderModelListTp2 = MongoReader.getOrderModel("US8008CustomerBuyWithTpAndZeroAmountTest" + "TP2");
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us8008ValidateOrdersStatusesTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelList.get(0).getOrderId());
		ordersSteps.openOrder(orderModelList.get(0).getOrderId());
		orderInfoModel = ordersSteps.grabOrderInfo();

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp1.get(0).getOrderId());
		ordersSteps.openOrder(orderModelListTp1.get(0).getOrderId());
		orderInfoModelTp1 = ordersSteps.grabOrderInfo();

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.get(0).getOrderId());
		ordersSteps.openOrder(orderModelListTp2.get(0).getOrderId());
		orderInfoModelTp2 = ordersSteps.grabOrderInfo();

		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung erfolgreich");
		orderWorkflows.validateOrderStatus(orderInfoModelTp1.getOrderStatus(), "Zahlung geplant");
		orderWorkflows.validateOrderStatus(orderInfoModelTp2.getOrderStatus(), "Zahlung geplant");

		customVerifications.printErrors();
	}

}

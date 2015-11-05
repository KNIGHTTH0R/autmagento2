package com.tests.uss23;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ImportInterfaceCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001PayAndImportOrderInNavTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	private OrderModel orderModel;

	@Before
	public void setUp() throws Exception {
		orderModel = MongoReader.getOrderModel("US23001SfmTest").get(0);
	}

	@Test
	public void us23001PayAndImportOrderInNavTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModel.getOrderId());
		ordersSteps.openOrder(orderModel.getOrderId());
		ordersSteps.markOrderAsPaid();

		ImportInterfaceCalls.importOrderInNav(UrlConstants.IMPORT_INTERFACE_URL, orderModel.getOrderId());
	}

}

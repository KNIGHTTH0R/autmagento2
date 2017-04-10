package com.tests.uss23;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.OrderInfoMagCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.external.navision.NavisionHomeSteps;
import com.steps.external.navision.NavisionImportSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.data.backend.OrderModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001PayAndImportOrderInNavisionTest extends BaseTest {

	
	@Steps
	public NavisionHomeSteps navisionSteps;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public NavisionImportSteps navisionImportSteps;

	private OrderModel orderModel;
	private DBOrderModel DBorderModel;
	List<SyncInfoModel> syncronizedMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> syncronizedNavProducts = new ArrayList<SyncInfoModel>();

	@Before
	public void setUp() throws Exception {
		orderModel = MongoReader.getOrderModel("US23001BuyProductsOnShopforMyselfTest").get(0);
	}

	@Test
	public void us23001PayAndImportOrderInNavisionTest() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		

		
		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModel.getOrderId());
		ordersSteps.openOrder(orderModel.getOrderId());
		ordersSteps.markOrderAsPaid();
		
		navisionSteps.performLoginIntoNavisonWebClient();
		DBorderModel=OrderInfoMagCalls.getOrderInfo(orderModel.getOrderId());
		navisionSteps.performOrderImport(DBorderModel.getOrderId());
	}
}

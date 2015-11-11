package com.tests.uss23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.external.navision.NavisionImportSteps;
import com.tests.BaseTest;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001PayAndImportOrderInNavisionTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public NavisionImportSteps navisionImportSteps;

	private OrderModel orderModel;

	List<SyncInfoModel> syncronizedMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> syncronizedNavProducts = new ArrayList<SyncInfoModel>();

	@Before
	public void setUp() throws Exception {
		orderModel = MongoReader.getOrderModel("US23001BuyProductsOnShopforMyselfTest").get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us23001PayAndImportOrderInNavisionTest() throws SQLException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModel.getOrderId());
		ordersSteps.openOrder(orderModel.getOrderId());
		ordersSteps.markOrderAsPaid();

		navisionImportSteps.importOrderInNav(UrlConstants.IMPORT_INTERFACE_URL, orderModel.getOrderId());

	}
}

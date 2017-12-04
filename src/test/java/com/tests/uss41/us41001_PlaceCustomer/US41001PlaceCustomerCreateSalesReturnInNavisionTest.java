package com.tests.uss41.us41001_PlaceCustomer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionSalesInvoicePostedCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.external.navision.NavisionHomeSteps;
import com.steps.external.navision.NavisionImportSteps;
import com.tests.BaseTest;
import com.tests.us8.us8001.US8001CustomerBuyWithForthyDiscountsAndJbTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.navision.SalesInvoicePosted;
import com.tools.data.navision.SyncInfoModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US41.1 Invoice and Return", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001PlaceCustomerCreateSalesReturnInNavisionTest extends BaseTest {

	
	@Steps
	public NavisionHomeSteps navisionSteps;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public NavisionImportSteps navisionImportSteps;
	@Steps
	public CustomVerification customVerifications;

	private OrderModel orderModel;
	List<SyncInfoModel> syncronizedMagentoProducts = new ArrayList<SyncInfoModel>();
	List<SyncInfoModel> syncronizedNavProducts = new ArrayList<SyncInfoModel>();
	private String postedOrderNo;

	@Before
	public void setUp() throws Exception {
		
		orderModel = MongoReader.getOrderModel("US41001OrderOnlyZzzProductsForCustomerTest").get(0);
		SalesInvoicePosted postOrder = NavisionSalesInvoicePostedCalls.orderInfo(orderModel.getOrderId());
		postedOrderNo=postOrder.getNo();
	}

	@Test
	public void us41001PlaceCustomerCreateSalesReturnInNavisionTest() throws Exception {
		
//		/navisionSteps.performLoginIntoNavisonWebClient();
		navisionSteps.createSalesOrderReturn(orderModel.getOrderId(),postedOrderNo);
	//	navisionSteps.postOrder(orderModel.getOrderId());
	//	navisionSteps.postOrder("10033381200");
		customVerifications.printErrors();
	}
}

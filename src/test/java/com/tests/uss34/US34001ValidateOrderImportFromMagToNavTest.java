package com.tests.uss34;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionSoapCalls;
import com.connectors.http.OrdersInfoMagentoCalls;

import com.steps.backend.ImportOrdersSteps;
import com.tests.BaseTest;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US34001ValidateOrderImportFromMagToNavTest extends BaseTest {

	@Steps
	public ImportOrdersSteps ordersImport;
	
	public List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
	public List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();

	@Before
	public void setUp() throws Exception {
		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("103587", "103587");
		navListOrders = NavisionSoapCalls.getOrdersList("100106871");
		
		
	}

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		
	}
}

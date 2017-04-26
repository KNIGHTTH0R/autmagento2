package com.tests.uss34;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionSoapCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.steps.backend.ImportOrdersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US34.1 Order Import to Navision", type = "Scenarios")
@Story(Application.ValidateOrderImport.US34_1.class)
@RunWith(SerenityRunner.class)
public class US34001ValidateOrderImportFromMagToNavTest extends BaseTest {

	@Steps
	public ImportOrdersSteps ordersImport;

	@Steps
	CustomVerification customVerification;
	
	private List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
	private List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();


	@Before
	public void setUp() throws Exception {
		
		// last range ("209874","210900")
//		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("10021681500 ","10021787300");
//		navListOrders = NavisionSoapCalls.getOrdersList("10021681500 ..10021787300");
//		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("10021787100","10021789400");
//		navListOrders = NavisionSoapCalls.getOrdersList("10021787100..10021789400");
//		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("209874","210900");
//		navListOrders = NavisionSoapCalls.getOrdersList("209874..210900");
		//shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("211021","211943");
		
		//last range ("209975","210999");
		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("220140","221140");
		navListOrders = NavisionSoapCalls.getOrdersList("220140..221140");
		
		
//		
//		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("218510","218510");
//		navListOrders = NavisionSoapCalls.getOrdersList("218510..218510");

		}
	
	

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		
		ordersImport.listOfOrdersNotImportedInNav();
		ordersImport.ordersWithProblemslist();
		
		customVerification.printErrors();
		ordersImport.generateImportOrdersReportList(shopListOrders.size());
		
		
	}
}

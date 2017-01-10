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
	//10021957500
	
//		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("103587", "103587");
//		navListOrders = NavisionSoapCalls.getOrdersList("100106871");
		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("212986","212986");
		navListOrders = NavisionSoapCalls.getOrdersList("10022006500");
		
	}

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		customVerification.printErrors();
	}
}

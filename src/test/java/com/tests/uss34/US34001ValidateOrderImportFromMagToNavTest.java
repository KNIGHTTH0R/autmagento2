package com.tests.uss34;

import java.util.ArrayList;
import java.util.Arrays;
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
	//before
//	private List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
//	private List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();
	///
	private List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
	private List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();
	
	List<String> shopOrderList = new ArrayList<String>(
		  // Arrays.asList("212000", "212952","212974"));
//			Arrays.asList("213310","213311"));
		//	Arrays.asList("213383")); <- configurable
		//	Arrays.asList("213385")); <- bundle
			Arrays.asList("213404")); 
	
	
	@Before
	public void setUp() throws Exception {

		
		//before
	//	shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("212000","212000");
	//	navListOrders = NavisionSoapCalls.getOrdersList("10021901100");
		///
		
		for (String shOrder : shopOrderList) {
			
			//am lasat lista pt ca metodele returneaza liste
			List<DBOrderModel> dbmodel1=new ArrayList<DBOrderModel>();
			List<NavOrderModel> navListOrders1 = new ArrayList<NavOrderModel>();
			dbmodel1=OrdersInfoMagentoCalls.getOrderWithItems(shOrder,shOrder);
			navListOrders1=NavisionSoapCalls.getOrdersList(dbmodel1.get(0).getIncrementId());
			
			shopListOrders.add(dbmodel1.get(0));
			navListOrders.add(navListOrders1.get(0));
		}
		

		for (NavOrderModel navOrderModel : navListOrders) {
			System.out.println(navOrderModel.toString());
		}
		
		for (DBOrderModel dbmodel1 : shopListOrders) {
			System.out.println(dbmodel1.toString());
		}
	}

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		customVerification.printErrors();
	}
}

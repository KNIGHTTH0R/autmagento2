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

//	List<String> shopOrderList = new ArrayList<String>(Arrays.asList("210302","210228 no reference","210243","210253","210254","210255","210314","210321","210329","210493","210545","210644"));
	//"213450","213451", "213452", "213453"));
	//"213456","213557",
//	"213383", "213434", "213428", "213429", "213445", "213448", "213449",
//"213458", "213462", "213466", "213468", "213470", "213471","213467", "213469","213542"));
	// Arrays.asList("213462"));
//	/"213311"

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
		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("209975","209975");
		navListOrders = NavisionSoapCalls.getOrdersList("209975..209975");
	
//		for (String shOrder : shopOrderList) {
//
//			// am lasat lista pt ca metodele returneaza liste
//			List<DBOrderModel> dbmodel1 = new ArrayList<DBOrderModel>();
//			List<NavOrderModel> navListOrders1 = new ArrayList<NavOrderModel>();
//			dbmodel1 = OrdersInfoMagentoCalls.getOrderWithItems(shOrder, shOrder);
//			navListOrders1 = NavisionSoapCalls.getOrdersList(dbmodel1.get(0).getIncrementId());
//			shopListOrders.add(dbmodel1.get(0));
//			if (!navListOrders1.isEmpty()) {
//				navListOrders.add(navListOrders1.get(0));
//			}else{
//				
//			}
//		}
		}
	
	//}

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		
		ordersImport.listOfOrdersNotImportedInNav();
		ordersImport.ordersWithProblemslist();
		
		customVerification.printErrors();
		ordersImport.generateImportOrdersReportList(shopListOrders.size());
		
		
	}
}

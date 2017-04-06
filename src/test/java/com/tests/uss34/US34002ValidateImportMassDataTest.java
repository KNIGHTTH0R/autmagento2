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

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US34002ValidateImportMassDataTest extends BaseTest {
	@Steps
	public ImportOrdersSteps ordersImport;

	@Steps
	CustomVerification customVerification;



	private List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
	private List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();
	int upperRange = 210175;
	int lowerRange = 209975;
	int limit = 100;
	int max = 0;
	String minimum;
	String maximum;

	@Before
	public void setUp() throws Exception {

		// last range ("209874","210900")

		// shopListOrders =
		// OrdersInfoMagentoCalls.getOrdersInRangeList("10021681500
		// ","10021787300");
		// navListOrders = NavisionSoapCalls.getOrdersList("10021681500
		// ..10021787300");

//		shopListOrders = OrdersInfoMagentoCalls.getOrdersInRangeList("211021","211021");
//		navListOrders = NavisionSoapCalls.getOrdersList("211021..211021");

		
		while(max<upperRange){
			max=lowerRange+limit;
			if(max>upperRange){
				max=upperRange;
				//da.add(Integer.toString(min) +" "+Integer.toString(max));
				minimum=Integer.toString(lowerRange);
				maximum=Integer.toString(max);
				shopListOrders.addAll( OrdersInfoMagentoCalls.getOrdersInRangeList(minimum,maximum));
				navListOrders.addAll(NavisionSoapCalls.getOrdersList(minimum+".."+maximum));
				
			}else{
			//	da.add(Integer.toString(min) +" "+Integer.toString(max));
				minimum=Integer.toString(lowerRange);
				maximum=Integer.toString(max);
				shopListOrders.addAll( OrdersInfoMagentoCalls.getOrdersInRangeList(minimum,maximum));
				navListOrders.addAll(NavisionSoapCalls.getOrdersList(minimum+".."+maximum));
				lowerRange=max+1;
				
			}
		}
	}

	@Test
	public void us34002ValidateImportMassDataTest() throws Exception {
		
		System.out.println("shopListOrders "+shopListOrders.size());
		
		System.out.println("navListOrders "+navListOrders.size());
//		 ordersImport.validateMassOrders(shopListOrders, navListOrders);
//		//
//		// ordersImport.listOfOrdersNotImportedInNav();
//		// //ordersImport.ordersWithProblemslist();
//		//
//		 customVerification.printErrors();
//		 ordersImport.generateMassImportOrdersReport(shopListOrders.size());
		// 
	}
}

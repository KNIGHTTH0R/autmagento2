package com.tests.uss39;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.connectors.http.NavisionSoapCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.connectors.http.StylistInventoryUpdateBorrowCalls;
import com.steps.backend.ImportOrdersSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.data.soap.StylistInvetoryUpdateModel;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US39.1 Stylist inventory borrow", type = "Scenarios")
// @RunWith(SerenityRunner.class)
public class StylistInvetoryBorrow {

	public static void main(String[] args) throws Exception {
		
		String orderIncrementId="10026671700";
		String customerId="123206";
		List<NavOrderLinesModel> billOfMaterial = NavisionBillOfMaterialCalls
				.getItemsList("xxxx");
		
		for (NavOrderLinesModel navOrderLinesModel : billOfMaterial) {
			String[] sku=navOrderLinesModel.getNo().toString().split("-");
			if(navOrderLinesModel.toString().contains("-")){
				
				StylistInvetoryUpdateModel model = StylistInventoryUpdateBorrowCalls.getBorrowUpdateInfo(customerId,orderIncrementId,sku[0],sku[1]);
				System.out.println("model: "+model.getResult());
				if(!model.getResult().contentEquals("1")){
					System.out.println("ASSSSSSSSERRRRRRRRRRRRRRRRT");
				}
			}else{
				StylistInvetoryUpdateModel model = StylistInventoryUpdateBorrowCalls.getBorrowUpdateInfo(customerId,orderIncrementId,sku[0],"");
				if(!model.getResult().contentEquals("1")){
					System.out.println("ASSSSSSSSERRRRRRRRRRRRRRRRT");
				}
			}
		}
		
		
	}


	/*@Steps
	CustomVerification customVerification;
	
	private List<DBOrderModel> shopListOrders = new ArrayList<DBOrderModel>();
	private List<NavOrderModel> navListOrders = new ArrayList<NavOrderModel>();


	@Before
	public void setUp() throws Exception {
		
		shopListOrders = OrdersInfoMagentoCalls.getOrderWithItems("231915","231915");
		navListOrders = NavisionSoapCalls.getOrdersList("231915..231915");

		}

	@Test
	public void us34001ValidateOrderIImportFromMagToNavTest() throws Exception {
		ordersImport.validateOrders(shopListOrders, navListOrders);
		
		ordersImport.listOfOrdersNotImportedInNav();
		ordersImport.ordersWithProblemslist();
		
		customVerification.printErrors();
		ordersImport.generateImportOrdersReportList(shopListOrders.size());
		
		
	}*/
	
}

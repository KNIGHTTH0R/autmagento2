package com.tests.uss30;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.termPurchase.TermPurchaseGridSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.PrintUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US30001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public TermPurchaseGridSteps termPurchaseGridSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public HostOrderProductsWorkflows hostOrderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public TermPurcaseOrderValidationWorkflows termPurcaseOrderValidationWorkflows;

	TermPurchaseOrderModel expectedModel3;
	TermPurchaseOrderModel expectedModel4;

	private static List<HostBasicProductModel> productsListTp3 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp3 = new ProductDetailedModel();
	OrderModel orderModelListTp3 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp4 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp4 = new ProductDetailedModel();
	OrderModel orderModelListTp4 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp3 = MongoReader.getOrderModel("US30001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		detailedProdListTp3 = MongoReader.grabProductDetailedModel("US30001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		productsListTp3 = MongoReader.grabHostBasicProductModel("US30001PartyHostBuysForCustomerTpTest" + "TP3");
		
		orderModelListTp4 = MongoReader.getOrderModel("US30001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		detailedProdListTp4 = MongoReader.grabProductDetailedModel("US30001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		productsListTp4 = MongoReader.grabHostBasicProductModel("US30001PartyHostBuysForCustomerTpTest" + "TP4");
		
		expectedModel3 = new TermPurchaseOrderModel();
		expectedModel3.setIncrementId(orderModelListTp3.getOrderId());
		//expectedModel1.setExecutionDate(DateUtils.addDaysToAAGivenDate(productsListTp1.get(0).getDeliveryDate(), "yyyy-MM-dd", 7));
		expectedModel3.setExecutionDate(productsListTp3.get(0).getDeliveryDate());
		expectedModel3.setProductSku(productsListTp3.get(0).getProdCode());
		expectedModel3.setIsDiscontinued(detailedProdListTp3.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setEarliestAv(detailedProdListTp3.getStockData().getEarliestAvailability());
		expectedModel3.setInStock(detailedProdListTp3.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setMinimumQty(detailedProdListTp3.getStockData().getMinQty());
		expectedModel3.setBoughtQty(productsListTp3.get(0).getQuantity());
		expectedModel3.setReason("");
		expectedModel3.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel3.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_COMPLETE);
		expectedModel3.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel3.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp3.getStockData().getQty(), productsListTp3.get(0).getQuantity()));
		
		expectedModel4 = new TermPurchaseOrderModel();
		expectedModel4.setIncrementId(orderModelListTp4.getOrderId());
		expectedModel4.setExecutionDate(productsListTp4.get(0).getDeliveryDate());
		expectedModel4.setProductSku(productsListTp4.get(0).getProdCode());
		expectedModel4.setIsDiscontinued(detailedProdListTp4.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel4.setEarliestAv(detailedProdListTp4.getStockData().getEarliestAvailability());
		expectedModel4.setInStock(detailedProdListTp4.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel4.setMinimumQty(detailedProdListTp4.getStockData().getMinQty());
		expectedModel4.setBoughtQty(productsListTp4.get(0).getQuantity());
		expectedModel4.setReason("");
		expectedModel4.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel4.setOrderStatus(ConfigConstants.CANCELED);
		expectedModel4.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel4.setProductQty(detailedProdListTp4.getStockData().getQty());


	}

	@Test
	public void us30001ValidatePostponedOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModelListTp3.getOrderId());
		backEndSteps.openOrderDetails(orderModelListTp3.getOrderId());
		ordersSteps.markOrderAsPaid();
		
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp3.getOrderId());
		//termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp1.getOrderId());
		TermPurchaseOrderModel grabbedModel3 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp3.getOrderId());
		
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModelListTp4.getOrderId());
		backEndSteps.openOrderDetails(orderModelListTp4.getOrderId());
		ordersSteps.cancelOrder();
		
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp4.getOrderId());
	//	termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp1.getOrderId());
		TermPurchaseOrderModel grabbedModel4 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp4.getOrderId());

		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel3, expectedModel3);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel4, expectedModel4);
		
		

		customVerifications.printErrors();
	

	}
	
	

}

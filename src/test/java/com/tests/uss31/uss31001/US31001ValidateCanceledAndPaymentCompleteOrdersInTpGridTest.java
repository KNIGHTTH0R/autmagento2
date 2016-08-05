package com.tests.uss31.uss31001;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.termPurchase.TermPurchaseGridSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.JenkinsConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest extends BaseTest {

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

	TermPurchaseOrderModel expectedModel1;
	TermPurchaseOrderModel expectedModel2;

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp2 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();
	OrderModel orderModelListTp3 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP3");
		
		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		productsListTp2 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP4");
		
		orderModelListTp3 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP5").get(0);
		
		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel1.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel1.setReason(ConfigConstants.REASON_POSTPONED_EARLIEST_AV_CHANGED);
		expectedModel1.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_COMPLETE);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(), productsListTp1.get(0).getQuantity()));
		
		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp2.getOrderId());
		expectedModel2.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel2.setProductSku(productsListTp2.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(detailedProdListTp2.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp2.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp2.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp2.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp2.get(0).getQuantity());
		expectedModel2.setReason(ConfigConstants.REASON_POSTPONED_EARLIEST_AV_CHANGED);
		expectedModel2.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_CANCELED);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel2.setProductQty(detailedProdListTp2.getStockData().getQty());
		
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP3");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP4");

	}

	@Test
	public void us31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModelListTp1.getOrderId());
		backEndSteps.openOrderDetails(orderModelListTp1.getOrderId());
		ordersSteps.markOrderAsPaid();
		
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());
		
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderModelListTp2.getOrderId());
		backEndSteps.openOrderDetails(orderModelListTp2.getOrderId());
		ordersSteps.cancelOrder();
		
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp2.getOrderId());
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp2.getOrderId());

		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);
		
		//razvan cron
		
		//validate ca nu exista
		
		

		customVerifications.printErrors();
	

	}
	
	@After
	public void runCron() throws Exception{
	
		
	    ApacheHttpHelper.sendGet(JenkinsConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp3.getOrderId() + JenkinsConstants.JOB_TOKEN);
	    
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
	    ApacheHttpHelper.sendGet(JenkinsConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
	    ApacheHttpHelper.sendGet(JenkinsConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT);
		
		MongoWriter.saveTermPurchaseModel(expectedModel1,getClass().getSimpleName()+"TP3");
		MongoWriter.saveTermPurchaseModel(expectedModel2,getClass().getSimpleName()+"TP4");
	}
	

}

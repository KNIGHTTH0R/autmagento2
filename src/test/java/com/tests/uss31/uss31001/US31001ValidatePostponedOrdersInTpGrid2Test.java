package com.tests.uss31.uss31001;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US31001ValidatePostponedOrdersInTpGrid2Test extends BaseTest {

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
	
	private static List<HostBasicProductModel> productsListTp = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp = new ProductDetailedModel();
	OrderModel orderModelListTp = new OrderModel();
	OrderModel orderModelListTp2 = new OrderModel();
	
	@Before
	public void setUp() throws ParseException {
		
		orderModelListTp = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP5").get(0);
		detailedProdListTp = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP5").get(0);
		detailedProdListTp.getStockData().setQty("-6");
		detailedProdListTp.getStockData().setUseConfigMinQty("0");
		detailedProdListTp.getStockData().setMinQty("-10");
		detailedProdListTp.getStockData().setIsInStock("1");
		detailedProdListTp.getStockData().setIsDiscontinued("1");
		detailedProdListTp.getStockData().setEarliestAvailability(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", -1));
		productsListTp = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP5");
		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);
		
		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp.getOrderId());
		expectedModel1.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel1.setProductSku(productsListTp.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(detailedProdListTp.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", -1));
		expectedModel1.setInStock(detailedProdListTp.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp.get(0).getQuantity());
		expectedModel1.setReason(ConfigConstants.REASON_POSTPONED_NOT_ENOUGH_STOCK);
		expectedModel1.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel1.setProductQty(detailedProdListTp.getStockData().getQty());
		
		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp.getOrderId());
		expectedModel2.setExecutionDate(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 7));
		expectedModel2.setProductSku(productsListTp.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(detailedProdListTp.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", -1));
		expectedModel2.setInStock(detailedProdListTp.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp.get(0).getQuantity());
		expectedModel2.setReason(ConfigConstants.REASON_POSTPONED_NOT_ENOUGH_STOCK);
		expectedModel2.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel2.setProductQty(detailedProdListTp.getStockData().getQty());
		
		MongoConnector.cleanCollection(getClass().getSimpleName()+ "TP5");
		
	}

	@Test
	public void us31001ValidatePostponedOrdersInTpGrid2Test() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp.getOrderId());
	
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp.getOrderId());
		termPurchaseGridSteps.postponeOrder(orderModelListTp.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.POSTPONE_SUCCESS_MESSAGE);
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp.getOrderId());
		
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);
		
		customVerifications.printErrors();
		
	

	}
	
	@After
	public void runCron() throws Exception{

		
		//script for updating deliveryDates
		
		//ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp2.getOrderId() + EnvironmentConstants.JOB_TOKEN);
		//backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
        ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
        backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
        ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT);
		MongoWriter.saveTermPurchaseModel(expectedModel2,getClass().getSimpleName()+ "TP5");
	}

	
	

}

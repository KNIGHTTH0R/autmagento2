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
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.generalCalculation.StockCalculations;
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
public class US31001ValidateCancelLimitReachedOrdersInTpGridTest extends BaseTest {

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

	TermPurchaseOrderModel expectedModel;
	TermPurchaseOrderModel expectedModel7;
	
	private static List<HostBasicProductModel> productsListTp = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp = new ProductDetailedModel();
	OrderModel orderModelListTp = new OrderModel();

	@Before
	public void setUp() throws ParseException {
		
		orderModelListTp = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);
		detailedProdListTp = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);
		productsListTp = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP6");
		
		expectedModel = new TermPurchaseOrderModel();
		expectedModel.setIncrementId(orderModelListTp.getOrderId());
		expectedModel.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel.setProductSku(productsListTp.get(0).getProdCode());
		expectedModel.setIsDiscontinued(detailedProdListTp.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel.setEarliestAv(detailedProdListTp.getStockData().getEarliestAvailability());
	//	DateUtils.addDaysToAAGivenDate(detailedProdListTp6.getStockData().getEarliestAvailability(), "yyyy-MM-dd", -1)
		expectedModel.setInStock(detailedProdListTp.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel.setMinimumQty(detailedProdListTp.getStockData().getMinQty());
		expectedModel.setBoughtQty(productsListTp.get(0).getQuantity());
		expectedModel.setReason(ConfigConstants.REASON_CANCEL_POSTPONING_LIMIT_REACHED);
		expectedModel.setRecomandation(ConfigConstants.RECOMMENDATION_TO_CANCEL);
		expectedModel.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp.getStockData().getQty(), productsListTp.get(0).getQuantity()));
		
		MongoConnector.cleanCollection(getClass().getSimpleName()+ "TP6");
		
	}

	@Test
	public void us31001ValidateCancelLimitReachedOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp.getOrderId());
		//validate cancel recomandation
		TermPurchaseOrderModel grabbedModel = termPurchaseGridSteps.grabOrderDetails(orderModelListTp.getOrderId());
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel, expectedModel);
        termPurchaseGridSteps.cancelOrder(orderModelListTp.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.CANCEL_SUCCESS_MESSAGE);
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp.getOrderId());
		ordersSteps.openOrder(orderModelListTp.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.CANCELED);

		
		
		customVerifications.printErrors();
	

	}
	
	@After
	public void runCron() throws Exception{

		
	
		
        ApacheHttpHelper.sendGet(JenkinsConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
        backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
        ApacheHttpHelper.sendGet(JenkinsConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT);
		MongoWriter.saveTermPurchaseModel(expectedModel,getClass().getSimpleName() + "TP6");
	}

	
	
	
}

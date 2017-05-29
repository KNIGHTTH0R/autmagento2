package com.tests.uss31.uss31008;

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
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
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

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_1.class)
@RunWith(SerenityRunner.class)
public class US31008ValidateCanceledAndReleasedOrdersInTpGridTest extends BaseTest {

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

	private static List<BasicProductModel> productsListTp1 = new ArrayList<BasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	private static List<BasicProductModel> productsListTp2 = new ArrayList<BasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();
	OrderModel orderModelListTp3 = new OrderModel();
	OrderModel orderModelListTp4 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp1 = MongoReader.getOrderModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP1")
				.get(0);
		detailedProdListTp1.getStockData().setMinQty("-10");
		detailedProdListTp1.getStockData().setQty("-10");
		detailedProdListTp1.getStockData().setIsInStock("0");
		detailedProdListTp1.getStockData().setIsDiscontinued("1");
		productsListTp1 = MongoReader.grabBasicProductModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP1");

		orderModelListTp2 = MongoReader.getOrderModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP2").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP2")
				.get(0);
		detailedProdListTp2.getStockData().setIsDiscontinued("1");
		detailedProdListTp2.getStockData().setQty("1000");
		productsListTp2 = MongoReader.grabBasicProductModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP2");

		orderModelListTp3 = MongoReader.getOrderModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP3").get(0);
		orderModelListTp4 = MongoReader.getOrderModel("US31008SfmWithTpTestSemiAutomatedCronTest" + "TP4").get(0);

		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel1.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(
				detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel1.setReason(ConfigConstants.REASON_CANCEL_SOLD_OUT);
		expectedModel1.setRecomandation(ConfigConstants.RECOMMENDATION_TO_CANCEL);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel1.setProductQty(detailedProdListTp1.getStockData().getQty());

		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp2.getOrderId());
		expectedModel2.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel2.setProductSku(productsListTp2.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(
				detailedProdListTp2.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp2.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp2.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp2.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp2.get(0).getQuantity());
		expectedModel2.setReason("");
		expectedModel2.setRecomandation(ConfigConstants.RECOMMENDATION_TO_RELEASE);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel2.setProductQty(detailedProdListTp2.getStockData().getQty());

		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP2");
	}

	@Test
	public void us31008ValidateCanceledAndReleasedOrdersInTpGridTest() throws ParseException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
		// validate cancel recomandation
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurchaseGridSteps.releaseOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.RELEASE_ERROR_MESSAGE);

		termPurchaseGridSteps.cancelOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.CANCEL_SUCCESS_MESSAGE);
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp1.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp1.getOrderId());
		ordersSteps.openOrder(orderModelListTp1.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.CANCELED);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp2.getOrderId());
		// validate relese recomandation
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp2.getOrderId());
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);

		termPurchaseGridSteps.releaseOrder(orderModelListTp2.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.RELEASE_SUCCESS_MESSAGE);
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp2.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.getOrderId());
		ordersSteps.openOrder(orderModelListTp2.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(),
				ConfigConstants.PAYMENT_IN_PROGRESS);

		customVerifications.printErrors();

	}

	@After
	public void runCron() throws Exception {

		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp3.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp4.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
				EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT, EnvironmentConstants.USERNAME,
				EnvironmentConstants.PASSWORD);

		MongoWriter.saveTermPurchaseModel(expectedModel1, getClass().getSimpleName() + "TP1");
		MongoWriter.saveTermPurchaseModel(expectedModel2, getClass().getSimpleName() + "TP2");
	}

}

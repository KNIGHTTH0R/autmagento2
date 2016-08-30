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
import com.connectors.http.MagentoProductCalls;
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
import com.tools.data.soap.StockDataModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_1.class)
@RunWith(SerenityRunner.class)
public class US31001ValidateOrdersInTpGridTest extends BaseTest {

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
	TermPurchaseOrderModel expectedModel3;
	TermPurchaseOrderModel expectedModel4;
	TermPurchaseOrderModel expectedModel5;
	TermPurchaseOrderModel expectedModel6;

	private String prod5IncrementId;
	private ProductDetailedModel updated5Product;

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp2 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp3 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp3 = new ProductDetailedModel();
	OrderModel orderModelListTp3 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp4 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp4 = new ProductDetailedModel();
	OrderModel orderModelListTp4 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp5 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp5 = new ProductDetailedModel();
	OrderModel orderModelListTp5 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp6 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp6 = new ProductDetailedModel();
	OrderModel orderModelListTp6 = new OrderModel();

	@Before
	public void setUp() {

		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP1")
				.get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP1");

		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP2")
				.get(0);
		productsListTp2 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP2");

		orderModelListTp3 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		detailedProdListTp3 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP3")
				.get(0);
		productsListTp3 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP3");

		orderModelListTp4 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		detailedProdListTp4 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP4")
				.get(0);
		productsListTp4 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP4");

		orderModelListTp5 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP5").get(0);
		detailedProdListTp5 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP5")
				.get(0);
		productsListTp5 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP5");
		prod5IncrementId = MongoReader.grabIncrementId("US31001PartyHostBuysForCustomerTpTest" + "TP5");

		orderModelListTp6 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);
		detailedProdListTp6 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP6")
				.get(0);
		productsListTp6 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP6");

		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel1.setExecutionDate(productsListTp1.get(0).getDeliveryDate());
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(
				detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel1.setReason("");
		expectedModel1.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(),
				productsListTp1.get(0).getQuantity()));

		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp2.getOrderId());
		expectedModel2.setExecutionDate(productsListTp2.get(0).getDeliveryDate());
		expectedModel2.setProductSku(productsListTp2.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(
				detailedProdListTp2.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp2.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp2.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp2.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp2.get(0).getQuantity());
		expectedModel2.setReason("");
		expectedModel2.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel2.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp2.getStockData().getQty(),
				productsListTp2.get(0).getQuantity()));

		expectedModel3 = new TermPurchaseOrderModel();
		expectedModel3.setIncrementId(orderModelListTp3.getOrderId());
		expectedModel3.setExecutionDate(productsListTp3.get(0).getDeliveryDate());
		expectedModel3.setProductSku(productsListTp3.get(0).getProdCode());
		expectedModel3.setIsDiscontinued(
				detailedProdListTp3.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setEarliestAv(detailedProdListTp3.getStockData().getEarliestAvailability());
		expectedModel3.setInStock(detailedProdListTp3.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setMinimumQty(detailedProdListTp3.getStockData().getMinQty());
		expectedModel3.setBoughtQty(productsListTp3.get(0).getQuantity());
		expectedModel3.setReason("");
		expectedModel3.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel3.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel3.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel3.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp3.getStockData().getQty(),
				productsListTp3.get(0).getQuantity()));

		expectedModel4 = new TermPurchaseOrderModel();
		expectedModel4.setIncrementId(orderModelListTp4.getOrderId());
		expectedModel4.setExecutionDate(productsListTp4.get(0).getDeliveryDate());
		expectedModel4.setProductSku(productsListTp4.get(0).getProdCode());
		expectedModel4.setIsDiscontinued(
				detailedProdListTp4.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel4.setEarliestAv(detailedProdListTp4.getStockData().getEarliestAvailability());
		expectedModel4.setInStock(detailedProdListTp4.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel4.setMinimumQty(detailedProdListTp4.getStockData().getMinQty());
		expectedModel4.setBoughtQty(productsListTp4.get(0).getQuantity());
		expectedModel4.setReason("");
		expectedModel4.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel4.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel4.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel4.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp4.getStockData().getQty(),
				productsListTp4.get(0).getQuantity()));

		expectedModel5 = new TermPurchaseOrderModel();
		expectedModel5.setIncrementId(orderModelListTp5.getOrderId());
		expectedModel5.setExecutionDate(productsListTp5.get(0).getDeliveryDate());
		expectedModel5.setProductSku(productsListTp5.get(0).getProdCode());
		expectedModel5.setIsDiscontinued(
				detailedProdListTp5.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel5.setEarliestAv(detailedProdListTp5.getStockData().getEarliestAvailability());
		expectedModel5.setInStock(detailedProdListTp5.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel5.setMinimumQty(detailedProdListTp5.getStockData().getMinQty());
		expectedModel5.setBoughtQty(productsListTp5.get(0).getQuantity());
		expectedModel5.setReason("");
		expectedModel5.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel5.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel5.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel5.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp5.getStockData().getQty(),
				productsListTp5.get(0).getQuantity()));

		expectedModel6 = new TermPurchaseOrderModel();
		expectedModel6.setIncrementId(orderModelListTp6.getOrderId());
		expectedModel6.setExecutionDate(productsListTp6.get(0).getDeliveryDate());
		expectedModel6.setProductSku(productsListTp6.get(0).getProdCode());
		expectedModel6.setIsDiscontinued(
				detailedProdListTp6.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel6.setEarliestAv(detailedProdListTp6.getStockData().getEarliestAvailability());
		expectedModel6.setInStock(detailedProdListTp6.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel6.setMinimumQty(detailedProdListTp6.getStockData().getMinQty());
		expectedModel6.setBoughtQty(productsListTp6.get(0).getQuantity());
		expectedModel6.setReason("");
		expectedModel6.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel6.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel6.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel6.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp6.getStockData().getQty(),
				productsListTp6.get(0).getQuantity()));

	}

	// @Pending
	@Test
	public void us31001ValidateOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp2.getOrderId());
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp2.getOrderId());

		termPurchaseGridSteps.searchForOrder(orderModelListTp3.getOrderId());
		TermPurchaseOrderModel grabbedModel3 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp3.getOrderId());

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp4.getOrderId());
		TermPurchaseOrderModel grabbedModel4 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp4.getOrderId());

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp5.getOrderId());
		TermPurchaseOrderModel grabbedModel5 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp5.getOrderId());

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp6.getOrderId());
		TermPurchaseOrderModel grabbedModel6 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp6.getOrderId());

		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel3, expectedModel3);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel4, expectedModel4);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel5, expectedModel5);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel6, expectedModel6);

		customVerifications.printErrors();

	}

	@After
	// @Test
	public void runCron() throws Exception {

		// 1 postponed order stock remains the same
		// 2 postponed- make modification to obtain wanted reason
		// we modify the fifth product to have:recomandion to postpone because
		// the stock is not enough
		// and for this is neccesary to set earliest_av in the past (today-1day)
		updated5Product = new ProductDetailedModel();
		StockDataModel stockModel = new StockDataModel();
		stockModel.setQty("-6");
		stockModel.setUseConfigMinQty("0");
		stockModel.setMinQty("-10");
		stockModel.setIsInStock("1");
		stockModel.setIsDiscontinued("1");
		stockModel.setEarliestAvailability(
				DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", -1));
		updated5Product.setStockData(stockModel);
		MagentoProductCalls.updateApiProduct(updated5Product, prod5IncrementId);

		// 3 postpoend - make modifications to obtain the 3rd wanted reson

		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp1.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
				EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
	}

}

package com.tests.uss31.uss31004;

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
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US31.1 TP execution cron - Automated Only Release", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_4.class)
@RunWith(SerenityRunner.class)
public class US31004ValidateCanceledAndReleasedOrdersInTpGridTest extends BaseTest {

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

	TermPurchaseOrderModel expectedModel2;

	private static List<HostBasicProductModel> productsListTp2 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();
	OrderModel orderModelListTp3 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp3 = MongoReader.getOrderModel("US31004PartyHostBuysForCustomerTpTest" + "TP3").get(0);

		orderModelListTp2 = MongoReader.getOrderModel("US31004PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US31004PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		detailedProdListTp2.getStockData().setMinQty("-10");
		detailedProdListTp2.getStockData().setQty("-10");
		detailedProdListTp2.getStockData().setIsInStock("0");
		productsListTp2 = MongoReader.grabHostBasicProductModel("US31004PartyHostBuysForCustomerTpTest" + "TP2");

		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp2.getOrderId());
		expectedModel2.setExecutionDate(DateUtils.getCurrentDate("yyyy-MM-dd"));
		expectedModel2.setProductSku(productsListTp2.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(detailedProdListTp2.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp2.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp2.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp2.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp2.get(0).getQuantity());
		expectedModel2.setReason(ConfigConstants.REASON_CANCEL_SOLD_OUT);
		expectedModel2.setRecomandation(ConfigConstants.RECOMMENDATION_TO_CANCEL);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel2.setProductQty(detailedProdListTp2.getStockData().getQty());

	}

	@Test
	public void us31004ValidateCanceledAndReleasedOrdersInTpGridTest() throws ParseException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.getOrderId());
		ordersSteps.openOrder(orderModelListTp2.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.PAYMENT_ON_HOLD);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp3.getOrderId());
		ordersSteps.openOrder(orderModelListTp3.getOrderId());
		// TODO change PAYMENT_IN_PROGRESS in PAYMENT_COMPLETE
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.PAYMENT_IN_PROGRESS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp3.getOrderId());
		termPurchaseGridSteps.searchForOrder(orderModelListTp2.getOrderId());
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp2.getOrderId());
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);

		customVerifications.printErrors();

	}

}

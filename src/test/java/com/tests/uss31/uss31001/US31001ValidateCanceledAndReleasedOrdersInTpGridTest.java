package com.tests.uss31.uss31001;

import java.text.ParseException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.termPurchase.TermPurchaseGridSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US31001ValidateCanceledAndReleasedOrdersInTpGridTest extends BaseTest {

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

	OrderModel orderModelListTp1 = new OrderModel();
	OrderModel orderModelListTp2 = new OrderModel();

	private String prod2IncrementId;
	private ProductDetailedModel updatedProduct;

	@Before
	public void setUp() throws ParseException {

		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);

		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		prod2IncrementId = MongoReader.grabIncrementId("US31001PartyHostBuysForCustomerTpTest" + "TP2");

		updatedProduct = MagentoProductCalls.updateProductStockModel();
		MagentoProductCalls.updateApiProduct(updatedProduct, prod2IncrementId);
	}

	@Test
	public void us31001ValidateCanceledAndReleasedOrdersInTpGridTest() throws ParseException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
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
		termPurchaseGridSteps.releaseOrder(orderModelListTp2.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.RELEASE_SUCCESS_MESSAGE);
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp2.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.getOrderId());
		ordersSteps.openOrder(orderModelListTp2.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.PAYMENT_IN_PROGRESS);

		customVerifications.printErrors();

	}

}

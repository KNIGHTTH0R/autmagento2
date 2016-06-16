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

import com.connectors.http.MagentoProductCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.termPurchase.TermPurchaseGridSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderItemModel;
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
public class US11007ValidateCanceledAndReleasedOrdersInTpGridTest extends BaseTest {

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

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp2 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();
	
	private String prod2IncrementId;
	private ProductDetailedModel updatedProduct;

	@Before
	public void setUp() throws ParseException {

		orderModelListTp1 = MongoReader.getOrderModel("US30001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US30001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US30001PartyHostBuysForCustomerTpTest" + "TP1");

		orderModelListTp2 = MongoReader.getOrderModel("US30001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US30001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		productsListTp2 = MongoReader.grabHostBasicProductModel("US30001PartyHostBuysForCustomerTpTest" + "TP2");
		prod2IncrementId = MongoReader.grabIncrementId("US30001PartyHostBuysForCustomerTpTest" + "TP2");
		
		updatedProduct = MagentoProductCalls.updateProductStockModel();
		MagentoProductCalls.updateApiProduct(updatedProduct, prod2IncrementId);
	}

	@Test
	public void us11007ValidateCanceledAndReleasedOrdersInTpGridTest() throws ParseException {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.cancelOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp1.getOrderId());

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp1.getOrderId());
		ordersSteps.openOrder(orderModelListTp1.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.CANCELED);

		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp2.getOrderId());
		termPurchaseGridSteps.releaseOrder(orderModelListTp2.getOrderId());
		termPurchaseGridSteps.checkOrderIsNotPresentInGrid(orderModelListTp2.getOrderId());
		
		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.getOrderId());
		ordersSteps.openOrder(orderModelListTp2.getOrderId());
		orderWorkflows.validateOrderStatus(ordersSteps.grabOrderInfo().getOrderStatus(), ConfigConstants.PAYMENT_IN_PROGRESS);
		
		customVerifications.printErrors();

	}

}

package com.tests.uss31.uss31001;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
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

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	private static List<HostBasicProductModel> productsListTp2 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp2 = new ProductDetailedModel();
	OrderModel orderModelListTp2 = new OrderModel();
	
	private static List<HostBasicProductModel> productsListTp3 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp3 = new ProductDetailedModel();
	OrderModel orderModelListTp3= new OrderModel();
	
	private static List<HostBasicProductModel> productsListTp4 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp4 = new ProductDetailedModel();
	OrderModel orderModelListTp4 = new OrderModel();

	@Before
	public void setUp() {

		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP1");
		
		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		detailedProdListTp2 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		productsListTp2 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP2");
		
		orderModelListTp3 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		detailedProdListTp3 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP3").get(0);
		productsListTp3 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP3");
		
		orderModelListTp4 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		detailedProdListTp4 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP4").get(0);
		productsListTp4 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP4");

		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel1.setExecutionDate(productsListTp1.get(0).getDeliveryDate());
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel1.setReason("");
		expectedModel1.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(), productsListTp1.get(0).getQuantity()));
		
		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp2.getOrderId());
		expectedModel2.setExecutionDate(productsListTp2.get(0).getDeliveryDate());
		expectedModel2.setProductSku(productsListTp2.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(detailedProdListTp2.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp2.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp2.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp2.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp2.get(0).getQuantity());
		expectedModel2.setReason("");
		expectedModel2.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel2.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp2.getStockData().getQty(), productsListTp2.get(0).getQuantity()));
		
		expectedModel3 = new TermPurchaseOrderModel();
		expectedModel3.setIncrementId(orderModelListTp3.getOrderId());
		expectedModel3.setExecutionDate(productsListTp3.get(0).getDeliveryDate());
		expectedModel3.setProductSku(productsListTp3.get(0).getProdCode());
		expectedModel3.setIsDiscontinued(detailedProdListTp3.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setEarliestAv(detailedProdListTp3.getStockData().getEarliestAvailability());
		expectedModel3.setInStock(detailedProdListTp3.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel3.setMinimumQty(detailedProdListTp3.getStockData().getMinQty());
		expectedModel3.setBoughtQty(productsListTp3.get(0).getQuantity());
		expectedModel3.setReason("");
		expectedModel3.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel3.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
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
		expectedModel4.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel4.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel4.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp4.getStockData().getQty(), productsListTp4.get(0).getQuantity()));

	}

	@Test
	public void us30001ValidateOrdersInTpGridTest() throws ParseException {
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
		
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel3, expectedModel3);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel4, expectedModel4);
		

		customVerifications.printErrors();
	

	}
	
	

}

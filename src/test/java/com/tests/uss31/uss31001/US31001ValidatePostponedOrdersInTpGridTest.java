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

@WithTag(name = "US11.7 Party Host Buys For Customer With Term Purchase Test", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_7.class)
@RunWith(SerenityRunner.class)
public class US31001ValidatePostponedOrdersInTpGridTest extends BaseTest {

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
	
	private String prod1IncrementId, prod2IncrementId;
	private ProductDetailedModel updated1Product, updated2Product;

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();
	OrderModel orderModelListTp2 = new OrderModel();

	@Before
	public void setUp() throws ParseException {
		
		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP1");
		prod1IncrementId = MongoReader.grabIncrementId("US31001PartyHostBuysForCustomerTpTest" + "TP1");
		
		orderModelListTp2 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP2").get(0);
		prod2IncrementId = MongoReader.grabIncrementId("US31001PartyHostBuysForCustomerTpTest" + "TP2");
		
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
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.PENDING);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(), productsListTp1.get(0).getQuantity()));
		
		expectedModel2 = new TermPurchaseOrderModel();
		expectedModel2.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel2.setExecutionDate(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 7));
		expectedModel2.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel2.setIsDiscontinued(detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel2.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel2.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel2.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel2.setReason(ConfigConstants.REASON_POSTPONED_EARLIEST_AV_CHANGED);
		expectedModel2.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel2.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel2.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel2.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(), productsListTp1.get(0).getQuantity()));
	
		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
	
		
	}

	@Test
	public void us31001ValidatePostponedOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
	
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.postponeOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.POSTPONE_SUCCESS_MESSAGE);
		TermPurchaseOrderModel grabbedModel2 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());
		
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel2, expectedModel2);
		
		customVerifications.printErrors();
	

	}
	
	@After
	public void runCron() throws Exception{
		//update the first product to have recommendation to cancel, because at least one item is sold out
		updated1Product = MagentoProductCalls.updateProductStockModel();
		updated1Product.getStockData().setMinQty("-10");
		updated1Product.getStockData().setQty("-10");
		updated1Product.getStockData().setIsInStock("0");
		updated1Product.getStockData().setIsDiscontinued("1");
		MagentoProductCalls.updateApiProduct(updated1Product, prod1IncrementId);
		
        //update the second product to have recommendation to release
		updated2Product = MagentoProductCalls.updateProductStockModel();
		updated2Product.getStockData().setIsDiscontinued("1");
		MagentoProductCalls.updateApiProduct(updated2Product, prod2IncrementId);
		
		
		//script for updating deliveryDates to today
		ApacheHttpHelper.sendGet(JenkinsConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp1.getOrderId() + JenkinsConstants.JOB_TOKEN);
		ApacheHttpHelper.sendGet(JenkinsConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp2.getOrderId() + JenkinsConstants.JOB_TOKEN);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
        ApacheHttpHelper.sendGet(JenkinsConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
        backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
        ApacheHttpHelper.sendGet(JenkinsConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT);
        
		MongoWriter.saveTermPurchaseModel(expectedModel2,getClass().getSimpleName()+"TP1");
        
        
	}

	
	

}

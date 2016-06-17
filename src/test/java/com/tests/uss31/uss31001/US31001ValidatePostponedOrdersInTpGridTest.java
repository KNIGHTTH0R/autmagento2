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

	private static List<HostBasicProductModel> productsListTp1 = new ArrayList<HostBasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	OrderModel orderModelListTp1 = new OrderModel();

	@Before
	public void setUp() throws ParseException {

		orderModelListTp1 = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31001PartyHostBuysForCustomerTpTest" + "TP1").get(0);
		productsListTp1 = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP1");
		
		
		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());
		expectedModel1.setExecutionDate(DateUtils.addDaysToAAGivenDate(productsListTp1.get(0).getDeliveryDate(), "yyyy-MM-dd", 7));
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		expectedModel1.setBoughtQty(productsListTp1.get(0).getQuantity());
		expectedModel1.setReason(ConfigConstants.REASON_POSTPONED_BY_ADMIN);
		expectedModel1.setRecomandation(ConfigConstants.NO_RECOMMENDATION);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(), productsListTp1.get(0).getQuantity()));
		
	}

	@Test
	public void us31001ValidatePostponedOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());
	
		termPurchaseGridSteps.postponeOrder(orderModelListTp1.getOrderId());
		termPurchaseGridSteps.validateMessage(ConfigConstants.POSTPONE_SUCCESS_MESSAGE);
	
		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps.grabOrderDetails(orderModelListTp1.getOrderId());
		
		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);
		
		customVerifications.printErrors();
	

	}
	
	

}

package com.tests.uss31.us31005;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
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

@WithTag(name = "US31.1 TP execution cron - Automated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_2.class)
@RunWith(SerenityRunner.class)
public class US31005ValidatePostponedOrdersInTpGridTest extends BaseTest {

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

	private String prod3IncrementId, prod2IncrementId;
	private ProductDetailedModel updated2Product, updated3Product;

	private static List<BasicProductModel> productsListTp1 = new ArrayList<BasicProductModel>();
	private static ProductDetailedModel detailedProdListTp1 = new ProductDetailedModel();
	
	private static List<BasicProductModel> productsListTp2 = new ArrayList<BasicProductModel>();
	OrderModel orderModelListTp1 = new OrderModel();
	OrderModel orderModelListTp2 = new OrderModel();
	OrderModel orderModelListTp3 = new OrderModel();
	//OrderModel orderModelListTp4 = new OrderModel();

	@Before
	public void setUp() throws Exception {

		orderModelListTp1 = MongoReader.getOrderModel("US31005SfmWithTpTestAutomatedCronTest" + "TP1").get(0);
		detailedProdListTp1 = MongoReader.grabProductDetailedModel("US31005SfmWithTpTestAutomatedCronTest" + "TP1")
				.get(0);
		productsListTp1 = MongoReader.grabBasicProductModel("US31005SfmWithTpTestAutomatedCronTest" + "TP1");
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp1.getOrderId() + EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);

		prod2IncrementId = MongoReader.grabIncrementId("US31005SfmWithTpTestAutomatedCronTest" + "TP2");
		orderModelListTp2 = MongoReader.getOrderModel("US31005SfmWithTpTestAutomatedCronTest" + "TP2").get(0);
		productsListTp2 = MongoReader.grabBasicProductModel("US31005SfmWithTpTestAutomatedCronTest" + "TP2");
		
		prod3IncrementId = MongoReader.grabIncrementId("US31005SfmWithTpTestAutomatedCronTest" + "TP3");
		orderModelListTp3 = MongoReader.getOrderModel("US31005SfmWithTpTestAutomatedCronTest" + "TP3").get(0);


		expectedModel1 = new TermPurchaseOrderModel();
		expectedModel1.setIncrementId(orderModelListTp1.getOrderId());

		String exectutionDate = DateUtils.isDateBefore(detailedProdListTp1.getStockData().getEarliestAvailability(),
				DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd")
						? DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 7)
						: detailedProdListTp1.getStockData().getEarliestAvailability();

		expectedModel1.setExecutionDate(exectutionDate);
		expectedModel1.setProductSku(productsListTp1.get(0).getProdCode());
		expectedModel1.setIsDiscontinued(
				detailedProdListTp1.getStockData().getIsDiscontinued().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setEarliestAv(detailedProdListTp1.getStockData().getEarliestAvailability());
		expectedModel1.setInStock(detailedProdListTp1.getStockData().getIsInStock().contentEquals("1") ? "Yes" : "No");
		expectedModel1.setMinimumQty(detailedProdListTp1.getStockData().getMinQty());
		//int boughtQty=Integer.parseInt(productsListTp1.get(0).getQuantity()) +Integer.parseInt(productsListTp2.get(0).getQuantity());
		expectedModel1.setBoughtQty(String.valueOf(productsListTp1.get(0).getQuantity()));
		expectedModel1.setReason(ConfigConstants.REASON_POSTPONED_EARLIEST_AV_CHANGED);
		expectedModel1.setRecomandation(ConfigConstants.RECOMMENDATION_TO_POSTPONE);
		expectedModel1.setOrderStatus(ConfigConstants.TP_GRID_PAYMENT_ON_HOLD);
		expectedModel1.setScheduledPaymentStatus(ConfigConstants.POSTPONED);
		expectedModel1.setProductQty(StockCalculations.calculateStockToInt(detailedProdListTp1.getStockData().getQty(),
				productsListTp1.get(0).getQuantity()));

	}

	@Test
	public void us31005ValidatePostponedOrdersInTpGridTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp1.getOrderId());

		TermPurchaseOrderModel grabbedModel1 = termPurchaseGridSteps
				.grabOrderDetailsAutomatedCron(orderModelListTp1.getOrderId());
		// termPurchaseGridSteps.validateMessage(ConfigConstants.POSTPONE_SUCCESS_MESSAGE);

		termPurcaseOrderValidationWorkflows.verifyTermPurchaseOrderDetails(grabbedModel1, expectedModel1);

		customVerifications.printErrors();

	}

	@After
	public void runCron() throws Exception {

		updated2Product = MagentoProductCalls.updateProductStockModel();
		updated2Product.getStockData().setMinQty("-10");
		updated2Product.getStockData().setQty("-10");
		updated2Product.getStockData().setIsInStock("0");
		updated2Product.getStockData().setIsDiscontinued("1");
		MagentoProductCalls.updateApiProduct(updated2Product, prod2IncrementId);

		updated3Product = MagentoProductCalls.updateProductStockModel();
		updated3Product.getStockData().setIsDiscontinued("1");
		MagentoProductCalls.updateApiProduct(updated3Product, prod3IncrementId);

		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp2.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp3.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
				EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		
//		 backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//		 ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp2.getOrderId() + EnvironmentConstants.JOB_TOKEN);
//		 backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//		 ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp3.getOrderId() + EnvironmentConstants.JOB_TOKEN);
//		 backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//	     ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT);
	}

}

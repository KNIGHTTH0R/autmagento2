package com.tests.uss31.uss31001;

import java.text.ParseException;

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
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.TermPurchase.TermPurcaseOrderValidationWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_1.class)
@RunWith(SerenityRunner.class)
public class US31001ValidatePostponedOrdersInTpGrid3Test extends BaseTest {

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

	OrderModel orderModelListTp;
	private static HostBasicProductModel productsListTp;
	TermPurchaseOrderModel expectedModel;

	@Before
	public void setUp() throws ParseException {

		orderModelListTp = MongoReader.getOrderModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);
		productsListTp = MongoReader.grabHostBasicProductModel("US31001PartyHostBuysForCustomerTpTest" + "TP6").get(0);

		expectedModel = new TermPurchaseOrderModel();
		expectedModel.setIncrementId(orderModelListTp.getOrderId());
		// we add 21 days to initial delivery date because we postponed the
		// order
		// 3 times
		expectedModel
				.setExecutionDate(DateUtils.addDaysToAAGivenDate(productsListTp.getDeliveryDate(), "yyyy-MM-dd", 21));
		System.out.println(
				"Dateeeeeeeeee " + DateUtils.addDaysToAAGivenDate(productsListTp.getDeliveryDate(), "yyyy-MM-dd", 21));

		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP6");

	}

	@Test
	public void us31001ValidatePostponedOrdersInTpGrid3Test() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnTermPurchaseGrid();
		termPurchaseGridSteps.searchForOrder(orderModelListTp.getOrderId());

		for (int i = 1; i <= 3; i++) {
			termPurchaseGridSteps.postponeOrder(orderModelListTp.getOrderId());
		}

		termPurchaseGridSteps.validateMessage(ConfigConstants.POSTPONE_SUCCESS_MESSAGE);

		customVerifications.printErrors();

	}

	@After
	public void runCron() throws Exception {

		ApacheHttpHelper.sendGet(EnvironmentConstants.CHANGE_TP_DELIVERY_URL + orderModelListTp.getOrderId()
				+ EnvironmentConstants.JOB_TOKEN, EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT,
				EnvironmentConstants.USERNAME, EnvironmentConstants.PASSWORD);
		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_POSTPONE_CANCEL_EMAIL_SCRIPT, EnvironmentConstants.USERNAME,
				EnvironmentConstants.PASSWORD);
		MongoWriter.saveTermPurchaseModel(expectedModel, getClass().getSimpleName() + "TP6");
	}

}

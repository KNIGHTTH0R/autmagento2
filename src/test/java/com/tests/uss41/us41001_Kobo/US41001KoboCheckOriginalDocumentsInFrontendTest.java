package com.tests.uss41.us41001_Kobo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US41.1 Regular Cart Check Nav Documents", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001KoboCheckOriginalDocumentsInFrontendTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public RegularUserOrderProductsWorkflows regularUserOrderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProfileNavSteps profileNavSteps;
	@Steps
	public CustomVerification customVerifications;

	private static OrderModel orderModel = new OrderModel();
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");


	@Before
	public void setUp() {

	

		int size = MongoReader.grabCustomerFormModels("US41001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US41001InitialKoboSubscriptionTest" + SoapKeys.GRAB).get(0);
		} else
			System.out.println("The database has no entries");


		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
		orderModel = MongoReader.grabOrderModels("US41001InitialKoboSubscriptionTest")
				.get(0);

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us41001KoboCheckOriginalDocumentsInFrontendTest() {
		frontEndSteps.performLogin(stylistRegistrationData.getEmailName(), stylistRegistrationData.getPassword());
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.goToProfile();
		profileNavSteps.selectMenu(ContextConstants.MEINE_BESTELLUNGEN);
		profileSteps.clickOnOrder(orderModel.getOrderId());
		profileSteps.clickOnDokumenteTab();
//		profileSteps.validateMessage(
//				"If wou wish to return the items, please use the form sent with your package and get the DHL label from here.");
		profileSteps.valdateOriginalInvoiceIsReceived(true);
		profileSteps.valdateOriginalReturnIsReceived(false);

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
	}
}

package com.tests.us6.us6001b;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.StylistRegistrationCartCalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.borrowCart.BorrowCartOrderProductsWorkflows;

@WithTag(name = "US6.1b Sc Registration New Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)
public class US6001bValidateStarterSetOrderInBackendTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public BorrowCartOrderProductsWorkflows borrowCartOrderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private static List<StylistRegistrationCartCalcDetailsModel> calcDetailsModelList = new ArrayList<StylistRegistrationCartCalcDetailsModel>();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();

	private String orderId;

	@Before
	public void setUp() {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US6001bScRegistrationNewCustForbiddenCountryTest");
		shippingModelList = MongoReader.grabShippingModel("US6001bScRegistrationNewCustForbiddenCountryTest");
		calcDetailsModelList = MongoReader.grabStarterSetCartCalcDetailsModels("US6001bScRegistrationNewCustForbiddenCountryTest");

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}

		shopTotalsModel.setSubtotal(shippingModelList.get(0).getSubTotal());
		shopTotalsModel.setShipping(shippingModelList.get(0).getShippingPrice());
		shopTotalsModel.setTotalAmount(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTax(calcDetailsModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");
	}

	@Test
	public void us6001bValidateStarterSetOrderInBackendTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateStarterSetCartCalculationTotals("TOTALS VALIVATION");

        ordersSteps.selectMenu(ConfigConstants.ADYEN_NOTIFICATION_TAB);
		
		ordersSteps.verifyAuthorization(orderInfoModel.getPspReference());
		ordersSteps.verifyCapture(orderInfoModel.getPspReference());
		
		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung erfolgreich");
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}
package com.tests.uss11.us11001;

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

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US11.1 Party Host Buys For Customer With Any Bonnus Applied", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_1.class)
@RunWith(SerenityRunner.class)
public class US11001ValidateOrderBackOfficeTestVDV extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
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

	private static List<HostBasicProductModel> productsList = new ArrayList<HostBasicProductModel>();
	private static List<HostCartCalcDetailsModel> calcDetailsModelList = new ArrayList<HostCartCalcDetailsModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();

	private String orderId;

	@Before
	public void setUp() {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US11001PartyHostBuysForCustomerWithNoBonusTestVDV" + SoapKeys.GRAB);
		productsList = MongoReader.grabHostBasicProductModel("US11001PartyHostBuysForCustomerWithNoBonusTestVDV" + SoapKeys.CALC);
		shippingModelList = MongoReader.grabShippingModel("US11001PartyHostBuysForCustomerWithNoBonusTestVDV" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabHostCartCalcDetailsModels("US11001PartyHostBuysForCustomerWithNoBonusTestVDV" + SoapKeys.CALC);

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}

		if (calcDetailsModelList.size() != 1) {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + calcDetailsModelList, calcDetailsModelList.size() == 1);
		}

		if (shippingModelList.size() != 1) {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + calcDetailsModelList, calcDetailsModelList.size() == 1);
		}
		
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);

		// Setup Data from all models in first test
		// from Shipping calculations
		shopTotalsModel.setSubtotal(shippingModelList.get(0).getSubTotal());
		shopTotalsModel.setShipping(shippingModelList.get(0).getShippingPrice());
		shopTotalsModel.setTotalAmount(shippingModelList.get(0).getTotalAmount());
		// Constants added
		shopTotalsModel.setTax(calcDetailsModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");
		shopTotalsModel.setTotalIP(calcDetailsModelList.get(0).getIpPoints());

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us11001ValidateOrderBackOfficeTestVDV() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderProducts();
		orderTotalsModel = ordersSteps.grabTotals();
		

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateRegularUserCalculationTotals("TOTALS VALIVATION");
		hostOrderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		hostOrderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");
		
		orderInfoModel = ordersSteps.grabOrderInfo();
		ordersSteps.selectMenu(ConfigConstants.ADYEN_NOTIFICATION_TAB);
		ordersSteps.verifyAuthorization(orderInfoModel.getPspReference());
	//	ordersSteps.verifyCaptureOrder(orderInfoModel.getPspReference());
	//	orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung erfolgreich");
//		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung geplant");
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderTotalsModel(orderTotalsModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

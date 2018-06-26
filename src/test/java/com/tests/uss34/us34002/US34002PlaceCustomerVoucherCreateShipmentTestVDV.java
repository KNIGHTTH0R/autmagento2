package com.tests.uss34.us34002;

import java.util.ArrayList;
import java.util.List;

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
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderProductsWorkflows;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(SerenityRunner.class)
public class US34002PlaceCustomerVoucherCreateShipmentTestVDV extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;

	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public OrderProductsWorkflows orderProductsWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public HostOrderProductsWorkflows hostOrderProductsWorkflows;
	

	private String orderId;
	private static List<HostBasicProductModel> productsList = new ArrayList<HostBasicProductModel>();
	private static List<HostCartCalcDetailsModel> calcDetailsModelList = new ArrayList<HostCartCalcDetailsModel>();

	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();

	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();

	@Before
	public void setUp() throws Exception {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.GRAB);
		productsList = MongoReader.grabHostBasicProductModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.CALC);
		shippingModelList = MongoReader.grabShippingModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabHostCartCalcDetailsModels("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.CALC);

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}

		if (calcDetailsModelList.size() != 1) {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + calcDetailsModelList,
					calcDetailsModelList.size() == 1);
		}

		if (shippingModelList.size() != 1) {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + calcDetailsModelList,
					calcDetailsModelList.size() == 1);
		}

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);

		// Setup Data from all models in first test
		// from Shipping calculations
		shopTotalsModel.setSubtotal(shippingModelList.get(0).getSubTotal());
		shopTotalsModel.setShipping(shippingModelList.get(0).getShippingPrice());
		shopTotalsModel.setTotalAmount(shippingModelList.get(0).getTotalAmount());
		// from calcDetails model calculations
		shopTotalsModel.setTotalIP(calcDetailsModelList.get(0).getIpPoints());
		shopTotalsModel.setTotalMarketingBonus("0");
		shopTotalsModel.setTotalBonusJeverly("0");

		shopTotalsModel.setTax(calcDetailsModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");
	}

	@Test
	public void us34002PlaceCustomerVoucherCreateShipmentTestVDV() throws Exception {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSalesOrders();
		backEndSteps.searchOrderByOrderId(orderId);
		backEndSteps.openOrderDetails(orderId);

		ordersSteps.clickShipButton();
		List<OrderItemModel> orderItemsList = ordersSteps.grabShipmentOrderItems();
		// orderTotalsModel = ordersSteps.grabTotals();

		ordersSteps.addTrakingNumber("123456");

		hostOrderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		hostOrderProductsWorkflows.validateProductsOnShipment("PRODUCTS VALIDATION");

		// with send email flag checked
		ordersSteps.submitShipment();
		ordersSteps.validateCompleteStatus();
		// ordersSteps.createOrderShipment();
		backEndSteps.clickOnSalesShipmentOrders();
		backEndSteps.searchShipmentByOrderId(orderId);
		backEndSteps.openShipmentDetails(orderId);

		customVerifications.printErrors();
	}

}

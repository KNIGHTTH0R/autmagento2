package com.tests.us3.us3009;

import java.util.ArrayList;
import java.util.List;

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
import com.tools.constants.Credentials;
import com.tools.constants.SoapKeys;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.OrderProductsWorkflows;
import com.workflows.backend.OrderWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.9 Shop for myself no valid VAT and no SMB billing and shipping DE", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_9.class)
@RunWith(SerenityRunner.class)
public class US3009ValidateOrderBackOfficeTest extends BaseTest {

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

	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<CalcDetailsModel> calcDetailsModelList = new ArrayList<CalcDetailsModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();

	private String orderId;

	@Before
	public void setUp() {

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US3009SfmNoVatNoSmbBillingShippingDeTest" + SoapKeys.GRAB);
		productsList = MongoReader.grabBasicProductModel("US3009SfmNoVatNoSmbBillingShippingDeTest" + SoapKeys.GRAB);
		shippingModelList = MongoReader.grabShippingModel("US3009SfmNoVatNoSmbBillingShippingDeTest" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabCalcDetailsModels("US3009SfmNoVatNoSmbBillingShippingDeTest" + SoapKeys.CALC);

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
		// from calcDetails model calculations
		shopTotalsModel.setTotalIP(calcDetailsModelList.get(0).getIpPoints());
		shopTotalsModel.setTotalMarketingBonus(calcDetailsModelList.get(0).getMarketingBonus());
		shopTotalsModel.setTotalBonusJeverly(calcDetailsModelList.get(0).getJewelryBonus());
		// Constants added
		shopTotalsModel.setTax(calcDetailsModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us3009ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderProducts();
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateCalculationTotals("TOTALS VALIVATION");

		orderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		orderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");
		
//		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung geplant");
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderTotalsModel(orderTotalsModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

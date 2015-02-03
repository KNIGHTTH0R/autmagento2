package com.tests.us3;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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
import com.tools.Constants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.OrderWorkflows;

@WithTag(name = "US003", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US003ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public OrderWorkflows orderWorkflows;

	// public static List<CartTotalsModel> cartTotals = new
	// ArrayList<CartTotalsModel>();
	public static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	public static List<CalcDetailsModel> calcDetailsModelList = new ArrayList<CalcDetailsModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();

	private String orderId;

	@Before
	public void setUp() {

		// TODO add setup config file for backend user and pass
		List<OrderModel> orderModelList = MongoReader.getOrderModel("US003CartSegmentationWithVatTest");

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}

		calcDetailsModelList = MongoReader.grabCalcDetailsModels("US003CartSegmentationWithVatTest");
		if (calcDetailsModelList.size() == 1) {
			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + calcDetailsModelList, calcDetailsModelList.size() == 1);
		}

		productsList = MongoReader.grabProductBasicModel("US003CartSegmentationWithVatTest");
		shippingModelList = MongoReader.grabShippingModel("US003CartSegmentationWithVatTest");

		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us003ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderData();
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		PrintUtils.printOrderItemsList(orderItemsList);
		PrintUtils.printOrderTotals(orderTotalsModel);
		PrintUtils.printOrderInfo(orderInfoModel);
		// TODO work for validation
		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, calcDetailsModelList.get(0));
		orderWorkflows.validateCalculationTotals("TOTALS VALIVATION");

		orderWorkflows.setValidateProductsModels(productsList, orderItemsList);
		orderWorkflows.validateProducts("PRODUCTS VALIDATION");
		shippingModelList.get(0);
		// orderWorkflows.validateOrderStatus(orderInfo.getOrderStatus(),
		// "Zahlung erfolgreich");
		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung geplant");
		// orderValidationSteps.validateProducts(productsList, orderItemsList);
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName());
		MongoWriter.saveOrderTotalsModel(orderTotalsModel, getClass().getSimpleName());

	}
}

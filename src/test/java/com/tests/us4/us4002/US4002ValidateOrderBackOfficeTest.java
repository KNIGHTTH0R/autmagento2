package com.tests.us4.us4002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.tools.CustomVerification;
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
import com.tools.utils.PrintUtils;
import com.workflows.backend.OrderProductsWorkflows;
import com.workflows.backend.OrderWorkflows;

@WithTag(name = "US4002", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US4002ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public OrderProductsWorkflows orderProductsWorkflows;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps 
	public CustomVerification customVerifications;

	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<CalcDetailsModel> calcDetailsModelList = new ArrayList<CalcDetailsModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();

	private String orderId;
	private String beUser,bePass;

	@Before
	public void setUp() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us4" + File.separator + "us4002.properties");
			prop.load(input);
			beUser = prop.getProperty("beUser");
			bePass = prop.getProperty("bePass");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US4002Test" + Constants.GRAB);
		productsList = MongoReader.grabBasicProductModel("US4002Test" + Constants.GRAB);
		shippingModelList = MongoReader.grabShippingModel("US4002Test" + Constants.CALC);
		calcDetailsModelList = MongoReader.grabCalcDetailsModels("US4002Test" + Constants.CALC);

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
		
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);

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
	public void us4002ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(beUser, bePass);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderData();
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		PrintUtils.printOrderItemsList(orderItemsList);
		PrintUtils.printOrderTotals(orderTotalsModel);
		PrintUtils.printOrderInfo(orderInfoModel);

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateCalculationTotals("TOTALS VALIVATION");

		orderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		orderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");
		
		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung geplant");
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveOrderTotalsModel(orderTotalsModel, getClass().getSimpleName() + Constants.GRAB);
	}
}
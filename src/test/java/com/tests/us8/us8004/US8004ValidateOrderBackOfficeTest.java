package com.tests.us8.us8004;

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
import com.tools.CustomVerification;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.env.constants.SoapKeys;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

@WithTag(name = "US8.4 Customer Buy With Kobo", type = "Scenarios")
@Story(Application.RegularCart.US8_4.class)
@RunWith(ThucydidesRunner.class)
public class US8004ValidateOrderBackOfficeTest extends BaseTest {

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
	public CustomVerification customVerifications;

	private static List<RegularBasicProductModel> productsList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularCartCalcDetailsModel> calcDetailsModelList = new ArrayList<RegularCartCalcDetailsModel>();
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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8004.properties");
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

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US8004CustomerBuyWithContactBoosterTest" + SoapKeys.GRAB);
		productsList = MongoReader.grabRegularBasicProductModel("US8004CustomerBuyWithContactBoosterTest" + SoapKeys.CALC);
		shippingModelList = MongoReader.grabShippingModel("US8004CustomerBuyWithContactBoosterTest" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabRegularCartCalcDetailsModels("US8004CustomerBuyWithContactBoosterTest" + SoapKeys.CALC);

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

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us8004ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(beUser, bePass);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderProducts();
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateRegularUserCalculationTotals("TOTALS VALIVATION");

		regularUserOrderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		regularUserOrderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");
		
//		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung geplant");
		
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderInfoModel(orderInfoModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderTotalsModel(orderTotalsModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}
}

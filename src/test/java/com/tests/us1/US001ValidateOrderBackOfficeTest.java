package com.tests.us1;

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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.OrderWorkflows;

@WithTag(name = "US1", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US001ValidateOrderBackOfficeTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public OrderValidationSteps orderValidationSteps;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps 
	public CustomVerification customVerifications;
	
	public static List<CartTotalsModel> cartTotals = new ArrayList<CartTotalsModel>();
	public static List<ProductBasicModel>  productsList = new ArrayList<ProductBasicModel>();

	private String orderId;
	private String beUser,bePass;

	@Before
	public void setUp() {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us1" + File.separator + "us001.properties");
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
		
		List<OrderModel> orderModel = MongoReader.getOrderModel("US001StyleCoachShoppingTest" + SoapKeys.GRAB);

		if (orderModel.size() == 1) {

			orderId = orderModel.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModel.size() == 1);
		}

		cartTotals = MongoReader.grabTotalsModels("US001StyleCoachShoppingTest" + SoapKeys.GRAB);
		if (cartTotals.size() == 1) {

			orderId = orderModel.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not validate Cart Totals Section. " + cartTotals , cartTotals.size() == 1);
		}
		
		productsList = MongoReader.grabProductBasicModel("US001StyleCoachShoppingTest" + SoapKeys.GRAB);		
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us001ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(beUser, bePass);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderId);
		ordersSteps.openOrder(orderId);
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderData();
		OrderTotalsModel ordertotal = ordersSteps.grabTotals();
		OrderInfoModel orderInfo = ordersSteps.grabOrderInfo();
		
		PrintUtils.printOrderItemsList(orderItemsList);
		PrintUtils.printOrderTotals(ordertotal);
		PrintUtils.printOrderInfo(orderInfo);
		orderWorkflows.setValidateTotals(ordertotal, cartTotals.get(0));
		orderWorkflows.validateTotals("TOTALS VALIVATION");
		
		orderWorkflows.setValidateProductsModels(productsList, orderItemsList);
		orderWorkflows.validateProducts("PRODUCTS VALIDATION");
		
//		orderWorkflows.validateOrderStatus(orderInfo.getOrderStatus(), "Zahlung erfolgreich");
//		orderWorkflows.validateOrderStatus(orderInfo.getOrderStatus(), "Zahlung geplant");
		
		customVerifications.printErrors();
	}
}

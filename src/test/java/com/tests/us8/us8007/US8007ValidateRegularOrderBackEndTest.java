package com.tests.us8.us8007;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.regularUser.RegularUserOrderProductsWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US8.7 Customer Buy With Term Purchase Test", type = "Scenarios")
@Story(Application.RegularCart.US8_7.class)
@RunWith(SerenityRunner.class)
public class US8007ValidateRegularOrderBackEndTest extends BaseTest {

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

	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderInfoModel orderInfoModelTp1 = new OrderInfoModel();
	private static OrderInfoModel orderInfoModelTp2 = new OrderInfoModel();

	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel orderTotalsModelTp1 = new OrderTotalsModel();
	private static OrderTotalsModel orderTotalsModelTp2 = new OrderTotalsModel();

	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModelTp1 = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModelTp2 = new OrderTotalsModel();

	private static List<RegularBasicProductModel> productsList = new ArrayList<RegularBasicProductModel>();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();
	List<OrderModel> orderModelList = new ArrayList<OrderModel>();

	private static List<RegularBasicProductModel> productsListTp1 = new ArrayList<RegularBasicProductModel>();
	private static List<ShippingModel> shippingModelListTp1 = new ArrayList<ShippingModel>();
	List<OrderModel> orderModelListTp1 = new ArrayList<OrderModel>();

	private static List<RegularBasicProductModel> productsListTp2 = new ArrayList<RegularBasicProductModel>();
	private static List<ShippingModel> shippingModelListTp2 = new ArrayList<ShippingModel>();
	List<OrderModel> orderModelListTp2 = new ArrayList<OrderModel>();

	@Before
	public void setUp() {

		orderModelList = MongoReader.getOrderModel("US8007CustomerBuyWithTpTest" + "TP0");
		productsList = MongoReader.grabRegularBasicProductModel("US8007CustomerBuyWithTpTest" + "TP0");
		shippingModelList = MongoReader.grabShippingModel("US8007CustomerBuyWithTpTest" + "TP0");

		orderModelListTp1 = MongoReader.getOrderModel("US8007CustomerBuyWithTpTest" + "TP1");
		productsListTp1 = MongoReader.grabRegularBasicProductModel("US8007CustomerBuyWithTpTest" + "TP1");
		shippingModelListTp1 = MongoReader.grabShippingModel("US8007CustomerBuyWithTpTest" + "TP1");

		orderModelListTp2 = MongoReader.getOrderModel("US8007CustomerBuyWithTpTest" + "TP2");
		productsListTp2 = MongoReader.grabRegularBasicProductModel("US8007CustomerBuyWithTpTest" + "TP2");
		shippingModelListTp2 = MongoReader.grabShippingModel("US8007CustomerBuyWithTpTest" + "TP2");

		// Setup Data from all models in first test
		// from Shipping calculations
		shopTotalsModel.setSubtotal(shippingModelList.get(0).getSubTotal());
		shopTotalsModel.setShipping(shippingModelList.get(0).getShippingPrice());
		shopTotalsModel.setTotalAmount(shippingModelList.get(0).getTotalAmount());
		// Constants added
		shopTotalsModel.setTax(shippingModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");

		shopTotalsModelTp1.setSubtotal(shippingModelListTp1.get(0).getSubTotal());
		shopTotalsModelTp1.setShipping(shippingModelListTp1.get(0).getShippingPrice());
		shopTotalsModelTp1.setTotalAmount(shippingModelListTp1.get(0).getTotalAmount());
		// Constants added
		shopTotalsModelTp1.setTax(shippingModelListTp1.get(0).getTax());
		shopTotalsModelTp1.setTotalPaid("0.00");
		shopTotalsModelTp1.setTotalRefunded("0.00");
		shopTotalsModelTp1.setTotalPayable(shippingModelListTp1.get(0).getTotalAmount());
		shopTotalsModelTp1.setTotalFortyDiscounts("0.00");

		shopTotalsModelTp2.setSubtotal(shippingModelListTp2.get(0).getSubTotal());
		shopTotalsModelTp2.setShipping(shippingModelListTp2.get(0).getShippingPrice());
		shopTotalsModelTp2.setTotalAmount(shippingModelListTp2.get(0).getTotalAmount());
		// Constants added
		shopTotalsModelTp2.setTax(shippingModelListTp2.get(0).getTax());
		shopTotalsModelTp2.setTotalPaid("0.00");
		shopTotalsModelTp2.setTotalRefunded("0.00");
		shopTotalsModelTp2.setTotalPayable(shippingModelListTp2.get(0).getTotalAmount());
		shopTotalsModelTp2.setTotalFortyDiscounts("0.00");

	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void us8007ValidateRegularOrderBackEndTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelList.get(0).getOrderId());
		ordersSteps.openOrder(orderModelList.get(0).getOrderId());
		List<OrderItemModel> orderItemsList = ordersSteps.grabOrderProducts();
		orderTotalsModel = ordersSteps.grabTotals();
		orderInfoModel = ordersSteps.grabOrderInfo();

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp1.get(0).getOrderId());
		ordersSteps.openOrder(orderModelListTp1.get(0).getOrderId());
		List<OrderItemModel> orderItemsListTp1 = ordersSteps.grabOrderProducts();
		orderTotalsModelTp1 = ordersSteps.grabTotals();
		orderInfoModelTp1 = ordersSteps.grabOrderInfo();

		backEndSteps.clickOnSalesOrders();
		ordersSteps.findOrderByOrderId(orderModelListTp2.get(0).getOrderId());
		ordersSteps.openOrder(orderModelListTp2.get(0).getOrderId());
		List<OrderItemModel> orderItemsListTp2 = ordersSteps.grabOrderProducts();
		orderTotalsModelTp2 = ordersSteps.grabTotals();
		orderInfoModelTp2 = ordersSteps.grabOrderInfo();

		orderWorkflows.setValidateCalculationTotals(orderTotalsModel, shopTotalsModel);
		orderWorkflows.validateRegularUserCalculationTotals("TOTALS VALIVATION");

		regularUserOrderProductsWorkflows.setValidateProductsModels(productsList, orderItemsList);
		regularUserOrderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");

		orderWorkflows.setValidateCalculationTotals(orderTotalsModelTp1, shopTotalsModelTp1);
		orderWorkflows.validateRegularUserCalculationTotals("TOTALS VALIVATION");

		regularUserOrderProductsWorkflows.setValidateProductsModels(productsListTp1, orderItemsListTp1);
		regularUserOrderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");

		orderWorkflows.setValidateCalculationTotals(orderTotalsModelTp2, shopTotalsModelTp2);
		orderWorkflows.validateRegularUserCalculationTotals("TOTALS VALIVATION");

		regularUserOrderProductsWorkflows.setValidateProductsModels(productsListTp2, orderItemsListTp2);
		regularUserOrderProductsWorkflows.validateProducts("PRODUCTS VALIDATION");

		orderWorkflows.validateOrderStatus(orderInfoModel.getOrderStatus(), "Zahlung wird geprüft");
		orderWorkflows.validateOrderStatus(orderInfoModelTp1.getOrderStatus(), "Zahlung geplant");
		orderWorkflows.validateScheduledDeliveryDate(orderInfoModelTp1.getDeliveryDate(),
				orderInfoModelTp1.getDeliveryDate());
		orderWorkflows.validateOrderStatus(orderInfoModelTp2.getOrderStatus(), "Zahlung geplant");
		orderWorkflows.validateScheduledDeliveryDate(orderInfoModelTp2.getDeliveryDate(),
				orderInfoModelTp2.getDeliveryDate());

		customVerifications.printErrors();
	}

}

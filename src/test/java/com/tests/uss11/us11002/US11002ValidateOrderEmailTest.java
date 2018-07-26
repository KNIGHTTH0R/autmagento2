package com.tests.uss11.us11002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.steps.EmailSteps;
import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.OrderValidationSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.EmailConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderWorkflows;
import com.workflows.backend.partyHost.HostOrderProductsWorkflows;

@WithTag(name = "US11.5 Party Host Buys For Customer With Free Shipping Voucher Test, ship to host", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_5.class)
@RunWith(SerenityRunner.class)
public class US11002ValidateOrderEmailTest extends BaseTest{
	
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
	@Steps
	public EmailClientSteps emailClientSteps;

	private static List<HostBasicProductModel> productsList = new ArrayList<HostBasicProductModel>();
	private static List<HostCartCalcDetailsModel> calcDetailsModelList = new ArrayList<HostCartCalcDetailsModel>();
	private static OrderInfoModel orderInfoModel = new OrderInfoModel();
	private static OrderTotalsModel orderTotalsModel = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();
	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();
	
	public static OrderTotalsModel grabbedTotals = new OrderTotalsModel();
	public static List<OrderItemModel> grabbedProductsList = new ArrayList<OrderItemModel>();

	private String orderId,username;

	@Before
	public void setUp() {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			

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

		List<OrderModel> orderModelList = MongoReader.getOrderModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.GRAB);
		productsList = MongoReader.grabHostBasicProductModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.RECALC);
		shippingModelList = MongoReader.grabShippingModel("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabHostCartCalcDetailsModels("US11002PartyHostBuysForCustomerWithVoucherTestVDV" + SoapKeys.CALC);

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
		shopTotalsModel.setDiscount(shippingModelList.get(0).getDiscountPrice());
	
	}
	
	@Test
	public void us11002ValidateOrderEmailTest() {
		
		emailClientSteps.openEmail(username.replace("@" + ConfigConstants.WEB_MAIL,""),"@MyHome : Ihre Bestellung Nr. "+orderId);
		grabbedProductsList=emailClientSteps.grabbedProductsList();
		grabbedTotals= emailClientSteps.grabbedOrderTotals();
		
	
		orderWorkflows.setValidateCalculationTotals(grabbedTotals, shopTotalsModel);
		orderWorkflows.validateCalculationOrderEmailTotals("TOTALS VALIVATION");
	
		hostOrderProductsWorkflows.setValidateProductsModels(productsList, grabbedProductsList);
		hostOrderProductsWorkflows.validateOrderMailsProducts("PRODUCTS VALIDATION");
		customVerifications.printErrors();
	
	}

}

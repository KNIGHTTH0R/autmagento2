package com.tests.us3.us3001vdv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.EmailSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.OrderProductsWorkflows;
import com.workflows.backend.OrderWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.1 Shop for myself with any bonnus applied",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US3001ValidateOrderEmailTestVDV extends BaseTest{
	
	
	public static void main(String[] args) {
		
	}
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public OrderWorkflows orderWorkflows;
	@Steps
	public OrderProductsWorkflows orderProductsWorkflows;
	
	private String username, orderId;
	private List<OrderModel> orderModel = new ArrayList<OrderModel>();
	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<OrderItemModel> grabbedProductsList = new ArrayList<OrderItemModel>();
	public static List<CalcDetailsModel> calcDetailsModelList = new ArrayList<CalcDetailsModel>();
	public static OrderTotalsModel grabbedTotals = new OrderTotalsModel();
	private static OrderTotalsModel shopTotalsModel = new OrderTotalsModel();

	private static List<ShippingModel> shippingModelList = new ArrayList<ShippingModel>();
	
	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
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
		
		
		List<OrderModel> orderModelList = MongoReader.getOrderModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.GRAB);
		productsList = MongoReader.grabBasicProductModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.RECALC);
		shippingModelList = MongoReader.grabShippingModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.CALC);
		calcDetailsModelList = MongoReader.grabCalcDetailsModels("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.CALC);

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
		
		shopTotalsModel.setTax(calcDetailsModelList.get(0).getTax());
		shopTotalsModel.setTotalPaid("0.00");
		shopTotalsModel.setTotalRefunded("0.00");
		shopTotalsModel.setTotalPayable(shippingModelList.get(0).getTotalAmount());
		shopTotalsModel.setTotalFortyDiscounts("0.00");
		shopTotalsModel.setDiscount(shippingModelList.get(0).getDiscountPrice());
		
		
		
		
	}
	
	@Test
	public void us3001ValidateOrderEmailTestVDV() {
	
		emailClientSteps.openEmail(username.replace("@" + ConfigConstants.WEB_MAIL,""),orderId);
		grabbedProductsList=emailClientSteps.grabbedProductsList();
		grabbedTotals= emailClientSteps.grabbedOrderTotals();
		
		
		orderWorkflows.setValidateCalculationTotals(grabbedTotals, shopTotalsModel);
		orderWorkflows.validateCalculationOrderEmailTotals("TOTALS VALIVATION");

		orderProductsWorkflows.setValidateProductsModels(productsList, grabbedProductsList);
		orderProductsWorkflows.validateOrderMailProducts("PRODUCTS VALIDATION");
	
		
		customVerifications.printErrors();
	}

}

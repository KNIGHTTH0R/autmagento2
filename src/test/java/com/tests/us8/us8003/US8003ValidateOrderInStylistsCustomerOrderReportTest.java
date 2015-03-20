package com.tests.us8.us8003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US8003", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US8003ValidateOrderInStylistsCustomerOrderReportTest extends BaseTest{
//	
//	@Steps
//	public ProfileSteps profileSteps;
	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private static OrderModel orderModel = new OrderModel();
	private String stylistUsername, stylistPassword;
	

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(Constants.RESOURCES_PATH + "us8" + File.separator + "us8003.properties");
			prop.load(input);
			stylistUsername = prop.getProperty("stylistUsername");
			stylistPassword = prop.getProperty("stylistPassword");			

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


		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);
		orderModel = MongoReader.grabOrderModels("US8003CustomerBuyWithForthyDiscountsJbAndBuy3Get1Test" + Constants.GRAB).get(0);
		
	}
	
	@Test
	public void us8003ValidateOrderInStylistsCustomerOrderReportTest() {
		
		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		
		headerSteps.redirectTostylistsCustomerOrderReport();
		List<OrderModel> orderHistory = stylistsCustomerOrdersReportSteps.grabOrderReport();

		String orderId = orderHistory.get(0).getOrderId();
		String orderPrice = orderHistory.get(0).getTotalPrice();
		stylistsCustomerOrdersReportSteps.verifyOrderId(orderId, orderModel.getOrderId());
		stylistsCustomerOrdersReportSteps.verifyOrderPrice(orderPrice, orderModel.getTotalPrice());
		orderModel = orderHistory.get(0);
		
		customVerifications.printErrors();
	}
}

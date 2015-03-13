package com.tests.us4.us4001;

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
import com.steps.frontend.ProfileSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US4001", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US4001UserProfileOrderIdTest extends BaseTest{
	
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private static OrderModel orderModel = new OrderModel();
	private String username, password;
	

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(Constants.RESOURCES_PATH + "us4" + File.separator + "us4001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");			

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
		orderModel = MongoReader.grabOrderModels("US4001Test" + Constants.GRAB).get(0);
		
	}
	
	@Test
	public void us4001UserProfileOrderIdTest() {
		
		frontEndSteps.performLogin(username, password);
		
		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		String orderPrice = orderHistory.get(0).getTotalPrice();
		profileSteps.verifyOrderId(orderId, orderModel.getOrderId());
		profileSteps.verifyOrderPrice(orderPrice, orderModel.getTotalPrice());
		orderModel = orderHistory.get(0);
		
		customVerifications.printErrors();
	}
}

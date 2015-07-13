package com.tests.uss16.us16001;

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

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US16", type = "frontend")
@Story(Application.Shop.BorrowCart.class)
@RunWith(ThucydidesRunner.class)
public class US16001UserProfileOrderIdTest extends BaseTest{
	
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
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
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_16_FOLDER + File.separator + "us16001.properties");
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

		orderModel = MongoReader.grabOrderModels("US16001StyleCoachBorrowCartTest" + SoapKeys.GRAB).get(0);
		
	}
	
	@Test
	public void us16001UserProfileOrderIdTest() {
		
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
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

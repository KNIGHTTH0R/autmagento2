package com.tests.us3.us3003vdv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US3.3 Shop for myself VAT valid and no SMB billing and shipping DE",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_3.class)
@RunWith(SerenityRunner.class)
public class US3003CheckOrderOnStylecoachProfileTestVDV extends BaseTest{
	
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	
	private static OrderModel orderModel = new OrderModel();
	private String username, password;
	

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3003.properties");
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

		orderModel = MongoReader.grabOrderModels("US3003SfmWithLbAppliedTestVDV" + SoapKeys.GRAB).get(0);
		
	}
	
	@Test
	public void us3003CheckOrderOnStylecoachProfileTestVDV() {
		
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

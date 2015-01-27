package com.tests.us3;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.ProfileSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.calculation.CartCalculation;
import com.tools.data.OrderModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.Application;
import com.workflows.frontend.CartWorkflows;


@WithTag(name = "US3", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US003CartSegmentationWithVATTest extends BaseTest {
	
	private String username, password;
	
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public CartCalculation calculusSteps;
	@Steps
	public CheckoutValidationSteps validationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public CartWorkflows cartWorkflows;

	private OrderModel orderNumber = new OrderModel();
	
	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();



	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us3\\us003.properties");
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
	
	}

	@Test
	public void uS003CartSegmentationWithVATTest() {
		frontEndSteps.performLogin("ioana.urcan@evozon.com", "ioana1234");
		ProductBasicModel productData;
		searchSteps.searchAndSelectProduct("K054SV", "WHITNEY SET");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		

	}

	@Test
	public void us002UserProfileOrderId() {

	}

	@After
	public void saveData() {

	}

}

package com.tests.uss15.us15003;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StarterSetSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistContextSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.stylecoachRegistration.AddStarterSetProductsWorkflow;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(SerenityRunner.class)
public class US15003StyleCoachRegistrationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public StylistContextSteps stylistContextSteps;
	@Steps
	public AddStarterSetProductsWorkflow addStarterSetProductsWorkflow;
	@Steps
	public StarterSetSteps starterSetSteps;

	private CreditCardModel creditCardData = new CreditCardModel();
	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	
	private String starterSet,starterKitPrice;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us6" + File.separator + "us6001.properties");
			prop.load(input);

			starterSet = prop.getProperty("starterSet");
			starterKitPrice = prop.getProperty("starterKitPrice");

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
	public void us15003StyleCoachRegistrationTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress, birthDate.getDate());
		
		stylistContextSteps.addStylistReference(customerFormData.getFirstName() + customerFormData.getLastName());

		addStarterSetProductsWorkflow.setStarterSetProductToCart(starterSet,starterKitPrice);
		starterSetSteps.submitStarterSetStep();
		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		
		customerFormDate.setDate(formCreationDate);
	}

	@After
	public void saveData() {

		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}

package com.tests.uss17.us17003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.stylecoachRegistration.AddStarterSetProductsWorkflow;

@WithTag(name = "US17.3 Check reassigned contacts up on canceled SC hierarchy when no new Sc is selected", type = "Scenarios")
@Story(Application.MassAction.US17_3.class)
@RunWith(SerenityRunner.class)
public class US17003StyleCoachRegistrationTest extends BaseTest {

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
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private String context;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_17_FOLDER + File.separator + "us17003.properties");
			prop.load(input);

			context = prop.getProperty("context");

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

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us17003StyleCoachRegistrationTest() {
		headerSteps.navigateToStylecoachRegisterFormUnderContext(context);
		stylistRegistrationSteps.fillCreateStylecoachFormWithKnownSponsor(customerFormData, customerFormAddress, birthDate.getDate());
		
		stylistContextSteps.addStylistReference(customerFormData.getFirstName() + customerFormData.getLastName());

		addStarterSetProductsWorkflow.setStarterSetProductToCart(EnvironmentConstants.STARTERSET, EnvironmentConstants.STARTERKITPRICE);
		starterSetSteps.submitStarterSetStep();
		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
		
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}

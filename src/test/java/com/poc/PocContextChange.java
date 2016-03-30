package com.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US3", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(SerenityRunner.class)
public class PocContextChange extends BaseTest {
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ValidationWorkflows validationWorkflows;
	
	private String username, password;

	
	
	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();
		
		//print before (with context from command line)
		System.out.println("context before: " + MongoReader.getContext());
		System.out.println("CLOSE_PARTY: " + MongoReader.getDictionaryItem("CLOSE_PARTY"));
		
		//Set new prefered context and then update dictionary
		MongoWriter.saveEnvContext("staging-int", "es");
		System.out.println("!!!!!      CONTEXT: " + MongoReader.getContext());
		updateDictionary();
		System.out.println("---------------------------------------------------------------");
		
	}

	@Test
	public void us3003CartSegmentationWithVatBillingShippingDeTest() throws InterruptedException {
		customerRegistrationSteps.performLogin(username, password);
		customerRegistrationSteps.wipeCart();
		
		updateDictionary();
		
		//print after (with context from test)
		System.out.println("context after: " + MongoReader.getContext());
		System.out.println("CLOSE_PARTY: " + MongoReader.getDictionaryItem("CLOSE_PARTY"));
		
		
		//print after switch back to de context
		MongoWriter.saveEnvContext("staging-int", "de");
		updateDictionary();
		
		System.out.println("context after: " + MongoReader.getContext());
		System.out.println("CLOSE_PARTY: " + MongoReader.getDictionaryItem("CLOSE_PARTY"));
		
	}
}
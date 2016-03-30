package com.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;

@RunWith(SerenityRunner.class)
public class CartCleanUpTest extends BaseTest{

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	
	
	@Test
	@Pending
	public void cartCleanUpTest(){
		frontEndSteps.performLogin("evopippajean@gmail.com", "ghiocel24");
		
		String rawURL = headerSteps.getDriver().getCurrentUrl();
		headerSteps.getDriver().get(headerSteps.getDriver().getCurrentUrl().replace("stylist/lounge/", "checkout/cart/clearAllItems/"));
		cartSteps.wipeCart(rawURL);
		
	}
}

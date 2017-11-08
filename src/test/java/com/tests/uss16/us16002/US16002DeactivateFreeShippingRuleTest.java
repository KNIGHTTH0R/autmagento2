package com.tests.uss16.us16002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US16.2 SC borrows with free shipping Test", type = "Scenarios")
@Story(Application.BorrowCart.US16_2.class)
@RunWith(SerenityRunner.class)
public class US16002DeactivateFreeShippingRuleTest extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;
	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us16002DeactivateFreeShippingRuleTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		shoppingCartPriceRulesSteps.deactivateRule("AUT-Free Shipping Rule For Borrow Cart");
	}
}

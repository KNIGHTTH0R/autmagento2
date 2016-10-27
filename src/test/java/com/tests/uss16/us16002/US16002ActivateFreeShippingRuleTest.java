package com.tests.uss16.us16002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US16.2 SC borrows with free shipping Test", type = "Scenarios")
@Story(Application.BorrowCart.US16_2.class)
@RunWith(SerenityRunner.class)
public class US16002ActivateFreeShippingRuleTest extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	@Test
	public void us16002ActivateFreeShippingRuleTest() {
		shoppingCartPriceRulesSteps.activateRule("AUT-Free Shipping Rule For All Carts");
	}
}

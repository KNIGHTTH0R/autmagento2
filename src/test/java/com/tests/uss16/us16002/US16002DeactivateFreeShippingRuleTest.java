package com.tests.uss16.us16002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US16.2 SC borrows with free shipping Test", type = "Scenarios")
@Story(Application.BorrowCart.US16_2.class)
@RunWith(ThucydidesRunner.class)
public class US16002DeactivateFreeShippingRuleTest extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	@Test
	public void us16002DeactivateFreeShippingRuleTest() {
		shoppingCartPriceRulesSteps.deactivateRule("AUT-Free Shipping Rule For All Carts");
	}
}

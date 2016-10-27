package com.tests.us3.us30010;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US3.10 Shop For Myself With Free Shipping ", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_10.class)
@RunWith(SerenityRunner.class)
public class US30010DeactivatefreeShippingRuleTest extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	@Test
	public void us30010DeactivatefreeShippingRuleTest() {
		shoppingCartPriceRulesSteps.deactivateRule("AUT-Free Shipping Rule For All Carts");
	}
}

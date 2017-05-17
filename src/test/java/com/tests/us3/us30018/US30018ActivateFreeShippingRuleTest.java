package com.tests.us3.us30018;

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

@WithTag(name = "US3.10 Shop For Myself With Free Shipping ", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_10.class)
@RunWith(SerenityRunner.class)
public class US30018ActivateFreeShippingRuleTest extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;
	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us30018ActivateFreeShippingRuleTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		shoppingCartPriceRulesSteps.activateRule("AUT-Free Shipping Rule For All Carts");
	}
}

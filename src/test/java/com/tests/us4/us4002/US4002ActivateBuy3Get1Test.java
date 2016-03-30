package com.tests.us4.us4002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US4.2 Shop for myself with Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_2.class)
@RunWith(SerenityRunner.class)
public class US4002ActivateBuy3Get1Test extends BaseTest {
	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	@Test
	public void us4002ActivateBuy3Get1Test() {
		shoppingCartPriceRulesSteps.activateRule("Buy 3 get 1 for 50% - stylist");
	}
}

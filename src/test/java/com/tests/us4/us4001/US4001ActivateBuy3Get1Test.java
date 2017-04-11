package com.tests.us4.us4001;

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

@WithTag(name = "US4.1 Shop for myself with JB,MMB and Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_1.class)
@RunWith(SerenityRunner.class)
public class US4001ActivateBuy3Get1Test extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;
	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us4001ActivateBuy3Get1Test() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		shoppingCartPriceRulesSteps.activateRule("Buy 3 get 1 for 50% - stylist");
	}
}

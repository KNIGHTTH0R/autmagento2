package com.tests.uss11.us11002;

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

@WithTag(name = "US11.2 Party Host Buys For Customer With Buy 3 Get 1 For 50%, ship to customer ", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_2.class)
@RunWith(SerenityRunner.class)
public class US11002DeactivateBuy3Get1Test extends BaseTest {
	@Steps
	public ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;
	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us11002DeactivateBuy3Get1Test() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		shoppingCartPriceRulesSteps.deactivateRule("Buy 3 get 1 for 50% - place customer order");
	}
}

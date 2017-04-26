package com.tests.us8.us8003;

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

@WithTag(name = "US8.3 Customer Buy With 40% Discount,JB and Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.RegularCart.US8_3.class)
@RunWith(SerenityRunner.class)
public class US8003DeactivateBuy3Get1Test extends BaseTest {

	@Steps
	ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;
	@Steps
	BackEndSteps backEndSteps;

	@Test
	public void us8003DeactivateBuy3Get1Test() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		shoppingCartPriceRulesSteps.deactivateRule("Buy 3 get 1 for 50% - regular");
	}
}

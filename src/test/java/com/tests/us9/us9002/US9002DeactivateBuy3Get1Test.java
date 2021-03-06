package com.tests.us9.us9002;

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

@WithTag(name = "US9.2 Place Host Order With 40% Discount, JB and Buy 3 get 1 for 50% Test", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(SerenityRunner.class)
public class US9002DeactivateBuy3Get1Test extends BaseTest {
	
	@Steps
	ShoppingCartPriceRulesSteps promotionSteps;
	@Steps
	BackEndSteps backEndSteps;

	@Test
	public void us9002DeactivateBuy3Get1Test() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		promotionSteps.deactivateRule("Buy 3 get 1 for 50% - host");
	}
}

package com.tests.us9.us9002;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US9.2 Place Host Order With 40% Discount, JB and Buy 3 get 1 for 50% Test", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(SerenityRunner.class)
public class US9002ActivateBuyGet1Test extends BaseTest {

	@Steps
	public ShoppingCartPriceRulesSteps promotionSteps;

	@Test
	public void us9002ActivateBuyGet1Test() {

		promotionSteps.activateRule("Buy 3 get 1 for 50% - host");
	}
}

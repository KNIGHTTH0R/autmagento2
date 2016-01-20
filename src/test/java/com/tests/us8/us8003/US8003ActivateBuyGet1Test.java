package com.tests.us8.us8003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US8.3 Customer Buy With 40% Discount,JB and Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.RegularCart.US8_3.class)
@RunWith(ThucydidesRunner.class)
public class US8003ActivateBuyGet1Test extends BaseTest {

	@Steps
	ShoppingCartPriceRulesSteps shoppingCartPriceRulesSteps;

	@Test
	public void us8003ActivateBuyGet1Test() {
		shoppingCartPriceRulesSteps.activateRule("Buy 3 get 1 for 50% - regular");
	}
}

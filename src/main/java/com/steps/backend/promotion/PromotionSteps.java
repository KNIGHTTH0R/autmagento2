package com.steps.backend.promotion;

import net.thucydides.core.annotations.Steps;

import com.steps.backend.BackEndSteps;
import com.tools.Constants;
import com.tools.requirements.AbstractSteps;

public class PromotionSteps extends AbstractSteps {

	@Steps
	public BackEndSteps backEndSteps;

	private static final long serialVersionUID = 1L;

	public void activateRule() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateRule() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void activateBuy3Get1ForRegular() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_REGULAR);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForRegular() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_REGULAR);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}
	public void activateBuy3Get1ForHost() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_HOST);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}
	
	public void deactivateBuy3Get1ForHost() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_HOST);
		backEndSteps.performLogin("admin", "admin1234");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

}

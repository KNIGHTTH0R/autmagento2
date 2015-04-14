package com.steps.backend.promotion;

import com.tools.env.stagingaut.Constants;
import com.tools.requirements.AbstractSteps;

public class PromotionSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void activateRule() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateRule() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void activateBuy3Get1ForRegular() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_REGULAR);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForRegular() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_REGULAR);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void activateBuy3Get1ForHost() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_HOST);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForHost() {
		getDriver().get(Constants.BE_URL_RULE_BUY3GET1_FOR_HOST);
		magentoLoginPage().inputUserName(Constants.BE_USER);
		magentoLoginPage().inputUserPassword(Constants.BE_PASS);
		magentoLoginPage().clickOnLogin();
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

}

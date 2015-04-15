package com.steps.backend.promotion;

import com.tools.env.variables.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class PromotionSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void activateRule() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - stylist");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - stylist");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateRule() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - stylist");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - stylist");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void activateBuy3Get1ForRegular() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - regular");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - regular");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForRegular() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - regular");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - regular");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void activateBuy3Get1ForHost() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - host");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - host");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForHost() {
		getDriver().get(MongoReader.getBaseURL() + "index.php/admin/");
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - host");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - host");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

}

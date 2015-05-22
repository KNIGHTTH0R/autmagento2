package com.steps.backend.promotion;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class PromotionSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	public void activateRule() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
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

	public void activateBuy3Get1ForPlaceACustomerOrder() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - place customer order");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - place customer order");
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	public void deactivateBuy3Get1ForPlaceACustomerOrder() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - place customer order");
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - place customer order");
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	@Step
	public void verifyThatNoOfUsesPerCouponIsCorrect(String couponCode, String usesPerCoupon) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().clickOnPromotions();
		navigationPage().clickOnShoppingCartPriceRules();
		shoppingCartPriceRulesPage().typeRuleCode(couponCode);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Contact Booster 100 vouchers");
		shoppingCartPriceRulesPage().getUsesPerCouponValue();

		Assert.assertTrue("The no of uses per coupon is not the expected one", shoppingCartPriceRulesPage().getUsesPerCouponValue().contentEquals(usesPerCoupon));

	}

}

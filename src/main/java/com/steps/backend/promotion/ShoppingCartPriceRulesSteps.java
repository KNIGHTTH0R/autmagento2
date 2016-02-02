package com.steps.backend.promotion;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.env.constants.Credentials;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class ShoppingCartPriceRulesSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

//	public void activateRule() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - stylist");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - stylist");
//		shoppingCartPriceRulesPage().activateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void deactivateRule() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - stylist");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - stylist");
//		shoppingCartPriceRulesPage().deactivateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
	
	public void activateRule(String ruleName) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleName(ruleName);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails(ruleName);
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}
	
	public void deactivateRule(String ruleName) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleName(ruleName);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails(ruleName);
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

//	public void activateBuy3Get1ForRegular() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - regular");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - regular");
//		shoppingCartPriceRulesPage().activateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void deactivateBuy3Get1ForRegular() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - regular");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - regular");
//		shoppingCartPriceRulesPage().deactivateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void activateBuy3Get1ForHost() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - host");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - host");
//		shoppingCartPriceRulesPage().activateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void deactivateBuy3Get1ForHost() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - host");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - host");
//		shoppingCartPriceRulesPage().deactivateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void activateBuy3Get1ForPlaceACustomerOrder() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - place customer order");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - place customer order");
//		shoppingCartPriceRulesPage().activateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
//	public void deactivateBuy3Get1ForPlaceACustomerOrder() {
//		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
//		magentoLoginPage().inputUserName(Credentials.BE_USER);
//		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
//		magentoLoginPage().clickOnLogin();
//		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
//		shoppingCartPriceRulesPage().typeRuleName("Buy 3 get 1 for 50% - place customer order");
//		shoppingCartPriceRulesPage().clickOnSearch();
//		shoppingCartPriceRulesPage().openRuleDetails("Buy 3 get 1 for 50% - place customer order");
//		shoppingCartPriceRulesPage().deactivateRule();
//		shoppingCartPriceRulesPage().saveRule();
//	}
//
	@Step
	public void verifyThatNoOfUsesPerCouponIsCorrect(String couponCode, String usesPerCoupon) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleCode(couponCode);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Contact Booster");
		shoppingCartPriceRulesPage().getUsesPerCouponValue();

		Assert.assertTrue("The no of uses per coupon is not the expected one", shoppingCartPriceRulesPage().getUsesPerCouponValue().contentEquals(usesPerCoupon));

	}
	@Step
	public void verifyStatusAndUsesPerCoupon(String couponCode, String usesPerCoupon,String status) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleCode(couponCode);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().verifyStatusAndOpenRuleDetails("Contact Booster",status);
		shoppingCartPriceRulesPage().getUsesPerCouponValue();
		
		Assert.assertTrue("The no of uses per coupon is not the expected one", shoppingCartPriceRulesPage().getUsesPerCouponValue().contentEquals(usesPerCoupon));
		
	}

}

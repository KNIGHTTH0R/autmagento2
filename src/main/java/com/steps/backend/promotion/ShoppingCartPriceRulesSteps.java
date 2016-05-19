package com.steps.backend.promotion;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.env.constants.Credentials;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class ShoppingCartPriceRulesSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void activateRule(String ruleName) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().dismissPopUp();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleName(ruleName);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails(ruleName);
		shoppingCartPriceRulesPage().activateRule();
		shoppingCartPriceRulesPage().saveRule();
	}
	@Step
	public void deactivateRule(String ruleName) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().dismissPopUp();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleName(ruleName);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails(ruleName);
		shoppingCartPriceRulesPage().deactivateRule();
		shoppingCartPriceRulesPage().saveRule();
	}

	@Step
	public void verifyThatNoOfUsesPerCouponIsCorrect(String couponCode, String usesPerCoupon) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().dismissPopUp();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleCode(couponCode);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().openRuleDetails("Contact Booster");
		shoppingCartPriceRulesPage().getUsesPerCouponValue();

		Assert.assertTrue("The no of uses per coupon is not the expected one", shoppingCartPriceRulesPage().getUsesPerCouponValue().contentEquals(usesPerCoupon));

	}

	@Step
	public void verifyStatusAndUsesPerCoupon(String couponCode, String usesPerCoupon, String status) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(Credentials.BE_USER);
		magentoLoginPage().inputUserPassword(Credentials.BE_PASS);
		magentoLoginPage().clickOnLogin();
		navigationPage().dismissPopUp();
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
		shoppingCartPriceRulesPage().typeRuleCode(couponCode);
		shoppingCartPriceRulesPage().clickOnSearch();
		shoppingCartPriceRulesPage().verifyStatusAndOpenRuleDetails("Contact Booster", status);
		shoppingCartPriceRulesPage().getUsesPerCouponValue();

		Assert.assertTrue("The no of uses per coupon is not the expected one", shoppingCartPriceRulesPage().getUsesPerCouponValue().contentEquals(usesPerCoupon));

	}

}

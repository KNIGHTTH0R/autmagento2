package com.tests.uss12.uss12001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(SerenityRunner.class)
public class US12001ChechUsesPerCouponAfterSubscriptionUpgradeCancelCMTest extends BaseTest {
	@Steps
	public ShoppingCartPriceRulesSteps promotionSteps;
	
	private String koboCode;
	private String usesPerCoupon = "125";

	@Before
	public void setUp() throws Exception {

		koboCode = MongoReader.grabKoboModel("US12001KoboSubscriptionUpgradeTest" + SoapKeys.GRAB);
		System.out.println(koboCode);

	}

	@Test
	public void us12001ChechUsesPerCouponAfterSubscriptionUpgradeCancelCMTest() {
		promotionSteps.verifyStatusAndUsesPerCoupon(koboCode, usesPerCoupon, ConfigConstants.ACTIVE);
	}
}

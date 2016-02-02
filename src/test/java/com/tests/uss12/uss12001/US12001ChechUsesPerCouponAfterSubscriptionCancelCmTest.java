package com.tests.uss12.uss12001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.ShoppingCartPriceRulesSteps;
import com.tests.BaseTest;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.SoapKeys;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(ThucydidesRunner.class)
public class US12001ChechUsesPerCouponAfterSubscriptionCancelCmTest extends BaseTest {
	@Steps
	public ShoppingCartPriceRulesSteps promotionSteps;
	
	private String koboCode;
	private String usesPerCoupon = "125";

	@Before
	public void setUp() throws Exception {

		koboCode = MongoReader.grabKoboModel("US12001VerifyStylistKoboStatusAfterSubscriptionTest" + SoapKeys.GRAB);
		System.out.println(koboCode);

	}

	@Test
	public void us12001ChechUsesPerCouponAfterSubscriptionCancelCmTest() {
		promotionSteps.verifyStatusAndUsesPerCoupon(koboCode, usesPerCoupon, ConfigConstants.ACTIVE);
	}
}

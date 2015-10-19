package com.tests.uss12.uss12001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.env.constants.ConfigConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US12.1 Validate all kobo subscription and upgrade states", type = "Scenarios")
@Story(Application.KoboSubscription.US12_1.class)
@RunWith(ThucydidesRunner.class)
public class US12001ChechUsesPerCouponAfterSubscriptionUpgardeCMTest extends BaseTest {
	@Steps
	public PromotionSteps promotionSteps;
	
	private String koboCode;
	private String usesPerCoupon = "100";

	@Before
	public void setUp() throws Exception {

		koboCode = MongoReader.grabKoboModel("US12001KoboSubscriptionUpgradeTest" + SoapKeys.GRAB);
		System.out.println(koboCode);

	}

	@Test
	public void us12001ChechUsesPerCouponAfterSubscriptionUpgardeCMTest() {
		promotionSteps.verifyStatusAndUsesPerCoupon(koboCode, usesPerCoupon, ConfigConstants.ACTIVE);
	}
}

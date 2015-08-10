package com.tests.uss12;

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
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

/**
 * Deactivate 3+1 rule in backend for host cart
 * 
 * @author voicu.vac
 *
 */
@WithTag(name = "US12", type = "backend")
@Story(Application.KoboSubscription.class)
@RunWith(ThucydidesRunner.class)
public class US12001ChechUsesPerCouponAfterSubscriptionUpgradeTest extends BaseTest {
	@Steps
	public PromotionSteps promotionSteps;
	String koboCode;
	String usesPerCoupon = "125";

	@Before
	public void setUp() throws Exception {

		koboCode = MongoReader.grabKoboModel("US12001KoboSubscriptionUpgradeTest" + SoapKeys.GRAB);
		System.out.println(koboCode);

	}

	@Test
	public void us12001ChechUsesPerCouponAfterSubscriptionUpgradeTest() {
		promotionSteps.verifyThatNoOfUsesPerCouponIsCorrect(koboCode, usesPerCoupon);
	}
}

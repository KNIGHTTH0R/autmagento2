package com.tests.us9.us9002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@WithTag(name = "US9.2 Place Host Order With 40% Discount, JB and Buy 3 get 1 for 50% Test", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(ThucydidesRunner.class)
public class US9002ActivateBuyGet1Test extends BaseTest {

	@Steps
	public PromotionSteps promotionSteps;

	@Test
	public void us9002ActivateBuyGet1Test() {

		promotionSteps.activateBuy3Get1ForHost();
	}
}

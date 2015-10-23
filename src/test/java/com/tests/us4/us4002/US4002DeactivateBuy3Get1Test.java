package com.tests.us4.us4002;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.poc.DeactivateBuy3Get1ForShopForMyself;
import com.steps.backend.promotion.PromotionSteps;
import com.tools.requirements.Application;

@WithTag(name = "US4.2 Shop for myself with Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_2.class)
@RunWith(ThucydidesRunner.class)
public class US4002DeactivateBuy3Get1Test extends DeactivateBuy3Get1ForShopForMyself {

	@Steps
	public PromotionSteps promotionSteps;

	@Test
	public void us4002DeactivateBuy3Get1Test() {
		promotionSteps.deactivateRule();
	}
}

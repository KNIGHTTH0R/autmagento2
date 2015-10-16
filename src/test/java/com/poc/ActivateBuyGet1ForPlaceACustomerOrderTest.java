package com.poc;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;

/**
 * Activate 3+1 rule in backend for host cart
 * @author voicu.vac
 *
 */
@RunWith(ThucydidesRunner.class)
public class ActivateBuyGet1ForPlaceACustomerOrderTest extends BaseTest{
	@Steps
	public PromotionSteps promotionSteps;
	
	@Test
	public void changeRuleStatusTest(){
		
		promotionSteps.activateBuy3Get1ForPlaceACustomerOrder();		
		
	}
}

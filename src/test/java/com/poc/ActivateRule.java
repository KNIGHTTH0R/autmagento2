package com.poc;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;

/**
 * Activate 3+1 rule in backend
 * @author voicu.vac
 *
 */
@RunWith(ThucydidesRunner.class)
public class ActivateRule extends BaseTest{
	@Steps
	public PromotionSteps promotionSteps;
	
	@Test
	public void changeRuleStatusTest(){
		
		promotionSteps.activateRule();		
		
	}
}
package com.poc;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;


@RunWith(ThucydidesRunner.class)
public class DeactivateRule extends BaseTest{
	@Steps
	public PromotionSteps promotionSteps;
	
	@Test
	public void changeRuleStatusTest(){
		promotionSteps.deactivateRule();	
		
	}
}

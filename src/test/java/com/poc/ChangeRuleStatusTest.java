package com.poc;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class ChangeRuleStatusTest extends BaseTest{	
	
	@Steps
	public PromotionSteps promotionSteps;
	
	@Test
	public void changeRuleStatusTest(){
		
		promotionSteps.activateRule();		
		
	}
}
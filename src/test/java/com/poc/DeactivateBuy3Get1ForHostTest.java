package com.poc;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.promotion.PromotionSteps;
import com.tests.BaseTest;


/**
 * Deactivate 3+1 rule in backend for host cart
 * @author voicu.vac
 *
 */
@RunWith(ThucydidesRunner.class)
public class DeactivateBuy3Get1ForHostTest extends BaseTest{
	@Steps
	public PromotionSteps promotionSteps;
	
	@Test
	public void deactivateBuy3Get1ForHostTest(){
		promotionSteps.deactivateBuy3Get1ForHost();	
		
	}
}

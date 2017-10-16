package com.tests.uss40;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pages.external.academy.LoginAcademyPage;
import com.steps.external.academy.AcademySteps;
import com.steps.external.academy.LoginAcademySteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class HappyFlowAcademyTest extends BaseTest {


	@Steps
	LoginAcademySteps loginAcademySteps;
	@Steps
	AcademySteps academySteps;
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void stylistInventoryBorrowCustomPackageTest() throws Exception {
		loginAcademySteps.performAcademyLogin("em99@evozon.com","emilian1");
		//verify login in pippajean
		academySteps.clickEnrollButton();
		academySteps.startTraining();
		academySteps.completeCourse("1");
		academySteps.completeQuiz("1");
		
	}

}

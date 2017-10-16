package com.tests.uss40;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pages.external.academy.LoginAcademyPage;
import com.steps.external.academy.AcademySteps;
import com.steps.external.academy.LoginAcademySteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class HappyFlowAcademyTest2 extends BaseTest {

	@Steps
	CustomVerification customVerification;
	@Steps
	LoginAcademySteps loginAcademySteps;
	@Steps
	AcademySteps academySteps;
	@Steps
	public HeaderSteps headerSteps;
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void stylistInventoryBorrowCustomPackageTest() throws Exception {
		loginAcademySteps.performAcademyLogin("em99@evozon.com","emilian1");
		//verify login in pippajean
		academySteps.clickEnrollButton();
		academySteps.startTraining();
	//	academySteps.completeSingleCourse("1");
	//	academySteps.completeSingleCourse("2");
		academySteps.completeSingleCourse("3");
	//	academySteps.completeSingleCourse("4");
	//	academySteps.completeSingleCourse("5");
	//	academySteps.completeSingleCourse("6");
	//	academySteps.completeSingleCourse("7");
		
		headerSteps.openNewTab();
		headerSteps.switchToNewestOpenedTab();
		headerSteps.navigate("https://staging.pippajean.com/de/");
		
		headerSteps.checkSucesfullLoginInPippa();
		
		customVerification.printErrors();
	}

}

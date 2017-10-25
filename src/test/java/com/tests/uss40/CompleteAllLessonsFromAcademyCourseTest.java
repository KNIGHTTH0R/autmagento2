package com.tests.uss40;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.PippaDb.PippaDBConnection;
import com.connectors.http.ApacheHttpHelper;
import com.steps.external.academy.AcademySteps;
import com.steps.external.academy.LoginAcademySteps;
import com.steps.frontend.HeaderSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class CompleteAllLessonsFromAcademyCourseTest extends BaseTest {

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
		//clean data for stylist from academy database
		ApacheHttpHelper.sendGet("http://172.22.100.164:81/job/RESET_STYLIST_DATA/build?token=XDWRmbcL3U1mRlCjOBrF", "emilian","emilian1");
		
		//clean data for stylist from pippajean-aut database
		PippaDBConnection.deleteAcademyUserData("298", "119838");
	}

	@Test
	public void completeAllLessonsFromAcademyCourseTest() throws Exception {
		loginAcademySteps.performAcademyLogin("em99@evozon.com", "emilian1");
		// verify login in pippajean
		academySteps.clickEnrollButton();
		academySteps.startTraining();
	
		academySteps.completeAcademyLesson("1");
		
		academySteps.completeAcademyLesson("2");
		
		academySteps.completeAcademyLesson("3");
		
		academySteps.completeAcademyLesson("4");
		
		academySteps.completeAcademyLesson("5");
		
		academySteps.completeAcademyLesson("6");
		
		academySteps.completeAcademyLesson("7");

		headerSteps.openNewTab();
		headerSteps.switchToNewestOpenedTab();
		headerSteps.navigate("https://staging.pippajean.com/de/");
		headerSteps.checkSucesfullLoginInPippa();
		customVerification.printErrors();

	}

}

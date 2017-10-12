package com.steps.external.academy;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class AcademySteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void performAcademyLogin(String name, String password) {
		academyPage().clickLoginWithPippajeanAccount();
		academyPage().inputEmail(name);
		academyPage().inputPassword(password);
	}

	@Step
	public void clickEnrollButton() {
		// TODO Auto-generated method stub
		academyPage().clickEnrollButton();
	}
	
	@Step
	public void startTraining() {
		// TODO Auto-generated method stub
		academyPage().startTraining();
	}

	@Step
	public void completeCourse(String courseNo) {
		academyPage().completeCourse(courseNo);
	}
	@Step
	public void startQuiz(){
		academyPage().startQuiz();
	}
	
	@Step
	public void completeQuiz(String string) {
		startQuiz();
		academyPage().startTest();
		academyQuizPage().completeQuizCourse1();
		
	}
}

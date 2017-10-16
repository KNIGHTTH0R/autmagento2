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
	public void completeSingleCourse(String courseNo) {
		boolean isComplete=academyPage().completeSingleCourse(courseNo);
		if(courseNo.contentEquals("1") || courseNo.contentEquals("2") ||courseNo.contentEquals("7")){
			System.out.println("courseNo______________: "+courseNo);
			verifyCourseIsCompleteInTrainingPage(courseNo,isComplete);
		}else{
			boolean courseIsComplete=verifyQuizIsComplete(isComplete);
			verifyCourseIsCompleteInTrainingPage(courseNo,courseIsComplete);
		}
		
	}
	
	@Step
	public void startQuiz(){
		academyPage().startQuiz();
	}
	
	@Step
	public boolean verifyQuizIsComplete(boolean quizNo){
		
		return academyPage().verifyCompleteQuiz(quizNo);
	}
	
	@Step
	public void verifyCourseIsCompleteInTrainingPage(String courseNo,boolean isComplete){
		academyPage().verifyCourseIsCompleteInTrainingPage(courseNo,isComplete);
	}
	
	@Step
	public void completeQuiz(String string) {
		startQuiz();
		academyPage().startTest();
		academyQuizPage().completeQuizCourse1();
	
		
	}
}

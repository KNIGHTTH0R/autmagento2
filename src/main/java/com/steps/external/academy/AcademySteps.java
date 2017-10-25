package com.steps.external.academy;

import java.sql.SQLException;

import com.connectors.PippaDb.PippaDBConnection;
import com.tools.CustomVerification;
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
	public void completeCourse(String lessonNo) {
		academyPage().completeCourse(lessonNo);
	}
	
	@Step
	public void completeAcademyLesson(String lessonNo) throws SQLException {
		boolean isComplete=academyPage().completeSingleLesson(lessonNo);
	
		if(lessonNo.contentEquals("1") || lessonNo.contentEquals("2") || lessonNo.contentEquals("7")){
			System.out.println("courseNo______________: "+lessonNo);
			verifyAcademyLessonIsCompleteInTrainingPage(lessonNo,isComplete);
		}else{
			System.out.println("courseNo______________: "+lessonNo);
			boolean lessonIsComplete=verifyQuizIsComplete(isComplete);
			verifyAcademyLessonIsCompleteInTrainingPage(lessonNo,lessonIsComplete);
			validateAcademyLessonIsNotCompleteInShop(PippaDBConnection.completedCoursesInShop());
		}
		
	}
	
	@Step
	public void startQuiz(){
		academyPage().startQuiz();
	}
	
	@Step("Verify if quiz is complete")
	public boolean verifyQuizIsComplete(boolean quizNo){
		
		return academyPage().verifyCompleteQuiz(quizNo);
	}
	
	@Step("Verify if lesson appears as complete in training Page")
	public void verifyAcademyLessonIsCompleteInTrainingPage(String courseNo,boolean isComplete){
		academyPage().verifyAcademyLessonIsCompleteInTrainingPage(courseNo,isComplete);
	}
	
	@Step
	public void completeQuiz(String string) {
		startQuiz();
		academyPage().startTest();
		academyQuizPage().completeQuizAcademyLesson();
	
		
	}

	@Step("Verify if course appears as complete in shop")
	public void validateCourseIsCompleteInShop(String lessonComplete) {
		
		CustomVerification.verifyTrue("Failure: The course does not appears as completed in Magento Shop", lessonComplete.contains("144"));
		
	}
	
	@Step("Verify if course does not appears as complete in shop")
	public void validateAcademyLessonIsNotCompleteInShop(String lessonComplete) {
		
		CustomVerification.verifyTrue("Failure: The course appears as completed in Magento Shop and shouldn't", !lessonComplete.contains("144"));
		
	}
}

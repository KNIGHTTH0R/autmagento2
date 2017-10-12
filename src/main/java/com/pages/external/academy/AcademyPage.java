package com.pages.external.academy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

public class AcademyPage extends AbstractPage {
	@FindBy(id = "button-magento-sql")
	private WebElement pjAccountBtn;

	@FindBy(id = "username")
	private WebElement inputUserName;

	@FindBy(id = "password")
	private WebElement inputPassword;

	@FindBy(css = ".pj-bg-gold")
	private WebElement enrollBtn;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(1) h4")
	private WebElement course1;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(2) h4")
	private WebElement course2;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(3) h4")
	private WebElement course3;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(4) h4")
	private WebElement course4;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(5) h4")
	private WebElement course5;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(6) h4")
	private WebElement course6;

	@FindBy(css = ".lessons_list .is_not_sample:nth-child(7) h4")
	private WebElement course7;

	@FindBy(css = "#learndash_quizzes h4")
	private WebElement startQuizBtn;

	@FindBy(css = ".wpProQuiz_text input")
	private WebElement startTest;

	//

	//

	public void clickLoginWithPippajeanAccount() {
		element(pjAccountBtn).waitUntilVisible();
		pjAccountBtn.click();

	}

	public void inputEmail(String name) {
		element(inputUserName).waitUntilVisible();
		inputUserName.sendKeys(name);
	}

	public void inputPassword(String password) {
		element(inputUserName).waitUntilVisible();
		inputPassword.sendKeys(password);

	}

	public void clickEnrollButton() {
		element(enrollBtn).waitUntilVisible();
		enrollBtn.click();
	}

	public void startTraining() {
		List<WebElement> joinBtn = getDriver().findElements(By.cssSelector(".learndash_join_button"));

		if (joinBtn.size() != 0) {
			joinBtn.get(0).click();
		}
	}

	public void openCourse(String courseNo) {
		List<WebElement> course = getDriver()
				.findElements(By.cssSelector(".lessons_list .is_not_sample:nth-child(" + courseNo + ") h4"));
		course.get(0).click();
	}

	public void completeCourse(String courseno) {
		openCourse(courseno);
		List<WebElement> linkNextPrev = getDriver().findElements(By.cssSelector("#learndash_next_prev_link a"));
		List<WebElement> quiz = getDriver().findElements(By.cssSelector("#learndash_quizzes"));
		if (linkNextPrev.size() != 0) {
			while (quiz.size() == 0) {
				for (WebElement link : linkNextPrev) {
					if (link.getText().contains("Nächste Übung")) {
						link.click();
						waitABit(2000);
						linkNextPrev = getDriver().findElements(By.cssSelector("#learndash_next_prev_link a"));
						quiz = getDriver().findElements(By.cssSelector("#learndash_quizzes"));
						System.out.println("quiz sice: " + quiz.size());
					}
				}
			}
		} else {
			System.out.println("this course does not have any page");
		}

	}

	public void startQuiz() {
		element(startQuizBtn).waitUntilVisible();
		startQuizBtn.click();

	}

	public void startTest() {
		element(startTest).waitUntilVisible();
		startTest.click();

	}

	public void completeQuizCourse1() {
		

	}

	public static Map<Object, List<String>>  quizDatabase() {
		Map<Object, List<String>> multiMap = new HashMap<Object, List<String>>();
		List<String> quiz1_1 = new ArrayList<String>(Arrays.asList("About the 40% voucher through follow-up parties",
				"About the jewelry bonus depending on the party turnover"));
		List<String> quiz1_2 = new ArrayList<String>(
				Arrays.asList("In persönlicher Einzelansprache durch die Gastgeberin"));
		List<String> quiz1_3 = new ArrayList<String>(
				Arrays.asList("Infos wann und wie die Party stattfindet", "Infos wann und wie die Party stattfindet"));
		List<String> quiz1_4 = new ArrayList<String>(Arrays.asList("40-80"));
		List<String> quiz1_5 = new ArrayList<String>(Arrays.asList("Den Stream liken", "Kommentare schreiben",
				"Andere Gäste im Live Stream animieren mitzureden und zu liken"));
		List<String> quiz1_6 = new ArrayList<String>(Arrays.asList("Sehr wichtig"));

		multiMap.put("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)", quiz1_1);
		multiMap.put("Wie sollen die Gäste eingeladen werden?", quiz1_2);
		multiMap.put("Was sollte die Gastgeberin gleich mitschicken?", quiz1_3);
		multiMap.put("Wieviele Gäste sollte die Gastgeberin einladen?", quiz1_4);
		multiMap.put("Was sollte die Gastgeberin während des Live Streams machen? (Mehrfachauswahl möglich)", quiz1_5);
		multiMap.put("Wie wichtig ist die Rolle der Gastgeberin für den Erfolg der Party?", quiz1_6);
		return multiMap;

	}
	
	public static void main(String[] args) {
		Map<Object, List<String>> mymap=quizDatabase();
		
		for (Object value1 : mymap.keySet()) { 
			if(value1.toString().contains("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)")){
				System.out.println(mymap.get(value1));
				if(mymap.get(value1).contains("Andere Gäste im Live Stream animieren mitzureden und zu liken")){
					System.out.println("bifat");
				}
				break;
			}
		//	System.out.println(value1);
		}
		
	}

}

package com.pages.external.academy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
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
		List<WebElement> completeBtn = getDriver().findElements(By.cssSelector("#learndash_mark_complete_button"));
		if (linkNextPrev.size() != 0) {
			while (quiz.size() == 0) {
				for (WebElement link : linkNextPrev) {
					if (link.getText().contains("Nächste Übung")) {
						link.click();
						waitABit(2000);
						linkNextPrev = getDriver().findElements(By.cssSelector("#learndash_next_prev_link a"));
						quiz = getDriver().findElements(By.cssSelector("#learndash_quizzes"));
				//		System.out.println("quiz sice: " + quiz.size());
					}
				}
			}
		} else {
			System.out.println("this course does not have any page");
		}

	}

	public boolean completeSingleCourse(String courseNo) {
		boolean completeCourse = false;

		openCourse(courseNo);
		List<WebElement> completeBtn = getDriver().findElements(By.cssSelector("#learndash_mark_complete_button"));
		List<WebElement> quiz = getDriver().findElements(By.cssSelector("#learndash_quizzes"));
		if (completeBtn.size() == 1) {
			completeBtn.get(0).click();
			completeCourse=true;
			navigate("https://staging-academy.pippajean.com/courses/online-style-party-training/");
		} else if (quiz.size() == 1) {
			startQuiz();
			startTest();
			completeCourse = completeQuizCourse1();
			// navigate("https://staging-academy.pippajean.com/courses/online-style-party-training/");
		}
		return completeCourse;
	}

	public void startQuiz() {
		System.out.println("--> Start Quiz <--");
		element(startQuizBtn).waitUntilVisible();
		startQuizBtn.click();

	}

	public void startTest() {
		element(startTest).waitUntilVisible();
		startTest.click();

	}

	public boolean completeQuizCourse1() {
		boolean courseComplete = false;
		List<WebElement> pagini = getDriver().findElements(By.cssSelector(".wpProQuiz_list .wpProQuiz_listItem"));
		// System.out.println("pageQuiz: " + pagini);
		Map<Object, List<String>> mymap = quizNotSoCorrectDatabase();
		// System.out.println("mymap: " + mymap);
		for (WebElement page : pagini) {

			List<String> answersDB = mymap.get(page.findElement(By.cssSelector(".wpProQuiz_question_text")).getText());
//		/	System.out.println("answersDB: " + answersDB);

			if (answersDB != null) {
				for (WebElement answer : page.findElements(By.cssSelector(".wpProQuiz_questionList li"))) {
				//	System.out.println("answer " + answer.getText());
					if (answersDB.contains(answer.getText())) {
						answer.findElement(By.cssSelector(".wpProQuiz_questionInput")).click();
					}
				}
			}

			page.findElement(By.cssSelector("input[name*='next']")).click();

		}
		waitABit(5000);
		List<WebElement> score = getDriver().findElements(By.cssSelector(".wpProQuiz_points"));
		if (score.size() == 1) {

			String stringScore = score.get(0).getText()
					.substring(score.get(0).getText().indexOf('('), score.get(0).getText().indexOf('%'))
					.replaceAll("[^\\d.]", "");
			System.out.println("stringScore: " + stringScore);
			BigDecimal scoreDec = new BigDecimal(stringScore);
			BigDecimal value = new BigDecimal(80);

			if (scoreDec.compareTo(value) >= 0) {
				courseComplete = true;
			}

		}
		return courseComplete;

	}

	public boolean completeCorrectCourse() {
		boolean isComplete=false;
		List<WebElement> pagini = getDriver().findElements(By.cssSelector(".wpProQuiz_list .wpProQuiz_listItem"));
	//	System.out.println("pageQuiz: " + pagini);
		Map<Object, List<String>> mymap = quizCorrectDatabase();
		//System.out.println("mymap: " + mymap);
		for (WebElement page : pagini) {

			List<String> answersDB = mymap.get(page.findElement(By.cssSelector(".wpProQuiz_question_text")).getText());
		//	System.out.println("answersDB: " + answersDB);

			if (answersDB != null) {
				for (WebElement answer : page.findElements(By.cssSelector(".wpProQuiz_questionList li"))) {
				//	System.out.println("answer " + answer.getText());
					if (answersDB.contains(answer.getText())) {
						answer.findElement(By.cssSelector(".wpProQuiz_questionInput")).click();
					}
				}
			}

			page.findElement(By.cssSelector("input[name*='next']")).click();
		}
		waitABit(5000);
		
		List<WebElement> score = getDriver().findElements(By.cssSelector(".wpProQuiz_points"));
		if (score.size() == 1) {

			String stringScore = score.get(0).getText()
					.substring(score.get(0).getText().indexOf('('), score.get(0).getText().indexOf('%'))
					.replaceAll("[^\\d.]", "");
			System.out.println("stringScore: " + stringScore);
			BigDecimal scoreDec = new BigDecimal(stringScore);
			BigDecimal value = new BigDecimal(80);

			if (scoreDec.compareTo(value) >= 0) {
				isComplete = true;
			}

		}
		return isComplete;

	}

	public static Map<Object, List<String>> quizNotSoCorrectDatabase() {
		Map<Object, List<String>> multiMap = new HashMap<Object, List<String>>();
		List<String> quiz1_1 = new ArrayList<String>(Arrays.asList("dÜbersds den 40% Gutschein durch Folgepartys",
				"Über den Schmuckbonus in Abhängigkeit vom Partyumsatz"));
		List<String> quiz1_2 = new ArrayList<String>(
				Arrays.asList("In persön33licher Einzelansprache durch die Gastgeberin"));
		List<String> quiz1_3 = new ArrayList<String>(
				Arrays.asList("Infos wann und wie die Party stattfindet", "Am besten Beispielbilder vom Schmuck"));
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

	public static Map<Object, List<String>> quizCorrectDatabase() {
		Map<Object, List<String>> multiMap = new HashMap<Object, List<String>>();
		List<String> quiz3_1 = new ArrayList<String>(Arrays.asList("Über den 40% Gutschein durch Folgepartys",
				"Über den Schmuckbonus in Abhängigkeit vom Partyumsatz"));
		List<String> quiz3_2 = new ArrayList<String>(
				Arrays.asList("In persönlicher Einzelansprache durch die Gastgeberin"));
		List<String> quiz3_3 = new ArrayList<String>(
				Arrays.asList("Infos wann und wie die Party stattfindet", "Am besten Beispielbilder vom Schmuck"));
		List<String> quiz3_4 = new ArrayList<String>(Arrays.asList("40-80"));
		List<String> quiz3_5 = new ArrayList<String>(Arrays.asList("Den Stream liken", "Kommentare schreiben",
				"Andere Gäste im Live Stream animieren mitzureden und zu liken"));
		List<String> quiz3_6 = new ArrayList<String>(Arrays.asList("Sehr wichtig"));

		
		List<String> quiz4_1 = new ArrayList<String>(Arrays.asList("Ein paar Tage täglich vor der Live Stream posten","Nach dem Live Stream"));
		List<String> quiz4_2 = new ArrayList<String>(Arrays.asList("Meine Gastgeberin sollte die Posts liken und kommentieren"));
		
		List<String> quiz5_1 = new ArrayList<String>(Arrays.asList("Ja"));
		List<String> quiz5_2 = new ArrayList<String>(Arrays.asList("Ja"));
		List<String> quiz5_3 = new ArrayList<String>(Arrays.asList("Von vorne"));
		List<String> quiz5_4 = new ArrayList<String>(Arrays.asList("Auf Augenhöhe oder höher"));
		List<String> quiz5_5 = new ArrayList<String>(Arrays.asList("Ja"));
		List<String> quiz5_6 = new ArrayList<String>(
				Arrays.asList("Prüfen, ob der Ton stimmt", " Prüfen, ob das Licht stimmt",
						"Prüfen, ob die Perspektive so passt", "Um sich warm zu machen für den Live Stream"));

		List<String> quiz6_1 = new ArrayList<String>(Arrays.asList(
				"Ich muss beim Anlegen der Party in der Lounge die Party als Online Style Party markieren, da sonst der Online Style Party Manager nicht gestartet werden kann"));
		List<String> quiz6_2 = new ArrayList<String>(Arrays.asList("Geschlossene Gruppe nur für die Gäste"));
		List<String> quiz6_3 = new ArrayList<String>(
				Arrays.asList("Damit eine hohe Qualität und das Branding der Marke gegeben ist",
						"Weil PIPPA&JEAN die Post auswertet und damit die Vorlagen ständig verbessert"));
		List<String> quiz6_4 = new ArrayList<String>(Arrays.asList(
				"Die Links zu den Produkten, eurem Shop, Willkommensgeschenk, etc. enthalten den Party Code und sorgen dafür, dass alle Bestellungen in meinem Shop auf dieser Party landen",
				"Es ist viel einfachere die Kommentare der Gäste zu lesen"));

		
		
		
		
		multiMap.put("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)", quiz3_1);
		multiMap.put("Wie sollen die Gäste eingeladen werden?", quiz3_2);
		multiMap.put("Was sollte die Gastgeberin gleich mitschicken?", quiz3_3);
		multiMap.put("Wieviele Gäste sollte die Gastgeberin einladen?", quiz3_4);
		multiMap.put("Was sollte die Gastgeberin während des Live Streams machen? (Mehrfachauswahl möglich)", quiz3_5);
		multiMap.put("Wie wichtig ist die Rolle der Gastgeberin für den Erfolg der Party?", quiz3_6);

		multiMap.put("Wann sollte ich Posts erstellen?  (Mehrfachauswahl möglich)", quiz4_1);
		multiMap.put("Was sollte meine Gastgeberin mit den Posts machen?", quiz4_2);
		
		multiMap.put("Muss ich alle Geräte (Handy, Laptop) an den Strom anschliessen?", quiz5_1);
		multiMap.put("Soll ich mein Smart Phone in den Flugmodus schalten bzw. die Benachrichtigungen ausschalten?",
				quiz5_2);
		multiMap.put("Von wo soll das Licht am besten kommen?", quiz5_3);
		multiMap.put("Wo sollte die Kamera stehen?", quiz5_4);
		multiMap.put("Mehr Licht ist besser. Stimmt die Aussage?", quiz5_5);
		multiMap.put("Warum sollte man jedes Mal eine Probeaufnahme machen? (Mehrfachauswahl möglich)", quiz5_6);

		multiMap.put("Was ist beim Anlegen der Party zu beachten?)", quiz6_1);
		multiMap.put("Welche Art Facebook Gruppe sollte ich anlegen?", quiz6_2);
		multiMap.put("Warum sollte ich immer Bilder und Texte von PIPPA&JEAN verwenden? (Mehrfachauswahl möglich)",
				quiz6_3);
		multiMap.put("Warum ist der Online Style Party Manager so wichtig? (Mehrfachauswahl möglich)", quiz6_4);

		return multiMap;

	}

	/*
	 * public static void main(String[] args) { Map<Object, List<String>>
	 * mymap=quizDatabase();
	 * 
	 * for (Object value1 : mymap.keySet()) { if(value1.toString().contains(
	 * "Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)"
	 * )){ System.out.println(mymap.get(value1)); if(mymap.get(value1).contains(
	 * "Andere Gäste im Live Stream animieren mitzureden und zu liken")){
	 * System.out.println("bifat"); } break; } // System.out.println(value1); }
	 * 
	 * }
	 */

	public boolean verifyCompleteQuiz(boolean isQuizComplete) {
		boolean isCourseComplete=false;
		List<WebElement> quizComplete = getDriver().findElements(By.cssSelector("#quiz_continue_link"));
		if (isQuizComplete == true) {
			
			CustomVerification.verifyTrue("The button <Click here to continue is not displayed",
					quizComplete.size() == 1);
			navigate("https://staging-academy.pippajean.com/courses/online-style-party-training/");
		} else {
			WebElement restart = getDriver().findElement(By.cssSelector("input[name='restartQuiz']"));
			restart.click();
			startTest();
			isCourseComplete=completeCorrectCourse();
			 quizComplete = getDriver().findElements(By.cssSelector("#quiz_continue_link"));
			 if(quizComplete.size()==1){
				 quizComplete.get(0).click();
			 }
			
			navigate("https://staging-academy.pippajean.com/courses/online-style-party-training/");
		}
		return isCourseComplete;

	}

	public void verifyCourseIsCompleteInTrainingPage(String courseNo, boolean isComplete) {
		List<WebElement> course = getDriver()
				.findElements(By.cssSelector(".lessons_list .is_not_sample:nth-child(" + courseNo + ") h4 .completed"));
		if (isComplete == true) {
			CustomVerification.verifyTrue("The check box should be present over the course", course.size() == 1);
		} else {
			CustomVerification.verifyTrue("The check box should NOT be present over the course", course.size() == 0);
		//	System.out.println("The check box should NOT be present over the course");
		}
	}

}

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

public class AcademyQuizPage extends AbstractPage {
	
	@FindBy(css = ".wpProQuiz_list .wpProQuiz_listItem")
	private List<WebElement> pageQuiz;
	
	@FindBy(css = ".wpProQuiz_list .wpProQuiz_listItem input[name*='next']")
	private List<WebElement> nextButtonList;
	
	@FindBy(css = ".wpProQuiz_list .wpProQuiz_listItem .wpProQuiz_question_text")
	private List<WebElement> questionList;
	
	@FindBy(css = ".wpProQuiz_list .wpProQuiz_listItem .wpProQuiz_questionList")
	private List<WebElement> answerList;
	
	
	


	public void completeQuizAcademyLesson() {
		List<WebElement> pagini=getDriver().findElements(By.cssSelector(".wpProQuiz_list .wpProQuiz_listItem"));
		System.out.println("pageQuiz: "+pagini);
		Map<Object, List<String>> mymap=quizDatabase();
		System.out.println("mymap: "+mymap);
		for (WebElement page : pagini) {
			
			List<String> answersDB=mymap.get(page.findElement(By.cssSelector(".wpProQuiz_question_text")).getText());
			System.out.println("answersDB: "+answersDB);
		
			for (WebElement answer : page.findElements(By.cssSelector(".wpProQuiz_questionList li"))) {
				System.out.println("answer "+answer.getText());
				if(answersDB.contains(answer.getText())){
					answer.findElement(By.cssSelector(".wpProQuiz_questionInput")).click();
				}
			}
			
			page.findElement(By.cssSelector("input[name*='next']")).click();
		}
		waitABit(5000);

	}

	public static Map<Object, List<String>>  quizDatabase() {
		Map<Object, List<String>> multiMap = new HashMap<Object, List<String>>();
		List<String> quiz1_1 = new ArrayList<String>(Arrays.asList("Über den 40% Gutschein durch Folgepartys",
				"Über den Schmuckbonus in Abhängigkeit vom Partyumsatz"));
		List<String> quiz1_2 = new ArrayList<String>(
				Arrays.asList("In persönlicher Einzelansprache durch die Gastgeberin"));
		List<String> quiz1_3 = new ArrayList<String>(
				Arrays.asList("Infos wann und wie die Party stattfindet","Am besten Beispielbilder vom Schmuck"));
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
		
//		for (Object value1 : mymap.keySet()) { 
//			if(value1.toString().contains("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)")){
//				System.out.println(mymap.get(value1));
//				if(mymap.get(value1).contains("Andere Gäste im Live Stream animieren mitzureden und zu liken")){
//					System.out.println("bifat");
//				}
//				break;
//			}
//			
//			
//			
//		//	System.out.println(value1);
//		}
		
		if(mymap.containsKey("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)")){
			List<String> value = mymap.get("Über was muss ich die Gastgeberin informieren? (Mehrfachauswahl möglich)");
			System.out.println( value);
		}
	}

}

package com.pages.external;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailinatorPage extends AbstractPage {

	@FindBy(id = "mailcontainer")
	private WebElement inboxContainer;

	@FindBy(className = "mailview")
	private WebElement mailContainer;

	@FindBy(css = "div#mailshowdivbody iframe")
	private WebElement iFrameElement;

	public String grabEmail() {
		element(inboxContainer).waitUntilVisible();
		String returnText = "";

		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("li"));

		for (WebElement itemNow : emailList) {
			String allText = itemNow.getText();
			System.out.println("Row: " + allText);
			if (allText.contains("Willkommen")) {
				returnText = allText;
				break;
			} else {
				if (allText.contains("Benutzerkonto")) {
					itemNow.findElement(By.cssSelector("div.subject")).click();
					break;
				}
			}
		}

		return returnText;
	}

	public String grabUserAcountConfirmationEmail() {
		element(inboxContainer).waitUntilVisible();
		String returnText = "";

		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("li"));

		for (WebElement itemNow : emailList) {
			String allText = itemNow.getText();
			System.out.println("Row: " + allText);
			if (allText.contains("Benutzerkonto Bestätigung für")) {
				itemNow.findElement(By.cssSelector("div.subject")).click();
				break;
			}
		}

		return returnText;
	}

	public String grabEmail(String title) {
		element(inboxContainer).waitUntilVisible();
		String returnText = "";
		boolean foundEmail = false;
		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("li"));

		for (WebElement itemNow : emailList) {
			String allText = itemNow.getText();
			System.out.println("Row: " + allText);
			if (allText.contains(title)) {
				itemNow.findElement(By.cssSelector("div.subject")).click();
				foundEmail = true;
				break;
			}
		}
		Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
		return returnText;
	}

	public String confirmEmail() {
		getDriver().switchTo().frame(iFrameElement);
		element(mailContainer).waitUntilVisible();
		String confirmLink = mailContainer.findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		System.out.println("Confirm link: " + confirmLink);
		return confirmLink;
	}

	public String grabCouponCode() {
		getDriver().switchTo().frame(iFrameElement);
		String codeSection = mailContainer.findElement(By.cssSelector("table[bgcolor='#FFFFFF'] tbody > tr:nth-child(3)")).getText();

		return codeSection;
	}

}

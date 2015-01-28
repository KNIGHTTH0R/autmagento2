package com.pages.external;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

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

	public String confirmEmail() {

		getDriver().switchTo().frame(iFrameElement);

		element(mailContainer).waitUntilVisible();
		String confirmLink = mailContainer.findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");

		System.out.println("Confirm link: " + confirmLink);

		return confirmLink;

	}

}

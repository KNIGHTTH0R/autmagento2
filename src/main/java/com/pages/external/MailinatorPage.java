package com.pages.external;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.TimeConstants;
import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

public class MailinatorPage extends AbstractPage {

	@FindBy(css = "div.someviewport")
	private WebElement inboxContainer;

	@FindBy(className = "mailview")
	private WebElement mailContainer;

	@FindBy(id = "publicshowmaildivcontent")
	private WebElement iFrameElement;

	public void openEmail(String email, String title) {
		System.out.println(email);
		navigate(UrlConstants.MAILINATOR_WEB_MAIL);
		//getDriver().navigate().refresh();
		//waitForPageToLoad();
		
		
		waitABit(TimeConstants.TIME_MEDIUM);
		element(inboxContainer).waitUntilVisible();
		boolean foundEmail = false;
		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("div[ng-repeat='email in emails']"));
		for (WebElement itemNow : emailList) {
			String allText = itemNow.getText();
			System.out.println("countorrr "+allText);
			if (allText.contains(title)) {
				itemNow.findElement(By.cssSelector("div.innermail.ng-binding")).click();
				foundEmail = true;
				break;
			}
		}
		Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
	}

	public String getConfirmationLink() {
		getDriver().switchTo().frame(iFrameElement);
		waitABit(1000);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}
	
	public String getRegisterLink() {
		getDriver().switchTo().frame(iFrameElement);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='/customer/party/confirm']")).getAttribute("href");
		return confirmLink;
	}

	public void clickConfirmEmail() {
		getDriver().switchTo().frame(iFrameElement);
		getDriver().findElement(By.cssSelector("a[href*='confirm']")).click();
	}

	public String grabCouponCode() {
		getDriver().switchTo().frame(iFrameElement);
		return getDriver().findElement(By.cssSelector("table[bgcolor='#FFFFFF'] tbody > tr:nth-child(3)")).getText();
	}



	public void clickPartyConfirmationLink() {
		getDriver().switchTo().frame(iFrameElement);
		//get first a (link with Yes) from page
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}
	
}

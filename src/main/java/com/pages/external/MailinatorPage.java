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

		navigate(UrlConstants.MAILINATOR_WEB_MAIL + UrlConstants.MAILINATOR_IMBOX_SUFFIX + email);
		getDriver().navigate().refresh();
		waitABit(TimeConstants.TIME_MEDIUM);
		element(inboxContainer).waitUntilVisible();
		boolean foundEmail = false;
		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("div[ng-repeat='email in emails']"));
		for (WebElement itemNow : emailList) {
			String allText = itemNow.getText();
			if (allText.contains(title)) {
				itemNow.findElement(By.cssSelector("div.innermail.ng-binding")).click();
				foundEmail = true;
				break;
			}
		}
		Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
	}

	public String getConfirmationEmail() {
		getDriver().switchTo().frame(iFrameElement);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
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

}

package com.pages.external;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

public class YopmailPage extends AbstractPage {

	@FindBy(css = "body.bodyinbox")
	private WebElement inboxContainer;

	@FindBy(className = "mailview")
	private WebElement mailContainer;

	@FindBy(css = "iframe#ifmail")
	private WebElement iFrameElement;

	@FindBy(id = "ifinbox")
	private WebElement iFrameInbox;

	@FindBy(id = "login")
	private WebElement emailInput;

	@FindBy(css = "input[type='submit']")
	private WebElement submitEmailBtn;

	public void openEmail(String email, String title) {

		navigate(UrlConstants.YOPMAIL_WEB_MAIL);
		element(emailInput).clear();
		element(emailInput).sendKeys(email);
		submitEmailBtn.click();
		getDriver().switchTo().frame(iFrameInbox);
		element(inboxContainer).waitUntilVisible();
		boolean foundEmail = false;
		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("div.m a"));
		for (WebElement itemNow : emailList) {
			if (itemNow.getText().contains(title)) {
				itemNow.click();
				getDriver().switchTo().defaultContent();
				waitABit(2000);
				foundEmail = true;
				break;
			}
		}
		Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
	}

	public String getConfirmationLink() {

		getDriver().switchTo().frame(iFrameElement);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public String getRegisterLink() {

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

	public void clickPartyConfirmationLink() {
		getDriver().switchTo().frame(iFrameElement);
		//get first a (link with Yes) from page
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}

}

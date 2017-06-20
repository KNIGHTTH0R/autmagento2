package com.pages.external;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

public class FakeMailGenerator extends AbstractPage {
//	@FindBy(id = "#email-list")
//	private WebElement inboxContainer;
	@FindBy(id = "emailFrame")
	private WebElement iFrameInbox;
	public void openEmail(String email, String title) {
		System.out.println("this is the email" + email);
		navigate(UrlConstants.FMG_WEB_MAIL + UrlConstants.FMG_WEB_MAIL_INBOX_SUFFIX + UrlConstants.FMG_WEB_MAIL_DOMAIN+email);
		waitABit(10000);
		List<WebElement> emailList = getDriver().findElements(By.cssSelector("#email-list li a"));
		
		
		boolean foundEmail = false;
		
		if(emailList.size()!=0){
			for (WebElement itemNow : emailList) {
			
				if (itemNow.getText().contains(title)) {
					itemNow.click();
					//getDriver().switchTo().defaultContent();
					waitABit(5000);
					foundEmail = true;
					break;
				}
			}
			
			
			CustomVerification.verifyTrue("The email with the title " + title + " was not found", foundEmail);
		}else{
			CustomVerification.verifyTrue("No emails received", true);
		}
		
		getDriver().switchTo().frame(iFrameInbox);
	}

	public String getConfirmationLink() {
		waitABit(4000);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;

	}

	public String getRegisterLink() {
		waitABit(4000);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public void clickConfirmEmail() {
		getDriver().findElement(By.cssSelector("a[href*='confirm']")).click();
	}

	public String grabCouponCode() {

		return getDriver().findElement(By.cssSelector("table[bgcolor='#FFFFFF'] tbody > tr:nth-child(3)")).getText();
	}

	public void clickPartyConfirmationLink() {
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}
}

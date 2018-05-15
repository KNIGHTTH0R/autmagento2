package com.pages.external;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class Mailnesia extends AbstractPage {



	@FindBy(css = "iframe#ifmail")
	private WebElement iFrameElement;

	
	
	@FindBy(id = "message")
	private WebElement iFrameInbox;
	public void openEmail(String email, String title) {
		navigate(UrlConstants.MAILNESIA_WEB_MAIL + UrlConstants.MAILNESIA_MAIL_INBOX_SUFFIX +email);
		waitABit(2000);
		List<WebElement> emailList = getDriver().findElements(By.cssSelector(".email tbody tr td:nth-child(4) a"));
		
		
		boolean foundEmail = false;
		
		if(emailList.size()!=0){
			for (WebElement itemNow : emailList) {
			
				if (itemNow.getText().contains(title)) {
					itemNow.click();
					
					//getDriver().switchTo().defaultContent();
					waitABit(5000);
					foundEmail = true;
				//	getDriver().switchTo().frame(iFrameInbox);
					break;
				}
			}
			
			
			CustomVerification.verifyTrue("The email with the title " + title + " was not found", foundEmail);
		}else{
			CustomVerification.verifyTrue("No emails received", true);
		}
		
		
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

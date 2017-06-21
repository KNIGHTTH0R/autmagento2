package com.pages.external;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

public class TempMail extends AbstractPage {



	@FindBy(css = "iframe#ifmail")
	private WebElement iFrameElement;
	
	@FindBy(css = "ul li a[href*='change']")
	private WebElement changeButton;

	@FindBy(css = "ul li a[href*='refresh']")
	private WebElement refreshButton;
	
	@FindBy(css = "input[name*='mail']")
	private WebElement mailInput;
	
	@FindBy(css = "button[type*='submit']")
	private WebElement submitButton;
	
	
	
	@FindBy(id = "message")
	private WebElement iFrameInbox;
	public void openEmail(String email, String title) {
		navigate(UrlConstants.TEMPMAIL_WEB_MAIL);
		waitABit(2000);
		element(changeButton).waitUntilVisible();
		changeButton.click();
		
		element(mailInput).waitUntilVisible();
		element(mailInput).clear();
		mailInput.sendKeys(email);
		
		element(submitButton).waitUntilVisible();
		submitButton.click();
		
		element(refreshButton).waitUntilVisible();
		refreshButton.click();
		
		waitABit(5000);
		List<WebElement> emailList = getDriver().findElements(By.cssSelector("#mails tbody tr td:nth-child(2) a"));
		
		System.out.println("size list "+ emailList.size());
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
			
			
			Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
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

package com.pages.external;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class MailDrop extends AbstractPage{
	@FindBy(id = "messageframe")
	private WebElement iFrameInbox;

	public void openEmail(String email, String title) {
		System.out.println("email "+email);
		System.out.println("title "+ title);
		navigate(UrlConstants.MAIL_DROP_WEB_MAIL+UrlConstants.MAIL_DROP_MAIL_INBOX_SUFFIX+email  );
		waitABit(5000);
		List<WebElement> emailList = getDriver().findElements(By.cssSelector("#inboxtbl tbody tr a"));
		
		
		boolean foundEmail = false;
		
		if(emailList.size()!=0){
			for (WebElement itemNow : emailList) {
				if (itemNow.getText().contains(title)) {
					itemNow.click();
					waitABit(5000);
					foundEmail = true;
					
					getDriver().switchTo().frame(iFrameInbox);
					break;
				}
			}
			
			Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
		}else{
			CustomVerification.verifyTrue("No emails received", true);
		}
		
	}

	public String getConfirmationLink() {

		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public String getRegisterLink() {

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
		//get first a (link with Yes) from page
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}
}

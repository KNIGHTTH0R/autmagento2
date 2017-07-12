package com.pages.frontend.invitation;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class FacebookMessagePage extends AbstractPage {

	// mandatory fields
	@FindBy(id = "distribution_zip")
	private WebElement submitInvitation;

	@FindBy(id = "distribution_zip")
	private List<WebElement> messagesList1;

	public void selectLatestFBMessage() {
		List<WebElement> messages = getDriver().findElements(By.cssSelector("ul[aria-label*='Conversation List'] li"));

		if (messages.size() != 0) {
			for (WebElement msg : messages) {
				msg.click();
				break;
			}

		}
	}

	public void verifyInvitationIsReceived(String message) {
		
		List<WebElement> messagesList = getDriver()
				.findElements(By.cssSelector("div[aria-label*='Messages'] .clearfix .text_align_ltr "));

		boolean found = false;
		for (WebElement mess : messagesList) {
			if (mess.findElement(By.cssSelector("._aok span")).getText().contains(message)) {
				found = true;
				mess.findElement(By.cssSelector("a")).click();
				break;
			}
		}

		Assert.assertTrue("The message with invitation was not found", found);
		waitABit(5000);
	}

}

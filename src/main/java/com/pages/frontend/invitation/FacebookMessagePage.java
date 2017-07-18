package com.pages.frontend.invitation;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class FacebookMessagePage extends AbstractPage {

	

	@FindBy(css = ".col-main a")
	private WebElement invitationLink;
	
	

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

	public void clickOnInvitationLink() {
		element(invitationLink).waitUntilVisible();
		invitationLink.click();
	}

	public void verifySharedMessage(String message) {
		List<WebElement> messagesList = getDriver()
				.findElements(By.cssSelector("#recent_capsule_container ol>div:nth-child(1) div[role*='article']"));
		Actions actions = new Actions(getDriver());

		
		boolean found = false;
		for (WebElement mess : messagesList) {
			if (mess.findElement(By.cssSelector(".userContent p")).getText().contains(message)) {
				found = true;
				List<WebElement>link= mess.findElements(By.cssSelector("._3ekx a"));
				actions.moveToElement(link.get(0)).click().perform();
				break;
			}
		}

		Assert.assertTrue("The Share post was not found", found);
		waitABit(2000);
		
	}

	
	
	
}

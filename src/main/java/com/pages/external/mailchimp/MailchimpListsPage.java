package com.pages.external.mailchimp;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailchimpListsPage extends AbstractPage {

	public void selectDesiredLink(String linkName) {
		List<WebElement> links = getDriver().findElements(By.cssSelector("ul#lists li a[href*='/lists/members/?id=']"));
		boolean foundLink = false;
		for (WebElement link : links) {
			if (link.getText().contentEquals(linkName)) {
				foundLink = true;
				link.click();
				break;
			}
		}
		Assert.assertTrue("The link was not found !!!", foundLink);
	}
}

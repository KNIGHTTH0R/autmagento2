package com.pages.external.mailchimp;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailchimpSearchPage extends AbstractPage {

	@FindBy(id = "global-search")
	private WebElement searchInput;

	@FindBy(css = "button[type='submit']")
	private WebElement submitSearch;

	public void inputSearchTerm(String term) {
		element(searchInput).waitUntilVisible();
		element(searchInput).sendKeys(term);
	}

	public void submitSearch() {
		element(submitSearch).waitUntilVisible();
		submitSearch.click();
	}

	public void selectSearchResultAndViewProfile(String searchTerm) {

		List<WebElement> links = getDriver().findElements(By.cssSelector("ul.media-list li div.line.member-summary"));
		boolean foundLink = false;
		for (WebElement link : links) {
			if (link.getText().contains(searchTerm)) {
				foundLink = true;
				link.findElement(By.cssSelector("div.button-small.nomargin")).click();
				break;
			}
		}
		Assert.assertTrue("The link was not found !!!", foundLink);
	}

}

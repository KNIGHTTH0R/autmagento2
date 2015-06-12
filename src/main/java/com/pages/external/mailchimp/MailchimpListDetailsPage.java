package com.pages.external.mailchimp;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailchimpListDetailsPage extends AbstractPage {

	@FindBy(css = "a.freddicon.search.lists-search.nomargin")
	private WebElement startSearchButton;

	public void startSearch() {
		element(startSearchButton).waitUntilVisible();
		startSearchButton.click();
	}
}

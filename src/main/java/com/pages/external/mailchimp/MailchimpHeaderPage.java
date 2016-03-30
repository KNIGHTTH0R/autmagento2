package com.pages.external.mailchimp;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MailchimpHeaderPage extends AbstractPage {

	@FindBy(css = "ul.linear-list.selfclear a[href='/lists/']")
	private WebElement listsTab;

	public void goToListsTab() {
		element(listsTab).waitUntilVisible();
		listsTab.click();
	}

}

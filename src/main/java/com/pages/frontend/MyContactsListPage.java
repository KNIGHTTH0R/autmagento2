package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MyContactsListPage extends AbstractPage {

	@FindBy(id = "contact-search-query")
	private WebElement searchInput;

	@FindBy(id = "contact-search-submit")
	private WebElement searchSubmit;

	public void inputSearchTerm(String contactName) {
		element(searchInput).waitUntilVisible();
		searchInput.sendKeys(contactName);
	}

	public void submitContactSearch() {
		element(searchSubmit).waitUntilVisible();
		searchSubmit.click();
	}

	public void verifyThatContactIsUniqueInStylecoachList() {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		Assert.assertTrue("There should be only one row (contact) found !!!", contactsList.size() == 1);
	}

	// only two contacts should be redistributed each time and the contacts
	// should be in the top of the list.So in order be sure that the contacts
	// are the first two from the list we get only the first two rows of the
	// table (performance improved also)

	public void verifyThatContactIsInTheList(String contactName) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr")).subList(0, 2);
		boolean found = false;
		for (WebElement contact : contactsList) {
			if (contact.findElement(By.cssSelector("td:nth-child(4) a")).getText().contentEquals(contactName.toUpperCase())) {
				// Assert.assertTrue("The background is not grey!!!",
				// contact.getAttribute("style").contentEquals("background-color: lightgrey;"));
				found = true;
			}
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);
	}

	// public void verifyThatContactMatchesAllTerms(String... terms) {
	// List<WebElement> contactsList =
	// getDriver().findElements(By.cssSelector("form#contacts-form tbody tr")).subList(0,
	// 2);
	// boolean found = false;
	// for (WebElement contact : contactsList) {
	// boolean matchesAllTerms = true;
	// for (String term : terms) {
	// if (!contact.getText().contains(term.toUpperCase())) {
	// matchesAllTerms = false;
	// }
	// }
	// if (matchesAllTerms) {
	// Assert.assertTrue("The background is not grey!!!",
	// contact.getAttribute("style").contentEquals("background-color: lightgrey;"));
	// found = true;
	// break;
	// }
	//
	// }
	// Assert.assertTrue("Contact was not found or not all the terms were found",
	// found);
	// }
	public void verifyUnicAndOpenContactDetails(String... terms) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		boolean found = false;
		for (WebElement contact : contactsList) {
			boolean matchesAllTerms = true;
			for (String term : terms) {
				if (!contact.getText().contains(term.toUpperCase())) {
					matchesAllTerms = false;
				}
			}
			if (matchesAllTerms) {
				if (found) {
					Assert.fail(String.format("A duplicate of the element was found in the table!"));
				} else {
					found = true;
					contact.findElement(By.cssSelector("td a.blue-text.contact-link"));
				}
			}

		}
		Assert.assertTrue("Contact was not found!!!", found);
	}

}

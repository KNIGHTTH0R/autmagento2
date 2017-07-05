package com.pages.frontend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

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

	public void verifyThatContactIsInTheList(String contactName) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		boolean found = false;
		for (WebElement contact : contactsList) {
			if (contact.findElement(By.cssSelector("td:nth-child(4) a")).getText().contentEquals(contactName.toUpperCase())) {
				Assert.assertTrue("The background is not grey!!!", contact.getAttribute("style").contentEquals("background-color: lightgrey;"));
				found = true;
			}
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);
	}

	
	public void verifyUnicAndOpenContactDetails(String... terms) {
		WebElement link = null;
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
					break;
				} else {
					found = true;
					link = contact.findElement(By.cssSelector("td a[href*='/index/'] "));
				}
			}

		}
		if (found) {
			link.click();
		}
		Assert.assertTrue("Contact was not found!!!", found);
	}

	
//	public void verifyUnicAndOpenContactDetails(String... terms) {
//		WebElement link = null;
//		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr[class*='grey']"));
//		boolean found = false;
//		for (WebElement contact : contactsList) {
//			boolean matchesAllTerms = true;
//			for (String term : terms) {
//				if (!contact.getText().contains(term.toUpperCase())) {
//					matchesAllTerms = false;
//				}
//			}
//			if (matchesAllTerms) {
//				if (found) {
//					Assert.fail(String.format("A duplicate of the element was found in the table!"));
//					break;
//				} else {
//					found = true;
//					link = contact.findElement(By.cssSelector("td a.blue-text.contact-link"));
//				}
//			}
//
//		}
//		if (found) {
//			link.click();
//		}
//		Assert.assertTrue("Contact was not found!!!", found);
//	}

	public void openContactDetailsPage(String contactEmail) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		WebElement link = null;
		boolean found = false;
		for (WebElement contact : contactsList) {
			if (contact.findElement(By.cssSelector("td:nth-child(4) a")).getText().contentEquals(contactEmail.toUpperCase())) {
				found = true;
				link=contact.findElement(By.cssSelector("td:nth-child(3) a"));
				
			}
		}
		if(found){
			link.click();
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);
		
	}

}

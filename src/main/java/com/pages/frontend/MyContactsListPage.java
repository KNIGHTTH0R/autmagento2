package com.pages.frontend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
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
			if (contact.findElement(By.cssSelector("td:nth-child(4) a")).getText()
					.contentEquals(contactName.toUpperCase())) {
				Assert.assertTrue("The background is not grey!!!",
						contact.getAttribute("style").contentEquals("background-color: lightgrey;"));
				found = true;
			}
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);
	}

	public void verifyUnicAndOpenContactDetails(String... terms) {

		System.out.println("sunt aici");
		WebElement link = null;
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		boolean found = false;
		for (WebElement contact : contactsList) {
			System.out.println("contact: " + contact);
			boolean matchesAllTerms = true;
			for (String term : terms) {
				System.out.println("term: " + term);
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

	// public void verifyUnicAndOpenContactDetails(String... terms) {
	// WebElement link = null;
	// List<WebElement> contactsList =
	// getDriver().findElements(By.cssSelector("form#contacts-form tbody
	// tr[class*='grey']"));
	// boolean found = false;
	// for (WebElement contact : contactsList) {
	// boolean matchesAllTerms = true;
	// for (String term : terms) {
	// if (!contact.getText().contains(term.toUpperCase())) {
	// matchesAllTerms = false;
	// }
	// }
	// if (matchesAllTerms) {
	// if (found) {
	// Assert.fail(String.format("A duplicate of the element was found in the
	// table!"));
	// break;
	// } else {
	// found = true;
	// link = contact.findElement(By.cssSelector("td
	// a.blue-text.contact-link"));
	// }
	// }
	//
	// }
	// if (found) {
	// link.click();
	// }
	// Assert.assertTrue("Contact was not found!!!", found);
	// }

	public void openContactDetailsPage(String firstname,String lastName) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		System.out.println("firstname "+firstname);
		System.out.println("contactsList" +contactsList.size());
		WebElement link = null;
		boolean found = false;
		for (WebElement contact : contactsList) {
			System.out.println("textttul"+contact.findElement(By.cssSelector("td:nth-child(2)")).getText());
			if (contact.findElement(By.cssSelector("td:nth-child(2)")).getText()
					.contains(firstname.toUpperCase()) ) {
				found = true;
				link = contact.findElement(By.cssSelector("td:nth-child(2) a"));
				break;
			}
		}
		if (found) {
			link.click();
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);

	}

	public void verifyContactCreationUnderHost(String[] terms) {
		System.out.println("sunt aici");
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr"));
		boolean found = false;

		boolean matchesAllTerms = true;
		for (String term : terms) {
			System.out.println("term: " + term);
			if (!contactsList.get(0).getText().contains(term.toUpperCase())) {
				matchesAllTerms = false;
			}
		}

		CustomVerification.verifyTrue("Failure: not all term matches", matchesAllTerms);
		Assert.assertTrue("Contact was not found!!!", found);
	}

}

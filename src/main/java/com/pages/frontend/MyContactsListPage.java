package com.pages.frontend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class MyContactsListPage extends AbstractPage {

	// only two contacts should be redistributed each time and the contacts
	// should be in the top of the list.So in order be sure that the contacts
	// are the first two from the list we get only the first two rows of the
	// table (performance improved also)

	public void verifyThatContactIsInTheList(String contactName) {
		List<WebElement> contactsList = getDriver().findElements(By.cssSelector("form#contacts-form tbody tr td:nth-child(4) a")).subList(0, 2);
		boolean found = false;
		for (WebElement contact : contactsList) {
			if (contact.getText().contentEquals(contactName.toUpperCase())) {
				found = true;
			}
		}
		Assert.assertTrue("The contact was not found in the contact list of the stylecoach", found);
	}

}

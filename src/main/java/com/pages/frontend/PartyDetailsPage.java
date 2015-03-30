package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.Constants;
import com.tools.requirements.AbstractPage;

public class PartyDetailsPage extends AbstractPage {

	@FindBy(id = "closeParty")
	private WebElement closeParty;

	@FindBy(css = "input#guests")
	private WebElement guests;

	@FindBy(css = "div.style-party-detail > p")
	private WebElement messageContainer;

	@FindBy(css = ".button[type*='submit'][value*='YES']")
	private WebElement popupPartyCloseButton;

	public void closeParty() {
		element(closeParty).waitUntilVisible();
		closeParty.click();
	}

	public void popupCloseParty() {
		element(popupPartyCloseButton).waitUntilVisible();
		popupPartyCloseButton.click();
	}

	public void verifyThatPartyIsClosed() {		
		element(messageContainer).waitUntilVisible();
		System.out.println(messageContainer.getText() + " : " + Constants.PARTY_CLOSED);
		Assert.assertTrue("The status should be Party geschlossen and it's not ", messageContainer.getText().contains(Constants.PARTY_CLOSED));	

	}

	public void typePartyAttendersNumber(String number) {
		element(guests).waitUntilVisible();
		element(guests).clear();
		element(guests).sendKeys(number);
	}

}

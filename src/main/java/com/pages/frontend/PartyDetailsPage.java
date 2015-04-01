package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.Constants;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractPage;

public class PartyDetailsPage extends AbstractPage {

	@FindBy(id = "closeParty")
	private WebElement closeParty;

	@FindBy(id = "editParty")
	private WebElement editParty;

	@FindBy(id = "deleteParty")
	private WebElement deleteParty;

	@FindBy(css = "#deleteForm button[value*='YES']")
	private WebElement confirmDeleteParty;

	@FindBy(id = "invitations-list-table")
	private WebElement invitationsList;

	@FindBy(id = "name_0")
	private WebElement guestName;

	@FindBy(id = "email_0")
	private WebElement guestEmail;

	@FindBy(id = "message")
	private WebElement guestMessage;

	@FindBy(css = "#inviteGuestsWrapper button[type*='submit']")
	private WebElement sendInvitation;

	@FindBy(id = "inviteGuests")
	private WebElement inviteGuests;

	@FindBy(css = "a[href*='http://staging-aut.pippajean.com/qateam/stylist/party/create/parentId/']")
	private WebElement createFolowUpParty;

	@FindBy(css = "input#guests")
	private WebElement guests;

	@FindBy(css = "div.style-party-detail > p")
	private WebElement messageContainer;

	@FindBy(css = ".button[type*='submit'][value*='YES']")
	private WebElement popupPartyCloseButton;

	@FindBy(css = "table.data-table.follow-up-parties")
	private WebElement folowUpPartysTable;

	public void closeParty() {
		element(closeParty).waitUntilVisible();
		closeParty.click();
	}

	public void editParty() {
		element(editParty).waitUntilVisible();
		editParty.click();
	}

	public void createFolowUpParty() {
		element(createFolowUpParty).waitUntilVisible();
		createFolowUpParty.click();
	}

	public void deleteParty() {
		element(deleteParty).waitUntilVisible();
		deleteParty.click();
	}

	public void confirmDeleteParty() {
		element(confirmDeleteParty).waitUntilVisible();
		confirmDeleteParty.click();
	}

	public void inviteGuests() {
		element(inviteGuests).waitUntilVisible();
		inviteGuests.click();
	}

	public void sendInvitation() {
		element(sendInvitation).waitUntilVisible();
		sendInvitation.click();
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

	public void verifyThatFolowUpPartyAppearsOnPartyDetailsPage(String... terms) {
		element(folowUpPartysTable).waitUntilVisible();
		List<WebElement> partiesList = folowUpPartysTable.findElements(By.cssSelector("tbody tr"));
		boolean foundParty = false;
		for (WebElement party : partiesList) {
			boolean foundAllTerms = true;
			for (String term : terms) {
				if (!party.getText().contains(term)) {
					foundAllTerms = false;
				}

			}
			if (foundAllTerms) {
				foundParty = true;
				break;
			}

		}
		Assert.assertTrue("The folow up party was not found", foundParty);
	}

	public void typePartyAttendersNumber(String number) {
		element(guests).waitUntilVisible();
		element(guests).clear();
		element(guests).sendKeys(number);
	}

	public void typeGuestName(String name) {
		element(guestName).waitUntilVisible();
		element(guestName).sendKeys(name);
	}

	public void typeGuestEmail(String email) {
		element(guestEmail).waitUntilVisible();
		element(guestEmail).sendKeys(email);
	}

	public void typeMessageForGuest(String message) {
		element(guestMessage).waitUntilVisible();
		element(guestMessage).sendKeys(message);
	}

	public void verifyThatGuestIsInvited(CustomerFormModel customerData) {
		element(invitationsList).waitUntilVisible();
		List<WebElement> invitesList = invitationsList.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement invite : invitesList) {
			if (invite.findElement(By.cssSelector("td:first-child")).getText().contentEquals(customerData.getFirstName())) {
				found = true;
			}

		}
		Assert.assertTrue("The guest was not found in invites list", found);
	}

}

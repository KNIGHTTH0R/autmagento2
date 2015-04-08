package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

	@FindBy(id = "my-orders-table")
	private WebElement ordersList;

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

	@FindBy(css = "div.col-main.pos-rel")
	private WebElement partyDetailsAndActionsContainer;

	@FindBy(css = "button[title*='Für eine Kundin bestellen']")
	private WebElement orderForCustomer;

	@FindBy(id = "hostess-confirmation")
	private WebElement sendInvitationToHostess;

	@FindBy(css = "#hostessConfirmation button[type*='submit']")
	private WebElement hostessInviteConfirmation;

	@FindBy(css = "div#wishlistGuestsFormContainer img")
	private WebElement wishlistProductImage;

	@FindBy(css = "div.prod-tooltip.js-over")
	private WebElement wishlistProductNameContainer;

	@FindBy(css = "input.input-checkbox.contact-chk")
	private WebElement wishlistProductCheckbox;
	
	@FindBy(css = "button[title*='In den Warenkorb']")
	private WebElement addToBorrowCart;

	// this is made for a single product.if the products is the expected
	// one,select it and borrow it
	public void selectWishlistProductAndAddItToBorrowCart(String productName) {

		Actions builder = new Actions(getDriver());
		builder.moveToElement(wishlistProductImage).build().perform();
		element(wishlistProductNameContainer).waitUntilVisible();
		if (wishlistProductNameContainer.getText().contains(productName)) {
			wishlistProductCheckbox.click();
		}
		addToBorrowCart.click();

	}

	public void orderForCustomer() {
		element(orderForCustomer).waitUntilVisible();
		orderForCustomer.click();
	}

	public void hostessInviteConfirmation() {
		element(hostessInviteConfirmation).waitUntilVisible();
		hostessInviteConfirmation.click();
	}

	public void sendInvitationToHostess() {
		element(sendInvitationToHostess).waitUntilVisible();
		sendInvitationToHostess.click();
	}

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

	public void verifyHostessInviteLink(boolean hostessInviteLinkIsPresent) {
		if (hostessInviteLinkIsPresent) {
			Assert.assertTrue("The invite hostess link should be present and it's not", partyDetailsAndActionsContainer.getText().contains("GASTGEBERIN EINLADEN"));
		} else {
			Assert.assertFalse("The invite hostess link should not be present", partyDetailsAndActionsContainer.getText().contains("GASTGEBERIN EINLADEN"));
		}

	}

	public void verifyEditLink(boolean editLinkIsPresent) {
		if (editLinkIsPresent) {
			Assert.assertTrue("The edit link should be present and it's not", partyDetailsAndActionsContainer.getText().contains("Style Party bearbeiten"));
		} else {
			Assert.assertFalse("The edit link should not be present", partyDetailsAndActionsContainer.getText().contains("Style Party bearbeiten"));
		}

	}

	public void verifyDeleteLink(boolean deleteLinkIsPresent) {
		if (deleteLinkIsPresent) {
			Assert.assertTrue("The delete link should be present and it's not", partyDetailsAndActionsContainer.getText().contains("Style Party löschen"));
		} else {
			Assert.assertFalse("The delete link should not be present", partyDetailsAndActionsContainer.getText().contains("Style Party löschen"));
		}

	}

	public void verifyInviteGuestsLink(boolean inviteGuestsLinkIsPresent) {
		if (inviteGuestsLinkIsPresent) {
			Assert.assertTrue("The invite guests button should be present and it's not", partyDetailsAndActionsContainer.getText().contains("GÄSTE EINLADEN"));
		} else {
			Assert.assertFalse("The invite guests button should not be present", partyDetailsAndActionsContainer.getText().contains("GÄSTE EINLADEN"));
		}

	}

	public void verifyFolowUpPartyLink(boolean folowUpPartyLinkIsPresent) {
		if (folowUpPartyLinkIsPresent) {
			Assert.assertTrue("The follow up button should be present and it's not", partyDetailsAndActionsContainer.getText().contains("LEGE EINE FOLGEPARTY AN"));
		} else {
			Assert.assertFalse("The follow up button should not be present", partyDetailsAndActionsContainer.getText().contains("LEGE EINE FOLGEPARTY AN"));
		}

	}

	public void verifyCustomerOrderLink(boolean customerOrderLinkIsPresent) {
		if (customerOrderLinkIsPresent) {
			Assert.assertTrue("The customer order button should be present and it's not", partyDetailsAndActionsContainer.getText().contains("FÜR EINE KUNDIN BESTELLEN"));
		} else {
			Assert.assertFalse("The customer order button should not be present", partyDetailsAndActionsContainer.getText().contains("FÜR EINE KUNDIN BESTELLEN"));
		}

	}

	public void verifyClosePartyLink(boolean closePartyLinkIsPresent) {
		if (closePartyLinkIsPresent) {
			Assert.assertTrue("The close party button should be present and it's not", partyDetailsAndActionsContainer.getText().contains("STYLE PARTY SCHLIESSEN"));
		} else {
			Assert.assertFalse("The close party  button should not be present", partyDetailsAndActionsContainer.getText().contains("STYLE PARTY SCHLIESSEN"));
		}

	}

	public void verifyPartyStatus(String status) {
		getDriver().navigate().refresh();
		element(messageContainer).waitUntilVisible();
		Assert.assertTrue("The status should be " + status + " and it's not ", messageContainer.getText().contains(status));

	}

	public void verifyPlannedPartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyPartyStatus(Constants.PARTY_PLANNED);
		verifyHostessInviteLink(true);
		verifyEditLink(true);
		verifyDeleteLink(true);
		verifyInviteGuestsLink(true);
		verifyFolowUpPartyLink(false);
		verifyCustomerOrderLink(false);
		verifyClosePartyLink(false);
	}

	public void verifyActivePartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyPartyStatus(Constants.PARTY_ACTIVE);
		verifyHostessInviteLink(true);
		verifyEditLink(false);
		verifyDeleteLink(false);
		verifyInviteGuestsLink(true);
		verifyFolowUpPartyLink(true);
		verifyCustomerOrderLink(true);
		verifyClosePartyLink(true);
	}

	public void verifyClosedPartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyPartyStatus(Constants.PARTY_CLOSED);
		verifyHostessInviteLink(true);
		verifyEditLink(false);
		verifyDeleteLink(false);
		verifyInviteGuestsLink(false);
		verifyFolowUpPartyLink(false);
		verifyCustomerOrderLink(false);
		verifyClosePartyLink(false);
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

	public void verifyThatGuestIsInvited(String name) {
		element(invitationsList).waitUntilVisible();
		List<WebElement> invitesList = invitationsList.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement invite : invitesList) {
			if (invite.findElement(By.cssSelector("td:first-child")).getText().contentEquals(name)) {
				found = true;
			}

		}
		Assert.assertTrue("The guest was not found in invites list", found);
	}

	public void verifyThatOrderIsInTheOrdersList(String order) {
		element(ordersList).waitUntilVisible();
		List<WebElement> invitesList = ordersList.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement invite : invitesList) {
			if (invite.findElement(By.cssSelector("td:first-child")).getText().contentEquals(order)) {
				found = true;
			}

		}
		Assert.assertTrue("The order was not found in orders list", found);
	}

}

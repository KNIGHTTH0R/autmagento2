package com.pages.frontend;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

public class PartyDetailsPage extends AbstractPage {

	@FindBy(id = "closeParty")
	private WebElement closeParty;

	@FindBy(css = "div.goes-up.right.pos-abs #editParty")
	private WebElement editParty;

	@FindBy(css = "div.goes-up.right.pos-abs #deleteParty")
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

	@FindBy(css = "a[href*='stylist/party/create/parentId/']")
	private WebElement createFolowUpParty;

	@FindBy(css = "input#guests")
	private WebElement guests;

	@FindBy(css = "div.style-party-detail > p")
	private WebElement messageContainer;

	@FindBy(css = "div.style-party-detail > div > div > p")
	private WebElement closeDateContainer;

	@FindBy(css = ".button[type*='submit'][value*='YES']")
	private WebElement popupPartyCloseButton;

	@FindBy(css = "table.data-table.follow-up-parties")
	private WebElement folowUpPartysTable;

	@FindBy(css = "section.col-main.pos-rel")
	private WebElement partyDetailsAndActionsContainer;

	@FindBy(css = "form[action*='placeCustomerOrder'] button")
	private WebElement orderForCustomer;

	@FindBy(css = "div.clearfix.invite-buttons #hostess-confirmation")
	private WebElement sendInvitationToHostess;

	@FindBy(css = "#hostessConfirmation button[type*='submit']")
	private WebElement hostessInviteConfirmation;

	@FindBy(css = "div#wishlistGuestsFormContainer img")
	private WebElement wishlistProductImage;

	@FindBy(css = "div.prod-tooltip.js-over p")
	private WebElement wishlistProductNameContainer;

	@FindBy(css = "input.input-checkbox.contact-chk")
	private WebElement wishlistProductCheckbox;

	@FindBy(css = "#closeSuccess #jewelry span")
	private WebElement receivedJbContainer;

	@FindBy(css = "#closeSuccess #fiftydiscount")
	private WebElement receivedForthyDiscountsContainer;

	@FindBy(css = "div#wishlistGuestsFormContainer form button[class='button blue-button right clear']")
	private WebElement addToBorrowCart;

	@FindBy(css = "a.fancybox-item.fancybox-close")
	private WebElement closeFancy;

	// this is made for a single product.if the products is the expected
	// one,select it and borrow it
	public void selectWishlistProductAndAddItToBorrowCart(String productName) {
		element(wishlistProductImage).waitUntilVisible();
		List<WebElement> wishlistProductsList = getDriver()
				.findElements(By.cssSelector("div.customer-list-container.clearfix .mini-box img"));

		Assert.assertTrue("There are produscts in party wishlist which should not be there !!!",
				wishlistProductsList.size() == 1);

		Actions builder = new Actions(getDriver());
		builder.moveToElement(wishlistProductImage).build().perform();
		element(wishlistProductNameContainer).waitUntilVisible();
		boolean found = false;
		if (wishlistProductNameContainer.getText().contains(productName)) {
			found = true;
			waitABit(TimeConstants.WAIT_TIME_SMALL);
			wishlistProductNameContainer.click();
			waitABit(TimeConstants.WAIT_TIME_SMALL);
		}
		// just trying to fix a problem - not needed
		builder.moveToElement(addToBorrowCart).build().perform();
		addToBorrowCart.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.WAIT_TIME_SMALL);

		Assert.assertTrue("The product expected to be in wishlist is not present !!!", found);
	}

	public void checkThatPartyWishlistIsEmpty() {
		Assert.assertTrue("The product was found in the wishlist and it shouldn't !!!", getDriver()
				.findElements(By.cssSelector("div.customer-list-container.clearfix .mini-box img")).size() == 0);
	}

	public void returnToParty() {
		element(closeFancy).waitUntilVisible();
		closeFancy.click();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public void orderForCustomer() {
		element(orderForCustomer).waitUntilVisible();
		orderForCustomer.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void hostessInviteConfirmation() {
		element(hostessInviteConfirmation).waitUntilVisible();
		hostessInviteConfirmation.click();
	}

	public void sendInvitationToHostess() {
		element(sendInvitationToHostess).waitUntilVisible();
		sendInvitationToHostess.click();
	}

	public ClosedPartyPerformanceModel grabClosedPartyPerformance() {
		ClosedPartyPerformanceModel result = new ClosedPartyPerformanceModel();
		result.setNoOfOrders(
				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(1) td:nth-child(2)"))
						.getText());
		result.setRetail(
				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(2) td:nth-child(2)"))
						.getText().replace(",", ".").replace(" €", "").replace("€ ", ""));
		result.setIp(
				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(3) td:nth-child(2)"))
						.getText());
		result.setIpInPayment(
				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(4) td:nth-child(2)"))
						.getText());
		result.setJewelryBonus(getDriver().findElement(By.cssSelector("div.col-3 p:nth-child(2) .price")).getText()
				.replace(",", ".").replace(" €", "").replace("€ ", "").trim());
		String[] parts = getDriver().findElement(By.cssSelector("div.col-3 p:nth-child(3)")).getText().split(":");
		result.setFourthyDiscounts(parts[1].trim());

		return result;

	}
	
	public ClosedPartyPerformanceModel grabClosedPartyPerformanceNoOrders() {
		ClosedPartyPerformanceModel result = new ClosedPartyPerformanceModel();
//		result.setNoOfOrders(
//				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(1) td:nth-child(2)"))
//						.getText());
//		result.setRetail(
//				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(2) td:nth-child(2)"))
//						.getText().replace(",", ".").replace(" €", "").replace("€ ", ""));
//		result.setIp(
//				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(3) td:nth-child(2)"))
//						.getText());
//		result.setIpInPayment(
//				getDriver().findElement(By.cssSelector("table.party-performance tbody tr:nth-child(4) td:nth-child(2)"))
//						.getText());
		result.setJewelryBonus(getDriver().findElement(By.cssSelector("div.col-3 p:nth-child(2) .price")).getText()
				.replace(",", ".").replace(" €", "").replace("€ ", "").trim());
		String[] parts = getDriver().findElement(By.cssSelector("div.col-3 p:nth-child(3)")).getText().split(":");
		result.setFourthyDiscounts(parts[1].trim());

		return result;

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
		
		waitForLoadingImageToDissapear();
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
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.textToBePresentInElement(
				getDriver().findElement(By.id("closePartyWrapper")), ContextConstants.SUCCESSFULY_CLOSED_PARTY));
	}

	public void verifyHostessInviteLink(boolean hostessInviteLinkIsPresent) {
		if (hostessInviteLinkIsPresent) {
			Assert.assertTrue("The invite hostess link should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.INVITE_HOSTESS));
		} else {
			Assert.assertFalse("The invite hostess link should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.INVITE_HOSTESS));
		}

	}

	public void verifyEditLink(boolean editLinkIsPresent) {
		if (editLinkIsPresent) {
			Assert.assertTrue("The edit link should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.UPDATE_PARTY));
		} else {
			Assert.assertFalse("The edit link should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.UPDATE_PARTY));
		}

	}

	public void verifyDeleteLink(boolean deleteLinkIsPresent) {
		if (deleteLinkIsPresent) {
			Assert.assertTrue("The delete link should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.DELETE_PARTY));
		} else {
			Assert.assertFalse("The delete link should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.DELETE_PARTY));
		}

	}

	public void verifyInviteGuestsLink(boolean inviteGuestsLinkIsPresent) {
		if (inviteGuestsLinkIsPresent) {
			Assert.assertTrue("The invite guests button should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.INVITE_GUEST));
		} else {
			Assert.assertFalse("The invite guests button should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.INVITE_GUEST));
		}

	}

	public void verifyFolowUpPartyLink(boolean folowUpPartyLinkIsPresent) {
		if (folowUpPartyLinkIsPresent) {
			Assert.assertTrue("The follow up button should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.CREATE_FOLLOW_UP_PARTY));
		} else {
			Assert.assertFalse("The follow up button should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.CREATE_FOLLOW_UP_PARTY));
		}

	}

	public void verifyCustomerOrderLink(boolean customerOrderLinkIsPresent) {
		if (customerOrderLinkIsPresent) {
			Assert.assertTrue("The customer order button should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.ORDER_FOR_CUSTOMER));
		} else {
			Assert.assertFalse("The customer order button should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.ORDER_FOR_CUSTOMER));
		}

	}

	public void verifyClosePartyLink(boolean closePartyLinkIsPresent) {
		if (closePartyLinkIsPresent) {
			Assert.assertTrue("The close party button should be present and it's not",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.CLOSE_PARTY));
		} else {
			Assert.assertFalse("The close party  button should not be present",
					partyDetailsAndActionsContainer.getText().contains(ContextConstants.CLOSE_PARTY));
		}

	}

	public void verifyPartyStatus(String status) {
		getDriver().navigate().refresh();
		element(messageContainer).waitUntilVisible();
		Assert.assertTrue("The status should be " + status + " and it's not ",
				messageContainer.getText().contains(status));

	}

	public void verifyPartyAutomaticallyCloseDate(String status) {
		getDriver().navigate().refresh();
		element(messageContainer).waitUntilVisible();
		Assert.assertTrue("The status should be " + status + " and it's not ",
				messageContainer.getText().contains(status));

	}

	public void verifyPlannedPartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyPartyStatus(ContextConstants.PARTY_PLANNED);
		verifyHostessInviteLink(true);
		verifyEditLink(true);
		verifyDeleteLink(true);
		verifyInviteGuestsLink(true);
		verifyFolowUpPartyLink(false);
		verifyCustomerOrderLink(true);
		verifyClosePartyLink(false);
	}

	public void verifyActivePartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyHostessInviteLink(false);
		verifyEditLink(false);
		verifyDeleteLink(false);
		verifyInviteGuestsLink(false);
		verifyFolowUpPartyLink(true);
		verifyCustomerOrderLink(true);
		verifyClosePartyLink(true);
	}

	public void verifyClosedPartyAvailableActions() {
		element(partyDetailsAndActionsContainer).waitUntilVisible();
		verifyPartyStatus(ContextConstants.PARTY_CLOSED);
		verifyHostessInviteLink(false);
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
		element(guestName).sendKeys(String.valueOf(name));
	}

	public void typeGuestEmail(String email) {
		element(guestEmail).waitUntilVisible();
		element(guestEmail).sendKeys(String.valueOf(email));
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

	public void verifyGuestIsInvited(String name) {
		element(invitationsList).waitUntilVisible();
		List<WebElement> invitesList = invitationsList.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement invite : invitesList) {
			if (invite.findElement(By.cssSelector("td:first-child")).getText().contains(name)) {
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

	public void verifyThatBonusesAreRemovedFromParty() {
		Assert.assertTrue("Bonus sections is present and should be not",
				!partyDetailsAndActionsContainer.getText().contains(ContextConstants.HOSTESS_BONUS));
	}

	public void verifyThatAutomaticallyClosePartyDateIsCorrect() throws ParseException {
		if (MongoReader.getContext() == "de") {

			Assert.assertTrue("Automatically close date is not correct",
					closeDateContainer.getText()
							.contains(DateUtils.addDaysToAAGivenDate(
									DateUtils.getCurrentDate("dd. MMM. yyyy", Locale.GERMANY), "dd. MMM. yyyy",
									Locale.GERMANY, 5)));

		} else if (MongoReader.getContext() == "es") {

			final Locale spanish = new Locale("es", "ES");
			Assert.assertTrue("Automatically close date is not correct",
					closeDateContainer.getText().contains(DateUtils.addDaysToAAGivenDate(
							DateUtils.getCurrentDate("dd. MMM. yyyy", spanish), "dd. MMM. yyyy", spanish, 5)));

		} else if (MongoReader.getContext() == "en") {

			final Locale english = new Locale("en", "EN");
			Assert.assertTrue("Automatically close date is not correct",
					closeDateContainer.getText().contains(DateUtils.addDaysToAAGivenDate(
							DateUtils.getCurrentDate("dd. MMM. yyyy", english), "dd. MMM. yyyy", english, 5)));

		}
	}

	public void checkIfAddToBorrowCartButtonIsDisplayed(boolean isDisplayed) {

		// element(addToBorrowCart).waitUntilVisible();

		List<WebElement> partyButton = getDriver().findElements(By.cssSelector(
				"div#wishlistGuestsFormContainer form button[class='button blue-button right clear'] span"));
		// System.out.println("partyyy"+partyButton.get(0).getText());

		if (isDisplayed)
			Assert.assertTrue("The Add to borrow cart button should be present and it's not !!!",
					partyButton.get(0).getText().contains("IN DEN LEIHWARENKORB"));

		else
			// Assert.assertTrue("The Add to borrow cart button is present and
			// it shouldn't !!!",
			// !partyButton.get(0).getText().contains("IN DEN LEIHWARENKORB"));
			Assert.assertTrue("The Add to borrow cart button is present and it shouldn't !!!", partyButton.isEmpty());
	}

	public void checkWishlistSection(List<RegularBasicProductModel> productsWishList) {
		List<WebElement> result = new ArrayList<WebElement>();
		System.out.println("list size " +productsWishList.size());
		List<WebElement> wishlistProductsList = getDriver()
				.findElements(By.cssSelector("div.customer-list-container.clearfix .mini-box img"));

		for (int i = 0; i < productsWishList.size(); i++) {
			for (WebElement item : wishlistProductsList) {
				System.out.println("product "+productsWishList.get(i).getName());
				System.out.println("grabbed product " + item.getAttribute("alt"));
				if (item.getAttribute("alt").contains(productsWishList.get(i).getName())) {
					result.add(item);
					break;
				}
			}

		}
		System.out.println("list size " +productsWishList.size());
		System.out.println("grabbed size " + result.size());
		Assert.assertTrue("Not all products have been validated ", result.size() == productsWishList.size());
	}

	public void editTime() {
		Select oSelect = new Select(getDriver().findElement(By.id("time")));
	//	oSelect.selectByIndex(1);
		oSelect.selectByValue("10:45");
		oSelect.selectByVisibleText("10:45");
		
	}

	public void saveEdit() {
		WebElement save= getDriver().findElement(By.id("save-edit-party"));
		save.click();
	}
}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void closeTheParty(String number) {
		partyDetailsPage().closeParty();
		partyDetailsPage().typePartyAttendersNumber(number);
		partyDetailsPage().popupCloseParty();

	}

	@StepGroup
	public void sendInvitationToGest(CustomerFormModel customerData) {
		partyDetailsPage().inviteGuests();
		partyDetailsPage().typeGuestName(customerData.getFirstName());
		partyDetailsPage().typeGuestEmail(customerData.getEmailName());
		partyDetailsPage().sendInvitation();
	}

	@StepGroup
	public void sendInvitationToGest(String name, String username) {
		partyDetailsPage().inviteGuests();
		partyDetailsPage().typeGuestName(name);
		partyDetailsPage().typeGuestEmail(username);
		partyDetailsPage().sendInvitation();
	}

	@StepGroup
	public void sendInvitationToHostess() {
		partyDetailsPage().sendInvitationToHostess();
		partyDetailsPage().hostessInviteConfirmation();
		waitABit(3000);
	}

	@Step
	public void verifyPartyStatus(String status) {
		getDriver().navigate().refresh();
		partyDetailsPage().verifyPartyStatus(status);
	}

	@Step
	public void typePartyAttendersNumber(String number) {

		partyDetailsPage().typePartyAttendersNumber(number);
	}

	@Step
	public void popupCloseParty() {
		partyDetailsPage().popupCloseParty();
	}

	@Step
	public void inviteGuests() {
		partyDetailsPage().inviteGuests();
	}

	@Step
	public void editParty() {
		partyDetailsPage().editParty();
		waitABit(1000);
	}

	@Step
	public void deleteParty() {
		partyDetailsPage().deleteParty();
		partyDetailsPage().confirmDeleteParty();

	}

	@Step
	public void orderForCustomer() {
		partyDetailsPage().orderForCustomer();
	}

	@Step
	public void orderForCustomerFromParty(String name) {
		placeCustomerOrderFromPartyPage().typeContactName(name);
		placeCustomerOrderFromPartyPage().startOrderForCustomer();
	}

	@Step
	public void verifyCountryRestrictionWhenSelectingCustomerParty(String name) {
		placeCustomerOrderFromPartyPage().typeContactName(name);
		placeCustomerOrderFromPartyPage().startOrderForCustomer();
		placeCustomerOrderFromPartyPage().verifyCustomerIsNotSuitableForTheOrderErrorMessage();
	}

	@Step
	public void verifyThatGuestIsInvited(String name) {
		partyDetailsPage().verifyThatGuestIsInvited(name);
	}

	@Step
	public void verifyThatOrderIsInTheOrdersList(String order) {
		partyDetailsPage().verifyThatOrderIsInTheOrdersList(order);
	}

	@Step
	public void verifyPlannedPartyAvailableActions() {
		partyDetailsPage().verifyPlannedPartyAvailableActions();
	}

	@Step
	public void verifyActivePartyAvailableActions() {
		partyDetailsPage().verifyActivePartyAvailableActions();
	}

	@Step
	public void verifyClosedPartyAvailableActions() {
		partyDetailsPage().verifyClosedPartyAvailableActions();
	}

	@Step
	public void verifyThatFolowUpPartyAppearsOnPartyDetailsPage(String... terms) {
		partyDetailsPage().verifyThatFolowUpPartyAppearsOnPartyDetailsPage(terms);
	}

	@Step
	public void createFolowUpParty() {
		partyDetailsPage().createFolowUpParty();
	}

	@Step
	public void selectWishlistProductAndAddItToBorrowCart(String productName) {
		partyDetailsPage().selectWishlistProductAndAddItToBorrowCart(productName);
	}

}

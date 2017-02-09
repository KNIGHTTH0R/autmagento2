package com.steps.frontend;

import java.text.ParseException;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.constants.TimeConstants;
import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void closeTheParty() {
		partyDetailsPage().closeParty();
		partyDetailsPage().typePartyAttendersNumber("10");
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
		waitABit(TimeConstants.TIME_CONSTANT);
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
		waitABit(TimeConstants.WAIT_TIME_SMALL);
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
	public void returnToParty() {
		partyDetailsPage().returnToParty();
	}

	@Step
	public ClosedPartyPerformanceModel grabClosedPartyPerformance() {
		return partyDetailsPage().grabClosedPartyPerformance();
	}

	@StepGroup
	public void orderForCustomerFromParty(String name) {
		typeContactName(name);
		startOrderForCustomer();
	}

	@Step
	public void typeContactName(String name) {
		placeCustomerOrderFromPartyPage().typeContactName(name);
	}

	@Step
	public void startOrderForCustomer() {
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
	public void verifyThatAutomaticallyClosePartyDateIsCorrect() throws ParseException {
		partyDetailsPage().verifyThatAutomaticallyClosePartyDateIsCorrect();
	}

	@Step
	public void verifyThatBonusesAreRemovedFromParty() {
		partyDetailsPage().verifyThatBonusesAreRemovedFromParty();
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
		refresh();
	}

	@Step
	public void checkThatPartyWishlistIsEmpty() {
		partyDetailsPage().checkThatPartyWishlistIsEmpty();
	}
	
	@Step
	public void checkIfAddToBorrowCartButtonIsDisplayed(boolean isDispalyed) {
		partyDetailsPage().checkIfAddToBorrowCartButtonIsDisplayed(isDispalyed);
	}

	@Step
	public void verifyGuestIsInvited(String customerName) {
		// TODO Auto-generated method stub
		partyDetailsPage().verifyGuestIsInvited(customerName);
	}

	@Step
	public void checkWishlistSection(List<RegularBasicProductModel> productsWishList) {
	
		partyDetailsPage().checkWishlistSection(productsWishList);
		
	}
	
	

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.ClosedPartyPerformanceModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractSteps;

public class PartyDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@StepGroup
	public void closeTheParty() {
		partyDetailsPage().closeParty();
		partyDetailsPage().typePartyAttendersNumber("10");
		partyDetailsPage().popupCloseParty();
		ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.id("closePartyWrapper")), ContextConstants.SUCCESSFULY_CLOSED_PARTY);
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
		refresh();
	}

}

package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractSteps;

public class PartyCreationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickOrderForHostess() {
		partyCreationPage().clickOrderForHostess();
	}

	@Step
	public void clickAddContact() {
		partyCreationPage().clickAddContact();
	}

	@Step
	public void selectFirstAvailableDate() {
		partyCreationPage().selectFirstAvailableDate();
	}

	@Step
	public void selectFirstAvailableHour() {
		partyCreationPage().selectFirstAvailableHour();
	}

	@Step
	public String submitParty() {
		return partyCreationPage().submitParty();
	}

	@Step
	public void typeLocation(String location) {
		partyCreationPage().typeLocation(location);
	}

	@Step
	public void verifyThatPartyCountryListDoesNotContainCountry() {
		partyCreationPage().verifyThatPartyCountryListDoesNotContainRestrictedCountry();
	}

	@Step
	public String fillPartyDetailsForStylistHost() {
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		partyCreationPage().verifyThatPartyCountryListDoesNotContainRestrictedCountry();
		return partyCreationPage().submitParty();

	}

	@Step
	public String fillPartyDetailsForCustomerHost(String name) {
		partyCreationPage().checkHostedByCustomer();
		partyCreationPage().typeCustomerName(name);
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		partyCreationPage().verifyThatPartyCountryListDoesNotContainRestrictedCountry();
		partyCreationPage().selectPartyCountry(ContextConstants.COUNTRY_NAME);
		waitABit(1000);
		return partyCreationPage().submitParty();

	}

	@Step
	public String fillPartyDetailsForNewCustomerHost() {
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		partyCreationPage().verifyThatPartyCountryListDoesNotContainRestrictedCountry();
		waitABit(2000);
		return partyCreationPage().submitParty();

	}

}

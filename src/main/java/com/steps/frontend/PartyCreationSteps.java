package com.steps.frontend;

import java.util.ArrayList;

import net.thucydides.core.annotations.Step;

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
	public String fillPartyDetailsForStylistHost() {
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		return partyCreationPage().submitParty();
	}

//	@Step
//	public ArrayList<String> fillPartyDetailsWithStylistHostForFollowUpParties() {
//		
//		ArrayList<String> ar = new ArrayList<String>();	
//		ar.add(partyCreationPage().selectFirstAvailableDate());
//		ar.add(partyCreationPage().selectFirstAvailableHour());
//		partyCreationPage().submitParty();
//		
//		return ar;
//	}

	@Step
	public String fillPartyDetailsForCustomerHost(String name) {
		partyCreationPage().checkHostedByCustomer();
		partyCreationPage().typeCustomerName(name);
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		return partyCreationPage().submitParty();
	}

	@Step
	public String fillPartyDetailsForNewCustomerHost() {
		partyCreationPage().selectFirstAvailableDate();
		partyCreationPage().selectFirstAvailableHour();
		waitABit(2000);
		return partyCreationPage().submitParty();

	}

}

package com.steps.backend.stylecoach;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.requirements.AbstractSteps;

public class PartyDetailsBackendSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectOptionFromHorizontalMenu(String searchedOption) {
		partyDetailsBackendPage().selectOptionFromHorizontalMenu(searchedOption);
	}

	@Step
	public void addNewBonus() {
		partyDetailsBackendPage().addNewBonus();
	}

	@Step
	public void saveBonus() {
		partyDetailsBackendPage().saveBonus();
	}

	@Step
	public void selectBonusType(String type) {
		partyDetailsBackendPage().selectBonusType(type);
	}

	@Step
	public void typeBonusValue(String value) {
		partyDetailsBackendPage().typeBonusValue(value);
	}

	@Step
	public void reopenParty() {
		partyDetailsBackendPage().reopenParty();
	}

	@Step
	public void goToBonusTab() {
		partyDetailsBackendPage().goToBonusTab();
	}

	@Step
	public void verifyAddBonusSuccessMessage() {
		partyDetailsBackendPage().verifyAddBonusSuccessMessage();
	}

	// TODO make this pretty
	@StepGroup
	public void addJewelryAndFourthyDiscountBonusToParty() {
		partyDetailsBackendPage().goToBonusTab();
		partyDetailsBackendPage().addNewBonus();
		partyDetailsBackendPage().selectBonusType("Jewelry bonus");
		partyDetailsBackendPage().typeBonusValue("100000");
		partyDetailsBackendPage().saveBonus();
		partyDetailsBackendPage().verifyAddBonusSuccessMessage();
		partyDetailsBackendPage().addNewBonus();
		partyDetailsBackendPage().selectBonusType("30% discount bonus");
		partyDetailsBackendPage().typeBonusValue("100000");
		partyDetailsBackendPage().saveBonus();
		partyDetailsBackendPage().verifyAddBonusSuccessMessage();

	}
	@StepGroup
	public void addJewelryAndFourthyDiscountBonusToParty(String jewelryBonus, String forthyDiscount) {
		partyDetailsBackendPage().goToBonusTab();
		partyDetailsBackendPage().addNewBonus();
		partyDetailsBackendPage().selectBonusType("Jewelry bonus");
		partyDetailsBackendPage().typeBonusValue(jewelryBonus);
		partyDetailsBackendPage().saveBonus();
		partyDetailsBackendPage().verifyAddBonusSuccessMessage();
		partyDetailsBackendPage().addNewBonus();
		partyDetailsBackendPage().selectBonusType("30% discount bonus");
		partyDetailsBackendPage().typeBonusValue(forthyDiscount);
		partyDetailsBackendPage().saveBonus();
		partyDetailsBackendPage().verifyAddBonusSuccessMessage();
		
	}

}

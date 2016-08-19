package com.pages.backend.stylecoach;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.data.backend.PartyBackendPerformanceModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

public class PartyDetailsBackendPage extends AbstractPage {

	@FindBy(css = "#party_details_view li")
	private WebElement optionsList;
	
	@FindBy(css = "button[onClick*='reopenParty']")
	private WebElement reopenPartyButton;

	@FindBy(css = "button.scalable.add")
	private WebElement addBonusButton;

	@FindBy(id = "bonusType")
	private WebElement bonusType;

	@FindBy(id = "party_details_view_party_details_tab_bonuses")
	private WebElement bonusTab;

	@FindBy(id = "value")
	private WebElement bonusValue;

	@FindBy(css = "button.scalable.save")
	private WebElement saveBonus;

	@FindBy(css = "li.success-msg")
	private WebElement successMessage;

	public void selectOptionFromHorizontalMenu(String searchedOption) {
		evaluateJavascript("jQuery.noConflict();");
		element(optionsList).waitUntilVisible();
		List<WebElement> optionsList = getDriver().findElements(By.cssSelector("#party_details_view li"));
		for (WebElement option : optionsList) {
			if (option.getText().contains(searchedOption)) {
				option.findElement(By.cssSelector("a")).click();
				waitABit(2000);
				break;
			}
		}
	}

	public void addNewBonus() {
		evaluateJavascript("jQuery.noConflict();");
		element(addBonusButton).waitUntilVisible();
		addBonusButton.click();
	}

	public void goToBonusTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(bonusTab).waitUntilVisible();
		bonusTab.click();
		bonusTab.click();
	}

	public void saveBonus() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveBonus).waitUntilVisible();
		saveBonus.click();
		waitForPageToLoad();
	}
	
	public void reopenParty() {
		evaluateJavascript("jQuery.noConflict();");
		element(reopenPartyButton).waitUntilVisible();
		reopenPartyButton.click();
	}

	public void selectBonusType(String type) {
		evaluateJavascript("jQuery.noConflict();");
		element(bonusType).waitUntilVisible();
		element(bonusType).selectByVisibleText(type);
	}

	public void typeBonusValue(String value) {
		element(bonusValue).waitUntilVisible();
		element(bonusValue).sendKeys(value);
	}

	public void verifyAddBonusSuccessMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + ContextConstants.BOUNUS_SUCCESS_MESSAGE + " and it's not! Actual: " + successMessage.getText(), successMessage.getText().contains(ContextConstants.BOUNUS_SUCCESS_MESSAGE));
	}
	
	public PartyBackendPerformanceModel getPartyBackendPerformance(){
		PartyBackendPerformanceModel partyModel= new PartyBackendPerformanceModel();
		partyModel.setNoOfOrders(getDriver().findElement(By.cssSelector("#performance tr:nth-child(1) td:nth-child(2)")).getText().trim());
		partyModel.setRetail(getDriver().findElement(By.cssSelector("#performance tr:nth-child(2) td:nth-child(2)")).getText().replace("€", "").replace(",", ".").trim());
		partyModel.setIp(getDriver().findElement(By.cssSelector("#performance tr:nth-child(5) td:nth-child(2)")).getText().trim());
		partyModel.setJewelryBonus(getDriver().findElement(By.cssSelector("#party_hostess_reward tr:nth-child(1) td:nth-child(2)")).getText().replace("€", "").replace(",", ".").trim());
	    partyModel.setFourthyDiscounts(getDriver().findElement(By.cssSelector("#party_hostess_reward tr:nth-child(2) td:nth-child(2)")).getText().trim());
	    
	    PrintUtils.printPartyBackendPerformanceModel(partyModel);
	    return partyModel;
	}
	
	

}

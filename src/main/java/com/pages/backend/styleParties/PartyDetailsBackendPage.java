package com.pages.backend.styleParties;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.stagingaut.ContextConstants;
import com.tools.requirements.AbstractPage;

public class PartyDetailsBackendPage extends AbstractPage {

	@FindBy(css = "#party_details_view li")
	private WebElement optionsList;

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
				System.out.println("bingo");
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

}

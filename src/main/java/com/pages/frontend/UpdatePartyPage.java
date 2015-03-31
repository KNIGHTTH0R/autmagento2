package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class UpdatePartyPage extends AbstractPage {

	@FindBy(id = "date")
	private WebElement dateSelect;

	@FindBy(id = "time")
	private WebElement hourSelectDropDown;

	@FindBy(id = "save-edit-party")
	private WebElement partySubmitButton;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td a:nth-child(1).ui-state-default.ui-state-highlight")
	private WebElement firstAvailableDateButton;

	public void selectFirstAvailableDate() {

		element(dateSelect).waitUntilVisible();
		dateSelect.click();
		element(firstAvailableDateButton).waitUntilVisible();
		firstAvailableDateButton.click();
	}

	public void selectSecondAvailableHour() {

		List<WebElement> hoursList = hourSelectDropDown.findElements(By.xpath("//option[not(@disabled)]"));
		hoursList.get(2).click();

	}

	public void submitParty() {
		element(partySubmitButton).waitUntilVisible();
		partySubmitButton.click();

	}

}

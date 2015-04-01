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

	@FindBy(css = "#ui-datepicker-div div.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all a:nth-child(2)")
	private WebElement nextMonthButton;

	@FindBy(id = "save-edit-party")
	private WebElement partySubmitButton;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td a:nth-child(1).ui-state-default.ui-state-highlight")
	private WebElement firstAvailableDateButton;

	@FindBy(css = "#info-time-elapsed a#move-party")
	private WebElement moveParty;

	public void selectDay() {
		List<WebElement> daysList = getDriver().findElements(By.cssSelector("table.ui-datepicker-calendar tbody tr td a:nth-child(1).ui-state-default"));
		for (WebElement day : daysList) {
			if (day.getText().contentEquals("1")) {
				day.click();
				break;
			}
		}
	}

	public void selectADateGreaterWithMinimum180Days() {

		element(dateSelect).waitUntilVisible();
		dateSelect.click();
		for (int i = 0; i < 7; i++) {
			nextMonthButton.click();
		}
		selectDay();	}

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

	public void confirmMoveParty() {
		element(moveParty).waitUntilVisible();
		moveParty.click();

	}

	public void submitParty() {
		element(partySubmitButton).waitUntilVisible();
		partySubmitButton.click();
	}

}

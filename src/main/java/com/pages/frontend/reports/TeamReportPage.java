package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.requirements.AbstractPage;

public class TeamReportPage extends AbstractPage {

	@FindBy(css = "div#team_report_length select")
	private WebElement reportPagination;

	@FindBy(id = "level")
	private WebElement stylistLevel;

	@FindBy(id = "month")
	private WebElementFacade monthDateForReport;

	@FindBy(css = "#team_report_filter input[type='Search']")
	private WebElement searchInput;

	@FindBy(id = "team_report_next")
	private WebElement reportNextPageButton;

	@FindBy(id = "tab_team")
	private WebElement teamTab;

	@FindBy(id = "tab_party")
	private WebElement partyTab;

	@FindBy(id = "tab_takeoff")
	private WebElement takeOffPhaseTab;

	@FindBy(css = "#team_report tbody")
	private WebElement teamReportTable;

	@FindBy(css = "#team_report_paginate span a:last-child")
	private WebElement pageNumber;

	public void selectNumberOfEntriesOnReport(String numberEntries) {
		element(reportPagination).waitUntilVisible();
		element(reportPagination).selectByVisibleText(numberEntries);
	}

	public void selectScLevel(String levelNumber) {
		element(stylistLevel).waitUntilVisible();
		element(stylistLevel).selectByVisibleText(levelNumber);
	}

	public void selectMonthForReport(String monthDate) {
		monthDateForReport.waitUntilVisible();
		element(monthDateForReport).selectByVisibleText(monthDate);
	}

	public void searchInput(String seachKey) {
		element(searchInput).waitUntilClickable();
		searchInput.sendKeys(seachKey);
	}

	public void clickTeamTab() {
		element(teamTab).waitUntilVisible();
		teamTab.click();
	}

	public void clickStylePartyTab() {
		element(partyTab).waitUntilVisible();
		partyTab.click();
	}

	public void clickTakeOffPhaseTab() {
		element(takeOffPhaseTab).waitUntilVisible();
		takeOffPhaseTab.click();
	}

	public void selectPagination(String number) {
		element(reportPagination).waitUntilVisible();
		reportPagination.sendKeys(number);

	}

	public void selectMonth(String month) {
		element(monthDateForReport).waitUntilVisible();
		monthDateForReport.sendKeys(month);

	}

	public List<String> getStylistName() {
		List<String> result = new ArrayList<String>();
		int currentPage = 1;
		int numberOfPages = Integer.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());
		System.out.println("pageeeeeeee:"+numberOfPages);

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				String stylistName = elementNow.findElement(By.cssSelector("td:nth-child(4) a")).getText();
				result.add(stylistName);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;
			
		} while (currentPage <= numberOfPages);
		System.out.println("stylistiiiiiiiii:"+result.size());
		for (String item : result) {
			System.out.println(item);
		}
		return result;
	}
}

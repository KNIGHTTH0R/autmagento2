package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.TeamReportPartyTabModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

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

	public List<TeamReportTeamTabModel> getTeamReportTeamModel() {

		List<TeamReportTeamTabModel> result = new ArrayList<TeamReportTeamTabModel>();

		int currentPage = 1;
		int numberOfPages = Integer.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				TeamReportTeamTabModel teamReportModel = new TeamReportTeamTabModel();
				teamReportModel.setStyleCoachName(elementNow.findElement(By.cssSelector("td:nth-child(4) a")).getText());
				teamReportModel.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
				teamReportModel.setActivationDate(elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText());
				teamReportModel.setIp(elementNow.findElement(By.cssSelector("td:nth-child(7)")).getText());
				teamReportModel.setTqv(elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText());
				teamReportModel.setCarrerLevelThisMonth(elementNow.findElement(By.cssSelector("td:nth-child(9)")).getText());
				teamReportModel.setCarrerLevelLastMonth(elementNow.findElement(By.cssSelector("td:nth-child(10)")).getText());
				teamReportModel.setPayLevel(elementNow.findElement(By.cssSelector("td:nth-child(11)")).getText());
				teamReportModel.setIpNewRecruited(elementNow.findElement(By.cssSelector("td:nth-child(12)")).getText());
				teamReportModel.setVacationMonth(elementNow.findElement(By.cssSelector("td:nth-child(13)")).getText());
				teamReportModel.setNewStylist(elementNow.findElement(By.cssSelector("td:nth-child(14)")).getText());
				teamReportModel.setQuitDate(elementNow.findElement(By.cssSelector("td:nth-child(15)")).getText());
		
				result.add(teamReportModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
		PrintUtils.printTeamReportTeamTabModel(result);
		return result;
	}
	
	public List<TeamReportPartyTabModel> getTeamReportPartyModel() {

		List<TeamReportPartyTabModel> result = new ArrayList<TeamReportPartyTabModel>();

		int currentPage = 1;
		int numberOfPages = Integer.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				TeamReportPartyTabModel teamReportPartyModel = new TeamReportPartyTabModel();
				teamReportPartyModel.setStylistName(elementNow.findElement(By.cssSelector("td:nth-child(4) a")).getText());
				teamReportPartyModel.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
				teamReportPartyModel.setIpThisMonth(elementNow.findElement(By.cssSelector("td:nth-child(16)")).getText());
				teamReportPartyModel.setIpLastMonth(elementNow.findElement(By.cssSelector("td:nth-child(17)")).getText());
				teamReportPartyModel.setPartiesHeld(elementNow.findElement(By.cssSelector("td:nth-child(18)")).getText());
				teamReportPartyModel.setPartiesPlanned(elementNow.findElement(By.cssSelector("td:nth-child(19)")).getText());
				teamReportPartyModel.setPartiesUpcoming(elementNow.findElement(By.cssSelector("td:nth-child(20)")).getText());
				teamReportPartyModel.setIpPerParty(elementNow.findElement(By.cssSelector("td:nth-child(21)")).getText());
			
		
				result.add(teamReportPartyModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
		PrintUtils.printTeamReportPartyTabModel(result);
		return result;
	}
	
	public List<TeamReportTakeOffPhaseModel> getTeamReportTakeOffPhaseModel() {

		List<TeamReportTakeOffPhaseModel> result = new ArrayList<TeamReportTakeOffPhaseModel>();

		int currentPage = 1;
		int numberOfPages = Integer.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				TeamReportTakeOffPhaseModel teamReportTakeOffPhaseModel = new TeamReportTakeOffPhaseModel();
				teamReportTakeOffPhaseModel.setStylistName(elementNow.findElement(By.cssSelector("td:nth-child(4) a")).getText());
				teamReportTakeOffPhaseModel.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
				teamReportTakeOffPhaseModel.setActivationDate(elementNow.findElement(By.cssSelector("td:nth-child(22)")).getText());
				teamReportTakeOffPhaseModel.setTakeOffPhaseEndDate(elementNow.findElement(By.cssSelector("td:nth-child(23)")).getText());
				teamReportTakeOffPhaseModel.setDaysLeft(elementNow.findElement(By.cssSelector("td:nth-child(24)")).getText());
				teamReportTakeOffPhaseModel.setIp(elementNow.findElement(By.cssSelector("td:nth-child(25)")).getText());
				teamReportTakeOffPhaseModel.setNumberOfFrontliners(elementNow.findElement(By.cssSelector("td:nth-child(26)")).getText());
			
		
				result.add(teamReportTakeOffPhaseModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
		PrintUtils.printTeamReportTakeOffPhaseTabModel(result);
		return result;
	}

}

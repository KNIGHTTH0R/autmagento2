package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.TeamReportPartyTabModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.data.TeamReportTotalsModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

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

	@FindBy(css = "a[class*='paginate_button'][data-dt-idx='1']")
	private WebElement firstPageButton;

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
		element(stylistLevel).selectByValue(levelNumber);
	}

	public void selectMonthForReport(String monthDate) {
		monthDateForReport.waitUntilVisible();
		element(monthDateForReport).selectByVisibleText(monthDate);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
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
		int numberOfPages = Integer
				.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());
		// the pagination when changing between tabs remains where it was
		// before,so we always want to start to grab data fron the first page
		firstPageButton.click();

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				TeamReportTeamTabModel teamReportModel = new TeamReportTeamTabModel();
				WebElement nameContainer = elementNow.findElement(By.cssSelector("td:nth-child(1) a"));

				teamReportModel.setStyleCoachId(
						StringUtils.substringBetween(nameContainer.getAttribute("href"), "userId/", "/userType"));
				teamReportModel.setStyleCoachName(nameContainer.getText());
				teamReportModel.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText());
				teamReportModel.setActivationDate(elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
				teamReportModel.setIp(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
				teamReportModel.setTqv(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
				/*teamReportModel.setCarrerLevelThisMonth(TeamReportCalculations.getFullNameOfAbbreviation(
						elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText()));
				teamReportModel.setCarrerLevelLastMonth(TeamReportCalculations.getFullNameOfAbbreviation(
						elementNow.findElement(By.cssSelector("td:nth-child(7)")).getText()));
				teamReportModel.setPayLevel(TeamReportCalculations.getFullNameOfAbbreviation(
						elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText()));*/
				teamReportModel.setCarrerLevelThisMonth(
						elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText());
				teamReportModel.setCarrerLevelLastMonth
						(elementNow.findElement(By.cssSelector("td:nth-child(7)")).getText());
				teamReportModel.setPayLevel(
						elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText());
				teamReportModel.setIpNewRecruited(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(9)")).getText()));
				teamReportModel.setVacationMonth(elementNow.findElement(By.cssSelector("td:nth-child(10)")).getText());
				teamReportModel.setNewStylist(elementNow.findElement(By.cssSelector("td:nth-child(11)")).getText());
				teamReportModel.setQuitDate(elementNow.findElement(By.cssSelector("td:nth-child(12)")).getText());

				result.add(teamReportModel);
		//		System.out.println(teamReportModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
	//	PrintUtils.printTeamReportTeamTabModel(result);
		return result;
	}

	public List<TeamReportPartyTabModel> getTeamReportPartyModel() {

		List<TeamReportPartyTabModel> result = new ArrayList<TeamReportPartyTabModel>();

		int currentPage = 1;
		int numberOfPages = Integer
				.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());

		// the pagination when changing between tabs remains where it was
		// before,so we always want to start to grab data fron the first page
		firstPageButton.click();

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				TeamReportPartyTabModel teamReportPartyModel = new TeamReportPartyTabModel();
				WebElement nameContainer = elementNow.findElement(By.cssSelector("td:nth-child(1) a"));

				teamReportPartyModel.setStyleCoachId(
						StringUtils.substringBetween(nameContainer.getAttribute("href"), "userId/", "/userType"));
				teamReportPartyModel.setStylistName(nameContainer.getText());

				teamReportPartyModel
						.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText());
				teamReportPartyModel.setIpThisMonth(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText()));
				teamReportPartyModel.setIpLastMonth(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
				teamReportPartyModel
						.setPartiesHeld(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
				teamReportPartyModel
						.setPartiesPlanned(elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText());
				teamReportPartyModel
						.setPartiesUpcoming(elementNow.findElement(By.cssSelector("td:nth-child(7)")).getText());
				teamReportPartyModel.setRevenuePerParty(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText()));

				result.add(teamReportPartyModel);
		//		System.out.println(teamReportPartyModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
	//	PrintUtils.printTeamReportPartyTabModel(result);
		return result;
	}

	public List<TeamReportTakeOffPhaseModel> getTeamReportTakeOffPhaseModel() {

		List<TeamReportTakeOffPhaseModel> result = new ArrayList<TeamReportTakeOffPhaseModel>();

		int currentPage = 1;
		int numberOfPages = Integer
				.valueOf(getDriver().findElement(By.cssSelector("#team_report_paginate span a:last-child")).getText());

		// the pagination when changing between tabs remains where it was
		// before,so we always want to start to grab data fron the first page
		firstPageButton.click();

		do {
			List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

			waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

			for (WebElement elementNow : listEntries) {

				WebElement nameContainer = elementNow.findElement(By.cssSelector("td:nth-child(1) a"));

				TeamReportTakeOffPhaseModel teamReportTakeOffPhaseModel = new TeamReportTakeOffPhaseModel();

				teamReportTakeOffPhaseModel.setStyleCoachId(
						StringUtils.substringBetween(nameContainer.getAttribute("href"), "userId/", "/userType"));
				teamReportTakeOffPhaseModel.setStylistName(nameContainer.getText());
				teamReportTakeOffPhaseModel
						.setSponsorName(elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText());
				teamReportTakeOffPhaseModel
						.setActivationDate(elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
				teamReportTakeOffPhaseModel
						.setTakeOffPhaseEndDate(elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
				teamReportTakeOffPhaseModel
						.setDaysLeft(elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
				teamReportTakeOffPhaseModel.setIp(FormatterUtils
						.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText()));
				teamReportTakeOffPhaseModel
				.setIp30(FormatterUtils.parseValueToZeroDecimals(elementNow.findElement(By.cssSelector("td:nth-child(7)")).getText()));
				teamReportTakeOffPhaseModel
						.setNewStylistTop(elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText());

				result.add(teamReportTakeOffPhaseModel);
			}
			reportNextPageButton.click();
			waitABit(1000);
			currentPage++;

		} while (currentPage <= numberOfPages);
	//	PrintUtils.printTeamReportTakeOffPhaseTabModel(result);
		return result;
	}

	public TeamReportTotalsModel getTeamReportTeamTotals(TeamReportTotalsModel totals) {

		clickTeamTab();
		WebElement teamTabTotalContainer = getDriver().findElement(By.cssSelector("div.dataTables_scrollFoot"));

		totals.setIpTotal(FormatterUtils
				.parseValueToZeroDecimals(teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ip.tab_team")).getText()));
		totals.setTqvTotal(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_team_points.tab_team")).getText());
		totals.setIpNewInclTotal(FormatterUtils
				.parseValueToZeroDecimals(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ip_incl_new_sc.tab_team")).getText()));
		totals.setNewScTotal(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_no_new_sc.tab_team")).getText());

		return totals;
	}

	public TeamReportTotalsModel getTeamReportPartyTotals(TeamReportTotalsModel totals) {

		clickStylePartyTab();
		WebElement teamTabTotalContainer = getDriver().findElement(By.cssSelector("div.dataTables_scrollFoot"));

		totals.setIpThisMonthTotal(FormatterUtils
				.parseValueToZeroDecimals(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ip_month.tab_party")).getText()));
		totals.setIpLastMonthTotal(FormatterUtils
				.parseValueToZeroDecimals(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ip_last_month.tab_party")).getText()));
		totals.setPartiesHeldTotal(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_held_parties.tab_party")).getText());
		totals.setPartiesPlannedTotal(teamTabTotalContainer
				.findElement(By.cssSelector("th.table_total_planned_parties.tab_party")).getText());
		totals.setPartiesUpcomingTotal(teamTabTotalContainer
				.findElement(By.cssSelector("th.table_total_upcoming_parties.tab_party")).getText());
		totals.setRevenuePartyTotal(
				teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ip_parties.tab_party")).getText());

		return totals;

	}

	public TeamReportTotalsModel getTeamReportTakeOffTotals(TeamReportTotalsModel totals) {

		clickTakeOffPhaseTab();
		WebElement teamTabTotalContainer = getDriver().findElement(By.cssSelector("div.dataTables_scrollFoot"));

		totals.setDaysLeftTotal(teamTabTotalContainer
				.findElement(By.cssSelector("th.table_total_remaining_top.tab_takeoff")).getText());
		totals.setIpTakeOffTotal(FormatterUtils
				.parseValueToZeroDecimals(teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ips_in_top.tab_takeoff")).getText()));
		totals.setIp30TakeOffTotal(FormatterUtils
				.parseValueToZeroDecimals(teamTabTotalContainer.findElement(By.cssSelector("th.table_total_ips_in_first_month_top.tab_takeoff")).getText()));
		totals.setNewScTakeOffTotal(teamTabTotalContainer
				.findElement(By.cssSelector("th.table_total_top_new_frontliners.tab_takeoff")).getText());

		return totals;
	}
}

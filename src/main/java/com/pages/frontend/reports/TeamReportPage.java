package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.backend.OrderItemModel;
import com.tools.requirements.AbstractPage;

public class TeamReportPage extends AbstractPage {

	@FindBy(css = "div#team_report_length select")
	private WebElement reportPagination;
	
	@FindBy(id = "level")
	private WebElement stylistLevel;
	
	@FindBy(id = "month")
	private WebElement monthDateForReport;
	
	@FindBy(id = "team_report_filter")
	private WebElement searchInput;
	
	@FindBy(id = "team")
	private WebElement teamTab;
	
	@FindBy(id = "party")
	private WebElement partyTab;
	
	@FindBy(id = "takeoff")
	private WebElement takeOffPhaseTab;
	
	@FindBy(id = "#team_report tbody")
	private WebElement teamReportTable;
	
	
	public void selectNumberOfEntriesOnReport(String numberEntries) {
		element(reportPagination).waitUntilVisible();
		element(reportPagination).selectByVisibleText(numberEntries);
	}
	
	public void selectScLevel(String levelNumber) {
		element(stylistLevel).waitUntilVisible();
		element(stylistLevel).selectByVisibleText(levelNumber);
	}
	
	public void selectMonthForReport(String monthDate) {
		element(monthDateForReport).waitUntilVisible();
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
	
	public List<String> stylistName() {
		List<String> result = new ArrayList<String>();
//		get list size;
		
//		foreach page
		
		List<WebElement> listEntries = getDriver().findElements(By.cssSelector("#team_report > tbody > tr"));

		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

		for (WebElement elementNow : listEntries) {

			String stylistName = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
			result.add(stylistName);
		}
		return result;
	}
}

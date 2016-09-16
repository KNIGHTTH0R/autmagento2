package com.pages.frontend.reports;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class TeamReportPage extends AbstractPage {

	@FindBy(css = "div#team_report_length select")
	private WebElement reportPagination;
	
	@FindBy(id = "level")
	private WebElement stylistLevel;
	
	@FindBy(id = "team_report_filter")
	private WebElement searchStylist;
	
	@FindBy(id = "team")
	private WebElement teamTab;
	
	@FindBy(id = "party")
	private WebElement partyTab;
	
	@FindBy(id = "takeoff")
	private WebElement takeOffPhaseTab;
	
	@FindBy(id = "#team_report tbody")
	private WebElement teamReportTable;
	
}

package com.steps.frontend.reports;

import net.thucydides.core.annotations.Step;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractSteps;

public class TeamReportSteps extends AbstractSteps{
	
	private static final long serialVersionUID = 1L;

	
	@Step
	public void searchInput(String searchKey) {
		teamReportPage().searchInput(searchKey);
		
	}
	
	@Step
	public void clickTeamTab() {
		teamReportPage().clickTeamTab();;
		
	}
	
	@Step
	public void clickStylePartyTab() {
		teamReportPage().clickStylePartyTab();;
		
	}
	
	@Step
	public void clickTakeOffPhaseTab() {
		teamReportPage().clickTakeOffPhaseTab();;
		
	}
	
	@Step
	public void selectPagination(String select) {
		teamReportPage().selectPagination(select);
	}
	
	@Step
	public void selectMonth(String month) {
		teamReportPage().selectMonthForReport(month);
	}
}

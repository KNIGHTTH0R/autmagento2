package com.steps.external.navision;

import net.thucydides.core.annotations.Step;

import com.connectors.http.ImportInterfaceCalls;
import com.pages.external.navision.NavisionHomePage;
import com.tools.requirements.AbstractSteps;

public class NavisionHomeSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickSearchButton() {
		navisionHomePage().clickSearchButton();
	}
	
	@Step
	public void inputSearchTerm(String input) {
		navisionSearchPage().inputSearchTerm(input);
	}
	
	@Step
	public void clickOnMenu(String menu) {
		navisionSearchPage().clickOnMenu(menu);
	}
	
	@Step
	public void clickPJOrderList(String tableName) {
		navisionSyncDashboardPage().clickPJOrderList(tableName);
	}
	
	@Step
	public void clickLinesLink() {
		navisionSyncDashboardPage().clickLinesLink();
	}
	
	@Step
	public void clickFilterLink() {
		navisionSyncDashboardPage().clickFilterLink();;
	}
	
	

}

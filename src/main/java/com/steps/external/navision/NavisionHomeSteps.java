package com.steps.external.navision;

import net.thucydides.core.annotations.Step;

import java.util.List;

import com.connectors.http.ImportInterfaceCalls;
import com.pages.external.navision.NavisionHomePage;
import com.tools.persistance.MongoReader;
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
	
	
	@Step
	public void performLoginIntoNavisonWebClient() throws Exception{
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=43%3bh%2fqqAAJ7%2f1AAUABKAF8ATQBJAFgARQBEAF8ATQBBAE4AVQBBAEw%3d&mode=Edit&page=11205257&i=3AF&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
	}

	@Step
	public void performOrderImport(String orderId) {
		navisionSyncDashboardPage().clickOnSalesOrderListLine();
	//	navisionSyncDashboardPage().clickOnLines();

		navisionSyncDashboardPage().clickOnLinesDropdown();
		navisionSyncDashboardPage().clickOnFilter();
		navisionSyncDashboardPage().inputOrderId(orderId);
		navisionSyncDashboardPage().closeDialogModal();
		navisionSyncDashboardPage().selectActionsTab();
		navisionSyncDashboardPage().performOrderImport();
		navisionSyncDashboardPage().selectActionsTab();
	}

	@Step
	public void syncEarliestAvDateOnItem(String skuItem) throws Exception {
			navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
			navisionSyncDashboardPage().insertAuthentificationCredentials();
			navisionSyncDashboardPage().searchForItem(skuItem);
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnItemJournalMenuBtn();
			navisionSyncDashboardPage().insertValuesForItem(skuItem,"","1100","QS","100");
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnPostMenuBtn();
			navisionSyncDashboardPage().confirmPostJournalLines();
			navisionSyncDashboardPage().confirmSuccesfullyPostedJournalLines();
		//	navisionSyncDashboardPage().clickOnPostMenuBtn();
		}
		
	
	@Step
	public void syncQtyOnItem(String skuItem,String variantCode) throws Exception {
			navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
			navisionSyncDashboardPage().insertAuthentificationCredentials();
			navisionSyncDashboardPage().searchForItem(skuItem);
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnItemJournalMenuBtn();
			navisionSyncDashboardPage().insertValuesForItem(skuItem,variantCode,"1100","1000","500");
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnPostMenuBtn();
			navisionSyncDashboardPage().confirmPostJournalLines();
			navisionSyncDashboardPage().confirmSuccesfullyPostedJournalLines();
		//	navisionSyncDashboardPage().clickOnPostMenuBtn();
		}
	
	
	@Step
	public void checkTermPurchasecheckbox(String skuItem) throws Exception {
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
		navisionSyncDashboardPage().searchForItem(skuItem);
		navisionSyncDashboardPage().clickOnEditPoints();
		navisionSyncDashboardPage().clickOnEditLink();
		navisionSyncDashboardPage().clickOnTermPurchaseCheckbox();
		navisionSyncDashboardPage().closeWindow();
		
	}
	
	}
	
	



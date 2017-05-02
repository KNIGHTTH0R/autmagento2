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
	public void accessNavisonWebClientItemList() throws Exception{
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
	}

	@Step
	public void syncEarliestAvDateOnItem(String skuItem,String variantCode,String qty) throws Exception {
			navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
			navisionSyncDashboardPage().searchForItem(skuItem);
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnItemJournalMenuBtn();
			//navisionSyncDashboardPage().insertValuesForItem(skuItem,variantCode,"1100","QS",qty);
			navisionSyncDashboardPage().insertItemNo(skuItem);
			navisionSyncDashboardPage().insertItemVariantCode(variantCode);
			navisionSyncDashboardPage().insertKostenstelleCode("1100");
			navisionSyncDashboardPage().insertLocationCode("QS");
			navisionSyncDashboardPage().insertQty(qty);
			
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnPostMenuBtn();
			navisionSyncDashboardPage().confirmPostJournalLines();
			navisionSyncDashboardPage().confirmSuccesfullyPostedJournalLines();
			navisionSyncDashboardPage().closeWindow();
			waitABit(3000);
		//	navisionSyncDashboardPage().clickOnPostMenuBtn();
		//	navisionSyncDashboardPage().closeWindow();
		}
		
	
	@Step
	public void syncQtyOnItem(String skuItem,String variantCode,String qty) throws Exception {
			navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
			navisionSyncDashboardPage().searchForItem(skuItem);
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnItemJournalMenuBtn();
			System.out.println("Variant code "+ variantCode);
			//navisionSyncDashboardPage().insertValuesForItem(skuItem,variantCode,"1100","1000",qty);
			navisionSyncDashboardPage().insertItemNo(skuItem);
			navisionSyncDashboardPage().insertItemVariantCode(variantCode);
			navisionSyncDashboardPage().insertKostenstelleCode("1100");
			navisionSyncDashboardPage().insertLocationCode("1000");
			navisionSyncDashboardPage().insertQty(qty);
			
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnPostMenuBtn();
			navisionSyncDashboardPage().confirmPostJournalLines();
			navisionSyncDashboardPage().confirmSuccesfullyPostedJournalLines();
			navisionSyncDashboardPage().closeWindow();
			waitABit(3000);
		}
	
	
	@Step
	public void checkTermPurchasecheckbox(String skuItem) throws Exception {
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=11%3bGwAAAAJ7&mode=View&page=31&i=7A03&IsDlg=1");
		navisionSyncDashboardPage().searchForItem(skuItem);
		navisionSyncDashboardPage().clickOnEditPoints();
		navisionSyncDashboardPage().clickOnEditLink();
		navisionSyncDashboardPage().clickOnTermPurchaseCheckbox();
		navisionSyncDashboardPage().closeWindow();
		
	}

	@Step
	public void loginToTransferQuantityPage() throws Exception{
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=19%3bbBYAAAJ7BDkAMwA0ADI%3d&mode=View&page=5742&i=19B0&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
	}
	
	@Step
	public void transferQtyFromQSin1000(String sku,String variantCode,String qty) throws Exception {
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=19%3bbBYAAAJ7BDkAMwA0ADI%3d&mode=View&page=5742&i=19B0&IsDlg=1");
		navisionSyncDashboardPage().clickOnNewEntry();
		navisionSyncDashboardPage().completeTransferDetails(sku,variantCode,qty,"QS","1000","TRANS","1100");
		navisionSyncDashboardPage().insertTranferQty(qty);
		navisionSyncDashboardPage().selectHomeTab();
		navisionSyncDashboardPage().clickOnPostMenuBtn();
		navisionSyncDashboardPage().confirmPostedTransferOrders();
		navisionSyncDashboardPage().clickOnPostMenuBtn();
		navisionSyncDashboardPage().confirmPostedTransferOrders2();
		navisionSyncDashboardPage().confirmPostedTransferOrders2();
		navisionSyncDashboardPage().closeWindow();
//		navisionSyncDashboardPage().clickOnPost();
//		navisionSyncDashboardPage().clickOnPost();
		
	}
	
	@Step
	public void waitAfterTransfer(){
		System.out.println("Sunt aici");
		waitABit(40000);
		System.out.println("am asteptat aici");
	}
	
	}
	
	



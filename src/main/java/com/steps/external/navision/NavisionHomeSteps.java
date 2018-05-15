package com.steps.external.navision;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

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
	
	
	
	public void performLoginIntoNavisonWebClient() throws Exception {
		//navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=43%3bh%2fqqAAJ7%2f1AAUABKAF8ATQBJAFgARQBEAF8ATQBBAE4AVQBBAEw%3d&mode=Edit&page=11205257&i=3AF&IsDlg=1");
		getDriver().manage().window().maximize();

		getDriver().get("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=43%3bh%2fqqAAJ7%2f1AAUABKAF8ATQBJAFgARQBEAF8ATQBBAE4AVQBBAEw%3d&mode=Edit&page=11205257&i=3AF&IsDlg=1");
		
		navisionSyncDashboardPage().insertAuthentificationCredentials();
	}

/*	@Step
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
	}*/
	
	
	@Step
	public void performOrderImport(String orderId) {
		navisionSyncDashboardPage().clickOnSalesOrderListLine();
		navisionSyncDashboardPage().clickOnLinesDropdown();
		navisionSyncDashboardPage().clickOnFilter();
		navisionSyncDashboardPage().inputOrderId(orderId);
		navisionSyncDashboardPage().closeDialogModal();
		
		navisionSyncDashboardPage().clickOnSalesOrderInfoRetriveLine();
		navisionSyncDashboardPage().clickOnLinesDropdown();
		navisionSyncDashboardPage().clickOnFilter();
		navisionSyncDashboardPage().inputOrderIdSalesInfo(orderId);
		navisionSyncDashboardPage().closeDialogModal();
		
		navisionSyncDashboardPage().selectActionsTab();
		navisionSyncDashboardPage().performOrderImport();
		navisionSyncDashboardPage().selectActionsTab();
		waitABit(10000);
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
	public void postOrder(String incrementId) throws Exception {
			navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=39%3bJAAAAACLAQAAAAJ7CzEAMAAwADMAMwAzADgAMQAyADAAMA%3d%3d&node=0000232e-0000-0002-0008-0000836bd2d2&mode=View&page=9305&i=CAC&ni=4");
			navisionSyncDashboardPage().searchForItem(incrementId);
			navisionSyncDashboardPage().clickOnEditPoints();
			navisionSyncDashboardPage().clickOnEditLink();
			navisionSyncDashboardPage().selectHomeTab();
			navisionSyncDashboardPage().clickOnOrderMenuBtn("Post");
			navisionSyncDashboardPage().confirmPostOrder();
		//	navisionSyncDashboardPage().closeWindow();
		}
	
	
	
	
	

	@Step
	public void createSalesOrderReturn(String orderId, String postedOrderNo) throws Exception {
	
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&mode=Create&page=42&i=176E1&IsDlg=1");
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&mode=Create&page=42&i=176E1&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
		navisionSyncDashboardPage().selectHomeTab();
		navisionSyncDashboardPage().clickOnCopyDocument();
		navisionSyncDashboardPage().insertDocNo(postedOrderNo);
	//	navisionSyncDashboardPage().selectHomeTab();
	//	refresh();
		navisionSyncDashboardPage().removeChargeItem(2);
		navisionSyncDashboardPage().selectHomeTab();
		navisionSyncDashboardPage().clickOnOrderMenuBtn("Post");
		navisionSyncDashboardPage().confirmPostOrder();
		
		
		
		
	}
	
	public void grabOrderNoFromPostedSalesInvoice() throws Exception{
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&mode=Create&page=42&i=176E1&IsDlg=1");
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&mode=Create&page=42&i=176E1&IsDlg=1");
		navisionHomePage().clickSearchButton();
		navisionSearchPage().inputSearchTerm("Posted Sales Invoices");
		navisionSearchPage().clickOnMenu("Posted Sales Invoices");
	}
	
	public void replicateOrder(String orderNo) throws Exception{
	
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=23%3bcAAAAAJ7BjEAMAAzADAAMAAy&mode=View&page=143&i=5B6B&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
		navisionSyncDashboardPage().searchForItem(orderNo);
		navisionSyncDashboardPage().clickOnTheLastRow();
		navisionSyncDashboardPage().selectActionsTab();
		navisionSyncDashboardPage().clickOnNewMenuBtn("Replicate");
	
	}
	
	public String blanketMakeOrder(String orderNo) throws Exception{
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=25%3bJAAAAACLBAAAAAJ7BDEAMAAwADY%3d&node=0000232e-0000-0031-0008-0000836bd2d2&mode=View&page=9303&i=5E44&ni=5");
		navisionSyncDashboardPage().searchForItem(orderNo);
		navisionSyncDashboardPage().clickOnEditPoints();
		navisionSyncDashboardPage().clickOnEditLink();
		navisionSyncDashboardPage().selectHomeTab();
		navisionSyncDashboardPage().clickOnOrderMenuBtn("Make");
		navisionSyncDashboardPage().confirmPostJournalLines();
		String order=navisionSyncDashboardPage().grabOrderNoFromModal();
		navisionSyncDashboardPage().confirmPostOrder();
		System.out.println(order);
		return order;
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
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=21%3bbBYAAAJ7BTEAMwAzADUANQ%3d%3d&mode=View&page=5742&i=3D23&IsDlg=1");
		navisionSyncDashboardPage().insertAuthentificationCredentials();
	}
	
	@Step
	public void transferQtyFromQSin1000(String sku,String variantCode,String qty) throws Exception {
	//	navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=19%3bbBYAAAJ7BDkAMwA0ADI%3d&mode=View&page=5742&i=19B0&IsDlg=1");
		navigate("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=21%3bbBYAAAJ7BTEAMwAzADUANQ%3d%3d&mode=View&page=5742&i=3D23&IsDlg=1");
		navisionSyncDashboardPage().clickOnNewEntry();
		navisionSyncDashboardPage().completeTransferDetails(sku,variantCode,qty,"QS","1000","TRANS","1100");
		System.out.println("da e totul inserat cu succes");
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

	public void open() {
		
		getDriver().get("http://Tinxit:NAV-MAG12#$@185.48.116.231:8080/DynamicsNAV90/WebClient/");
		getDriver().get("http://185.48.116.231:8080/DynamicsNAV90/WebClient/");
		
		 WebDriverWait wait =new WebDriverWait(this.getDriver(), 30);
		  String url="http://185.48.116.231:8080/DynamicsNAV90/WebClient/";
		  try {
		      this.getDriver().get(urlWithAccount(url));
		    }
		    catch (Exception ex) {
		      throw new RuntimeException("home page cannot be opened");
		    }

		    try {
		      wait.until(ExpectedConditions.urlContains(url));
		    }
		    catch (Exception ex) {
		      throw new RuntimeException("home page didnt load correctly - " + 
		                                 "expected url = " + url + 
		                                 " - " + 
		                                 "actual url = " + this.getDriver().getCurrentUrl());
		    }
		    
		    
		    
	}
	
	private String urlWithAccount(String url) {
	     String username = System.getProperty("Tinxit");
	     String password = System.getProperty("NAV-MAG12#$");

	     String newUrl = url.replace("http://", 
	                                 "http://" + 
	                                 username + 
	                                 ":" + 
	                                 password + 
	                                 "@");

	     return newUrl;
	  }

	
	}
	
	



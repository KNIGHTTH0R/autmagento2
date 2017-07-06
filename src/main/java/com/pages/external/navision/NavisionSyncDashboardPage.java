package com.pages.external.navision;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.connectors.navisionLogin.LoginWindow;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class NavisionSyncDashboardPage extends AbstractPage {

	@FindBy(css = ".ms-nav-band-caption a[title='Lines']")
	private WebElement linesOption;

	@FindBy(css = ".ms-nav-ctxmenu-item a[title='Filters']")
	private WebElement filtersOption;

	@FindBy(css = "table[summary*='MAGE Job Lines']  tr:nth-child(3) td:nth-child(4) a")
	private WebElement salesOrderListLine;

	@FindBy(css = "table[summary*='MAGE Job Inbound Line Filter'] tr:nth-child(1) td:nth-child(7)")
	private WebElement inputOrderId;

	@FindBy(css = "a.ms-nav-group-caption[title*=Lines]")
	private WebElement linesDropDown;

	@FindBy(css = ".ms-nav-ctxmenu-itemlist li a[title*=Filters]")
	private WebElement filter;

	@FindBy(css = ".task-dialog.form-no-factboxes .dialog-close")
	private WebElement dialogModal;

	@FindBy(css = "a.ms-cui-tt-a[title*='Actions'] span:not([tabindex])")
	private WebElement actionsTab;

	@FindBy(css = "a.ms-cui-tt-a[title*='Home'] span:not([tabindex])")
	private WebElement homeTab;

	// @FindBy(css = ".ms-cui-img-cont-float img[src*='Action_Post']")
	@FindBy(css = ".ms-cui-tabBody li a:nth-child(3) span")
	private WebElement processButton;

	@FindBy(css = ".icon-Magnifier")
	private WebElement searchItemBtn;

	@FindBy(css = "input[class*='stringcontrol-edit']")
	private WebElement inputItemField;

	@FindBy(css = ".ms-cui-tabContainer li:nth-child(3) a:nth-child(3)")
	private WebElement itemJurnalBtn;

	@FindBy(css = "table[summary*='Item Journal'] tbody  tr:nth-child(1) td:nth-child(7)")
	private WebElement insertItemNo;

	@FindBy(css = "table[summary*='Item Journal'] tbody  tr:nth-child(1) td:nth-child(8) input[type*='text']")
	private WebElement insertItemVariantCode;

	@FindBy(css = "table[summary*='Item Journal'] tbody  tr:nth-child(1) td:nth-child(10)")
	private WebElement insertKostenstelleCode;

	@FindBy(css = "table[summary*='Item Journal'] tbody  tr:nth-child(1) td:nth-child(11) input[type*='text']")
	private WebElement insertLocationCode;

	@FindBy(css = "table[summary*='Item Journal'] tbody  tr:nth-child(1) td:nth-child(12)")
	private WebElement insertQty;

	@FindBy(css = "input[value*='Yes']")
	private WebElement confirmPostJournalLines;
	
	@FindBy(id = "createNewEntityLink")
	private WebElement clickOnNewItem;

	@FindBy(css = ".dialog-title p")
	private WebElement dialogTitle;

	@FindBy(css = "input[value*='OK']")
	private WebElement confirmSuccesfullyPosted;

	@FindBy(css = ".ms-cui-tabBody li:nth-child(2) .ms-cui-row-onerow a:nth-child(1)")
	private WebElement postButton;

	@FindBy(css = ".ms-list-itemLink-td")
	private WebElement clickOnEditPoints;

	@FindBy(css = ".ms-nav-ctxmenu-item [title*='Edit']")
	private WebElement clickOnEditLink;

	@FindBy(css = ".ms-nav-band.hide-additional-fields.expanded .collapsibleTab div:nth-child(26) input")
	private WebElement clickOnTermPurchaseCheckbox;

	@FindBy(css = ".icon-Dismiss.dialog-close")
	private WebElement closeModalWindow;
	
	
	@FindBy(css = "table[summary*='Lookupform'] tbody tr:nth-child(1) td:nth-child(2) a ")
	private WebElement selectElementInModal;
	
	
	
	@FindBy(css = ".ms-nav-band.expanded .collapsibleTab .multiple-columns-group div")
	private List<WebElement> transferFrom;
	
		
	@FindBy(css = "table[summary*='Transfer '] tbody tr:nth-child(1) td:nth-child(3) input[type*='text']")
	private WebElement inserItemNo;		
	
	@FindBy(css = "table[summary*='Transfer '] tbody tr:nth-child(1) td:nth-child(7) input[type*='text']")
	private WebElement insertQuantity;		
	
	@FindBy(css = "table[summary*='Transfer '] tbody tr:nth-child(1) td:nth-child(5) input[type*='text']")
	private WebElement inserItemVariantNo;		
	
	
	@FindBy(css = ".pagetitle-control")
	private WebElement titlePage;	
	
	
	
	public void clickLinesLink() {
		element(linesOption).waitUntilVisible();
		linesOption.click();
	}

	public void clickFilterLink() {
		element(filtersOption).waitUntilVisible();
		filtersOption.click();
	}

	public void clickPJOrderList(String tableName) {
		List<WebElement> tableList = getDriver().findElements(By.cssSelector(".ms-nav-scrollable tbody tr"));
		boolean found = false;
		for (WebElement tableItem : tableList) {
			if (tableItem.findElement(By.cssSelector("td:nth-child(1)")).getText().contentEquals(tableName)) {
				found = true;
				tableItem.click();

			}
		}
		Assert.assertTrue("The table was not found", found);
	}

	public void insertAuthentificationCredentials() throws Exception {

		LoginWindow loginthread = new LoginWindow();
		loginthread.login();

	}

	public void clickOnSalesOrderListLine() {
		waitABit(2000);
		element(salesOrderListLine).waitUntilVisible();
		salesOrderListLine.click();
		waitABit(2000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(salesOrderListLine).click().perform();
	}

	public void clickOnLinesDropdown() {

		waitABit(2000);
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(linesDropDown).click().perform();
		
	}

	public void clickOnFilter() {
		waitABit(2000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(filter).click().perform();

	}

	public void inputOrderId(String orderId) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(inputOrderId).click().perform();
		actions.moveToElement(inputOrderId).sendKeys(orderId).perform();
	}

	public void performOrderImport() {
		waitABit(2000);
		element(processButton).waitUntilVisible();
		Actions builder = new Actions(getDriver());
		builder.moveToElement(processButton).click(processButton);
		builder.perform();
	}

	public void closeDialogModal() {

		element(dialogModal).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(dialogModal).click().perform();

	}

	public void selectActionsTab() {
		element(actionsTab).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(actionsTab).click().perform();
	}

	public void searchForItem(String skuItem) {
		element(searchItemBtn).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(searchItemBtn).click().perform();

		waitABit(2000);
		element(inputItemField).waitUntilVisible();
		actions.moveToElement(inputItemField).click().perform();
		actions.moveToElement(inputItemField).sendKeys(skuItem).perform();

	}

	public void clickOnItemJournalMenuBtn() {

		element(itemJurnalBtn).waitUntilVisible();
		Actions builder = new Actions(getDriver());
		builder.moveToElement(itemJurnalBtn).click(itemJurnalBtn);
		builder.perform();

	}

	public void insertItemNo(String skuItem) {
		element(insertItemNo).waitUntilVisible();
		Actions actions = new Actions(getDriver());

		actions.moveToElement(insertItemNo).click().perform();
		actions.moveToElement(insertItemNo).sendKeys(skuItem).perform();
	}
	public void insertItemVariantCode(String variantCode) {
		element(insertItemVariantCode).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(insertItemVariantCode).click().perform();
		actions.moveToElement(insertItemVariantCode).sendKeys(variantCode).perform();
	}
	
	
	public void insertKostenstelleCode(String kostenstelleCode) {
		element(insertKostenstelleCode).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(insertKostenstelleCode).click().perform();
		actions.moveToElement(insertKostenstelleCode).sendKeys(kostenstelleCode).perform();
	}
	
	public void insertLocationCode(String locationCode) {
		element(insertLocationCode).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(insertLocationCode).click().perform();
		actions.moveToElement(insertLocationCode).sendKeys(locationCode).perform();
	}
	
	public void insertQty(String qty) {
		element(insertQty).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(insertQty).click().perform();
		waitABit(1000);
		actions.moveToElement(insertQty).sendKeys(qty).perform();
	}

	public void insertValuesForItem(String skuItem, String variantCode, String kostenstelleCode, String locationCode,
			String qty) {
		element(insertItemNo).waitUntilVisible();
		Actions actions = new Actions(getDriver());

		actions.moveToElement(insertItemNo).click().perform();
		actions.moveToElement(insertItemNo).sendKeys(skuItem).perform();
		actions.moveToElement(insertItemVariantCode).click().perform();
		actions.moveToElement(insertItemVariantCode).sendKeys(variantCode).perform();

		waitABit(2000);
		actions.moveToElement(insertKostenstelleCode).click().perform();
		actions.moveToElement(insertKostenstelleCode).sendKeys(kostenstelleCode).perform();

		waitABit(2000);
		actions.moveToElement(insertLocationCode).click().perform();
		actions.moveToElement(insertLocationCode).sendKeys(locationCode).perform();

		waitABit(2000);
		actions.moveToElement(insertQty).click().perform();
		actions.moveToElement(insertQty).sendKeys(qty).perform();
	}

	public void clickOnPostMenuBtn() {
		waitABit(2000);
		element(postButton).waitUntilVisible();
		System.out.println("postButton sunt aici 2 ");

		Actions builder = new Actions(getDriver());
		builder.moveToElement(postButton).click(postButton);
		builder.perform();
		waitABit(2000);

	}

	public void selectHomeTab() {
		element(homeTab).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(homeTab).click().perform();
	}

	public void confirmPostJournalLines() {
		element(confirmPostJournalLines).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(confirmPostJournalLines).click().perform();
		waitABit(2000);
	}

	public void clickOnEditPoints() {
		element(clickOnEditPoints).waitUntilVisible();
		waitABit(2000);
		clickOnEditPoints.click();
	}

	public void clickOnEditLink() throws InterruptedException {
		element(clickOnEditLink).waitUntilVisible();
		waitABit(2000);
		clickOnEditLink.click();
	}

	public void clickOnTermPurchaseCheckbox() {
		element(clickOnTermPurchaseCheckbox).waitUntilVisible();
		waitABit(2000);
		if(getDriver().findElements(By.cssSelector(".ms-nav-band.hide-additional-fields.expanded .collapsibleTab div:nth-child(26) input[title*='No']")).size()>0){
			clickOnTermPurchaseCheckbox.click();
		}
		
	}

	public void closeWindow() {
		element(closeModalWindow).waitUntilVisible();
		waitABit(2000);
		closeModalWindow.click();
	}

	public void confirmSuccesfullyPostedJournalLines() {
		waitABit(2000);
		element(confirmSuccesfullyPosted).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(confirmSuccesfullyPosted).click().perform();
	}
	
	public void confirmPostedTransferOrders() {
		waitABit(2000);
		element(confirmSuccesfullyPosted).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(confirmSuccesfullyPosted).click().perform();
	}
	
	public void confirmPostedTransferOrders2() {
		waitABit(2000);
		element(titlePage).waitUntilVisible();
		String orderId=findInputFieldByLabel("No.").getText();
		System.out.println("orderId" +orderId);
		System.out.println();
		element(confirmSuccesfullyPosted).waitUntilVisible();
		
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(confirmSuccesfullyPosted).click().perform();
		
		
	}

	public void clickOnNewEntry() {
		// TODO Auto-generated method stub
		element(clickOnNewItem).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(clickOnNewItem).click().perform();
		waitABit(2000);
		
		
	}

	public void completeTransferDetails(String sku,String variantCode, String qty, String QS, String location_1000, String transitCode,
			String konstelleCode) {
		//WebElement transferCode=getDriver().findElements(By.cssSelector(".ms-nav-band.expanded .collapsibleTab .multiple-columns-group")).get(0).getText().contains("");
		WebElement transferFrom=findInputFieldByLabel("Transfer-from Code");
		Actions actions = new Actions(getDriver());
		actions.moveToElement(transferFrom).click().perform();
		waitABit(3000);
		actions.moveToElement(transferFrom).sendKeys(QS).perform();
		actions.moveToElement(transferFrom).click().perform();
		element(selectElementInModal).waitUntilVisible();
		actions.moveToElement(selectElementInModal).click().perform();
		
		waitABit(1000);
		
		WebElement transferTo=findInputFieldByLabel("Transfer-to Code");
		element(transferTo).waitUntilVisible();
		actions.moveToElement(transferTo).click().perform();
		actions.moveToElement(transferTo).sendKeys(location_1000).perform();
		element(selectElementInModal).waitUntilVisible();
		actions.moveToElement(selectElementInModal).click().perform();
		
		waitABit(1000);
		
		WebElement inTransitCode=findInputFieldByLabel("In-Transit Code");
		element(inTransitCode).waitUntilVisible();
		actions.moveToElement(inTransitCode).click().perform();
		actions.moveToElement(inTransitCode).sendKeys("TRANS").perform();
		element(selectElementInModal).waitUntilVisible();
		actions.moveToElement(selectElementInModal).click().perform();
		
		
		waitABit(1000);
		
		WebElement kostenstelleCode=findInputFieldByLabel("Kostenstelle Code");
		element(kostenstelleCode).waitUntilVisible();
		actions.moveToElement(kostenstelleCode).click().perform();
		actions.moveToElement(kostenstelleCode).sendKeys(konstelleCode).perform();
		element(selectElementInModal).waitUntilVisible();

		actions.moveToElement(selectElementInModal).click().perform();

		
		waitABit(1000);
		
		element(inserItemNo).waitUntilVisible();
		actions.moveToElement(inserItemNo).click().perform();
		actions.moveToElement(inserItemNo).sendKeys(sku).perform();
		element(selectElementInModal).waitUntilVisible();
		actions.moveToElement(selectElementInModal).click().perform();

		waitABit(2000);
		
		
		element(inserItemVariantNo).waitUntilVisible();
		actions.moveToElement(inserItemVariantNo).click().perform();
		actions.moveToElement(inserItemVariantNo).sendKeys(variantCode).perform();
//		if(!variantCode.contentEquals("")){
//		element(selectElementInModal).waitUntilVisible();
//			actions.moveToElement(selectElementInModal).click().perform();
//		}
	
		waitABit(2000);
		
		
		
		
	}
	

	public void insertTranferQty(String qty) {
		// TODO Auto-generated method stub
		Actions actions = new Actions(getDriver());
		element(insertQuantity).waitUntilVisible();

		actions.moveToElement(insertQuantity).click().perform();
		waitABit(2000);
		actions.moveToElement(insertQuantity).click().perform();
		actions.moveToElement(insertQuantity).sendKeys(qty).perform();
		
	
	}
	
	public WebElement findInputFieldByLabel(String element){
		List<WebElement> line = new ArrayList<WebElement>();
		WebElement inputField = null;
		
		for (WebElement webElement : transferFrom) {
			if(webElement.getText().contains(element)){
				line.add(webElement);
				break;
			}
		}
		
		if(line.size()==1){
			inputField=line.get(0).findElement(By.cssSelector("div input[name] "));
		}else{
			System.out.println("line"+ line);
		}
		
		return inputField;
		
	}

	

}

package com.pages.external.navision;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.connectors.navisionLogin.LoginWindow;
import com.thoughtworks.selenium.Wait;
import com.tools.requirements.AbstractPage;

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
	
	//@FindBy(css = ".ms-cui-img-cont-float img[src*='Action_Post']")
	@FindBy(css = ".ms-cui-tabBody li a:nth-child(3) span")
	private WebElement processButton;
	
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
		
	//	(new Thread(new LoginWindow())).start();
		LoginWindow loginthread =new LoginWindow();
		loginthread.login();
		// open your url. this will prompt you for windows authentication
		//getDriver().get("http://185.48.116.231:8080/DynamicsNAV90/WebClient/?company=PippaJean&bookmark=43%3bh%2fqqAAJ7%2f1AAUABKAF8ATQBJAFgARQBEAF8ATQBBAE4AVQBBAEw%3d&mode=Edit&page=11205257&i=301&IsDlg=1");

		
		// add test scripts below ...
	//	getDriver().findElement(By.linkText("Home")).click();

	}

	

	public void clickOnSalesOrderListLine() {
		System.out.println("clickOnSalesOrderListLine sunt aici");
		waitABit(2000);
		element(salesOrderListLine).waitUntilVisible();
		salesOrderListLine.click();
		waitABit(2000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(salesOrderListLine).click().perform();
		//waitABit(2000);
	//	salesOrderListLine.click();
	}
	
	public void clickOnLinesDropdown() {
		
		
		System.out.println("clickOnLinesDropdown sunt aici");
		waitABit(2000);
		//element(linesDropDown).waitUntilVisible();
		//linesDropDown.click();
		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(linesDropDown).click().perform();
		//actions.moveToElement(element).click().perform();
		
//		WebDriver webDriver = getDriver();
//		String jQuerySelector = "'"+linesDropDown+"'";
//		if (webDriver instanceof JavascriptExecutor) {
//		    ((JavascriptExecutor)webDriver).executeScript("return $(" + jQuerySelector+ ").get(0);");
//		} else {
//		    throw new IllegalStateException("This driver does not support JavaScript!");
//		}
	}
	
	public void clickOnFilter() {
		System.out.println("clickOnFileter sunt aici");
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(filter).click().perform();

	}
	
	public void inputOrderId(String orderId){
		System.out.println("inputOrderId sunt aici");
//		element(inputOrderId).waitUntilVisible();
//		inputOrderId.sendKeys(orderId);
//		
		Actions actions = new Actions(getDriver());
		actions.moveToElement(inputOrderId).click().perform();
		actions.moveToElement(inputOrderId).sendKeys(orderId).perform();
//		/inputOrderId.sendKeys(orderId);
	}
	

	
	
	public void performOrderImport() {
		System.out.println("performOrderImport sunt aici");
		waitABit(2000);
//		Actions actions = new Actions(getDriver());
//		actions.moveToElement(processButton).click().perform();
		
		
		element(processButton).waitUntilVisible();
		
		System.out.println("performOrderImport sunt aici 2 ");
	//	Actions actions = new Actions(getDriver());
	//	processButton.sendKeys(Keys.RETURN);
		///actions.moveToElement(processButton).click().perform();
		//
//		processButton.click();
	//	processButton.click();
	//	element(processButton).waitUntilVisible();
	//	waitABit(10000);
		
		
		//WebElement element = driver.findElement(By.id("something"));
//		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
//		executor.executeScript("arguments[0].click();", processButton);
		
		
		
		//WebElement webElement = driver.findElement(By.id("Your ID Here"));
		Actions builder = new Actions(getDriver());
		builder.moveToElement(processButton).click(processButton);
		builder.perform();
	}

	public void closeDialogModal() {
		
		System.out.println("closeDialogModal sunt aici");
		element(dialogModal).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(dialogModal).click().perform();
		
	}
	
	public void selectActionsTab(){
		System.out.println("selectActionsTab sunt aici");
		element(actionsTab).waitUntilVisible();
		waitABit(2000);
		Actions actions = new Actions(getDriver());

		actions.moveToElement(actionsTab).click().perform();
	}
	
	
	
}

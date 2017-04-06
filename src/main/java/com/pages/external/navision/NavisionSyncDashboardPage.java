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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.connectors.navisionLogin.LoginWindow;
import com.tools.requirements.AbstractPage;

public class NavisionSyncDashboardPage extends AbstractPage {

	@FindBy(css = ".ms-nav-band-caption a[title='Lines']")
	private WebElement linesOption;

	@FindBy(css = ".ms-nav-ctxmenu-item a[title='Filters']")
	private WebElement filtersOption;
	
	@FindBy(css = "table[summary*='MAGE Job Lines']  tr:nth-child(3) td:nth-child(4) a")
	private WebElement salesOrderListLine;
	
	@FindBy(css = "a.ms-nav-group-caption[title*=Lines]")
	private WebElement linesDropDown;
	
	@FindBy(css = ".pagetitle-control")
	private WebElement linesClick;
	
	
	
	@FindBy(css = ".ms-nav-ctxmenu-itemlist li a[title*=Filters]")
	private WebElement filter;

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
//		salesOrderListLine.click();
//		waitABit(4000);
	}
	
	public void clickOnLines() {
		System.out.println("clickOnLines sunt aici");
		waitABit(2000);
		element(linesClick).waitUntilVisible();
		linesClick.click();
		
	}
	
	public void clickOnLinesDropdown() {
		System.out.println("clickOnLinesDropdown sunt aici");
		waitABit(4000);
		element(linesDropDown).waitUntilVisible();
		waitABit(4000);
		linesDropDown.click();
		waitABit(4000);
		linesDropDown.click();
		
	}
	
	public void clickOnFileter() {
		System.out.println("clickOnFileter sunt aici");
		waitABit(2000);
		element(filter).waitUntilVisible();
		filter.click();
	}

	public void performOrderImport(String orderId) {
		
		
	}
	
	
	
	
}

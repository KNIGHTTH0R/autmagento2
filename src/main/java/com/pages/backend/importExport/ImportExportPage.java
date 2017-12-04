package com.pages.backend.importExport;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ImportExportPage extends AbstractPage {

	@FindBy(id = "pippajean_termpurchase_schedule_payments_switch_order_execution")
	private WebElement executionTypeOption;
	@FindBy(id = "nav")
	private WebElement navigationBar;

	@FindBy(css = "#nav li.parent.level0:nth-child(9)  ul li.level1:nth-child(7)")
	private WebElement importExportMenu;

	@FindBy(css = "#nav li.parent.level0:nth-child(9)  ul li.level1:nth-child(7) ul li.level2:nth-child(4)")
	private WebElement dataFlowProfileMenu;

	@FindBy(id = "convert_profile_tabs_upload")
	private WebElement uploadFileTab;

	@FindBy(id = "convert_profile_tabs_run")
	private WebElement runProfileTab;

	@FindBy(id = "file_1")
	private WebElement chooseFile1Btn;

	@FindBy(css = "button[onclick*='continue/true/']")
	private WebElement saveAndContinueBtn;
	
	@FindBy(css = "button[onClick*=runProfile]")
	private WebElement runProfileBtn;
	
	

	public void selectExecutionType(String executionType) {
		element(executionTypeOption).waitUntilVisible();
		element(executionTypeOption).selectByVisibleText(executionType);

	}

	public void selectDataFlowProfileMenu() {
		evaluateJavascript("jQuery.noConflict();");
		Actions builder = new Actions(getDriver());

		builder.moveToElement(importExportMenu).build().perform();
		builder.moveToElement(dataFlowProfileMenu).build().perform();

		element(dataFlowProfileMenu).waitUntilVisible();
		dataFlowProfileMenu.click();

		/*
		 * evaluateJavascript("jQuery.noConflict();");
		 * element(navigationBar).waitUntilVisible(); List<WebElement> menuList
		 * = navigationBar.findElements(By.cssSelector(
		 * "li.parent.level0 ul > li.level1 > a")); for (WebElement menuNow :
		 * menuList) { if (menuNow.getAttribute("href").contains(submenu)) {
		 * getDriver().get(menuNow.getAttribute("href")); break; } }
		 */

	}

	public void selectUploadFileTab() {
		// TODO Auto-generated method stub
		evaluateJavascript("jQuery.noConflict();");
		waitABit(2000);
		element(uploadFileTab).waitUntilVisible();
		uploadFileTab.click();

	}

	public void uploadFile(String path) {
		evaluateJavascript("jQuery.noConflict();");

		WebElement chooseFile = getDriver().findElement(By.id("file_1"));
		waitABit(2000);
		chooseFile.sendKeys(path);
		waitABit(4000);

	}

	public void selectImportDataCsv() {
		List<WebElement> list = getDriver()
				.findElements(By.cssSelector("#convertProfileGrid tbody tr td:nth-child(2)"));
		for (WebElement webElement : list) {
			if (webElement.getText().contains("Import Order Data Csv")) {
				webElement.click();
				break;
			}

		}

	}

	public void saveAndContinue() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveAndContinueBtn).waitUntilVisible();
		saveAndContinueBtn.click();
		waitForPageToLoad();

	}

	public void selectRunProfileTab() {
		evaluateJavascript("jQuery.noConflict();");
		waitABit(2000);
		element(runProfileTab).waitUntilVisible();
		runProfileTab.click();
	}

	public void selectUploadedFile(String path) {
		List<WebElement> lista=getDriver().findElements(By.cssSelector("#files option"));
		Select oSelect = new Select(getDriver().findElement(By.id("files")));
		/*for (WebElement option : lista) {
			if (option.getText().contains(path)) {
				oSelect.selectByValue(option.getText());
				break;
			}
		}*/
		
		if (lista.get(lista.size()-1).getText().contains(path)) {
			oSelect.selectByValue(lista.get(lista.size()-1).getText());
			
		}else{
			Assert.assertTrue("The document was not found",false);
		}
		
	
		
	}

	public void clickOnRunProfileBtn() {
		evaluateJavascript("jQuery.noConflict();");
		element(runProfileBtn).waitUntilVisible();
		runProfileBtn.click();
		waitForPageToLoad();
	}

	
}

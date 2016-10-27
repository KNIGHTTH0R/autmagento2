package com.pages.backend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class SystemConfigurationPage extends AbstractPage {

	@FindBy(id = "pippajean_termpurchase_schedule_payments_switch_order_execution")
	private WebElement executionTypeOption;

	@FindBy(css = "button[onclick='configForm.submit()']")
	private WebElement saveConfigurationButton;

	@FindBy(css = ".side-col")
	private WebElement listContainer;

	public void selectExecutionType(String executionType) {
    	element(executionTypeOption).waitUntilVisible();
		element(executionTypeOption).selectByVisibleText(executionType);
		
		
}
	public void saveConfiguration() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveConfigurationButton).waitUntilVisible();
		saveConfigurationButton.click();
		waitForPageToLoad();
	}

	public void clickOnDesiredTab(String tabName) {
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> tablist = listContainer.findElements(By.cssSelector("#system_config_tabs a"));
		boolean found = false;
		for (WebElement tab : tablist) {
			if (tab.findElement(By.cssSelector("span")).getText().trim().contains(tabName)) {
				found = true;
				tab.click();
				break;
			}
		}
		Assert.assertTrue("Tab not found !!!", found);

	}

}

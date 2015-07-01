package com.pages.backend.stylecoach;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StylecoachListBackendPage extends AbstractPage {

	@FindBy(id = "stylist_grid_filter_customer_email")
	private WebElement emailFilterInput;

	@FindBy(id = "sc-reassing")
	private WebElement reasignStylistsSelect;

	@FindBy(id = "stylist_grid_massaction-select")
	private WebElement actionsListSelect;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	public void selectActionList(String actionName) {
		evaluateJavascript("jQuery.noConflict();");
		element(actionsListSelect).waitUntilVisible();
		element(actionsListSelect).selectByVisibleText(actionName);
	}

	public void inputEmailFilter(String emailText) {
		evaluateJavascript("jQuery.noConflict();");
		element(emailFilterInput).waitUntilVisible();
		emailFilterInput.clear();
		emailFilterInput.sendKeys(emailText);

	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
		waitABit(2000);
	}

	public void selectStylecoachToReassignContactsTo(String stylecoachName) {
		evaluateJavascript("jQuery.noConflict();");
		element(reasignStylistsSelect).waitUntilVisible();
		reasignStylistsSelect.click();
		List<WebElement> stylistList = reasignStylistsSelect.findElements(By.cssSelector("option"));
		for (WebElement stylist : stylistList) {
			if (stylist.getText().contains(stylecoachName)) {
				stylist.click();
			}
		}
	}

	public void openPartyDetails(String searchTerm) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.cssSelector("tbody > tr"));
		boolean found = false;
		theFor: for (WebElement elementNow : listElements) {
			String id = elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText();
			if (id.contentEquals(searchTerm)) {
				elementNow.findElement(By.cssSelector("td:nth-child(1) input")).click();
				found = true;
				break theFor;
			}
		}
		Assert.assertTrue("Failure: Open Party Details - The party was not found in the list", found);
	}

}

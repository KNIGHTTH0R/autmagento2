package com.pages.backend.stylecoach;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.ConfigConstants;
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

	@FindBy(css = "#stylist_grid_table tbody tr:nth-child(1)")
	private WebElement styleCoachRow;

	@FindBy(css = "button[onclick='stylist_grid_massactionJsObject.apply()']")
	private WebElement submitReassignStylecoach;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	public void selectActionList() {
		evaluateJavascript("jQuery.noConflict();");
		element(actionsListSelect).waitUntilVisible();
		element(actionsListSelect).selectByVisibleText(ConfigConstants.CANCEL_SC_AND_REASSIGN_CONTACTS);
	}

	public void selectStylecoachToReassignContactsTo(String stylecoachName) {
		evaluateJavascript("jQuery.noConflict();");
		element(reasignStylistsSelect).waitUntilVisible();
		element(reasignStylistsSelect).selectByVisibleText(getFullTextOfStylecoachToReassignContactsTo(stylecoachName));
		waitABit(2000);
	}

	public void inputEmailFilter(String emailText) {
		evaluateJavascript("jQuery.noConflict();");
		element(emailFilterInput).waitUntilVisible();
		emailFilterInput.clear();
		emailFilterInput.sendKeys(emailText);

	}

	public void clickOnsubmitReassignStylecoach() {
		evaluateJavascript("jQuery.noConflict();");
		element(submitReassignStylecoach).waitUntilVisible();
		submitReassignStylecoach.click();
		waitABit(1000);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
		waitABit(2000);
	}

	public void verifyStylecoachEmailAndStatus(String stylecoachEmail) {
		evaluateJavascript("jQuery.noConflict();");
		element(styleCoachRow).waitUntilVisible();
		Assert.assertTrue("The stylecoach email is not the expected one", styleCoachRow.findElement(By.cssSelector("td:nth-child(6)")).getText().contains(stylecoachEmail));
		Assert.assertTrue("The status should be " + ConfigConstants.NOT_ACTIVE + " and it's not",
				styleCoachRow.findElement(By.cssSelector("td:nth-child(8)")).getText().contains(ConfigConstants.NOT_ACTIVE));
	}

	private String getFullTextOfStylecoachToReassignContactsTo(String stylecoachName) {
		evaluateJavascript("jQuery.noConflict();");
		element(reasignStylistsSelect).waitUntilVisible();
		String stylistName = "";
		boolean found = false;
		List<WebElement> stylistList = reasignStylistsSelect.findElements(By.cssSelector("option"));
		for (WebElement stylist : stylistList) {
			if (stylist.getText().contains(stylecoachName)) {
				found = true;
				stylistName = stylist.getText();
			}
		}
		Assert.assertTrue("No option with this email was found", found);
		return stylistName;
	}

	public void checkDesiredStylecoach(String searchTerm) {
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

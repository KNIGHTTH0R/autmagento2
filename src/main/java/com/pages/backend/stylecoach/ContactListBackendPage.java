package com.pages.backend.stylecoach;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.ConfigConstants;
import com.tools.requirements.AbstractPage;

public class ContactListBackendPage extends AbstractPage {

	@FindBy(id = "contact_grid_filter_customer_email")
	private WebElement emailFilterInput;

	@FindBy(css = "#contact_grid_table tbody tr:nth-child(1)")
	private WebElement styleCoachRow;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

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

	public void openStylistDetails() {
		evaluateJavascript("jQuery.noConflict();");
		element(styleCoachRow).waitUntilVisible();
		getDriver().get(styleCoachRow.getAttribute("title"));
	}

}

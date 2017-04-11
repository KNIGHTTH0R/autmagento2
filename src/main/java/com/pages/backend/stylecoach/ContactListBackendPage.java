package com.pages.backend.stylecoach;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class ContactListBackendPage extends AbstractPage {

	@FindBy(id = "contact_grid_filter_email")
	private WebElement emailFilterInput;

	@FindBy(css = "#contact_grid_table tbody tr:nth-child(1)")
	private WebElement styleCoachRow;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;
	
	@FindBy(css = "#contact_tabs li:nth-child(1)")
	private WebElement contactInformationsTab;

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

	public void openContactDetails() {
		evaluateJavascript("jQuery.noConflict();");
		element(styleCoachRow).waitUntilVisible();
		getDriver().get(styleCoachRow.getAttribute("title"));
		waitABit(3000);
	}
	
	public void clickOnContactInformationsTab(){
		evaluateJavascript("jQuery.noConflict();");
		element(contactInformationsTab).waitUntilVisible();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(contactInformationsTab).click().perform();
		
		System.out.println("sunt aici");
	}

}

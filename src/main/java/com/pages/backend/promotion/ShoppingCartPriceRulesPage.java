package com.pages.backend.promotion;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ShoppingCartPriceRulesPage extends AbstractPage {

	@FindBy(id = "rule_is_active")
	private WebElement activationDropdown;
	
	@FindBy(css = "p.form-buttons button:nth-child(4)")
	private WebElement saveRule;

	public void activateRule() {
		evaluateJavascript("jQuery.noConflict();");
		element(activationDropdown).selectByVisibleText("Aktiv");
	}

	public void deactivateRule() {
		evaluateJavascript("jQuery.noConflict();");
		element(activationDropdown).selectByVisibleText("Nicht aktiv");
	}
	
	public void saveRule(){
		element(saveRule).waitUntilVisible();
		element(saveRule).click();
	}
	
	@FindBy(css = "input#promo_quote_grid_filter_name")
	private WebElement ruleName;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	public void typeRuleName(String id) {
		element(ruleName).waitUntilVisible();
		element(ruleName).typeAndEnter(id);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void openRuleDetails(String searchTerm) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.cssSelector("tbody > tr"));
		boolean found = false;
		theFor: for (WebElement elementNow : listElements) {
			String id = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (id.contentEquals(searchTerm)) {
				elementNow.click();
				found = true;
				break theFor;
			}
		}
		Assert.assertTrue("Failure: Open Rule Details - The rule was not found in the list", found);
	}

}

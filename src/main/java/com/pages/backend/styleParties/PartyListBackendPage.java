package com.pages.backend.styleParties;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class PartyListBackendPage extends AbstractPage {

	@FindBy(css = "input#party_grid_filter_id_from")
	private WebElement idFrom;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	public void typeIdFrom(String id) {
		element(idFrom).waitUntilVisible();
		element(idFrom).typeAndEnter(id);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	public void openPartyDetails(String searchTerm) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.cssSelector("tbody > tr"));
		boolean found = false;
		theFor: for (WebElement elementNow : listElements) {
			String id = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
			if (id.contentEquals(searchTerm)) {
				elementNow.click();
				found = true;
				break theFor;
			}
		}
		Assert.assertTrue("Failure: Open Party Details - The party was not found in the list", found);
	}

}

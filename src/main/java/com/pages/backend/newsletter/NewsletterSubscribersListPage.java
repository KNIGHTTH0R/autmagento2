package com.pages.backend.newsletter;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NewsletterSubscribersListPage extends AbstractPage {

	@FindBy(id = "subscriberGrid_filter_email")
	private WebElement emailFilterInput;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;
	
	@FindBy(css = "td.filter-actions.a-right button:nth-child(1)")
	private WebElement resetFilter;

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
	public void clickOnResetFilter() {
		evaluateJavascript("jQuery.noConflict();");
		element(resetFilter).waitUntilVisible();
		resetFilter.click();
	}

	public void openNewsletterSubscriberDetails(String searchTerm) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.cssSelector("tbody > tr"));

		theFor: for (WebElement elementNow : listElements) {
			String currentEmail = elementNow.findElement(By.cssSelector("*:nth-child(3)")).getText();
			WebElement currentLink = elementNow.findElement(By.cssSelector("*:nth-child(3)"));
			System.out.println("Current Email: " + currentEmail);
			if (currentEmail.trim().contentEquals(searchTerm)) {
				currentLink.click();
				waitABit(1000);
				break theFor;
			}
		}
	}
}

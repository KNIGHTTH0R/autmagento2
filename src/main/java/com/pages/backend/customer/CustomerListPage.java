package com.pages.backend.customer;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class CustomerListPage extends AbstractPage {

	@FindBy(id = "customerGrid_filter_email")
	private WebElement emailFilterInput;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	public void inputEmailFilter(String emailText) {
		element(emailFilterInput).waitUntilVisible();
		emailFilterInput.sendKeys(emailText);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}
	
	public void openCustomerDetails(String searchTerm){
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		
		theFor:
		for(WebElement elementNow:listElements){
			String currentEmail = elementNow.findElement(By.cssSelector("*:nth-child(4)")).getText();
			WebElement currentLink = elementNow.findElement(By.cssSelector("*:nth-child(4)"));
			System.out.println("Current Email: " + currentEmail);
			if(currentEmail.trim().contentEquals(searchTerm)){
				currentLink.click();
				break theFor;
			}
		}
	}
}

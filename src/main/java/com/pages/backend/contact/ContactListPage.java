package com.pages.backend.contact;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ContactListPage extends AbstractPage {

	@FindBy(id = "contact_grid_filter_email")
	private WebElement emailFilterInput;
	
	@FindBy(id = "contact_grid_massaction-select")
	private WebElement actionDropDown;

	@FindBy(css = "div.hor-scroll")
	private WebElement listContainer;
	
	@FindBy(css = "td.filter-actions.a-right button:nth-child(1)")
	private WebElement resetFilter;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;
	
	@FindBy(css = "button[onclick*='contact_grid_massactionJsObject.apply()']")
	private WebElement submitButton;
	

	public void inputEmailFilter(String emailText) {
		waitABit(3000);
	//	evaluateJavascript("jQuery.noConflict();");
		element(emailFilterInput).waitUntilVisible();
		emailFilterInput.clear();
		emailFilterInput.sendKeys(emailText);
		waitABit(1000);
	
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
		waitABit(TimeConstants.TIME_CONSTANT);
	
	}
	public void clickOnResetFilter() {
		evaluateJavascript("jQuery.noConflict();");
		element(resetFilter).waitUntilVisible();
		resetFilter.click();
	}

	public void openCustomerDetails(String searchTerm) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.cssSelector("tbody > tr"));

		theFor: for (WebElement elementNow : listElements) {
			String currentEmail = elementNow.findElement(By.cssSelector("*:nth-child(4)")).getText();
			WebElement currentLink = elementNow.findElement(By.cssSelector("*:nth-child(4)"));
			System.out.println("Current Email: " + currentEmail);
			if (currentEmail.trim().contentEquals(searchTerm)) {
				currentLink.click();
				waitABit(1000);
				break theFor;
			}
		}
	}
	
	
	public WebElement findContact(String contactEmail) {
		evaluateJavascript("jQuery.noConflict();");
		WebElement foundContact = null;
		//element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		for (WebElement elementNow : listElements) {
			if (elementNow.getText().contains(contactEmail)) {
				foundContact = elementNow;
				break;
			}
		}
		return foundContact;
	}

	public boolean findContactAndCheckIt(String emailText) {
		
		List<WebElement> contactList=getDriver().findElements(By.cssSelector("#contact_grid_table tbody tr"));
		boolean found=false;
		for (WebElement contact : contactList) {
			if(	contact.findElement(By.cssSelector("td:nth-child(6)")).getText().contains(emailText) ){
				found=true;
				contact.findElement(By.cssSelector("td:nth-child(1) input")).click();
				break;
			}
		}
		Assert.assertTrue("the contact"+ emailText+ "does not exist in the list", found );
		return found;
	}

	
	public boolean selectContact(String contactEmail) {
		boolean found=false;
		WebElement contact=findContact(contactEmail).findElement(By.cssSelector("td input"));
		if(contact!=null){
			found=true;
			contact.click();
		}
		return found;
	
	}
	
	public void deleteContact() {
		Select oSelect = new Select(actionDropDown);
		oSelect.selectByVisibleText("Löschen");
		
		element(submitButton).waitUntilVisible();
		submitButton.click();
		
		
		waitABit(TimeConstants.TIME_CONSTANT);
		Alert alert = getDriver().switchTo().alert();
		alert.accept();		
		
		waitABit(10000);
		
		
		
		
		 
		
	}
}

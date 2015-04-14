
package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class PartyCreationPage extends AbstractPage {

	@FindBy(css = "a.button.blue-button.btn-fix")
	private WebElement orderForHostessButton;

	@FindBy(css = "table.ui-datepicker-calendar tbody tr td a:nth-child(1).ui-state-default.ui-state-highlight")
	private WebElement firstAvailableDateButton;

	@FindBy(id = "location")
	private WebElement locationContainer;

	@FindBy(id = "date")
	private WebElement dateSelect;
	
	@FindBy(id = "addContact")
	private WebElement addContact;

	@FindBy(css = "input#hostedBy_contact")
	private WebElement hostedByCustomer;
	
	@FindBy(css = "input#contact")
	private WebElement customerName;
	
	@FindBy(css = "ul li.ui-menu-item a")
	private WebElement selectContact;

	@FindBy(id = "time")
	private WebElement hourSelectDropDown;

	@FindBy(xpath = "//option[not(@disabled)]")
	private WebElement availableHoursButton;

	@FindBy(css = ".button[type*='submit']")
	private WebElement partySubmitButton;
	
	@FindBy(id = "city")
	private WebElement cityInput;

	public void clickAddContact() {
		element(addContact).waitUntilVisible();
		addContact.click();
	}
	
	public void clickOrderForHostess() {
		element(orderForHostessButton).waitUntilVisible();
		orderForHostessButton.click();
	}

	public String submitParty() {
		element(partySubmitButton).waitUntilVisible();
		partySubmitButton.click();
		return getDriver().getCurrentUrl();
	}

	public void checkHostedByCustomer() {
		element(hostedByCustomer).waitUntilVisible();
		hostedByCustomer.click();

	}

	public void typeLocation(String location) {
		element(locationContainer).waitUntilVisible();
		locationContainer.sendKeys(location);

	}
	public void typeCustomerName(String name) {
		element(customerName).waitUntilVisible();
		element(customerName).sendKeys(name);
		element(selectContact).waitUntilVisible();
		element(selectContact).click();
		waitABit(1000);
		
		
	}

	public void selectFirstAvailableDate() {

		element(dateSelect).waitUntilVisible();
		dateSelect.click();
		element(firstAvailableDateButton).waitUntilVisible();
		firstAvailableDateButton.click();

	}

	public void selectFirstAvailableHour() {

		List<WebElement> hoursList = hourSelectDropDown.findElements(By.xpath("//option[not(@disabled)]"));
		hoursList.get(2).click();
		

	}

}


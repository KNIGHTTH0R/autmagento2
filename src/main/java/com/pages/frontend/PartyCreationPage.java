
package com.pages.frontend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
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
	
	@FindBy(id = "country")
	private WebElement countryInput;
	
	@FindBy(css = "#form-stylist-party-create #country")
	private WebElement countryDdl;
	
	public void verifyThatPartyCountryListDoesNotContainRestrictedCountry(){
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE) || !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
		System.out.println(countryDdl.getText());
	}

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
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		return getDriver().getCurrentUrl();
		
	}

	public void checkHostedByCustomer() {
		element(hostedByCustomer).waitUntilVisible();
		hostedByCustomer.click();
		waitABit(TimeConstants.WAIT_TIME_SMALL);

	}

	public void typeLocation(String location) {
		element(locationContainer).waitUntilVisible();
		locationContainer.sendKeys(location);

	}

	public void selectPartyCountry(String country) {
		element(countryInput).waitUntilVisible();
		countryInput.sendKeys(country);

	}

	public void typeCustomerName(String name) {
		element(customerName).waitUntilVisible();
		element(customerName).sendKeys(name);
		element(selectContact).waitUntilVisible();
		element(selectContact).click();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void selectFirstAvailableDate() {
		element(dateSelect).waitUntilVisible();
		waitABit(2000);
		dateSelect.click();
		element(firstAvailableDateButton).waitUntilVisible();
		firstAvailableDateButton.click();

	}

	public void selectFirstAvailableHour() {

		List<WebElement> hoursList = hourSelectDropDown.findElements(By.xpath("//option[not(@disabled)]"));
		hoursList.get(2).click();
		

	}

}



package com.pages.frontend;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
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
	
	@FindBy(id = "is_online")
	private WebElement onlineStylePartyCheckBox;
	
	@FindBy(id = "city")
	private WebElement cityInput;
	
	@FindBy(id = "country")
	private WebElement countryInput;
	
	@FindBy(css = "#form-stylist-party-create #country")
	private WebElement countryDdl;
	
	public void verifyThatPartyCountryListDoesNotContainRestrictedCountry(){
		Assert.assertTrue("The ddl contains the country name and it should not !!!", !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE) || !countryDdl.getText().contains(ContextConstants.NOT_PREFERED_LANGUAGE.toUpperCase()));
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
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
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

	public void checkOnlineStyleParty() {
		element(onlineStylePartyCheckBox).waitUntilVisible();
		onlineStylePartyCheckBox.click();
		waitABit(TimeConstants.WAIT_TIME_SMALL);

		
	}

	public String grabHostPageURL() {
		String hostUrlPage="";
		List<WebElement> hostLink=getDriver().findElements(By.cssSelector(".beta-block tbody tr:nth-child(1) td:nth-child(2)"));
		for (WebElement link : hostLink) {
			if(link.getText().contains("p/h/"));
			hostUrlPage=link.getText();
		}
		CustomVerification.verifyTrue("Failure: The host link was not found", hostUrlPage!="");
		return hostUrlPage;
	}

	public String grabHostPassword() {
		List<WebElement> hostPass=getDriver().findElements(By.cssSelector(".beta-block tbody tr:nth-child(2) td:nth-child(2)"));
		return hostPass.get(1).getText();
	}

	public String grabPartyId() {
		String url=getDriver().getCurrentUrl();
		return extractPartyID(url);
	}
	private String extractPartyID(String urlModel) {
		return urlModel.substring(urlModel.indexOf("id/")+3).replaceAll("\\D+","");
		
		
	}
}


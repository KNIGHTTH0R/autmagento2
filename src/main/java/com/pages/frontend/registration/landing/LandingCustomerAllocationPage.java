package com.pages.frontend.registration.landing;

import net.serenitybdd.core.annotations.findby.FindBy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.requirements.AbstractPage;

/**
 * This page is the second page, after the registration form on the landing page
 * has been confirmed. Style coach allocation is selected on this page. You can
 * choose a specific StyleCoach or one that is provided by Pippa Jean (default)
 * 
 * @author voicu.vac
 *
 */
public class LandingCustomerAllocationPage extends AbstractPage {

	@CacheLookup
	@FindBy(id = "by_sc_name")
	private WebElement radioSelectByName;

	@FindBy(id = "search_firstname")
	private WebElement firstNameInput;

	@FindBy(id = "search_lastname")
	private WebElement lastNameInput;

	@FindBy(name = "search_by_name_submit")
	private WebElement searchSubmitButton;

	@FindBy(id = "kostenlos-anmelden")
	private WebElement submitButton;

	@FindBy(id = "by_geoip")
	private WebElement searchStylistByGeoip;

	@FindBy(id = "search_postcode")
	private WebElement searchPostcode;

	@FindBy(id = "search_countryId")
	private WebElement searchCountry;

	@FindBy(css = "ul#stylist-list li:nth-child(1) div button")
	private WebElement firstStylistContainer;

	@FindBy(css = "button[name='search_by_geoip_submit']")
	private WebElement searchByGeoipSubmitButton;

	public void searchStylistByGeoip() {
		searchStylistByGeoip.click();
	}

	public void inputPostcodeFilter(String postcode) {
		searchPostcode.clear();
		searchPostcode.sendKeys(postcode);
	}

	public void selectCountryFilter(String countryName) {
		element(searchCountry).waitUntilVisible();
		element(searchCountry).selectByVisibleText(countryName);
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void selectFirstStylistFromList() {
		element(firstStylistContainer).waitUntilVisible();
		firstStylistContainer.click();
	}

	public void searchByGeoipSubmit() {
		element(searchByGeoipSubmitButton).waitUntilVisible();
		searchByGeoipSubmitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public enum StyleMode {
		CustomStylist, DefaultStylist
	}

	public void selectStylistOption(StyleMode mode, String firstName, String lastName, AddressModel addressModel) {
		element(searchStylistByGeoip).waitUntilVisible();

		switch (mode) {
		case CustomStylist:
			radioSelectByName.click();
			element(firstNameInput).waitUntilEnabled();
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				firstNameInput.sendKeys(firstName);
				lastNameInput.sendKeys(lastName);
			}
			searchSubmitButton.click();
			withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
					By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
			selectFirstStylistFromList();

			break;
		case DefaultStylist:
			searchStylistByGeoip();
			inputPostcodeFilter(addressModel.getPostCode());
			selectCountryFilter(addressModel.getCountryName());
			searchByGeoipSubmit();
			withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
					By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
			selectFirstStylistFromList();
			break;

		default:
			break;
		}

	}

	public void submitAndContinue() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}
}

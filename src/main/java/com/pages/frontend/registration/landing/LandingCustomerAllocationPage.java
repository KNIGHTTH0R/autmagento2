package com.pages.frontend.registration.landing;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

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
	}

	public void selectFirstStylistFromList() {
		element(firstStylistContainer).waitUntilVisible();
		firstStylistContainer.click();
	}

	public void searchByGeoipSubmit() {
		element(searchByGeoipSubmitButton).waitUntilVisible();
		searchByGeoipSubmitButton.click();
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
			selectFirstStylistFromList();

			break;
		case DefaultStylist:
			searchStylistByGeoip();
			inputPostcodeFilter(addressModel.getPostCode());
			selectCountryFilter(addressModel.getCountryName());
			searchByGeoipSubmit();
			waitABit(2000);
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

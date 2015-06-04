package com.pages.backend.customer.details;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.frontend.AddressModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class CustomerDetailsPage extends AbstractPage {

	@FindBy(id = "customer_info_tabs_stylecoach_lead_manage")
	private WebElement leadSettingsButton;

	@FindBy(css = "#rewardPointsBalanceGrid_table tbody tr:nth-child(2) td:nth-child(3)")
	private WebElement jewerlyContainer;

	@FindBy(css = "table.box-left tr:nth-child(5) td:last-child")
	private WebElement customerTypeContainer;

	@FindBy(css = "table.box-left tr:nth-child(2) td:last-child")
	private WebElement statusContainer;

	@FindBy(id = "customer_info_tabs_customer_edit_tab_view_content")
	private WebElement detailsContainer;

	@FindBy(id = "customer_info_tabs_addresses")
	private WebElement addressesTab;

	@FindBy(css = "input[id*='street0'][id^='_item']")
	private WebElement streetInput;

	@FindBy(css = "input[id*='house_number'][id^='_item']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[id*='city'][id^='_item']")
	private WebElement cityInput;

	@FindBy(css = "select[id*='country_id'][id^='_item']")
	private WebElement countrySelect;

	@FindBy(css = "input[id*='postcode'][id^='_item']")
	private WebElement postCodeInput;

	public void clickOnLeadSettings() {
		evaluateJavascript("jQuery.noConflict();");
		element(leadSettingsButton).waitUntilVisible();
		leadSettingsButton.click();
	}

	public void inputStreet(String street) {
		evaluateJavascript("jQuery.noConflict();");
		element(streetInput).waitUntilVisible();
		element(streetInput).sendKeys(street);
	}

	public void inputHouseNumber(String number) {
		evaluateJavascript("jQuery.noConflict();");
		element(houseNumberInput).waitUntilVisible();
		element(houseNumberInput).sendKeys(number);
	}

	public void inputCity(String city) {
		evaluateJavascript("jQuery.noConflict();");
		element(cityInput).waitUntilVisible();
		element(cityInput).sendKeys(city);
	}

	public void inputPostCode(String code) {
		evaluateJavascript("jQuery.noConflict();");
		element(postCodeInput).waitUntilVisible();
		element(postCodeInput).sendKeys(code);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(countryName);
	}

	public void clickOnAddressesTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(addressesTab).waitUntilVisible();
		addressesTab.click();
	}

	public CoordinatesModel changeAdress(AddressModel addressModel) {
		CoordinatesModel coordinatesModel = new CoordinatesModel();

		return coordinatesModel;
	}

	public String extractEmailConfirmationStatus() {
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("E-Mail-Adresse"))
				status = elemNow.getText();
			status = status.replace("Bestätigte E-Mail-Adresse:", "").trim();
		}

		return status;
	}

	public String extractRegistrationDate() {
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("Konto erstellt am"))
				status = elemNow.getText();
		}

		return status;
	}

	public String extractConfirmationDate() {
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("Registrierungsdatum als SC"))
				status = elemNow.getText();
		}

		return status;
	}

	public String extractEmailConfirmationStatusWithoutLabel() {
		return statusContainer.getText();
	}

	public String extractCustomerType() {
		element(customerTypeContainer).waitUntilVisible();
		return customerTypeContainer.getText();
	}

	/**
	 * Grab customer id from url.
	 */
	public String grabCustomerId() {
		String url = getDriver().getCurrentUrl();
		String userId[] = url.split("/");
		return userId[8];
	}

	public void deleteCustomer() {
		List<WebElement> deleteButtons = getDriver().findElements(By.cssSelector("div.main-col div.main-col-inner button.delete"));

		for (WebElement buttonNow : deleteButtons) {
			buttonNow.click();
			waitABit(TimeConstants.TIME_CONSTANT);
			String alertText = getDriver().switchTo().alert().getText();
			getDriver().switchTo().alert().accept();
			if (alertText.contains(ContextConstants.PROCESS)) {
				break;
			}
		}
	}

	public String extractJewelryBonusValue() {
		String jb = "";
		List<WebElement> list = getDriver().findElements(By.cssSelector("#rewardPointsBalanceGrid_table tbody tr"));
		for (WebElement item : list) {
			if (item.getText().contains("Schmuckbonus")) {
				jb = item.findElement(By.cssSelector("td:nth-child(3)")).getText();
				break;
			}
		}

		return jb;
	}

}

package com.pages.backend.customer.details;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

public class CustomerDetailsPage extends AbstractPage {

	@FindBy(id = "customer_info_tabs_customer_edit_tab_reward")
	private WebElement rewardPointsTab;

	@FindBy(id = "reward_reward_type")
	private WebElement rewardType;

	@FindBy(id = "reward_points_delta")
	private WebElement rewardPointsInput;

	@FindBy(css = "input#_accountemail")
	private WebElement emailInput;

	@FindBy(id = "customer_info_tabs_stylecoach_lead_manage")
	private WebElement leadSettingsButton;

	@FindBy(css = "#rewardPointsBalanceGrid_table tbody tr:nth-child(2) td:nth-child(3)")
	private WebElement jewerlyContainer;

	@FindBy(css = "table.box-left tr:nth-child(5) td:last-child")
	private WebElement customerTypeContainer;

	@FindBy(css = "#customer_info_tabs_customer_edit_tab_view_content div:nth-child(2) table tbody tr[onmouseover*='this.style.backgroundColor']:nth-child(1) td:nth-child(2)")
	private WebElement incrementIdContainer;

	@FindBy(css = "table.box-left tr:nth-child(2) td:last-child")
	private WebElement statusContainer;

	@FindBy(id = "customer_info_tabs_customer_edit_tab_view_content")
	private WebElement detailsContainer;

	@FindBy(css = "a#customer_info_tabs_addresses")
	private WebElement addressesTab;

	@FindBy(css = "a#customer_info_tabs_account")
	private WebElement accountInfoTab;

	@FindBy(css = "a#customer_info_tabs_stylecoach_profile")
	private WebElement styleCoachProfileTab;

	@FindBy(css = "a#customer_info_tabs_stylecoach_entity")
	private WebElement styleCoachManagementTab;

	@FindBy(css = "input[id*='street0'][id^='_item']")
	private WebElement streetInput;

	@FindBy(css = "input[id*='house_number'][id^='_item']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[id*='city'][id^='_item']")
	private WebElement cityInput;

	@FindBy(css = "input[title*='Set as Default Billing Address']")
	private WebElement defaultBillingCheckbox;

	@FindBy(css = "input[title*='Set as Default Shipping Address']")
	private WebElement defaultShippingCheckbox;

	@FindBy(css = "select[id*='country_id'][id^='_item']")
	private WebElement countrySelect;

	@FindBy(css = "input[id*='postcode'][id^='_item']")
	private WebElement postCodeInput;

	@FindBy(css = "button[onclick*='editForm.submit();']")
	private WebElement saveCustomer;

	@FindBy(css = "button[onclick*='saveAndContinueEdit']")
	private WebElement saveAndContinueEdit;

	@FindBy(id = "add_address_button")
	private WebElement addAddressButton;

	@FindBy(css = "select[name='stylistentity[sponsor_id]']")
	private WebElement sponsorDropown;

	@FindBy(id = "_stylistprofilebank_account_vat_payer")
	private WebElement vatPayerDropown;

	@FindBy(id = "_stylistprofilebank_account_vat_number")
	private WebElement vatNumberInput;

	@FindBy(css = "li.success-msg")
	private WebElement successMessage;

	@FindBy(id = "customer_info_tabs_performance")
	private WebElement performanceTab;

	public void addNewAddress() {
		evaluateJavascript("jQuery.noConflict();");
		element(addAddressButton).waitUntilVisible();
		addAddressButton.click();
	}

	public void checkDefaultBillingAddress() {
		evaluateJavascript("jQuery.noConflict();");
		element(defaultBillingCheckbox).waitUntilVisible();
		defaultBillingCheckbox.click();
	}

	public void checkDefaultShippingAddress() {
		evaluateJavascript("jQuery.noConflict();");
		element(defaultShippingCheckbox).waitUntilVisible();
		defaultShippingCheckbox.click();
	}

	public void saveCustomer() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveCustomer).waitUntilVisible();
		saveCustomer.click();
	}

	public void saveAndContinueEdit() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveAndContinueEdit).waitUntilVisible();
		saveAndContinueEdit.click();
	}

	public void clickOnLeadSettings() {
		evaluateJavascript("jQuery.noConflict();");
		element(leadSettingsButton).waitUntilVisible();
		leadSettingsButton.click();
	}

	public void inputStreet(String street) {
		evaluateJavascript("jQuery.noConflict();");
		element(streetInput).waitUntilVisible();
		element(streetInput).clear();
		element(streetInput).sendKeys(street);
	}

	public void inputHouseNumber(String number) {
		evaluateJavascript("jQuery.noConflict();");
		element(houseNumberInput).waitUntilVisible();
		element(houseNumberInput).clear();
		element(houseNumberInput).sendKeys(number);
	}

	public void inputCity(String city) {
		evaluateJavascript("jQuery.noConflict();");
		element(cityInput).waitUntilVisible();
		element(cityInput).clear();
		element(cityInput).sendKeys(city);
	}

	public void inputPostCode(String code) {
		evaluateJavascript("jQuery.noConflict();");
		element(postCodeInput).waitUntilVisible();
		element(postCodeInput).clear();
		element(postCodeInput).sendKeys(code);
	}

	public void inputVatNumber(String vatNumber) {
		evaluateJavascript("jQuery.noConflict();");
		element(vatNumberInput).waitUntilVisible();
		element(vatNumberInput).sendKeys(vatNumber);
	}

	public void selectCountryName(String countryName) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByVisibleText(countryName);
	}

	public void selectSponsor(String sponsor) {
		element(sponsorDropown).waitUntilVisible();
		element(sponsorDropown).selectByValue(sponsor);
	}

	public void selectVatPayer(String vatPayer) {
		element(vatPayerDropown).waitUntilVisible();
		element(vatPayerDropown).selectByValue(vatPayer);
	}

	public void clickOnAddressesTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(addressesTab).waitUntilVisible();
		addressesTab.click();
	}

	public void clickOnStylecoachProfileTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(styleCoachProfileTab).waitUntilVisible();
		styleCoachProfileTab.click();
	}

	public void clickOnStylecoachManagementTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(styleCoachManagementTab).waitUntilVisible();
		styleCoachManagementTab.click();
	}

	public void clickOnAccountInfoTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(accountInfoTab).waitUntilVisible();
		accountInfoTab.click();
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

	public String extractCustomerIncrementId() {
		element(incrementIdContainer).waitUntilVisible();
		return incrementIdContainer.getText();
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

	public String extractMarketingMaterialBonusValue() {
		String mmb = "";
		List<WebElement> list = getDriver().findElements(By.cssSelector("#rewardPointsBalanceGrid_table tbody tr"));
		for (WebElement item : list) {
			if (item.getText().contains("Marketing Material Bonus")) {
				mmb = item.findElement(By.cssSelector("td:nth-child(3)")).getText();
				break;
			}
		}

		return mmb;
	}

	public void selectRewardPointstype(String reward) {
		element(rewardType).waitUntilVisible();
		element(rewardType).selectByVisibleText(reward);
	}

	public void clickOnRewardsPointsTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(rewardPointsTab).waitUntilVisible();
		rewardPointsTab.click();
	}

	public void typeRewardsPointsValue(String value) {
		evaluateJavascript("jQuery.noConflict();");
		element(rewardPointsInput).waitUntilVisible();
		element(rewardPointsInput).sendKeys(value);
	}

	public void typeEmail(String value) {
		evaluateJavascript("jQuery.noConflict();");
		element(emailInput).waitUntilVisible();
		element(emailInput).clear();
		element(emailInput).sendKeys(value);
	}

	public void verifySaveCustomerSuccessMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + ContextConstants.CUSTOMER_SAVE_SUCCESS_MESSAGE + " and it's not! Actual: " + successMessage.getText(), successMessage
				.getText().contains(ContextConstants.CUSTOMER_SAVE_SUCCESS_MESSAGE));
	}

	public RewardPointsOfStylistModel getRewardPointsOfStylistModel() {
		RewardPointsOfStylistModel rewardPoints = new RewardPointsOfStylistModel();
		rewardPoints.setJewelryBonus(extractJewelryBonusValue());
		rewardPoints.setMarketingMaterialBonus(extractMarketingMaterialBonusValue());
		return rewardPoints;
	}

	public void clickOnPerformanceTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(performanceTab).waitUntilVisible();
		performanceTab.click();
		waitABit(5000);

	}

	public LoungeIpPerformanceModel grabSCPerformanceIpLogicAdmin() {

		LoungeIpPerformanceModel result = new LoungeIpPerformanceModel();

		result.setCareerLevel(getDriver().findElement(By.cssSelector("#career")).getText());
		result.setPayLevel(getDriver().findElement(By.cssSelector("#paylevel")).getText());
		result.setIndividualPoints(getDriver().findElement(By.cssSelector("#ip")).getText());
		result.setTeamPoints(getDriver().findElement(By.cssSelector("#teamPoints")).getText());
		result.setStyleCoachFirstLevel(getDriver().findElement(By.cssSelector("#frontliners")).getText());
		result.setGoldStyleCoaches(getDriver().findElement(By.cssSelector("#goldStylists")).getText());

		PrintUtils.printLoungeIpPerformanceModel(result);

		return result;
	}
}

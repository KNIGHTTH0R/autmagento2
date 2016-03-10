package com.steps.backend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

import com.tools.data.StylistDataModel;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.TimeConstants;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class BackEndSteps extends AbstractSteps {

	private static final long serialVersionUID = 6989975368757184274L;

	@Step
	public void performAdminLogin(String userName, String userPass) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();
		// dismissPopUp();
	}

	@Step
	public void goToBackend() {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
	}

	@Step
	public void performLogin(String userName, String userPass) {
		getDriver().get(MongoReader.getBaseURL() + UrlConstants.BASE_URL_AUT);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();
	}

	@Step
	public void clickOnCustomers() {
		navigationPage().selectMenuFromNavbar("Kunden", "Kunden verwalten");
	}

	@Step
	public void clickOnProducts() {
		navigationPage().selectMenuFromNavbar("Katalog", "Produkte verwalten");
	}

	@Step
	public void goToNewsletterSubribers() {
		navigationPage().selectMenuFromNavbar("Newsletter", "Newsletter Bezieher");
	}

	@Step
	public void clickOnStyleParties() {
		navigationPage().selectMenuFromNavbar("Stylecoach", "Style Parties");
	}

	@Step
	public void clickOnStylecoachList() {
		navigationPage().selectMenuFromNavbar("Stylecoach", "Stylecoach List");
	}

	@Step
	public void clickOnContactList() {
		navigationPage().selectMenuFromNavbar("Stylecoach", "Kontakte");
	}

	@Step
	public void clickOnShoppingCartPriceRules() {
		navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb Preisgebote");
	}

	@Step
	public void clickOnSalesOrders() {
		navigationPage().selectMenuFromNavbar("Verkäufe", "Aufträge");
	}

	@Step
	public void clickOnCreditMemo() {
		navigationPage().selectMenuFromNavbar("Verkäufe", "Gutschriften");
	}

	@Step
	public void searchForEmail(String emailText) {
		customerListPage().clickOnResetFilter();
		waitABit(3000);
		customerListPage().inputEmailFilter(emailText);
		customerListPage().clickOnSearch();

	}

	@Step
	public void searchOrderByName(String name) {
		orderListPage().inputOrderName(name);
		orderListPage().clickOnSearch();
		waitABit(2000);
	}

	@Step
	public void searchOrderByOrderId(String orderId) {
		orderListPage().inputOderId(orderId);
		orderListPage().clickOnSearch();
		waitABit(2000);
	}

	@Step
	public void openOrderDetails(String name) {
		orderListPage().openOrderDetails(name);
	}

	@Step
	public void searchCreditMemoByorderId(String orderId) {
		creditMemoListPage().inputCreditMemoOrderId(orderId);
		creditMemoListPage().clickOnSearch();
		waitABit(2000);
	}

	@Step
	public void openCreditMemoDetails(String name) {
		creditMemoListPage().openCreditMemoDetails(name);
	}

	@Step
	public void cancelCreditMemo() {
		creditMemoDetailsPage().cancelCreditMemo();
		creditMemoDetailsPage().verifyCreditMemoRefundedMessage();
	}

	@Step
	public String openCustomerDetails(String emailText) {
		customerListPage().openCustomerDetails(emailText);
		waitABit(TimeConstants.TIME_CONSTANT);
		refresh();
		return getDriver().getCurrentUrl();
	}

	@Step
	public void clickOnAddressesTab() {
		customerDetailsHomePage().clickOnAddressesTab();
	}

	@Step
	public void addAddress() {
		customerDetailsHomePage().addNewAddress();
	}

	@Step
	public void checkDefaultBillingAddress() {
		customerDetailsHomePage().checkDefaultBillingAddress();
	}

	@Step
	public void checkDefaultShippingAddress() {
		customerDetailsHomePage().checkDefaultShippingAddress();
	}

	@Step
	public void editAddress(AddressModel addressModel) {
		customerDetailsHomePage().clickOnAddressesTab();
		customerDetailsHomePage().inputStreet(addressModel.getStreetAddress());
		customerDetailsHomePage().inputHouseNumber(addressModel.getStreetNumber());
		customerDetailsHomePage().inputCity(addressModel.getHomeTown());
		customerDetailsHomePage().selectCountryName(addressModel.getCountryName());
		customerDetailsHomePage().inputPostCode(addressModel.getPostCode());
		customerDetailsHomePage().saveCustomer();
	}

	@Step
	public void editCity(String city) {
		customerDetailsHomePage().clickOnAddressesTab();
		customerDetailsHomePage().inputCity(city);
		customerDetailsHomePage().saveAndContinueEdit();
	}

	@Step
	public void changeStylecoachSponsor(String sponsor) {
		customerDetailsHomePage().clickOnStylecoachManagementTab();
		customerDetailsHomePage().selectSponsor(sponsor);
		customerDetailsHomePage().saveAndContinueEdit();

	}

	@Step
	public void changeStylecoachVatSettings(String vatPayer, String vatNumber) {
		customerDetailsHomePage().clickOnStylecoachProfileTab();
		customerDetailsHomePage().selectVatPayer(vatPayer);
		customerDetailsHomePage().inputVatNumber(vatNumber);
		customerDetailsHomePage().saveAndContinueEdit();

	}

	@Step
	public void editEmail(CustomerFormModel customerData) {
		customerDetailsHomePage().clickOnAccountInfoTab();
		customerDetailsHomePage().typeEmail(customerData.getEmailName().replace("@mailinator.com", "@evozon.com"));
		customerDetailsHomePage().saveCustomer();
	}

	@Step
	public void confirmCustomer() {
		customerDetailsHomePage().clickOnAccountInfoTab();
		customerDetailsHomePage().selectConfirmationStatus("Confirmed");
		customerDetailsHomePage().saveCustomer();
	}

	@Step
	public void addNewAddress(AddressModel addressModel) {
		customerDetailsHomePage().clickOnAddressesTab();
		customerDetailsHomePage().addNewAddress();
		customerDetailsHomePage().inputStreet(addressModel.getStreetAddress());
		customerDetailsHomePage().inputHouseNumber(addressModel.getStreetNumber());
		customerDetailsHomePage().inputCity(addressModel.getHomeTown());
		customerDetailsHomePage().selectCountryName(addressModel.getCountryName());
		customerDetailsHomePage().inputPostCode(addressModel.getPostCode());
		customerDetailsHomePage().checkDefaultBillingAddress();
		customerDetailsHomePage().checkDefaultShippingAddress();
		customerDetailsHomePage().saveCustomer();
		waitABit(2000);
	}

	@Step
	public void clickOnLeadSettings() {
		customerDetailsHomePage().clickOnLeadSettings();
	}

	public void dismissPopUp() {
		navigationPage().dismissPopUp();
	}

	public StylistDataModel grabLeadSettingsData() {
		return leadSettingsPage().grabValidationFields();
	}

	public String extractEmailConfirmationStatus() {
		return customerDetailsHomePage().extractEmailConfirmationStatus();
	}

	public String extractJewelryBonusValue() {
		return customerDetailsHomePage().extractJewelryBonusValue();
	}

	public String extractEmailConfirmationStatusWithoutLabel() {
		return customerDetailsHomePage().extractEmailConfirmationStatusWithoutLabel();
	}

	public String extractCustomerType() {
		return customerDetailsHomePage().extractCustomerType();
	}

	@Step
	public String extractCustomerIncrementId() {
		return customerDetailsHomePage().extractCustomerIncrementId();
	}

	@Step
	public void deleteCustomer() {
		customerDetailsHomePage().deleteCustomer();
	}

	public StylistPropertiesModel grabCustomerConfiguration() {
		StylistPropertiesModel stylistModel = new StylistPropertiesModel();
		stylistModel.setType(extractCustomerType());
		stylistModel.setStatus(extractEmailConfirmationStatusWithoutLabel());
		stylistModel.setJewelryreceived(extractJewelryBonusValue());
		return stylistModel;
	}

	@Step
	public RegistrationActivationDateModel grabStylistRegistrationAndConfirmationDates() {
		RegistrationActivationDateModel datesModel = new RegistrationActivationDateModel();
		datesModel.setRegistrationDate(customerDetailsHomePage().extractRegistrationDate());
		datesModel.setConfirmationDate(customerDetailsHomePage().extractConfirmationDate());

		return datesModel;

	}

	@Step
	public void clickOnRewardsPointsTab() {
		customerDetailsHomePage().clickOnRewardsPointsTab();
	}

	@Step
	public RewardPointsOfStylistModel getRewardPointsOfStylistModel() {
		return customerDetailsHomePage().getRewardPointsOfStylistModel();
	}

	@StepGroup
	public void addJewelryAndFourthyDiscountBonusToRegularCustomer() {
		customerDetailsHomePage().clickOnRewardsPointsTab();
		customerDetailsHomePage().selectRewardPointstype("Schmuckbonus (EUR)");
		customerDetailsHomePage().typeRewardsPointsValue("10000");
		customerDetailsHomePage().saveAndContinueEdit();
		customerDetailsHomePage().verifySaveCustomerSuccessMessage();
		customerDetailsHomePage().selectRewardPointstype("Forty Discounts");
		customerDetailsHomePage().typeRewardsPointsValue("10000");
		customerDetailsHomePage().saveAndContinueEdit();
		customerDetailsHomePage().verifySaveCustomerSuccessMessage();

	}

	@Title("Verify that stylist has the address set in backend")
	@Step
	public void verifyThatAddressExist(AddressModel addressModel) {
		customerDetailsHomePage().verifyThatAddressExist(addressModel);
	}

	@Step
	public String openProductDetails(String emailText) {

		waitABit(TimeConstants.TIME_CONSTANT);
		return getDriver().getCurrentUrl();
	}

}

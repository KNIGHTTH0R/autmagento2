package com.steps.backend;

import com.tools.constants.ConfigConstants;
import com.tools.constants.TimeConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Title;

public class BackEndSteps extends AbstractSteps {

	private static final long serialVersionUID = 6989975368757184274L;

	@Step
	public void performAdminLogin(String userName, String userPass) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();

	}

	/////////////
	@Step
	public void performStagingAdminLogin(String userName, String userPass) {
		navigate("https://staging.pippajean.com" + UrlConstants.BASE_URL_BE);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();

		waitABit(2000);
		boolean isPresent = isdismissPopUpPresent();

		if (isPresent == false) {

			waitABit(3000);
			magentoLoginPage().inputUserName(userName);
			magentoLoginPage().inputUserPassword(userPass);
			magentoLoginPage().clickOnLogin();
		}
		dismissPopUp();
	}

	@Step
	public void goToBackend() {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_BE);
	}

	@Step
	public void insertCredentials(String userName, String userPass) {
		getDriver().navigate().refresh();
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		waitABit(5000);
	}

	@Step
	public void clickOnLoginButton() throws InterruptedException {
		magentoLoginPage().clickOnLogin();
		dismissPopUp();
	}

	@Step
	public void performLogin(String userName, String userPass) {
		navigate(MongoReader.getBaseURL() + UrlConstants.BASE_URL_AUT);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		// emilian shoud adjust
		// magentoLoginPage().clickOnLogin();
	}

	// @Step
	// public void clickOnCustomers() {
	// navigationPage().selectMenuFromNavbar("Kunden", "Kunden verwalten");
	// }
	@Step
	public void openCustomersGrid() {
		navigationPage().openMenu("customer");
		navigationPage().openAllCustomers();
	}

	public void clickOnContacts() {
		navigationPage().openCustomerMenu("/admin/contact/index/");
	}

	// @Step
	// public void clickOnProducts() {
	// navigationPage().selectMenuFromNavbar("Katalog", "Produkte verwalten");
	// }
	@Step
	public void clickOnProducts() {
		navigationPage().openCustomerMenu("catalog_product");
	}

	// @Step
	// public void goToNewsletterSubribers() {
	// navigationPage().selectMenuFromNavbar("Newsletter", "Newsletter
	// Bezieher");
	// }
	@Step
	public void goToNewsletterSubribers() {
		navigationPage().openCustomerMenu("newsletter_subscriber");
	}

	// @Step
	// public void clickOnStyleParties() {
	// navigationPage().selectMenuFromNavbar("Stylecoach", "Style Parties");
	// }
	@Step
	public void clickOnStyleParties() {
		navigationPage().openCustomerMenu("party");
	}

	// @Step
	// public void clickOnStylecoachList() {
	// navigationPage().selectMenuFromNavbar("Stylecoach", "Stylecoach List");
	// }
	@Step
	public void clickOnStylecoachList() {
		navigationPage().openCustomerMenu("stylist/index/key/");
	}

	// @Step
	// public void clickOnContactList() {
	// navigationPage().selectMenuFromNavbar("Stylecoach", "Kontakte");
	// }
	@Step
	public void clickOnContactList() {
		navigationPage().openCustomerMenu("contact/index/key/");
	}

	// @Step
	// public void clickOnShoppingCartPriceRules() {
	// navigationPage().selectMenuFromNavbar("Promotionen", "Warenkorb
	// Preisgebote");
	// }
	@Step
	public void clickOnShoppingCartPriceRules() {
		navigationPage().openCustomerMenu("promo_quote");
	}

	// @Step
	// public void clickOnSalesOrders() {
	// navigationPage().selectMenuFromNavbar("Verkäufe", "Aufträge");
	// }
	@Step
	public void clickOnSalesOrders() {
		navigationPage().openCustomerMenu("sales_order");
	}

	@Step
	public void clickOnSalesInvoiceOrders() {
		navigationPage().openCustomerMenu("sales_invoice");
	}

	@Step
	public void clickOnSalesShipmentOrders() {
		navigationPage().openCustomerMenu("sales_shipment");
	}

	@Step
	public void clickOnSalesCreditOrders() {
		navigationPage().openCustomerMenu("sales_creditmemo");
	}

	// @Step
	// public void clickOnCreditMemo() {
	// navigationPage().selectMenuFromNavbar("Verkäufe", "Gutschriften");
	// }
	@Step
	public void clickOnCreditMemo() {
		navigationPage().openCustomerMenu("sales_creditmemo");
	}

	@Step
	public void clickOnTermPurchaseGrid() {
		navigationPage().openCustomerMenu("scheduledorders");
	}

	@Step
	public void openFilters() {
		navigationPage().clickOnFilters();
	}

	@Step
	public void searchForEmail(String emailText) {
		customerListPage().inputEmailFilter(emailText);
		customerListPage().clickOnSearch();

	}

	@StepGroup
	public void searchForContactEmail(String emailText) {
		contactListPage().inputEmailFilter(emailText);
		contactListPage().clickOnSearch();
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
	public void searchRMAByOrderId(String orderId) {
		rmaListPage().inputOderId(orderId);
		rmaListPage().clickOnSearch();
		waitABit(2000);
	}

	@Step
	public void searchInvoiceByOrderId(String orderId) {
		orderListPage().inputInvoiceOderId(orderId);
		orderListPage().clickOnInvoiceSearch();
		waitABit(2000);
	}

	@Step
	public void searchShipmentByOrderId(String orderId) {
		orderListPage().inputShipmentOderId(orderId);
		orderListPage().clickOnShipmentSearch();
		waitABit(2000);
	}

	@Step
	public void searchCreditMemoByOrderId(String orderId) {
		orderListPage().inputCreditMemoOderId(orderId);
		orderListPage().clickOnCreditMemoSearch();
		waitABit(2000);
	}

	@Step
	public void openOrderDetails(String name) {
		orderListPage().openOrderDetails(name);
	}

	@Step
	public void openRMADetails(String name) {
		rmaListPage().openRMADetails(name);
	}

	@Step
	public void openInvoiceDetails(String name) {
		orderListPage().openInvoiceDetails(name);
	}

	@Step
	public void openShipmentDetails(String name) {
		orderListPage().openShipmentDetails(name);
	}

	@Step
	public void openCreditMemoDetails(String name) {
		orderListPage().openCreditMemoDetails(name);
	}

	@Step
	public void searchCreditMemoByorderId(String orderId) {
		creditMemoListPage().inputCreditMemoOrderId(orderId);
		creditMemoListPage().clickOnSearch();
		waitABit(2000);
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
	public boolean selectContact1(String emailText) {
		return contactListPage().findContactAndCheckIt(emailText);

	}

	@Step
	public boolean selectContact(String emailText) {
		return contactListPage().selectContact(emailText);

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
	public void deleteAllAdresses() {
		customerDetailsHomePage().clickOnAddressesTab();
		customerDetailsHomePage().deleteAllAdresses();
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
		customerDetailsHomePage()
				.typeEmail(customerData.getEmailName().replace(ConfigConstants.WEB_MAIL, "evozon.com"));
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

	public boolean isdismissPopUpPresent() {
		return navigationPage().isDismissPopPresent();
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

	@Step
	public void deleteContact() {
		contactListPage().deleteContact();
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

	@Step
	public void clickOnSystemConfiguration() {
		navigationPage().openCustomerMenu("system_config");
	}

	@Step
	public void goToHomePage() {
		navigationPage().goToHomePage();

	}

	@Step
	public void selectAllowedToBorrow(String allowedToBorrowOption) {
		customerDetailsHomePage().clickOnBorrowInfoTab();
		customerDetailsHomePage().selectAllowedToBorrow(allowedToBorrowOption);
		customerDetailsHomePage().saveAndContinueEdit();
	}

	public void selectTopStatus(String topStatus) {
		customerDetailsHomePage().clickOnStylecoachProfileTab();
		customerDetailsHomePage().selectTopStatus(topStatus);
		customerDetailsHomePage().saveAndContinueEdit();

	}

	public void updateKloutScore(String kloutScore) {
		customerDetailsHomePage().clickOnStylecoachProfileTab();
		customerDetailsHomePage().inputKloutScore(kloutScore);
		customerDetailsHomePage().saveAndContinueEdit();

	}

	public void updateOnlineStyleCoachStatus(String scStatus) {
		customerDetailsHomePage().clickOnStylecoachProfileTab();
		customerDetailsHomePage().selectOlineScStatus(scStatus);
		customerDetailsHomePage().saveAndContinueEdit();
	}

	public void clickOnSystemTab() {
		navigationPage().clickOnSystemTab();

	}

	@Step
	public void clickOnPippajeanStylistTab() {
		systemConfigurationPage().clickOnDesiredTab1("Stylistin");

	}

	@Step
	public void expendCustomerDistributionTab() {
		systemConfigurationPage().expendCustomerDistributionTab();

	}

	@Step
	public void selectDistributedOnSpecificSc(String string) {
		// TODO Auto-generated method stub
		systemConfigurationPage().selectDistributedOnSpecificSc(string);

	}

	@Step
	public void saveConfiguration() {
		systemConfigurationPage().saveConfiguration();

	}

	public void selectSpecifiSC(String scEmail) {
		// TODO Auto-generated method stub
		systemConfigurationPage().selectSpecifiSC(scEmail);
	}

	public void clickOnRMA() {
		navigationPage().selectSubmenuLevel2("/rma/");

	}

	// @Step
	// public void selectTermPurchseOption(String termPurchaseType) {
	// systemConfigurationPage().selectTermPurchseOption(termPurchaseType);
	// }
	//
	// @Step
	// public void inputMaxNumberOfDAys(String days) {
	// systemConfigurationPage().inputMaxNumberOfDAys(days);
	// }
	//
	// @Step
	// public void inputStartDateOfTpNotAvailablePeriod(String date) {
	// systemConfigurationPage().inputStartDateOfTpNotAvailablePeriod(date);
	// }
	//
	//
	// @Step
	// public void inputEndDateOfTpNotAvailablePeriod(String date) {
	// systemConfigurationPage().inputEndDateOfTpNotAvailablePeriod(date);
	// }
	//
	// @Step
	// public void selectDayOfWeekOption(String dayOption) {
	// systemConfigurationPage().selectDayOfWeek(dayOption);
	// }
	//
	// @Step
	// public void inputDayToDelay(String days) {
	// systemConfigurationPage().inputDaytoDelay(days);
	//
	// }
	//
	// @Step
	// public void inputDaysBetweenShipments(String daysBetweenShipment) {
	// systemConfigurationPage().inputDaysBetweenShipments(daysBetweenShipment);
	// }
	//
	// @Step
	// public void inputDayBeforeDeliverySchedule(String
	// daysBeforeDeliverySchedule) {
	// systemConfigurationPage().inputDayBeforeDeliverySchedule(daysBeforeDeliverySchedule);
	// systemConfigurationPage().saveConfiguration();
	// }

}

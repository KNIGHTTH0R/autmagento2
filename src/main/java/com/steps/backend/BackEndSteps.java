package com.steps.backend;

import net.thucydides.core.annotations.Step;

import com.tools.Constants;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.requirements.AbstractSteps;

public class BackEndSteps extends AbstractSteps {

	private static final long serialVersionUID = 6989975368757184274L;

	@Step
	public void performAdminLogin(String userName, String userPass) {

		getDriver().get(Constants.BASE_URL_BE);
		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();
	}

	@Step
	public void goToBackend() {
		getDriver().get(Constants.BASE_URL_BE);
	}

	@Step
	public void performLogin(String userName, String userPass) {

		magentoLoginPage().inputUserName(userName);
		magentoLoginPage().inputUserPassword(userPass);
		magentoLoginPage().clickOnLogin();
	}

	@Step
	public void clickOnCustomers() {
		navigationPage().clickOnCustomers();
		navigationPage().clickOnManageCustomers();
	}
	@Step
	public void clickOnStyleParties() {
		navigationPage().clickOnStyleCoach();
		navigationPage().clickOnStyleParties();
	}

	@Step
	public void clickOnSalesOrders() {
		navigationPage().clickOrdersPage();
		navigationPage().clickOnSales();
	}

	@Step
	public void searchForEmail(String emailText) {
		customerListPage().inputEmailFilter(emailText);
		customerListPage().clickOnSearch();
	}
	@Step
	public void searchOrderByName(String emailText) {
		orderListPage().inputOrderName(emailText);
		orderListPage().clickOnSearch();
	}

	@Step
	public void openOrderDetails(String emailText) {
		orderListPage().openOrderDetails(emailText);
	}
	
	@Step
	public String openCustomerDetails(String emailText) {
		customerListPage().openCustomerDetails(emailText);
		waitABit(Constants.TIME_CONSTANT);
		return getDriver().getCurrentUrl();
	}

	@Step
	public void clickOnLeadSettings() {
		customerDetailsHomePage().clickOnLeadSettings();
	}

	// not present in report
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

}

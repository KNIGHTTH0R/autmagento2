package com.steps.backend;

import net.thucydides.core.annotations.Step;

import com.tools.Constants;
import com.tools.data.StylistDataModel;
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
	public void clickOnCustomers() {
		navigationPage().clickOnCustomers();
		navigationPage().clickOnManageCustomers();
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
	public void openCustomerDetails(String emailText) {
		customerListPage().openCustomerDetails(emailText);
	}

	@Step
	public void clickOnLeadSettings() {
		customerDetailsHomePage().clickOnLeadSettings();
	}

	// not present in report
	public void dismissPopUp() {
		navigationPage().dismissPopUp();
	}

	@Step
	public StylistDataModel grabLeadSettingsData() {
		return leadSettingsPage().grabValidationFields();
	}

	@Step
	public String extractEmailConfirmationStatus() {
		return customerDetailsHomePage().extractEmailConfirmationStatus();
	}

}

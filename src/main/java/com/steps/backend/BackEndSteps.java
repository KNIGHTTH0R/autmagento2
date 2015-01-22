package com.steps.backend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.Constants;
import com.tools.data.OrderTotalsModel;
import com.tools.data.StylistDataModel;
import com.tools.data.backend.OrderItemModel;

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
	public void redirectToManageCustomers() {
		navigationPage().clickOnCustomers();

		// @SuppressWarnings("unused")
		// String url = navigationPage().getManageCustomersPage();
		// getDriver().get(url);
	}

	@Step
	public void redirectToSalesOrders() {
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

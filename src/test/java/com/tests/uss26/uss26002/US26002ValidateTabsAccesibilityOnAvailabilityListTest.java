package com.tests.uss26.uss26002;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.AvailabilityReportSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US26.2 Verify products in mobile version(desktop) of availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_2.class)
@RunWith(SerenityRunner.class)
public class US26002ValidateTabsAccesibilityOnAvailabilityListTest extends BaseTest {

	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public AvailabilityReportSteps availabilitySteps;

	// private String stylistUsername, stylistPassword;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void us26002ValidateTabsAccesibilityOnAvailabilityListTest() throws IOException, ParseException {
		// frontEndSteps.performLogin(stylistUsername, stylistPassword);
		
		headerSteps.navigate("https://staging-availability.pippajean.com/?store=de_lang_de");
		headerSteps.waitABit(3000);
		// validate back in stock tab
		availabilitySteps.clickOnBackInStockTabProductsTab();
		availabilitySteps.verifyIfBackInStockPoductsTabIsSelected();
		availabilitySteps.verifyReportIsOpen();
	
		// less than 20 items
		availabilitySteps.clickOnLessThenXProductsTab();
		availabilitySteps.verifyIfLessThenXPoductsTabIsSelected();
		availabilitySteps.verifyReportIsOpen();
		// validate temporarly not available tab
		availabilitySteps.clickOnTemporarlyNotAvailablePoductsTab();
		availabilitySteps.verifyIfTemporarlyNotAvailablePoductsTabIsSelected();
		availabilitySteps.verifyReportIsOpen();
		// availabilitySteps.reloadReports();

		// validate not longer available tab
		availabilitySteps.clickOnNotLongerAvailableProductsTab();
		availabilitySteps.verifyIfNotLongerAvailablePoductsTabIsSelected();
		availabilitySteps.verifyReportIsOpen();
		
		// Stock report

		availabilitySteps.clickOnStockReportTabProductsTab();
		availabilitySteps.verifyIfStockPoductsTabIsSelected();
		availabilitySteps.verifyReportIsOpen();

	}
}

package com.tests.uss22;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ComissionRestCalls;
import com.steps.backend.BackEndSteps;
import com.steps.backend.customer.CustomerDetailsBackendSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.steps.external.commission.CommissionReportSteps;
import com.tests.BaseTest;
import com.tools.data.backend.IpModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.requirements.Application;
import com.workflows.commission.StylecoachPerformanceValidationWorkflow;

@WithTag(name = "US17", type = "backend")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US22001VerifySCPerformanceNewIpLogicBackendTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public StylecoachPerformanceValidationWorkflow stylecoachPerformanceValidationWorkflow;
	@Steps
	public CommissionReportSteps commissionReportSteps;
	@Steps
	public CustomerDetailsBackendSteps customerDetailsBackendSteps;

	private IpModel ipModel;
	private CommissionStylistModel commissionStylistModel;
	private LoungeIpPerformanceModel expectedLoungeIpPerformanceModel;
	private LoungeIpPerformanceModel grabbedLoungeIpPerformanceModel;

	@Before
	public void setUp() throws Exception {

		commissionStylistModel = ComissionRestCalls.getStylistInfo("1835");
		backEndSteps.navigate(UrlConstants.COMMISSION_REPORTS_URL);
		ipModel = commissionReportSteps.closeLastMonthAndGetCurrentMonthIps();
		expectedLoungeIpPerformanceModel = stylecoachPerformanceValidationWorkflow.populateLoungeIpPerformanceModel(ipModel, commissionStylistModel);
	}

	@Test
	public void us17003VerifyThatOldStylistWasDeactivatedTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("mihaialexandrubarta@gmail.com");
		backEndSteps.openCustomerDetails("mihaialexandrubarta@gmail.com");
		customerDetailsBackendSteps.clickOnPerformanceTab();
		grabbedLoungeIpPerformanceModel = customerDetailsBackendSteps.grabSCPerformanceIpLogicAdmin();

		stylecoachPerformanceValidationWorkflow.validatePerformanceValues(expectedLoungeIpPerformanceModel, grabbedLoungeIpPerformanceModel);

	}

}

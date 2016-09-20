package com.tests.uss22;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ComissionRestCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.customer.CustomerDetailsBackendSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.steps.external.commission.CommissionReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.IpModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.commission.StylecoachPerformanceValidationWorkflow;

@WithTag(name = "US22.1 Verify SC Performance based on new IP logic on backend and frontend", type = "Scenarios")
@Story(Application.NewIpLogicStylecoachPerformance.US22_1.class)
@RunWith(SerenityRunner.class)
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
	@Steps
	public CustomVerification customVerifications;

	private IpModel ipModel;
	private CommissionStylistModel commissionStylistModel;
	private LoungeIpPerformanceModel expectedLoungeIpPerformanceModel;
	private LoungeIpPerformanceModel grabbedLoungeIpPerformanceModel;
	private String email;
	private String stylistId;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_22_FOLDER + File.separator + "us22001.properties");
			prop.load(input);
			email = prop.getProperty("email");
			stylistId = prop.getProperty("stylistId");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		MongoConnector.cleanCollection(getClass().getSimpleName());

		backEndSteps.navigate(UrlConstants.COMMISSION_URL);
		commissionReportSteps.loginAsAdmin();
		commissionReportSteps.selectMenu(UrlConstants.COMMISSION_REPORTS);
		ipModel = commissionReportSteps.closeLastMonthAndGetCurrentMonthIps(stylistId);
		commissionStylistModel = ComissionRestCalls.getStylistInfo(stylistId);
		expectedLoungeIpPerformanceModel = stylecoachPerformanceValidationWorkflow.populateLoungeIpPerformanceModel(ipModel, commissionStylistModel);
	}

	@Test
	public void us22001VerifySCPerformanceNewIpLogicBackendTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		grabbedLoungeIpPerformanceModel = customerDetailsBackendSteps.grabSCPerformanceIpLogicAdmin();
		stylecoachPerformanceValidationWorkflow.validatePerformanceValues(expectedLoungeIpPerformanceModel, grabbedLoungeIpPerformanceModel);
		customVerifications.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveLoungeIpPerformanceModel(expectedLoungeIpPerformanceModel, getClass().getSimpleName());
	}

}

package com.tests.uss22;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

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
import com.tools.data.backend.IpModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoWriter;
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

		commissionStylistModel = ComissionRestCalls.getStylistInfo(stylistId);
		backEndSteps.navigate(UrlConstants.COMMISSION_REPORTS_URL);
		ipModel = commissionReportSteps.closeLastMonthAndGetCurrentMonthIps();
		expectedLoungeIpPerformanceModel = stylecoachPerformanceValidationWorkflow.populateLoungeIpPerformanceModel(ipModel, commissionStylistModel);
	}

	@Test
	public void us22001VerifySCPerformanceNewIpLogicBackendTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		customerDetailsBackendSteps.clickOnPerformanceTab();
		grabbedLoungeIpPerformanceModel = customerDetailsBackendSteps.grabSCPerformanceIpLogicAdmin();

		stylecoachPerformanceValidationWorkflow.validatePerformanceValues(expectedLoungeIpPerformanceModel, grabbedLoungeIpPerformanceModel);

	}

	@After
	public void saveData() {
		MongoWriter.saveLoungeIpPerformanceModel(expectedLoungeIpPerformanceModel, getClass().getSimpleName());
	}

}

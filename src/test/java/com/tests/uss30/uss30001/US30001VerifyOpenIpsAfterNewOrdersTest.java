package com.tests.uss30.uss30001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.IpReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.generalCalculation.IpReportCalculation;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.reports.IpReportValidationWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US30.1 Check products in availability report", type = "Scenarios")
@Story(Application.IpReport.US30_1.class)
@RunWith(SerenityRunner.class)
public class US30001VerifyOpenIpsAfterNewOrdersTest extends BaseTest {

	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public IpReportsSteps ipReportsSteps;
	@Steps
	public IpReportValidationWorkflow ipReportValidationWorkflow;

	List<TermPurchaseIpModel> ipModelList;
	TermPurchaseIpModel grabbedIpModel;
	TermPurchaseIpModel expectedIpModel;
	private String stylistUsername, stylistPassword;

	@Before
	public void setUp() throws Exception {

		ipModelList = new ArrayList<TermPurchaseIpModel>();
		ipModelList.add(MongoReader.getIpModel("US30001GetInitialOpenIps").get(0));
		ipModelList.add(MongoReader.getIpModel("US8007CustomerBuyWithTpTest").get(0));
		ipModelList.add(MongoReader.getIpModel("US9004PlaceHostOrderWithTpTest").get(0));
		ipModelList.add(MongoReader.getIpModel("US11007PartyHostBuysForCustomerTpTest").get(0));

		expectedIpModel = IpReportCalculation.calculateTermPurchaseIps(ipModelList);

		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_IP_SCRIPT_JOB_URL, EnvironmentConstants.USERNAME_JENKINS_COMM,
				EnvironmentConstants.PASSWORD_JENKINS_COMM);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_30_FOLDER + File.separator + "us30001.properties");
			prop.load(input);
			stylistUsername = prop.getProperty("username");
			stylistPassword = prop.getProperty("password");

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

	}

	@Test
	public void us30001VerifyOpenIpsAfterNewOrdersTest() throws Exception {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnIpReports();
		grabbedIpModel = ipReportsSteps.grabIpsInfo();
		ipReportValidationWorkflow.validateIps(expectedIpModel, grabbedIpModel);

	}
}

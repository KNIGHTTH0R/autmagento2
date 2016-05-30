package com.tests.uss29;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
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
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.env.constants.FilePaths;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US29.1 Check ips for current and next month received for TP orders in ip report", type = "Scenarios")
@Story(Application.IpReport.US29_1.class)
@RunWith(SerenityRunner.class)
public class US29001GetInitialOpenIps extends BaseTest {

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

	TermPurchaseIpModel ipModel;
	private String stylistUsername, stylistPassword;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_29_FOLDER + File.separator + "us29001.properties");
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
	public void us29001GetInitialOpenIps() throws Exception {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnIpReports();
		ipModel = ipReportsSteps.grabIpsInfo();

		ApacheHttpHelper.sendGet(JenkinsConstants.IMPORT_ALL_JOB);
	}

	@After
	public void tearDown() {
		MongoWriter.saveIpModel(ipModel, getClass().getSimpleName());
	}
}

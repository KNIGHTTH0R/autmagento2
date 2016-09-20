package com.tests.uss33;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.TeamReportSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;


@WithTag(name = "US33.1 Team report scenarios", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US33001TeamReportTest extends BaseTest {
	
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public TeamReportSteps teamReportSteps;
	
	
	
	private String username, password;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");

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
	public void us33001TeamReportTest() {
		frontEndSteps.performLogin(username, password);
		
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnTeamReports();
		//teamReportSteps.searchInput("mos");
		teamReportSteps.clickTeamTab();
		teamReportSteps.clickStylePartyTab();
		teamReportSteps.clickTakeOffPhaseTab();
		teamReportSteps.selectPagination("100");
		teamReportSteps.clickTeamTab();
		teamReportSteps.selectMonth("2016-SEP");
	}
	
}

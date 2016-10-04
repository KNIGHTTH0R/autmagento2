package com.tests.uss33;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.TeamReportSteps;
import com.tests.BaseTest;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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

	CommissionStylistModel expectedModel1;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void us33001TeamReportTest() {

		frontEndSteps.performLogin("irina.neagu@evozon.com", "irina1");

		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnTeamReports();
		// teamReportSteps.selectPagination("100");
		teamReportSteps.clickTeamTab();
		teamReportSteps.selectMonth("2016-Aug");
		teamReportSteps.getTeamReportTeamModel();
		teamReportSteps.clickStylePartyTab();
		teamReportSteps.getTeamReportPartyModel();
		teamReportSteps.clickTakeOffPhaseTab();
		teamReportSteps.getTeamReportTakeOffPhaseModel();

	}

}

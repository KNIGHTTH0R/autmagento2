package com.tests.uss33;

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

import com.connectors.http.ComissionRestCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.connectors.http.PartyMagentoCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.TeamReportSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.DBStylistPartyModel;
import com.tools.generalCalculation.CommissionService;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

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
	private TeamReportTeamTabModel teamReport = new TeamReportTeamTabModel();

	private String username, password;

	@Before
	public void setUp() throws Exception {

		List<CommissionStylistModel> allStylists = ComissionRestCalls.getStylistListInfo();
		List<CommissionStylistModel> level1List = CommissionService.getStylistsFromLevel(allStylists, "1030", 1);
		List<CommissionStylistModel> level2List = CommissionService.getStylistsFromLevel(allStylists, "1030", 2);
		List<CommissionStylistModel> level3List = CommissionService.getStylistsFromLevel(allStylists, "1030", 3);

		List<TeamReportTeamTabModel> allTeamTabStylists = new ArrayList<TeamReportTeamTabModel>();

		for (CommissionStylistModel commissionStylistModel : level1List) {

			int partiesHeld = 0;
			int partiesPlanned = 0;

			TeamReportTeamTabModel teamReportModel = new TeamReportTeamTabModel();

			teamReportModel.setNewStylist(commissionStylistModel.getName());
			// do the rest

			List<DBStylistPartyModel> partyList = PartyMagentoCalls.getPartyList(commissionStylistModel.getId());
			for (DBStylistPartyModel party : partyList) {
				if (DateUtils.isDateBeetween(party.getPartyDate(), "2016-09-01 00:00:00", "2016-09-30 23:59:59",
						"yyyy-MM-dd HH:mm:ss")) {
					partiesPlanned++;
					List<DBOrderModel> ordersList = OrdersInfoMagentoCalls.getPartyOrdersList(party.getPartyId());
					for (DBOrderModel order : ordersList) {
						if (Double.parseDouble(order.getGrandTotal()) > 0) {
							partiesHeld++;
						}
					}
				}
			}

			allTeamTabStylists.add(teamReportModel);
		}


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
		// frontEndSteps.performLogin("irina.neagu@evozon.com", "irina1");

		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnTeamReports();
		teamReportSteps.searchInput("roberto");
		teamReportSteps.clickTeamTab();
//		teamReportSteps.clickStylePartyTab();
//		teamReportSteps.clickTakeOffPhaseTab();
		teamReportSteps.selectPagination("100");
		teamReportSteps.clickTeamTab();
	//	teamReportSteps.selectMonth("2016-Apr");
		
		teamReportSteps.getTeamReportTeamModel();
		teamReportSteps.clickStylePartyTab();
		teamReportSteps.getTeamReportPartyModel();
		teamReportSteps.clickTakeOffPhaseTab();
		teamReportSteps.getTeamReportTakeOffPhaseModel();
		
	}

}

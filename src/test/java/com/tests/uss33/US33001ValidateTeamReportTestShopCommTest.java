package com.tests.uss33;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

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
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.TeamReportModel;
import com.tools.data.TeamReportPartyTabModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.data.TeamReportTotalsModel;
import com.tools.generalCalculation.TeamReportCalculations;
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
public class US33001ValidateTeamReportTestShopCommTest extends BaseTest {

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
	@Steps
	public CustomVerification customVerification;

	Map<String, List<TeamReportModel>> expectedTeamMap = new HashMap<String, List<TeamReportModel>>();

	private String username;
	private String password;
	private String stylistId;
	private String reportMonth;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_33_FOLDER + File.separator + "us33001.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");
			stylistId = prop.getProperty("stylistId");
			reportMonth = prop.getProperty("reportMonth");

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

		List<TeamReportModel> teamReportList = TeamReportCalculations.getTeamReportListShop(stylistId, reportMonth);

		// for java 1.8 or above solution
		// teamReportList.stream().collect(Collectors.groupingBy(TeamReportModel::getLevel));

		// java 1.7 solution
		for (TeamReportModel model : teamReportList) {
			List<TeamReportModel> currentList = expectedTeamMap.get(model.getLevel());
			if (currentList == null) {
				expectedTeamMap.put(model.getLevel(), new ArrayList<TeamReportModel>(Arrays.asList(model)));
			} else {
				currentList.add(model);
			}
		}
	}

	@Test
	public void us33001ValidateTeamReportTestShopCommTest() throws ParseException {

		frontEndSteps.performLogin(username, password);

	/*	if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());*/
		
		headerSteps.redirectToTeamReport();
		teamReportSteps.selectPagination("100");
		teamReportSteps.selectMonth(DateUtils.parseDate(reportMonth, "yyyy-MM-dd HH:mm:ss", "yyyy-MMM",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build()));

		for (Map.Entry<String, List<TeamReportModel>> entry : expectedTeamMap.entrySet()) {

			TeamReportTotalsModel calculatedTotals = TeamReportCalculations.calculateTotals(entry.getValue());
			TeamReportTotalsModel grabbedTotals = new TeamReportTotalsModel();

			teamReportSteps.selectScLevel(entry.getKey());
			teamReportSteps.clickTeamTab();
			List<TeamReportTeamTabModel> grabbedTeamModel = teamReportSteps.getTeamReportTeamModel();
			grabbedTotals = teamReportSteps.getTeamReportTeamTotals(grabbedTotals);

			teamReportSteps.clickStylePartyTab();
			List<TeamReportPartyTabModel> grabbedPArtyModel = teamReportSteps.getTeamReportPartyModel();
			grabbedTotals = teamReportSteps.getTeamReportPartyTotals(grabbedTotals);

			teamReportSteps.clickTakeOffPhaseTab();
			List<TeamReportTakeOffPhaseModel> takeOffPhaseModel = teamReportSteps.getTeamReportTakeOffPhaseModel();
			grabbedTotals = teamReportSteps.getTeamReportTakeOffTotals(grabbedTotals);

			teamReportSteps.validateTeamReportTeamTab(entry.getValue(), grabbedTeamModel);
			teamReportSteps.validateTeamReportPartyTab(entry.getValue(), grabbedPArtyModel);
			teamReportSteps.validateTeamReportTakeOffPhaseTab(entry.getValue(), takeOffPhaseModel);
			teamReportSteps.validateTotals(calculatedTotals, grabbedTotals);
		
		}

		customVerification.printErrors();
	}
}

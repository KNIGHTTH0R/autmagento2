package com.steps.frontend.reports;

import java.util.List;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.TeamReportModel;
import com.tools.data.TeamReportPartyTabModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class TeamReportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;
	@Step
	public void searchInput(String searchKey) {
		teamReportPage().searchInput(searchKey);

	}

	@Step
	public void clickTeamTab() {
		teamReportPage().clickTeamTab();

	}

	@Step
	public void clickStylePartyTab() {
		teamReportPage().clickStylePartyTab();

	}

	@Step
	public void clickTakeOffPhaseTab() {
		teamReportPage().clickTakeOffPhaseTab();

	}

	@Step
	public void selectPagination(String select) {
		teamReportPage().selectPagination(select);
	}

	@Step
	public void selectScLevel(String levelNumber) {
		teamReportPage().selectScLevel(levelNumber);
	}

	@Step
	public void selectMonth(String month) {
		teamReportPage().selectMonthForReport(month);
	}

	@Step
	public List<TeamReportTeamTabModel> getTeamReportTeamModel() {
		return teamReportPage().getTeamReportTeamModel();

	}

	@Step
	public List<TeamReportPartyTabModel> getTeamReportPartyModel() {
		return teamReportPage().getTeamReportPartyModel();

	}

	@Step
	public List<TeamReportTakeOffPhaseModel> getTeamReportTakeOffPhaseModel() {
		return teamReportPage().getTeamReportTakeOffPhaseModel();

	}

	public void validateTeamReportTeamTab(List<TeamReportModel> expectedList,
			List<TeamReportTeamTabModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportModel stylist : expectedList) {
			TeamReportTeamTabModel compare = findTeamStylist(stylist.getStyleCoachId(), grabbedList);

			if (compare.getStyleCoachName() != null) {
				matchStylistName(stylist.getStyleCoachName(), compare.getStyleCoachName());
				validateStartDate(stylist.getActivationDate(), compare.getActivationDate());
				validateIp(stylist.getIp(), compare.getIp());
				validateTp(stylist.getTqv(), compare.getTqv());
				validateCareerLevelForThisMonth(stylist.getCarrerLevelThisMonth(), compare.getCarrerLevelThisMonth());
				validateCareerLevelForLastMonth(stylist.getCarrerLevelLastMonth(), compare.getCarrerLevelLastMonth());
				validatePayLevel(stylist.getPayLevel(), compare.getPayLevel());
				validateIpIncludingNewSc(stylist.getIpNewRecruited(), compare.getIpNewRecruited());
				validateNewScs(stylist.getNewStylist(), compare.getNewStylist());

			} else {
				Assert.assertTrue("Failure: Could not validate all stylists in the list", compare != null);
			}
			int index = grabbedList.indexOf(compare);
			if (index > -1) {

				grabbedList.remove(index);

			}
		}
		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);
	}

	public void validateTeamReportPartyTab(List<TeamReportModel> expectedList,
			List<TeamReportPartyTabModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportModel stylist : expectedList) {
			TeamReportPartyTabModel compare = findPartyStylist(stylist.getStyleCoachId(), grabbedList);
			if (compare.getStylistName() != null) {
				matchStylistName(stylist.getStyleCoachName(), compare.getStylistName());
				validateIpsThisMonth(stylist.getIpThisMonth(), compare.getIpThisMonth());
				validateIpsLastMonth(stylist.getIpLastMonth(), compare.getIpLastMonth());
				validatePartiesHeld(stylist.getPartiesHeld(), compare.getPartiesHeld());
				validatePartiesPlanned(stylist.getPartiesPlanned(), compare.getPartiesPlanned());
				validateUpcomingParties(stylist.getPartiesUpcoming(), compare.getPartiesUpcoming());
				validateIpsPerParty(stylist.getRevenuePerParty(), compare.getRevenuePerParty());

			} else {
				Assert.assertTrue("Failure: Could not validate all stylists in the list", compare != null);
			}
			int index = grabbedList.indexOf(compare);
			if (index > -1) {
				grabbedList.remove(index);
			}
		}
		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);
	}

	public void validateTeamReportTakeOffPhaseTab(List<TeamReportModel> expectedList,
			List<TeamReportTakeOffPhaseModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportModel stylist : expectedList) {
			TeamReportTakeOffPhaseModel compare = findTofStylist(stylist.getStyleCoachId(), grabbedList);
			if (compare.getStylistName() != null) {
				matchStylistName(stylist.getStyleCoachName(), compare.getStylistName());
				validateStartDate(stylist.getActivationDate(), compare.getActivationDate());
				validateTopEndDate(stylist.getTakeOffPhaseEndDate(), compare.getTakeOffPhaseEndDate());
				validateLeftDays(stylist.getDaysLeft(), compare.getDaysLeft());
				validateIpsOnTakeOffPhase(stylist.getIp(), compare.getIp());
				validateStylistWon(stylist.getNewStylistTop(), compare.getNewStylistTop());

			} else {
				Assert.assertTrue("Failure: Could not validate all stylists in the list", compare != null);
			}
			int index = grabbedList.indexOf(compare);
			if (index > -1) {

				grabbedList.remove(index);

			}
		}
		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);
	}

	private TeamReportTeamTabModel findTeamStylist(String id, List<TeamReportTeamTabModel> grabbedList) {
		TeamReportTeamTabModel result = new TeamReportTeamTabModel();
		theFor: for (TeamReportTeamTabModel item : grabbedList) {
			if (item.getStyleCoachId().contains(id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	private TeamReportPartyTabModel findPartyStylist(String id, List<TeamReportPartyTabModel> grabbedList) {
		TeamReportPartyTabModel result = new TeamReportPartyTabModel();
		theFor: for (TeamReportPartyTabModel item : grabbedList) {
			if (item.getStyleCoachId().contains(id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	private TeamReportTakeOffPhaseModel findTofStylist(String id, List<TeamReportTakeOffPhaseModel> grabbedList) {
		TeamReportTakeOffPhaseModel result = new TeamReportTakeOffPhaseModel();
		theFor: for (TeamReportTakeOffPhaseModel item : grabbedList) {
			if (item.getStyleCoachId().contains(id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}


	@Step
	private void matchStylistName(String stylistName, String compare) {
	}

	@Step
	private void validateSponsorName(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Sponsor name dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateStartDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Start date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIp(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ip values dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateTp(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Tp values dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateCareerLevelForThisMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Career level for this month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateCareerLevelForLastMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Career level for last month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validatePayLevel(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Pay levels dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIpIncludingNewSc(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips including new Scs dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateVacationMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Vacation month date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateNewScs(String stylistName, String compare) {
		CustomVerification
				.verifyTrue("Failure: The number of active frontliners with activated at in current month dont match: "
						+ stylistName + " - " + compare, stylistName.contentEquals(compare));
	}

	@Step
	private void validateQuitDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Quit dates dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIpsThisMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips for this month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIpsLastMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips for last month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validatePartiesHeld(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of Parties held dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validatePartiesPlanned(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of planned parties dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateUpcomingParties(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of upcoming parties dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIpsPerParty(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of Ips per party dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateStylistActivatedAt(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: The stylist activation date for team member dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateTopEndDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: TOP end date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateLeftDays(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of days remaining until TOP ends dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateIpsOnTakeOffPhase(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of Ips in the take Off phase dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	private void validateStylistWon(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of frontliners recruited in TOP dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

}

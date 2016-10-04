package com.steps.frontend.reports;

import java.util.List;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.TeamReportPartyTabModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.requirements.AbstractSteps;

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
		;

	}

	@Step
	public void selectPagination(String select) {
		teamReportPage().selectPagination(select);
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

	public void validateTeamReportTeamTab(List<TeamReportTeamTabModel> expectedList,
			List<TeamReportTeamTabModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportTeamTabModel stylist : expectedList) {
			TeamReportTeamTabModel compare = findTeamStylist(stylist.getStyleCoachName(), grabbedList);

			if (compare.getStyleCoachName() != null) {
				matchStylistName(stylist.getStyleCoachName(), compare.getStyleCoachName());
				validateSponsorName(stylist.getSponsorName(), compare.getSponsorName());
				validateStartDate(stylist.getActivationDate(), compare.getActivationDate());
				validateIp(stylist.getIp(), compare.getIp());
				validateTp(stylist.getTqv(), compare.getTqv());
				validateCareerLevelForThisMonth(stylist.getCarrerLevelThisMonth(), compare.getCarrerLevelThisMonth());
				validateCareerLevelForLastMonth(stylist.getCarrerLevelLastMonth(), compare.getCarrerLevelLastMonth());
				validatePayLevel(stylist.getPayLevel(), compare.getPayLevel());
				validateIpIncludingNewSc(stylist.getIpNewRecruited(), compare.getIpNewRecruited());
				validateNewScs(stylist.getNewStylist(), compare.getNewStylist());
				validateVacationMonth(stylist.getVacationMonth(), compare.getVacationMonth());
				validateQuitDate(stylist.getQuitDate(), compare.getQuitDate());

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

	public void validateTeamReportPartyTab(List<TeamReportPartyTabModel> expectedList,
			List<TeamReportPartyTabModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportPartyTabModel stylist : expectedList) {
			TeamReportPartyTabModel compare = findPartyStylist(stylist.getStylistName(), grabbedList);
			if (compare.getStylistName() != null) {
				matchStylistName(stylist.getStylistName(), compare.getStylistName());
				validateSponsorName(stylist.getSponsorName(), compare.getSponsorName());
				validateIpsThisMonth(stylist.getIpThisMonth(), compare.getIpThisMonth());
				validateIpsLastMonth(stylist.getIpLastMonth(), compare.getIpLastMonth());
				validatePartiesHeld(stylist.getPartiesHeld(), compare.getPartiesHeld());
				validatePartiesPlanned(stylist.getPartiesPlanned(), compare.getPartiesPlanned());
				validateUpcomingParties(stylist.getPartiesUpcoming(), compare.getPartiesUpcoming());
				validateIpsPerParty(stylist.getIpPerParty(), compare.getIpPerParty());

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

	public void validateTeamReportTakeOffPhaseTab(List<TeamReportTakeOffPhaseModel> expectedList,
			List<TeamReportTakeOffPhaseModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (TeamReportTakeOffPhaseModel stylist : expectedList) {
			TeamReportTakeOffPhaseModel compare = findTofStylist(stylist.getStylistName(), grabbedList);
			if (compare.getStylistName() != null) {
				matchStylistName(stylist.getStylistName(), compare.getStylistName());
				validateSponsorName(stylist.getSponsorName(), compare.getSponsorName());
				validateStartDate(stylist.getActivationDate(), compare.getActivationDate());
				validateTopEndDate(stylist.getTakeOffPhaseEndDate(), compare.getTakeOffPhaseEndDate());
				validateLeftDays(stylist.getDaysLeft(), compare.getDaysLeft());
				validateIpsOnTakeOffPhase(stylist.getIp(), compare.getIp());
				validateStylistWon(stylist.getNumberOfFrontliners(), compare.getNumberOfFrontliners());

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

	public TeamReportTeamTabModel findTeamStylist(String name, List<TeamReportTeamTabModel> grabbedList) {
		TeamReportTeamTabModel result = new TeamReportTeamTabModel();
		theFor: for (TeamReportTeamTabModel item : grabbedList) {
			if (item.getStyleCoachName().contains(name)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	public TeamReportPartyTabModel findPartyStylist(String name, List<TeamReportPartyTabModel> grabbedList) {
		TeamReportPartyTabModel result = new TeamReportPartyTabModel();
		theFor: for (TeamReportPartyTabModel item : grabbedList) {
			if (item.getStylistName().contains(name)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	public TeamReportTakeOffPhaseModel findTofStylist(String name, List<TeamReportTakeOffPhaseModel> grabbedList) {
		TeamReportTakeOffPhaseModel result = new TeamReportTakeOffPhaseModel();
		theFor: for (TeamReportTakeOffPhaseModel item : grabbedList) {
			if (item.getStylistName().contains(name)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	@Step
	public void matchStylistName(String stylistName, String compare) {
	}

	@Step
	public void validateSponsorName(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Sponsor name dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateStartDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Start date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIp(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ip values dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateTp(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Tp values dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateCareerLevelForThisMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Career level for this month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateCareerLevelForLastMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Career level for last month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validatePayLevel(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Pay levels dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIpIncludingNewSc(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips including new Scs dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateVacationMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Vacation month date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateNewScs(String stylistName, String compare) {
		CustomVerification
				.verifyTrue("Failure: The number of active frontliners with activated at in current month dont match: "
						+ stylistName + " - " + compare, stylistName.contentEquals(compare));
	}

	@Step
	public void validateQuitDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Quit dates dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIpsThisMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips for this month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIpsLastMonth(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Ips for last month dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validatePartiesHeld(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of Parties held dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validatePartiesPlanned(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of planned parties dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateUpcomingParties(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of upcoming parties dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIpsPerParty(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: Number of Ips per party dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateStylistActivatedAt(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: The stylist activation date for team member dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateTopEndDate(String stylistName, String compare) {
		CustomVerification.verifyTrue("Failure: TOP end date dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateLeftDays(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of days remaining until TOP ends dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateIpsOnTakeOffPhase(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of Ips in the take Off phase dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

	@Step
	public void validateStylistWon(String stylistName, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Number of frontliners recruited in TOP dont match: " + stylistName + " - " + compare,
				stylistName.contentEquals(compare));
	}

}

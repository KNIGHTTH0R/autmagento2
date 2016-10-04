package com.tools.generalCalculation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.connectors.http.ComissionRestCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.connectors.http.PartyMagentoCalls;
import com.tools.constants.ComissionConfigConstants;
import com.tools.data.TeamReportModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.DBStylistPartyModel;
import com.tools.utils.DateUtils;
import com.tools.utils.PrintUtils;

public class TeamReportCalculations {

	public static void main(String[] args) throws Exception {
		TeamReportCalculations.determineTeamReportList("1030", 2, "2016-08-15 12:00:00");
	}

	private static List<String> payedStatusesList = new ArrayList<String>(
			Arrays.asList("complete", "payment_complete", "payment_in_progress", "pending_payment_hold", "closed"));

	public static List<TeamReportModel> determineTeamReportList(String stylistId, int level, String month)
			throws Exception {

		String[] dateFields = DateUtils.getDateFields(month, "yyyy-MM-dd HH:mm:ss");
		String previousMonth = DateUtils.getPreviousMonth(month, "yyyy-MM-dd HH:mm:ss");
		String[] dateFieldsPreviousMonth = DateUtils.getDateFields(previousMonth, "yyyy-MM-dd HH:mm:ss");

		List<CommissionStylistModel> allStylists = ComissionRestCalls.getStylistListInfo(dateFields[0], dateFields[1]);
		List<CommissionStylistModel> levelStylistsList = getStylistsFromLevel(allStylists, stylistId, level);

		List<TeamReportModel> allTeamTabStylists = new ArrayList<TeamReportModel>();

		for (CommissionStylistModel commissionStylistModel : levelStylistsList) {

			int partiesHeld = 0;
			int partiesPlanned = 0;
			int upcomingParties = 0;
			double grandRevenue = 0.0000;
			double revenuePerParty = 0.0000;

			TeamReportModel teamReportModel = new TeamReportModel();

			List<DBStylistPartyModel> partyList = PartyMagentoCalls.getPartyList(commissionStylistModel.getStylistId());
			for (DBStylistPartyModel party : partyList) {

				if (party.getDeletedAt() == null && DateUtils.isDateBeetween(party.getPartyDate(),
						DateUtils.getFirstDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"),
						DateUtils.getLastDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {

					double partyRevenue = 0.0000;
					partiesPlanned++;
					List<DBOrderModel> ordersList = OrdersInfoMagentoCalls.getPartyOrdersList(party.getPartyId());
					for (DBOrderModel order : ordersList) {
						if (Double.parseDouble(order.getGrandTotal()) > 0 && isPayed(order)) {
							partyRevenue += Double.parseDouble(order.getGrandTotal());
						}
					}
					if (partyRevenue > 0) {
						partiesHeld++;
					}
					grandRevenue += partyRevenue;

				}
				if (party.getClosedAt() == null && party.getDeletedAt() == null && DateUtils.isDateBeetween(
						party.getPartyDate(), DateUtils.getCurrentDateBegining("yyyy-MM-dd HH:mm:ss"),
						DateUtils.getLastDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {

					upcomingParties++;
				}
			}
			if (partiesHeld != 0) {
				revenuePerParty = grandRevenue / partiesHeld;
			}

			int newSc = determineNewSc(commissionStylistModel.getStylistId(), allStylists,
					DateUtils.getFirstDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"),
					DateUtils.getLastDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"));

			String takeOffEnd = calculateTakeOffPhaseAndDate(commissionStylistModel.getActivatedAt(),
					"yyyy-MM-dd HH:mm:ss");
			System.out.println("### " + takeOffEnd);

			int takeOfPeriodNewSc = determineNewSc(commissionStylistModel.getStylistId(), allStylists,
					commissionStylistModel.getActivatedAt(), takeOffEnd);

			int daysLeft = DateUtils.getNumberOfDaysBeetweenTwoDates(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
					takeOffEnd, "yyyy-MM-dd HH:mm:ss");

			String tqv = calculateTqv(commissionStylistModel.getIp(), commissionStylistModel.getTeamPoints());

			CommissionStylistModel commStylistModelPreviousMonth = ComissionRestCalls.getStylistInfo(
					commissionStylistModel.getStylistId(), dateFieldsPreviousMonth[0], dateFieldsPreviousMonth[1]);

			teamReportModel.setStyleCoachName(commissionStylistModel.getName());
			teamReportModel.setActivationDate(
					DateUtils.parseDate(commissionStylistModel.getActivatedAt(), "yyyy-MM-dd HH:mm:ss", "dd/MM/yy"));
			teamReportModel.setTakeOffPhaseEndDate(DateUtils.parseDate(takeOffEnd, "yyyy-MM-dd HH:mm:ss", "dd/MM/yy"));
			teamReportModel.setDaysLeft(String.valueOf(daysLeft));
			teamReportModel.setIpTop(commissionStylistModel.getIpTop());
			teamReportModel.setNewStylistTop(String.valueOf(takeOfPeriodNewSc));
			teamReportModel.setIpThisMonth(commissionStylistModel.getIp());
			teamReportModel.setIpLastMonth(commStylistModelPreviousMonth.getIp());
			teamReportModel.setPartiesHeld(String.valueOf(partiesHeld));
			teamReportModel.setPartiesPlanned(String.valueOf(partiesPlanned));
			teamReportModel.setPartiesUpcoming(String.valueOf(upcomingParties));
			teamReportModel.setRevenuePerParty(String.valueOf(revenuePerParty));
			teamReportModel.setIp(commissionStylistModel.getIp());
			teamReportModel.setTqv(tqv);
			teamReportModel.setCarrerLevelThisMonth(commissionStylistModel.getCareer());
			teamReportModel.setCarrerLevelThisMonth(commStylistModelPreviousMonth.getCareer());
			teamReportModel.setPayLevel(commissionStylistModel.getPayLevelName());
			teamReportModel.setIpNewRecruited(commissionStylistModel.getIpNewFl());
			teamReportModel.setNewStylist(String.valueOf(newSc));

			allTeamTabStylists.add(teamReportModel);
		}
		PrintUtils.printTeamReportModelList(allTeamTabStylists);
		return allTeamTabStylists;
	}

	public static List<CommissionStylistModel> getStylistsFromLevel(List<CommissionStylistModel> allStylists,
			String stylistId, int level) throws Exception {

		List<CommissionStylistModel> levelStylists = new ArrayList<CommissionStylistModel>();

		for (CommissionStylistModel stylist : allStylists) {
			String[] ancestors = stylist.getAncestors().split(",");
			if (ArrayUtils.indexOf(ancestors, stylistId) == level - 1) {
				levelStylists.add(stylist);
			}
		}
		return levelStylists;
	}

	private static boolean isPayed(DBOrderModel model) {
		boolean found = false;
		for (String status : payedStatusesList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	public static int determineNewSc(String stylistId, List<CommissionStylistModel> allStylists, String startDate,
			String endDate) throws ParseException {

		int newSc = 0;
		for (CommissionStylistModel stylist : allStylists) {
			if (stylist.getStatus().contentEquals("active") && stylist.getParentStylistId().contentEquals(stylistId)
					&& DateUtils.isDateBeetween(stylist.getActivatedAt(), startDate, endDate, "yyyy-MM-dd HH:mm:ss")) {
				newSc++;
			}
		}
		return newSc;
	}

	public static String calculateTakeOffPhaseAndDate(String activationDate, String format) throws ParseException {

		return DateUtils.addDaysToAAGivenDate(activationDate, format, ComissionConfigConstants.TAKE_OFF_PERIOD);
	}

	public static String calculateTqv(String ip, String tp) {
		return String.valueOf(Integer.parseInt(ip) + Integer.parseInt(tp));
	}
}

package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.connectors.http.ComissionRestCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.connectors.http.PartyMagentoCalls;
import com.connectors.http.StylistListMagentoCalls;
import com.steps.frontend.HeaderSteps;
import com.tools.constants.ComissionConfigConstants;
import com.tools.data.IpCalculationModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.TeamReportModel;
import com.tools.data.TeamReportTotalsModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.data.soap.DBStylistPartyModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;
import com.tools.utils.PrintUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class TeamReportCalculations extends AbstractSteps {

	/*
	 * public static void main(String[] args) throws Exception {
	 * List<TeamReportModel> list =
	 * TeamReportCalculations.getTeamReportList("1030", "2016-10-15 12:00:00");
	 * }
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<String> payedStatusesList = new ArrayList<String>(
			Arrays.asList("complete", "payment_complete", "payment_in_progress", "pending_payment_hold", "closed"));

	/**
	 * returns the list of all stylists (List<TeamReportModel>) containing all
	 * the data needed for validating team report
	 * 
	 * level (1- level 1 , 2 - level 2 , 3- level 3, 4 - all levels ) the month
	 * is given in the format yyyy-MM-dd HH:mm:ss, but only the year and month
	 * is essential
	 * 
	 * @param stylistId
	 * @param level
	 * @param month
	 * @return
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		/*
		 * List<CommissionStylistModel> allStylists =
		 * ComissionRestCalls.getStylistListInfo("2016", "01");
		 * System.out.println("list size "+allStylists.size());
		 * 
		 * List<CommissionStylistModel> allStylists =
		 * ComissionRestCalls.getStylistListInfo("2018", "4");
		 * 
		 * List<CommissionStylistModel> levelStylistsList =
		 * getStylistsForAllLevels(allStylists, "1");
		 * 
		 * for (CommissionStylistModel sc : levelStylistsList) {
		 * System.out.println(sc.getStylistId() + " - "+ sc.getLevel());
		 * 
		 * }
		 */

		List<TeamReportModel> list = TeamReportCalculations.getTeamReportListShop("1", "2018-05-03 12:00:00");

		// System.out.println("salut");

		/*
		 * HashMap<String, String> hm = new HashMap<String, String>();
		 * hm.put("33", "235");
		 * 
		 * 
		 * System.out.println(hm.get("33"));
		 */
		// System.out.println(hm.containsKey("33"));

		// System.out.println(list.size());
	}

	@Step
	public static List<TeamReportModel> getTeamReportList(String stylistId, String month) throws Exception {

		String[] dateFields = DateUtils.getDateFields(month, "yyyy-MM-dd HH:mm:ss");
		String previousMonth = DateUtils.getPreviousMonth(month, "yyyy-MM-dd HH:mm:ss");
		String[] dateFieldsPreviousMonth = DateUtils.getDateFields(previousMonth, "yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFields[0] + " " + dateFields[1]);

		List<CommissionStylistModel> allStylists = ComissionRestCalls.getStylistListInfo(dateFields[0], dateFields[1]);

		Thread.sleep(8000);

		List<CommissionStylistModel> allPreviousMonthStylists = ComissionRestCalls
				.getStylistListInfo(dateFieldsPreviousMonth[0], dateFieldsPreviousMonth[1]);

		List<CommissionStylistModel> levelStylistsList = getStylistsForAllLevels(allStylists, stylistId);
		List<TeamReportModel> allTeamReportStylists = new ArrayList<TeamReportModel>();

		for (CommissionStylistModel commissionStylistModel : levelStylistsList) {

			int partiesHeld = 0;
			int partiesPlanned = 0;
			int upcomingParties = 0;
			double grandRevenue = 0.0000;
			double revenuePerParty = 0.0000;

			TeamReportModel teamReportModel = new TeamReportModel();
			// get the list of parties for a style coach
			List<DBStylistPartyModel> partyList = PartyMagentoCalls.getPartyList(commissionStylistModel.getStylistId());
			for (DBStylistPartyModel party : partyList) {
				// if the party is placed in the given month and it's not
				// deleted,increment the
				// parties held number

				if (party.getDeletedAt() == null && DateUtils.isDateBeetween(party.getPartyDate(),
						DateUtils.getFirstDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"),
						DateUtils.getLastDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {

					double partyRevenue = 0.0000;
					partiesPlanned++;
					// if the party is placed in the given month and it's not
					// deleted,get orders(if
					// the orders has one list party with grand total > 0
					// increment the parties held number and calculate revenue
					// per party)
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
				// if the party is placed between current date and the last
				// date of the month and it's not deleted or closed,increment
				// upcoming parties
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

			int takeOfPeriodNewSc = determineNewSc(commissionStylistModel.getStylistId(), allStylists,
					commissionStylistModel.getActivatedAt(), takeOffEnd);

			int daysLeft = DateUtils.getNumberOfDaysBeetweenTwoDates(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
					DateUtils.setHourToDate(takeOffEnd, 23, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");

			String tqv = calculateTqv(commissionStylistModel.getIp(), commissionStylistModel.getTeamPoints());

			// Thread.sleep(5000);
			/*
			 * CommissionStylistModel commStylistModelPreviousMonth =
			 * ComissionRestCalls.getStylistInfo(
			 * commissionStylistModel.getStylistId(),
			 * dateFieldsPreviousMonth[0], dateFieldsPreviousMonth[1]);
			 */
			CommissionStylistModel commStylistModelPreviousMonth = grabPreviousMonthStylistData(
					commissionStylistModel.getStylistId(), allPreviousMonthStylists);

			teamReportModel.setStyleCoachId(commissionStylistModel.getStylistId());
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
			teamReportModel.setCarrerLevelThisMonth(commissionStylistModel.getCareerLevelName());
			teamReportModel.setCarrerLevelLastMonth(commStylistModelPreviousMonth.getCareerLevelName());
			teamReportModel.setPayLevel(commissionStylistModel.getPayLevelName());
			teamReportModel.setIpNewRecruited(commissionStylistModel.getIpNewFl());
			teamReportModel.setNewStylist(String.valueOf(newSc));
			teamReportModel.setLevel(commissionStylistModel.getLevel());
			teamReportModel.setCanceledDate(commissionStylistModel.getCanceledAt());
			teamReportModel.setIp30Top(String.valueOf(commissionStylistModel.getIp30Top()));
			// teamReportModel.setTopNrNewStylists(commissionStylistModel.getTopNrNewStylists());
			teamReportModel.setTopNrNewStylists(String.valueOf(takeOfPeriodNewSc));
			allTeamReportStylists.add(teamReportModel);

		}
		PrintUtils.printTeamReportModelList(allTeamReportStylists);
		return allTeamReportStylists;
	}

	private static CommissionStylistModel grabPreviousMonthStylistData(String stylistId,
			List<CommissionStylistModel> allPreviousMonthStylists) {
		List<CommissionStylistModel> result = new ArrayList<CommissionStylistModel>();

		result = allPreviousMonthStylists.stream().filter(item -> item.getStylistId().equals(stylistId))
				.collect(Collectors.toList());

		if (result.isEmpty()) {
			CommissionStylistModel model = new CommissionStylistModel();
			model.setIp("0");
			model.setCareerLevelName("n/a");
			result.add(model);
		}

		return result.get(0);
	}

	public static List<CommissionStylistModel> getStylistsForAllLevels(List<CommissionStylistModel> allStylists,
			String stylistId) throws Exception {

		List<CommissionStylistModel> levelStylists = new ArrayList<CommissionStylistModel>();

		for (CommissionStylistModel stylist : allStylists) {
			String[] ancestors = stylist.getAncestors().split(",");
			// if the index is not -1 it means that the style coach was found
			// in the ancestors array
			// because the array start from index 0, for the correct level we
			// add 1 (Ex: if the style coach is found on the first position in
			// the
			// array - position 0 - the level is 1)
			if (ArrayUtils.indexOf(ancestors, stylistId) != -1) {
				stylist.setLevel(String.valueOf(1 + ArrayUtils.indexOf(ancestors, stylistId)));
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
			/*
			 * if (stylist.getStatus().contentEquals("active") &&
			 * stylist.getParentStylistId().contentEquals(stylistId) &&
			 * DateUtils.isDateBeetween(stylist.getActivatedAt(), startDate,
			 * endDate, "yyyy-MM-dd HH:mm:ss")) { newSc++; }
			 */

			if (stylist.getParentStylistId().contentEquals(stylistId)
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

	public static String getFullNameOfAbbreviation(String abbreviation) {

		String result = "";

		switch (abbreviation) {

		case "SC":
			result = "Style Coach";
			break;

		case "DIR":
			result = "Director";
			break;

		case "PRE":
			result = "President";
			break;

		case "BSC":
			result = "Bronze";
			break;

		case "BDIR":
			result = "Bronze Director";
			break;

		case "RPRE":
			result = "Ruby President";
			break;

		case "SSC":
			result = "Silver";
			break;

		case "SDIR":
			result = "Silver Director";
			break;

		case "EPRE":
			result = "Emerald President";
			break;

		case "GSC":
			result = "Gold";
			break;

		case "GDIR":
			result = "Gold Director";
			break;

		case "DPRE":
			result = "Diamond President";
			break;

		}
		return result;
	}

	public static TeamReportTotalsModel calculateTotals(List<TeamReportModel> teamReportList) {

		int ipTotal = 0;
		int tqvTotal = 0;
		int ipNewInclTotal = 0;
		int newScTotal = 0;
		int ipLastMonthTotal = 0;
		int partiesHeldTotal = 0;
		int partiesPlannedTotal = 0;
		int partiesUpcomingTotal = 0;
		double revenuePartyTotal = 0;
		int daysLeftTotal = 0;
		int ipTakeOffTotal = 0;
		int newScTakeOffTotal = 0;
		int ip30TakeOffTotal = 0;

		TeamReportTotalsModel total = new TeamReportTotalsModel();

		for (TeamReportModel teamReportModel : teamReportList) {

			ipTotal = ipTotal + Integer.parseInt(teamReportModel.getIp());
			tqvTotal = tqvTotal + Integer.parseInt(teamReportModel.getTqv());
			ipNewInclTotal = ipNewInclTotal + Integer.parseInt(teamReportModel.getIpNewRecruited());
			newScTotal = newScTotal + Integer.parseInt(teamReportModel.getNewStylist());
			ipLastMonthTotal = ipLastMonthTotal + Integer.parseInt(teamReportModel.getIpLastMonth());
			partiesHeldTotal = partiesHeldTotal + Integer.parseInt(teamReportModel.getPartiesHeld());
			partiesPlannedTotal = partiesPlannedTotal + Integer.parseInt(teamReportModel.getPartiesPlanned());
			partiesUpcomingTotal = partiesUpcomingTotal + Integer.parseInt(teamReportModel.getPartiesUpcoming());
			revenuePartyTotal = revenuePartyTotal + Double.parseDouble(teamReportModel.getRevenuePerParty());
			daysLeftTotal = daysLeftTotal + Integer.parseInt(teamReportModel.getDaysLeft());
			ipTakeOffTotal = ipTakeOffTotal + Integer.parseInt(teamReportModel.getIpTop());
			newScTakeOffTotal = newScTakeOffTotal + Integer.parseInt(teamReportModel.getNewStylistTop());
			ip30TakeOffTotal = ip30TakeOffTotal + Integer.parseInt(teamReportModel.getIp30Top());
		}

		total.setIpTotal(String.valueOf(ipTotal));
		total.setTqvTotal(String.valueOf(tqvTotal));
		total.setIpNewInclTotal(String.valueOf(ipNewInclTotal));
		total.setNewScTotal(String.valueOf(newScTotal));
		total.setIpThisMonthTotal(String.valueOf(ipTotal));
		total.setIpLastMonthTotal(String.valueOf(ipLastMonthTotal));
		total.setPartiesHeldTotal(String.valueOf(partiesHeldTotal));
		total.setPartiesPlannedTotal(String.valueOf(partiesPlannedTotal));
		total.setPartiesUpcomingTotal(String.valueOf(partiesUpcomingTotal));
		total.setRevenuePartyTotal(String.valueOf(revenuePartyTotal));
		total.setDaysLeftTotal(String.valueOf(daysLeftTotal));
		total.setIpTakeOffTotal(String.valueOf(ipTakeOffTotal));
		total.setNewScTakeOffTotal(String.valueOf(newScTakeOffTotal));
		total.setIp30TakeOffTotal(String.valueOf(ip30TakeOffTotal));
		return total;

	}

	@Step
	public static List<TeamReportModel> getTeamReportListShop(String stylistId, String month) throws Exception {
		HashMap<String, ArrayList<IpCalculationModel>> ipCorrection = new HashMap<String, ArrayList<IpCalculationModel>>();
		List<IpCalculationModel> ipCorrectionList = new ArrayList<IpCalculationModel>();
		IpCalculationModel ipValue = new IpCalculationModel();
		IpCalculationModel ipValue2 = new IpCalculationModel();

		/*String lastComissionRun = "2018-05-01 17:00:00";
		String nextComissionRun = "2018-05-02 17:00:00";*/
		
		String lastComissionRun = "2018-05-01 17:00:00";
		String nextComissionRun = "2018-05-11 20:00:00";

		ipValue.setDate("2018-05-01 14:00:10");
		ipValue.setValue("135");
		ipCorrectionList.add(ipValue);

		ipValue2.setDate("2018-04-01 14:00:10");
		ipValue2.setValue("125");
		ipCorrectionList.add(ipValue2);

		ipCorrection.put("33", (ArrayList<IpCalculationModel>) ipCorrectionList);

		List<DBStylistModel> stylists = StylistListMagentoCalls.getStylistList("stylist_id", "gt", "1");

		List<TeamReportModel> shopDataList = new ArrayList<TeamReportModel>();

		// List<IpOverviewModel> shopDataList=new ArrayList<IpOverviewModel>();

		for (DBStylistModel stylist : stylists) {
			List<IpCalculationModel> commIpCorrection = null;
			TeamReportModel teamModel = new TeamReportModel();
			if (ipCorrection.containsKey(stylist.getStylistId())) {

				commIpCorrection = ipCorrection.get(stylist.getStylistId());
			}

			IpOverviewModel model = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndOpenedLastMonth(
					stylist.getStylistId(), commIpCorrection, stylist.getActivatedAt(), "2018-05-11 00:00:00",
					lastComissionRun, nextComissionRun);
			teamModel.setStyleCoachId(stylist.getStylistId());
			teamModel.setActivationDate(stylist.getActivatedAt());
			teamModel.setIp(model.getTotalIp());
			teamModel.setIpThisMonth(model.getPaidOrdersThisMonth());
			teamModel.setIpLastMonth(model.getPaidOrdersPreviosMonth());
			teamModel.setIpTop(calculateIpTop(model.getIpTop(), model.getReturns(),model.getManualIpCorrectionTop()));
			teamModel.setManualIpCorrection(model.getManualIpCorrection());
			teamModel.setManualIpCorrectionTop(model.getManualIpCorrectionTop());
			teamModel.setReturns(model.getReturns());

			shopDataList.add(teamModel);
		}

		for (TeamReportModel shopmodel : shopDataList) {
			System.out.println("");
			System.out.println("sc id: " + shopmodel.getStyleCoachId());
			System.out.println("total  ip: " + shopmodel.getIp());
			System.out.println("getActivatedAt: " + shopmodel.getActivationDate());
		//	System.out.println("getIpThisMonth: " + shopmodel.getIpThisMonth());
		//	System.out.println("getIpLastMont: " + shopmodel.getIpLastMonth());
			System.out.println("ip Top: " + shopmodel.getIpTop());
			System.out.println("ip Manual: " + shopmodel.getManualIpCorrection());
			System.out.println("ip Manual Top : " + shopmodel.getManualIpCorrectionTop());
			
			  List<IpOverViewReturnsListModel> chargebacks = shopmodel.getReturns(); for (IpOverViewReturnsListModel ipOverViewReturnsListModel : chargebacks) { System.out.println("charge TOP " +ipOverViewReturnsListModel); }
			 

		}

		String[] dateFields = DateUtils.getDateFields(month, "yyyy-MM-dd HH:mm:ss");
		String previousMonth = DateUtils.getPreviousMonth(month, "yyyy-MM-dd HH:mm:ss");
		String[] dateFieldsPreviousMonth = DateUtils.getDateFields(previousMonth, "yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFields[0] + " " + dateFields[1]);

		List<CommissionStylistModel> allStylists = ComissionRestCalls.getStylistListInfo(dateFields[0], dateFields[1]);

		Thread.sleep(8000);

		List<CommissionStylistModel> allPreviousMonthStylists = ComissionRestCalls
				.getStylistListInfo(dateFieldsPreviousMonth[0], dateFieldsPreviousMonth[1]);

		List<CommissionStylistModel> levelStylistsList = getStylistsForAllLevels(allStylists, stylistId);
		List<TeamReportModel> allTeamReportStylists = new ArrayList<TeamReportModel>();

		for (CommissionStylistModel commissionStylistModel : levelStylistsList) {

			int partiesHeld = 0;
			int partiesPlanned = 0;
			int upcomingParties = 0;
			double grandRevenue = 0.0000;
			double revenuePerParty = 0.0000;

			TeamReportModel teamReportModel = new TeamReportModel();
			// get the list of parties for a style coach
			List<DBStylistPartyModel> partyList = PartyMagentoCalls.getPartyList(commissionStylistModel.getStylistId());
			for (DBStylistPartyModel party : partyList) {
				// if the party is placed in the given month and it's not //
				// deleted,increment the / parties held number

				if (party.getDeletedAt() == null && DateUtils.isDateBeetween(party.getPartyDate(),
						DateUtils.getFirstDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"),
						DateUtils.getLastDayOfAGivenMonth(month, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")) {

					double partyRevenue = 0.0000;
					partiesPlanned++;
					// if the party is placed in the given month and it's not //
					// deleted,get orders(if
					// the orders has one list party with grand total > 0
					// increment the parties held number and calculate revenue
					// // per party)
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

				// if the party is placed between current date and the last
				// dateof the month and it's not deleted or closed,increment
				// upcomingarties
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

			int takeOfPeriodNewSc = determineNewSc(commissionStylistModel.getStylistId(), allStylists,
					commissionStylistModel.getActivatedAt(), takeOffEnd);

			int daysLeft = DateUtils.getNumberOfDaysBeetweenTwoDates(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
					DateUtils.setHourToDate(takeOffEnd, 23, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");

			
			TeamReportModel ipShopModel=grabStylistDataFromShop(commissionStylistModel.getStylistId(),shopDataList);
			
		//	String tqv = calculateTqv(commissionStylistModel.getIp(), commissionStylistModel.getTeamPoints());
			String tqv = calculateTqv(ipShopModel.getIp(), commissionStylistModel.getTeamPoints());

			// Thread.sleep(5000);
			
			/* * CommissionStylistModel commStylistModelPreviousMonth =
			 * ComissionRestCalls.getStylistInfo(
			 * commissionStylistModel.getStylistId(),
			 * dateFieldsPreviousMonth[0], dateFieldsPreviousMonth[1]);*/
			 
			CommissionStylistModel commStylistModelPreviousMonth = grabPreviousMonthStylistData(
					commissionStylistModel.getStylistId(), allPreviousMonthStylists);
			

			teamReportModel.setStyleCoachId(commissionStylistModel.getStylistId());
			teamReportModel.setStyleCoachName(commissionStylistModel.getName());
			teamReportModel.setActivationDate(
					DateUtils.parseDate(commissionStylistModel.getActivatedAt(), "yyyy-MM-dd HH:mm:ss", "dd/MM/yy"));
			teamReportModel.setTakeOffPhaseEndDate(DateUtils.parseDate(takeOffEnd, "yyyy-MM-dd HH:mm:ss", "dd/MM/yy"));
			teamReportModel.setDaysLeft(String.valueOf(daysLeft));
			//emilian
		//	teamReportModel.setIpTop(commissionStylistModel.getIpTop());
			teamReportModel.setIpTop(ipShopModel.getIpTop());
			teamReportModel.setNewStylistTop(String.valueOf(takeOfPeriodNewSc));
			teamReportModel.setIpThisMonth(commissionStylistModel.getIp());
			teamReportModel.setIpLastMonth(commStylistModelPreviousMonth.getIp());
			teamReportModel.setPartiesHeld(String.valueOf(partiesHeld));
			teamReportModel.setPartiesPlanned(String.valueOf(partiesPlanned));
			teamReportModel.setPartiesUpcoming(String.valueOf(upcomingParties));
			teamReportModel.setRevenuePerParty(String.valueOf(revenuePerParty));

			// teamReportModel.setIp(commissionStylistModel.getIp());
			// shop value
			teamReportModel.setIp(ipShopModel.getIp());
			teamReportModel.setTqv(tqv);
			teamReportModel.setCarrerLevelThisMonth(commissionStylistModel.getCareerLevelName());
			teamReportModel.setCarrerLevelLastMonth(commStylistModelPreviousMonth.getCareerLevelName());
			teamReportModel.setPayLevel(commissionStylistModel.getPayLevelName());
			teamReportModel.setIpNewRecruited(commissionStylistModel.getIpNewFl());
			teamReportModel.setNewStylist(String.valueOf(newSc));
			teamReportModel.setLevel(commissionStylistModel.getLevel());
			teamReportModel.setCanceledDate(commissionStylistModel.getCanceledAt());
			teamReportModel.setIp30Top(String.valueOf(commissionStylistModel.getIp30Top())); //
			teamReportModel.setTopNrNewStylists(commissionStylistModel.getTopNrNewStylists());
			teamReportModel.setTopNrNewStylists(String.valueOf(takeOfPeriodNewSc));
			allTeamReportStylists.add(teamReportModel);

		}
		PrintUtils.printTeamReportModelList(allTeamReportStylists); //
		// return allTeamReportStylists;
		return null;
	}

	private static TeamReportModel grabStylistDataFromShop(String stylistId, List<TeamReportModel> shopDataList) {
		// TODO Auto-generated method stub
		List<TeamReportModel> result = new ArrayList<TeamReportModel>();

		result = shopDataList.stream().filter(item -> item.getStyleCoachId().equals(stylistId))
				.collect(Collectors.toList());

		return result.get(0);
	}

	private static String calculateIpTop(String totalIpTop, List<IpOverViewReturnsListModel> ipOverViewReturns, String manualIpCorrectionTop) {
		BigDecimal totalIp = BigDecimal.valueOf(Integer.parseInt(totalIpTop));
		for (IpOverViewReturnsListModel ipReturn : ipOverViewReturns) {
			if (ipReturn.getRefundType().contentEquals("2")) {
				totalIp = totalIp.subtract(BigDecimal.valueOf(Integer.parseInt((ipReturn.getIp()))));
			} else if (ipReturn.getRefundType().contentEquals("3")) {
				totalIp = totalIp.add(BigDecimal.valueOf(Integer.parseInt((ipReturn.getIp()))));
			}
		}

		return totalIp.add(BigDecimal.valueOf(Integer.parseInt(manualIpCorrectionTop))).toString();
	}

}

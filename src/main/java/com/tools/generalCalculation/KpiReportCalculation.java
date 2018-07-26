package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.ParseException;

import com.connectors.PippaDb.AtMyhomeDBKpiReport;
import com.tools.data.KpiReportModelInfo;
import com.tools.utils.DateUtils;

public class KpiReportCalculation {

	public static void main(String[] args) throws SQLException, ParseException {
		KpiReportCalculation calc = new KpiReportCalculation();
		System.out.println(calc.kpiReportInfo(""));
		// System.out.println(calc.calculateTotalTurnoverBeforeReturnInclVat("0", "0",
		// "136.65", "3.96"));
	}

	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static String guestNo;
	private static String buyingGuestNo;
	private static String partyAvgInclVat;
	boolean isCurrentMonth = true;

	public KpiReportModelInfo kpiReportInfo(String kpi_report_month1) throws SQLException, ParseException {

		KpiReportModelInfo model = new KpiReportModelInfo();
		AtMyhomeDBKpiReport kpiDb = new AtMyhomeDBKpiReport();

		/// shoul be set as parameter
		String kpi_report_month = "2018-07-30 12:00:00";
		
		isCurrentMonth = true;

		String firstDayCurrentMonth = DateUtils.getFirstDayOfAGivenMonth(kpi_report_month, dateFormat);
		String lastDayCurrentMonth = DateUtils.getLastDayOfAGivenMonth(kpi_report_month, dateFormat);
		String firstDayPrevMonth = DateUtils.getFirstDayOfPreviousMonth(kpi_report_month, dateFormat);
		String lastDayPrevMonth = DateUtils.getLastDayOfPreviousMonth(kpi_report_month, dateFormat);
		String firstDayNextMonth = DateUtils.getFirstDayOfANextGivenMonthBeginning(kpi_report_month, dateFormat);

		// System.out.println(kpi.getBuyingRate("1.33", "2"));

		model.setSalesforceBeginning(kpiDb.getSalesforceBeginning(firstDayPrevMonth, lastDayPrevMonth));
		model.setRecruits(kpiDb.getRecruits(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setDeactivated(kpiDb.getDeactivated(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setSfEnding(
				kpiDb.getSfEnding(model.getSalesforceBeginning(), model.getRecruits(), model.getDeactivated()));
		model.setPerformingSf(kpiDb.getPerformingSfExclDeactivated(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setTotalParties(kpiDb.getTotalParties(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setAvgPartiesPerActiveSf(
				kpiDb.getAveragePartiesPerActiveSF(model.getTotalParties(), model.getPerformingSf()));
		model.setTotalFollowUpParties(kpiDb.getTotalFollowUpPaties(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setAvgFollowUpPartiesPerActiveSf(
				kpiDb.getAverageFollowUpPartiesPerActiveSF(model.getTotalFollowUpParties(), model.getPerformingSf()));
		guestNo = kpiDb.getGuestNo(firstDayCurrentMonth, lastDayCurrentMonth);
		model.setAvgGuest(calculateAverage(guestNo, model.getTotalParties()));
		buyingGuestNo = kpiDb.getBuyingGuestNo(firstDayCurrentMonth, lastDayCurrentMonth);
		model.setAvgBuyingguest(calculateAverage(buyingGuestNo, model.getTotalParties()));
		model.setBuyingRate(kpiDb.getBuyingRate(model.getAvgBuyingguest(), model.getAvgGuest()));
		partyAvgInclVat = kpiDb.getPartyBeforeReturnsIncVat(firstDayCurrentMonth, lastDayCurrentMonth);
		model.setPartyAvgBeforeReturnIncVat(calculateAverage(partyAvgInclVat, model.getTotalParties()));
		model.setPartyAvgBeforeReturnExclVat(calculateAverageExlVat(partyAvgInclVat, model.getTotalParties()));
		model.setOnlineSalesInclVat(kpiDb.getOnlineSalesInclVat(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setOnlineSalesExclVatl(calculateValueWithoutVat(model.getOnlineSalesInclVat()));
		model.setSfmInclVat(kpiDb.getSfmInclVat(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setSfmExclVat(calculateValueWithoutVat(model.getSfmInclVat()));

		model.setTotalTurnoverBeforeReturnsInclVat(
				calculateTotalTurnoverBeforeReturnInclVat(model.getPartyAvgBeforeReturnIncVat(),
						model.getTotalParties(), model.getOnlineSalesInclVat(), model.getSfmInclVat()));

		model.setTotalTurnoverBeforeReturnsExclVat(
				calculateTotalTurnoverBeforeReturnExclVat(model.getPartyAvgBeforeReturnExclVat(),
						model.getTotalParties(), model.getOnlineSalesExclVatl(), model.getSfmExclVat()));

		model.setReturnsInclVat(kpiDb.getReturnInclVat(firstDayCurrentMonth, lastDayCurrentMonth));
		model.setReturnsExclVat(calculateValueWithoutVat(model.getReturnsInclVat()));

		model.setTotalTurnoverAfterReturnsInclVat(calculateTotalTurnoverAfterReturnsInclVat(
				model.getTotalTurnoverBeforeReturnsInclVat(), model.getReturnsInclVat()));

		model.setTotalTurnoverAfterReturnsExclVat(calculateTotalTurnoverAfterReturnsExclVat(
				model.getTotalTurnoverBeforeReturnsExclVat(), model.getReturnsExclVat()));
		model.setReturnRate(calculateAverage(model.getReturnsInclVat(), model.getTotalTurnoverBeforeReturnsInclVat()));


		String[] plannedInMonth1 = DateUtils.getASpecifcWeekNextMonth(firstDayNextMonth, "yyyy-MM-dd HH:mm:ss", "1",
				isCurrentMonth);
		// model.setPartiesPlannedInMonth1(kpiDb.getPlannedPartyInMonth(plannedInMonth1[0],
		// plannedInMonth1[1]));
		System.out.println("plannedInMonth1");
		System.out.println(plannedInMonth1[0]);
		System.out.println(plannedInMonth1[1]);
		model.setPartiesPlannedInMonth1(kpiDb.getPlannedPartyInMonth(plannedInMonth1[0], plannedInMonth1[1]));

		String[] plannedInMonth2 = DateUtils.getASpecifcWeekNextMonth(firstDayNextMonth, "yyyy-MM-dd HH:mm:ss", "2",
				isCurrentMonth);
		model.setPartiesPlannedInMonth2(kpiDb.getPlannedPartyInMonth(plannedInMonth2[0], plannedInMonth2[1]));
		System.out.println("plannedInMonth2");
		System.out.println(plannedInMonth2[0]);
		System.out.println(plannedInMonth2[1]);

		String[] plannedInMonth3 = DateUtils.getASpecifcWeekNextMonth(firstDayNextMonth, "yyyy-MM-dd HH:mm:ss", "3",
				isCurrentMonth);
		model.setPartiesPlannedInMonth3(kpiDb.getPlannedPartyInMonth(plannedInMonth3[0], plannedInMonth3[1]));

		System.out.println("plannedInMonth3");
		System.out.println(plannedInMonth3[0]);
		System.out.println(plannedInMonth3[1]);

		String[] plannedInMonth4 = DateUtils.getASpecifcWeekNextMonth(firstDayNextMonth, "yyyy-MM-dd HH:mm:ss", "4",
				isCurrentMonth);
		model.setPartiesPlannedInMonth4(kpiDb.getPlannedPartyInMonth(plannedInMonth4[0],
				DateUtils.getFirstDayOfANextGivenMonthFull(plannedInMonth4[0], dateFormat)));
		System.out.println("plannedInMonth4");
		System.out.println(plannedInMonth4[0]);
		System.out.println(DateUtils.getFirstDayOfANextGivenMonthFull(plannedInMonth4[0], dateFormat));

		String[] currentWeek = DateUtils.getCurrentWeekDate("yyyy-MM-dd HH:mm:ss");
		System.out.println(currentWeek[0] + " " + currentWeek[1]);
		model.setPartiesPlannedCurrentWeek(kpiDb.getPlannedPartyInWeek(currentWeek[0], currentWeek[1]));

		String[] plannedInWeek1 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "1",
				isCurrentMonth);
		model.setPartiesPlannedWeek1(kpiDb.getPlannedPartyInWeek(plannedInWeek1[0], plannedInWeek1[1]));

		String[] plannedInWeek2 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "2",
				isCurrentMonth);
		model.setPartiesPlannedWeek2(kpiDb.getPlannedPartyInWeek(plannedInWeek2[0], plannedInWeek2[1]));

		String[] plannedInWeek3 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "3",
				isCurrentMonth);
		model.setPartiesPlannedWeek3(kpiDb.getPlannedPartyInWeek(plannedInWeek3[0], plannedInWeek3[1]));

		String[] plannedInWeek4 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "4",
				isCurrentMonth);
		model.setPartiesPlannedWeek4(kpiDb.getPlannedPartyInWeek(plannedInWeek4[0], plannedInWeek4[1]));

		String[] plannedInWeek5 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "5",
				isCurrentMonth);
		model.setPartiesPlannedWeek5(kpiDb.getPlannedPartyInWeek(plannedInWeek5[0], plannedInWeek5[1]));

		String[] plannedInWeek6 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "6",
				isCurrentMonth);
		model.setPartiesPlannedWeek6(kpiDb.getPlannedPartyInWeek(plannedInWeek6[0], plannedInWeek6[1]));

		String[] plannedInWeek7 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "7",
				isCurrentMonth);
		model.setPartiesPlannedWeek7(kpiDb.getPlannedPartyInWeek(plannedInWeek7[0], plannedInWeek7[1]));

		String[] plannedInWeek8 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "8",
				isCurrentMonth);
		model.setPartiesPlannedWeek8(kpiDb.getPlannedPartyInWeek(plannedInWeek8[0], plannedInWeek8[1]));

		String[] plannedInWeek9 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "9",
				isCurrentMonth);
		model.setPartiesPlannedWeek9(kpiDb.getPlannedPartyInWeek(plannedInWeek9[0], plannedInWeek9[1]));

		String[] plannedInWeek10 = DateUtils.getASpecifcWeekAfterDate(firstDayCurrentMonth, "yyyy-MM-dd HH:mm:ss", "10",
				isCurrentMonth);
		model.setPartiesPlannedWeek10(kpiDb.getPlannedPartyInWeek(plannedInWeek10[0], plannedInWeek10[1]));

		kpiDb.ConnectionClose();

		return model;

	}

	private String calculateTotalTurnoverAfterReturnsExclVat(String totalTurnoverBeforeReturnsExclVat,
			String returnsExclVat) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(BigDecimal.valueOf(Double.valueOf(totalTurnoverBeforeReturnsExclVat)));
		result = result.subtract(BigDecimal.valueOf(Double.valueOf(returnsExclVat)));
		return result.toString();
	}

	private String calculateTotalTurnoverAfterReturnsInclVat(String totalTurnoverBeforeReturnsInclVat,
			String returnsInclVat) {

		BigDecimal result = BigDecimal.ZERO;

		result = result.add(BigDecimal.valueOf(Double.valueOf(totalTurnoverBeforeReturnsInclVat)));
		result = result.subtract(BigDecimal.valueOf(Double.valueOf(returnsInclVat)));
		return result.toString();
	}

	private String calculateTotalTurnoverBeforeReturnInclVat(String partyAvgBeforeReturnIncVat, String totalParties,
			String onlineSalesInclVat, String sfmInclVat) {

		BigDecimal result = BigDecimal.ZERO;
		result = result.add(BigDecimal.valueOf(Double.valueOf(partyAvgBeforeReturnIncVat)));
		result = result.multiply(BigDecimal.valueOf(Double.valueOf(totalParties)));
		result = result.add(BigDecimal.valueOf(Double.valueOf(onlineSalesInclVat)));
		result = result.add(BigDecimal.valueOf(Double.valueOf(sfmInclVat)));
		return result.setScale(2, RoundingMode.HALF_UP).toString();
	}

	private String calculateTotalTurnoverBeforeReturnExclVat(String partyAvgBeforeReturnExclVat, String totalParties,
			String onlineSalesExclVat, String sfmExclVat) {

		BigDecimal result = BigDecimal.ZERO;
		result = result.add(BigDecimal.valueOf(Double.valueOf(partyAvgBeforeReturnExclVat)));
		result = result.multiply(BigDecimal.valueOf(Double.valueOf(totalParties)));
		result = result.add(BigDecimal.valueOf(Double.valueOf(onlineSalesExclVat)));
		result = result.add(BigDecimal.valueOf(Double.valueOf(sfmExclVat)));
		return result.setScale(2, RoundingMode.HALF_UP).toString();
	}

	private String calculateAverage(String guestNo, String totalParties) {
		double result = Double.valueOf(guestNo) / Double.valueOf(totalParties);
		return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP).toString();
	}

	private String calculateAverageExlVat(String guestNo, String totalParties) {
		double result = Double.valueOf(guestNo) / Double.valueOf(totalParties);
		double resultExlVat = result / Double.valueOf(1.19);
		return BigDecimal.valueOf(resultExlVat).setScale(2, RoundingMode.HALF_UP).toString();
	}

	private String calculateValueWithoutVat(String value) {
		double resultExlVat = Double.valueOf(value) / Double.valueOf(1.19);
		return BigDecimal.valueOf(resultExlVat).setScale(2, RoundingMode.HALF_UP).toString();
	}
}

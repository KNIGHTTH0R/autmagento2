package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tools.data.soap.DBOrderModel;
import com.tools.env.constants.DateConstants;
import com.tools.utils.DateUtils;

public class OrdersCalculation {

	private static List<String> unsafeIpStatusesList = new ArrayList<String>(Arrays.asList("processing", "waiting_authorozation", "payment_review", "payment_failed",
			"pending_payment", "payment_in_progress", "pending_payment_hold"));

	private static List<String> payedStatusesList = new ArrayList<String>(Arrays.asList("complete", "payment_complete", "closed"));

	public static BigDecimal calculateTotalIpOnPreviousMonth(List<DBOrderModel> allOrdersList, String stylistId, String createdStartDate, String createdEndDate)
			throws NumberFormatException, ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculation(order, createdStartDate, createdEndDate)) {
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total IpOnPreviousMonth: " + String.valueOf(totalMonthIp));
		return totalMonthIp;
	}

	public static BigDecimal calculateTotalIpFromOrdersInTakeOfPeriod(List<DBOrderModel> allOrdersList, String stylistId, String activationDate, String createdStartDate) throws NumberFormatException, ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculationInTob(order, activationDate, createdStartDate)) {
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total ip take of period: " + String.valueOf(totalMonthIp));
		return totalMonthIp;
	}

	public static BigDecimal calculateTotalUnsafeIpOnCurrentMonth(List<DBOrderModel> allOrdersList, String stylistId, String createdStartDate) throws NumberFormatException,
			ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;

		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForUnsafeIpCalc(order, createdStartDate)) {
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total unsafe: " + String.valueOf(totalMonthIp));
		return totalMonthIp;
	}

	private static boolean isOrderCompatibleForIpCalculationInTob(DBOrderModel order, String activationDate, String createdStartDate)
			throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(),activationDate,
						DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(),activationDate,
						DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, order.getCreatedAt(), DateConstants.FORMAT) <= 91
				&& DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, order.getPaidAt(), DateConstants.FORMAT) <= 91;

	}

	// private static boolean
	// isOrderCompatibleForIpCalculationInTob(DBOrderModel order, String
	// activationDate) throws ParseException {
	// return isPayed(order)
	// && DateUtils.isDateBeetween(order.getCreatedAt(), activationDate,
	// DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
	// "yyyy-MM-dd HH:mm:ss")
	// && DateUtils.isDateBeetween(order.getPaidAt(), activationDate,
	// DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
	// "yyyy-MM-dd HH:mm:ss");
	//
	// }

	private static boolean isOrderCompatibleForIpCalculation(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {
		return isPayed(order)
				&& (isOrderCompatibleForIpCalcCase1(order, createdStartDate, createdEndDate) || isOrderCompatibleForIpCalcCase2(order, createdStartDate, createdEndDate));
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

	private static boolean hasUnsafeIpStatus(DBOrderModel model) {
		boolean found = false;
		for (String status : unsafeIpStatusesList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	private static boolean isOrderCompatibleForIpCalcCase1(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), createdEndDate, DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalcCase2(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), "2015-01-01 00:00:00", DateUtils.getLastDayOfPreviousMonth(createdStartDate, DateConstants.FORMAT),
				DateConstants.FORMAT) && DateUtils.isDateBeetween(order.getPaidAt(), createdStartDate, createdEndDate, DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForUnsafeIpCalc(DBOrderModel order, String createEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth("1970-10-10 00:00:00", DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(createEndDate, DateConstants.FORMAT), DateConstants.FORMAT)
				&& !isPayed(order) && hasUnsafeIpStatus(order);
	}

}

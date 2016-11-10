package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.tools.constants.DateConstants;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.utils.DateUtils;

public class CreditMemoCalculation {

	public static BigDecimal calculateTotalIpsForCreditMemos(List<DBCreditMemoModel> completeCMList, String stylistId, String createdStartDate, String createdEndDate)
			throws NumberFormatException, ParseException {

		BigDecimal totalMonthRefundedIp = BigDecimal.ZERO;

		for (DBCreditMemoModel order : completeCMList) {

			if (isOrderCompatibleForDecreasingIp(order, createdStartDate, createdEndDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.subtract(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
			if (isOrderCompatibleForIncreasingIp(order, createdStartDate, createdEndDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
		}
		return totalMonthRefundedIp;
	}

	public static BigDecimal calculateTotalIpsForCreditMemosInTakeOfPeriod(List<DBCreditMemoModel> completeCMList, String stylistId, String activationDate, String createdStartDate)
			throws NumberFormatException, ParseException {

		BigDecimal totalMonthRefundedIp = BigDecimal.ZERO;

		for (DBCreditMemoModel order : completeCMList) {

			if (isOrderCompatibleForDecreasingIpForTob(order, activationDate, createdStartDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.subtract(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
			if (isOrderCompatibleForIncreasingIpForTob(order, activationDate, createdStartDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
		}
		return totalMonthRefundedIp;
	}

	private static boolean isOrderCompatibleForIncreasingIp(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {
		return creditMemo.getState().contentEquals("3")
				&& (isCreditMemoCompatibleForIpCalcCase1(creditMemo, createdStartDate, createdEndDate) || isCreditMemoCompatibleForIpCalcCase2(creditMemo, createdStartDate,
						createdEndDate));
	}

	private static boolean isOrderCompatibleForIncreasingIpForTob(DBCreditMemoModel creditMemo, String activationDate, String createdStartDate) throws ParseException {
		return creditMemo.getState().contentEquals("3")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
						DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
						DateConstants.FORMAT) && DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, creditMemo.getCreatedAt(), DateConstants.FORMAT) <= 91
				&& DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, creditMemo.getOrderPaidAt(), DateConstants.FORMAT) <= 91;
	}

	private static boolean isOrderCompatibleForDecreasingIp(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {
		return creditMemo.getState().contentEquals("2") && isCreditMemoCompatibleForIpCalcCase1(creditMemo, createdStartDate, createdEndDate)
				|| isCreditMemoCompatibleForIpCalcCase2(creditMemo, createdStartDate, createdEndDate);
	}

	private static boolean isOrderCompatibleForDecreasingIpForTob(DBCreditMemoModel creditMemo, String activationDate, String createdStartDate) throws ParseException {
		return creditMemo.getState().contentEquals("2")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
						DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
						DateConstants.FORMAT) && DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, creditMemo.getCreatedAt(), DateConstants.FORMAT) <= 91
				&& DateUtils.getNumberOfDaysBeetweenTwoDates(activationDate, creditMemo.getOrderPaidAt(), DateConstants.FORMAT) <= 91;

	}

	private static boolean isCreditMemoCompatibleForIpCalcCase1(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(creditMemo.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), createdEndDate,
						DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, DateConstants.FORMAT), createdEndDate,
						DateConstants.FORMAT);
	}

	private static boolean isCreditMemoCompatibleForIpCalcCase2(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(creditMemo.getOrderCreatedAt(), "2015-01-01 00:00:00", DateUtils.getLastDayOfPreviousMonth(createdStartDate, DateConstants.FORMAT),
				DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), createdStartDate, createdEndDate, DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), createdStartDate, createdEndDate, DateConstants.FORMAT);
	}

	public static List<DBCreditMemoModel> populateCreditMemosListWithOrderDetails(List<DBCreditMemoModel> creditMemoList, List<DBOrderModel> orderList) {

		for (DBCreditMemoModel creditMemo : creditMemoList) {
			for (DBOrderModel order : orderList) {
				if (creditMemo.getOrderIncrementId().contentEquals(order.getIncrementId())) {
					creditMemo.setOrderCreatedAt(order.getCreatedAt());
					creditMemo.setOrderPaidAt(order.getPaidAt());
				}
			}
		}
		return creditMemoList;
	}

}

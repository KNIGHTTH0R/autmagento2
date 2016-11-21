package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.constants.DateConstants;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.utils.DateUtils;

public class IpOverviewCalculations {

	private static List<String> payedStatusesList = new ArrayList<String>(
			Arrays.asList("complete", "payment_complete", "closed"));

	public IpOverViewSummaryModel calculateIpOverviewSummary(String stylistId, String month)
			throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation
				.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);

		IpOverViewSummaryModel result = new IpOverViewSummaryModel();
		IpOverviewCalculations.calculateIps(result, allOrdersList, month);

		return result;
	}

	public static IpOverViewSummaryModel calculateIps(IpOverViewSummaryModel result, List<DBOrderModel> allOrdersList,
			String month) throws NumberFormatException, ParseException {

		BigDecimal totatIpPreviousMonth = BigDecimal.ZERO;
		BigDecimal totalIpCurrentMonth = BigDecimal.ZERO;

		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculationPrevMonth(order, month)) {
				totatIpPreviousMonth = totatIpPreviousMonth
						.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month)) {
				totalIpCurrentMonth = totalIpCurrentMonth
						.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		result.setPaidOrdersPreviosMonth(String.valueOf(totatIpPreviousMonth));
		result.setPaidOrdersThisMonth(String.valueOf(totalIpCurrentMonth));

		return result;
	}

	public static IpOverViewSummaryModel calculateCreditMemos(IpOverViewSummaryModel result,
			List<DBCreditMemoModel> cmList, String month) throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;

		for (DBCreditMemoModel cm : cmList) {
			
		}
		result.setChargebacksThisMonth(String.valueOf(chargeBacksCurrentMonth));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));

		return result;
	}

	public static BigDecimal calculateTotalIpsForCurrentMonth(List<DBOrderModel> allOrdersList, String month)
			throws NumberFormatException, ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month)) {
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		return totalMonthIp;
	}

	private static boolean isOrderCompatibleForIpCalculationPrevMonth(DBOrderModel order, String month)
			throws ParseException {

		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalculationCurrentMonth(DBOrderModel order, String month)
			throws ParseException {
		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), month, DateConstants.FORMAT);
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

}

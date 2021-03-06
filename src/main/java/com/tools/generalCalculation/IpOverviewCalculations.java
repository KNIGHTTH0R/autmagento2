package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.constants.ComissionConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.DateConstants;
import com.tools.data.IpCalculationModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.utils.DateUtils;

public class IpOverviewCalculations {

	private static List<String> payedStatusesList = new ArrayList<String>(
			Arrays.asList("complete", "payment_complete", "closed"));

	private static List<String> notPayedStatusesList = new ArrayList<String>(Arrays.asList("pending", "processing",
			"waiting_authorization", "payment_review", "payment_failed", "pending_payment", "payment_in_progress"));

	private static List<String> notPayedStatusesTPList = new ArrayList<String>(Arrays.asList("pending_payment_hold"));


	public static IpOverviewModel calculateIpOverviewForOpenMonthAndClosedLastMonth(String stylistId, String month,
			String previousCommissionRun, String lastComissionRun, String nextComissionRun)
					throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation
				.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);

		IpOverviewModel result = new IpOverviewModel();

		result = IpOverviewCalculations.calculateOrdersForOpenMonthAndClosedLastMonth(result, allOrdersList, month,
				lastComissionRun, nextComissionRun);
		result = IpOverviewCalculations.calculateCreditMemos(result, completeCMList, month, previousCommissionRun,
				lastComissionRun);

		return result;
	}

	public static IpOverviewModel calculateIpOverviewForOpenMonthAndOpenedLastMonth(String stylistId, String month,
			String lastComissionRun, String nextComissionRun) throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation
				.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);
		IpOverviewModel result = new IpOverviewModel();

		result = IpOverviewCalculations.calculateOrdersForOpenMonthAndOpenedLastMonth(result, allOrdersList, month,
				lastComissionRun, nextComissionRun);

		result = IpOverviewCalculations.calculateCreditMemosOpenMonth(result, completeCMList, month, lastComissionRun,
				nextComissionRun);
		result.setManualIpCorrection("0");
		result.setTotalIp(calculateTotalIp(result));
		result.setOpenIpTotal(calculateTotalOpenIp(result));
		return result;
	}

	public static IpOverviewModel calculateIpOverviewForOpenMonthAndOpenedLastMonth(String stylistId,
			List<IpCalculationModel> ipCorrection, String takeOffStart, String month, String lastComissionRun,
			String nextComissionRun) throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation
				.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);
		IpOverviewModel result = new IpOverviewModel();

		result = IpOverviewCalculations.calculateOrdersForOpenMonthAndOpenedLastMonth(result, allOrdersList, month,
				takeOffStart, lastComissionRun, nextComissionRun);

		result = IpOverviewCalculations.calculateCreditMemosOpenMonth(result, completeCMList, month, lastComissionRun,
				nextComissionRun, takeOffStart);
		result.setManualIpCorrectionTop(calculateIpCorrectionTop(takeOffStart, ipCorrection));
		result.setManualIpCorrection(calculateIpCorrection(month, ipCorrection));
		result.setTotalIp(calculateTotalIp(result));
		result.setOpenIpTotal(calculateTotalOpenIp(result));
		return result;
	}

	private static String calculateIpCorrection(String month, List<IpCalculationModel> ipCorrection)
			throws ParseException {
		BigDecimal manualIp = BigDecimal.ZERO;

		if (ipCorrection!=null) {

			for (IpCalculationModel ipManual : ipCorrection) {
				if (DateUtils.isInTheSameMonth(ipManual.getDate(), month, DateConstants.FORMAT)) {
					manualIp = manualIp.add(BigDecimal.valueOf(Integer.parseInt(ipManual.getValue())));
				}
			}
		}

		return manualIp.toString();
	}
	
	private static String calculateIpCorrectionTop(String takeOffStart, List<IpCalculationModel> ipCorrection)
			throws ParseException {
		BigDecimal manualIp = BigDecimal.ZERO;
		String takeOffEnd = calculateTakeOffPhaseAndDate(takeOffStart, "yyyy-MM-dd HH:mm:ss");

		if (ipCorrection!=null) {

			for (IpCalculationModel ipManual : ipCorrection) {
				if (DateUtils.isDateBefore(ipManual.getDate(), takeOffEnd, DateConstants.FORMAT)
						&& DateUtils.isDateAfter(ipManual.getDate(), takeOffStart, DateConstants.FORMAT)) {
					manualIp = manualIp.add(BigDecimal.valueOf(Integer.parseInt(ipManual.getValue())));
				}
			}
		}

		return manualIp.toString();
	}

	public static String calculateTotalIp(IpOverviewModel result) {
		BigDecimal intial = BigDecimal.ZERO;

	
		BigDecimal totalIp = intial
				.add(BigDecimal.valueOf(Integer.parseInt((result.getPaidOrdersThisMonth()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getReverseChargebackThisMonth()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getChargebacksThisMonth()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getReturnsThisMonth()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getManualIpCorrection()))));

		return totalIp.toString();

	}

	public static String calculateTotalOpenIp(IpOverviewModel result) {
		BigDecimal intial = BigDecimal.ZERO;

		BigDecimal totalIp = intial.add(BigDecimal.valueOf(Integer.parseInt((result.getOpenChargebacks()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getIpThisMonth()))))
				.add(BigDecimal.valueOf(Integer.parseInt((result.getIpLastMonth()))));

		return totalIp.toString();

	}

	public static IpOverviewModel calculateIpOverviewForClosedMonth(String stylistId, String month,
			String previousComissionRun, String lastComissionRun, String nextComissionRun)
					throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation
				.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);

		IpOverviewModel result = new IpOverviewModel();

		result = IpOverviewCalculations.calculateOrdersForClosedMonthAndClosedLastMonth(result, allOrdersList, month,
				previousComissionRun, lastComissionRun, nextComissionRun);
		result = IpOverviewCalculations.calculateCreditMemosForClosedMonthAndClosedLastMonth(result, completeCMList,
				month, previousComissionRun, lastComissionRun, nextComissionRun);

		return result;
	}

	public static IpOverviewModel calculateOrdersForOpenMonthAndClosedLastMonth(IpOverviewModel ipOverviewModel,
			List<DBOrderModel> allOrdersList, String month, String lastComissionRun, String nextComissionRun)
					throws NumberFormatException, ParseException {

		BigDecimal ipsPreviousMonth = BigDecimal.ZERO;
		BigDecimal ipsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsPreviousMonth = BigDecimal.ZERO;
		BigDecimal tpIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal tpIpUpcomingMonths = BigDecimal.ZERO;
		List<IpOverViewPayedOrdersModel> paidOrderList = new ArrayList<IpOverViewPayedOrdersModel>();

		for (DBOrderModel order : allOrdersList) {

			if (isOrderCompleteForCurrentMonth(order, month)) {
				// the method populateIpOverViewPayedOrdersModel(order) returns
				// a IpOverViewPayedOrdersModel add we add it directly in the
				// list
				paidOrderList.add(populateIpOverViewPayedOrdersModel(order));
			}

			if (isOrderCompatibleForIpCalculationPrevMonth(order, month, lastComissionRun, nextComissionRun)) {
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));

			}
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month, nextComissionRun)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOpenIpsCurrentMonthForClosedLastMonth(order, month)) {
				openIpsCurrentMonth = openIpsCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isOpenIpsPreviousMonthClosedLastMonth(order, month)) {
				openIpsPreviousMonth = openIpsPreviousMonth
						.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isTermPurchaseIpsCurrentMonth(order, month)) {
				tpIpsCurrentMonth = tpIpsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isTermPurchaseIpsUpcomingMonth(order, month)) {
				tpIpUpcomingMonths = tpIpUpcomingMonths.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

		}

		ipOverviewModel.setPaidOrdersPreviosMonth(String.valueOf(ipsPreviousMonth));
		ipOverviewModel.setPaidOrdersThisMonth(String.valueOf(ipsCurrentMonth));
		ipOverviewModel.setPayedOrders(paidOrderList);
		ipOverviewModel.setIpThisMonth(String.valueOf(openIpsCurrentMonth));
		ipOverviewModel.setIpLastMonth(String.valueOf(openIpsPreviousMonth));
		ipOverviewModel.setIpTPOrdersThisMonth(String.valueOf(tpIpsCurrentMonth));
		ipOverviewModel.setIpTPOrdersLastMonth(String.valueOf(tpIpUpcomingMonths));

		return ipOverviewModel;
	}

	public static IpOverviewModel calculateOrdersForOpenMonthAndOpenedLastMonth(IpOverviewModel ipOverviewModel,
			List<DBOrderModel> allOrdersList, String month, String lastComissionRun, String nextComissionRun)
					throws NumberFormatException, ParseException {

		BigDecimal ipsPreviousMonth = BigDecimal.ZERO;
		BigDecimal ipsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsPreviousMonth = BigDecimal.ZERO;
		BigDecimal tpIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal tpIpUpcomingMonths = BigDecimal.ZERO;
		BigDecimal ipPrevMonth = BigDecimal.ZERO;

		List<IpOverViewPayedOrdersModel> paidOrderList = new ArrayList<IpOverViewPayedOrdersModel>();

		for (DBOrderModel order : allOrdersList) {

			if (isOrderCompleteForCurrentMonth(order, month)) {
				// the method populateIpOverViewPayedOrdersModel(order) returns
				// a IpOverViewPayedOrdersModel add we add it directly in the
				// list
				paidOrderList.add(populateIpOverViewPayedOrdersModel(order));
			}

			if (isOrderCompatibleForIpCalculationPrevMonth(order, month, lastComissionRun, nextComissionRun)) {
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month, nextComissionRun)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOpenIpsCurrentMonthForOpenLastMonth(order, month, lastComissionRun)) {
				openIpsCurrentMonth = openIpsCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isOpenIpsPreviousMonthOpenLastMonth(order, month)) {
				openIpsPreviousMonth = BigDecimal.ZERO;

			}

			if (isTermPurchaseIpsCurrentMonth(order, month)) {
				tpIpsCurrentMonth = tpIpsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isTermPurchaseIpsUpcomingMonth(order, month)) {
				tpIpUpcomingMonths = tpIpUpcomingMonths.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			/*
			 * if(isOrderCompatibleForIpPrevMonth(order,month)){
			 * System.out.println("order increment: "+order);
			 * ipPrevMonth=ipPrevMonth.add(BigDecimal.valueOf(Integer.parseInt((
			 * order.getTotalIp())))); }
			 */

		}

		System.out.println("ipPrevMonth: " + ipPrevMonth);
		ipOverviewModel.setPaidOrdersPreviosMonth(String.valueOf(ipsPreviousMonth));
		ipOverviewModel.setPaidOrdersThisMonth(String.valueOf(ipsCurrentMonth));
		ipOverviewModel.setPayedOrders(paidOrderList);

		// emilian
		ipOverviewModel.setIpThisMonth(String.valueOf(openIpsCurrentMonth));
		ipOverviewModel.setIpLastMonth(String.valueOf(openIpsPreviousMonth));
		ipOverviewModel.setIpTPOrdersThisMonth(String.valueOf(tpIpsCurrentMonth));
		ipOverviewModel.setIpTPOrdersLastMonth(String.valueOf(tpIpUpcomingMonths));

		return ipOverviewModel;
	}

	public static IpOverviewModel calculateOrdersForOpenMonthAndOpenedLastMonth(IpOverviewModel ipOverviewModel,
			List<DBOrderModel> allOrdersList, String month, String takeOffPhaseStart, String lastComissionRun,
			String nextComissionRun) throws NumberFormatException, ParseException {

		BigDecimal ipsPreviousMonth = BigDecimal.ZERO;
		BigDecimal ipsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsPreviousMonth = BigDecimal.ZERO;
		BigDecimal tpIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal tpIpUpcomingMonths = BigDecimal.ZERO;
	//	BigDecimal ipPrevMonth = BigDecimal.ZERO;
		BigDecimal ipTakeOff = BigDecimal.ZERO;

		String takeOffEnd = calculateTakeOffPhaseAndDate(takeOffPhaseStart, "yyyy-MM-dd HH:mm:ss");

		List<IpOverViewPayedOrdersModel> paidOrderList = new ArrayList<IpOverViewPayedOrdersModel>();

		for (DBOrderModel order : allOrdersList) {

			if (isOrderCompleteForCurrentMonth(order, month)) {
				// the method populateIpOverViewPayedOrdersModel(order) returns
				// a IpOverViewPayedOrdersModel add we add it directly in the
				// list
				paidOrderList.add(populateIpOverViewPayedOrdersModel(order));
			}

			if (isOrderCompatibleForIpCalculationPrevMonth(order, month, lastComissionRun, nextComissionRun)) {
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month, nextComissionRun)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOpenIpsCurrentMonthForOpenLastMonth(order, month, lastComissionRun)) {
				openIpsCurrentMonth = openIpsCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isOpenIpsPreviousMonthOpenLastMonth(order, month)) {
				openIpsPreviousMonth = BigDecimal.ZERO;

			}

			if (isTermPurchaseIpsCurrentMonth(order, month)) {
				tpIpsCurrentMonth = tpIpsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isTermPurchaseIpsUpcomingMonth(order, month)) {
				tpIpUpcomingMonths = tpIpUpcomingMonths.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			/*
			 * if(isOrderCompatibleForIpPrevMonth(order,month)){
			 * System.out.println("order increment: "+order);
			 * ipPrevMonth=ipPrevMonth.add(BigDecimal.valueOf(Integer.parseInt((
			 * order.getTotalIp())))); }
			 */

			if (isOrderCompatibleforTopIpsCalculation(order, month, takeOffPhaseStart, takeOffEnd)) {
				ipTakeOff = ipTakeOff.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

		}

		System.out.println("ipTakeOff: " + ipTakeOff);
		ipOverviewModel.setPaidOrdersPreviosMonth(String.valueOf(ipsPreviousMonth));
		ipOverviewModel.setPaidOrdersThisMonth(String.valueOf(ipsCurrentMonth));
		ipOverviewModel.setPayedOrders(paidOrderList);
		ipOverviewModel.setIpTop(String.valueOf(ipTakeOff));
		// emilian
		ipOverviewModel.setIpThisMonth(String.valueOf(openIpsCurrentMonth));
		ipOverviewModel.setIpLastMonth(String.valueOf(openIpsPreviousMonth));
		ipOverviewModel.setIpTPOrdersThisMonth(String.valueOf(tpIpsCurrentMonth));
		ipOverviewModel.setIpTPOrdersLastMonth(String.valueOf(tpIpUpcomingMonths));

		return ipOverviewModel;
	}

	public static String calculateTakeOffPhaseAndDate(String activationDate, String format) throws ParseException {

		return DateUtils.addDaysToAAGivenDate(activationDate, format, ComissionConfigConstants.TAKE_OFF_PERIOD);
	}

	public static IpOverviewModel calculateOrdersForClosedMonthAndClosedLastMonth(IpOverviewModel ipOverviewModel,
			List<DBOrderModel> allOrdersList, String month, String previousComissionRun, String lastComissionRun,
			String nextComissionRun) throws NumberFormatException, ParseException {

		BigDecimal ipsPreviousMonth = BigDecimal.ZERO;
		BigDecimal ipsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsPreviousMonth = BigDecimal.ZERO;
		BigDecimal tpIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal tpIpUpcomingMonths = BigDecimal.ZERO;
		List<IpOverViewPayedOrdersModel> paidOrderList = new ArrayList<IpOverViewPayedOrdersModel>();

		for (DBOrderModel order : allOrdersList) {

			if (isOrderCompleteForCurrentMonth(order, month)) {
				// the method populateIpOverViewPayedOrdersModel(order) returns
				// a IpOverViewPayedOrdersModel add we add it directly in the
				// list
				paidOrderList.add(populateIpOverViewPayedOrdersModel(order));
			}

			if (isOrderCompatibleForIpCalculationPrevMonthSelected(order, month, lastComissionRun, nextComissionRun)) {
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month, nextComissionRun)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

			if (isOpenIpsCurrentMonthForClosedLastMonth(order, month)) {
				openIpsCurrentMonth = BigDecimal.ZERO;
			}

			if (isTermPurchaseIpsCurrentMonth(order, month)) {
				tpIpsCurrentMonth = tpIpsCurrentMonth.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}
			if (isTermPurchaseIpsUpcomingMonth(order, month)) {
				tpIpUpcomingMonths = tpIpUpcomingMonths.add(BigDecimal.valueOf(Integer.parseInt((order.getTotalIp()))));
			}

		}

		ipOverviewModel.setPaidOrdersPreviosMonth(String.valueOf(ipsPreviousMonth));
		ipOverviewModel.setPaidOrdersThisMonth(String.valueOf(ipsCurrentMonth));
		ipOverviewModel.setPayedOrders(paidOrderList);
		ipOverviewModel.setIpThisMonth(String.valueOf(openIpsCurrentMonth));
		ipOverviewModel.setIpLastMonth(String.valueOf(openIpsPreviousMonth));
		ipOverviewModel.setIpTPOrdersThisMonth(String.valueOf(tpIpsCurrentMonth));
		ipOverviewModel.setIpTPOrdersLastMonth(String.valueOf(tpIpUpcomingMonths));

		return ipOverviewModel;
	}

	public static IpOverviewModel calculateCreditMemosForClosedMonthAndClosedLastMonth(IpOverviewModel result,
			List<DBCreditMemoModel> cmList, String month, String previousCommissiomRun, String lastComissionRun,
			String nextCommissionRun) throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal returns = BigDecimal.ZERO;
	//	BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;
		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();

		for (DBCreditMemoModel cm : cmList) {

			if (isReverseChargeeBackCurrentMonthFirstCondition(cm, month, previousCommissiomRun, nextCommissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			if (isReverseChargeBackCurrentMonthSecondCondition(cm, month, previousCommissiomRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}
			/*
			 * if (isChargeBackCurrentMonthFirstCondition(cm, month,
			 * previousCommissiomRun, lastComissionRun)) {
			 * chargeBacksCurrentMonth = chargeBacksCurrentMonth
			 * .add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()
			 * )))); }
			 */
			if (isChargeBackCurrentMonthSecondCondition(cm, month, previousCommissiomRun, lastComissionRun)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}
			if (isOpenChargeback(cm, month)) {
		//		openChargebackPreviousAndCurrent = BigDecimal.ZERO;
			}
			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousCommissiomRun, lastComissionRun)) {
				returns = returns.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}

			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousCommissiomRun, lastComissionRun)) {
				chargebacks.add(populateIpOverViewReturnsListModel(cm));
			}

		}
		result.setChargebacksThisMonth(String.valueOf("-" + chargeBacksCurrentMonth));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));
		result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		result.setReturnsThisMonth(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		// result.setOpenChargebacks(String.valueOf(openChargebackPreviousAndCurrent));
		// result.setReturnsThisMonth(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		result.setReturns(chargebacks);

		return result;
	}

	public static IpOverviewModel calculateCreditMemos(IpOverviewModel result, List<DBCreditMemoModel> cmList,
			String month, String previousComissionRun, String lastComissionRun)
					throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal returns = BigDecimal.ZERO;
		BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;
		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();

		for (DBCreditMemoModel cm : cmList) {

			if (isReverseChargeeBackCurrentMonthFirstCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			if (isReverseChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			/*
			 * if (isChargeBackCurrentMonthFirstCondition(cm, month,
			 * previousComissionRun, lastComissionRun)) {
			 * chargeBacksCurrentMonth = chargeBacksCurrentMonth
			 * .add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()
			 * )))); }
			 */
			if (isChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}
			if (isOpenChargeback(cm, month)) {
				openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
				;
			}
			if (isCreditMemoForCurrentMonthIfLastMonthIsClosed(cm, month, previousComissionRun, lastComissionRun)) {
				returns = BigDecimal.ZERO;
			}

			if (isCreditMemoForCurrentMonthIfLastMonthIsClosed(cm, month, previousComissionRun, lastComissionRun)) {
				chargebacks.add(populateIpOverViewReturnsListModel(cm));
			}

		}
		result.setChargebacksThisMonth(String.valueOf("-" + chargeBacksCurrentMonth));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));
		// result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		// result.setReturnsThisMonth(String.valueOf(returns));
		result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		result.setReturnsThisMonth(String.valueOf(returns));

		result.setReturns(chargebacks);

		return result;
	}

	public static IpOverviewModel calculateCreditMemosOpenMonth(IpOverviewModel result, List<DBCreditMemoModel> cmList,
			String month, String previousComissionRun, String lastComissionRun)
					throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal returns = BigDecimal.ZERO;
		BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;
		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();

		for (DBCreditMemoModel cm : cmList) {

			if (isReverseChargeeBackCurrentMonthFirstCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			if (isReverseChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			/*
			 * if (isChargeBackCurrentMonthFirstCondition(cm, month,
			 * previousComissionRun, lastComissionRun)) {
			 * chargeBacksCurrentMonth = chargeBacksCurrentMonth
			 * .add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()
			 * )))); }
			 */
			if (isChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}

			// emilian
			if (isOpenChargeback(cm, month)) {
				System.out.println("sunt aici cu statusul: " + cm.getState() + " si cu total ip refunded: "
						+ cm.getTotalIpRefunded());
				if (cm.getState().contentEquals("2")) {
					openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent
							.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
				}
				if (cm.getState().contentEquals("3")) {
					openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent
							.subtract(BigDecimal.valueOf(2 * Integer.parseInt((cm.getTotalIpRefunded()))));
				}

			}
			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousComissionRun, lastComissionRun)) {
				returns = BigDecimal.ZERO;
			}

			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousComissionRun, lastComissionRun)) {
				chargebacks.add(populateIpOverViewReturnsListModel(cm));
			}

		}

		BigDecimal chargeBack = chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth);
		result.setChargebacksThisMonth(String.valueOf("-" + chargeBack));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));
		// result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		// result.setReturnsThisMonth(String.valueOf(returns));
		// emilian
		// result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		result.setOpenChargebacks(String.valueOf(openChargebackPreviousAndCurrent));
		result.setReturnsThisMonth(String.valueOf(returns));

		result.setReturns(chargebacks);

		return result;
	}

	public static IpOverviewModel calculateCreditMemosOpenMonth(IpOverviewModel result, List<DBCreditMemoModel> cmList,
			String month, String previousComissionRun, String lastComissionRun, String takeOffPhaseStart)
					throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal returns = BigDecimal.ZERO;
		BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;

		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();
		List<IpOverViewReturnsListModel> chargebackTop = new ArrayList<IpOverViewReturnsListModel>();

		String takeOffEnd = calculateTakeOffPhaseAndDate(takeOffPhaseStart, "yyyy-MM-dd HH:mm:ss");

		for (DBCreditMemoModel cm : cmList) {

			if (isReverseChargeeBackCurrentMonthFirstCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			if (isReverseChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));

			}
			/*
			 * if (isChargeBackCurrentMonthFirstCondition(cm, month,
			 * previousComissionRun, lastComissionRun)) {
			 * chargeBacksCurrentMonth = chargeBacksCurrentMonth
			 * .add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()
			 * )))); }
			 */
			if (isChargeBackCurrentMonthSecondCondition(cm, month, previousComissionRun, lastComissionRun)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth
						.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
			}

			// emilian
			if (isOpenChargeback(cm, month)) {
				System.out.println("sunt aici cu statusul: " + cm.getState() + " si cu total ip refunded: "
						+ cm.getTotalIpRefunded());
				if (cm.getState().contentEquals("2")) {
					openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent
							.add(BigDecimal.valueOf(Integer.parseInt((cm.getTotalIpRefunded()))));
				}
				if (cm.getState().contentEquals("3")) {
					openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent
							.subtract(BigDecimal.valueOf(2 * Integer.parseInt((cm.getTotalIpRefunded()))));
				}

			}
			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousComissionRun, lastComissionRun)) {
				returns = BigDecimal.ZERO;
			}

			if (isCreditMemoForCurrentMonthIfLastMonthIsOpened(cm, month, previousComissionRun, lastComissionRun)) {
				chargebacks.add(populateIpOverViewReturnsListModel(cm));
			}

			if (isCreditMemoForTop(cm, month, takeOffPhaseStart, takeOffEnd)) {
				chargebackTop.add(populateIpOverViewReturnsListModel(cm));
			}

		}

		BigDecimal chargeBack = chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth);
		result.setChargebacksThisMonth(String.valueOf("-" + chargeBack));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));
		// result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		// result.setReturnsThisMonth(String.valueOf(returns));
		// emilian
		// result.setOpenChargebacks(String.valueOf(chargeBacksCurrentMonth.subtract(reverseChargeBacksCurrentMonth)));
		result.setOpenChargebacks(String.valueOf(openChargebackPreviousAndCurrent));
		result.setReturnsThisMonth(String.valueOf(returns));

		result.setReturns(chargebackTop);

		return result;
	}

	private static IpOverViewPayedOrdersModel populateIpOverViewPayedOrdersModel(DBOrderModel order) {

		IpOverViewPayedOrdersModel ipOverviewPaidOrdersModel = new IpOverViewPayedOrdersModel();

		ipOverviewPaidOrdersModel.setOrderID(order.getIncrementId());
		ipOverviewPaidOrdersModel.setCustomerName(order.getCustomerName());
		ipOverviewPaidOrdersModel.setAmount(order.getGrandTotal());
		ipOverviewPaidOrdersModel.setOrderDate(order.getCreatedAt());
		ipOverviewPaidOrdersModel.setPaymentDate(order.getPaidAt());
		ipOverviewPaidOrdersModel.setOrderStatus(order.getStatus());
		ipOverviewPaidOrdersModel.setIp(order.getTotalIp());

		return ipOverviewPaidOrdersModel;
	}

	private static IpOverViewReturnsListModel populateIpOverViewReturnsListModel(DBCreditMemoModel cm) {

		IpOverViewReturnsListModel ipOverViewReturnsListModel = new IpOverViewReturnsListModel();

		ipOverViewReturnsListModel.setOrderId(cm.getOrderIncrementId());
		ipOverViewReturnsListModel.setIp(cm.getTotalIpRefunded());
		ipOverViewReturnsListModel.setAmount(cm.getGrandTotal());
		ipOverViewReturnsListModel.setRefundDate(cm.getCreatedAt());
		ipOverViewReturnsListModel.setRefundType(cm.getState());

		// we dont have the amount of the order on cm ,so we cannot populate
		// this field yet.please put this field on credit memo model(source is
		// the order, the same as for OrderIcrementId)
		// please continue with the rest

		return ipOverViewReturnsListModel;
	}

	// private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order,
	// String month) throws ParseException {
	//
	// return isPayed(order)
	// && DateUtils.isDateBeetween(order.getCreatedAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	// DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
	// DateConstants.FORMAT);
	// }

	private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order, String month) throws ParseException {

		return isPayed(order) && (DateUtils.isDateBeetween(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBefore(order.getPaidAt(), DateUtils.getCurrentDate(DateConstants.FORMAT),
						DateConstants.FORMAT));
		// && (DateUtils.isDateBefore(order.getCreatedAt(),
		// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
		// DateConstants.FORMAT)
		// &&
		// DateUtils.isDateBeetween(order.getPaidAt(),DateUtils.getFirstDayOfAGivenMonth(month,
		// DateConstants.FORMAT), DateUtils.getLastDayOfAGivenMonth(month,
		// DateConstants.FORMAT), DateConstants.FORMAT));
	}

	private static boolean isOrderCompatibleForIpCalculationPrevMonth(DBOrderModel order, String month,
			String lastComissionRun, String nextComissionRun) throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBefore(order.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), lastComissionRun, nextComissionRun,
						DateConstants.FORMAT);
	}

	/*private static boolean isOrderCompatibleForIpPrevMonth(DBOrderModel order, String month) throws ParseException {

		return DateUtils.isDateBefore(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
		
		 * return isPayed(order) && DateUtils.isDateBefore(order.getCreatedAt(),
		 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
		 * DateConstants.FORMAT);
		 
		
		 * && DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getf,
		 * nextComissionRun, DateConstants.FORMAT);
		 
	}
*/
	private static boolean isOrderCompatibleforTopIpsCalculation(DBOrderModel order, String month,
			String takeOffPhaseStart, String takeOffPhaseEnd) throws ParseException {
		System.out.println("order.getCreatedAt() " + order.getCreatedAt());
		System.out.println("takeOffPhaseStart " + takeOffPhaseStart);
		System.out.println("takeOffPhaseEnd " + takeOffPhaseEnd);
		System.out.println("order.getCreatedAt()" + order.getCreatedAt());
		System.out.println("order.getPaidAt() " + order.getPaidAt());

		return isPayed(order) && DateUtils.isDateBefore(order.getCreatedAt(), takeOffPhaseEnd, DateConstants.FORMAT);
		/*
		 * && DateUtils.isDateAfter(order.getPaidAt(), takeOffPhaseStart,
		 * DateConstants.FORMAT);
		 */
	}

	private static boolean isOrderCompatibleForIpCalculationPrevMonthSelected(DBOrderModel order, String month,
			String lastComissionRun, String nextComissionRun) throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBefore(order.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), lastComissionRun, nextComissionRun,
						DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalculationCurrentMonth(DBOrderModel order, String month,
			String nextComissionRun) throws ParseException {
		return isPayed(order) && DateUtils.isDateBeetween(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)

				&& DateUtils.isDateBeetween(order.getPaidAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), nextComissionRun,
						DateConstants.FORMAT);
	}

	// for last month=opened
	private static boolean isOpenIpsCurrentMonthForOpenLastMonth(DBOrderModel order, String month,
			String lastCommissionRun) throws ParseException {

		return isNotPayed(order) && DateUtils.isDateAfter(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	// for last month=closed
	private static boolean isOpenIpsCurrentMonthForClosedLastMonth(DBOrderModel order, String month)
			throws ParseException {

		return isNotPayed(order) && DateUtils.isDateBeetween(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	// for last month=opened
	private static boolean isOpenIpsPreviousMonthOpenLastMonth(DBOrderModel order, String month) throws ParseException {

		// aici e ori 0 , ori nu intra in cazul asta
		return isNotPayed(order) && DateUtils.isDateAfter(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	// for last month=closed
	private static boolean isOpenIpsPreviousMonthClosedLastMonth(DBOrderModel order, String month)
			throws ParseException {

		return isNotPayed(order) && DateUtils.isDateBefore(order.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	private static boolean isTermPurchaseIpsCurrentMonth(DBOrderModel order, String month) throws ParseException {

		return isNotPayedTermPurchase(order) && DateUtils.isDateBeetween(order.getScheduledDeliveryDate(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isTermPurchaseIpsUpcomingMonth(DBOrderModel order, String month) throws ParseException {

		return isNotPayedTermPurchase(order) && DateUtils.isDateAfter(order.getScheduledDeliveryDate(),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	// private static boolean
	// isReverseChargeeBackCurrentMonthFirstCondition(DBCreditMemoModel cm,
	// String month, String previousCommissiomRun, String nextComm) throws
	// ParseException {
	//
	// return (cm.getState().contentEquals("3")
	// && DateUtils.isDateBeetween(cm.getOrderCreatedAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month,
	// DateConstants.FORMAT),DateUtils.getLastDayOfAGivenMonth(month,
	// DateConstants.FORMAT), DateConstants.FORMAT)
	// && DateUtils.isDateBeetween(cm.getOrderPaidAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	// nextComm, DateConstants.FORMAT)
	// && DateUtils.isDateBefore(cm.getCreatedAt(), nextComm,
	// DateConstants.FORMAT));
	// }
	//
	// private static boolean
	// isReverseChargeBackCurrentMonthSecondCondition(DBCreditMemoModel cm,
	// String month, String previousCommissiomRun, String nextComm) throws
	// ParseException {
	//
	// return (cm.getState().contentEquals("3")
	// && DateUtils.isDateBefore(cm.getOrderCreatedAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	// DateConstants.FORMAT)
	// && DateUtils.isDateBefore(cm.getOrderPaidAt(), nextComm,
	// DateConstants.FORMAT)
	// && DateUtils.isDateBeetween(cm.getCreatedAt(), previousCommissiomRun,
	// nextComm, DateConstants.FORMAT));
	//
	// }
	//
	// private static boolean
	// isChargeBackCurrentMonthFirstCondition(DBCreditMemoModel cm, String
	// month, String previousCommissiomRun, String nextComm) throws
	// ParseException {
	//
	// return (cm.getState().contentEquals("2")
	// && DateUtils.isDateBeetween(cm.getOrderCreatedAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month,
	// DateConstants.FORMAT),DateUtils.getLastDayOfAGivenMonth(month,
	// DateConstants.FORMAT), DateConstants.FORMAT)
	// && DateUtils.isDateBeetween(cm.getOrderPaidAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	// nextComm, DateConstants.FORMAT)
	// && DateUtils.isDateBefore(cm.getCreatedAt(), nextComm,
	// DateConstants.FORMAT));
	// }
	//
	// private static boolean
	// isChargeBackCurrentMonthSecondCondition(DBCreditMemoModel cm, String
	// month, String previousCommissiomRun, String nextComm) throws
	// ParseException {
	//
	// return (cm.getState().contentEquals("2")
	// && DateUtils.isDateBefore(cm.getOrderCreatedAt(),
	// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	// DateConstants.FORMAT)
	// && DateUtils.isDateBefore(cm.getOrderPaidAt(), nextComm,
	// DateConstants.FORMAT) &&
	// DateUtils.isDateBeetween(cm.getCreatedAt(),
	// previousCommissiomRun,nextComm, DateConstants.FORMAT));
	//
	// }

	private static boolean isReverseChargeeBackCurrentMonthFirstCondition(DBCreditMemoModel cm, String month,
			String previousCommissiomRun, String nextComm) throws ParseException {

		return (cm.getState().contentEquals("3")
				&& DateUtils.isDateBeetween(cm.getOrderCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getOrderPaidAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), nextComm, DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT));
	}

	private static boolean isReverseChargeBackCurrentMonthSecondCondition(DBCreditMemoModel cm, String month,
			String previousCommissiomRun, String nextComm) throws ParseException {

		return (cm.getState().contentEquals("3")
				&& DateUtils.isDateBefore(cm.getOrderCreatedAt(),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getOrderPaidAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getCreatedAt(),
						DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT));

	}

	/*
	 * private static boolean
	 * isChargeBackCurrentMonthFirstCondition(DBCreditMemoModel cm, String
	 * month, String previousCommissiomRun, String nextComm) throws
	 * ParseException {
	 * 
	 * return (cm.getState().contentEquals("2") &&
	 * DateUtils.isDateBeetween(cm.getOrderCreatedAt(),
	 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateConstants.FORMAT) && DateUtils.isDateBeetween(cm.getOrderPaidAt(),
	 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * nextComm, DateConstants.FORMAT) &&
	 * DateUtils.isDateBeetween(cm.getCreatedAt(),
	 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateConstants.FORMAT)); }
	 */

	/*
	 * private static boolean
	 * isChargeBackCurrentMonthSecondCondition(DBCreditMemoModel cm, String
	 * month, String previousCommissiomRun, String nextComm) throws
	 * ParseException {
	 * 
	 * return (cm.getState().contentEquals("2") &&
	 * DateUtils.isDateBefore(cm.getOrderCreatedAt(),
	 * DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateConstants.FORMAT) && DateUtils.isDateBefore(cm.getOrderPaidAt(),
	 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateConstants.FORMAT) && DateUtils.isDateBeetween(cm.getCreatedAt(),
	 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
	 * DateConstants.FORMAT));
	 * 
	 * }
	 */

	private static boolean isChargeBackCurrentMonthSecondCondition(DBCreditMemoModel cm, String month,
			String previousCommissiomRun, String nextComm) throws ParseException {

		/*
		 * return (cm.getState().contentEquals("2") &&
		 * DateUtils.isDateBefore(cm.getOrderCreatedAt(),
		 * DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
		 * DateConstants.FORMAT) &&
		 * DateUtils.isDateBeetween(cm.getOrderPaidAt(),
		 * DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
		 * DateConstants.FORMAT) &&
		 * DateUtils.isDateBeetween(cm.getOrderPaidAt(), previousCommissiomRun,
		 * nextComm, DateConstants.FORMAT)
		 * 
		 * && DateUtils.isDateBeetween(cm.getCreatedAt(), previousCommissiomRun,
		 * nextComm, DateConstants.FORMAT));
		 */

		System.out.println(cm);

		return (cm.getState().contentEquals("2")

				&& DateUtils.isDateAfter(cm.getOrderPaidAt(), previousCommissiomRun, "yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateAfter(cm.getCreatedAt(), previousCommissiomRun, "yyyy-MM-dd HH:mm:ss"));

	}

	private static boolean isCreditMemoForCurrentMonthIfLastMonthIsOpened(DBCreditMemoModel cm, String month,
			String previousCommissiomRun, String nextComm) throws ParseException {

		System.out.println(cm.getOrderIncrementId() + "   " + cm.getOrderPaidAt());
		// return (DateUtils.isDateBeetween(cm.getOrderCreatedAt(),
		// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
		// DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT),
		// DateConstants.FORMAT)
		//
		// && DateUtils.isDateBeetween(cm.getOrderPaidAt(),
		// DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
		// nextComm, DateConstants.FORMAT)
		// && DateUtils.isDateBefore(cm.getCreatedAt(), nextComm,
		// DateConstants.FORMAT));
		return (
		// emilian
		DateUtils.isDateAfter(cm.getOrderPaidAt(), previousCommissiomRun, DateConstants.FORMAT));
		// && DateUtils.isDateAfter(cm.getCreatedAt(), previousCommissiomRun,
		// DateConstants.FORMAT));

	}

	private static boolean isCreditMemoForTop(DBCreditMemoModel cm, String month, String takeOffPhaseStart,
			String takeOffPhaseStop) throws ParseException {

		System.out.println(cm.getOrderIncrementId() + "   " + cm.getOrderPaidAt());
		return (

		DateUtils.isDateAfter(cm.getOrderPaidAt(), takeOffPhaseStart, DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getCreatedAt(), takeOffPhaseStop, DateConstants.FORMAT));

	}

	private static boolean isCreditMemoForCurrentMonthIfLastMonthIsClosed(DBCreditMemoModel cm, String month,
			String previousCommissiomRun, String nextComm) throws ParseException {

		return (DateUtils.isDateBefore(cm.getOrderCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getOrderPaidAt(), nextComm, DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getCreatedAt(), previousCommissiomRun, nextComm, DateConstants.FORMAT));

	}

	/**
	 * @param cm
	 * @param month
	 * @return all chargebacks with state 2 and 3 of type DEFAULT created in
	 *         previous and current months
	 * @throws ParseException
	 */
	private static boolean isOpenChargeback(DBCreditMemoModel cm, String month) throws ParseException {

		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return DateUtils.isDateBeetween(cm.getCreatedAt(),
				DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
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

	private static boolean isNotPayed(DBOrderModel model) {
		boolean found = false;
		for (String status : notPayedStatusesList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	private static boolean isNotPayedTermPurchase(DBOrderModel model) {
		boolean found = false;
		for (String status : notPayedStatusesTPList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	public static String toAsciiString(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < str.length(); index++) {
			char c = str.charAt(index);
			int pos = ContextConstants.UNICODE.indexOf(c);
			if (pos > -1)
				sb.append(ContextConstants.PLAIN_ASCII.charAt(pos));
			else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws ParseException {
		IpOverviewModel model = new IpOverviewModel();
		// model=calculateIpOverviewForOpenMonthAndOpenedLastMonth("6013","2017-11-23
		// 00:00:00","2017-11-15 17:00:00","2017-11-24 17:00:00");
		// model=calculateIpOverviewForOpenMonthAndOpenedLastMonth("6409","2017-11-23
		// 00:00:00","2017-11-15 17:00:00","2017-11-24 17:00:00");

		// model=calculateIpOverviewForOpenMonthAndOpenedLastMonth("33","2018-04-10
		// 00:00:00","2018-04-09 17:00:00","2018-04-19 17:00:00");
		model = calculateIpOverviewForOpenMonthAndOpenedLastMonth("33", "2018-05-02 00:00:00", "2018-05-01 17:00:00",
				"2018-05-03 17:00:00");

		// model=
		// IpOverviewCalculations.calculateIpOverviewForOpenMonthAndClosedLastMonth("6013","2017-11-10
		// 00:00:00","2017-10-10 00:00:00","2017-11-15 17:00:00","2017-12-06
		// 17:00:00");

		// System.out.println(model.getIpLastMonth());
		// System.out.println(model.getIpThisMonth());
		//
		// System.out.println(model.getOpenIpTotal());
		//
		// System.out.println(model.getManualIpCorrection());
		//
		// System.out.println(model.getPaidOrdersThisMonth());

		// System.out.println(model);
		// // System.out.println("returns1
		// "+model.getManualCorections().size());
		// System.out.println( "returns"+model.getReturns().size());
		// System.out.println( "orders"+model.getPayedOrders());

		System.out.println("ip this month:" + model.getChargebacksThisMonth());
		System.out.println("returns in this month" + model.getReturnsThisMonth());
		System.out.println("size list " + model.getPayedOrders().size());
		List<IpOverViewPayedOrdersModel> lista = model.getPayedOrders();
		List<IpOverViewReturnsListModel> lista2 = model.getReturns();
		System.out.println("lista2 size :" + lista2.size());
		for (IpOverViewReturnsListModel ipOverViewReturnsListModel : lista2) {
			System.out.println("return: " + ipOverViewReturnsListModel);
		}
		for (IpOverViewPayedOrdersModel order : lista) {
			System.out.println(
					"order date: " + order.getOrderStatus() + " " + order.getOrderID() + " " + order.getOrderDate());

		}

		System.out.println(model);
		/*
		 * System.out.println("ip last month:"+model.getIpLastMonth());
		 * System.out.println("return in current month:"
		 * +model.getReturnsThisMonth());
		 */

		// // System.out.println(DateUtils.isDateBeetween("2016-12-30 12:00:00",
		// // DateUtils.getFirstDayOfAGivenMonth("2016-11-30 12:00:00",
		// // DateConstants.FORMAT),
		// // DateUtils.getLastDayOfAGivenMonth("2016-11-30 12:00:00",
		// // DateConstants.FORMAT),DateConstants.FORMAT)
		// // );
		// // List<DBOrderModel> allOrdersList =
		// // OrdersInfoMagentoCalls.getOrdersList("2513");
		// // lista ordere platite
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2016-11-05 12:00:00","2016-10-06
		// 12:00:00","2016-12-07 12:00:00","2016-12-10 12:00:00");
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2016-12-05 12:00:00","2016-09-15
		// 12:00:00","2016-11-17 12:00:00","2017-01-09 12:00:00");
		// // pentru decembrie
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2016-12-05 12:00:00","2016-11-17
		// 12:00:00","2016-12-06 12:00:00","2017-01-10 12:00:00");
		// // pentru ianuarie
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2017-01-05 12:00:00","2016-12-06
		// 12:00:00","2017-01-09 12:00:00","2017-02-10 12:00:00");
		// // pentru decembrie luna deschisa
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2016-12-05 00:00:00","2016-12-06
		// 00:00:00","2017-01-09 00:00:00");
		// // pentru ianuarie-OKKKK
		// // IpOverviewModel
		// // model=calculateIpOverview("2513","2017-01-05 00:00:00","2017-01-09
		// 00:00:00","2017-02-10 00:00:00");
		// IpOverviewModel model = calculateIpOverview("2513", "2017-02-05
		// 00:00:00", "2017-01-09 13:07:29", "2017-02-17 17:00:00");
		//
		// List<IpOverViewPayedOrdersModel> paidOrders = model.getPayedOrders();
		// List<IpOverViewReturnsListModel> returns = model.getReturns();
		// System.out.println("hhhhhhhi" + returns);
		//
		// // for (IpOverViewPayedOrdersModel ipOver : paidOrders) {
		// // //
		// // System.out.println(ipOver.getOrderID());
		// // System.out.println(ipOver.getOrderStatus());
		// // System.out.println(ipOver.getOrderDate());
		// // System.out.println(ipOver.getIp());
		// //
		// //
		// // // System.out.println(ipOver.getScheduledDelivery());
		// // //
		// // System.out.println("lungime"+paidOrders.size());
		// //
		// //
		// // }
		//
		// // for (IpOverViewReturnsListModel ipOver : returns) {
		// // System.out.println(ipOver.getIp());
		// // System.out.println(ipOver.getOrderId());
		// //
		// //
		// // }
		//
		// String ipsprevious = model.getPaidOrdersPreviosMonth();
		// System.out.println("Paid previous month:" + ipsprevious);
		// String current = model.getPaidOrdersThisMonth();
		// System.out.println(" Paid current month:" + current);
		// String reversed = model.getReverseChargebackThisMonth();
		// System.out.println("reversed ips this month:" + reversed);
		// String chargeback = model.getChargebacksThisMonth();
		// System.out.println("chargeback:" + chargeback);
		// String returnss = model.getReturnsThisMonth();
		// System.out.println("returns in current month:" + returnss);
		//
		// String open = model.getIpThisMonth();
		// System.out.println("open ips this month:" + open);
		// String openlast = model.getIpLastMonth();
		// System.out.println("open ips last month:" + openlast);
		// String openchargeback = model.getOpenChargebacks();
		// System.out.println("openchargeback:" + openchargeback);
		//
		// String tpthis = model.getIpTPOrdersThisMonth();
		// System.out.println("tp ips this month" + tpthis);
		// String tplast = model.getIpTPOrdersLastMonth();
		// System.out.println("tp ips previous" + tplast);
		//
		// //
		// System.out.println("ascii"+IpOverviewCalculations.toAsciiString("Ä"));
	}

}

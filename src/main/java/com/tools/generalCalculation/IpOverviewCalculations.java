package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.constants.DateConstants;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.utils.DateUtils;

public class IpOverviewCalculations {

	private static List<String> payedStatusesList = new ArrayList<String>(Arrays.asList("complete", "payment_complete", "closed"));

	private static List<String> notPayedStatusesList = new ArrayList<String>(Arrays.asList("pending_payment", "payment_in_progress", "pending_payment_hold"));

	public static IpOverviewModel calculateIpOverview(String stylistId, String month, String previousComissionRun, String lastComissionRun) throws NumberFormatException, ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);

		IpOverviewModel result = new IpOverviewModel();

		IpOverviewCalculations.calculateOrders(result, allOrdersList, month, lastComissionRun);
		IpOverviewCalculations.calculateCreditMemos(result, completeCMList, month);

		return result;
	}

	public static IpOverviewModel calculateOrders(IpOverviewModel ipOverviewModel, List<DBOrderModel> allOrdersList, String month, String lastComissionRun)
			throws NumberFormatException, ParseException {

		BigDecimal ipsPreviousMonth = BigDecimal.ZERO;
		BigDecimal ipsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal openIpsPreviousMonth = BigDecimal.ZERO;
		BigDecimal tpIpsCurrentMonth = BigDecimal.ZERO;
		BigDecimal tpIpUpcomingMonths = BigDecimal.ZERO;
		List<IpOverViewPayedOrdersModel> paidOrderList = new ArrayList<IpOverViewPayedOrdersModel>();

		for (DBOrderModel order : allOrdersList) {

			if (isOrderCompleteForCurrentMonth(order, month, lastComissionRun)) {
				// the method populateIpOverViewPayedOrdersModel(order) returns
				// a IpOverViewPayedOrdersModel add we add it directly in the
				// list
				paidOrderList.add(populateIpOverViewPayedOrdersModel(order));
			}

			if (isOrderCompatibleForIpCalculationPrevMonth(order, month)) {
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOpenIpsCurrentMonth(order, month)) {
				openIpsCurrentMonth = openIpsCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOpenIpsPreviousMonth(order, month)) {
				openIpsPreviousMonth = openIpsPreviousMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isTermPurchaseIpsCurrentMonth(order, month)) {
				tpIpsCurrentMonth = tpIpsCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isTermPurchaseIpsUpcomingMonth(order, month)) {
				tpIpUpcomingMonths = tpIpUpcomingMonths.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}

			// write methods for the rest
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

	public static IpOverviewModel calculateCreditMemos(IpOverviewModel result, List<DBCreditMemoModel> cmList, String month) throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;
		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();

		for (DBCreditMemoModel cm : cmList) {
			if (isReverseCharcheBackCurrentMonth(cm, month)) {
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
			}
			if (isCharcheBackCurrentMonth(cm, month)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
			}
			if (isOpenChargeback(cm, month)) {
				openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
			}
			if (isChargebackForCurrentMonth(cm, month)) {
				chargebacks.add(populateIpOverViewReturnsListModel(cm));
			}

			// write methods for the rest
		}
		result.setChargebacksThisMonth(String.valueOf(chargeBacksCurrentMonth));
		result.setReverseChargebackThisMonth(String.valueOf(reverseChargeBacksCurrentMonth));
		result.setOpenChargebacks(String.valueOf(reverseChargeBacksCurrentMonth));
		result.setReturns(chargebacks);

		return result;
	}

	private static IpOverViewPayedOrdersModel populateIpOverViewPayedOrdersModel(DBOrderModel order) {

		IpOverViewPayedOrdersModel ipOverviewPaidOrdersModel = new IpOverViewPayedOrdersModel();

		ipOverviewPaidOrdersModel.setOrderID(order.getIncrementId());
		ipOverviewPaidOrdersModel.setCustomerName(order.getOrderCustomerName());
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
		ipOverViewReturnsListModel.setRefundDate(cm.getCreatedAt());
		ipOverViewReturnsListModel.setRefundType(cm.getState());

		// we dont have the amount of the order on cm ,so we cannot populate
		// this field yet.please put this field on credit memo model(source is
		// the order, the same as for OrderIcrementId)
		// please continue with the rest

		return ipOverViewReturnsListModel;
	}

	private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order, String month, String lastComissionRun) throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBefore(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), lastComissionRun,
						DateUtils.getCurrentDate(DateConstants.FORMAT),DateConstants.FORMAT);

	}

	private static boolean isChargebackForCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {

		return DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
				DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalculationPrevMonth(DBOrderModel order, String month) throws ParseException {

		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalculationCurrentMonth(DBOrderModel order, String month) throws ParseException {
		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), month, DateConstants.FORMAT);
	}

	private static boolean isReverseCharcheBackCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {

		return cm.getState().contentEquals("3")
				&& DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isCharcheBackCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {

		return cm.getState().contentEquals("2")
				&& DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isOpenIpsCurrentMonth(DBOrderModel order, String month) throws ParseException {

		return isNotPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	private static boolean isOpenIpsPreviousMonth(DBOrderModel order, String month) throws ParseException {

		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return isNotPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT);

	}

	private static boolean isTermPurchaseIpsCurrentMonth(DBOrderModel order, String month) throws ParseException {
		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getScheduledDeliveryDate(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isTermPurchaseIpsUpcomingMonth(DBOrderModel order, String month) throws ParseException {
		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateAfter(order.getScheduledDeliveryDate(), DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
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

		return DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
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

}

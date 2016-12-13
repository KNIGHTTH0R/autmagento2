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

	private static List<String> notPayedStatusesList = new ArrayList<String>(Arrays.asList("processing","waiting_authorization","payment_review","payment_failed","payment_pending","payment_in_progress","pending_payment_hold", "pending"));
	private static List<String> notPayedStatusesTPList = new ArrayList<String>(Arrays.asList("pending_payment_hold"));

	public static IpOverviewModel calculateIpOverview(String stylistId, String month, String previousComissionRun, String lastComissionRun,String nextComissionRun) throws NumberFormatException,
			ParseException {

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList);

		IpOverviewModel result = new IpOverviewModel();

		result = IpOverviewCalculations.calculateOrders(result, allOrdersList, month, lastComissionRun,nextComissionRun);
		result = IpOverviewCalculations.calculateCreditMemos(result, completeCMList, month, previousComissionRun, lastComissionRun);

		return result;
	}

	public static IpOverviewModel calculateOrders(IpOverviewModel ipOverviewModel, List<DBOrderModel> allOrdersList, String month, String lastComissionRun, String nextComissionRun)
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
				ipsPreviousMonth = ipsPreviousMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOrderCompatibleForIpCalculationCurrentMonth(order, month, nextComissionRun)) {
				ipsCurrentMonth = ipsCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		
			if (isOpenIpsCurrentMonthForClosedLastMonth(order, month)) {
				openIpsCurrentMonth = openIpsCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
			if (isOpenIpsPreviousMonthClosedLastMonth(order, month)) {
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

	public static IpOverviewModel calculateCreditMemos(IpOverviewModel result, List<DBCreditMemoModel> cmList, String month,String previousCommissiomRun, String lastComissionRun) throws NumberFormatException, ParseException {

		BigDecimal chargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal reverseChargeBacksCurrentMonth = BigDecimal.ZERO;
		BigDecimal openChargebackPreviousAndCurrent = BigDecimal.ZERO;
		List<IpOverViewReturnsListModel> chargebacks = new ArrayList<IpOverViewReturnsListModel>();

		for (DBCreditMemoModel cm : cmList) {
		
			if (isReverseCharcheBackCurrentMonth(cm, month,previousCommissiomRun, lastComissionRun)) {
				
				reverseChargeBacksCurrentMonth = reverseChargeBacksCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
				
			}
			if (isChargeBackCurrentMonth(cm, month,previousCommissiomRun, lastComissionRun)) {
				chargeBacksCurrentMonth = chargeBacksCurrentMonth.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
			}
			if (isOpenChargeback(cm, month)) {
				openChargebackPreviousAndCurrent = openChargebackPreviousAndCurrent.add(BigDecimal.valueOf(Double.parseDouble(cm.getTotalIpRefunded())));
			}
			if (isCreditMemoForCurrentMonth(cm, month,previousCommissiomRun, lastComissionRun)) {
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

//	private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order, String month, String lastComissionRun) throws ParseException {
//
//		return isPayed(order) && DateUtils.isDateBefore(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
//				&& DateUtils.isDateBeetween(order.getPaidAt(), lastComissionRun, DateUtils.getCurrentDate(DateConstants.FORMAT), DateConstants.FORMAT);
//
//	}
	
//	private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order, String month, String lastComissionRun) throws ParseException {
//
//		return isPayed(order) && DateUtils.isDateAfter(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),DateConstants.FORMAT)
//				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
//				
//
//	}

//	private static boolean isOrderCompatibleForIpCalculationPrevMonth(DBOrderModel order, String month) throws ParseException {
//
//		String previousMonth = DateUtils.getPreviousMonth(month, DateConstants.FORMAT);
//
//		return isPayed(order)
//				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(previousMonth, DateConstants.FORMAT),
//						DateUtils.getLastDayOfAGivenMonth(previousMonth, DateConstants.FORMAT), DateConstants.FORMAT)
//				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
//						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
//	}
	
	private static boolean isOrderCompleteForCurrentMonth(DBOrderModel order, String month) throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}
	
	private static boolean isOrderCompatibleForIpCalculationPrevMonth(DBOrderModel order, String month, String lastComissionRun,String nextComissionRun) throws ParseException {

		return isPayed(order)
				&& DateUtils.isDateBefore(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(order.getPaidAt(), lastComissionRun, nextComissionRun, DateConstants.FORMAT);
	}

	private static boolean isOrderCompatibleForIpCalculationCurrentMonth(DBOrderModel order, String month, String nextComissionRun) throws ParseException {
		return isPayed(order)
				&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&&(DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				|| DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), nextComissionRun, DateConstants.FORMAT));
	}
	
	
//	private static boolean isOrderCompatibleForIpCalculationCurrentMonth(DBOrderModel order, String month, String lastCommissionRun, String nextComissionRun) throws ParseException {
//		return isPayed(order)
//				&& DateUtils.isDateBeetween(order.getCreatedAt(),lastCommissionRun,
//						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
//				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
//				|| DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), nextComissionRun, DateConstants.FORMAT);
//	}
	
	//for last month=closed
		private static boolean isOpenIpsCurrentMonthForClosedLastMonth(DBOrderModel order, String month) throws ParseException {
			
			return isNotPayed(order)
					&& DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
							DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

		}
		//for  last month=opened
		private static boolean isOpenIpsPreviousMonthOpenLastMonth(DBOrderModel order, String month) throws ParseException {
	         
			//aici e ori 0 , ori nu intra in cazul asta
			return isNotPayed(order)
					&& DateUtils.isDateAfter(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
							 DateConstants.FORMAT);

		}

	//for  last month=opened
	private static boolean isOpenIpsCurrentMonthForOpenLastMonth(DBOrderModel order, String month , String lastCommissionRun) throws ParseException {
			
		return isNotPayed(order)
				&& DateUtils.isDateAfter(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);

	}
	
	//for last month=closed
	private static boolean isOpenIpsPreviousMonthClosedLastMonth(DBOrderModel order, String month) throws ParseException {

		return isNotPayed(order)
				&& DateUtils.isDateBefore(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
					 DateConstants.FORMAT);

	}

	private static boolean isTermPurchaseIpsCurrentMonth(DBOrderModel order, String month) throws ParseException {
		
		return isNotPayedTermPurchase(order)
				&& DateUtils.isDateBeetween(order.getScheduledDeliveryDate(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

	private static boolean isTermPurchaseIpsUpcomingMonth(DBOrderModel order, String month) throws ParseException {
		
		return isNotPayedTermPurchase(order)
				&& DateUtils.isDateAfter(order.getScheduledDeliveryDate(),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
	}

//	private static boolean isReverseCharcheBackCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {
//
//		return cm.getState().contentEquals("3")
//				&& DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
//						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
//	}
//
//	private static boolean isChargeBackCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {
//
//		return cm.getState().contentEquals("2")
//				&& DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
//						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
//	}
//	
//	private static boolean isCreditMemoForCurrentMonth(DBCreditMemoModel cm, String month) throws ParseException {
//		
//		return DateUtils.isDateBeetween(cm.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
//						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT);
//	}
	
	private static boolean isReverseCharcheBackCurrentMonth( DBCreditMemoModel cm, String month, String previousCommissiomRun, String lastCommissiomRun) throws ParseException {

		return (cm.getState().contentEquals("3")
				&& DateUtils.isDateBeetween(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getOrderPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						lastCommissiomRun, DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getCreatedAt(),lastCommissiomRun, DateConstants.FORMAT))
	            || (cm.getState().contentEquals("3")
	            		&& DateUtils.isDateBefore(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	    						 DateConstants.FORMAT)
	    				&& DateUtils.isDateAfter(cm.getOrderPaidAt(),lastCommissiomRun, DateConstants.FORMAT)
	    				&& DateUtils.isDateBeetween(cm.getCreatedAt(),previousCommissiomRun, lastCommissiomRun, DateConstants.FORMAT));
	}

	private static boolean isChargeBackCurrentMonth(DBCreditMemoModel cm, String month, String previousCommissiomRun, String lastCommissiomRun) throws ParseException {

		return (cm.getState().contentEquals("2")
				&& DateUtils.isDateBeetween(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getOrderPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						lastCommissiomRun, DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getCreatedAt(),lastCommissiomRun, DateConstants.FORMAT))
	            || (cm.getState().contentEquals("2")
	            		&& DateUtils.isDateBefore(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	    						 DateConstants.FORMAT)
	    				&& DateUtils.isDateAfter(cm.getOrderPaidAt(),lastCommissiomRun, DateConstants.FORMAT)
	    				&& DateUtils.isDateBeetween(cm.getCreatedAt(),previousCommissiomRun, lastCommissiomRun, DateConstants.FORMAT));
	}
	
	private static boolean isCreditMemoForCurrentMonth(DBCreditMemoModel cm, String month, String previousCommissiomRun, String lastCommissiomRun) throws ParseException {
		
		return (DateUtils.isDateBeetween(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						DateUtils.getLastDayOfAGivenMonth(month, DateConstants.FORMAT), DateConstants.FORMAT)
				&& DateUtils.isDateBeetween(cm.getOrderPaidAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
						lastCommissiomRun, DateConstants.FORMAT)
				&& DateUtils.isDateBefore(cm.getCreatedAt(),lastCommissiomRun, DateConstants.FORMAT))
	            || (DateUtils.isDateBefore(cm.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(month, DateConstants.FORMAT),
	    						 DateConstants.FORMAT)
	    				&& DateUtils.isDateAfter(cm.getOrderPaidAt(),lastCommissiomRun, DateConstants.FORMAT)
	    				&& DateUtils.isDateBeetween(cm.getCreatedAt(),previousCommissiomRun, lastCommissiomRun, DateConstants.FORMAT));
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
	
	private static boolean isNotPayedTermPurchase(DBOrderModel model) {
		boolean found = false;
		for (String status : notPayedStatusesTPList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	
	public static void main(String[] args) throws ParseException{
//		System.out.println(DateUtils.isDateBeetween("2016-12-30 12:00:00", 
//				DateUtils.getFirstDayOfAGivenMonth("2016-11-30 12:00:00", DateConstants.FORMAT),
//				DateUtils.getLastDayOfAGivenMonth("2016-11-30 12:00:00", DateConstants.FORMAT),DateConstants.FORMAT)
//				);
//		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList("2513");
		//lista ordere platite
		IpOverviewModel model=calculateIpOverview("1030","2016-11-05 12:00:00","2016-10-06 12:00:00","2016-11-07 12:00:00","2016-12-10 12:00:00");
//		IpOverviewModel model=calculateIpOverview("1355","2016-11-05 12:00:00","2016-10-06 12:00:00","2016-11-07 12:00:00","2016-12-10 12:00:00");
		List<IpOverViewPayedOrdersModel> paidOrders =model.getPayedOrders();
		List<IpOverViewReturnsListModel> returns=model.getReturns();
	
		for (IpOverViewPayedOrdersModel ipOver : paidOrders) {
			System.out.println(ipOver.getOrderID());
			System.out.println(ipOver.getOrderStatus());
			System.out.println(ipOver.getOrderDate());
			System.out.println(ipOver.getIp());
			System.out.println("lungime"+paidOrders.size());
			
		}
		
		for (IpOverViewReturnsListModel return1 : returns) {
			System.out.println("ererererer"+return1.getIp());
			System.out.println(return1.getOrderId());
			System.out.println(return1.getAmount());
			System.out.println("lungime"+paidOrders.size());
		}
		
		
	
		String ipsprevious=model.getPaidOrdersPreviosMonth();
		System.out.println("previous"+ipsprevious);
		String current=model.getPaidOrdersThisMonth();
		System.out.println("current month"+current);
		String reversed=model.getReverseChargebackThisMonth();
		System.out.println("reversed month"+reversed);
		String open=model.getIpThisMonth();
		System.out.println("open month"+open);
		String openlast=model.getIpLastMonth();
		System.out.println("open month last"+openlast);
		String chargeback=model.getChargebacksThisMonth();
		System.out.println("open month"+openlast);
		
		String tpthis=model.getIpTPOrdersThisMonth();
		System.out.println("previous"+tpthis);
		String tplast=model.getIpTPOrdersLastMonth();
		System.out.println("previous"+tplast);
		}
	
	
}

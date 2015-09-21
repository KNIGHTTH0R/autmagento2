package com.tools.calculation;

import java.math.BigDecimal;
import java.text.ParseException;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.utils.DateUtils;

public class ClosedMonthBonusCalculation {

	public static BigDecimal calculateClosedMonthBonuses(String stylistId, String startDate, String endDate) throws NumberFormatException, ParseException {
		BigDecimal ipForOrders = OrdersInfoMagentoCalls.calculateTotalIpOnPreviousMonth(stylistId, startDate, endDate);
		BigDecimal ipForCreditMemos = CreditMemosInfoMagentoCalls.calculateTotalIpsForCreditMemos(stylistId, startDate, endDate);
		System.out.println(ipForOrders.add(ipForCreditMemos));

		return ipForOrders.add(ipForCreditMemos);

	}

	public static void main(String[] args) throws NumberFormatException, ParseException {
		ClosedMonthBonusCalculation.calculateClosedMonthBonuses("1835", "2015-08-15 00:00:00", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
	}

}

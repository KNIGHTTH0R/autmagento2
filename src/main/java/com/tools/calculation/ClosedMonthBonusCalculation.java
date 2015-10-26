package com.tools.calculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.data.backend.IpModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;

public class ClosedMonthBonusCalculation {

	public static RewardPointsOfStylistModel calculateClosedMonthBonuses(String stylistId, String startDate, String endDate) throws NumberFormatException, ParseException {

		RewardPointsOfStylistModel result = new RewardPointsOfStylistModel();
		BigDecimal totalIp = BigDecimal.ZERO;

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemosInfoMagentoCalls.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList, stylistId, startDate);

		BigDecimal ipForOrders = OrdersInfoMagentoCalls.calculateTotalIpOnPreviousMonth(allOrdersList, stylistId, startDate, endDate);
		BigDecimal ipForCreditMemos = CreditMemosInfoMagentoCalls.calculateTotalIpsForCreditMemos(completeCMList, stylistId, startDate, endDate);

		totalIp = totalIp.add(ipForOrders);
		totalIp = totalIp.add(ipForCreditMemos);

		System.out.println("total ip: " + totalIp);

		result.setJewelryBonus(String.valueOf(determineJewelryBonusAmount(totalIp)));
		result.setMarketingMaterialBonus(String.valueOf(determineMarketingMaterialBonusAmount(totalIp)));

		System.out.println(result.getJewelryBonus());
		System.out.println(result.getMarketingMaterialBonus());

		return result;

	}

	public static IpModel calculateCurrentMonthBonuses(String stylistId, String startDate, String endDate) throws NumberFormatException, ParseException {

		IpModel result = new IpModel();
		BigDecimal totalIp = BigDecimal.ZERO;

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		System.out.println("orders done !!!   " + allOrdersList.size());
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		System.out.println("credit memos done !!!   " + creditMemoList.size());
		List<DBCreditMemoModel> completeCMList = CreditMemosInfoMagentoCalls.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList, stylistId, startDate);

		BigDecimal ipForOrders = OrdersInfoMagentoCalls.calculateTotalIpOnPreviousMonth(allOrdersList, stylistId, startDate, endDate);
		System.out.println("orders calculated !!!   " + allOrdersList.size());
		BigDecimal ipForCreditMemos = CreditMemosInfoMagentoCalls.calculateTotalIpsForCreditMemos(completeCMList, stylistId, startDate, endDate);
		System.out.println("cm calculated !!!   " + allOrdersList.size());
		totalIp = totalIp.add(ipForOrders);
		totalIp = totalIp.add(ipForCreditMemos);
		System.out.println("dsddddddddddddddddddddddd      " + totalIp);

		BigDecimal unsafeIpForOrders = OrdersInfoMagentoCalls.calculateTotalUnsafeIpOnCurrentMonth(allOrdersList, stylistId, endDate);

		result.setIp(String.valueOf(totalIp.intValue()));
		result.setUnsafeIp(String.valueOf(unsafeIpForOrders.intValue()));

		System.out.println(result.getIp());
		System.out.println(result.getUnsafeIp());

		return result;

	}

	public static RewardPointsOfStylistModel calculateRewardPoints(RewardPointsOfStylistModel model1, RewardPointsOfStylistModel model2) {

		RewardPointsOfStylistModel result = new RewardPointsOfStylistModel();
		result.setJewelryBonus(String.valueOf(BigDecimal.valueOf(Double.parseDouble(model1.getJewelryBonus())).add(
				BigDecimal.valueOf(Double.parseDouble(model2.getJewelryBonus())).setScale(4))));
		result.setMarketingMaterialBonus(String.valueOf(BigDecimal.valueOf(Double.parseDouble(model1.getMarketingMaterialBonus())).add(
				BigDecimal.valueOf(Double.parseDouble(model2.getMarketingMaterialBonus())).setScale(4))));

		return result;
	}

	public static BigDecimal determineJewelryBonusAmount(BigDecimal totalIp) {
		BigDecimal jewelry = BigDecimal.ZERO;

		if (isBetween(totalIp, BigDecimal.valueOf(750), BigDecimal.valueOf(1000))) {
			jewelry = totalIp.multiply(BigDecimal.valueOf(0.03));
		} else if (isBetween(totalIp, BigDecimal.valueOf(1000), BigDecimal.valueOf(100000000))) {
			jewelry = totalIp.multiply(BigDecimal.valueOf(0.05));
		}
		return jewelry;
	}

	public static BigDecimal determineMarketingMaterialBonusAmount(BigDecimal totalIp) {
		BigDecimal marketingMaterial = BigDecimal.ZERO;

		if (isBetween(totalIp, BigDecimal.valueOf(500), BigDecimal.valueOf(1500))) {
			marketingMaterial = totalIp.multiply(BigDecimal.valueOf(0.03));
		} else if (isBetween(totalIp, BigDecimal.valueOf(1500), BigDecimal.valueOf(100000000))) {
			marketingMaterial = totalIp.multiply(BigDecimal.valueOf(0.05));
		}
		return marketingMaterial;
	}

	public static boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}

	public static void main(String[] args) throws NumberFormatException, ParseException {
		ClosedMonthBonusCalculation.calculateCurrentMonthBonuses("1835", "2015-10-26 00:00:00", "2015-10-26 12:00:00");
	}
}

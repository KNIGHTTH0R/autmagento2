package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.data.backend.IpModel;
import com.tools.data.backend.RewardPointsOfStylistModel;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.env.constants.ComissionConfigConstants;

public class ClosedMonthBonusCalculation {

	public static RewardPointsOfStylistModel calculateClosedMonthBonuses(String stylistId, String activationDate, String startDate, String endDate) throws NumberFormatException,
			ParseException {

		RewardPointsOfStylistModel result = new RewardPointsOfStylistModel();
		BigDecimal totalIp = BigDecimal.ZERO;
		BigDecimal totalIpForTob = BigDecimal.ZERO;

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		System.out.println("orders list size: " + allOrdersList.size());
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList, stylistId, startDate);
		System.out.println("creditMemo list size: " + creditMemoList.size());
		
		BigDecimal ipForOrders = OrdersCalculation.calculateTotalIpOnPreviousMonth(allOrdersList, stylistId, startDate, endDate);
		BigDecimal ipForCreditMemos = CreditMemoCalculation.calculateTotalIpsForCreditMemos(completeCMList, stylistId, startDate, endDate);

		totalIp = totalIp.add(ipForOrders);
		totalIp = totalIp.add(ipForCreditMemos);

		BigDecimal tobIpForOrders = OrdersCalculation.calculateTotalIpFromOrdersInTakeOfPeriod(allOrdersList, stylistId, activationDate, startDate);
		BigDecimal tobIpForCreditMemos = CreditMemoCalculation.calculateTotalIpsForCreditMemosInTakeOfPeriod(completeCMList, stylistId, activationDate, startDate);

		totalIpForTob = totalIpForTob.add(tobIpForOrders);
		totalIpForTob = totalIpForTob.add(tobIpForCreditMemos);

		System.out.println("TOB jewelry " + determineJewelryBonusAmountForTob(totalIpForTob));

		result.setJewelryBonus(String.valueOf(determineJewelryBonusAmount(totalIp).add(determineJewelryBonusAmountForTob(totalIpForTob))));
		result.setMarketingMaterialBonus(String.valueOf(determineMarketingMaterialBonusAmount(totalIp)));

		System.out.println(result.getJewelryBonus());
		System.out.println(result.getMarketingMaterialBonus());

		return result;

	}

	public static IpModel calculateCurrentMonthBonuses(String stylistId, String startDate, String endDate) throws NumberFormatException, ParseException {

		IpModel result = new IpModel();
		BigDecimal totalIp = BigDecimal.ZERO;

		List<DBOrderModel> allOrdersList = OrdersInfoMagentoCalls.getOrdersList(stylistId);
		System.out.println("orders list size: " + allOrdersList.size());
	
		List<DBCreditMemoModel> creditMemoList = CreditMemosInfoMagentoCalls.getCreditMemosList(stylistId);
		List<DBCreditMemoModel> completeCMList = CreditMemoCalculation.populateCreditMemosListWithOrderDetails(creditMemoList, allOrdersList, stylistId, startDate);

		BigDecimal ipForOrders = OrdersCalculation.calculateTotalIpOnPreviousMonth(allOrdersList, stylistId, startDate, endDate);
		BigDecimal ipForCreditMemos = CreditMemoCalculation.calculateTotalIpsForCreditMemos(completeCMList, stylistId, startDate, endDate);
		totalIp = totalIp.add(ipForOrders);
		totalIp = totalIp.add(ipForCreditMemos);

		BigDecimal unsafeIpForOrders = OrdersCalculation.calculateTotalUnsafeIpOnCurrentMonth(allOrdersList, stylistId, endDate);

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

		if (isBetween(totalIp, BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_FIRST_LIMIT), BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_SECOND_LIMIT))) {
			jewelry = totalIp.multiply(BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_FIRST_RANGE_PERCENTAGE));
		} else if (isBetween(totalIp, BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_SECOND_LIMIT), BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_LAST_LIMIT))) {
			jewelry = totalIp.multiply(BigDecimal.valueOf(ComissionConfigConstants.JEWERLY_SECOND_RANGE_PERCENTAGE));
		}
		return jewelry;
	}

	public static BigDecimal determineJewelryBonusAmountForTob(BigDecimal totalIpForTob) {

		BigDecimal jewelry = BigDecimal.ZERO;

		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		int tobNumber = totalIpForTob.divide(BigDecimal.valueOf(ComissionConfigConstants.TOB_MARGIN_VALUE), mc).intValue();
		tobNumber = tobNumber < ComissionConfigConstants.TOB_MAX_NO ? tobNumber : ComissionConfigConstants.TOB_MAX_NO;
		jewelry = BigDecimal.valueOf(tobNumber).multiply(BigDecimal.valueOf(ComissionConfigConstants.TOB_VALUE));

		return jewelry;
	}

	public static BigDecimal determineMarketingMaterialBonusAmount(BigDecimal totalIp) {
		BigDecimal marketingMaterial = BigDecimal.ZERO;

		if (isBetween(totalIp, BigDecimal.valueOf(ComissionConfigConstants.MARKETING_FIRST_LIMIT), BigDecimal.valueOf(ComissionConfigConstants.MARKETING_SECOND_LIMIT))) {
			marketingMaterial = totalIp.multiply(BigDecimal.valueOf(ComissionConfigConstants.MARKETING_FIRST_RANGE_PERCENTAGE));
		} else if (isBetween(totalIp, BigDecimal.valueOf(ComissionConfigConstants.MARKETING_SECOND_LIMIT), BigDecimal.valueOf(ComissionConfigConstants.MARKETING_LAST_LIMIT))) {
			marketingMaterial = totalIp.multiply(BigDecimal.valueOf(ComissionConfigConstants.MARKETING_SECOND_RANGE_PERCENTAGE));
		}
		return marketingMaterial;
	}

	public static boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}

	public static void main(String[] args) throws NumberFormatException, ParseException {
//		ClosedMonthBonusCalculation.calculateClosedMonthBonuses("1835","2015-12-15 01:00:00", "2016-02-15 00:00:00", "2016-03-08 15:00:00");
//		ClosedMonthBonusCalculation.calculateCurrentMonthBonuses("1835","2016-02-23 00:00:00", "2016-02-23 16:00:00");
	}
}

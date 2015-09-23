package com.tools.calculation;

import java.math.BigDecimal;
import java.text.ParseException;

import com.connectors.http.CreditMemosInfoMagentoCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.tools.data.backend.RewardPointsOfStylistModel;

public class ClosedMonthBonusCalculation {

	public static BigDecimal calculateClosedMonthBonuses(String stylistId, String startDate, String endDate) throws NumberFormatException, ParseException {
		BigDecimal ipForOrders = OrdersInfoMagentoCalls.calculateTotalIpOnPreviousMonth(stylistId, startDate, endDate);
		BigDecimal ipForCreditMemos = CreditMemosInfoMagentoCalls.calculateTotalIpsForCreditMemos(stylistId, startDate, endDate);
		System.out.println("tolal IP received: " + ipForOrders.add(ipForCreditMemos));

		return ipForOrders.add(ipForCreditMemos);

	}

	public static void main(String[] args) throws NumberFormatException, ParseException {
		ClosedMonthBonusCalculation.calculateClosedMonthBonuses("1835", "2015-08-15 00:00:00", "2015-09-23 00:00:00");
	}

//	public static RewardPointsOfStylistModel calculateRewards(RewardPointsOfStylistModel model1, RewardPointsOfStylistModel model2) {
//
//		RewardPointsOfStylistModel result = new RewardPointsOfStylistModel();
//		result.setJewelryBonus(String.valueOf(BigDecimal.valueOf(Double.parseDouble(model1.getJewelryBonus())).add(BigDecimal.valueOf(Double.parseDouble(model2.getJewelryBonus())))));
//		result.setMarketingMaterialBonus(marketingMaterialBonus);
//		return result;
//	}
}

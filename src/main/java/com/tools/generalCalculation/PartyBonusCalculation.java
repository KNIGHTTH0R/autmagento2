package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.data.soap.DBOrderModel;

public class PartyBonusCalculation {
	
	private static List<String> notPayedStatusesList = new ArrayList<String>(Arrays.asList("processing","waiting_authorization","payment_review","payment_failed","payment_pending","payment_in_progress","pending_payment_hold", "pending"));

	
	private static boolean isNotPayed(PartyBonusCalculationModel model) {
		boolean found = false;
		for (String status : notPayedStatusesList) {
			if (model.getOrderStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}
	public static BigDecimal calculatePartyRetail(List<PartyBonusCalculationModel> ordersList) {
		BigDecimal partyRetail = BigDecimal.ZERO;

		for (PartyBonusCalculationModel order : ordersList) {

			BigDecimal orderRetail = BigDecimal.valueOf(Double.parseDouble(order.getTotal())).multiply(BigDecimal.valueOf(Double.parseDouble(order.getPercent())));
			orderRetail = orderRetail.divide(BigDecimal.valueOf(100));
			partyRetail = partyRetail.add(orderRetail);
		}

		return partyRetail.setScale(4);
	}

	public static BigDecimal calculatePartyTotal(List<PartyBonusCalculationModel> ordersList) {
		BigDecimal partyTotal = BigDecimal.ZERO;

		for (PartyBonusCalculationModel order : ordersList) {

			partyTotal = partyTotal.add(BigDecimal.valueOf(Double.parseDouble(order.getTotal())));
		}
		return partyTotal.setScale(2);
	}

	public static int calculatePartyIp(List<PartyBonusCalculationModel> ordersList) {
		BigDecimal partyTotal = BigDecimal.ZERO;

		for (PartyBonusCalculationModel order : ordersList) {

			partyTotal = partyTotal.add(BigDecimal.valueOf(Double.parseDouble(order.getIp())));
		}
		return partyTotal.setScale(2).intValue();
	}
	
	public static int calculatePartyIpInPayment(List<PartyBonusCalculationModel> ordersList) {
		BigDecimal partyTotal = BigDecimal.ZERO;

		
		for (PartyBonusCalculationModel order : ordersList) {
           if(isNotPayed(order)){
        	   
			partyTotal = partyTotal.add(BigDecimal.valueOf(Double.parseDouble(order.getIp())));
		}}
		return partyTotal.setScale(2).intValue();
	}

	public static BigDecimal calculatePartyJewelryBonus(List<PartyBonusCalculationModel> ordersList, String manualJwewlryBonus) {

		BigDecimal partyRetail = calculatePartyRetail(ordersList);
		BigDecimal partyJb = BigDecimal.ZERO;

		if (isBetween(partyRetail, BigDecimal.valueOf(250), BigDecimal.valueOf(500))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(10));
			partyJb = partyJb.divide(BigDecimal.valueOf(100).setScale(4, RoundingMode.HALF_UP));

		} else if (isBetween(partyRetail, BigDecimal.valueOf(500), BigDecimal.valueOf(1000))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(15));
			partyJb = partyJb.divide(BigDecimal.valueOf(100).setScale(4, RoundingMode.HALF_UP));

		} else if (isBetween(partyRetail, BigDecimal.valueOf(1000), BigDecimal.valueOf(100000))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(20));
			partyJb = partyJb.divide(BigDecimal.valueOf(100).setScale(4, RoundingMode.HALF_UP));
		}

		partyJb = partyJb.compareTo(BigDecimal.valueOf(35)) < 0 && ordersList.size() != 0 ? BigDecimal.valueOf(35).setScale(2, RoundingMode.HALF_UP) : partyJb.setScale(2,
				RoundingMode.HALF_UP);

		return partyJb.add(BigDecimal.valueOf(Double.parseDouble(manualJwewlryBonus)));
	}

	public static boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}

}

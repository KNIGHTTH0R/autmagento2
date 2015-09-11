package com.tools.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tools.data.frontend.PartyBonusCalculationModel;

public class PartyBonusCalculation {

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

	public static BigDecimal calculatePartyJewelryBonus(List<PartyBonusCalculationModel> ordersList) {

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

		return partyJb.compareTo(BigDecimal.valueOf(35)) < 0 && ordersList.size() !=0 ? BigDecimal.valueOf(35).setScale(2, RoundingMode.HALF_UP) : partyJb
				.setScale(2, RoundingMode.HALF_UP);
	}

	public static boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}

}

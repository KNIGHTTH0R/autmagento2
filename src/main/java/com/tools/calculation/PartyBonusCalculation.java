package com.tools.calculation;

import java.math.BigDecimal;
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

	public static BigDecimal calculatePartyJewelryBonus(List<PartyBonusCalculationModel> ordersList, boolean isFollowUp) {

		BigDecimal partyRetail = calculatePartyRetail(ordersList);
		BigDecimal partyJb = BigDecimal.ZERO;

		if (isBetween(partyRetail, BigDecimal.valueOf(250), BigDecimal.valueOf(500))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(10));
			partyJb = partyJb.divide(BigDecimal.valueOf(100));

		} else if (isBetween(partyRetail, BigDecimal.valueOf(500), BigDecimal.valueOf(1000))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(15));
			partyJb = partyJb.divide(BigDecimal.valueOf(100));

		} else if (isBetween(partyRetail, BigDecimal.valueOf(1000), BigDecimal.valueOf(100000))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(20));
			partyJb = partyJb.divide(BigDecimal.valueOf(100));
		}

		return partyJb.compareTo(BigDecimal.valueOf(35)) < 0 && isFollowUp ? BigDecimal.valueOf(35) : partyJb.setScale(4);
	}

	public static boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}

}

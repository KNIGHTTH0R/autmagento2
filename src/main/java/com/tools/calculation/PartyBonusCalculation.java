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

		return partyRetail;
	}

	public BigDecimal calculatePartyJewelryBonus(BigDecimal partyRetail, boolean isFollowUp) {
		BigDecimal partyJb = BigDecimal.ZERO;

		if (isBetween(partyRetail, BigDecimal.valueOf(250), BigDecimal.valueOf(250))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(10));
			partyJb = partyJb.divide(BigDecimal.valueOf(100));

		} else if (isBetween(partyRetail, BigDecimal.valueOf(500), BigDecimal.valueOf(1000))) {

			partyJb = partyRetail.multiply(BigDecimal.valueOf(10));
			partyJb = partyJb.divide(BigDecimal.valueOf(100));
		}

		return partyJb;
	}

	boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end) {
		return price.compareTo(start) >= 0 && price.compareTo(end) < 0;
	}
}

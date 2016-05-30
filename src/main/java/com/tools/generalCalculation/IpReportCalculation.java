package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.util.List;

import com.tools.data.frontend.TermPurchaseIpModel;

public class IpReportCalculation {

	public static TermPurchaseIpModel calculateTermPurchaseIps(List<TermPurchaseIpModel> list) {
		TermPurchaseIpModel result = new TermPurchaseIpModel();

		BigDecimal currentMonthIp = BigDecimal.ZERO;
		BigDecimal nextMonthIp = BigDecimal.ZERO;

		for (TermPurchaseIpModel termPurchaseIpModel : list) {
			currentMonthIp = currentMonthIp
					.add(BigDecimal.valueOf(Double.parseDouble(termPurchaseIpModel.getCurrentMonthIp())));
			nextMonthIp = nextMonthIp.add(BigDecimal.valueOf(Double.parseDouble(termPurchaseIpModel.getNextMonthIp())));
		}

		result.setCurrentMonthIp(String.valueOf(currentMonthIp));
		result.setNextMonthIp(String.valueOf(nextMonthIp));

		return result;
	}

}

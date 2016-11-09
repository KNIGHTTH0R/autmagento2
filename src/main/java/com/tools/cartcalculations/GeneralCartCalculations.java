package com.tools.cartcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.tools.data.frontend.ShippingModel;
import com.tools.utils.DateUtils;

public class GeneralCartCalculations {

	/**
	 * Calculates new shipping based on remaining sum if voucher is bigger than
	 * total amount
	 * 
	 * @param totalAmount
	 * @param ruleDiscount
	 * @param shipping
	 * @return
	 */
	public static String calculateNewShipping(BigDecimal totalAmount, BigDecimal ruleDiscount, BigDecimal shipping) {

		if (ruleDiscount.compareTo(totalAmount) > 0) {

			BigDecimal remainingSum = ruleDiscount.subtract(totalAmount);
			shipping = shipping.subtract(remainingSum);
			shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;
		}

		return String.valueOf(shipping.setScale(2, RoundingMode.HALF_UP));
	}

	public static String calculateAdyenTotal(ShippingModel... shippingModelsList) {

		BigDecimal total = BigDecimal.ZERO;

		for (ShippingModel list : shippingModelsList) {
			total = total.add(BigDecimal.valueOf(Double.parseDouble(list.getTotalAmount())));
		}
		return String.valueOf(total);
	}

	public static List<String> calculateDeliveryDates(String earliestAvailability, String unavailableStartDate,
			String unavailableEndDate, int maxExecDays, int maxDropdownDays) throws ParseException {

		List<String> availableDates = new ArrayList<String>();
		String firstDeliveryDate = DateUtils.getFirstFridayAfterDate(earliestAvailability, "yyyy-MM-dd");
		String lastDeliveryDate = DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd",
				maxExecDays);
		String lastDropdownDay = DateUtils.addDaysToAAGivenDate(firstDeliveryDate, "yyyy-MM-dd", maxDropdownDays);
		lastDeliveryDate = DateUtils.isDateBefore(lastDeliveryDate, lastDropdownDay, "yyyy-MM-dd") ? lastDeliveryDate
				: lastDropdownDay;
		availableDates = DateUtils.getFridaysBetweenDates(earliestAvailability, lastDeliveryDate, "yyyy-MM-dd");

		ListIterator<String> iter = availableDates.listIterator();
		while (iter.hasNext()) {
			if (DateUtils.isDateBeetween(iter.next(), unavailableStartDate, unavailableEndDate, "yyyy-MM-dd")) {
				iter.remove();
			}
		}

		return availableDates;
	}

	public static String calculateIpBasedOnSpecialPrice(String initialIp, String price, String specialPrice) {

		BigDecimal specialIps = BigDecimal.ZERO;

		BigDecimal initialProductIps = BigDecimal.valueOf(Double.parseDouble(initialIp));
		BigDecimal specialProductPrice = BigDecimal.valueOf(Double.parseDouble(specialPrice));
		BigDecimal productPrice = BigDecimal.valueOf(Double.parseDouble(price));

		specialIps = specialIps.add(specialProductPrice);
		specialIps = specialIps.divide(productPrice, 4, RoundingMode.HALF_UP);
		specialIps = specialIps.multiply(initialProductIps);

		return String.valueOf(specialIps.setScale(0, RoundingMode.HALF_UP));

	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(GeneralCartCalculations.calculateIpBasedOnSpecialPrice("63",
		// "75.00", "39.00"));
		List<String> dates = GeneralCartCalculations.calculateDeliveryDates("2016-11-11", "2016-11-20", "2016-11-30",
				45, 49);
		for (String date : dates) {
			System.out.println(date);
		}
	}

}

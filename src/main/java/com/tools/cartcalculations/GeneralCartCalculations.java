package com.tools.cartcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.soap.DBStylistModel;
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

	public static List<String> calculateDeliveryDates(String earliestAvailability, String mostAwayEarliest,
			String unavailableStartDate, String unavailableEndDate, int maxExecDays, int maxDropdownDays)
			throws ParseException {

		List<String> availableDates = new ArrayList<String>();
		String firstDeliveryDate = DateUtils.getFirstFridayAfterDate(mostAwayEarliest, "yyyy-MM-dd");
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

	public static List<String> getCommonDates(List<List<String>> lists) throws ParseException {

		List<String> commons = new ArrayList<String>();

		commons.addAll(lists.get(0));

		for (ListIterator<List<String>> iter = lists.listIterator(0); iter.hasNext();) {
			commons.retainAll(iter.next());
		}

		return commons;

	}

	public String getMostAwayEarliest(List<HostBasicProductModel> productList) {
		String earliestAvailability = "";
		
		for (HostBasicProductModel product : productList) {
			
		}
		
		return earliestAvailability;
	}
	
	public static List<String> sortDates(List<String> productList) {

		Collections.sort(productList, new Comparator<String>() {

			public int compare(String date1, String date2) {
				boolean isAfter = false;
				try {
					isAfter = DateUtils.isDateAfter(date2, date1, "yyyy-MM-dd");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (isAfter)
					return 1;
				if (!isAfter)
					return -1;
				return 0;
			}
		});

		return productList;
	}

	// public <T> Set<T> intersection(List<T>... list) {
	// Set<T> result = Sets.newHashSet(list[0]);
	// for (List<T> numbers : list) {
	// result = Sets.intersection(result, Sets.newHashSet(numbers));
	// }
	// return result;
	// }

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
		List<String> dates = GeneralCartCalculations.calculateDeliveryDates("2016-11-11", "2016-11-11",
				DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 14),
				DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 28), 45, 49);
		for (String date : dates) {
			System.out.println(date);
		}
	}

}

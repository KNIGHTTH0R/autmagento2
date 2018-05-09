package com.tools.cartcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import com.tools.data.frontend.ShippingModel;
import com.tools.data.soap.ProductDetailedModel;
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
			System.out.println("remainingSum " + remainingSum);

			shipping = shipping.subtract(remainingSum);
			shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;

		}

		System.out.println("value of shipping " + shipping);
		return String.valueOf(shipping.setScale(2, RoundingMode.HALF_UP));
	}

	public static String calculateNewShipping1(BigDecimal subTotal, BigDecimal ruleDiscount, BigDecimal shipping) {
		System.out.println("totalAmount" + subTotal);
		if (ruleDiscount.compareTo(subTotal) > 0) {

			BigDecimal remainingSum = ruleDiscount.subtract(subTotal);
			System.out.println("remainingSum " + remainingSum);
			if (remainingSum.compareTo(BigDecimal.valueOf(800)) == 1) {

				shipping = BigDecimal.valueOf(0);
			} else {
				shipping = shipping.subtract(remainingSum);
				shipping = shipping.compareTo(BigDecimal.ZERO) > 0 ? shipping : BigDecimal.ZERO;
			}

		} else {
			BigDecimal remainingSum2 = subTotal.subtract(ruleDiscount);
			
			if (remainingSum2.compareTo(BigDecimal.valueOf(800)) > -1) {

				shipping = BigDecimal.valueOf(0);
				System.out.println("firt gr" + shipping);
			}
		}

		System.out.println("value of shipping " + shipping);
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

	public static List<ProductDetailedModel> sortDates(List<ProductDetailedModel> datesList, final String format) {
		System.out.println("list size"+ datesList.size());
		System.out.println("format" +format);
		Collections.sort(datesList, new Comparator<ProductDetailedModel>() {

			
			public int compare(ProductDetailedModel o1, ProductDetailedModel o2) {
				
				boolean isAfter = false;
				try {
					isAfter = DateUtils.isDateAfter(o1.getStockData().getEarliestAvailability(),
							o2.getStockData().getEarliestAvailability(), format);
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

		return datesList;
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
		BigDecimal totalAmount = BigDecimal.valueOf(159.5);
		BigDecimal ruleDiscount = BigDecimal.valueOf(25);
		BigDecimal shipping = BigDecimal.valueOf(0.9);
		calculateNewShipping1(totalAmount, ruleDiscount, shipping);

		// System.out.println(GeneralCartCalculations.calculateIpBasedOnSpecialPrice("63",
		// "75.00", "39.00"));
		// List<String> dates =
		// GeneralCartCalculations.calculateDeliveryDates("2016-11-11",
		// "2016-11-11",
		// DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"),
		// "yyyy-MM-dd", 14),
		// DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"),
		// "yyyy-MM-dd", 28), 45, 49);
		// for (String date : dates) {
		// System.out.println(date);
		// }
	}

}

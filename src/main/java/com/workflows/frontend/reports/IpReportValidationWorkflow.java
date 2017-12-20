package com.workflows.frontend.reports;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;

import org.joda.time.format.FormatUtils;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.reports.ValidateIpsOverviewSteps;
import com.tools.CustomVerification;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.utils.DateUtils;

public class IpReportValidationWorkflow {
	@Steps
	ValidateIpsOverviewSteps validateIpSteps;
	
	@Title("Validate current month and next month ips")
	@Step
	public void validateIps(TermPurchaseIpModel expectedModel, TermPurchaseIpModel grabbedModel) {

		verifyCurrentMonthIps(expectedModel.getCurrentMonthIp(), grabbedModel.getCurrentMonthIp());
		verifyNextMonthIps(expectedModel.getNextMonthIp(), grabbedModel.getNextMonthIp());
	}

	@Step
	public void verifyNextMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Next month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}

	@Step
	public void verifyCurrentMonthIps(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue(
				"Failure: Current month ips don't match  - Expected: " + expectedValue + " Actual: " + grabbedValue,
				grabbedValue.contentEquals(expectedValue));
	}
	
	@Title("Validate Ips in Ip overview report")
	public void verifyIpOverviewReportDetails(IpOverViewSummaryModel grabbedModel,  IpOverviewModel expectedModel) {
		
		validateIpSteps.verifyPaidOrdersPreviousMonths(grabbedModel.getPaidOrdersPreviosMonth(), expectedModel.getPaidOrdersPreviosMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getPaidOrdersPreviosMonth());
		System.out.println("model validari expectedd"+ expectedModel.getPaidOrdersPreviosMonth());
		validateIpSteps.verifyPaidOrdersCurrentMonth(grabbedModel.getPaidOrdersThisMonth(), expectedModel.getPaidOrdersThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getPaidOrdersThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getPaidOrdersThisMonth());
		validateIpSteps.verifyReverseChargebacksThisMonth(grabbedModel.getReverseChargebackThisMonth(), expectedModel.getReverseChargebackThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getReverseChargebackThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getReverseChargebackThisMonth());
		validateIpSteps.verifyChargebacksCurrentMonth(grabbedModel.getChargebacksThisMonth(), expectedModel.getChargebacksThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getChargebacksThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getChargebacksThisMonth());
		validateIpSteps.verifyReturns(grabbedModel.getReturnsThisMonth(), expectedModel.getReturnsThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getReturnsThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getReturnsThisMonth());
	}
	
	
	@Title("Validate Ips in Ip overview report")
	@StepGroup
	public void verifyOpenIpFromOverviewReportDetailsNotCurrentMonth(IpOverViewOpenIpsModel grabbedModel, IpOverviewModel expectedModel) {

		verifyOpenIpsCurrentMonth(grabbedModel.getIpThisMonth(), expectedModel.getIpThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpThisMonth());
		verifyOpenIpsPreviousMonth(grabbedModel.getIpLastMonth(), expectedModel.getIpLastMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpLastMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpLastMonth());
		verifyOpenChargebacks(grabbedModel.getOpenChargebacks(), expectedModel.getOpenChargebacks());
		System.out.println("model validari grabbed"+ grabbedModel.getOpenChargebacks());
		System.out.println("model validari expectedd"+ expectedModel.getOpenChargebacks());
		
	}
	
	@Title("Validate Ips in Ip overview report")
	@StepGroup
	public void verifyOpenIpFromOverviewReportDetailsCurrentMonth(IpOverViewOpenIpsModel grabbedModel, IpOverviewModel expectedModel) {

		verifyOpenIpsCurrentMonth(grabbedModel.getIpThisMonth(), expectedModel.getIpThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpThisMonth());
		verifyOpenIpsPreviousMonth(grabbedModel.getIpLastMonth(), expectedModel.getIpLastMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpLastMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpLastMonth());
		verifyOpenChargebacks(grabbedModel.getOpenChargebacks(), expectedModel.getOpenChargebacks());
		System.out.println("model validari grabbed"+ grabbedModel.getOpenChargebacks());
		System.out.println("model validari expectedd"+ expectedModel.getOpenChargebacks());
		verifyIpInTermPurchaseCurrentMonth(grabbedModel.getIpTPOrdersThisMonth(), expectedModel.getIpTPOrdersThisMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpTPOrdersThisMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpTPOrdersThisMonth());
		verifyIpTermPurchasePreviousMonth(grabbedModel.getIpTPOrdersLastMonth(), expectedModel.getIpTPOrdersLastMonth());
		System.out.println("model validari grabbed"+ grabbedModel.getIpTPOrdersLastMonth());
		System.out.println("model validari expectedd"+ expectedModel.getIpTPOrdersLastMonth());
		
	}

	@Title("Validate Orders in Ip overview report")
	@StepGroup
	public void verifyPayedOrdersList(List<IpOverViewPayedOrdersModel> expectedList, List<IpOverViewPayedOrdersModel> grabbedList) throws ParseException {


		CustomVerification.verifyTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());


		for (IpOverViewPayedOrdersModel order : expectedList) {
			IpOverViewPayedOrdersModel compare = findOrder(order.getOrderID(),grabbedList);
			if (compare.getOrderID() != null) {
			
				validateOrderId(order.getOrderID(), compare.getOrderID());
				validateCustomerName(order.getCustomerName(), compare.getCustomerName());
				validateOrderDate(order.getOrderDate(), compare.getOrderDate());
				validatePaymentDate(order.getPaymentDate(), compare.getPaymentDate());
				validateOrderStatus(order.getOrderStatus(), compare.getOrderStatus());
				validateAmount(order.getAmount(), compare.getAmount());
				validateIp(order.getIp(), compare.getIp());

			} else {
				Assert.assertTrue("Failure: Could not validate all orders in the list", compare != null);
			}
//			int index = grabbedList.indexOf(compare);
//			if (index > -1) {
//
//				grabbedList.remove(index);
//
//			}
		}
//		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);

	}
	
	@Title("Validate Returns in Ip overview report")
	@StepGroup
	public void verifyReturnedOrdersList(List<IpOverViewReturnsListModel> expectedList, List<IpOverViewReturnsListModel> grabbedList) {

		CustomVerification.verifyTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (IpOverViewReturnsListModel returns : expectedList) {
			IpOverViewReturnsListModel compare = findReturnedOrder(returns.getOrderId(),grabbedList);
			if (compare.getOrderId() != null) {
			
				validateOrderIdForReturns(returns.getOrderId(), compare.getOrderId());
				validateReturnDate(returns.getRefundDate(), compare.getRefundDate());
				validateReturnType(returns.getRefundType(), compare.getRefundType());
				validateReturnedAmount(returns.getAmount(), compare.getAmount());
				validateReturnedIp(returns.getIp(), compare.getIp());

			} else {
				Assert.assertTrue("Failure: Could not validate all orders in the list", compare != null);
			}
//			int index = grabbedList.indexOf(compare);
//			if (index > -1) {
//
//				grabbedList.remove(index);

//			}
		}
		//Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);

	}
	
	private IpOverViewPayedOrdersModel findOrder(String orderId, List<IpOverViewPayedOrdersModel> grabbedList) {
		IpOverViewPayedOrdersModel result = new IpOverViewPayedOrdersModel();
		theFor: for (IpOverViewPayedOrdersModel item : grabbedList) {
			if (item.getOrderID().contains(orderId)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}
	
	private IpOverViewReturnsListModel findReturnedOrder(String orderId, List<IpOverViewReturnsListModel> grabbedList) {
		IpOverViewReturnsListModel result = new IpOverViewReturnsListModel();
		theFor: for (IpOverViewReturnsListModel item : grabbedList) {
			if (item.getOrderId().contains(orderId)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}
	
	@Step
	public void validateOrderId(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Order id doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateCustomerName(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Order id doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	@Step
	public void validateOrderDate(String expected, String grabbed) throws ParseException {
		System.out.println("expected " +expected);
		String expectedDate =DateUtils.parseDate(expected, "yyyy-mm-dd", "dd.mm.yyyy");
		
		
		System.out.println("expectedDate "+expectedDate);
		CustomVerification.verifyTrue("Failure: Order date doesn't match Expected: " + grabbed + " Actual: " + expectedDate,
				grabbed.contentEquals(expectedDate));
	}
	
	@Step
	public void validatePaymentDate(String expected , String grabbed) throws ParseException {
		System.out.println("expected " +expected);
		String expectedDate =DateUtils.parseDate(expected, "yyyy-mm-dd", "dd.mm.yyyy");
		
		
		CustomVerification.verifyTrue("Failure: Payment date doesn't match Expected: " + expectedDate + " Actual: " + grabbed,
				grabbed.contentEquals(expectedDate));
	}
	
	@Step
	public void validateOrderStatus(String expected, String grabbed) {
		String englishStatus=grabbed.contentEquals("Zahlung erfolgreich")?"payment_complete":grabbed;
		
		CustomVerification.verifyTrue("Failure: Order status doesn't match Expected: " + expected + " Actual: " + englishStatus,
				englishStatus.contentEquals(expected));
	}
	
	@Step
	public void validateAmount(String expected, String grabbed) {
		BigDecimal expectedGrandTotal = new BigDecimal(Double.parseDouble(expected)).setScale(2, RoundingMode.HALF_UP);
		CustomVerification.verifyTrue("Failure: Order amount doesn't match Expected: " + expectedGrandTotal + " Actual: " + grabbed,
				grabbed.contentEquals(expectedGrandTotal.toString()));
		
	}
	
	@Step
	public void validateIp(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Ips don't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateOrderIdForReturns(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Order id doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateReturnDate(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Return date  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateReturnType(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Return type  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateReturnedAmount(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Return amount  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void validateReturnedIp(String grabbed, String expected) {
		CustomVerification.verifyTrue("Failure: Return ip  doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	

//	@Step
//	public void verifyPaidOrdersPreviousMonths(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for paid orders from previous months doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyPaidOrdersCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for paid orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyReverseChargebacksThisMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for reversed charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyChargebacksCurrentMonth(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for charbacks from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
//	
//	@Step
//	public void verifyReturns(String grabbed, String expected) {
//		CustomVerification.verifyTrue("Failure: Ip value for returns doesn't match Expected: " + expected + " Actual: " + grabbed,
//				grabbed.contentEquals(expected));
//	}
	@Step
	public void verifyOpenIpsCurrentMonth(String grabbed, String expected) {
//		Assert.assertTrue("Failure: Open ips for current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				expected.contains(grabbed));
		CustomVerification.verifyTrue(
				"Failure: Open ips for current month doesn't match Expected:  " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	@Step
	public void verifyOpenIpsPreviousMonth(String grabbed, String expected) {
//		Assert.assertTrue("Failure: Open Ip value for previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				expected.contains(grabbed));
		CustomVerification.verifyTrue(
				"Failure: Open Ip value for previous month doesn't match Expected:  " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	@Step
	public void verifyOpenChargebacks(String grabbed, String expected) {
//		Assert.assertTrue("Failure: Open Ips value for charbacks from previous and current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				expected.contains(grabbed));
		CustomVerification.verifyTrue(
				"Failure: Open Ips value for charbacks from previous and current month doesn't match Expected:  " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	@Step
	public void verifyIpInTermPurchaseCurrentMonth(String grabbed, String expected) {
//		Assert.assertTrue("Failure: Ip value for term purchase orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				expected.contains(grabbed));
		CustomVerification.verifyTrue(
				"Failure: Ip value for term purchase orders from current month doesn't match Expected: " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	@Step
	public void verifyIpTermPurchasePreviousMonth(String grabbed, String expected) {
//		Assert.assertTrue("Failure: Ip value for term purchase orders from previous month doesn't match Expected: " + expected + " Actual: " + grabbed,
//				expected.contains(grabbed));
		CustomVerification.verifyTrue(
				"Failure: Ip value for term purchase orders from previous month doesn't match Expected:  " + expected + " Actual: " + grabbed,
				grabbed.contentEquals(expected));
	}
	
	public static void main(String[] args) {
		
	/*	BigDecimal value = BigDecimal.valueOf(100);
		BigDecimal vat = taxPercent.add(value).divide(value);
		BigDecimal shopTaxAmount = gtValue.subtract(gtValue.divide(vat, 2, RoundingMode.HALF_UP));*/
		String expected ="10.9999";
		BigDecimal grandTotal = new BigDecimal(Double.parseDouble(expected)).setScale(2, RoundingMode.HALF_UP);
		
		System.out.println(grandTotal);
	}

}

package com.steps.frontend.reports;

import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.IpOverViewIpCorrectionModel;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverviewModel;
import com.tools.data.TeamReportModel;
import com.tools.data.TeamReportTakeOffPhaseModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.requirements.AbstractSteps;

public class IpReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public TermPurchaseIpModel grabIpsInfo() {
		return ipReportsPage().grabIpsInfo();
	}

	@Step
	public IpOverViewSummaryModel getIpOverviewSummaryModel() {
		return ipReportsPage().getIpOverviewSummaryModel();
	}
	
	@Step
	public  IpOverViewOpenIpsModel getOpenIpsModel() {
		return ipReportsPage().getOpenIpsModel();
	}

	@Step
	public List<IpOverViewPayedOrdersModel> getPayedOrdersModel() {
		return ipReportsPage().getPayedOrdersModel();
	}

	@Step
	public List<IpOverViewReturnsListModel> getReturnsListModel() {
		return ipReportsPage().getReturnsListModel();
	}

	@Step
	public List<IpOverViewIpCorrectionModel> getIpCorrectionModel() {
		return ipReportsPage().getIpCorrectionModel();
	}

	@Step
	public void selectMonth(String month) {
		ipReportsPage().selectMonthForReport(month);
		
	}
	@Step
	public void validateIpOverViewSummaryModel(IpOverviewModel expected,
			IpOverViewSummaryModel grabbed) {

		validatePaidOrdersPreviosMonth(expected.getIpLastMonth(),grabbed.getPaidOrdersPreviosMonth());
		validatePaidOrdersThisMonth(expected.getPaidOrdersThisMonth(),grabbed.getPaidOrdersThisMonth());
		validateReversedChargeBack(expected.getReverseChargebackThisMonth(),grabbed.getReverseChargebackThisMonth());
		validateChargebackCurrentMonth(expected.getChargebacksThisMonth(),grabbed.getChargebacksThisMonth());
		validateReturnsInCurrentMonth(expected.getReturnsThisMonth(),grabbed.getReturnsThisMonth());
		validateManualCorrection(expected.getManualIpCorrection(),grabbed.getManualIpCorrection());
		validateTotals(expected.getTotalIp(),grabbed.getTotal());
		//altele
	}
	@Step
	public void validateOpenIps(IpOverviewModel expected,
			IpOverViewOpenIpsModel grabbed) {
		
		validateOpenIpsCurrentMonth(expected.getIpThisMonth(),grabbed.getIpThisMonth());
		validateOpenIpsPreviousMonth(expected.getIpLastMonth(),grabbed.getIpLastMonth());
		validateOpenChargebacks(expected.getOpenChargebacks(),grabbed.getOpenChargebacks());
		validateIpsInTermPurchaseCurrentMonth(expected.getIpTPOrdersThisMonth(),grabbed.getIpTPOrdersThisMonth());
		validateIpsInTermPurchaseUpcomingMonth(expected.getIpTPOrdersLastMonth(),grabbed.getIpTPOrdersLastMonth());
		
		
		//altele
	}
	
	@Step
	private void validatePaidOrdersPreviosMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for paid orders in previous month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validatePaidOrdersThisMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for paid orders this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateReversedChargeBack(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for reversed chargebacks this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateChargebackCurrentMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for chargebacks this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateReturnsInCurrentMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for returns this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateManualCorrection(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips for manual corrections this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateTotals(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Total ips for this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateOpenIpsCurrentMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Open ips for this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateOpenIpsPreviousMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Open ips for previous month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateOpenChargebacks(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Open chargebacks for current and previous month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateIpsInTermPurchaseCurrentMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips in Term Purchase for this month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateIpsInTermPurchaseUpcomingMonth(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips in Term Purchase for next month dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	
	
	//-----------------------------------------------------------
	
	public void validateIpOverViewPayedOrdersModelList(List<IpOverViewPayedOrdersModel> expectedList,
			List<IpOverViewPayedOrdersModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (IpOverViewPayedOrdersModel order : expectedList) {
			IpOverViewPayedOrdersModel compare = findOrder(order.getOrderID(), grabbedList);
			if (compare.getOrderID() != null) {
				validateCustomerName(order.getCustomerName(), compare.getCustomerName());
				validateCreatedAt(order.getOrderDate(), compare.getOrderDate());
				validatePaidOrders(order.getPaymentDate(), compare.getPaymentDate());
				validateOrderStatus(order.getOrderStatus(), compare.getOrderStatus());
				validateOrderAmount(order.getAmount(), compare.getAmount());
				validateIps(order.getIp(), compare.getIp());

			} else {
				Assert.assertTrue("Failure: Could not validate all orders in the list", compare != null);
			}
			int index = grabbedList.indexOf(compare);
			if (index > -1) {

				grabbedList.remove(index);

			}
		}
		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);
	}
	
	public void validateIpOverViewRefundAndReturnsModelList(List<IpOverViewReturnsListModel> expectedList,
			List<IpOverViewReturnsListModel> grabbedList) {

		Assert.assertTrue("Failure: The list size are not equal", expectedList.size() == grabbedList.size());

		for (IpOverViewReturnsListModel order : expectedList) {
			IpOverViewReturnsListModel compare = findReturnedOrder(order.getOrderId(), grabbedList);
			if (compare.getOrderId() != null) {
				validateRefundDate(order.getRefundDate(), compare.getRefundDate());
				validateReturnType(order.getRefundType(), compare.getRefundType());
				validateAmount(order.getAmount(), compare.getAmount());
				validateRefundIps(order.getIp(), compare.getIp());

			} else {
				Assert.assertTrue("Failure: Could not validate all orders in the list", compare != null);
			}
			int index = grabbedList.indexOf(compare);
			if (index > -1) {

				grabbedList.remove(index);

			}
		}
		Assert.assertTrue("Failure: Not all products have been validated . ", grabbedList.size() == 0);
	}


	

	private IpOverViewPayedOrdersModel findOrder(String id, List<IpOverViewPayedOrdersModel> grabbedList) {
		IpOverViewPayedOrdersModel result = new IpOverViewPayedOrdersModel();
		theFor: for (IpOverViewPayedOrdersModel item : grabbedList) {
			if (item.getOrderID().contains(id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}
	
	private IpOverViewReturnsListModel findReturnedOrder(String id, List<IpOverViewReturnsListModel> grabbedList) {
		IpOverViewReturnsListModel result = new IpOverViewReturnsListModel();
		theFor: for (IpOverViewReturnsListModel item : grabbedList) {
			if (item.getOrderId().contains(id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}
	
	@Step
	private void validateCustomerName(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Customer name dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateCreatedAt(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Created at dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validatePaidOrders(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Paid at date dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateOrderStatus(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Order status dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateOrderAmount(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Order amount dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateIps(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Ips on order dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateRefundDate(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: refund date for order dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateReturnType(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Return type dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateAmount(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Order amount dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	@Step
	private void validateRefundIps(String expected, String grabbed) {
		CustomVerification.verifyTrue("Failure: Refunded Ips on order dont match " + expected + " - " + grabbed,
				expected.contentEquals(grabbed));
	}
	
	

	

}

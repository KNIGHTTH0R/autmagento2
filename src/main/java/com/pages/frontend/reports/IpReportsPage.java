package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.IpOverViewIpCorrectionModel;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.TeamReportTeamTabModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class IpReportsPage extends AbstractPage {

	@FindBy(css = "table.data-table.mr-t-50.mr-b-50.open-ips tbody")
	private WebElement openIpsTable;

	@FindBy(id="month-report-selector")
	private WebElementFacade monthDateForReport;

	public TermPurchaseIpModel grabIpsInfo() {

		TermPurchaseIpModel ipModel = new TermPurchaseIpModel();

		ipModel.setCurrentMonthIp(openIpsTable.findElement(By.cssSelector("tr:nth-child(4) td:last-child")).getText());
		ipModel.setNextMonthIp(openIpsTable.findElement(By.cssSelector("tr:nth-child(5) td:last-child")).getText());

		return ipModel;

	}

	public IpOverViewSummaryModel getIpOverviewSummaryModel() {

	//	List<IpOverViewSummaryModel> result = new ArrayList<IpOverViewSummaryModel>();

		List<WebElement> ipTables = getDriver().findElements(By.cssSelector("table.data-table tbody"));
		waitFor(ExpectedConditions.visibilityOfAllElements(ipTables));

		IpOverViewSummaryModel ipSummary = new IpOverViewSummaryModel();

		ipSummary.setPaidOrdersPreviosMonth(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(1) td:last-child")).getText());

		ipSummary.setPaidOrdersThisMonth(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(2) td:last-child")).getText());

		ipSummary.setReverseChargebackThisMonth(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(3) td:last-child")).getText());

		ipSummary.setChargebacksThisMonth(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(4) td:last-child")).getText());

		ipSummary.setReturnsThisMonth(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(5) td:last-child")).getText());

		ipSummary.setManualIpCorrection(
				ipTables.get(0).findElement(By.cssSelector("tr:nth-child(6) td:last-child")).getText());

		ipSummary.setTotal(ipTables.get(0).findElement(By.cssSelector("tr:nth-child(7) td:last-child")).getText());

	//	result.add(ipSummary);

		PrintUtils.printSummaryReportModel(ipSummary);
		return ipSummary;
		
	
	}

	public IpOverViewOpenIpsModel getOpenIpsModel() {
	//	List<IpOverViewOpenIpsModel> result = new ArrayList<IpOverViewOpenIpsModel>();

		List<WebElement> ipTables = getDriver().findElements(By.cssSelector("table.data-table tbody"));
		waitFor(ExpectedConditions.visibilityOfAllElements(ipTables));

		IpOverViewOpenIpsModel openIps = new IpOverViewOpenIpsModel();
		openIps.setIpThisMonth(ipTables.get(1).findElement(By.cssSelector("tr:nth-child(1) td:last-child")).getText());

		openIps.setIpLastMonth(ipTables.get(1).findElement(By.cssSelector("tr:nth-child(2) td:last-child")).getText());

		openIps.setOpenChargebacks(
				ipTables.get(1).findElement(By.cssSelector("tr:nth-child(3) td:last-child")).getText());

		openIps.setIpTPOrdersThisMonth(
				ipTables.get(1).findElement(By.cssSelector("tr:nth-child(4) td:last-child")).getText());

		openIps.setIpTPOrdersLastMonth(
				ipTables.get(1).findElement(By.cssSelector("tr:nth-child(5) td:last-child")).getText());

		openIps.setOpenIpTotal(ipTables.get(1).findElement(By.cssSelector("tr:nth-child(6) td:last-child")).getText());

	//	result.add(openIps);

		PrintUtils.printOpenIpsReportMode(openIps);

		return openIps;
	}

	public List<IpOverViewPayedOrdersModel> getPayedOrdersModel() {
		List<IpOverViewPayedOrdersModel> result = new ArrayList<IpOverViewPayedOrdersModel>();
		List<WebElement> ipTables = getDriver().findElements(By.cssSelector("table.data-table tbody"));
		waitFor(ExpectedConditions.visibilityOfAllElements(ipTables));

		List<WebElement> oderList = ipTables.get(2).findElements(By.cssSelector("tr"));
		waitFor(ExpectedConditions.visibilityOfAllElements(oderList));
		if (oderList.get(0).findElements(By.cssSelector("td")).size() > 1) {
			for (WebElement item : oderList) {
				IpOverViewPayedOrdersModel payedOrder = new IpOverViewPayedOrdersModel();

				payedOrder.setOrderID(item.findElement(By.cssSelector("td:nth-child(1)")).getText());
				payedOrder.setCustomerName(item.findElement(By.cssSelector("td:nth-child(2)")).getText());
				payedOrder.setOrderDate(item.findElement(By.cssSelector("td:nth-child(3)")).getText());
				payedOrder.setPaymentDate(item.findElement(By.cssSelector("td:nth-child(4)")).getText());
				payedOrder.setOrderStatus(item.findElement(By.cssSelector("td:nth-child(5)")).getText());
				payedOrder.setAmount(FormatterUtils
						.parseValueToTwoDecimals(item.findElement(By.cssSelector("tr td:nth-child(6)")).getText()));
				payedOrder.setIp(item.findElement(By.cssSelector("tr td:nth-child(7)")).getText());

				result.add(payedOrder);

			}
		}
		PrintUtils.printPayedOrderReportModel(result);
		return result;

	}

	public List<IpOverViewReturnsListModel> getReturnsListModel() {
		List<IpOverViewReturnsListModel> result = new ArrayList<IpOverViewReturnsListModel>();
		List<WebElement> ipTables = getDriver().findElements(By.cssSelector("table.data-table tbody"));
		waitFor(ExpectedConditions.visibilityOfAllElements(ipTables));

		List<WebElement> oderList = ipTables.get(3).findElements(By.cssSelector("tr"));
		waitFor(ExpectedConditions.visibilityOfAllElements(oderList));
		if (oderList.get(0).findElements(By.cssSelector("td")).size() > 1) {
			for (WebElement item : oderList) {
				IpOverViewReturnsListModel returnList = new IpOverViewReturnsListModel();
				returnList.setOrderId(item.findElement(By.cssSelector("td:nth-child(1)")).getText());
				returnList.setRefundDate(item.findElement(By.cssSelector("td:nth-child(2)")).getText());
				returnList.setRefundType(item.findElement(By.cssSelector("td:nth-child(3)")).getText());
				returnList.setAmount(item.findElement(By.cssSelector("td:nth-child(4)")).getText());
				returnList.setIp(item.findElement(By.cssSelector("td:nth-child(5)")).getText());

				result.add(returnList);
			}
		}

		PrintUtils.printReturnsReportModel(result);
		return result;
	}

	public List<IpOverViewIpCorrectionModel> getIpCorrectionModel() {
		List<IpOverViewIpCorrectionModel> result = new ArrayList<IpOverViewIpCorrectionModel>();

		List<WebElement> ipTables = getDriver().findElements(By.cssSelector("table.data-table tbody"));
		waitFor(ExpectedConditions.visibilityOfAllElements(ipTables));

		List<WebElement> oderList = ipTables.get(4).findElements(By.cssSelector("tr"));
		waitFor(ExpectedConditions.visibilityOfAllElements(oderList));
		// if the first row has only one td it means that the table is empty(No
		// items in the list is on the first row)
		if (oderList.get(0).findElements(By.cssSelector("td")).size() > 1) {
			for (WebElement item : oderList) {
				IpOverViewIpCorrectionModel ipCorrectionList = new IpOverViewIpCorrectionModel();
				ipCorrectionList.setComment(item.findElement(By.cssSelector("td:nth-child(1)")).getText());
				ipCorrectionList.setDate(item.findElement(By.cssSelector("td:nth-child(2)")).getText());
				ipCorrectionList.setIp(item.findElement(By.cssSelector("td:nth-child(3)")).getText());

				result.add(ipCorrectionList);
			}
		}

		PrintUtils.printIpManualCorrectionReportModel(result);
		return result;
	}

	public void selectMonthForReport(String month) {
		monthDateForReport.waitUntilVisible();
		element(monthDateForReport).selectByVisibleText(month);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		
	}

	/*
	 * public static boolean isElementPresent(String p) { boolean present; try {
	 * 
	 * driver.findElement(by); present = true; }catch (NoSuchElementException e)
	 * { present = false; } return present; }
	 */
}

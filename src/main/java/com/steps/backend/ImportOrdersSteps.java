package com.steps.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.tools.CustomVerification;
import com.tools.data.NavOrderImportReport;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ImportOrdersSteps {

	@Steps
	CustomVerification customVerification;

	@Steps
	ImportOrdersToNavSteps importOrders;

	public static List<String> ordersWithProblemslist = new ArrayList<String>();
	public static List<String> ordersNotImportedInNav = new ArrayList<String>();
	public static int failedImportOrders = 0;
	public static String orderIncrementId = "";

	// to do
	// public model;

	public String percentOfFailedImportOrders(int listSize) {
		// System.out.println("percent getOrdersNotImportedInNav().size() " +
		// getOrdersNotImportedInNav().size());
		// DecimalFormat df = new DecimalFormat("##.##%");
		//
		// System.out.println("listSize "+listSize);
		// double percent = (getOrdersNotImportedInNav().size() / listSize);
		// String formattedPercent = df.format(percent);
		// System.out.println("percent1 "+percent);
		// return formattedPercent;

		BigDecimal base = BigDecimal.valueOf(Double.parseDouble(Integer.toString(listSize)));
		BigDecimal pct = BigDecimal.valueOf(Double.parseDouble(Integer.toString(getOrdersNotImportedInNav().size())));
		BigDecimal percentage = pct.divide(base, 3, RoundingMode.HALF_UP);
		// System.out.println("dsd"+ c.multiply(BigDecimal.valueOf(100)));
		return percentage.multiply(BigDecimal.valueOf(100)).toString();
	}

	public String percentOfOrdersWithProblems(int listSize) {
		// System.out.println(" procent getOrdersWithProblemslist().size() " +
		// getOrdersWithProblemslist().size());
		// DecimalFormat df = new DecimalFormat("##.##%");
		// double percent = (getOrdersWithProblemslist().size() / listSize);
		// String formattedPercent = df.format(percent);
		// System.out.println("percent1 "+formattedPercent);
		// return formattedPercent;

		BigDecimal base = BigDecimal.valueOf(Double.parseDouble(Integer.toString(listSize)));
		BigDecimal pct = BigDecimal.valueOf(Double.parseDouble(Integer.toString(getOrdersWithProblemslist().size())));
		BigDecimal percentage = pct.divide(base, 3, RoundingMode.HALF_UP);
		// System.out.println("dsd"+ c.multiply(BigDecimal.valueOf(100)));
		return percentage.multiply(BigDecimal.valueOf(100)).toString();
	}

	@Step
	public int noOfOrdersNotImportedInNav() {
		return getOrdersNotImportedInNav().size();
	}

	@Step
	public List<String> listOfOrdersNotImportedInNav() {
		System.out.println(getOrdersNotImportedInNav());
		return getOrdersNotImportedInNav();

	}

	@Step
	public List<String> ordersWithProblemslist() {
		System.out.println(getOrdersWithProblemslist());
		return getOrdersWithProblemslist();

	}

	public static void setOrderIncrementId(String orderIncrementId) {
		ImportOrdersSteps.orderIncrementId = orderIncrementId;
	}

	public void validateOrders(List<DBOrderModel> shopOrderList, List<NavOrderModel> navOrderList) throws Exception {

		// CustomVerification.verifyTrueForOrderImport("Failure: The list size
		// are not equal -> shopListsize "
		// + shopOrderList.size() + " - " + "navListsize: " +
		// navOrderList.size(),
		// shopOrderList.size() == navOrderList.size());
		customVerification.returnErrorCounter();

		for (DBOrderModel order : shopOrderList) {
			List<SalesOrderInfoModel> infoItemList = order.getItemInfo();
			setOrderIncrementId(order.getIncrementId());
			NavOrderModel compare = importOrders.findOrder(order.getIncrementId(), navOrderList);

			String navShipping = null;

			if (compare.getIncrementId() == null) {
				ordersNotImportedInNav.add(order.getIncrementId());
				CustomVerification.verifyTrueForOrderImport(
						"Failure: Could not validate order " + order.getIncrementId(), compare == null);

			} else {
				double shippingAmount = Double.parseDouble(order.getShippingAmount());

				if (compare.getContainsBom().contains("true")) {
					List<NavOrderLinesModel> navOrderItem = compare.getLines();
					for (NavOrderLinesModel item : navOrderItem) {

						if (item.getType().contains("_blank_") && item.isBomItem()) {

							List<NavOrderLinesModel> billOfMaterial = NavisionBillOfMaterialCalls
									.getItemsList(item.getBomItemNo());
							for (NavOrderLinesModel childItem : billOfMaterial) {
								SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
								infoItem.setSku(childItem.getNo());
								infoItemList.add(infoItem);
							}
						}
						navShipping = compare.getShippingAmount();
					}
				}

				if (shippingAmount > 0 && navShipping != "0") {
					SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
					infoItem.setSku("VERS01");
					infoItemList.add(infoItem);
				}
				order.setItemInfo(infoItemList);

				System.out.println("open order " + order.getIncrementId());
				
				importOrders.validateUpdatedNavDate(order.getUpdatedNav(),"2017-03-30","yyyy-MM-dd");

				importOrders.validateOrderIncrementId(order.getIncrementId(), compare.getIncrementId());
				importOrders.validateOrderItemsTest(order.getItemInfo(), compare.getLines());
				importOrders.validateExternalDocNo(order.getIncrementId(), compare.getExternalDocumentNo());

				importOrders.validateGrandTotal(order.getGrandTotal(), compare.getCalculatedGrandTotal());
				importOrders.validateOrderId(order.getOrderId(), compare.getYouRefercences());

				importOrders.validateOrderType(order.getOrderType(), compare.getShopOrderType());
				// magento values calculated compare

				importOrders.validateMagentoCalculatedGrandTotal(order.getGrandTotal(),
						order.getCalculatedGrandTotal());
				importOrders.validateMagentoCalculatedTaxAmount(order.getTaxAmount(), order.getCalculatedTaxAmount());

				// 1/03/2017
				importOrders.validateCartType(order.getCartType(), compare.getShopCartType());

				importOrders.validateSalesPersonCode(order.getStylistCustomerId(), compare.getSalesPersonCode());
				importOrders.validateShippingAmount(order.getShippingAmount(), compare.getShippingAmount());

				if (order.getCartType().contentEquals("3") || order.getCartType().contentEquals("4")) {
					importOrders.contactValidateSaleCustomerNo(order.getContactid(), compare.getSellToCustomerNo());
				} else {
					importOrders.validateSaleCustomerNo(order.getCustomerId(), compare.getSellToCustomerNo());
				}

				// 28/02/2017
				importOrders.validatePaymentMethod(order.getPaymentMethodTypet(), compare.getShopPaymentMethod());
				importOrders.validateWebsiteCode(order.getWebsiteCode(), compare.getShopWebsiteCode());
				importOrders.validateStoreLanguage(order.getStoreLanguage(), compare.getShopStoreLanguage());
				// modify
				// importOrders.validateCustomerName(order.getCustomerName(),
				// compare.getSellToCustomerName());
				importOrders.validateCustomerName(order.getBillToFirstName(), order.getBillToLastName(),
						compare.getSellToCustomerName());
				// on order
				importOrders.validateOrderCustomerEmail(order.getOrderCustomerEmail(), compare.getShipToEmail());

				importOrders.validateBillingToCustomerName(order.getBillToFirstName(), order.getBillToLastName(),
						compare.getBillToName());
				importOrders.validateBillingStreet(order.getBillToStreetAddress(), compare.getBillToAddress());
				importOrders.validateBillingCountryRegionCode(order.getBillCountryId(),
						compare.getBillToCountryRegionCode());
				importOrders.validateBillingCity(order.getBillToCity(), compare.getBillToCity());
				importOrders.validateBillingPostCode(order.getBillToPostcode(), compare.getBillToPostCode());
				importOrders.validateShippingToCustomerName(order.getShipToFirstName(), order.getShipToLastName(),
						compare.getShipToName());
				importOrders.validateShippingStreet(order.getShipToStreetAddress(), compare.getShipToAddress());
				importOrders.validateShippingCountryRegionCode(order.getShipCountryId(),
						compare.getShipToCountryRegionCode());
				importOrders.validateShippingPostCode(order.getShipToPostcode(), compare.getShipToPostCode());
				importOrders.validateShippingCity(order.getShipToCity(), compare.getShipToCity());
				importOrders.validateShippingHouseNumber(order.getShipToHousNumber(), compare.getShipToHouseNumber());
				String navTaxAmount = calculateTaxAmount(order.getTaxAmount(), order.getTaxPrecent(),
						compare.getCalculatedGrandTotal());
				importOrders.validateTaxAmount(order.getTaxAmount(), navTaxAmount);

				importOrders.validateIsPreshipped(order.getIsPreshipped(), compare.getIsAlreadyShipped());
				importOrders.validateIsPom(order.getIsPom(), compare.getShopIsPom());
				importOrders.validatePartyId(order.getStylePartyId(), compare.getPartyId());

				importOrders.validateTotalIp(order.getTotalIp(), compare.getTotalIp());

				// should be clarified before, because here we have different
				// value
				  
				 
				
				importOrders.validateKoboSingleArticle(order.getKoboSingleArticle(), compare.getKoboSingleArticle());
				importOrders.validateVatNumber(order.getVatNumber(), compare.getVatNumber());
				importOrders.validateSmallBussinessMan(order.getSmallBusinessMan(), compare.getSmallBusinessMan());
				importOrders.validateBanckAccountNumber(order.getBanckAccountNumber(), compare.getBanckAccountNumber());
				importOrders.validateLanguageCode(order.getLanguageCode(), compare.getLanguageCode());

		}
			// System.out.println("counter
			// "+customVerification.returnErrorCounter());
			int cc = customVerification.returnErrorCounter();
			if (cc > 0) {
				ordersWithProblemslist.add(order.getIncrementId());
				System.out.println("Order:" + order.getIncrementId() + "with problems");
			}
		}
		setOrdersWithProblemslist(ordersWithProblemslist);
		System.out.println("lista1" + ordersWithProblemslist.size());
		setOrdersNotImportedInNav(ordersNotImportedInNav);
		System.out.println("lista2" + ordersNotImportedInNav.size());
	}

	public void validateMassOrders(List<DBOrderModel> shopOrderList, List<NavOrderModel> navOrderList)
			throws Exception {
		CustomVerification.verifyTrueForOrderImport("Failure: The list size are not equal -> shopListsize "
				+ shopOrderList.size() + " - " + "navListsize: " + navOrderList.size(),
				shopOrderList.size() == navOrderList.size());
		customVerification.returnErrorCounter();
		System.out.println("sunt aici");

		for (DBOrderModel order1 : shopOrderList) {
			// List<SalesOrderInfoModel> infoItemList = order.getItemInfo();
			setOrderIncrementId(order1.getIncrementId());

			System.out.println("order.getIncrementId() " + order1.getIncrementId());
			NavOrderModel compare = importOrders.findOrder(order1.getIncrementId(), navOrderList);
			// String navShipping = null;

			if (compare.getIncrementId() == null) {
				ordersNotImportedInNav.add(order1.getIncrementId());
				CustomVerification.verifyTrueForOrderImport(
						"Failure: Could not validate order " + order1.getIncrementId(), compare == null);
			} else {
			//	importOrders.validateGrandTotal(order1.getGrandTotal(), compare.getCalculatedGrandTotal());
				importOrders.validateUpdatedNavDate(order1.getUpdatedNav(),"2017-03-25","yyyy-MM-dd");
				// String navTaxAmount =
				// calculateTaxAmount(order.getTaxAmount(),
				// order.getTaxPrecent(),
				// compare.getCalculatedGrandTotal());
				// importOrders.validateTaxAmount(order.getTaxAmount(),
				// navTaxAmount);
			}
		}

		setOrdersNotImportedInNav(ordersNotImportedInNav);
	}

	private static String calculateTaxAmount(String taxAmount, String vatPercent, String calculatedGrandTotal) {
		double grandTotalDouble = Double.parseDouble(calculatedGrandTotal);

		double vatDouble = Double.parseDouble(vatPercent);

		BigDecimal gtValue = BigDecimal.valueOf(grandTotalDouble);
		BigDecimal vatValue = BigDecimal.valueOf(vatDouble);
		BigDecimal value = BigDecimal.valueOf(100);

		BigDecimal vat = vatValue.add(value).divide(value);
		BigDecimal navTaxAmount = gtValue.subtract(gtValue.divide(vat, 2, RoundingMode.HALF_UP));

		return navTaxAmount.toString();

	}

	public void generateImportOrdersReport(int shopListSize) {
		Map<String, String> errorData = CustomVerification.errorData;
		String eol = System.getProperty("line.separator");

		int counter = errorData.size();
		errorData.put(Integer.toString(counter++), " ");
		errorData.put(Integer.toString(counter++), "Test Summary Report:");
		errorData.put(Integer.toString(counter++), "Number of validated orders: " + shopListSize);
		errorData.put(Integer.toString(counter++),
				"No of orders not imported in nav: " + getOrdersNotImportedInNav().size());
		errorData.put(Integer.toString(counter++), "No of orders with problems: " + getOrdersWithProblemslist().size());
		errorData.put(Integer.toString(counter++),
				"percent of orders not imported: " + percentOfFailedImportOrders(shopListSize));
		errorData.put(Integer.toString(counter++),
				"percent of orders with problems: " + percentOfOrdersWithProblems(shopListSize));
		for (Entry<String, String> entry : errorData.entrySet()) {
			System.out.println("map: " + entry.getKey() + " " + entry.getValue());
		}

		try (Writer writer = new FileWriter("C:/Users/emilianmelian/Desktop/NavReport/orderImport.csv")) {
			writer.flush();
			writer.append("no").append(',').append("Order increment id").append(',').append("Error Message").append(',')
					.append("Grand total differences").append(eol);
			for (Entry<String, String> entry : errorData.entrySet()) {
				String string = entry.getValue().toString();
				if (string.contains("|")) {
					String message1 = string.substring(0, string.indexOf('|'));
					String message2 = string.substring(string.indexOf('|') + 1);
					String difference = "";
					if (string.contains("GT_diff_not_adjusted")) {
						difference = message2.substring(message2.indexOf('[') + 1, message2.indexOf(']'));
					}

					writer.append(entry.getKey()).append(',').append(message1).append(',').append(message2).append(',')
							.append(difference).append(eol);
				} else {

					writer.append(entry.getKey()).append(',').append(entry.getValue()).append(eol);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	public void generateImportOrdersReportList(int shopListSize) {
		// Map<String,String> errorData=CustomVerification.errorData;

		List<NavOrderImportReport> errorList = CustomVerification.errorDataList;
		String eol = System.getProperty("line.separator");

		NavOrderImportReport orderSummaryMessage = new NavOrderImportReport();
		orderSummaryMessage.setOrderIncrementId("Test Summary Report:");
		errorList.add(orderSummaryMessage);

		NavOrderImportReport noValidatedOrders = new NavOrderImportReport();
		noValidatedOrders.setOrderIncrementId("Number of validated orders: " + shopListSize);
		errorList.add(noValidatedOrders);

		NavOrderImportReport noNotImportedOrders = new NavOrderImportReport();
		noNotImportedOrders
				.setOrderIncrementId("No of orders not imported in nav: " + getOrdersNotImportedInNav().size());
		errorList.add(noNotImportedOrders);

		NavOrderImportReport noOrdersWithProblems = new NavOrderImportReport();
		noOrdersWithProblems.setOrderIncrementId("No of orders with problems: " + getOrdersWithProblemslist().size());
		errorList.add(noOrdersWithProblems);

		NavOrderImportReport percentOfnotImportedOrders = new NavOrderImportReport();
		percentOfnotImportedOrders.setOrderIncrementId(
				"percent of oders not imported: " + percentOfFailedImportOrders(shopListSize) + "%");
		errorList.add(percentOfnotImportedOrders);

		NavOrderImportReport percentOfOrdersWithPr = new NavOrderImportReport();
		percentOfOrdersWithPr.setOrderIncrementId(
				"percent of orders with problems: " + percentOfOrdersWithProblems(shopListSize) + "%");
		errorList.add(percentOfOrdersWithPr);

		try (Writer writer = new FileWriter("C:/Users/emilianmelian/Desktop/NavReport/orderImport.csv")) {
			writer.flush();
			writer.append("Order increment id").append(',').append("Error Message").append(',')
					.append("Grand total differences").append(eol);

			for (NavOrderImportReport entry : errorList) {

				writer.append(entry.getOrderIncrementId()).append(',').append(entry.getErrorMessage()).append(',')
						.append(entry.getGrandTotal()).append(eol);

			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	public void generateMassImportOrdersReport(int shopListSize) {
		// Map<String, String> errorData = CustomVerification.errorData;
		// String eol = System.getProperty("line.separator");
		//
		// int counter = errorData.size();
		// errorData.put(Integer.toString(counter++), " ");
		// errorData.put(Integer.toString(counter++), "Test Summary Report:");
		// errorData.put(Integer.toString(counter++), "Number of validated
		// orders: " + shopListSize);
		// errorData.put(Integer.toString(counter++),
		// "No of orders not imported in nav: " +
		// getOrdersNotImportedInNav().size());
		// errorData.put(Integer.toString(counter++),
		// "percent of oders not imported: " +
		// percentOfFailedImportOrders(shopListSize));
		// for (Entry<String, String> entry : errorData.entrySet()) {
		// System.out.println("map: " + entry.getKey() + " " +
		// entry.getValue());
		// }
		//
		// try (Writer writer = new
		// FileWriter("C:/Users/emilianmelian/Desktop/NavReport/massOrderImport.csv"))
		// {
		// writer.flush();
		// for (Entry<String, String> entry : errorData.entrySet()) {
		// String string = entry.getValue().toString();
		// if (string.contains("|")) {
		// String message1 = string.substring(0, string.indexOf('|'));
		// String message2 = string.substring(string.indexOf('|') + 1);
		// writer.append(entry.getKey()).append(',').append(message1).append(',').append(message2).append(eol);
		// } else {
		// writer.append(entry.getKey()).append(',').append(entry.getValue()).append(eol);
		// }
		// }
		// } catch (IOException ex) {
		// ex.printStackTrace(System.err);
		// }

		List<NavOrderImportReport> errorList = CustomVerification.errorDataList;
		String eol = System.getProperty("line.separator");

		NavOrderImportReport orderSummaryMessage = new NavOrderImportReport();
		orderSummaryMessage.setOrderIncrementId("Test Summary Report:");
		errorList.add(orderSummaryMessage);

		NavOrderImportReport noValidatedOrders = new NavOrderImportReport();
		noValidatedOrders.setOrderIncrementId("Number of validated orders: " + shopListSize);
		errorList.add(noValidatedOrders);

		NavOrderImportReport noNotImportedOrders = new NavOrderImportReport();
		noNotImportedOrders
				.setOrderIncrementId("No of orders not imported in nav: " + getOrdersNotImportedInNav().size());
		errorList.add(noNotImportedOrders);

		NavOrderImportReport noOrdersWithProblems = new NavOrderImportReport();
		noOrdersWithProblems.setOrderIncrementId("No of orders with problems: " + getOrdersWithProblemslist().size());
		errorList.add(noOrdersWithProblems);

		NavOrderImportReport percentOfnotImportedOrders = new NavOrderImportReport();
		percentOfnotImportedOrders.setOrderIncrementId(
				"percent of oders not imported: " + percentOfFailedImportOrders(shopListSize) + "%");
		errorList.add(percentOfnotImportedOrders);

		NavOrderImportReport percentOfOrdersWithPr = new NavOrderImportReport();
		percentOfOrdersWithPr.setOrderIncrementId(
				"percent of orders with problems: " + percentOfOrdersWithProblems(shopListSize) + "%");
		errorList.add(percentOfOrdersWithPr);

		try (Writer writer = new FileWriter("C:/Users/emilianmelian/Desktop/NavReport/massOrderImport.csv")) {
			writer.flush();
			writer.append("Order increment id").append(',').append("Error Message").append(',')
					.append("Grand total differences").append(eol);

			for (NavOrderImportReport entry : errorList) {

				writer.append(entry.getOrderIncrementId()).append(',').append(entry.getErrorMessage()).append(',')
						.append(entry.getGrandTotal()).append(eol);

			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}

	public static String getOrderIncrementId() {
		return orderIncrementId;
	}

	public static List<String> getOrdersWithProblemslist() {
		return ordersWithProblemslist;
	}

	public static void setOrdersWithProblemslist(List<String> ordersWithProblemslist) {
		ImportOrdersSteps.ordersWithProblemslist = ordersWithProblemslist;
	}

	public static List<String> getOrdersNotImportedInNav() {
		return ordersNotImportedInNav;
	}

	public static void setOrdersNotImportedInNav(List<String> ordersNotImportedInNav) {
		ImportOrdersSteps.ordersNotImportedInNav = ordersNotImportedInNav;
	}

	public static void main(String[] args) {
		System.out.println(calculateTaxAmount("", "19", "30.07"));
	}

}

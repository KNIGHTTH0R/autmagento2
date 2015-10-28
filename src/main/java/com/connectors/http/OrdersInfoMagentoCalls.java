package com.connectors.http;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.SoapKeys;
import com.tools.data.soap.DBOrderModel;
import com.tools.env.constants.SoapConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.utils.DateUtils;

/**
 * @author mihaibarta
 *
 */

public class OrdersInfoMagentoCalls {

	private static List<String> unsafeIpStatusesList = new ArrayList<String>(Arrays.asList("processing", "waiting_authorozation", "payment_review", "payment_failed",
			"pending_payment", "payment_in_progress", "pending_payment_hold"));

	private static List<String> payedStatusesList = new ArrayList<String>(Arrays.asList("complete", "payment_complete", "closed"));

	public static BigDecimal calculateTotalIpOnPreviousMonth(List<DBOrderModel> allOrdersList, String stylistId, String createdStartDate, String createdEndDate)
			throws NumberFormatException, ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		int ordersNumber = 0;
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculation(order, createdStartDate, createdEndDate)) {
				ordersNumber++;
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total: " + String.valueOf(totalMonthIp));
		System.out.println("orders number: " + ordersNumber);
		return totalMonthIp;
	}

	public static BigDecimal calculateTotalIpFromOrdersInTakeOfPeriod(List<DBOrderModel> allOrdersList, String stylistId, String activationDate) throws NumberFormatException,
			ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		int ordersNumber = 0;
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForIpCalculationInTob(order, activationDate)) {
				ordersNumber++;
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total: " + String.valueOf(totalMonthIp));
		System.out.println("orders number: " + ordersNumber);
		return totalMonthIp;
	}

	public static BigDecimal calculateTotalUnsafeIpOnCurrentMonth(List<DBOrderModel> allOrdersList, String stylistId, String createdStartDate) throws NumberFormatException,
			ParseException {
		BigDecimal totalMonthIp = BigDecimal.ZERO;
		int ordersNumber = 0;
		
		for (DBOrderModel order : allOrdersList) {
			if (isOrderCompatibleForUnsafeIpCalc(order, createdStartDate)) {
				ordersNumber++;
				totalMonthIp = totalMonthIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIp())));
			}
		}
		System.out.println("total: " + String.valueOf(totalMonthIp));
		System.out.println("orders number: " + ordersNumber);
		return totalMonthIp;
	}

	private static boolean isOrderCompatibleForIpCalculationInTob(DBOrderModel order, String activationDate) throws ParseException {
		return isPayed(order) && DateUtils.isDateBeetween(order.getCreatedAt(), activationDate, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(order.getPaidAt(), activationDate, DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isOrderCompatibleForIpCalculation(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {
		return isPayed(order)
				&& (isOrderCompatibleForIpCalcCase1(order, createdStartDate, createdEndDate) || isOrderCompatibleForIpCalcCase2(order, createdStartDate, createdEndDate));
	}

	private static boolean isPayed(DBOrderModel model) {
		boolean found = false;
		for (String status : payedStatusesList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	private static boolean hasUnsafeIpStatus(DBOrderModel model) {
		boolean found = false;
		for (String status : unsafeIpStatusesList) {
			if (model.getStatus().contentEquals(status)) {
				found = true;
			}
		}
		return found;
	}

	private static boolean isOrderCompatibleForIpCalcCase1(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"),
				DateUtils.getLastDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(order.getPaidAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"), createdEndDate, "yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isOrderCompatibleForIpCalcCase2(DBOrderModel order, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), "2015-01-01 00:00:00", DateUtils.getLastDayOfPreviousMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss") && DateUtils.isDateBeetween(order.getPaidAt(), createdStartDate, createdEndDate, "yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isOrderCompatibleForUnsafeIpCalc(DBOrderModel order, String createEndDate) throws ParseException {

		return DateUtils.isDateBeetween(order.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth("1970-10-10 00:00:00", "yyyy-MM-dd HH:mm:ss"),
				DateUtils.getLastDayOfAGivenMonth(createEndDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")
				&& !isPayed(order) && hasUnsafeIpStatus(order);
	}

	public static List<DBOrderModel> getOrdersList(String stylistId) {

		List<DBOrderModel> stylistList = new ArrayList<DBOrderModel>();

		try {
			SOAPMessage response = soapGetOrdersList(stylistId);
			System.out.println(response);
			try {
				stylistList = extractOrderData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stylistList;
	}

	public static SOAPMessage soapGetOrdersList(String stylistId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		System.out.println("Sesion id :" + sessID);

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		// SOAPMessage soapResponse = soapConnection.call(getOrdersList(sessID,
		// stylistId, createdStartDate, createdEndDate),
		// MongoReader.getSoapURL() + UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(getOrdersListRequest(sessID, stylistId), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getOrdersListRequest(String ssID, String stylistId) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement(SoapKeys.ORDERS_LIST, SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement filters = getStylistRequestParam.addChildElement(SoapKeys.FILTERS);
		SOAPElement complexFilter = filters.addChildElement(SoapKeys.COMPLEX_FILTER);
		SOAPElement complexObjectArray = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement key = complexObjectArray.addChildElement(SoapKeys.KEY);
		key.addTextNode(SoapConstants.STYLIST_ID_FILTER);
		SOAPElement value = complexObjectArray.addChildElement(SoapKeys.VALUE);
		SOAPElement key2 = value.addChildElement(SoapKeys.KEY);
		key2.addTextNode(SoapConstants.EQUAL);
		SOAPElement value2 = value.addChildElement(SoapKeys.VALUE);
		value2.addTextNode(stylistId);

		SOAPElement complexObjectArrayB = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement keyB = complexObjectArrayB.addChildElement(SoapKeys.KEY);
		keyB.addTextNode(SoapConstants.SOAP_CREATED_AT_FILTER);
		SOAPElement valueB = complexObjectArrayB.addChildElement(SoapKeys.VALUE);
		SOAPElement key2B = valueB.addChildElement(SoapKeys.KEY);
		key2B.addTextNode(SoapConstants.GREATER_THAN);
		SOAPElement value2B = valueB.addChildElement(SoapKeys.VALUE);
		value2B.addTextNode("2015-07-01 00:00:00");

		// testing purpose
		// SOAPElement complexObjectArrayC =
		// complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		// SOAPElement keyC = complexObjectArrayC.addChildElement(SoapKeys.KEY);
		// keyC.addTextNode("increment_id");
		// SOAPElement valueC =
		// complexObjectArrayC.addChildElement(SoapKeys.VALUE);
		// SOAPElement key2C = valueC.addChildElement(SoapKeys.KEY);
		// key2C.addTextNode(SoapConstants.EQUAL);
		// SOAPElement value2C = valueC.addChildElement(SoapKeys.VALUE);
		// value2C.addTextNode("staging-int00005854");

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static List<DBOrderModel> extractOrderData(SOAPMessage response) throws Exception {

		List<DBOrderModel> orderModelList = new ArrayList<DBOrderModel>();

		NodeList orderList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < orderList.getLength(); i++) {
			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("result")) {
				DBOrderModel model = new DBOrderModel();

				model.setTotalIp("0");
				model.setPaidAt("2010-01-21 11:45:03");

				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("increment_id")) {
						model.setIncrementId(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("created_at")) {
						model.setCreatedAt(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("status")) {
						model.setStatus(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("payment_complete_at")) {
						model.setPaidAt(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
						model.setStylistId(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_ip")) {
						model.setTotalIp(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("order_type")) {
						model.setOrderType(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("cart_type")) {
						model.setCartType(childNodes.item(j).getTextContent());
					}
				}
				orderModelList.add(model);
			}
		}
		return orderModelList;
	}

}

package com.connectors.http;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.SoapKeys;
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.env.constants.SoapConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.utils.DateUtils;

/**
 * @author mihaibarta
 *
 */

public class CreditMemosInfoMagentoCalls {

	public static BigDecimal calculateTotalIpsForCreditMemos(List<DBCreditMemoModel> completeCMList, String stylistId, String createdStartDate, String createdEndDate)
			throws NumberFormatException, ParseException {

		BigDecimal totalMonthRefundedIp = BigDecimal.ZERO;

		for (DBCreditMemoModel order : completeCMList) {

			if (isOrderCompatibleForDecreasingIp(order, createdStartDate, createdEndDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.subtract(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
			if (isOrderCompatibleForIncreasingIp(order, createdStartDate, createdEndDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
		}
		System.out.println("total: " + String.valueOf(totalMonthRefundedIp));
		return totalMonthRefundedIp;
	}

	public static BigDecimal calculateTotalIpsForCreditMemosInTakeOfPeriod(List<DBCreditMemoModel> completeCMList, String stylistId, String activationDate)
			throws NumberFormatException, ParseException {

		BigDecimal totalMonthRefundedIp = BigDecimal.ZERO;

		for (DBCreditMemoModel order : completeCMList) {

			if (isOrderCompatibleForDecreasingIpForTob(order, activationDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.subtract(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
			if (isOrderCompatibleForIncreasingIpForTob(order, activationDate)) {
				totalMonthRefundedIp = totalMonthRefundedIp.add(BigDecimal.valueOf(Double.parseDouble(order.getTotalIpRefunded())));
			}
		}
		System.out.println("total: " + String.valueOf(totalMonthRefundedIp));
		return totalMonthRefundedIp;
	}

	private static boolean isOrderCompatibleForIncreasingIp(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {
		return creditMemo.getState().contentEquals("3")
				&& (isCreditMemoCompatibleForIpCalcCase1(creditMemo, createdStartDate, createdEndDate) || isCreditMemoCompatibleForIpCalcCase2(creditMemo, createdStartDate,
						createdEndDate));
	}

	private static boolean isOrderCompatibleForIncreasingIpForTob(DBCreditMemoModel creditMemo, String activationDate) throws ParseException {
		return creditMemo.getState().contentEquals("3")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isOrderCompatibleForDecreasingIp(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {
		return creditMemo.getState().contentEquals("2") && isCreditMemoCompatibleForIpCalcCase1(creditMemo, createdStartDate, createdEndDate)
				|| isCreditMemoCompatibleForIpCalcCase2(creditMemo, createdStartDate, createdEndDate);
	}

	private static boolean isOrderCompatibleForDecreasingIpForTob(DBCreditMemoModel creditMemo, String activationDate) throws ParseException {
		return creditMemo.getState().contentEquals("2")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), activationDate, DateUtils.getLastDayOfAGivenMonth(activationDate, "yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isCreditMemoCompatibleForIpCalcCase1(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(creditMemo.getOrderCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"),
				DateUtils.getLastDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"), createdEndDate,
						"yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), DateUtils.getFirstDayOfAGivenMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"), createdEndDate,
						"yyyy-MM-dd HH:mm:ss");
	}

	private static boolean isCreditMemoCompatibleForIpCalcCase2(DBCreditMemoModel creditMemo, String createdStartDate, String createdEndDate) throws ParseException {

		return DateUtils.isDateBeetween(creditMemo.getOrderCreatedAt(), "2015-01-01 00:00:00", DateUtils.getLastDayOfPreviousMonth(createdStartDate, "yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getOrderPaidAt(), createdStartDate, createdEndDate, "yyyy-MM-dd HH:mm:ss")
				&& DateUtils.isDateBeetween(creditMemo.getCreatedAt(), createdStartDate, createdEndDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static List<DBCreditMemoModel> getCreditMemosList(String stylistId) {

		List<DBCreditMemoModel> creditMemoList = new ArrayList<DBCreditMemoModel>();

		try {
			SOAPMessage response = soapGetCreditMemosList(stylistId);
			System.out.println(response);
			try {
				creditMemoList = extractCreditMemoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return creditMemoList;
	}

	public static SOAPMessage soapGetCreditMemosList(String stylistId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		System.out.println("Sesion id :" + sessID);

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		// SOAPMessage soapResponse =
		// soapConnection.call(getCreditMemosList(sessID, stylistId,
		// createdStartDate, createdEndDate), MongoReader.getSoapURL() +
		// UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(getCreditMemosListRequest(sessID, stylistId), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getCreditMemosListRequest(String ssID, String stylistId) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement(SoapKeys.CREDIT_MEMOS_LIST, SoapKeys.URN_PREFIX);

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

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static List<DBCreditMemoModel> extractCreditMemoData(SOAPMessage response) throws Exception {

		List<DBCreditMemoModel> credtMemoModelList = new ArrayList<DBCreditMemoModel>();

		NodeList stylistList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < stylistList.getLength(); i++) {
			DBCreditMemoModel model = new DBCreditMemoModel();
			NodeList childNodes = stylistList.item(i).getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("state")) {
					model.setState(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_ip_refunded")) {
					model.setTotalIpRefunded(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("created_at")) {
					model.setCreatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("updated_at")) {
					model.setUpdatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("order_increment_id")) {
					model.setOrderIncrementId(childNodes.item(j).getTextContent());
				}
			}
			if (model.getState().equals("2")) {
				credtMemoModelList.add(model);

			} else if (model.getState().equals("3")) {
				DBCreditMemoModel newModel = (DBCreditMemoModel) model.clone();
				model.setState("2");
				newModel.setCreatedAt(newModel.getUpdatedAt());
				credtMemoModelList.add(model);
				credtMemoModelList.add(newModel);

			}

		}
		return credtMemoModelList;
	}

	public static List<DBCreditMemoModel> populateCreditMemosListWithOrderDetails(List<DBCreditMemoModel> creditMemoList, List<DBOrderModel> orderList, String stylistId,
			String createdStartDate) {

		for (DBCreditMemoModel creditMemo : creditMemoList) {
			for (DBOrderModel order : orderList) {
				if (creditMemo.getOrderIncrementId().contentEquals(order.getIncrementId())) {
					creditMemo.setOrderCreatedAt(order.getCreatedAt());
					creditMemo.setOrderPaidAt(order.getPaidAt());
				}
			}
		}
		return creditMemoList;
	}

}

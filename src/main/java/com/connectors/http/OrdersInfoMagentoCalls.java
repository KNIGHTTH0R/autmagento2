package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.constants.SoapConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.DBOrderModel;
import com.tools.persistance.MongoReader;

/**
 * @author mihaibarta
 *
 */

public class OrdersInfoMagentoCalls {

	// private static String sessID = LoginSoapCall.performLogin();

	public static List<DBOrderModel> getOrdersList(String stylistId) {

		List<DBOrderModel> orderList = new ArrayList<DBOrderModel>();

		try {
			SOAPMessage response = soapGetOrdersList(stylistId);
			System.out.println(response);
			try {
				orderList = extractOrderData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderList;
	}

	public static List<DBOrderModel> getPartyOrdersList(String partyId) {

		List<DBOrderModel> orderList = new ArrayList<DBOrderModel>();

		try {
			SOAPMessage response = soapGetPartyOrdersList(partyId);
			System.out.println(response);
			try {
				orderList = extractOrderData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderList;
	}

	public static SOAPMessage soapGetOrdersList(String stylistId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getOrdersListRequest(sessID, stylistId),
				MongoReader.getSoapURL() + UrlConstants.API_URI);
		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersListRequest(sessID, stylistId),
		// "http://aut-pippajean.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapGetPartyOrdersList(String partyId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		// SOAPMessage soapResponse =
		// soapConnection.call(getPartyOrdersListRequest(sessID, partyId),
		// MongoReader.getSoapURL() + UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(getPartyOrdersListRequest(sessID, partyId),
				"https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

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
		value2B.addTextNode("2016-06-01 00:00:00");

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage getPartyOrdersListRequest(String ssID, String partyId)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement(SoapKeys.ORDERS_LIST, SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement filters = getStylistRequestParam.addChildElement(SoapKeys.FILTERS);
		SOAPElement complexFilter = filters.addChildElement(SoapKeys.COMPLEX_FILTER);
		SOAPElement complexObjectArray = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement key = complexObjectArray.addChildElement(SoapKeys.KEY);
		key.addTextNode(SoapConstants.STYLE_PARTY_ID_FILTER);
		SOAPElement value = complexObjectArray.addChildElement(SoapKeys.VALUE);
		SOAPElement key2 = value.addChildElement(SoapKeys.KEY);
		key2.addTextNode(SoapConstants.EQUAL);
		SOAPElement value2 = value.addChildElement(SoapKeys.VALUE);
		value2.addTextNode(partyId);

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
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("grand_total")) {
						model.setGrandTotal(childNodes.item(j).getTextContent());
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

	// public static void main(String args[]) {
	// System.out.println(OrdersInfoMagentoCalls.getPartyOrdersList("14830").get(0).getIncrementId());
	// }

}

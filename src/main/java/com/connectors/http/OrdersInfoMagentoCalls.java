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

import com.tools.SoapKeys;
import com.tools.data.soap.DBOrderModel;
import com.tools.env.constants.SoapConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;

/**
 * @author mihaibarta
 *
 */

public class OrdersInfoMagentoCalls {

	public static List<DBOrderModel> getOrdersList(String stylistId, String createdStartDate, String createdEndDate) {

		List<DBOrderModel> stylistList = new ArrayList<DBOrderModel>();

		try {
			SOAPMessage response = soapGetOrdersList(stylistId, createdStartDate, createdEndDate);
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

	public static SOAPMessage soapGetOrdersList(String stylistId, String createdStartDate, String createdEndDate) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		System.out.println("Sesion id :" + sessID);

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		// SOAPMessage soapResponse = soapConnection.call(getOrdersList(sessID,
		// stylistId, createdStartDate, createdEndDate),
		// MongoReader.getSoapURL() + UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(getOrdersList(sessID, stylistId, createdStartDate, createdEndDate), "https://admin-staging-aut.pippajean.com/"
				+ UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getOrdersList(String ssID, String stylistId, String createdStartDate, String createdEndDate) throws SOAPException, IOException {
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
		value2B.addTextNode(createdStartDate);

		// SOAPElement complexObjectArrayC =
		// complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		// SOAPElement keyC = complexObjectArrayC.addChildElement(SoapKeys.KEY);
		// keyC.addTextNode(SoapConstants.SOAP_CREATED_AT_FILTER);
		// SOAPElement valueC =
		// complexObjectArrayC.addChildElement(SoapKeys.VALUE);
		// SOAPElement key2C = valueC.addChildElement(SoapKeys.KEY);
		// key2C.addTextNode(SoapConstants.LESS_THAN);
		// SOAPElement value2C = valueC.addChildElement(SoapKeys.VALUE);
		// value2C.addTextNode(createdEndDate);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static List<DBOrderModel> extractOrderData(SOAPMessage response) throws Exception {

		List<DBOrderModel> stylistModelList = new ArrayList<DBOrderModel>();

		NodeList stylistList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < stylistList.getLength(); i++) {
			DBOrderModel model = new DBOrderModel();
			NodeList childNodes = stylistList.item(i).getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("increment_id")) {
					model.setIncrementId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("created_at")) {
					model.setCreatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
					model.setStylistId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_ip")) {
					model.setTotalIp(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_ip_refunded")) {
					model.setTotalIpRefunded(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("order_type")) {
					model.setOrderType(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("cart_type")) {
					model.setCartType(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("status")) {
					model.setStatus(childNodes.item(j).getTextContent());
				}
			}
			stylistModelList.add(model);
		}
		return stylistModelList;

	}

	public static void main(String args[]) {
		List<DBOrderModel> list = OrdersInfoMagentoCalls.getOrdersList("1835", "2015-09-14", "2015-09-14");
		System.out.println(list.get(0).getIncrementId());
	}

}

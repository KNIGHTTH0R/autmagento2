package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

import com.tools.constants.NavSoapKeys;
import com.tools.data.navision.SyncInfoModel;

public class NavisionProductListCalls {

	public static SyncInfoModel getItemInfo(String filterValue, String filterValue2) throws Exception {

		Authentication.setAuthenticator();

		List<SyncInfoModel> items = null;
		try {
			SOAPMessage response = sendItemsListRequest(filterValue, filterValue2);
			items = extractItemsList(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return items.get(0);
	}

	private static List<SyncInfoModel> extractItemsList(SOAPMessage response) throws Exception {

		List<String> items = new ArrayList<String>();

		List<SyncInfoModel> stockItem = new ArrayList<SyncInfoModel>();

		NodeList orderList = response.getSOAPBody().getElementsByTagName("ProductList");
		System.out.println("lungime" + orderList.getLength());
		for (int i = 0; i < 1; i++) {

			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("ReadMultiple_Result")) {

				SyncInfoModel stockLine = new SyncInfoModel();
				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("QS_Inventory")) {
						items.add(childNodes.item(j).getTextContent());
						stockLine.setQs(childNodes.item(j).getTextContent());

					}

				}

				stockItem.add(stockLine);

				/*
				 * 
				 * private String isInStock; private String pendingQuantity;
				 */

			}
		}
		return stockItem;
	}

	protected static SOAPMessage createInventorySyncDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.PRODUCT_LIST_URN_PREFIX,
				NavSoapKeys.PRODUCT_LIST_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	public static SOAPMessage sendItemsListRequest(String filterValue, String filterValue2)
			throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createItemsListRequest(filterValue, filterValue2),
				NavSoapKeys.PRODUCT_LIST_API_URI);

		return soapResponse;
	}

	private static SOAPMessage createItemsListRequest(String filterValue, String filterValue2)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = createInventorySyncDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE,
				NavSoapKeys.PRODUCT_LIST_URN_PREFIX);

		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		field.addTextNode("No");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		criteria.addTextNode(filterValue);

		
		SOAPElement filter1 = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		SOAPElement field1 = filter1.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		field1.addTextNode("Variant_Code");
		SOAPElement criteria1 = filter1.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.PRODUCT_LIST_URN_PREFIX);
		criteria1.addTextNode(filterValue2);
		
		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static void main(String args[]) throws Exception {

		SyncInfoModel item = NavisionProductListCalls.getItemInfo("R185SV", "18");

		System.out.println(item.getQs());

	}
}

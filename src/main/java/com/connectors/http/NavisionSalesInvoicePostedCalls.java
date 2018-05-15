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
import com.tools.data.navision.SalesInvoicePosted;

public class NavisionSalesInvoicePostedCalls {

	public static SalesInvoicePosted orderInfo(String filterValue) throws Exception {

		Authentication.setAuthenticator();

		List<SalesInvoicePosted> items = null;
		try {
			SOAPMessage response = sendItemsListRequest(filterValue);
			items = extractItemsList(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return items.get(0);
	}

	private static List<SalesInvoicePosted> extractItemsList(SOAPMessage response) throws Exception {

		List<String> items = new ArrayList<String>();

		List<SalesInvoicePosted> stockItem = new ArrayList<SalesInvoicePosted>();
		List<SalesInvoicePosted> stockItem1 = new ArrayList<SalesInvoicePosted>();

		NodeList orderList = response.getSOAPBody().getElementsByTagName("SalesInvoicesPosted");
		System.out.println("lungime" + orderList.getLength());
		for (int i = 0; i < orderList.getLength(); i++) {

			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("ReadMultiple_Result")) {

				SalesInvoicePosted stockLine = new SalesInvoicePosted();
				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("No")) {
						items.add(childNodes.item(j).getTextContent());
						stockLine.setNo(childNodes.item(j).getTextContent());
					}

				}

				stockItem.add(stockLine);
				
			}
		}
		stockItem1.add(stockItem.get(stockItem.size()-1));
		return stockItem1;
	}

	protected static SOAPMessage createSalesInvoicePostedDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.SALES_ORDER_URN_PREFIX,
				NavSoapKeys.SALES_INVOICE_POSTED_SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	public static SOAPMessage sendItemsListRequest(String filterValue)
			throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createItemsListRequest(filterValue),
				NavSoapKeys.SALES_INVOICE_POSTED);

		return soapResponse;
	}

	private static SOAPMessage createItemsListRequest(String filterValue)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = createSalesInvoicePostedDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE,
				NavSoapKeys.SALES_ORDER_URN_PREFIX);

		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		field.addTextNode("External_Document_No");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		criteria.addTextNode(filterValue);


		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static void main(String args[]) throws Exception {

		SalesInvoicePosted item = NavisionSalesInvoicePostedCalls.orderInfo("10026893600");

		System.out.println(item.getNo());

	}
}

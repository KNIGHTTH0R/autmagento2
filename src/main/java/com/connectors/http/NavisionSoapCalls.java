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
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;

public class NavisionSoapCalls {

	public static List<NavOrderModel> getOrdersList(String filterValue) throws Exception {

		Authentication.setAuthenticator();

		List<NavOrderModel> orderList = null;
		try {
			SOAPMessage response = sendOrdersListRequest(filterValue);
			orderList = extractOrderData(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderList;
	}
	
	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 * @throws IOException
	 */
	protected static SOAPMessage createSalesOrderDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.SALES_ORDER_URN_PREFIX, NavSoapKeys.SALES_ORDER_SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	private static SOAPMessage createOrdersListRequest(String orderIncrementId) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSalesOrderDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		field.addTextNode("No");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		criteria.addTextNode(orderIncrementId);
		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage sendOrdersListRequest(String filterValue) throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createOrdersListRequest(filterValue), NavSoapKeys.SALES_ORDER_API_URI);

		return soapResponse;
	}

	private static List<NavOrderModel> extractOrderData(SOAPMessage response) throws Exception {

		List<NavOrderModel> orderModelList = new ArrayList<NavOrderModel>();

		NodeList orderList = response.getSOAPBody().getElementsByTagName("SalesOrder");
		for (int i = 0; i < orderList.getLength(); i++) {

			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("ReadMultiple_Result")) {
				NavOrderModel model = new NavOrderModel();

				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("No")) {
						model.setIncrementId(childNodes.item(j).getTextContent());
					}
//					more repetition foreach field
//					if (childNodes.item(j).getNodeName().equalsIgnoreCase("No")) {
//						model.setIncrementId(childNodes.item(j).getTextContent());
//					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("SalesLines")) {    

						List<NavOrderLinesModel> orderLinesModel = new ArrayList<NavOrderLinesModel>();

						NodeList orderLinesList = childNodes.item(j).getChildNodes(); 
						
						for (int k = 0; k < orderLinesList.getLength(); k++) {

							NavOrderLinesModel line = new NavOrderLinesModel();

							NodeList lineNodes = orderLinesList.item(k).getChildNodes(); 

							for (int l = 0; l < lineNodes.getLength(); l++) {

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("No")) {
									line.setNo(lineNodes.item(l).getTextContent());
								}
								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Contains_BOM")) {
									line.setContainsBom(lineNodes.item(l).getTextContent());
								}
							}
							orderLinesModel.add(line);
						}
						model.setLines(orderLinesModel);
					}
				}
				orderModelList.add(model);
			}
		}
		return orderModelList;
	}

	public static void main(String args[]) throws Exception {

		List<NavOrderModel> ordersList = NavisionSoapCalls.getOrdersList("10023578400..10023578700");
		for (NavOrderModel order : ordersList) {
			System.out.println(order.getIncrementId());
			for (NavOrderLinesModel line : order.getLines()) {
				System.out.println(line.getNo());

			}
		}

	}

}

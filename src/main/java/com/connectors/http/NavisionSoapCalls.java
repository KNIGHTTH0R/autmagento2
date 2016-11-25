package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;

public class NavisionSoapCalls {

	public static List<NavOrderModel> getOrdersList(String filterValue) throws Exception {

		Authentication.setAuthenticator();

		List<NavOrderModel> orderList = null;
		try {
			SOAPMessage response = NavisionSoapConnector.getOrdersList(filterValue);
			orderList = extractOrderData(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderList;
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

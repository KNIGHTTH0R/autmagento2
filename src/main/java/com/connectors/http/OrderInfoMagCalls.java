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

//import com.tools.constants.SoapConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;

public class OrderInfoMagCalls {

	public static DBOrderModel getOrdersInfo(String orderIncrementId,String sessionId) {

		DBOrderModel order = new DBOrderModel();

		try {
			SOAPMessage response = soapGetOrdersInfo(orderIncrementId,sessionId);
			System.out.println(response);
			try {
				order = extractOrderInfoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return order;
	}

	public static SOAPMessage soapGetOrdersInfo(String orderIncrementId,String sessionId) throws SOAPException, IOException {
		
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersInfoRequest(sessID, orderIncrementId),
		// MongoReader.getSoapURL() + UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
				"http://aut-pippajean.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getOrdersInfoRequest(String ssID, String order_Increment_Id)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement("salesOrderInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement orderIncrementId = getStylistRequestParam.addChildElement("orderIncrementId");
		orderIncrementId.addTextNode(order_Increment_Id);

		SOAPElement clinetKey = getStylistRequestParam.addChildElement(SoapKeys.CLIENT_KEY);
		clinetKey.addTextNode(SoapKeys.CLIENT_KEY_VALUE);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static DBOrderModel extractOrderInfoData(SOAPMessage response) throws Exception {

		DBOrderModel model = new DBOrderModel();
		List<SalesOrderInfoModel> item = new ArrayList<SalesOrderInfoModel>();
		
		NodeList itemList = response.getSOAPBody().getElementsByTagName("complexObjectArray");

		for (int i = 0; i < itemList.getLength(); i++) {
			if (itemList.item(i).getParentNode().getNodeName().equalsIgnoreCase("items")) {
				SalesOrderInfoModel infoItem = new SalesOrderInfoModel();

				NodeList childNodes = itemList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("item_id")) {
						infoItem.setItem_id(childNodes.item(j).getTextContent());

					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("sku")) {
						infoItem.setSku(childNodes.item(j).getTextContent());
					}
				}
				item.add(infoItem);
			}
		}
		model.setItemInfo(item);

		return model;
	}

	public static void main(String[] args) {
		List<SalesOrderInfoModel> list = OrderInfoMagCalls.getOrdersInfo("qa-int0000862500","sessionid").getItemInfo();
		for (SalesOrderInfoModel salesOrderInfoModel : list) {
			System.out.println(salesOrderInfoModel.getSku());
		}
	}
}
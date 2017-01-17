package com.connectors.http;

import java.io.IOException;
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

import com.tools.constants.EnvironmentConstants;
//import com.tools.constants.SoapConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;

public class OrderInfoMagCalls {

	public static DBOrderModel getOrdersInfo(String orderIncrementId, String sessionId) {

		DBOrderModel order = new DBOrderModel();

		try {
			SOAPMessage response = soapGetOrdersInfo(orderIncrementId, sessionId);
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

	public static SOAPMessage soapGetOrdersInfo(String orderIncrementId, String sessionId)
			throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getOrdersInfoRequest(sessionId,
		 orderIncrementId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);


//		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
//				"https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

//		 SOAPMessage soapResponse =
//		 soapConnection.call(getOrdersInfoRequest(sessionId,
//		 orderIncrementId),
//		 "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

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

		NodeList result = response.getSOAPBody().getElementsByTagName("result");
		NodeList itemList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		NodeList shippingAddress = response.getSOAPBody().getElementsByTagName("shipping_address");
		NodeList billigAddress = response.getSOAPBody().getElementsByTagName("billing_address");

		for (int i = 0; i < result.getLength(); i++) {
			NodeList resultNodes = result.item(i).getChildNodes();
			for (int r = 0; r < resultNodes.getLength(); r++) {

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_nav")) {
					model.setUpdatedNav(resultNodes.item(r).getTextContent());
				}
				
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("order_currency_code")) {
					model.setOrderCurrencyCode(resultNodes.item(r).getTextContent());
				}
				
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("style_party_id")) {
					model.setStylePartyId(resultNodes.item(r).getTextContent());
				}
				
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("kobo_single_article")) {
					model.setKoboSingleArticle(resultNodes.item(r).getTextContent());
				}
				
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_firstname")) {
					model.setCustomerFirstName(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_lastname")) {
					model.setCustomerLastName(resultNodes.item(r).getTextContent());
				}
				
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("billing_address")) {

					NodeList billingNodes = billigAddress.item(i).getChildNodes();
					for (int b = 0; b < billingNodes.getLength(); b++) {
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("firstname")) {
							model.setBillToFirstName(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("lastname")) {
							model.setBillToLastName(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("postcode")) {
							model.setBillToPostcode(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("street")) {
							model.setBillToStreetAddress(billingNodes.item(b).getTextContent());
						}
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("city")) {
							model.setBillToCity(billingNodes.item(b).getTextContent());
						}
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("country_id")) {
							model.setBillCountryId(billingNodes.item(b).getTextContent());
						}
					}
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("shipping_address")) {
					NodeList shippingNodes = shippingAddress.item(i).getChildNodes();
					for (int s = 0; s < shippingNodes.getLength(); s++) {
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("firstname")) {
							model.setShipToFirstName(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("lastname")) {
							model.setShipToLastName(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("postcode")) {
							model.setShipToPostcode(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("street")) {
							model.setShipToStreetAddress(shippingNodes.item(s).getTextContent());
						}
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("city")) {
							model.setShipToCity(shippingNodes.item(s).getTextContent());
						}
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("country_id")) {
							model.setShipCountryId(shippingNodes.item(s).getTextContent());
						}
					}
				}
			}

			for (int c = 0; c < itemList.getLength(); c++) {
				if (itemList.item(c).getParentNode().getNodeName().equalsIgnoreCase("items")) {
					SalesOrderInfoModel infoItem = new SalesOrderInfoModel();

					NodeList childNodes = itemList.item(c).getChildNodes();

					for (int j = 0; j < childNodes.getLength(); j++) {
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("sku")) {
						
							List<String> list = new ArrayList<String>(
									Arrays.asList(childNodes.item(j).getTextContent().split("-")));
							String sku = null;

							if (list.size() > 1) {
								if (containsNumbers(list.get(1).substring(0, 1))
										|| isLowerCase(list.get(1).substring(0, 1))) {
									sku = list.get(0) + "-" + list.get(1);
								}
							} else {
								sku = list.get(0);
							}

							infoItem.setSku(sku);
						}
						
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("tax_percent")) {
							model.setTaxPrecent(childNodes.item(j).getTextContent());
							
						}
					}
					item.add(infoItem);
				}
			}

		}

		model.setItemInfo(item);

		return model;
	}

	public static boolean containsNumbers(String password) {
		boolean digitFound = false;
		for (char ch : password.toCharArray()) {
			if (Character.isDigit(ch)) {
				digitFound = true;
			}
		}
		return digitFound;
	}

	public static boolean isLowerCase(String s) {
		boolean isLowerCase = false;
		if (Character.isLowerCase(s.charAt(0))) {
			isLowerCase = true;
		}
		return isLowerCase;
	}

	public static void main(String[] args) throws SOAPException, IOException {
		 
		String sessID = HttpSoapConnector.performLogin();
		DBOrderModel dbmodel = OrderInfoMagCalls.getOrdersInfo("10022003900", sessID);
		
		System.out.println("style party  : " + dbmodel.getStylePartyId());
		System.out.println("kobo article "+ dbmodel.getKoboSingleArticle());
		
		System.out.println("bill post  : " + dbmodel.getBillToPostcode());
		System.out.println("bill fname  : " + dbmodel.getBillToFirstName());
		System.out.println("billl lname : " + dbmodel.getBillToLastName());
		System.out.println("bill street : " + dbmodel.getBillToStreetAddress());
		System.out.println("bill city : " + dbmodel.getBillToCity());
		System.out.println("bill country id : " + dbmodel.getBillCountryId());

		System.out.println("customer_firstname: " + dbmodel.getCustomerFirstName());
		System.out.println("customer_lastname : " + dbmodel.getCustomerLastName());
		
		// shipp

		System.out.println("shipp post : " + dbmodel.getShipToPostcode());
		System.out.println("fname  : " + dbmodel.getShipToFirstName());
		System.out.println("ship lname : " + dbmodel.getShipToLastName());
		System.out.println("ship street : " + dbmodel.getShipToStreetAddress());
		System.out.println("ship city : " + dbmodel.getShipToCity());
		System.out.println("ship country id : " + dbmodel.getShipCountryId());
		
		
		System.out.println("updated nav: "+dbmodel.getUpdatedNav());
		System.out.println("order currency code : "+dbmodel.getOrderCurrencyCode());
		
		
		
		
		List<SalesOrderInfoModel> list = dbmodel.getItemInfo();
		for (SalesOrderInfoModel salesOrderInfoModel : list) {
			System.out.println("sku: " + salesOrderInfoModel.getSku());
		}
	}
}
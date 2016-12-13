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
import com.tools.data.soap.DBCreditMemoModel;
import com.tools.persistance.MongoReader;

/**
 * @author mihaibarta
 *
 */

public class CreditMemosInfoMagentoCalls {

	// private static String sessID = LoginSoapCall.performLogin();

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
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		 SOAPMessage soapResponse =
		 soapConnection.call(getCreditMemosListRequest(sessID, stylistId),
		 MongoReader.getSoapURL() + UrlConstants.API_URI);
//		SOAPMessage soapResponse = soapConnection.call(getCreditMemosListRequest(sessID, stylistId), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);
//		SOAPMessage soapResponse = soapConnection.call(getCreditMemosListRequest(sessID, stylistId), "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);
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
		value2B.addTextNode("2016-08-01 00:00:00");

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

}

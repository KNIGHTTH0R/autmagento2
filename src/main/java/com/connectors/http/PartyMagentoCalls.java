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
import com.tools.data.soap.DBStylistPartyModel;

public class PartyMagentoCalls {
	
	public static List<DBStylistPartyModel> getPartyList(String stylistId) {

		List<DBStylistPartyModel> partyList = new ArrayList<DBStylistPartyModel>();

		try {
			SOAPMessage response = soapGetStylistPartyList(stylistId);
			System.out.println(response);
			try {
				partyList = extractStylistPartyData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return partyList;
	}
	
	public static SOAPMessage soapGetStylistPartyList(String stylistId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getStylistPartyList(sessID,stylistId), "https://admin-staging-int.pippajean.com/index.php/api/v2_soap/index/");

		return soapResponse;
	}
	
	private static SOAPMessage getStylistPartyList(String ssID, String stylistId) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylisPartytRequestParam = soapBody.addChildElement("stylistPartyListRequestParam", "urn");

		SOAPElement sessionID = getStylisPartytRequestParam.addChildElement("sessionId");
		sessionID.addTextNode(ssID);

		SOAPElement filters = getStylisPartytRequestParam.addChildElement("filters");
		SOAPElement filter = filters.addChildElement("filter");
		SOAPElement complexObjectArray = filter.addChildElement("complexObjectArray");
		SOAPElement key = complexObjectArray.addChildElement("key");
		key.addTextNode(SoapConstants.STYLIST_ID_FILTER);
		SOAPElement value = complexObjectArray.addChildElement("value");
		value.addTextNode(stylistId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}
	
	private static List<DBStylistPartyModel> extractStylistPartyData(SOAPMessage response) throws Exception {

		List<DBStylistPartyModel> stylistPartyModelList = new ArrayList<DBStylistPartyModel>();

		NodeList stylistPartyList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < stylistPartyList.getLength(); i++) {
			DBStylistPartyModel model = new DBStylistPartyModel();
		//	model.setClosetAt("2054-02-02 11:20:55");
		//	model.setDeletedAt("2054-02-02 11:20:55");
			NodeList childNodes = stylistPartyList.item(i).getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("party_id")) {
					model.setStylist_id(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
					model.setParty_id(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("closed_at")) {
					model.setStylist_id(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("deleted_at")) {
					model.setParty_id(childNodes.item(j).getTextContent());
				}
			
			}
			stylistPartyModelList.add(model);
		}
		return stylistPartyModelList;

	}

}

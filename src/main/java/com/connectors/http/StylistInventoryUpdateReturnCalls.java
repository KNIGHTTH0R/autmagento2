package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.StylistInvetoryUpdateModel;

public class StylistInventoryUpdateReturnCalls {
	public static StylistInvetoryUpdateModel getReturnUpdateInfo(String customerId,String incrementId,String prodSku,String variantCode,String packSku) {

		StylistInvetoryUpdateModel updateInfo = new StylistInvetoryUpdateModel();

		try {
			SOAPMessage response = soapGetBorrowInfo(customerId,incrementId,prodSku,variantCode,packSku);
			System.out.println(response);
			try {
				updateInfo = extractReturnInfo(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return updateInfo;
	}

	public static SOAPMessage soapGetBorrowInfo(String customerId,String incrementId,String prodSku,String variantCode, String packSku) throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getProductInfoRequest(sessionId, customerId,incrementId,prodSku,variantCode,packSku),
				EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getProductInfoRequest(String ssID, String custId,String incrementId,String prodSku,String variantC, String packSku) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getContactRequestParam = soapBody.addChildElement("stylistInventoryStockUpdateRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getContactRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement customerId = getContactRequestParam.addChildElement("customerId");
		customerId.addTextNode(custId);

		SOAPElement productSku = getContactRequestParam.addChildElement("productSku");
		productSku.addTextNode(prodSku);

		SOAPElement variantCode = getContactRequestParam.addChildElement("Variant_Code");
		variantCode.addTextNode(variantC);
		
		SOAPElement actionDate = getContactRequestParam.addChildElement("actionDate");
		actionDate.addTextNode("2017-08-16");

		SOAPElement qtyChange = getContactRequestParam.addChildElement("qtyChange");
		qtyChange.addTextNode("1");

		SOAPElement actualQty = getContactRequestParam.addChildElement("actualQty");
		actualQty.addTextNode("0");

		SOAPElement actionType = getContactRequestParam.addChildElement("actionType");
		actionType.addTextNode("return");

		SOAPElement packageSku = getContactRequestParam.addChildElement("packageSku");
		packageSku.addTextNode(packSku);

		SOAPElement productType = getContactRequestParam.addChildElement("productType");
		productType.addTextNode("simple");

		SOAPElement orderIncrementId = getContactRequestParam.addChildElement("orderIncrementId");
		orderIncrementId.addTextNode(incrementId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static StylistInvetoryUpdateModel extractReturnInfo(SOAPMessage response) throws Exception {

		StylistInvetoryUpdateModel result = new StylistInvetoryUpdateModel();

		NodeList nodeList = response.getSOAPBody().getElementsByTagName("result");

		result.setResult(nodeList.item(0).getTextContent());
		

		return result;

	}

	public static void main(String[] args) {
		StylistInvetoryUpdateModel model = StylistInventoryUpdateReturnCalls.getReturnUpdateInfo("123206","10026671700","R199RS","18","xxxx");
		System.out.println(model.toString());
	}
}

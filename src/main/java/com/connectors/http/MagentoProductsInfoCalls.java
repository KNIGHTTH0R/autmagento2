package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;

public class MagentoProductsInfoCalls {
	public static ProductDetailedModel getProductInfo(String productId) {

		ProductDetailedModel productInfo = new ProductDetailedModel();

		try {
			SOAPMessage response = soapGetProductInfo(productId);
			System.out.println(response);
			try {
				productInfo = extractProductInfo(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productInfo;
	}
	
	public static ProductDetailedModel getProductInfo(String sessionId,String productId) {

		ProductDetailedModel productInfo = new ProductDetailedModel();

		try {
			SOAPMessage response = soapGetProductInfo(sessionId,productId);
			System.out.println(response);
			try {
				productInfo = extractProductInfo(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productInfo;
	}

	public static SOAPMessage soapGetProductInfo(String userId) throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getProductInfoRequest(sessionId, userId),
				EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		return soapResponse;
	}
	
	public static SOAPMessage soapGetProductInfo(String sessionId,String userId) throws SOAPException, IOException {
		//String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getProductInfoRequest(sessionId, userId),
				EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		return soapResponse;
	}



	private static SOAPMessage getProductInfoRequest(String ssID, String prodId) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getContactRequestParam = soapBody.addChildElement("catalogProductInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getContactRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement productId = getContactRequestParam.addChildElement("productId");
		productId.addTextNode(prodId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	

	private static ProductDetailedModel extractProductInfo(SOAPMessage response) throws Exception {

		ProductDetailedModel result = new ProductDetailedModel();

		NodeList productInfoList = response.getSOAPBody().getElementsByTagName("result");
		Node productInfo = productInfoList.item(0);
		NodeList childNodes = productInfo.getChildNodes();
		for (int j = 0; j < childNodes.getLength(); j++) {


			
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("url_key")) {
				result.setName(childNodes.item(j).getTextContent());
			}
			
			if (childNodes.item(j).getNodeName().equalsIgnoreCase("price")) {
				result.setPrice(childNodes.item(j).getTextContent());
			}
			
		}
		return result;

	}

	
	public static void main(String[] args) {
		ProductDetailedModel model=MagentoProductsInfoCalls.getProductInfo("4469");
		System.out.println(model.toString());
	}
}

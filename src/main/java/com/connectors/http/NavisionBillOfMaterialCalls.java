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

public class NavisionBillOfMaterialCalls {

	public static List<NavOrderLinesModel> getItemsList(String filterValue) throws Exception {

		Authentication.setAuthenticator();

		List<NavOrderLinesModel> items = null;
		try {
			SOAPMessage response = sendItemsListRequest(filterValue);
			items = extractItemsList(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;
	}

	private static List<NavOrderLinesModel> extractItemsList(SOAPMessage response) throws Exception {

		List<String> items = new ArrayList<String>();

		List<NavOrderLinesModel>  billItem =new ArrayList<NavOrderLinesModel>();
		
		
		NodeList orderList = response.getSOAPBody().getElementsByTagName("BillOfMaterial");
		for (int i = 0; i < orderList.getLength(); i++) {

			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("ReadMultiple_Result")) {

				NavOrderLinesModel billLine=new NavOrderLinesModel();
				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("No")) {
						items.add(childNodes.item(j).getTextContent());
						billLine.setNo(childNodes.item(j).getTextContent());
						
					}
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Variant_Code")) {
						billLine.setVarianteCode(childNodes.item(j).getTextContent());
					}
					
				}
				
				billItem.add(billLine);
				
				
				if (billItem.get(billItem.size() - 1).getVarianteCode() != null) {
					String variantCode = billItem.get(billItem.size() - 1).getVarianteCode();
					String no = billItem.get(billItem.size() - 1).getNo();

					billItem.get(billItem.size() - 1).setNo(no + "-" + variantCode);

				}
				
				
				
			}
		}
		return billItem;
	}

	protected static SOAPMessage createBillOfMaterialDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.BILL_OF_MATERIAL_URN_PREFIX,
				NavSoapKeys.BILL_OF_MATERIAL_SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	public static SOAPMessage sendItemsListRequest(String filterValue) throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createItemsListRequest(filterValue),
				NavSoapKeys.BILL_OF_MATERIAL_API_URI);

		return soapResponse;
	}

	private static SOAPMessage createItemsListRequest(String filterValue) throws SOAPException, IOException {
		SOAPMessage soapMessage = createBillOfMaterialDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE,
				NavSoapKeys.BILL_OF_MATERIAL_URN_PREFIX);
		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.BILL_OF_MATERIAL_URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.BILL_OF_MATERIAL_URN_PREFIX);
		field.addTextNode("Parent_Item_No");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.BILL_OF_MATERIAL_URN_PREFIX);
		criteria.addTextNode(filterValue);
		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static void main(String args[]) throws Exception {

		List<NavOrderLinesModel> items = NavisionBillOfMaterialCalls.getItemsList("X006");
		for (NavOrderLinesModel item : items) {
			System.out.println(item.getNo());
		}

	}

}

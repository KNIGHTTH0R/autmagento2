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

import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;

public class MagentoStockProductsListCalls {
	public static List<ProductDetailedModel> getProductListInfo(String sessionId,String from, String to) {

		List<ProductDetailedModel> productInfo = new ArrayList<ProductDetailedModel>();

		try {
			SOAPMessage response = soapGetProductInfo(sessionId,from,to);
			System.out.println(response);
			try {
				productInfo =  extractProductInfo(response);
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
	
	
	public static List<ProductDetailedModel> getAvailabilityStockProducts(String fromProductId,String toProductId ) throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		List<ProductDetailedModel> productList=new ArrayList<ProductDetailedModel>();
		List<ProductDetailedModel> productInfo=new ArrayList<ProductDetailedModel>();
		List<ProductDetailedModel> productCatalogInventory=new ArrayList<ProductDetailedModel>();

		productList = getProductListInfo(sessionId,fromProductId,toProductId);
		productInfo =getStockProductInfo(sessionId,productList);
		productCatalogInventory=getCatalogInventoryStockProductInfo(sessionId,productInfo);

		
		return productCatalogInventory;
	}
	
	
	public static List<ProductDetailedModel> getStockProductInfo(String sessionId,List<ProductDetailedModel> productsList) {
		List<ProductDetailedModel> stockProductInfo = new ArrayList<ProductDetailedModel>();
		
		for (ProductDetailedModel product : productsList) {
			ProductDetailedModel productInfo=new ProductDetailedModel();
			productInfo=MagentoProductsInfoCalls.getProductInfo(sessionId,product.getProductId());
			product.setPrice(productInfo.getPrice());
			product.setUrlKey(productInfo.getUrlKey());
			stockProductInfo.add(product);
		}
		return stockProductInfo;
		
	}
	
	public static List<ProductDetailedModel> getCatalogInventoryStockProductInfo(String sessionId,List<ProductDetailedModel> productsList) {
		List<ProductDetailedModel> stockProductInfo = new ArrayList<ProductDetailedModel>();
		for (ProductDetailedModel product : productsList) {
			ProductDetailedModel productInfo=new ProductDetailedModel();
			productInfo=MagentoCatalogInventoryStockItemListCalls.getCatalogInventoryStockProductInfo(sessionId,product.getProductId());
		
			product.setQuantity(productInfo.getQuantity());
			product.setMinQty(productInfo.getMinQty());
			product.setIsInStock(productInfo.getIsInStock());
			product.setIsDiscountinued(productInfo.getIsDiscountinued());
			stockProductInfo.add(product);
		}
		
  return stockProductInfo;
		
	}

	public static SOAPMessage soapGetProductInfo(String sessionId,String from,String to) throws SOAPException, IOException {
	//	String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getProductInfoRequest(sessionId, from,to),
				EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		return soapResponse;
	}


	private static SOAPMessage getProductInfoRequest(String ssID, String from, String to) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getProductRequestParam = soapBody.addChildElement("catalogProductListRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getProductRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement filters = getProductRequestParam.addChildElement(SoapKeys.FILTERS);
		SOAPElement complexFilter = filters.addChildElement(SoapKeys.COMPLEX_FILTER);
		addProductFilter(complexFilter, "product_id", "from", from);
		addProductFilter(complexFilter, "product_id", "to", to);
		

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static void addProductFilter(SOAPElement complexFilter, String filterKey, String operator, String filterValue)
			throws SOAPException {

		SOAPElement complexObjectArray = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement key = complexObjectArray.addChildElement(SoapKeys.KEY);
		key.addTextNode(filterKey);
		SOAPElement value = complexObjectArray.addChildElement(SoapKeys.VALUE);
		SOAPElement key2 = value.addChildElement(SoapKeys.KEY);
		key2.addTextNode(operator);
		SOAPElement value2 = value.addChildElement(SoapKeys.VALUE);
		value2.addTextNode(filterValue);
	}

	

	private static List<ProductDetailedModel> extractProductInfo(SOAPMessage response) throws Exception {
		List<ProductDetailedModel> listResult=new ArrayList<ProductDetailedModel>();
		

		NodeList productList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < productList.getLength(); i++) {
			ProductDetailedModel result = new ProductDetailedModel();
			if (productList.item(i).getParentNode().getNodeName().equalsIgnoreCase("result")) {
				NodeList childNodes = productList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
				
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("product_id")) {
						result.setProductId(childNodes.item(j).getTextContent());
					}
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("sku")) {
						result.setSku(childNodes.item(j).getTextContent());
					}
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("name")) {
						result.setName(childNodes.item(j).getTextContent());
					}
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("type")) {
						result.setType(childNodes.item(j).getTextContent());
					}
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("website_ids")) {
						List<String> websiteArray = new ArrayList<String>();
						NodeList websiteNode = childNodes.item(j).getChildNodes();
						for(int x=0;x<websiteNode.getLength();x++){
							websiteArray.add(websiteNode.item(x).getTextContent().trim());
						}
						result.setWebsiteArray(websiteArray);
					}
					
					
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("category_ids")) {
						List<String> categoriesArray = new ArrayList<String>();
						NodeList websiteNode = childNodes.item(j).getChildNodes();
						for(int x=0;x<websiteNode.getLength();x++){
							categoriesArray.add(websiteNode.item(x).getTextContent().trim());
						}
						result.setCategoriesArray(categoriesArray);
					}
					
				}
				listResult.add(result);
			}
		}
		return listResult;
	}

	
	
	
	public static void main(String[] args) throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		List<ProductDetailedModel> model=MagentoStockProductsListCalls.getProductListInfo(sessionId,"0","10000");
	/*	for (ProductDetailedModel productDetailedModel : model) {
			System.out.println(productDetailedModel);
	}*/
		System.out.println(model.size());
		//System.out.println(model.toString());
	}
}

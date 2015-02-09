package com.connectors.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.data.soap.TierPriceModel;

public class HttpSoapConnector {

	public static void main(String args[]) throws Exception {
		// // Create SOAP Connection
		// SOAPConnectionFactory soapConnectionFactory =
		// SOAPConnectionFactory.newInstance();
		// SOAPConnection soapConnection =
		// soapConnectionFactory.createConnection();
		//
		// // Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		// SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),
		// url);
		//
		// // print SOAP Response
		// System.out.print("Response SOAP Message:");
		// soapResponse.writeTo(System.out);
		//
		// soapConnection.close();
		createProduct(new ProductDetailedModel("zzzA"));

		// createLoginRequest("u", "p");
	}
	
	
	/**
	 * Create a login message for SOAP call.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 */
	private static SOAPMessage createLoginRequest(String user, String pass) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		// SOAP Body
		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement loginContainer = soapBody.addChildElement(SoapKeys.LOGIN_PARAM, SoapKeys.URN_PREFIX);
		SOAPElement userBody = loginContainer.addChildElement(SoapKeys.USER_NAME);
		userBody.addTextNode(user);
		SOAPElement apikeyBody = loginContainer.addChildElement(SoapKeys.API_KEY);
		apikeyBody.addTextNode(pass);

		soapMessage.saveChanges();

		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 */
	private static SOAPMessage createSoapDefaultMessage() throws DOMException, SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		String serverURI = SoapKeys.SERVER_URI;
		soapMessage.getSOAPPart().getEnvelope().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(SoapKeys.URN_PREFIX, serverURI);
		soapMessage.getSOAPBody().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(SoapKeys.SOAP_PREFIX);

		return soapMessage;

	}


	public static SOAPMessage createProduct(ProductDetailedModel product) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductCreateRequestParam = soapBody.addChildElement(SoapKeys.CATALOG_CONTAINER, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = catalogProductCreateRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(product.getSessionId());

		catalogProductCreateRequestParam.addChildElement(addOptionalField(SoapKeys.TYPE, product.getType(), catalogProductCreateRequestParam));
		catalogProductCreateRequestParam.addChildElement(addOptionalField(SoapKeys.SET, product.getSet(), catalogProductCreateRequestParam));
		catalogProductCreateRequestParam.addChildElement(addOptionalField(SoapKeys.SKU, product.getSku(), catalogProductCreateRequestParam));

		// Add product data here
		catalogProductCreateRequestParam.addChildElement(generateProductMessage(catalogProductCreateRequestParam, product));

		SOAPElement store = catalogProductCreateRequestParam.addChildElement(SoapKeys.STORE);
		store.addTextNode(product.getStore());

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPElement generateProductMessage(SOAPElement bodyElement, ProductDetailedModel product) throws SOAPException {
		SOAPElement productData = bodyElement.addChildElement(SoapKeys.PRODUCT_DATA);

		productData.addChildElement(addOptionalField(SoapKeys.NAME, product.getName(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.DESCRIPTION, product.getDescription(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.SHORT_DESCRIPTION, product.getShortDescription(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.WEIGHT, product.getWeight(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.STATUS, product.getStatus(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.URL_KEY, product.getUrlKey(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.URL_PATH, product.getUrlPath(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.VISIBILITY, product.getVisibility(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.HAS_OPTIONS, product.getHasOptions(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.GIFT_MSG_AV, product.getGiftMessageAvailable(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.PRICE, product.getPrice(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.SPECIAL_PRICE, product.getSpecialPrice(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.SPECIAL_FROM_DATE, product.getSpecialFromDate(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.SPECIAL_TO_DATE, product.getSpecialToDate(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.TAX_CLASS_ID, product.getTaxClassId(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.META_TITLE, product.getMetaTitle(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.META_KEYWORD, product.getMetaKeyword(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.META_DESCRIPTION, product.getMetaDescription(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.CUSTOM_DESIGN, product.getCustomDesign(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.CUSTOM_LAYOUT_UPDATE, product.getCustomLayoutUpdate(), productData));
		productData.addChildElement(addOptionalField(SoapKeys.OPTIONS_CONTAINER, product.getOptionsContainer(), productData));

		//Added stock data section
		productData.addChildElement(generateStockDataMessage(product.getStockData(), productData));
		productData.addChildElement(generateTierPriceMessage(product.getTierPriceList(), productData));
		
		//these should be implemented in the same manner
		productData.addChildElement(generateWebsiteIds(product.getWebsiteIdsArray(), productData));
		productData.addChildElement(generateWebsites(product.getWebsiteArray(), productData));
		productData.addChildElement(generateCategories(product.getCategoriesArray(), productData));
		productData.addChildElement(generateCategoryIds(product.getCategoryIdsArray(), productData));
		productData.addChildElement(generateAdditionalAttributes(product.getAdditionalAttributes(), productData));

		// Lists and other objects
		return productData;

	}

	private static SOAPElement generateStockDataMessage(StockDataModel product, SOAPElement bodyElement) throws SOAPException {
		SOAPElement stockData = bodyElement.addChildElement(SoapKeys.STOCK_DATA);

		if (product != null) {
			stockData.addChildElement(addOptionalField(SoapKeys.QTY, product.getQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.IS_IN_STOCK, product.getIsInStock(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.MANAGE_STOCK, product.getManageStock(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MANAGE_STOCK, product.getUseConfigManageStock(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.MIN_QTY, product.getMinQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MIN_QTY, product.getUseConfigMinQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.MIN_SALE_QTY, product.getMinSaleQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MIN_SALE_QTY, product.getUseConfigMinSaleQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.MAX_SALE_QTY, product.getMaxSaleQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MAX_SALE_QTY, product.getUseConfigMaxSaleQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.IS_QTY_DECIMAL, product.getIsQtyDecimal(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.BACKORDERS, product.getBackorders(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_BACKORDERS, product.getUseConfigBackorders(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.NOTIFY_STOCK_QTY, product.getNotifyStockQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_NOTIFY_STOCK_QTY, product.getUseConfigNotifyStockQty(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.IS_DISCONTINUED, product.getIsDiscontinued(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.EARLIEST_AVAILABILITY, product.getEarliestAvailability(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.MAXIMUM_PERCENTAGE_TO_BORROW, product.getMaximumPercentageToBorrow(), stockData));
			stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MAXIMUM_PERCENTAGE_TO_BORROW, product.getUseConfigMaximumPercentageToBorrow(), stockData));
		}else{
			System.out.println("ERROR: Product - Stock Data Model is NULL - see soap - generateStockDataMessage()");
		}
		return stockData;
	}
	
	private static SOAPElement generateTierPriceMessage(List<TierPriceModel> productList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.TIER_PRICES);

		if (productList.size() > 0) {
			for (TierPriceModel product : productList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				
				objArray.addChildElement(addOptionalField(SoapKeys.CUSTOMER_GROUP_ID, product.getCustomerGroupId(), objArray));
				objArray.addChildElement(addOptionalField(SoapKeys.WEBSITE, product.getWebsite(), objArray));
				objArray.addChildElement(addOptionalField(SoapKeys.QTY, product.getQty(), objArray));
				objArray.addChildElement(addOptionalField(SoapKeys.PRICE, product.getPrice(), objArray));
			}
		}else{
			System.out.println("ERROR: Product - Tier Price Model list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}
	
	private static SOAPElement generateAdditionalAttributes(Map<String, String> map, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.TIER_PRICES);
		
		if (map.size() > 0) {
			for (String product : map.keySet()) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				
				objArray.addChildElement(addOptionalField(SoapKeys.KEY, product, objArray));
				objArray.addChildElement(addOptionalField(SoapKeys.VALUE, map.get(product), objArray));
			}
		}else{
			System.out.println("ERROR: Product - Additional Atributes list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}
	
	/**
	 * Elements might bee kept in a different way. 
	 * @param productList
	 * @param bodyElement
	 * @return
	 * @throws SOAPException
	 */
	private static SOAPElement generateWebsiteIds(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.WEBSITE_IDS);
		
		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		}else{
			System.out.println("ERROR: Product - Website Ids list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}
	
	
	private static SOAPElement generateWebsites(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.WEBSITES);
		
		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		}else{
			System.out.println("ERROR: Product - Websites list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}
	
	private static SOAPElement generateCategories(List<String> categoriesList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.CATEGORIES);
		
		if (categoriesList.size() > 0) {
			for (String product : categoriesList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		}else{
			System.out.println("ERROR: Product - Categories list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}
	
	private static SOAPElement generateCategoryIds(List<String> categoriesList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.CATEGORY_IDS);
		
		if (categoriesList.size() > 0) {
			for (String product : categoriesList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		}else{
			System.out.println("ERROR: Product - Category Ids list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}

	/**
	 * This method will create an child element and add a value to it if the
	 * value of the element is not empty.
	 * 
	 * @param fieldKey
	 * @param fieldValue
	 * @param parentElement
	 * @return
	 * @throws SOAPException
	 */
	private static SOAPElement addOptionalField(String fieldKey, String fieldValue, SOAPElement parentElement) throws SOAPException {
		SOAPElement result = null;
		if (!fieldValue.isEmpty()) {
			result = parentElement.addChildElement(fieldKey);
			result.addTextNode(fieldValue);
		}
		return result;
	}
}

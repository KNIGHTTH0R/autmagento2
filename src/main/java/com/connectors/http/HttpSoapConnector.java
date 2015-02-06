package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.SoapKeys;
import com.tools.data.soap.ProductDetailedModel;

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

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage createProduct(ProductDetailedModel product) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductCreateRequestParam = soapBody.addChildElement(SoapKeys.CATALOG_CONTAINER, "urn");
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
		
		//Add stock data section
		productData.addChildElement(generateStockDataMessage(product, productData));
		
		
		//Lists and other objects
		return productData;

	}
	
	
	private static SOAPElement generateStockDataMessage(ProductDetailedModel product, SOAPElement bodyElement) throws SOAPException {
		SOAPElement stockData = bodyElement.addChildElement(SoapKeys.STOCK_DATA);
		stockData.addChildElement(addOptionalField(SoapKeys.QTY, product.getStockData().getQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.IS_IN_STOCK, product.getStockData().getIsInStock(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.MANAGE_STOCK, product.getStockData().getManageStock(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MANAGE_STOCK, product.getStockData().getUseConfigManageStock(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.MIN_QTY, product.getStockData().getMinQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MIN_QTY, product.getStockData().getUseConfigMinQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.MIN_SALE_QTY, product.getStockData().getMinSaleQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MIN_SALE_QTY, product.getStockData().getUseConfigMinSaleQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.MAX_SALE_QTY, product.getStockData().getMaxSaleQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MAX_SALE_QTY, product.getStockData().getUseConfigMaxSaleQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.IS_QTY_DECIMAL, product.getStockData().getIsQtyDecimal(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.BACKORDERS, product.getStockData().getBackorders(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_BACKORDERS, product.getStockData().getUseConfigBackorders(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.NOTIFY_STOCK_QTY, product.getStockData().getNotifyStockQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_NOTIFY_STOCK_QTY, product.getStockData().getUseConfigNotifyStockQty(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.IS_DISCONTINUED, product.getStockData().getIsDiscontinued(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.EARLIEST_AVAILABILITY, product.getStockData().getEarliestAvailability(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.MAXIMUM_PERCENTAGE_TO_BORROW, product.getStockData().getMaximumPercentageToBorrow(), stockData));
		stockData.addChildElement(addOptionalField(SoapKeys.USE_CONFIG_MAXIMUM_PERCENTAGE_TO_BORROW, product.getStockData().getUseConfigMaximumPercentageToBorrow(), stockData));
		
		return stockData;
		
	}
	
	/**
	 * This method will create an child element and add a value to it if the value of the element is not empty.
	 * @param fieldKey
	 * @param fieldValue
	 * @param parentElement
	 * @return
	 * @throws SOAPException 
	 */
	private static SOAPElement addOptionalField(String fieldKey, String fieldValue, SOAPElement parentElement) throws SOAPException{
		SOAPElement result = null;
		if(!fieldValue.isEmpty()){
			result = parentElement.addChildElement(fieldKey);
			result.addTextNode(fieldValue);
		}
		return result;
	}
}

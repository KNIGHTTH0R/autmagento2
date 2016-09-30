package com.connectors.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

import com.tools.constants.Credentials;
import com.tools.constants.SoapConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.CategoryModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.data.soap.TierPriceModel;
import com.tools.persistance.MongoReader;

public class HttpSoapConnector {

	// private static String sessID = LoginSoapCall.performLogin();

	/**
	 * Create a product and return the message. Performs login and creates a xml
	 * based on the provided product model.
	 * 
	 * @param product
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 */
	public static SOAPMessage soapCreateCategory(CategoryModel category, String parentId) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createCategory(category, parentId, sessID), MongoReader.getSoapURL() + UrlConstants.API_URI);
//		SOAPMessage soapResponse = soapConnection.call(createCategory(category, parentId, sessID), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapCreateProduct(ProductDetailedModel product) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createProduct(product, sessID), MongoReader.getSoapURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapUpdateProduct(ProductDetailedModel product, String productId) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(updateProduct(product, sessID, productId), MongoReader.getSoapURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapCreateJbZzzProduct(ProductDetailedModel product) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createJbZzzProduct(product, sessID), MongoReader.getSoapURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapGetStylistInfo(String stylistId) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getStylistInfo(stylistId, sessID), MongoReader.getSoapURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapGetStylistList(String filter, String operand, String filterValue) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getStylistList(sessID, filter, operand, filterValue), MongoReader.getSoapURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	public static SOAPMessage soapProductInfo(String productId) throws SOAPException, IOException {
		String sessID = performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getProductInfo(sessID, productId), MongoReader.getSoapURL() + UrlConstants.API_URI);
//		SOAPMessage soapResponse = soapConnection.call(getProductInfo(sessID, productId), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);
		return soapResponse;
	}

	/**
	 * This method will login with a user in {@link SoapKeys} and return the
	 * sessionID.
	 *
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 */
	protected static String performLogin() throws SOAPException, IOException {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(
				createLoginRequest(Credentials.LOGIN_USER_SOAP, Credentials.LOGIN_PASS_SOAP),
				MongoReader.getSoapURL() + UrlConstants.API_URI);
		// SOAPMessage soapResponse =
		// soapConnection.call(createLoginRequest(Credentials.LOGIN_USER_SOAP,
		// Credentials.LOGIN_PASS_SOAP), "http://aut-pippajean.evozon.com/"
		// + UrlConstants.API_URI);
		String result = "";

		NodeList returnList = soapResponse.getSOAPBody().getElementsByTagName(SoapKeys.RESULT);
		if (returnList.getLength() == 1) {
			result = returnList.item(0).getTextContent();
		}

		System.out.println("SessionID -> " + result);
		System.out.println("Login Response  ");
		soapResponse.writeTo(System.out);
		return result;
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
	public static SOAPMessage createLoginRequest(String user, String pass) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		// SOAP Body
		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement loginContainer = soapBody.addChildElement(SoapKeys.LOGIN_PARAM, SoapKeys.URN_PREFIX);
		SOAPElement userBody = loginContainer.addChildElement(SoapKeys.USER_NAME);
		userBody.addTextNode(user);
		SOAPElement apikeyBody = loginContainer.addChildElement(SoapKeys.API_KEY);
		apikeyBody.addTextNode(pass);

		soapMessage.saveChanges();

		soapMessage.writeTo(System.out);

		return soapMessage;
	}

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 */
	protected static SOAPMessage createSoapDefaultMessage() throws DOMException, SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(SoapKeys.URN_PREFIX, SoapKeys.SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(SoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	private static SOAPMessage createCategory(CategoryModel category, String parentId, String ssID) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement categoryCreateRequestParam = soapBody.addChildElement(SoapKeys.CATEGORY_CREATE_REQUEST, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = categoryCreateRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);
		SOAPElement parent = categoryCreateRequestParam.addChildElement(SoapKeys.PARENT_ID);
		parent.addTextNode(parentId);

		// Add product data here
		categoryCreateRequestParam = generateCategoryData(categoryCreateRequestParam, category);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage createProduct(ProductDetailedModel product, String ssID) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductCreateRequestParam = soapBody.addChildElement(SoapKeys.CATALOG_CONTAINER, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = catalogProductCreateRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		catalogProductCreateRequestParam = addOptionalField(SoapKeys.TYPE, product.getType(), catalogProductCreateRequestParam);
		catalogProductCreateRequestParam = addOptionalField(SoapKeys.SET, product.getSet(), catalogProductCreateRequestParam);
		catalogProductCreateRequestParam = addOptionalField(SoapKeys.SKU, product.getSku(), catalogProductCreateRequestParam);

		// Add product data here
		catalogProductCreateRequestParam = generateProductMessage(catalogProductCreateRequestParam, product);

		SOAPElement store = catalogProductCreateRequestParam.addChildElement(SoapKeys.STORE);
		store.addTextNode(product.getStore());

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage updateProduct(ProductDetailedModel product, String ssID, String productId) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductUpdateRequestParam = soapBody.addChildElement(SoapKeys.UPDATE_PRODUCT_CATALOG_CONTAINER, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = catalogProductUpdateRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);
		SOAPElement productIdNode = catalogProductUpdateRequestParam.addChildElement(SoapKeys.PRODUCT_ID);
		productIdNode.addTextNode(productId);

		// Add product data here
		catalogProductUpdateRequestParam = generateProductUpdateStockMessage(catalogProductUpdateRequestParam, product);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage createJbZzzProduct(ProductDetailedModel product, String ssID) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductCreateRequestParam = soapBody.addChildElement(SoapKeys.CATALOG_CONTAINER, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = catalogProductCreateRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		catalogProductCreateRequestParam = addOptionalField(SoapKeys.TYPE, product.getType(), catalogProductCreateRequestParam);
		catalogProductCreateRequestParam = addOptionalField(SoapKeys.SET, product.getSet(), catalogProductCreateRequestParam);
		catalogProductCreateRequestParam = addOptionalField(SoapKeys.SKU, product.getSku(), catalogProductCreateRequestParam);

		// Add product data here
		catalogProductCreateRequestParam = generateProductMessage(catalogProductCreateRequestParam, product);

		SOAPElement store = catalogProductCreateRequestParam.addChildElement(SoapKeys.STORE);
		store.addTextNode(product.getStore());

		catalogProductCreateRequestParam.addChildElement(generateCartIds(product.getAvailableCartsArray(), catalogProductCreateRequestParam));

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage getStylistInfo(String id, String ssID) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement(SoapKeys.STYLIST_INFO, SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement stylistId = getStylistRequestParam.addChildElement(SoapKeys.STYLIST_ID);
		stylistId.addTextNode(id);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage getStylistList(String ssID, String filterName, String operand, String filterValue) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement(SoapKeys.STYLIST_LIST, SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement filters = getStylistRequestParam.addChildElement(SoapKeys.FILTERS);
		SOAPElement complexFilter = filters.addChildElement(SoapKeys.COMPLEX_FILTER);
		SOAPElement complexObjectArray = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement key = complexObjectArray.addChildElement(SoapKeys.KEY);
		key.addTextNode(filterName);
		SOAPElement value = complexObjectArray.addChildElement(SoapKeys.VALUE);
		SOAPElement key2 = value.addChildElement(SoapKeys.KEY);
		key2.addTextNode(operand);
		SOAPElement value2 = value.addChildElement(SoapKeys.VALUE);
		value2.addTextNode(filterValue);

		// in testing
		SOAPElement complexObjectArrayB = complexFilter.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		SOAPElement keyB = complexObjectArrayB.addChildElement(SoapKeys.KEY);
		keyB.addTextNode(SoapConstants.SOAP_STATUS_FILTER);
		SOAPElement valueB = complexObjectArrayB.addChildElement(SoapKeys.VALUE);
		SOAPElement key2B = valueB.addChildElement(SoapKeys.KEY);
		key2B.addTextNode(SoapConstants.EQUAL);
		SOAPElement value2B = valueB.addChildElement(SoapKeys.VALUE);
		value2B.addTextNode("1");

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPMessage getProductInfo(String ssID, String productId) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();

		SOAPElement getProductRequestParam = soapBody.addChildElement(SoapKeys.PRODUCT_INFO, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = getProductRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement productIds = getProductRequestParam.addChildElement(SoapKeys.PRODUCT_IDS);
		SOAPElement complexObjectArray = productIds.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
		complexObjectArray.addTextNode(productId);

		soapMessage.saveChanges();
		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static SOAPElement generateProductMessage(SOAPElement bodyElement, ProductDetailedModel product) throws SOAPException {
		SOAPElement productData = bodyElement.addChildElement(SoapKeys.PRODUCT_DATA);

		productData = addOptionalField(SoapKeys.NAME, product.getName(), productData);
		productData = addOptionalField(SoapKeys.DESCRIPTION, product.getDescription(), productData);
		productData = addOptionalField(SoapKeys.SHORT_DESCRIPTION, product.getShortDescription(), productData);
		productData = addOptionalField(SoapKeys.WEIGHT, product.getWeight(), productData);
		productData = addOptionalField(SoapKeys.STATUS, product.getStatus(), productData);
		productData = addOptionalField(SoapKeys.URL_KEY, product.getUrlKey(), productData);
		productData = addOptionalField(SoapKeys.URL_PATH, product.getUrlPath(), productData);
		productData = addOptionalField(SoapKeys.VISIBILITY, product.getVisibility(), productData);
		productData = addOptionalField(SoapKeys.HAS_OPTIONS, product.getHasOptions(), productData);
		productData = addOptionalField(SoapKeys.GIFT_MSG_AV, product.getGiftMessageAvailable(), productData);
		productData = addOptionalField(SoapKeys.PRICE, product.getPrice(), productData);
		productData = addOptionalField(SoapKeys.SPECIAL_PRICE, product.getSpecialPrice(), productData);
		productData = addOptionalField(SoapKeys.SPECIAL_FROM_DATE, product.getSpecialFromDate(), productData);
		productData = addOptionalField(SoapKeys.SPECIAL_TO_DATE, product.getSpecialToDate(), productData);
		productData = addOptionalField(SoapKeys.TAX_CLASS_ID, product.getTaxClassId(), productData);
		productData = addOptionalField(SoapKeys.META_TITLE, product.getMetaTitle(), productData);
		productData = addOptionalField(SoapKeys.META_KEYWORD, product.getMetaKeyword(), productData);
		productData = addOptionalField(SoapKeys.META_DESCRIPTION, product.getMetaDescription(), productData);
		productData = addOptionalField(SoapKeys.CUSTOM_DESIGN, product.getCustomDesign(), productData);
		productData = addOptionalField(SoapKeys.CUSTOM_LAYOUT_UPDATE, product.getCustomLayoutUpdate(), productData);
		productData = addOptionalField(SoapKeys.OPTIONS_CONTAINER, product.getOptionsContainer(), productData);
		productData = addOptionalField(SoapKeys.PRODUCT_IP, product.getIp(), productData);
		productData = addOptionalField(SoapKeys.NEWS_FROM_DATE, product.getNewsFromDate(), productData);
		productData = addOptionalField(SoapKeys.NEWS_TO_DATE, product.getNewsToDate(), productData);
		productData = addOptionalField(SoapKeys.JEWERLY_BONUS_VALUE, product.getJewerlyBonusValue(), productData);
		productData = addOptionalField(SoapKeys.ALLOW_JEWERLY_BONUS_CART, product.getJewelryBonusCart(), productData);

		// productData = addOptionalField(SoapKeys.JEWELRY_BONUS,
		// product.getJewelryBonus(), productData);

		// Added stock data section
		productData.addChildElement(generateStockDataMessage(product.getStockData(), productData));

		// these should be implemented in the same manner
		productData.addChildElement(generateWebsiteIds(product.getWebsiteIdsArray(), productData));
		productData.addChildElement(generateWebsites(product.getWebsiteArray(), productData));
		productData.addChildElement(generateCategories(product.getCategoriesArray(), productData));
		productData.addChildElement(generateCategoryIds(product.getCategoryIdsArray(), productData));
		productData.addChildElement(generateAdditionalAttributes(product.getAdditionalAttributes(), productData));

		productData.addChildElement(generateTierPriceMessage(product.getTierPriceList(), productData));

		// Lists and other objects
		return productData;

	}

	private static SOAPElement generateCategoryData(SOAPElement bodyElement, CategoryModel cat) throws SOAPException {
		SOAPElement categoryData = bodyElement.addChildElement(SoapKeys.CATEGORY_DATA);

		categoryData = addOptionalField(SoapKeys.IS_ACTIVE, cat.getIsActive(), categoryData);
		categoryData = addOptionalField(SoapKeys.NAME, cat.getName(), categoryData);
		categoryData = addOptionalField(SoapKeys.INCLUDE_IN_MENU, cat.getIncludeInMenu(), categoryData);

		categoryData.addChildElement(generateFilters(cat.getAvailableSortBy(), categoryData));

		categoryData = addOptionalField(SoapKeys.DEFAULT_SORT_BY, cat.getDefaultSortBy(), categoryData);
		categoryData = addOptionalField(SoapKeys.URL_KEY, cat.getUrlKey(), categoryData);

		// Lists and other objects
		return categoryData;

	}

	private static SOAPElement generateProductUpdateStockMessage(SOAPElement bodyElement, ProductDetailedModel product) throws SOAPException {
		SOAPElement productData = bodyElement.addChildElement(SoapKeys.PRODUCT_DATA);

		productData.addChildElement(generateStockDataMessage(product.getStockData(), productData));

		return productData;

	}

	private static SOAPElement generateStockDataMessage(StockDataModel product, SOAPElement bodyElement) throws SOAPException {
		SOAPElement stockData = bodyElement.addChildElement(SoapKeys.STOCK_DATA);

		if (product != null) {
			stockData = addOptionalField(SoapKeys.QTY, product.getQty(), stockData);
			stockData = addOptionalField(SoapKeys.IS_IN_STOCK, product.getIsInStock(), stockData);
			stockData = addOptionalField(SoapKeys.MANAGE_STOCK, product.getManageStock(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_MANAGE_STOCK, product.getUseConfigManageStock(), stockData);
			stockData = addOptionalField(SoapKeys.MIN_QTY, product.getMinQty(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_MIN_QTY, product.getUseConfigMinQty(), stockData);
			stockData = addOptionalField(SoapKeys.MIN_SALE_QTY, product.getMinSaleQty(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_MIN_SALE_QTY, product.getUseConfigMinSaleQty(), stockData);
			stockData = addOptionalField(SoapKeys.MAX_SALE_QTY, product.getMaxSaleQty(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_MAX_SALE_QTY, product.getUseConfigMaxSaleQty(), stockData);
			stockData = addOptionalField(SoapKeys.IS_QTY_DECIMAL, product.getIsQtyDecimal(), stockData);
			stockData = addOptionalField(SoapKeys.BACKORDERS, product.getBackorders(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_BACKORDERS, product.getUseConfigBackorders(), stockData);
			stockData = addOptionalField(SoapKeys.NOTIFY_STOCK_QTY, product.getNotifyStockQty(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_NOTIFY_STOCK_QTY, product.getUseConfigNotifyStockQty(), stockData);
			stockData = addOptionalField(SoapKeys.IS_DISCONTINUED, product.getIsDiscontinued(), stockData);
			stockData = addOptionalField(SoapKeys.EARLIEST_AVAILABILITY, product.getEarliestAvailability(), stockData);
			stockData = addOptionalField(SoapKeys.MAXIMUM_PERCENTAGE_TO_BORROW, product.getMaximumPercentageToBorrow(), stockData);
			stockData = addOptionalField(SoapKeys.USE_CONFIG_MAXIMUM_PERCENTAGE_TO_BORROW, product.getUseConfigMaximumPercentageToBorrow(), stockData);
		} else {
			System.out.println("Warning: Product - Stock Data Model is NULL - see soap - generateStockDataMessage()");
		}
		return stockData;
	}

	/**
	 * Component of the product model. TierPrice Model
	 * 
	 * @param productList
	 * @param bodyElement
	 * @return
	 * @throws SOAPException
	 */
	private static SOAPElement generateTierPriceMessage(List<TierPriceModel> productList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement tierPrices = bodyElement.addChildElement(SoapKeys.TIER_PRICES);

		if (productList.size() > 0) {
			for (TierPriceModel product : productList) {
				SOAPElement objArray = tierPrices.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);

				SOAPElement groupId = objArray.addChildElement(SoapKeys.CUSTOMER_GROUP_ID);
				groupId.addTextNode(product.getCustomerGroupId());

				SOAPElement web = objArray.addChildElement(SoapKeys.WEBSITE);
				web.addTextNode(product.getCustomerGroupId());

				SOAPElement qty = objArray.addChildElement(SoapKeys.QTY);
				qty.addTextNode(product.getCustomerGroupId());

				SOAPElement price = objArray.addChildElement(SoapKeys.PRICE);
				price.addTextNode(product.getCustomerGroupId());

			}
		} else {
			System.out.println("Warning: Product - Tier Price Model list is empty - see soap - generateTierPriceMessage()");
		}
		return tierPrices;
	}

	private static SOAPElement generateAdditionalAttributes(Map<String, String> map, SOAPElement bodyElement) throws SOAPException {
		SOAPElement additionalAttributes = bodyElement.addChildElement(SoapKeys.ADDITIONAL_ATTRIBUTES);

		if (map.size() > 0) {
			for (String product : map.keySet()) {
				SOAPElement objArray = additionalAttributes.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);

				objArray.addChildElement(addOptionalField(SoapKeys.KEY, product, objArray));
				objArray.addChildElement(addOptionalField(SoapKeys.VALUE, map.get(product), objArray));
			}
		} else {
			System.out.println("Warning: Product - Additional Atributes list is empty - see soap - generateTierPriceMessage()");
		}
		return additionalAttributes;
	}

	/**
	 * Elements might bee kept in a different way.
	 * 
	 * @param productList
	 * @param bodyElement
	 * @return
	 * @throws SOAPException
	 */
	private static SOAPElement generateFilters(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement websiteIds = bodyElement.addChildElement(SoapKeys.AVAILABLE_SORT_BY);

		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = websiteIds.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Website Ids list is empty - see soap - generateTierPriceMessage()");
		}
		return websiteIds;
	}

	private static SOAPElement generateWebsiteIds(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement websiteIds = bodyElement.addChildElement(SoapKeys.WEBSITE_IDS);

		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = websiteIds.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Website Ids list is empty - see soap - generateTierPriceMessage()");
		}
		return websiteIds;
	}

	private static SOAPElement generateCartIds(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement websiteIds = bodyElement.addChildElement(SoapKeys.CARTS);

		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = websiteIds.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Cart Ids list is empty");
		}
		return websiteIds;
	}

	private static SOAPElement generateWebsites(List<String> idsList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement websites = bodyElement.addChildElement(SoapKeys.WEBSITES);

		if (idsList.size() > 0) {
			for (String product : idsList) {
				SOAPElement objArray = websites.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Websites list is empty - see soap - generateTierPriceMessage()");
		}
		return websites;
	}

	private static SOAPElement generateCategories(List<String> categoriesList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement categories = bodyElement.addChildElement(SoapKeys.CATEGORIES);

		if (categoriesList.size() > 0) {
			for (String product : categoriesList) {
				SOAPElement objArray = categories.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Categories list is empty - see soap - generateTierPriceMessage()");
		}
		return categories;
	}

	private static SOAPElement generateCategoryIds(List<String> categoriesList, SOAPElement bodyElement) throws SOAPException {
		SOAPElement categoryIDs = bodyElement.addChildElement(SoapKeys.CATEGORY_IDS);

		if (categoriesList.size() > 0) {
			for (String product : categoriesList) {
				SOAPElement objArray = categoryIDs.addChildElement(SoapKeys.COMPLEX_OBJECT_ARRAY);
				objArray.addTextNode(product);
			}
		} else {
			System.out.println("Warning: Product - Category Ids list is empty - see soap - generateTierPriceMessage()");
		}
		return categoryIDs;
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
		SOAPElement result = parentElement;
		if (fieldValue != null && !fieldValue.contentEquals("")) {
			SOAPElement elemNow = parentElement.addChildElement(fieldKey);
			elemNow.addTextNode(fieldValue);
			result.addChildElement(elemNow);
		}

		return result;
	}

	public static void main(String args[]) throws SOAPException, IOException {
		HttpSoapConnector.performLogin();
	}
}

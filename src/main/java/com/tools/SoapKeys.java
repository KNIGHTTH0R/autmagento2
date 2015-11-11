package com.tools;


public class SoapKeys {
	
	//SOAP TEST DATA - data grouping
	public static final String GRAB = "Grab";
	public static final String CALC = "Calc";
	public static final String COMPLETE = "Complete";
	public static final String CANCELED = "Cancelled";
	public static final String MAGENTO_INITIAL_CHANGING_STOCK = "MICS";
	public static final String NAVISION_INITIAL_CHANGING_STOCK = "NICS";
	public static final String MAGENTO_INITIAL_CONSTANT_STOCK = "MIConsS";
	public static final String NAVISION_INITIAL_CONSTANT_STOCK = "NIConsS";
	public static final String MAGENTO_AFTER_ORDER_PLACED_STOCK = "MagentoAfterOrderPlacedStock";
	public static final String NAVISION_AFTER_ORDER_PLACED_STOCK = "NavisionAfterOrderPlacedStock";
	public static final String MAGENTO_AFTER_ORDER_IMPORTED_STOCK = "MagentoAfterOrderImportedStock";
	public static final String NAVISION_AFTER_ORDER_IMPORTED_STOCK = "NavisionAfterOrderImportedStock";

	// General keys
	public static final String URN_PREFIX = "urn";
	public static final String SOAP_PREFIX = "soapenv";
	public static final String SERVER_URI = "urn:Magento";
	public static final String COMPLEX_OBJECT_ARRAY = "complexObjectArray";

	// Login keys
	public static final String LOGIN_PARAM = "loginParam";
	public static final String USER_NAME = "username";
	public static final String API_KEY = "apiKey";

	// Create Product Keys
	public static final String CATALOG_CONTAINER = "catalogProductCreateRequestParam";
	public static final String SESSION_ID = "sessionId";
	public static final String TYPE = "type";
	public static final String SET = "set";
	public static final String SKU = "sku";
	public static final String STORE = "store";
	public static final String CARTS = "carts";
	public static final String PRODUCT_DATA = "productData";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SHORT_DESCRIPTION = "short_description";
	public static final String WEIGHT = "weight";
	public static final String STATUS = "status";
	public static final String URL_KEY = "url_key";
	public static final String URL_PATH = "url_path";
	public static final String VISIBILITY = "visibility";
	public static final String HAS_OPTIONS = "has_options";
	public static final String GIFT_MSG_AV = "gift_message_available";
	public static final String PRICE = "price";
	public static final String SPECIAL_PRICE = "special_price";
	public static final String SPECIAL_FROM_DATE = "special_from_date";
	public static final String SPECIAL_TO_DATE = "special_to_date";
	public static final String TAX_CLASS_ID = "tax_class_id";
	public static final String META_TITLE = "meta_title";
	public static final String META_KEYWORD = "meta_keyword";
	public static final String META_DESCRIPTION = "meta_description";
	public static final String CUSTOM_DESIGN = "custom_design";
	public static final String CUSTOM_LAYOUT_UPDATE = "custom_layout_update";
	public static final String OPTIONS_CONTAINER = "options_container";
	public static final String PRODUCT_IP = "product_ip";
	public static final String JEWELRY_BONUS = "jewelry_bonus_value";
	public static final String NEWS_FROM_DATE = "news_from_date";
	public static final String NEWS_TO_DATE = "news_to_date";
	
	// Stock Data
	public static final String STOCK_DATA = "stock_data";
	public static final String QTY = "qty";
	public static final String IS_IN_STOCK = "is_in_stock";
	public static final String MANAGE_STOCK = "manage_stock";
	public static final String USE_CONFIG_MANAGE_STOCK = "use_config_manage_stock";
	public static final String MIN_QTY = "min_qty";
	public static final String USE_CONFIG_MIN_QTY = "use_config_min_qty";
	public static final String MIN_SALE_QTY = "min_sale_qty";
	public static final String USE_CONFIG_MIN_SALE_QTY = "use_config_min_sale_qty";
	public static final String MAX_SALE_QTY = "max_sale_qty";
	public static final String USE_CONFIG_MAX_SALE_QTY = "use_config_max_sale_qty";
	public static final String IS_QTY_DECIMAL = "is_qty_decimal";
	public static final String BACKORDERS = "backorders";
	public static final String USE_CONFIG_BACKORDERS = "use_config_backorders";
	public static final String NOTIFY_STOCK_QTY = "notify_stock_qty";
	public static final String USE_CONFIG_NOTIFY_STOCK_QTY = "use_config_notify_stock_qty";
	public static final String IS_DISCONTINUED = "is_discontinued";
	public static final String EARLIEST_AVAILABILITY = "earliest_availability";
	public static final String MAXIMUM_PERCENTAGE_TO_BORROW = "maximum_percent_to_borrow";
	public static final String USE_CONFIG_MAXIMUM_PERCENTAGE_TO_BORROW = "use_config_maximum_percent_to_borrow";
	public static final String JEWERLY_BONUS_VALUE = "jewelry_bonus_value";
	public static final String ALLOW_JEWERLY_BONUS_CART = "allow_jewelry_bonus_cart";
	// Tier Prices Model
	public static final String TIER_PRICES = "tier_price";
	public static final String CUSTOMER_GROUP_ID = "customer_group_id";
	public static final String WEBSITE = "website";
	public static final String WEBSITE_IDS = "website_ids";
	public static final String WEBSITES = "websites";
	public static final String CATEGORIES = "categories";
	public static final String CATEGORY_IDS = "category_ids";
	public static final String KEY = "key";
	public static final String VALUE = "value";
	public static final String RESULT = "result";
	public static final String ADDITIONAL_ATTRIBUTES = "additional_attributes";

	// delete customer
	public static final String CUSTOMER_CUSTOMER_DELETE_REQUEST_PARAM = "customerCustomerDeleteRequestParam";
	public static final String CUSTOMER_ID = "customerId";
	
	//getStylistList
	public static final String STYLIST_LIST = "stylistStylistListRequestParam";
	public static final String STYLIST_ID = "stylistId";
	public static final String STYLIST_INFO = "stylistStylistInfoRequestParam";
	public static final String FILTERS = "filters";
	public static final String COMPLEX_FILTER = "complex_filter";
	
	//getOrdersList
	public static final String ORDERS_LIST = "salesOrderListRequestParam";
	//getCreditMemosList
	public static final String CREDIT_MEMOS_LIST = "salesOrderCreditmemoListRequestParam";
	
	//get product info   
	
	public static final String PRODUCT_INFO = "catalogInventoryStockItemListRequestParam";
	public static final String PRODUCT_IDS = "productIds";
}

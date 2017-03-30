package com.connectors.http;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.constants.EnvironmentConstants;
//import com.tools.constants.SoapConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;

public class OrderInfoMagCalls {

	public static DBOrderModel getOrdersInfo(String orderIncrementId, String sessionId) {

		DBOrderModel order = new DBOrderModel();

		try {
			SOAPMessage response = soapGetOrdersInfo(orderIncrementId, sessionId);
			System.out.println(response);
			try {
				order = extractOrderInfoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return order;
	}

	public static SOAPMessage soapGetOrdersInfo(String orderIncrementId, String sessionId)
			throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
				EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersInfoRequest(sessionId,
		// orderIncrementId),
		// "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersInfoRequest(sessionId,
		// orderIncrementId),
		// "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getOrdersInfoRequest(String ssID, String order_Increment_Id)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement("salesOrderInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement orderIncrementId = getStylistRequestParam.addChildElement("orderIncrementId");
		orderIncrementId.addTextNode(order_Increment_Id);

		SOAPElement clinetKey = getStylistRequestParam.addChildElement(SoapKeys.CLIENT_KEY);
		clinetKey.addTextNode(SoapKeys.CLIENT_KEY_VALUE);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static DBOrderModel extractOrderInfoData(SOAPMessage response) throws Exception {

		DBOrderModel model = defaultDBOrderModelValues();
		List<SalesOrderInfoModel> item = new ArrayList<SalesOrderInfoModel>();

		NodeList result = response.getSOAPBody().getElementsByTagName("result");
		NodeList itemList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		NodeList shippingAddress = response.getSOAPBody().getElementsByTagName("shipping_address");
		NodeList billigAddress = response.getSOAPBody().getElementsByTagName("billing_address");
		NodeList stylist = response.getSOAPBody().getElementsByTagName("stylist");
		NodeList customer = response.getSOAPBody().getElementsByTagName("customer");

		BigDecimal shippingAmount = BigDecimal.valueOf(0);
		BigDecimal taxPercent = BigDecimal.valueOf(0);
		BigDecimal subtotal = BigDecimal.valueOf(Double.parseDouble(model.getBaseSubtotal()));
		BigDecimal originalPrice = BigDecimal.valueOf(Double.parseDouble(model.getOriginalPrice()));
		BigDecimal qtyOrdered = BigDecimal.valueOf(Double.parseDouble(model.getQtyOrdered()));

		BigDecimal baseDiscountAmount = BigDecimal.valueOf(Double.parseDouble(model.getBaseDiscountAmount()));
		BigDecimal jewelryCreditsUsed = BigDecimal.valueOf(Double.parseDouble(model.getJewelryCreditsUsed()));
		BigDecimal marketingCreditsUsed = BigDecimal.valueOf(Double.parseDouble(model.getMarketingCreditsUsed()));
		BigDecimal fiftyDiscountsUsed = BigDecimal.valueOf(Double.parseDouble(model.getFiftyDiscountsUsed()));
		BigDecimal fiftyDiscountsAmount = BigDecimal.valueOf(Double.parseDouble(model.getFiftyDiscountsAmount()));

		boolean isBundle = false;
		boolean isConfigurableParent = false;

		for (int i = 0; i < result.getLength(); i++) {
			NodeList resultNodes = result.item(i).getChildNodes();
			for (int r = 0; r < resultNodes.getLength(); r++) {

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_nav")) {
					model.setUpdatedNav(DateUtils.parseDate(resultNodes.item(r).getTextContent(), "yyyy-MM-dd HH:mm:ss",
							"yyyy-MM-dd", new Locale.Builder().setLanguage("de").build()));

				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("order_currency_code")) {
					model.setOrderCurrencyCode(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("grand_total")) {
					String grand_total = FormatterUtils.parseValueToTwoDecimals(resultNodes.item(r).getTextContent());

					// System.out.println("grandCalculated "+grandCalculated);
					model.setGrandTotal(grand_total);
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("tax_amount")) {
					model.setTaxAmount(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("style_party_id")) {
					model.setStylePartyId(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("kobo_single_article")) {
					model.setKoboSingleArticle(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_firstname")) {
					model.setCustomerFirstName(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_lastname")) {
					model.setCustomerLastName(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("is_pom")) {
					model.setIsPom(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("tax_percent")) {
					taxPercent = taxPercent
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
					System.out.println("taxPercenttaxPercent " + taxPercent);
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("shipping_incl_tax")) {
					// model.setShippingAmount(resultNodes.item(r).getTextContent());
					model.setShippingAmount(resultNodes.item(r).getTextContent());
					shippingAmount = shippingAmount
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("base_discount_amount")) {
					model.setBaseDiscountAmount(resultNodes.item(r).getTextContent());
					baseDiscountAmount = baseDiscountAmount
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent()))).negate();
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("jewelry_credits_used")) {
					model.setJewelryCreditsUsed(resultNodes.item(r).getTextContent());
					jewelryCreditsUsed = jewelryCreditsUsed
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("marketing_credits_used")) {
					model.setMarketingCreditsUsed(resultNodes.item(r).getTextContent());
					marketingCreditsUsed = marketingCreditsUsed
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("fifty_discounts_used")) {
					model.setFiftyDiscountsUsed(resultNodes.item(r).getTextContent());
					fiftyDiscountsUsed = fiftyDiscountsUsed
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("fifty_discounts_amount")) {
					model.setFiftyDiscountsAmount(resultNodes.item(r).getTextContent());
					fiftyDiscountsAmount = fiftyDiscountsAmount
							.add(BigDecimal.valueOf(Double.parseDouble(resultNodes.item(r).getTextContent())));
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("order_customer_name")) {
					model.setOrderCustomerName(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("billing_address")) {

					NodeList billingNodes = billigAddress.item(i).getChildNodes();
					for (int b = 0; b < billingNodes.getLength(); b++) {
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("firstname")) {
							model.setBillToFirstName(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("lastname")) {
							model.setBillToLastName(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("postcode")) {
							model.setBillToPostcode(billingNodes.item(b).getTextContent());
						}

						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("street")) {
							model.setBillToStreetAddress(billingNodes.item(b).getTextContent());
						}
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("city")) {
							model.setBillToCity(billingNodes.item(b).getTextContent());
						}
						if (billingNodes.item(b).getNodeName().equalsIgnoreCase("country_id")) {
							model.setBillCountryId(billingNodes.item(b).getTextContent());
						}
					}
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("shipping_address")) {
					NodeList shippingNodes = shippingAddress.item(i).getChildNodes();
					for (int s = 0; s < shippingNodes.getLength(); s++) {
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("firstname")) {
							model.setShipToFirstName(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("lastname")) {
							model.setShipToLastName(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("postcode")) {
							model.setShipToPostcode(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("street")) {
							model.setShipToStreetAddress(shippingNodes.item(s).getTextContent());
						}
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("city")) {
							model.setShipToCity(shippingNodes.item(s).getTextContent());
						}
						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("country_id")) {
							model.setShipCountryId(shippingNodes.item(s).getTextContent());
						}

						if (shippingNodes.item(s).getNodeName().equalsIgnoreCase("house_number")) {
							model.setShipToHousNumber(shippingNodes.item(s).getTextContent());
						}
					}
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist")) {

					NodeList stylistNodes = stylist.item(i).getChildNodes();

					for (int x = 0; x < stylistNodes.getLength(); x++) {

						if (stylistNodes.item(x).getNodeName().equalsIgnoreCase("customer")) {
							NodeList customerNodes = customer.item(i).getChildNodes();
							for (int m = 0; m < customerNodes.getLength(); m++) {
								if (customerNodes.item(m).getNodeName().equalsIgnoreCase("vat_valid")) {
									model.setVatNumber(customerNodes.item(m).getTextContent());

								}

								if (customerNodes.item(m).getNodeName().equalsIgnoreCase("small_business_man")) {
									model.setSmallBusinessMan(customerNodes.item(m).getTextContent());
								}

								if (customerNodes.item(m).getNodeName().equalsIgnoreCase("bank_account_vat_number")) {
									if (!customerNodes.item(m).getTextContent().replaceAll("\\s+", "")
											.contentEquals("")) {
										model.setBanckAccountNumber(customerNodes.item(m).getTextContent());
									}
								}
							}
						}

					}
				}
			}

			for (int c = 0; c < itemList.getLength(); c++) {
				if (itemList.item(c).getParentNode().getNodeName().equalsIgnoreCase("items")) {
					SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
					NodeList childNodes = itemList.item(c).getChildNodes();

					for (int j = 0; j < childNodes.getLength(); j++) {

						if (childNodes.item(j).getNodeName().equalsIgnoreCase("product_type")) {
							isConfigurableParent = childNodes.item(j).getTextContent().contentEquals("configurable")
									? true : false;
						}
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("product_type")) {

							isBundle = childNodes.item(j).getTextContent().contentEquals("bundle") ? true : false;
						}
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("sku")) {
							if (isBundle) {

								// infoItem.setSku(retriveBundleParent(childNodes.item(j).getTextContent()));
								infoItem.setSku("bundle");
							} else if (isConfigurableParent == false) {
								infoItem.setSku(childNodes.item(j).getTextContent().toUpperCase());
							}

						}

						if (childNodes.item(j).getNodeName().equalsIgnoreCase("tax_percent")) {
							model.setTaxPrecent(childNodes.item(j).getTextContent());

						}
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("base_price_incl_tax")) {
							// model.setOriginalPrice(childNodes.item(j).getTextContent());

//							System.out.println("formateddd "
//									+ FormatterUtils.parseValueToTwoDecimals(childNodes.item(j).getTextContent()));
							originalPrice = BigDecimal.valueOf(0);
							originalPrice = BigDecimal.valueOf(Double.parseDouble(
									FormatterUtils.parseValueToTwoDecimals(childNodes.item(j).getTextContent())));
						}

						if (childNodes.item(j).getNodeName().equalsIgnoreCase("qty_ordered")) {
							// /model.setQtyOrdered(childNodes.item(j).getTextContent());
							qtyOrdered = BigDecimal.valueOf(0);
							qtyOrdered = BigDecimal.valueOf(Double.parseDouble(childNodes.item(j).getTextContent()));
						}
						if (childNodes.item(j).getNodeName().equalsIgnoreCase("tax_percent")) {
							taxPercent = BigDecimal.valueOf(Double.parseDouble(childNodes.item(j).getTextContent()));
						}
					}
					// do not add configurable parent on list
					if (isConfigurableParent == false) {
						item.add(infoItem);
					}

					// do not consider in calculation parent of configurable and
					// bundle items
					if (isConfigurableParent == false && isBundle == false) {

						subtotal = subtotal.add(originalPrice.multiply(qtyOrdered));

					}

				}
			}

		}

		BigDecimal calculateGrandTotal = subtotal.add(shippingAmount).subtract(baseDiscountAmount)
				.subtract(jewelryCreditsUsed).subtract(marketingCreditsUsed).subtract(fiftyDiscountsAmount);

		// System.out.println("calculateGrandTotal " + calculateGrandTotal);
		BigDecimal calculatedTaxAmount = calcultatedTaxAmount(taxPercent, calculateGrandTotal);

		// System.out.println("calculatedTaxAmount " + calculatedTaxAmount);
		model.setCalculatedGrandTotal(calculateGrandTotal.toString());
		model.setCalculatedTaxAmount(calculatedTaxAmount.toString());
		model.setItemInfo(item);

		return model;
	}

	private static BigDecimal calcultatedTaxAmount(BigDecimal taxPercent, BigDecimal gtValue) {
		BigDecimal value = BigDecimal.valueOf(100);
		BigDecimal vat = taxPercent.add(value).divide(value);
		BigDecimal shopTaxAmount = gtValue.subtract(gtValue.divide(vat, 2, RoundingMode.HALF_UP));
		return shopTaxAmount;
	}

	public static DBOrderModel defaultDBOrderModelValues() {
		DBOrderModel model = new DBOrderModel();
		model.setIncrementId("null");
		model.setCreatedAt("null");

		model.setCustomerId("null");
		model.setPaymentCompleteAt("null");
		model.setStylistCustomerId("null");
		model.setOrderId("null");
		model.setPaymentMethodTypet("null");
		model.setShippingType("null");
		model.setOrderType("null");
		model.setCartType("null");
		model.setStylePartyId("null");
		model.setGrandTotal("null");
		model.setIsPreshipped("null");
		model.setIsPom("null");
		model.setWebsiteCode("null");
		model.setStoreLanguage("null");
		model.setKoboSingleArticle("null");
		model.setUpdatedNav("null");
		model.setStylistId("null");
		model.setOrderCurrencyCode("null");

		// should be calculated maybe;
		model.setBaseSubtotal("0");
		model.setTaxAmount("null");
		model.setTaxPrecent("null");

		model.setCustomerFirstName("null");
		model.setCustomerLastName("null");
		model.setCustomerName("null");

		// from billing list details
		model.setBillToFirstName("null");
		model.setBillToLastName("null");
		model.setBillToPostcode("null");
		model.setBillToStreetAddress("null");
		model.setBillToCity("null");
		model.setBillCountryId("null");

		// from shipping list details
		model.setShipToFirstName("null");
		model.setShipToLastName("null");
		model.setShipToPostcode("null");
		model.setShipToStreetAddress("null");
		model.setShipToCity("null");
		model.setShipCountryId("null");

		// in nav should be extreacted from lines
		model.setShippingInclTax("null");
		model.setDiscountAmount("null");

		model.setPaidAt("null");
		model.setStatus("null");

		model.setTotalIp("null");
		model.setTotalIpRefunded("null");
		model.setTermPurchaseType("null");
		model.setOrderCustomerName("null");
		model.setScheduledDeliveryDate("null");
		//
		// private List<SalesOrderInfoModel> itemInfo;
		// private List<String> itemSku;
		model.setShippingAmount("null");
		model.setOriginalPrice("0");
		model.setQtyOrdered("0");
		model.setBaseDiscountAmount("0");
		model.setJewelryCreditsUsed("0");
		model.setMarketingCreditsUsed("0");
		model.setFiftyDiscountsAmount("0");
		model.setFiftyDiscountsUsed("0");
		model.setOrderCustomerEmail("null");
		model.setShipToHousNumber("null");
		model.setBillToHousNumber("null");
		model.setVatNumber("null");
		model.setBanckAccountNumber("null");
		model.setSmallBusinessMan("null");
		model.setLanguageCode("null");
		model.setContactid("null");
		return model;
	}

	public static boolean containsNumbers(String password) {
		boolean digitFound = false;
		for (char ch : password.toCharArray()) {
			if (Character.isDigit(ch)) {
				digitFound = true;
			}
		}
		return digitFound;
	}

	public static boolean isLowerCase(String s) {
		boolean isLowerCase = false;
		if (Character.isLowerCase(s.charAt(0))) {
			isLowerCase = true;
		}
		return isLowerCase;
	}

	public static String retriveBundleParent(String sku) {

		System.out.println("skuull" + sku);
		List<String> list = new ArrayList<String>(Arrays.asList(sku.split("-")));
		String parentSku = null;

		if (list.size() > 1) {
			if (containsNumbers(list.get(1).substring(0, 1)) || isLowerCase(list.get(1).substring(0, 1))) {
				parentSku = list.get(0) + "-" + list.get(1);
			} else {
				parentSku = list.get(0);
			}
		} else {
			parentSku = list.get(0);
		}

		System.out.println("parintele" + parentSku);
		return parentSku;
	}

	public static void main(String[] args) throws SOAPException, IOException {

		String sessID = HttpSoapConnector.performLogin();
		DBOrderModel dbmodel = OrderInfoMagCalls.getOrdersInfo("10021876100", sessID);

		System.out.println("style party  : " + dbmodel.getStylePartyId());
		System.out.println("kobo article " + dbmodel.getKoboSingleArticle());

		System.out.println("bill post  : " + dbmodel.getBillToPostcode());
		System.out.println("bill fname  : " + dbmodel.getBillToFirstName());
		System.out.println("billl lname : " + dbmodel.getBillToLastName());
		System.out.println("bill street : " + dbmodel.getBillToStreetAddress());
		System.out.println("bill city : " + dbmodel.getBillToCity());
		System.out.println("bill country id : " + dbmodel.getBillCountryId());

		System.out.println("customer_firstname: " + dbmodel.getCustomerFirstName());
		System.out.println("customer_lastname : " + dbmodel.getCustomerLastName());

		// shipp

		System.out.println("shipp post : " + dbmodel.getShipToPostcode());
		System.out.println("fname  : " + dbmodel.getShipToFirstName());
		System.out.println("ship lname : " + dbmodel.getShipToLastName());
		System.out.println("ship street : " + dbmodel.getShipToStreetAddress());
		System.out.println("ship city : " + dbmodel.getShipToCity());
		System.out.println("ship country id : " + dbmodel.getShipCountryId());

		System.out.println("updated nav: " + dbmodel.getUpdatedNav());
		System.out.println("order currency code : " + dbmodel.getOrderCurrencyCode());

		List<SalesOrderInfoModel> list = dbmodel.getItemInfo();
		for (SalesOrderInfoModel salesOrderInfoModel : list) {
			System.out.println("sku: " + salesOrderInfoModel.getSku());
		}
	}
}
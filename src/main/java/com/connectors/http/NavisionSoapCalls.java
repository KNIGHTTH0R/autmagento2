package com.connectors.http;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.tools.data.soap.NavOrderModel;

public class NavisionSoapCalls {

	public static List<NavOrderModel> getOrdersList(String filterValue) throws Exception {

		Authentication.setAuthenticator();

		List<NavOrderModel> orderList = null;
		try {
			SOAPMessage response = sendOrdersListRequest(filterValue);
			System.out.println("content description " + response);
			orderList = extractOrderData(response);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orderList;
	}

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 * @throws IOException
	 */
	protected static SOAPMessage createSalesOrderDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.SALES_ORDER_URN_PREFIX,
				NavSoapKeys.SALES_ORDER_SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	private static SOAPMessage createOrdersListRequest(String orderIncrementId) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSalesOrderDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE,
				NavSoapKeys.SALES_ORDER_URN_PREFIX);
		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		// field.addTextNode("No");
		field.addTextNode("Your_Reference");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.SALES_ORDER_URN_PREFIX);
		criteria.addTextNode(orderIncrementId);
		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage sendOrdersListRequest(String filterValue) throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(createOrdersListRequest(filterValue),
				NavSoapKeys.SALES_ORDER_API_URI);

		return soapResponse;
	}

	private static List<NavOrderModel> extractOrderData(SOAPMessage response) throws Exception {

		List<NavOrderModel> orderModelList = new ArrayList<NavOrderModel>();

		NodeList orderList = response.getSOAPBody().getElementsByTagName("SalesOrder");
		for (int i = 0; i < orderList.getLength(); i++) {

			if (orderList.item(i).getParentNode().getNodeName().equalsIgnoreCase("ReadMultiple_Result")) {
				NavOrderModel model = defaultNavOrderModelValues();
				BigDecimal grandTotal = BigDecimal.valueOf(0);

				BigDecimal shippingDiscount = BigDecimal.valueOf(0);
				BigDecimal totalIP = BigDecimal.valueOf(0);
				String shippingAddress = "";
				String billingAddress = "";
				String shippingAddress1 = "";
				String billingAddress1 = "";
				int count=1;
				NodeList childNodes = orderList.item(i).getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("No")) {
						model.setIncrementId(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Total_Amount_Incl_VAT")) {
						model.setBaseGrandTotal(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Order_Date")) {
						model.setOrderDate(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Posting_Date")) {
						model.setPostingDate(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Salesperson_Code")) {
						model.setSalesPersonCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Your_Reference")) {
						model.setYouRefercences(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("External_Document_No")) {
						model.setExternalDocumentNo(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Sell_to_Customer_No")) {
						model.setSellToCustomerNo(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("shop_shipment_method")) {
						model.setShopShipmentMethod(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_Payment_Method")) {
						model.setShopPaymentMethod(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_Order_Type")) {
						model.setShopOrderType(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_To_Email")) {
						model.setShipToEmail(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_Cart_Type")) {

						model.setShopCartType(childNodes.item(j).getTextContent());

					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("PartyId")) {
						if (!childNodes.item(j).getTextContent().contentEquals("0")) {
							model.setPartyId(childNodes.item(j).getTextContent());
						}

					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("IsAlreadyShipped")) {
						model.setIsAlreadyShipped(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Magento_Grand_Total")) {
						model.setMagentoGrandTotal(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_is_pom")) {
						model.setShopIsPom(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_Website_Code")) {
						model.setShopWebsiteCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Shop_store_language")) {
						model.setShopStoreLanguage(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Kobo_Article_Only")) {
						model.setKoboSingleArticle(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Prepmt_Pmt_Discount_Date")) {
						model.setPrepmtPmtDiscountDate(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_Name")) {
						model.setBillToName(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_Address")) {
						billingAddress = childNodes.item(j).getTextContent();
						//System.out.println("billingAddress " +billingAddress);
						model.setBillToAddress(billingAddress.concat(billingAddress1));
						
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_Address_2")) {
						billingAddress1 = childNodes.item(j).getTextContent();
						model.setBillToAddress(billingAddress.concat(" " + billingAddress1));
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_Post_Code")) {
						model.setBillToPostCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_City")) {
						model.setBillToCity(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Bill_to_Country_Region_Code")) {
						model.setBillToCountryRegionCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Name")) {
						model.setShipToName(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Address")) {
						shippingAddress = childNodes.item(j).getTextContent();
						model.setShipToAddress(shippingAddress.replaceAll("\\s+", "").concat(shippingAddress1.replaceAll("\\s+", "")));
					}
//					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Address_2")) {
//						shippingAddress1 = childNodes.item(j).getTextContent();
//						model.setShipToAddress(shippingAddress.replaceAll("\\s+", "").concat(" "+shippingAddress1.replaceAll("\\s+", "")));
//					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Name_2")) {
						shippingAddress1 = childNodes.item(j).getTextContent();
						model.setShipToAddress(shippingAddress.replaceAll("\\s+", "").concat(" "+shippingAddress1.replaceAll("\\s+", "")));
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Post_Code")) {
						model.setShipToPostCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_City")) {
						model.setShipToCity(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_Country_Region_Code")) {
						model.setShipToCountryRegionCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Ship_to_House_Number")) {
						model.setShipToHouseNumber(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Sell_to_Customer_Name")) {
						model.setSellToCustomerName(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Contains_BOM")) {
						model.setContainsBom(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Sell_to_Address")) {
						model.setSellToAddress(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("VAT_Registration_No_Valid")) {
						model.setVatNumber(childNodes.item(j).getTextContent());
					}
					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Small_Business")) {
						model.setSmallBusinessMan(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("VAT_Registration_No")) {
						model.setBanckAccountNumber(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("Language_Code")) {
						model.setLanguageCode(childNodes.item(j).getTextContent());
					}

					if (childNodes.item(j).getNodeName().equalsIgnoreCase("SalesLines")) {

						List<NavOrderLinesModel> orderLinesModel = new ArrayList<NavOrderLinesModel>();

						NodeList orderLinesList = childNodes.item(j).getChildNodes();

						for (int k = 0; k < orderLinesList.getLength(); k++) {
							String charge = null;

							NavOrderLinesModel line = defaultNavOrderLinesModelValues();

							NodeList lineNodes = orderLinesList.item(k).getChildNodes();
							for (int l = 0; l < lineNodes.getLength(); l++) {

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("No")) {
									line.setNo(lineNodes.item(l).getTextContent());
								}

								// emilian
								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Type")) {
									if (lineNodes.item(l).getTextContent().contentEquals("Charge_Item")) {
										charge = lineNodes.item(l).getTextContent();
									}
									line.setType(lineNodes.item(l).getTextContent());

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("BOM_Item_No")) {
									line.setIsBomItem(true);
									line.setBomItemNo(lineNodes.item(l).getTextContent());
								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Shop_Parent_Item_No")) {
									line.setShopParentItemNo(lineNodes.item(l).getTextContent());
								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Variant_Code")) {
									line.setVarianteCode(lineNodes.item(l).getTextContent());
								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Quantity")) {
									line.setQty(lineNodes.item(l).getTextContent());

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Line_Amount")) {
									
									line.setLineAmount(lineNodes.item(l).getTextContent());
//									 double lineAmountString =
//									 Double.parseDouble(lineNodes.item(l).getTextContent());
//									 BigDecimal lineAmount =
//									 BigDecimal.valueOf(lineAmountString);
//									 System.out.println(lineAmount);
//									 grandTotal2 =
//									 grandTotal2.add(lineAmount);

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Line_Discount_Amount")) {
									line.setLineDiscountAmount(lineNodes.item(l).getTextContent());
									if (line.getType().contains("Charge_Item")) {
										shippingDiscount = BigDecimal
												.valueOf(Double.parseDouble(lineNodes.item(l).getTextContent()));
									}

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("VAT_Prod_Posting_Group")) {
									model.setVatProdPostingGroup(lineNodes.item(l).getTextContent());

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Unit_Price")) {
									line.setUnitPrice(lineNodes.item(l).getTextContent());

									if (charge != null) {

										model.setShippingAmount(lineNodes.item(l).getTextContent());
									}

								}

								if (lineNodes.item(l).getNodeName().equalsIgnoreCase("Item_IP")) {
									double lineItemIpDouble = Double.parseDouble(lineNodes.item(l).getTextContent());
									BigDecimal lineItemIp = BigDecimal.valueOf(lineItemIpDouble);
									totalIP = totalIP.add(lineItemIp);

								}
							}
							orderLinesModel.add(line);

							NavOrderLinesModel lastLine = orderLinesModel.get(orderLinesModel.size() - 1);
							System.out.println("lastLine.getUnitPrice() "+lastLine.getUnitPrice());
							System.out.println("lastLine.getQty() "+lastLine.getQty());
							System.out.println("lastLine.getLineDiscountAmount()) "+lastLine.getLineDiscountAmount());
							
							double lineAmountString = Double.parseDouble(lastLine.getLineAmount());
							BigDecimal lineAmount =BigDecimal.valueOf(lineAmountString);
							System.out.println(lineAmount);
									 
							
//							BigDecimal lineAmount = calculateGrandTotal(lastLine.getUnitPrice(), lastLine.getQty(),
//									lastLine.getLineDiscountAmount());
							
							System.out.println("counter "+ count++);
							System.out.println("lineAmount "+lineAmount);
							grandTotal = grandTotal.add(lineAmount);
							System.out.println("grandTotalgrandTotal" +grandTotal);
							if (lastLine.getNo() == null) {

								// if product is bom else is bundle
								if (lastLine.getBomItemNo() != null && lastLine.getType() != "_blank_") {
									String bomItemNo = lastLine.getBomItemNo();
									lastLine.setNo(bomItemNo);
								} else {
									lastLine.setNo("bundle");
								}
							}

							// if product is configurable
							if (lastLine.getVarianteCode() != null) {
								String variantCode = lastLine.getVarianteCode();
								String no = lastLine.getNo();

								lastLine.setNo(no.toUpperCase() + "-" + variantCode.toUpperCase());
							}

						}
						System.out.println("grandTotal fara sh "+grandTotal);
						System.out.println("shippingDiscount "+shippingDiscount);
						grandTotal = grandTotal.subtract(shippingDiscount);
						System.out.println("grandTotal "+grandTotal);
						model.setTotalIp(totalIP.toString());
						model.setCalculatedGrandTotal(grandTotal.toString());
						model.setLines(orderLinesModel);

					}
				}
				orderModelList.add(model);

			}
		}
		return orderModelList;
	}

	private static NavOrderModel defaultNavOrderModelValues() {
		NavOrderModel model = new NavOrderModel();
		// model.setIncrementId("null");
		model.setNavGrandTotal("null");
		model.setPostingDate("null");
		model.setOrderDate("null");

		model.setExternalDocumentNo("null");
		model.setVatProdPostingGroup("null");

		model.setCalculatedGrandTotal("null");

		model.setSalesPersonCode("null");
		model.setYouRefercences("null");
		model.setSellToContactNo("null");
		model.setSellToCustomerNo("null");
		model.setShopShipmentMethod("null");
		model.setShopPaymentMethod("null");
		model.setShopOrderType("null");
		model.setShopCartType("null");
		model.setShippingAmount("0");
		model.setPartyId("null");
		model.setIsAlreadyShipped("null");
		model.setMagentoGrandTotal("null");
		model.setShopIsPom("null");
		model.setShopWebsiteCode("null");
		model.setShopStoreLanguage("null");
		model.setKoboSingleArticle("null");
		model.setPrepmtPmtDiscountDate("null");
		model.setBaseGrandTotal("null");
		model.setDiscountAmount("null");

		model.setBillToName("null");
		model.setBillToAddress("null");
		model.setBillToPostCode("null");
		model.setBillToCity("null");
		model.setBillToCountryRegionCode("null");
		model.setShipToName("null");
		model.setShipToAddress("null");
		model.setShipToPostCode("null");
		model.setShipToCity("null");
		model.setShipToCountryRegionCode("null");
		model.setSellToCustomerName("null");// include firstname +last name
		model.setSellToAddress("null");
		model.setContainsBom("null");

		// not in both env
		model.setStatus("null");
		model.setTotalIp("null");
		model.setShippingType("null");
		model.setShipToEmail("null");
		model.setShipToHouseNumber("null");
		model.setVatNumber("null");
		model.setBanckAccountNumber("null");
		model.setSmallBusinessMan("null");
		model.setLanguageCode("null");

		return model;
	}

	private static NavOrderLinesModel defaultNavOrderLinesModelValues() {
		NavOrderLinesModel navLine = new NavOrderLinesModel();
		// navLine.setNo("null");
		navLine.setType("null");
		// navLine.setBomItem("null");
		// navLine.setBomItemNo("null");
		// navLine.setShopParentItemNo("null");
		// navLine.setVarianteCode("null");
		navLine.setShippingAmount("null");
		navLine.setLineDiscountAmount("null");
		navLine.setQty("null");
		navLine.setUnitPrice("null");

		return navLine;
	}

	private static BigDecimal calculateGrandTotal(String unitPrice, String qty, String lineDiscountAmount) {

		
		BigDecimal grandT = BigDecimal.valueOf(0);
		BigDecimal unitPrice1 = BigDecimal.valueOf(Double.parseDouble(unitPrice)).setScale(4, BigDecimal.ROUND_HALF_UP);
		BigDecimal qty1 = BigDecimal.valueOf(Double.parseDouble(qty));
		BigDecimal lineDiscountAmount1 = BigDecimal.valueOf(Double.parseDouble(lineDiscountAmount));
		
		
		System.out.println("unitPrice1 "+unitPrice1);
		System.out.println("qty1 "+qty1);
		System.out.println("lineDiscountAmount1 "+lineDiscountAmount1);
		grandT = unitPrice1.multiply(qty1).subtract(lineDiscountAmount1).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		
		return grandT;

	}

	public static void main(String args[]) throws Exception {

		// List<NavOrderModel> ordersList =
		// NavisionSoapCalls.getOrdersList("10023578400..10023578700");
		// 10021960100
		
		System.out.println("calculata valoare  "+calculateGrandTotal("0.84034", "90", "68.56"));
		
		System.out.println("test");
		List<NavOrderModel> ordersList = NavisionSoapCalls.getOrdersList("221749..221749");

		for (NavOrderModel order : ordersList) {
			System.out.println("ceva ");
			System.out.println("increment id " + order.getIncrementId());

			System.out.println("base grand total " + order.getBaseGrandTotal());
			// System.out.println("order date " + order.getOrderDate());
			// System.out.println("posting date " + order.getPostingDate());
			// System.out.println("sales person code " +
			// order.getSalesPersonCode());
			// System.out.println("your referecenes " +
			// order.getYouRefercences());
			// System.out.println("external doc no " +
			// order.getExternalDocumentNo());
			// System.out.println("sell customer no " +
			// order.getSellToCustomerNo());
			// System.out.println("shop shipment method " +
			// order.getShopShipmentMethod());
			// System.out.println("shop payment method " +
			// order.getShopPaymentMethod());
			// System.out.println("getShopOrderType " +
			// order.getShopOrderType());
			// System.out.println("getShopCartType " + order.getShopCartType());
			// System.out.println("getSalesPersonCode " +
			// order.getSalesPersonCode());
			// System.out.println("getPartyId " + order.getPartyId());
			// System.out.println("getIsAlreadyShipped " +
			// order.getIsAlreadyShipped());
			// System.out.println("getMagentoGrandTotal " +
			// order.getMagentoGrandTotal());
			// System.out.println("getShopIsPom " + order.getShopIsPom());
			//
			// System.out.println("getShopWebsiteCode " +
			// order.getShopWebsiteCode());
			// System.out.println("getShopStoreLanguage " +
			// order.getShopStoreLanguage());
			System.out.println("getKoboSingleArticle " + order.getKoboSingleArticle());
			// System.out.println("getPrepmtPmtDiscountDate " +
			// order.getPrepmtPmtDiscountDate());
			// System.out.println("getBillToName " + order.getBillToName());
			System.out.println("getBillToAddress " + order.getBillToAddress());
			// System.out.println("getBillToPostCode " +
			// order.getBillToPostCode());
			// System.out.println("getBillToCity " + order.getBillToCity());
			// System.out.println("getBillToCountryRegionCode " +
			// order.getBillToCountryRegionCode());
			// System.out.println("getShipToName " + order.getShipToName());
			//
			System.out.println("getShipToAddress " + order.getShipToAddress());
			// System.out.println("getShipToPostCode " +
			// order.getShipToPostCode());
			// System.out.println("getShipToCity " + order.getShipToCity());
			// System.out.println("getShipToCountryRegionCode " +
			// order.getShipToCountryRegionCode());
			// System.out.println("getSellToCustomerName " +
			// order.getSellToCustomerName());
			// System.out.println("getSellToAddress " +
			// order.getSellToAddress());
			//
			// System.out.println("vat procent " +
			// order.getVatProdPostingGroup());
			// System.out.println("shipping amount " +
			// order.getShippingAmount());
			//
			System.out.println("Calculated Grand Total " + order.getCalculatedGrandTotal());
			//
			// System.out.println("total ip " + order.getTotalIp());

			for (NavOrderLinesModel line : order.getLines()) {
				System.out.println("line.getNo() " + line.getNo());

			}

		}
	}
}

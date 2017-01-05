package com.steps.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Assert;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.tools.CustomVerification;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;

import net.thucydides.core.annotations.Steps;

public class ImportOrdersSteps  {

	@Steps
	CustomVerification customVerification;
	
	@Steps
	ImportOrdersToNavSteps importOrders;

	
	public void validateOrders(List<DBOrderModel> shopOrderList, List<NavOrderModel> navOrderList) throws Exception {

		Assert.assertTrue("Failure: The list size are not equal", shopOrderList.size() == navOrderList.size());
		for (DBOrderModel order : shopOrderList) {
			List<SalesOrderInfoModel> infoItemList = order.getItemInfo();
			NavOrderModel compare = importOrders.findOrder(order.getIncrementId(), navOrderList);
			String navShipping = null;
			if (compare.getIncrementId() != null) {

			} else {
				Assert.assertTrue("Failure: Could not validate all orders in the list", compare != null);
			}

			double shippingAmount = Double.parseDouble(order.getShippingAmount());

			if (compare.getContainsBom().contains("true")) {
				List<NavOrderLinesModel> navOrderItem = compare.getLines();
				for (NavOrderLinesModel item : navOrderItem) {

					if (item.getType().contains("_blank_") && item.isBomItem()) {

						List<String> billOfMaterial = NavisionBillOfMaterialCalls.getItemsList(item.getBomItemNo());
						for (String childItem : billOfMaterial) {
							SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
							infoItem.setSku(childItem);
							infoItemList.add(infoItem);
						}
					}
					navShipping = compare.getShippingAmount();
				}
			}

			if (shippingAmount >= 0 && navShipping != "0") {
				SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
				infoItem.setSku("VERS01");
				infoItemList.add(infoItem);
			}
			order.setItemInfo(infoItemList);
			
			

			importOrders.validateOrderIncrementId(order.getIncrementId(), compare.getIncrementId());
			importOrders.validateOrderItemsTest(order.getItemInfo(), compare.getLines());
			importOrders.validateExternalDocNo(order.getIncrementId(), compare.getExternalDocumentNo());

			importOrders.validateGrandTotal(order.getGrandTotal(), compare.getCalculatedGrandTotal());
			importOrders.validateOrderId(order.getOrderId(), compare.getYouRefercences());
			importOrders.validateOrderType(order.getOrderType(), compare.getShopOrderType());
			importOrders.validateCartType(order.getCartType(), compare.getShopCartType());
			importOrders.validateSalesPersonCode(order.getCustomerId(), compare.getSalesPersonCode());

			importOrders.validateShippingAmount(order.getShippingAmount(), compare.getShippingAmount());
			importOrders.validateSaleCustomerNo(order.getStylistCustomerId(), compare.getSellToCustomerNo());
			importOrders.validatePaymentMethod(order.getPaymentMethodTypet(), compare.getShopPaymentMethod());
			importOrders.validateWebsiteCode(order.getWebsiteCode(), compare.getShopWebsiteCode());
			importOrders.validateStoreLanguage(order.getStoreLanguage(), compare.getShopStoreLanguage());
			importOrders.validateCustomerName(order.getCustomerName(),compare.getSellToCustomerName());
			// on order

			importOrders.validateBillingToCustomerName(order.getBillToFirstName(), order.getBillToLastName(),
					compare.getBillToName());
			importOrders.validateBillingStreet(order.getBillToStreetAddress(), compare.getBillToAddress());
			importOrders.validateBillingCountryRegionCode(order.getBillCountryId(), compare.getBillToCountryRegionCode());
			importOrders.validateBillingCity(order.getBillToCity(), compare.getBillToCity());
			importOrders.validateBillingPostCode(order.getBillToPostcode(), compare.getBillToPostCode());
			importOrders.validateShippingToCustomerName(order.getShipToFirstName(), order.getShipToLastName(),
					compare.getShipToName());
			importOrders.validateShippingStreet(order.getShipToStreetAddress(), compare.getShipToAddress());
			importOrders.validateShippingCountryRegionCode(order.getShipCountryId(), compare.getShipToCountryRegionCode());
			importOrders.validateShippingPostCode(order.getShipToPostcode(), compare.getShipToPostCode());
			importOrders.validateShippingCity(order.getShipToCity(), compare.getShipToCity());

			String navTaxAmount=calculateTaxAmount(order.getTaxAmount(), order.getTaxPrecent(), compare.getCalculatedGrandTotal());
			importOrders.validateTaxAmount(order.getTaxAmount(), navTaxAmount);

			importOrders.validateIsPreshipped(order.getIsPreshipped(), compare.getIsAlreadyShipped());
			importOrders.validateIsPom(order.getIsPom(), compare.getShopIsPom());
			importOrders.validatePartyId(order.getStylePartyId(), compare.getPartyId());

			importOrders.validateTotalIp(order.getTotalIp(), compare.getTotalIp());

			customVerification.printErrors();

			// should be clarified before, because here we have different value
			// validateUpdatedNavDate(order.getUpdatedNav(),
			// compare.getPrepmtPmtDiscountDate());
			// validateKoboSingleArticle(order.getKoboSingleArticle(),compare.getKoboSingleArticle());
		}

	}

	
	private String calculateTaxAmount(String taxAmount,String vatPercent,String calculatedGrandTotal){
		double grandTotalDouble = Double.parseDouble(calculatedGrandTotal);
		
		double vatDouble = Double.parseDouble(vatPercent);

		BigDecimal gtValue = BigDecimal.valueOf(grandTotalDouble);
		BigDecimal vatValue = BigDecimal.valueOf(vatDouble);
		BigDecimal value = BigDecimal.valueOf(100);

		BigDecimal vat = vatValue.add(value).divide(value);
		BigDecimal navTaxAmount = gtValue.subtract(gtValue.divide(vat, 2, RoundingMode.CEILING));
		
		return navTaxAmount.toString();
		
	}


}

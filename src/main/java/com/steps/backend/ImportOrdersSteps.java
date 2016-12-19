package com.steps.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.tools.CustomVerification;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

import net.thucydides.core.annotations.Step;

public class ImportOrdersSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void validateOrders(List<DBOrderModel> shopOrderList, List<NavOrderModel> navOrderList) throws Exception {

		Assert.assertTrue("Failure: The list size are not equal", shopOrderList.size() == navOrderList.size());
		for (DBOrderModel order : shopOrderList) {
			List<SalesOrderInfoModel> infoItemList = order.getItemInfo();
			NavOrderModel compare = findOrder(order.getIncrementId(), navOrderList);
			String shipping = null;
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

					if (item.getType() == "Charge_Item") {
						shipping = compare.getShippingAmount();
					}

				}
			}

			/// should be changed couse shipping child is added in navision even
			/// if shipping in shop==0
			if (shippingAmount >= 0 && shipping != null) {
				SalesOrderInfoModel infoItem = new SalesOrderInfoModel();
				infoItem.setSku("VERS01");
				infoItemList.add(infoItem);
			}
			order.setItemInfo(infoItemList);

			validateOrderIncrementId(order.getIncrementId(), compare.getIncrementId());
			validateOrderItemsTest(order.getItemInfo(), compare.getLines());

			validateGrandTotal(order.getGrandTotal(), compare.getBaseGrandTotal());
			validateCalculatedGrandTotal(order.getGrandTotal(), compare.getCalculatedGrandTotal());

			validateExternalDocNo(order.getIncrementId(), compare.getExternalDocumentNo());
			validateCreatedAtDate(order.getCreatedAt(), compare.getOrderDate());
			validatePaidAtDate(order.getPaidAt(), compare.getPostingDate());
			validateMagentoGrandTotal(order.getGrandTotal(), compare.getMagentoGrandTotal());
			validateOrderId(order.getOrderId(), compare.getYouRefercences());
			validateOrderType(order.getOrderType(), compare.getShopOrderType());
			validateCartType(order.getCartType(), compare.getShopCartType());
			validateSalesPersonCode(order.getCustomerId(), compare.getSalesPersonCode());
			validateShippingAmount(order.getShippingAmount(), compare.getShippingAmount());
			validateSaleCustomerNo(order.getStylistCustomerId(), compare.getSellToCustomerNo());
			validatePaymentMethod(order.getPaymentMethodTypet(), compare.getShopPaymentMethod());
			validateWebsiteCode(order.getWebsiteCode(), compare.getShopWebsiteCode());
			validateStoreLanguage(order.getStoreLanguage(), compare.getShopStoreLanguage());
			validateCustomerName(order.getCustomerFirstName(), order.getCustomerLastName(),
					compare.getSellToCustomerName()); // on order
			validateBillingToCustomerName(order.getBillToFirstName(), order.getBillToLastName(),
					compare.getBillToName());
			validateBillingStreet(order.getBillToStreetAddress(), compare.getBillToAddress());
			validateBillingCountryRegionCode(order.getBillCountryId(), compare.getBillToCountryRegionCode());
			validateBillingCity(order.getBillToCity(), compare.getBillToCity());
			validateBillingPostCode(order.getBillToPostcode(), compare.getBillToPostCode());
			validateShippingToCustomerName(order.getShipToFirstName(), order.getShipToLastName(),
					compare.getShipToName());
			validateShippingStreet(order.getShipToStreetAddress(), compare.getShipToAddress());
			// validateShippingCountryRegionCode(order.getshi,
			// compare.getShipToCountryRegionCode()); <- order.getshipto ...
			validateShippingPostCode(order.getShipToPostcode(), compare.getShipToPostCode());
			validateTaxAmount(order.getTaxAmount(), compare.getVatProdPostingGroup(), compare.getBaseGrandTotal());
			validateUpdatedNavDate(order.getUpdatedNav(), compare.getPrepmtPmtDiscountDate());
			validateKoboSingleArticle(order.getKoboSingleArticle(), compare.getKoboSingleArticle());
			validateIsPreshipped(order.getIsPreshipped(), compare.getIsAlreadyShipped());
			validateIsPom(order.getIsPom(), compare.getShopIsPom());
			validatePartyId(order.getStylePartyId(),compare.getPartyId());
			validateTotalIp(order.getTotalIp(),compare.getTotalIp());
		}

	}
	
	@Step
	private void validateTotalIp(String updatedNav, String compare) {

		CustomVerification.verifyTrue("Failure: Updated Nav Date doesn't match: " + updatedNav + " - " + compare,
				updatedNav.contentEquals(compare));
	}
	
	@Step
	private void validatePartyId(String partyId, String compare) {
		String shopPartyId = partyId == "null" ? "0" : "true";
		CustomVerification.verifyTrue("Failure: Is Pom Product doesn't match: " + shopPartyId + " - " + compare,
				shopPartyId.contentEquals(compare));
	}

	@Step
	private void validateIsPom(String isPom, String compare) {
		String shopisPom = isPom == "1" ? "true" : "false";
		CustomVerification.verifyTrue("Failure: Is Pom Product doesn't match: " + shopisPom + " - " + compare,
				shopisPom.contentEquals(compare));
	}
	
	@Step
	private void validateIsPreshipped(String isPreshipped, String compare) {
		String shopIsPreshipped = isPreshipped == "1" ? "true" : "false";
		CustomVerification.verifyTrue("Failure: Is Preshipped doesn't match: " + shopIsPreshipped + " - " + compare,
				shopIsPreshipped.contentEquals(compare));
	}

	@Step
	private void validateKoboSingleArticle(String koboSingleArticle, String compare) {
		String shopIsKoboSingle = koboSingleArticle == "1" ? "true" : "false";
		CustomVerification.verifyTrue("Failure: koboSingleArticle doesn't match: " + shopIsKoboSingle + " - " + compare,
				shopIsKoboSingle.contentEquals(compare));
	}

	@Step
	private void validateUpdatedNavDate(String updatedNav, String compare) {

		CustomVerification.verifyTrue("Failure: Updated Nav Date doesn't match: " + updatedNav + " - " + compare,
				updatedNav.contentEquals(compare));
	}

	@Step
	private void validateTaxAmount(String taxAmount, String vatProc, String grandTotal) {

		double grandTotalDouble = Double.parseDouble(grandTotal);
		double vatDouble = Double.parseDouble(vatProc);

		BigDecimal gtValue = BigDecimal.valueOf(grandTotalDouble);
		BigDecimal vatValue = BigDecimal.valueOf(vatDouble);
		BigDecimal value = BigDecimal.valueOf(100);

		BigDecimal vat = vatValue.add(value).divide(value);
		BigDecimal navTaxAmount = gtValue.subtract(gtValue.divide(vat, 2, RoundingMode.CEILING));

		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + taxAmount + " - " + navTaxAmount,
				taxAmount.contentEquals(navTaxAmount.toString()));
	}

	@Step
	private void validateShippingPostCode(String shippingPostCode, String compare) {
		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + shippingPostCode + " - " + compare,
				shippingPostCode.contentEquals(compare));
	}

	@Step
	private void validateShippingCountryRegionCode(String shippingCountryRegionCode, String compare) {

		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + shippingCountryRegionCode + " - " + compare,
				shippingCountryRegionCode.contentEquals(compare));
	}

	@Step
	private void validateShippingCity(String shippingCity, String compare) {

		CustomVerification.verifyTrue("Failure: Shipping City doesn't match: " + shippingCity + " - " + compare,
				shippingCity.contentEquals(compare));
	}

	@Step
	private void validateShippingStreet(String shippingStreet, String compare) {
		CustomVerification.verifyTrue("Failure: shippingStreet doesn't match: " + shippingStreet + " - " + compare,
				shippingStreet.contentEquals(compare));
	}

	@Step
	private void validateShippingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname + " " + lastName;
		CustomVerification.verifyTrue("Failure: Shipping to Name doesn't match: " + name + " - " + compare,
				name.contentEquals(compare));
	}

	@Step
	private void validateBillingPostCode(String billingPostCode, String compare) {
		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + billingPostCode + " - " + compare,
				billingPostCode.contentEquals(compare));
	}

	@Step
	private void validateBillingCity(String billingCity, String compare) {

		CustomVerification.verifyTrue("Failure: billingCity doesn't match: " + billingCity + " - " + compare,
				billingCity.contentEquals(compare));
	}

	@Step
	private void validateBillingCountryRegionCode(String billingCountryRegionCode, String compare) {

		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + billingCountryRegionCode + " - " + compare,
				billingCountryRegionCode.contentEquals(compare));
	}

	@Step
	private void validateBillingStreet(String billingStreet, String compare) {
		CustomVerification.verifyTrue("Failure: BillingStreet doesn't match: " + billingStreet + " - " + compare,
				billingStreet.contentEquals(compare));
	}

	//
	@Step
	private void validateBillingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname + " " + lastName;
		CustomVerification.verifyTrue("Failure: Billing to Name doesn't match: " + name + " - " + compare,
				name.contentEquals(compare));
	}

	@Step
	private void validateCustomerName(String firstname, String lastName, String compare) {

		String name = firstname + " " + lastName;
		CustomVerification.verifyTrue("Failure: Customer Name doesn't match: " + name + " - " + compare,
				name.contentEquals(compare));
	}

	@Step
	private void validateStoreLanguage(String storeLanguage, String compare) {
		String comapreStoreLanguage = compare.toLowerCase();
		CustomVerification.verifyTrue(
				"Failure: websiteCode doesn't match: " + storeLanguage + " - " + comapreStoreLanguage,
				storeLanguage.contentEquals(comapreStoreLanguage));
	}

	@Step
	private void validateWebsiteCode(String websiteCode, String compare) {
		String comapreWebsiteCode = compare.toLowerCase();
		CustomVerification.verifyTrue("Failure: websiteCode doesn't match: " + websiteCode + " - " + comapreWebsiteCode,
				websiteCode.contentEquals(comapreWebsiteCode));
	}

	@Step
	private void validatePaymentMethod(String paymentMethod, String compare) {
		String comparePaymentMethod = compare.toLowerCase();
		CustomVerification.verifyTrue(
				"Failure: PaymentMethod doesn't match: " + paymentMethod + " - " + comparePaymentMethod,
				paymentMethod.contentEquals(comparePaymentMethod));
	}

	@Step
	private void validateSaleCustomerNo(String saleCustomerNo, String compare) {
		CustomVerification.verifyTrue("Failure: SaleCustomerNo doesn't match: " + saleCustomerNo + " - " + compare,
				saleCustomerNo.contentEquals(compare));
	}

	@Step
	private void validateShippingAmount(String shippingAmount, String compare) {
		CustomVerification.verifyTrue("Failure: ShippingAmount doesn't match: " + shippingAmount + " - " + compare,
				shippingAmount.contentEquals(compare));
	}

	//
	@Step
	private void validateSalesPersonCode(String customerID, String compare) {
		CustomVerification.verifyTrue("Failure: SalesPersonCode doesn't match: " + customerID + " - " + compare,
				customerID.contentEquals(compare));
	}

	@Step
	private void validateCartType(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: OrderType doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}

	@Step
	private void validateOrderType(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: OrderType doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}

	@Step
	private void validateOrderId(String orderId, String compare) {
		CustomVerification.verifyTrue("Failure: PaidAt doesn't match: " + orderId + " - " + compare,
				orderId.contentEquals(compare));
	}

	@Step
	private void validatePaidAtDate(String paidAt, String compare) {
		CustomVerification.verifyTrue("Failure: PaidAt doesn't match: " + paidAt + " - " + compare,
				paidAt.contentEquals(compare));
	}

	@Step
	private void validateCreatedAtDate(String createdAt, String compare) {
		CustomVerification.verifyTrue("Failure: createdAt doesn't match: " + createdAt + " - " + compare,
				createdAt.contentEquals(compare));
	}

	@Step
	private void validateExternalDocNo(String externalDocNo, String compare) {
		CustomVerification.verifyTrue("Failure: ExternalDocNo doesn't match: " + externalDocNo + " - " + compare,
				externalDocNo.contentEquals(compare));
	}

	@Step
	private NavOrderModel findOrder(String incremenr_id, List<NavOrderModel> grabbedList) {
		NavOrderModel result = new NavOrderModel();
		theFor: for (NavOrderModel item : grabbedList) {
			if (item.getIncrementId().contains(incremenr_id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	@Step
	private void validateMagentoGrandTotal(String magGrandTotal, String compare) {
		CustomVerification.verifyTrue("Failure: GrandTotal  doesn't match: ", magGrandTotal.contains(compare));

	}

	@Step
	private void validateOrderIncrementId(String incrementId, String compare) {
		CustomVerification.verifyTrue("Failure: IncrementId doesn't match: " + incrementId + " - " + compare,
				incrementId.contentEquals(compare));
	}

	@Step
	private void validateCalculatedGrandTotal(String magGrandToal, String compare) {

		double gt = Double.parseDouble(magGrandToal);
		double roundGt = Math.round(gt * 100.0) / 100.0;
		String finalMagGrandToal = String.valueOf(roundGt);

		CustomVerification.verifyTrue("Failure: Grand Total doesn't match: " + magGrandToal + " - " + compare,
				finalMagGrandToal.contentEquals(compare));
	}

	@Step
	private void validateGrandTotal(String magGrandToal, String compare) {

		double gt = Double.parseDouble(magGrandToal);
		double roundGt = Math.round(gt * 100.0) / 100.0;
		String finalMagGrandToal = String.valueOf(roundGt);

		CustomVerification.verifyTrue("Failure: Grand Total doesn't match: " + magGrandToal + " - " + compare,
				finalMagGrandToal.contentEquals(compare));
	}

	@Step
	private void validateOrderItemsTest(List<SalesOrderInfoModel> shopLines, List<NavOrderLinesModel> navLines) {
		for (SalesOrderInfoModel SalesOrderInfoModel : shopLines) {
			System.out.println("shop item " + SalesOrderInfoModel.getSku());
		}
		System.out.println("  ");
		for (NavOrderLinesModel SalesOrderInfoModel : navLines) {
			System.out.println("nav item " + SalesOrderInfoModel.getNo());
		}
		for (NavOrderLinesModel shopLine : navLines) {
			validateItemTest(shopLine.getNo(), shopLines);
		}
	}

	@Step
	private void validateItemTest(String no, List<SalesOrderInfoModel> shopLines) {
		boolean found = false;
		for (SalesOrderInfoModel item : shopLines) {
			if (item.getSku().contains(no)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(no + " hasn't been found", found);
	}

}

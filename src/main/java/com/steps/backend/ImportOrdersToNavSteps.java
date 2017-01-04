package com.steps.backend;

import java.util.List;

import com.tools.CustomVerification;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class ImportOrdersToNavSteps extends AbstractSteps {

	public static final long serialVersionUID = 3609006291221433240L;
	
	public void validateTotalIp(String totalIp, String compare) {

		CustomVerification.verifyTrue("Failure: Total IP doesn't match: " + totalIp + " - " + compare,
				compare.contains(totalIp));
	}

	
	public void validatePartyId(String partyId, String compare) {
		
		CustomVerification.verifyTrue("Failure: Is Pom Product doesn't match: " + partyId + " - " + compare,
				partyId.contentEquals(compare));
	}

	
	public void validateIsPom(String isPom, String compare) {
		String shopisPom = isPom == "1" ? "true" : "false";
		CustomVerification.verifyTrue("Failure: Is Pom Product doesn't match: " + shopisPom + " - " + compare,
				shopisPom.contentEquals(compare));
	}

	
	public void validateIsPreshipped(String isPreshipped, String compare) {
		String shopIsPreshipped = isPreshipped == "1" ? "true" : "false";
		CustomVerification.verifyTrue("Failure: Is Preshipped doesn't match: " + shopIsPreshipped + " - " + compare,
				shopIsPreshipped.contentEquals(compare));
	}

	// public void validateKoboSingleArticle(String koboSingleArticle, String
	// compare) {
	// String shopIsKoboSingle = koboSingleArticle == "1" ? "true" : "false";
	// CustomVerification.verifyTrue("Failure: koboSingleArticle doesn't match:
	// " + shopIsKoboSingle + " - " + compare,
	// shopIsKoboSingle.contentEquals(compare));
	// }
	//
	// public void validateUpdatedNavDate(String updatedNav, String compare) {
	//
	// Assert.assertTrue("Failure: Updated Nav Date doesn't match: " +
	// updatedNav + " - " + compare,
	// updatedNav.contentEquals(compare));
	// }

	
	public void validateTaxAmount(String taxAmount,  String navTaxAmount) {


		CustomVerification.verifyTrue(
				"Failure: Tax Amount doesn't match: " +"Shop -> "+ taxAmount + " - Nav -> "+ navTaxAmount,
				taxAmount.contains(navTaxAmount.toString()));

	}

	
	public void validateShippingPostCode(String shippingPostCode, String compare) {
		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + shippingPostCode + " - " + compare,
				shippingPostCode.contentEquals(compare));
	}

	
	public void validateShippingCountryRegionCode(String shippingCountryRegionCode, String compare) {

		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + shippingCountryRegionCode + " - " + compare,
				shippingCountryRegionCode.contentEquals(compare));
	}

	
	public void validateShippingCity(String shippingCity, String compare) {

		CustomVerification.verifyTrue("Failure: Shipping City doesn't match: " + shippingCity + " - " + compare,
				shippingCity.contentEquals(compare));
	}

	
	public void validateShippingStreet(String shippingStreet, String compare) {
		CustomVerification.verifyTrue("Failure: shippingStreet doesn't match: " + shippingStreet + " - " + compare,
				shippingStreet.contentEquals(compare));
	}

	
	public void validateShippingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname + " " + lastName;
		CustomVerification.verifyTrue("Failure: Shipping to Name doesn't match: " + name + " - " + compare,
				name.contains(compare));
	}

	
	public void validateBillingPostCode(String billingPostCode, String compare) {
		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + billingPostCode + " - " + compare,
				billingPostCode.contentEquals(compare));
	}

	
	public void validateBillingCity(String billingCity, String compare) {

		CustomVerification.verifyTrue("Failure: billingCity doesn't match: " + billingCity + " - " + compare,
				billingCity.contentEquals(compare));
	}

	
	public void validateBillingCountryRegionCode(String billingCountryRegionCode, String compare) {

		CustomVerification.verifyTrue(
				"Failure: billingCountryRegionCode doesn't match: " + billingCountryRegionCode + " - " + compare,
				billingCountryRegionCode.contentEquals(compare));
	}

	
	public void validateBillingStreet(String billingStreet, String compare) {
		CustomVerification.verifyTrue("Failure: BillingStreet doesn't match: " + billingStreet + " - " + compare,
				billingStreet.contentEquals(compare));
	}

	
	public void validateBillingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname + " " + lastName;
	

		CustomVerification.verifyTrue("Failure: Billing to Name(last name) doesn't match:" + name + " - " + compare,
				name.contains(compare));

	}

	
	public void validateCustomerName(String customerName,String compare) {

	
		CustomVerification.verifyTrue("Failure: Customer Name doesn't match: " + customerName + " - " + compare,
				compare.contains(customerName));
	}

	
	public void validateStoreLanguage(String storeLanguage, String compare) {
		String comapreStoreLanguage = compare.toLowerCase();
		CustomVerification.verifyTrue(
				"Failure: websiteCode doesn't match: " + storeLanguage + " - " + comapreStoreLanguage,
				storeLanguage.contentEquals(comapreStoreLanguage));
	}


	public void validateWebsiteCode(String websiteCode, String compare) {
		String comapreWebsiteCode = compare.toLowerCase();
		CustomVerification.verifyTrue("Failure: websiteCode doesn't match: " + websiteCode + " - " + comapreWebsiteCode,
				websiteCode.contentEquals(comapreWebsiteCode));
	}

	
	public void validatePaymentMethod(String paymentMethod, String compare) {
		String comparePaymentMethod = compare.toLowerCase();
		String shopPaymentMethod=paymentMethod.toLowerCase();
		CustomVerification.verifyTrue(
				"Failure: PaymentMethod doesn't match: " + shopPaymentMethod + " - " + comparePaymentMethod,
				shopPaymentMethod.contentEquals(comparePaymentMethod));
	}

	
	public void validateSaleCustomerNo(String saleCustomerNo, String compare) {
		CustomVerification.verifyTrue("Failure: SaleCustomerNo doesn't match: " + saleCustomerNo + " - " + compare,
				saleCustomerNo.contentEquals(compare));
	}

	
	public void validateShippingAmount(String shippingAmount, String compare) {
		CustomVerification.verifyTrue("Failure: ShippingAmount doesn't match: " + shippingAmount + " - " + compare,
				shippingAmount.contains(compare));
	}

	
	public void validateSalesPersonCode(String customerID, String compare) {
		CustomVerification.verifyTrue("Failure: SalesPersonCode doesn't match: " + customerID + " - " + compare,
				customerID.contentEquals(compare));
	}

	
	public void validateCartType(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: OrderType doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}

	
	public void validateOrderType(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: OrderType doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}

	
	public void validateOrderId(String orderId, String compare) {
		CustomVerification.verifyTrue("Failure: OrderId doesn't match: " + orderId + " - " + compare,
				orderId.contentEquals(compare));
	}

	// public void validatePaidAtDate(String paidAt, String compare) {
	// Assert.assertTrue("Failure: PaidAt doesn't match: " + paidAt + " - " +
	// compare,
	// paidAt.contentEquals(compare));
	// }
	//
	// public void validateCreatedAtDate(String createdAt, String compare) {
	// Assert.assertTrue("Failure: createdAt doesn't match: " + createdAt + " -
	// " + compare,
	// createdAt.contentEquals(compare));
	// }


	public void validateExternalDocNo(String externalDocNo, String compare) {
		CustomVerification.verifyTrue("Failure: ExternalDocNo doesn't match: " + externalDocNo + " - " + compare,
				externalDocNo.contentEquals(compare));
	}


	@Step
	public NavOrderModel findOrder(String incremenr_id, List<NavOrderModel> grabbedList) {
		NavOrderModel result = new NavOrderModel();
		theFor: for (NavOrderModel item : grabbedList) {
			if (item.getIncrementId().contains(incremenr_id)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	// public void validateMagentoGrandTotal(String magGrandTotal, String
	// compare) {
	// Assert.assertTrue("Failure: GrandTotal doesn't match: ",
	// magGrandTotal.contains(compare));
	//
	// }

	
	public void validateOrderIncrementId(String incrementId, String compare) {
		CustomVerification.verifyTrue("Failure: IncrementId doesn't match: " + incrementId + " - " + compare,
				incrementId.contentEquals(compare));
	}


	
	public void validateGrandTotal(String magGrandToal, String compare) {

		double gt = Double.parseDouble(magGrandToal);
		double roundGt = Math.round(gt * 100.0) / 100.0;
		String finalMagGrandToal = String.valueOf(roundGt);


		CustomVerification.verifyTrue("Failure: Grand Total doesn't match: " + finalMagGrandToal + " - " + compare,
				finalMagGrandToal.contentEquals(compare));
	}

	@Title("Validate Order Items List")
	public void validateOrderItemsTest(List<SalesOrderInfoModel> shopLines, List<NavOrderLinesModel> navLines) {
		for (NavOrderLinesModel shopLine : navLines) {
			validateItemTest(shopLine.getNo(), shopLines);
		}
	}

	
	public void validateItemTest(String no, List<SalesOrderInfoModel> shopLines) {
		boolean found = false;
		for (SalesOrderInfoModel item : shopLines) {
			if (item.getSku().contains(no)) {
				found = true;
				break;
			}
		}
		CustomVerification.verifyTrue(no + " hasn't been found", found);
	}

}

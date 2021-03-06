package com.steps.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tools.CustomVerification;
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class ImportOrdersToNavSteps extends AbstractSteps {

	public static final long serialVersionUID = 3609006291221433240L;
	public static String orderid = "1212323";

	// @Step
	public void validateTotalIp(String totalIp, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: Total IP doesn't match: " + totalIp + " - " + compare,
				compare.contains(totalIp));

	}

	// @Step
	public void validatePartyId(String partyId, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: Party Id  doesn't match: " + partyId + " - " + compare,
				partyId.contentEquals(compare));

	}

	// @Step
	public void validateIsPom(String isPom, String compare) {
		
		String shopisPom = isPom.contentEquals("1") ? "true" : "false";
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Is Pom Product doesn't match: " + "shop-> " + shopisPom + " - " + "nav->" + compare,
				shopisPom.contentEquals(compare));

	}

	// @Step
	public void validateIsPreshipped(String isPreshipped, String compare) {

		String shopIsPreshipped = isPreshipped.contentEquals("1") ? "true" : "false";
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Is Preshipped doesn't match: " + shopIsPreshipped + " - " + compare,
				shopIsPreshipped.contentEquals(compare));

	}
	
	// @Step
	public void validateTaxAmount(String taxAmount, String navTaxAmount) {
		double finalMagTax = FormatterUtils.roundDouble(FormatterUtils.parseValueToDouble(taxAmount));
		double finalNavTax = FormatterUtils.roundDouble(Double.parseDouble(navTaxAmount));

		double diff = Math.abs(finalMagTax - finalNavTax);
		double finalDiff = FormatterUtils.roundDouble(diff);
		if (finalDiff > 0.01) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: Tax Amount doesn't match: " + "Shop -> " + taxAmount + " - Nav -> " + navTaxAmount,
					taxAmount.contains(navTaxAmount.toString()));
		}
		

	}

	// @Step
	public void validateShippingPostCode(String shippingPostCode, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: billingCountryRegionCode doesn't match: " + shippingPostCode + " - " + compare,
				shippingPostCode.contentEquals(compare));

	}

	// @Step
	public void validateShippingCountryRegionCode(String shippingCountryRegionCode, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: shippingCountryRegionCode doesn't match: shop->"
				+ shippingCountryRegionCode + " - nav->" + compare, shippingCountryRegionCode.contentEquals(compare));

	}

	// @Step
	public void validateShippingCity(String shippingCity, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Shipping City doesn't match: shop-> " + shippingCity + " - nav-> " + compare,
				shippingCity.contentEquals(compare));

	}

	// @Step
	public void validateShippingStreet(String shippingStreet, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Shipping Street doesn't match: shop->" + shippingStreet.replaceAll("\\s+", "") + " - nav->"
						+ compare.replaceAll("\\s+", ""),
				shippingStreet.replaceAll("\\s+", "").contentEquals(compare.replaceAll("\\s+", "")));

	}

	// @Step
	public void validateShippingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname.replaceAll("\\s+", "") + " " + lastName.replaceAll("\\s+", "");
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Shipping to Name doesn't match: " + name + " - " + compare,
				name.replaceAll("\\s+", "").contains(compare.replaceAll("\\s+", "")));

	}

	// @Step
	public void validateBillingPostCode(String billingPostCode, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: billingCountryRegionCode doesn't match: shop-> " + billingPostCode + " - nav->" + compare,
				billingPostCode.contentEquals(compare));

	}

	// @Step
	public void validateBillingCity(String billingCity, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: billingCity doesn't match: shop-> " + billingCity + " - nav->" + compare,
				billingCity.contentEquals(compare));

	}

	// @Step
	public void validateBillingCountryRegionCode(String billingCountryRegionCode, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: billingCountryRegionCode doesn't match: shop-> "
				+ billingCountryRegionCode + " - nav->" + compare, billingCountryRegionCode.contentEquals(compare));

	}

	// @Step
	public void validateBillingStreet(String billingStreet, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: BillingStreet doesn't match: shop-> " + billingStreet.replaceAll("\\s+", "") + " - nav-> "
						+ compare.replaceAll("\\s+", ""),
				billingStreet.replaceAll("\\s+", "").contentEquals(compare.replaceAll("\\s+", "")));

	}

	// @Step
	public void validateBillingToCustomerName(String firstname, String lastName, String compare) {

		String name = firstname.replaceAll("\\s+", "") + " " + lastName.replaceAll("\\s+", "");

		CustomVerification.verifyTrueForOrderImport("Failure: Billing to Name doesn't match:" + name + " - " + compare,
				name.replaceAll("\\s+", "").contains(compare.replaceAll("\\s+", "")));

	}

	// @Step
	public void validateCustomerName(String firstname, String lastName, String compare) {
		String name = firstname.replaceAll("\\s+", "") + " " + lastName.replaceAll("\\s+", "");

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Customer Name doesn't match: " + "shop:" + firstname + " " + lastName + " - nav: " + compare,
				name.replaceAll("\\s+", "").contains(compare.replaceAll("\\s+", "")));

	}

	// @Step
	public void validateStoreLanguage(String storeLanguage, String compare) {

		String comapreStoreLanguage = compare.toLowerCase();
		CustomVerification.verifyTrueForOrderImport(
				"Failure: StoreLanguage doesn't match: " + storeLanguage + " - " + comapreStoreLanguage,
				storeLanguage.contentEquals(comapreStoreLanguage));

	}

	// @Step
	public void validateWebsiteCode(String websiteCode, String compare) {

		String comapreWebsiteCode = compare.toLowerCase();
		CustomVerification.verifyTrueForOrderImport(
				"Failure: websiteCode doesn't match: " + websiteCode + " - " + comapreWebsiteCode,
				websiteCode.contentEquals(comapreWebsiteCode));

	}

	// @Step
	public void validatePaymentMethod(String paymentMethod, String compare) {

		// replaceAll("\\s+","") -> replace white spaces
		String comparePaymentMethod = compare.toLowerCase().replaceAll("\\s+", "");
		String shopPaymentMethod = paymentMethod.toLowerCase().replaceAll("\\s+", "");
		CustomVerification.verifyTrueForOrderImport(
				"Failure: PaymentMethod doesn't match: " + shopPaymentMethod + " - " + comparePaymentMethod,
				shopPaymentMethod.contentEquals(comparePaymentMethod));

	}

	// @Step
	public void validateSaleCustomerNo(String saleCustomerNo, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: SaleCustomerNo doesn't match: in shop->" + saleCustomerNo + " - in nav->" + compare,
				saleCustomerNo.contentEquals(compare));

	}

	// @Step
	public void contactValidateSaleCustomerNo(String saleCustomerNo, String compare) {

		if (saleCustomerNo.contentEquals("null") && !compare.contains("null")) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: SaleCustomerNo doesn't match: in shop->" + saleCustomerNo + " - in nav->" + compare,
					saleCustomerNo.contentEquals("null") && !compare.contains("null"));
		} else {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: SaleCustomerNo doesn't match: in shop->" + saleCustomerNo + " - in nav->" + compare,
					saleCustomerNo.contentEquals(compare));
		}

	}

	// @Step
	public void validateShippingAmount(String shippingAmount, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: ShippingAmount doesn't match: " + shippingAmount + " - " + compare,
				shippingAmount.contains(compare));

	}

	// @Step
	public void validateSalesPersonCode(String customerID, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: SalesPersonCode doesn't match: " + customerID + " - " + compare,
				customerID.contentEquals(compare));

	}

	// @Step
	public void validateCartType(String cartType, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: Cart Type doesn't match: " + cartType + " - " + compare,
				cartType.contentEquals(compare));

	}

	// @Step
	public void validateOrderType(String orderType, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: OrderType doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));

	}

	// @Step
	public void validateOrderId(String orderId, String compare) {

		CustomVerification.verifyTrueForOrderImport("Failure: OrderId doesn't match: " + orderId + " - " + compare,
				orderId.contentEquals(compare));

	}

	// @Step
	public void validateExternalDocNo(String externalDocNo, String compare) {

		CustomVerification.verifyTrueForOrderImport(
				"Failure: ExternalDocNo doesn't match: " + externalDocNo + " - " + compare,
				externalDocNo.contentEquals(compare));

	}

	// @Step
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

	

	@Step
	public void validateOrderIncrementId(String incrementId, String compare) {
		boolean isNullValue = false;
		if (incrementId != null && compare != null) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: IncrementId doesn't match: " + incrementId + " - " + compare,
					incrementId.contentEquals(compare));
		} else {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: IncrementId (contains null values) " + incrementId + " - " + compare, isNullValue);

		}

	}

	// @Step
	public void validateGrandTotal(String magGrandTotal, String compare) {
		BigDecimal finalMagGrandTotal = BigDecimal.valueOf(Double.parseDouble(magGrandTotal)).setScale(2,
				RoundingMode.HALF_UP);
		BigDecimal finalNavGrandTotal = BigDecimal.valueOf(Double.parseDouble(compare)).setScale(2,
				RoundingMode.HALF_UP);
		
		BigDecimal difference = finalMagGrandTotal.subtract(finalNavGrandTotal).abs();

		BigDecimal value = BigDecimal.valueOf(0.00);

		if (difference.compareTo(value) == 1) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: GT_diff_not_adjusted =[" + difference + "] Grand Total doesn't match: " + "shop -> "
							+ finalMagGrandTotal + " - nav -> " + finalNavGrandTotal,
					finalMagGrandTotal.toString().contentEquals(finalNavGrandTotal.toString()));
		} else {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: Grand Total doesn't match: " + "shop->" + finalMagGrandTotal + " - nav->"
							+ finalMagGrandTotal,
					finalMagGrandTotal.toString().contentEquals(finalMagGrandTotal.toString()));
		}

	}

	// @Step
	@Title("Validate Order Items List")
	public void validateOrderItemsTest(List<SalesOrderInfoModel> shopLines, List<NavOrderLinesModel> navLines) {

		if (shopLines.size() == navLines.size()) {
			for (NavOrderLinesModel shopLine : navLines) {
				// System.out.println("shop item"+shopLine);
				validateItemTest(shopLine.getNo(), shopLines);
			}
		} else {
			CustomVerification.verifyTrueForOrderImport("Failure: The item lists are not equal",
					shopLines.size() == navLines.size());
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
		CustomVerification.verifyTrueForOrderImport(no + " hasn't been found", found);
	}

	// @Step
	public void validateKoboSingleArticle(String koboSingleArticleShop, String koboSingleArticleNav) {
		// TODO Auto-generated method stub

		String koboArticle = koboSingleArticleShop.contentEquals("1") ? "true" : "false";

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Kobo Article  doesn't match: " + koboArticle + " - " + koboSingleArticleNav,
				koboArticle.contentEquals(koboSingleArticleNav));

	}

	 @Step
	public void validateUpdatedNavDate(String updatedNav, String compare, String dateFormat) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(" updated  nav test " + updatedNav);

		DateFormat format = new SimpleDateFormat(dateFormat);
		boolean updatedNavIsNull = updatedNav.contentEquals("null") ? true : false;
		System.out.println("updatedNavIsNull " + updatedNavIsNull);

		Date dateTocompare = format.parse(compare);
		if (updatedNavIsNull) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: Updated Nav in shop contains : " + updatedNav + " value ", false);
		} else {
			Date dateUpdatedNav = format.parse(updatedNav);
			CustomVerification.verifyTrueForOrderImport(
					"Failure: Updated Nav does not contains correct date : " + dateUpdatedNav,

					// updatedNav is equal or is grater than dateToCompare
					dateUpdatedNav.compareTo(dateTocompare) >= 0);

		}

		/*
		 * boolean isUpdatedByNav = !updatedNav.isEmpty();
		 * CustomVerification.verifyTrueForOrderImport(
		 * "Failure: The order is not Updated by Nav " + updatedNav,
		 * isUpdatedByNav);
		 * 
		 */
	}

	// @Step
	public void validateMagentoCalculatedGrandTotal(String grandTotal, String calculatedGrandTotal) {
		// TODO Auto-generated method stub
	
		double finalGrandToal = FormatterUtils.roundDouble(Double.parseDouble(grandTotal));
		double finalMagGrandToal = FormatterUtils.roundDouble(Double.parseDouble(calculatedGrandTotal));
		
		double diff = Math.abs(finalGrandToal - finalMagGrandToal);
		double finalDiff =FormatterUtils.roundDouble(diff);
		
		if (finalDiff > 0.01) {
			CustomVerification.verifyTrueForOrderImport(
					"Failure: Different GrandTotal in SHOP: " + "grabbed->" + finalGrandToal + " - calculated "
							+ finalMagGrandToal,
					String.valueOf(finalMagGrandToal).contentEquals(String.valueOf(finalGrandToal)));
		}

	}

	public void validateMagentoCalculatedTaxAmount(String taxAmount, String calculatedTaxAmount) {
	
		double finalTaxAmount = FormatterUtils.roundDouble(Double.parseDouble(taxAmount));
		double finalCalcMagTaxAmount = FormatterUtils.roundDouble(Double.parseDouble(calculatedTaxAmount));

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Different TaxAmount in SHOP: " + "grabbed-> " + finalTaxAmount + " - calculated "
						+ finalCalcMagTaxAmount,
				String.valueOf(finalTaxAmount).contentEquals(String.valueOf(finalCalcMagTaxAmount)));

	}

	public void validateOrderCustomerEmail(String orderCustomerEmail, String compare) {
		// TODO Auto-generated method stub
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Order Customer Email doesn't match: " + orderCustomerEmail + " - " + compare,
				orderCustomerEmail.contentEquals(compare));
	}

	public void validateShippingHouseNumber(String shipToHouseNumber, String compare) {
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Ship to house number doesn't match: " + shipToHouseNumber + " - " + compare,
				shipToHouseNumber.contentEquals(compare));

	}

	public void validateVatNumber(String vatNumber, String vatNumberNav) {
		String vatNumberShop = vatNumber.contentEquals("1") ? "true" : "false";

		CustomVerification.verifyTrueForOrderImport(
				"Failure: Vat Number value doesn't match: " + vatNumberShop + " - " + vatNumberNav,
				vatNumberShop.contentEquals(vatNumberNav));

	}

	public void validateSmallBussinessMan(String smallBusinessMan, String smallBusinessManNav) {
		String smallBusinessManShop = smallBusinessMan.contentEquals("1") ? "true" : "false";

		CustomVerification.verifyTrueForOrderImport("Failure: Small Business Man value doesn't match: "
				+ smallBusinessManShop + " - " + smallBusinessManNav,
				smallBusinessManShop.contentEquals(smallBusinessManNav));

	}

	public void validateBanckAccountNumber(String banckAccountNumber, String banckAccountNumberNav) {
		CustomVerification.verifyTrueForOrderImport("Failure: Banck Account Number Nav value doesn't match: "
				+ banckAccountNumber + " - " + banckAccountNumberNav,
				banckAccountNumber.contentEquals(banckAccountNumberNav));
	}

	public void validateLanguageCode(String languageCode, String languageCodeNav) {
		CustomVerification.verifyTrueForOrderImport(
				"Failure: Banck Account Number Nav value doesn't match: " + languageCode + " - " + languageCodeNav,
				languageCode.contentEquals(languageCodeNav));

	}

}

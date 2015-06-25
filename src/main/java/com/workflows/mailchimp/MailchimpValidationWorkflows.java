package com.workflows.mailchimp;

import net.thucydides.core.annotations.Step;

import com.tools.CustomVerification;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.persistance.MongoReader;

public class MailchimpValidationWorkflows {
	
	@Step
	public void validateMailchimpProperties1(SubscriberModel grabbedModel, SubscriberModel expectedModel) {
		verifyEmail(grabbedModel.getEmail(),expectedModel.getEmail());
		verifyActivatedAt(grabbedModel.getActivatedAt(),expectedModel.getActivatedAt());
		verifyFirstName(grabbedModel.getFirstName(), expectedModel.getFirstName());
		verifyLastName(grabbedModel.getLastName(), expectedModel.getLastName());
		verifyIsStylist(grabbedModel.getIsStylist(),expectedModel.getIsStylist());
		verifyLastDatePurchase(grabbedModel.getLastPurchase(), expectedModel.getLastPurchase());
		verifyCountry(grabbedModel.getCountry(),expectedModel.getCountry());
		verifyKoboUsed(grabbedModel.getKoboUsed(),expectedModel.getKoboUsed());
		verifyKoboCode(grabbedModel.getKoboCode(),expectedModel.getKoboCode());
		verifyPreferredWebsite(grabbedModel.getPreferredWebsite(),expectedModel.getPreferredWebsite());
		verifySCsponsorName(grabbedModel.getSCsponsorName(),expectedModel.getSCsponsorName());
		verifySCsponsorEmail(grabbedModel.getSCsponsorEmail(),expectedModel.getSCsponsorEmail());
		verifySCsponsorPhone(grabbedModel.getSCsponsorPhone(),expectedModel.getSCsponsorPhone());
		verifySCsponsorShop(grabbedModel.getSCsponsorShop(),expectedModel.getSCsponsorShop());
		verifyProductSKU(grabbedModel.getProductSku(),expectedModel.getProductSku());
		verifyProductName(grabbedModel.getProductName(),expectedModel.getProductName());
		verifyRevenue3Months(grabbedModel.getRevenue3Months(), expectedModel.getRevenue3Months());
		verifyRevenue6Months(grabbedModel.getRevenu6Months(),expectedModel.getRevenu6Months());
		verifyRevenue1Year(grabbedModel.getRevenue1Year(),expectedModel.getRevenue1Year());
		verifyContactOrUser(grabbedModel.getContactOrUser(),expectedModel.getContactOrUser());
		verifyCustomerKobo(grabbedModel.getCustKobo(),expectedModel.getCustKobo());
		verifyStylistFlag(grabbedModel.getFlagStylist(),expectedModel.getFlagStylist());
		verifyHostFlag(grabbedModel.getFlagHost(),expectedModel.getFlagHost());
	}
	@Step
	public void validateNewContactSubscriberMailchimpProperties(SubscriberModel grabbedModel, SubscriberModel expectedModel) {
		verifyEmail(grabbedModel.getEmail(),expectedModel.getEmail());
//		verifyActivatedAt(grabbedModel.getActivatedAt(),expectedModel.getActivatedAt());
//		verifyIsStylist(grabbedModel.getIsStylist(),expectedModel.getIsStylist());
//		verifyLastDatePurchase(grabbedModel.getLastPurchase(), expectedModel.getLastPurchase());
//		verifyPreferredWebsite(grabbedModel.getPreferredWebsite(),expectedModel.getPreferredWebsite());
//		verifyContactOrUser(grabbedModel.getContactOrUser(),expectedModel.getContactOrUser());
//		verifyStylistFlag(grabbedModel.getFlagStylist(),expectedModel.getFlagStylist());
//		verifyHostFlag(grabbedModel.getFlagHost(),expectedModel.getFlagHost());
	}
	@Step
	public void validateNewCustomerOrderWithKoboMailchimpProperties(SubscriberModel grabbedModel, SubscriberModel expectedModel) {
		verifyEmail(grabbedModel.getEmail(),expectedModel.getEmail());
//		verifyActivatedAt(grabbedModel.getActivatedAt(),expectedModel.getActivatedAt());
//		verifyIsStylist(grabbedModel.getIsStylist(),expectedModel.getIsStylist());
//		verifyLastDatePurchase(grabbedModel.getLastPurchase(), expectedModel.getLastPurchase());
//		verifyPreferredWebsite(grabbedModel.getPreferredWebsite(),expectedModel.getPreferredWebsite());
//		verifyContactOrUser(grabbedModel.getContactOrUser(),expectedModel.getContactOrUser());
//		verifyStylistFlag(grabbedModel.getFlagStylist(),expectedModel.getFlagStylist());
//		verifyHostFlag(grabbedModel.getFlagHost(),expectedModel.getFlagHost());
//		verifyCustomerKobo(grabbedModel.getCustKobo(),expectedModel.getCustKobo());
//		verifyFirstName(grabbedModel.getFirstName(), expectedModel.getFirstName());
//		verifyLastName(grabbedModel.getLastName(), expectedModel.getLastName());
//		verifyCountry(grabbedModel.getCountry(),expectedModel.getCountry());
//		verifyRevenue3Months(grabbedModel.getRevenue3Months(), expectedModel.getRevenue3Months());
//		verifyRevenue6Months(grabbedModel.getRevenu6Months(),expectedModel.getRevenu6Months());
//		verifyRevenue1Year(grabbedModel.getRevenue1Year(),expectedModel.getRevenue1Year());
//		verifyProductSKU(grabbedModel.getProductSku(),expectedModel.getProductSku());
//		
	}
	@Step
	public void validateNewScKoboSubscriptionAndShopForMyselfMailchimpProperties(SubscriberModel grabbedModel, SubscriberModel expectedModel) {
		verifyEmail(grabbedModel.getEmail(),expectedModel.getEmail());
//		verifyActivatedAt(grabbedModel.getActivatedAt(),expectedModel.getActivatedAt());
//		verifyIsStylist(grabbedModel.getIsStylist(),expectedModel.getIsStylist());
//		verifyLastDatePurchase(grabbedModel.getLastPurchase(), expectedModel.getLastPurchase());
//		verifyPreferredWebsite(grabbedModel.getPreferredWebsite(),expectedModel.getPreferredWebsite());
//		verifyContactOrUser(grabbedModel.getContactOrUser(),expectedModel.getContactOrUser());
//		verifyStylistFlag(grabbedModel.getFlagStylist(),expectedModel.getFlagStylist());
//		verifyHostFlag(grabbedModel.getFlagHost(),expectedModel.getFlagHost());
//		verifyKoboCode(grabbedModel.getKoboCode(),expectedModel.getKoboCode());
//		verifyFirstName(grabbedModel.getFirstName(), expectedModel.getFirstName());
//		verifyLastName(grabbedModel.getLastName(), expectedModel.getLastName());
//		verifyCountry(grabbedModel.getCountry(),expectedModel.getCountry());
//		verifyRevenue3Months(grabbedModel.getRevenue3Months(), expectedModel.getRevenue3Months());
//		verifyRevenue6Months(grabbedModel.getRevenu6Months(),expectedModel.getRevenu6Months());
//		verifyRevenue1Year(grabbedModel.getRevenue1Year(),expectedModel.getRevenue1Year());
//		verifyProductSKU(grabbedModel.getProductSku(),expectedModel.getProductSku());
//		
	}
	@Step
	public void validateNewCustomerOrderWithKoboMailchimpPropertiesZeroRevenue(SubscriberModel grabbedModel, SubscriberModel expectedModel) {
		verifyEmail(grabbedModel.getEmail(),expectedModel.getEmail());
//		verifyActivatedAt(grabbedModel.getActivatedAt(),expectedModel.getActivatedAt());
//		verifyIsStylist(grabbedModel.getIsStylist(),expectedModel.getIsStylist());
//		verifyLastDatePurchase(grabbedModel.getLastPurchase(), expectedModel.getLastPurchase());
//		verifyPreferredWebsite(grabbedModel.getPreferredWebsite(),expectedModel.getPreferredWebsite());
//		verifyContactOrUser(grabbedModel.getContactOrUser(),expectedModel.getContactOrUser());
//		verifyStylistFlag(grabbedModel.getFlagStylist(),expectedModel.getFlagStylist());
//		verifyHostFlag(grabbedModel.getFlagHost(),expectedModel.getFlagHost());
//		verifyCustomerKobo(grabbedModel.getCustKobo(),expectedModel.getCustKobo());
//		verifyFirstName(grabbedModel.getFirstName(), expectedModel.getFirstName());
//		verifyLastName(grabbedModel.getLastName(), expectedModel.getLastName());
//		verifyCountry(grabbedModel.getCountry(),expectedModel.getCountry());
//		verifyRevenue3Months(grabbedModel.getRevenue3Months(), expectedModel.getRevenue3Months());
//		verifyRevenue6Months(grabbedModel.getRevenu6Months(),expectedModel.getRevenu6Months());
//		verifyRevenue1Year(grabbedModel.getRevenue1Year(),expectedModel.getRevenue1Year());
//		verifyProductSKU(grabbedModel.getProductSku(),expectedModel.getProductSku());
//		
	}
	
	@Step
	public SubscriberModel populateSubsriberModelFromExistingData(CustomerFormModel dataModel, DateModel dateModel){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.CONTACT);
		resultSubscriber.setFlagStylist(ConfigConstants.NO);
		resultSubscriber.setFlagHost(ConfigConstants.NO);
		
		return resultSubscriber;
	}
	@Step
	public SubscriberModel populateNewCustomerWithKoboFromExistingData(CustomerFormModel dataModel,DateModel dateModel,BasicProductModel basicProductModel, ShippingModel shippingModel, String koboCode){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.USER);
		resultSubscriber.setFlagStylist(ConfigConstants.YES);
		resultSubscriber.setFlagHost(ConfigConstants.YES);
		resultSubscriber.setFirstName(dataModel.getFirstName());
		resultSubscriber.setLastName(dataModel.getLastName());
		resultSubscriber.setCountry(MongoReader.getContext().toUpperCase());
		resultSubscriber.setCustKobo(koboCode);
		resultSubscriber.setRevenue3Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenu6Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenue1Year(shippingModel.getTotalAmount());
		resultSubscriber.setProductSku(basicProductModel.getProdCode());
		
		return resultSubscriber;
	}
	@Step
	public SubscriberModel populateNewCustomerWithKoboFromExistingDataWithZeroRevenue(CustomerFormModel dataModel,DateModel dateModel,RegularBasicProductModel basicProductModel, ShippingModel shippingModel, String koboCode){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.USER);
		resultSubscriber.setFlagStylist(ConfigConstants.YES);
		resultSubscriber.setFlagHost(ConfigConstants.YES);
		resultSubscriber.setFirstName(dataModel.getFirstName());
		resultSubscriber.setLastName(dataModel.getLastName());
		resultSubscriber.setCountry(MongoReader.getContext().toUpperCase());
		resultSubscriber.setCustKobo(koboCode);
		resultSubscriber.setRevenue3Months(ConfigConstants.ZERO);
		resultSubscriber.setRevenu6Months(ConfigConstants.ZERO);
		resultSubscriber.setRevenue1Year(ConfigConstants.ZERO);
		resultSubscriber.setProductSku(basicProductModel.getProdCode());
		
		return resultSubscriber;
	}
	@Step
	public SubscriberModel populatePlaceCustomerOrderSubscriber(CustomerFormModel dataModel,DateModel dateModel,HostBasicProductModel hostbasicProductModel, ShippingModel shippingModel, String koboCode){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.USER);
		resultSubscriber.setFlagStylist(ConfigConstants.YES);
		resultSubscriber.setFlagHost(ConfigConstants.YES);
		resultSubscriber.setFirstName(dataModel.getFirstName());
		resultSubscriber.setLastName(dataModel.getLastName());
		resultSubscriber.setCountry(MongoReader.getContext().toUpperCase());
		resultSubscriber.setCustKobo(koboCode);
		resultSubscriber.setRevenue3Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenu6Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenue1Year(shippingModel.getTotalAmount());
		resultSubscriber.setProductSku(hostbasicProductModel.getProdCode());
		
		return resultSubscriber;
	}
	@Step
	public SubscriberModel populateNewStyleCoachFromExistingDataWithZeroRevenue(CustomerFormModel dataModel,DateModel dateModel,BasicProductModel basicProductModel, ShippingModel shippingModel, String subscriptionKoboCode){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.USER);
		resultSubscriber.setFlagStylist(ConfigConstants.YES);
		resultSubscriber.setFlagHost(ConfigConstants.YES);
		resultSubscriber.setFirstName(dataModel.getFirstName());
		resultSubscriber.setLastName(dataModel.getLastName());
		resultSubscriber.setCountry(MongoReader.getContext().toUpperCase());
		resultSubscriber.setKoboCode(subscriptionKoboCode);
		resultSubscriber.setRevenue3Months(ConfigConstants.ZERO);
		resultSubscriber.setRevenu6Months(ConfigConstants.ZERO);
		resultSubscriber.setRevenue1Year(ConfigConstants.ZERO);
		resultSubscriber.setProductSku(basicProductModel.getProdCode());
		
		return resultSubscriber;
	}
	@Step
	public SubscriberModel populateNewStyleCoachFromExistingData(CustomerFormModel dataModel,DateModel dateModel,BasicProductModel basicProductModel, ShippingModel shippingModel, String subscriptionKoboCode){		
		SubscriberModel resultSubscriber = new SubscriberModel();
		
		resultSubscriber.setEmail(dataModel.getEmailName());
		resultSubscriber.setActivatedAt(dateModel.getDate());
		resultSubscriber.setIsStylist(ConfigConstants.NO);
		resultSubscriber.setLastPurchase(dateModel.getDate());
		resultSubscriber.setPreferredWebsite(MongoReader.getContext());
		resultSubscriber.setContactOrUser(ConfigConstants.USER);
		resultSubscriber.setFlagStylist(ConfigConstants.YES);
		resultSubscriber.setFlagHost(ConfigConstants.YES);
		resultSubscriber.setFirstName(dataModel.getFirstName());
		resultSubscriber.setLastName(dataModel.getLastName());
		resultSubscriber.setCountry(MongoReader.getContext().toUpperCase());
		resultSubscriber.setKoboCode(subscriptionKoboCode);
		resultSubscriber.setRevenue3Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenu6Months(shippingModel.getTotalAmount());
		resultSubscriber.setRevenue1Year(shippingModel.getTotalAmount());
		resultSubscriber.setProductSku(basicProductModel.getProdCode());
		
		return resultSubscriber;
	}
	
	@Step
	public void verifyEmail(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Emails dont match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyActivatedAt(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: ActivatedAt date doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyFirstName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Firstname doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyLastName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Lastname doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyIsStylist(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: IsStylist doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}

	@Step
	public void verifyLastDatePurchase(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: LastDatePurchase doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyCountry(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Country doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyKoboUsed(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: KoboUsed field doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyKoboCode(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Kobo Code doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyPreferredWebsite(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Preferred Website doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifySCsponsorName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: SC sponsor name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifySCsponsorEmail(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: SC sponsor email doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifySCsponsorPhone(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: SC sponsor phone doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifySCsponsorShop(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: SC sponsor shop doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyProductSKU(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Product SKU doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyProductName(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Product name doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyRevenue3Months(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Revenue 3 months doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyRevenue6Months(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Revenue 6 months doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyRevenue1Year(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Revenue 1 year doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyContactOrUser(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: User type doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyStylistFlag(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Stylist flag doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	
	@Step
	public void verifyHostFlag(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Host flag match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
	
	@Step
	public void verifyCustomerKobo(String expectedValue, String grabbedValue) {
		CustomVerification.verifyTrue("Failure: Kobo used by custmer doesn't match Expected: " + expectedValue + " Actual: " + grabbedValue, expectedValue.contains(grabbedValue));
	}
}

package com.tests.uss10.us10006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.data.UrlModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.borrowCart.BorrowCartCalculator;
import com.tools.datahandlers.borrowCart.BorrowDataGrabber;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

@WithTag(name = "US10", type = "frontend")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10006CheckPartyWishlistAndBorrowProductTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public BorrowCartSteps borrowCartSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public BorrowCartValidationWorkflows borrowCartValidationWorkflows;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public CustomVerification customVerifications;

	public static UrlModel urlModel = new UrlModel();
	public static DateModel dateModel = new DateModel();
	private String username, password;
	private CreditCardModel creditCardData = new CreditCardModel();
	public static List<RegularBasicProductModel> productsList = new ArrayList<RegularBasicProductModel>();
	public static List<BorrowProductModel> borrowCartProductsList = new ArrayList<BorrowProductModel>();
	private String productName;
	private static String billingAddress;
	private static String shippingValue;
	private static String taxClass;



	@Before
	public void setUp() throws Exception {
		
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress = prop.getProperty("billingAddress");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass = prop.getProperty("taxClass");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		MongoConnector.cleanCollection(getClass().getSimpleName());

		urlModel = MongoReader.grabUrlModels("US10006CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		System.out.println(urlModel.getUrl());
		productsList = MongoReader.grabRegularBasicProductModel("US10006CustomerAddProductIntoWishlistTest" + SoapKeys.CALC);
		productName = productsList.get(0).getName();

	}

	@Test
	public void us10006CheckPartyWishlistAndBorrowProductTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}

		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.selectWishlistProductAndAddItToBorrowCart(productName);
		borrowCartSteps.clickWipeCart();
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.selectWishlistProductAndAddItToBorrowCart(productName);
		BorrowProductModel productData;

		productData = addBorrowedProductsWorkflow.setBorrowedDefaultProductToCart();
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		productData = addBorrowedProductsWorkflow.setBorrowedProductToCart(productsList.get(0), "0.00");
		BorrowCartCalculator.allBorrowedProductsList.add(productData);

		BorrowCartCalculator.calculateCartAndShippingTotals(taxClass, shippingValue);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		BorrowDataGrabber.grabbedBorrowCartProductsList = borrowCartSteps.grabProductsData();

		borrowCartSteps.grabTotals();
		borrowCartSteps.clickGoToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.checkTermsCheckbox();

		shippingSteps.grabBorrowedProductsList();
		shippingSteps.grabSurveyData();
		shippingSteps.clickGoToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.urlModel.setName("Payment URL");
		DataGrabber.urlModel.setUrl(url);
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.grabProductsList();
		confirmationSteps.grabConfirmationTotals();
		confirmationSteps.grabBillingData();
		confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		borrowCartValidationWorkflows.setBillingShippingAddress(billingAddress, billingAddress);
		borrowCartValidationWorkflows.performBorrowCartValidations();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {

		MongoWriter.saveBorrowCartCalcDetailsModel(BorrowCartCalculator.borrowCartCalcDetailsModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(BorrowCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(BorrowDataGrabber.borrowCartConfirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BorrowProductModel product : BorrowCartCalculator.allBorrowedProductsList) {
			MongoWriter.saveBorrowProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}

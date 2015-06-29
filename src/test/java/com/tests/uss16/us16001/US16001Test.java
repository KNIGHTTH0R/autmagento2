package com.tests.uss16.us16001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.SoapKeys;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.borrowCart.BorrowCartCalculator;
import com.tools.datahandlers.borrowCart.BorrowDataGrabber;
import com.tools.env.constants.FilePaths;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

@WithTag(name = "US16", type = "frontend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class US16001Test extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public BorrowCartSteps borrowCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public BorrowCartValidationWorkflows borrowCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private static String billingAddress;
	private static String shippingValue;
	private static String taxClass;
	private CreditCardModel creditCardData = new CreditCardModel();

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		genProduct1 = ApiCalls.createProductModel();
		genProduct1.setPrice("49.90");
		ApiCalls.createApiProduct(genProduct1);

		genProduct2 = ApiCalls.createProductModel();
		genProduct2.setPrice("89.00");
		ApiCalls.createApiProduct(genProduct2);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_16_FOLDER + File.separator + "us16001.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us16001Test() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickGoToBorrowCart();
		borrowCartSteps.clickWipeCart();
		BorrowProductModel productData;
		
		productData = addBorrowedProductsWorkflow.setBorrowedDefaultProductToCart();
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		productData = addBorrowedProductsWorkflow.setBorrowedProductToCart(genProduct1, "0.00");
		BorrowCartCalculator.allBorrowedProductsList.add(productData);
		productData = addBorrowedProductsWorkflow.setBorrowedProductToCart(genProduct2, "0.00");
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
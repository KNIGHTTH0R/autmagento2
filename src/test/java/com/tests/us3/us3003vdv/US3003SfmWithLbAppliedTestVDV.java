package com.tests.us3.us3003vdv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.3 Shop for myself with Laungery bonnus applied", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_3.class)
@RunWith(SerenityRunner.class)
public class US3003SfmWithLbAppliedTestVDV extends BaseTest {
	@Steps
	public UserRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ValidationWorkflows validationWorkflows;
	@Steps
	public SearchSteps searchSteps;

	private String username, password;
	private static String jewelryDiscount;
	private static String shippingValue;
	private static String taxClass;
	private static String addressString;
	private static String marketingDiscount;

	private CreditCardModel creditCardData;
//	private AddressModel addressModel;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		creditCardData = new CreditCardModel();
		//addressModel = new AddressModel();

		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsSFMTest" + SoapKeys.GRAB);

		genProduct1 = createdProductsList.get(0);
		genProduct1.setIp("0");
		genProduct2 = createdProductsList.get(1);
		genProduct2.setIp("0");

		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3003.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");
			jewelryDiscount = prop.getProperty("jewelryDisount");
			marketingDiscount = prop.getProperty("marketingDisount");
			addressString = prop.getProperty("addressString");
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
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.RECALC);

	}

	@Test
	public void us3003SfmWithLbAppliedTestVDV() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
		//	footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		BasicProductModel productData;
		
		searchSteps.navigateToProductPage(genProduct1.getParentProductSku());
		System.out.println("genProduct1.getSku(): "+genProduct1.getSku());
		System.out.println("genProduct1.getSku(): "+genProduct2.getSku());


		productData = addProductsWorkflow.setBasicChildProductToCart(genProduct1, "2", "0", ConfigConstants.DISCOUNT_20);
		CartCalculator.productsList25.add(productData);
	
		searchSteps.navigateToProductPage(genProduct2.getParentProductSku());
		productData = addProductsWorkflow.setBasicChildProductToCart(genProduct2, "1", "0", ConfigConstants.DISCOUNT_20);
		CartCalculator.productsList25.add(productData);
		
		CartCalculator.calculateJMDiscounts(jewelryDiscount, marketingDiscount, taxClass, shippingValue);
		
		
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		
		DataGrabber.cartProductsWith20Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		cartSteps.typeJewerlyBonus(jewelryDiscount);
		cartSteps.updateJewerlyBonus();

		DataGrabber.cartProductsWith20DiscountDiscounted = cartSteps.grabProductsDataWith25PercentDiscount();
		cartSteps.grabTotals();
		cartSteps.goToShipping();


		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.goBack();
		cartSteps.goToShipping();
		shippingSteps.grabProductsList();
		shippingSteps.grabSurveyData();
		shippingSteps.goToPaymentMethod();

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
	//	validationWorkflows.setBillingShippingAddress(addressModel.getCountryName(), addressModel.getCountryName());
		validationWorkflows.setBillingShippingAddress(addressString, addressString);
		validationWorkflows.performCartValidations();
		customVerifications.printErrors();
		
	}

	@After
	public void saveData() {
		MongoWriter.saveCalcDetailsModel(CartCalculator.calculatedTotalsDiscounts,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel,
				getClass().getSimpleName() + SoapKeys.CALC);
		MongoWriter.saveShippingModel(DataGrabber.confirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		for (BasicProductModel product : CartCalculator.allProductsListRecalculated) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
		for (BasicProductModel product : CartCalculator.allProductsListRecalculated) {
			System.out.println(product);
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.RECALC);
		}
	}
}
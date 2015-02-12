package com.tests.us3;

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
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.calculation.CartCalculation;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoTableKeys;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.CartWorkflows;

@WithTag(name = "US003", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US003CartSegmentationWithVatTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public CartCalculation calculationSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public CartWorkflows cartWorkflows;
	@Steps
	public PaymentSteps paymentSteps;

	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private static CartTotalsModel discountTotals = new CartTotalsModel();
	private static ShippingModel shippingTotals = new ShippingModel();
	private static ShippingModel confirmationTotals = new ShippingModel();
	private static List<ProductBasicModel> cartProductsList = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> shippingProductsList = new ArrayList<ProductBasicModel>();

	private static CalcDetailsModel discountCalculationModel;
	private static ShippingModel shippingCalculatedModel = new ShippingModel();
	private static UrlModel urlModel = new UrlModel();

	private CreditCardModel creditCardData = new CreditCardModel();

	// extracted from URL in first test - validated in second test
	private static OrderModel orderModel = new OrderModel();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();

	// Test data - from property file
	private String username, password;
	// Test data fields
	private static String jewelryDisount;
	private static String marketingDisount;
	private static String shippingPrice;
	private static String addressString;
	// Test data Credit card details
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us3\\us003.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			jewelryDisount = prop.getProperty("jewelryDisount");
			marketingDisount = prop.getProperty("marketingDisount");
			shippingPrice = prop.getProperty("shippingPrice");
			addressString = prop.getProperty("addressString");

			cardNumber = prop.getProperty("cardNumber");
			cardName = prop.getProperty("cardName");
			cardMonth = prop.getProperty("cardMonth");
			cardYear = prop.getProperty("cardYear");
			cardCVC = prop.getProperty("cardCVC");

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

		creditCardData.setCardNumber(cardNumber);
		creditCardData.setCardName(cardName);
		creditCardData.setMonthExpiration(cardMonth);
		creditCardData.setYearExpiration(cardYear);
		creditCardData.setCvcNumber(cardCVC);

		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);
	}

	@Test
	public void us003CartSegmentationWithVatTest() {
		frontEndSteps.performLogin(username, password);
		frontEndSteps.wipeCart();
		ProductBasicModel productData;

		searchSteps.searchAndSelectProduct("K024RO", "CORALIE SET (ROSÉ)");
		productData = productSteps.setProductAddToCart("1", "17");
		cartProductsList.add(productData);

		searchSteps.searchAndSelectProduct("K024RO", "CORALIE SET (ROSÉ)");
		productData = productSteps.setProductAddToCart("1", "17");
		cartProductsList.add(productData);

		searchSteps.searchAndSelectProduct("R025WT", "DAMARIS RING");
		productData = productSteps.setProductAddToCart("1", "16");
		cartProductsList.add(productData);

		searchSteps.searchAndSelectProduct("M101 ", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productData = productSteps.setProductAddToCart("1", "0");
		cartProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));

		CalculationModel totalsCartCalculated = CartCalculation.calculateTotalSum(totalsList);
		PrintUtils.printCalculationModel(totalsCartCalculated);

		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		cartTotals = cartSteps.grabTotals();

		// APPLY DISCOUNTS
		cartSteps.typeJewerlyBonus(jewelryDisount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDisount);
		cartSteps.updateMarketingBonus();

		discountCalculationModel = calculationSteps.calculateDiscountTotals(totalsList, jewelryDisount, marketingDisount);
		shippingCalculatedModel = calculationSteps.remove119VAT(discountCalculationModel, shippingPrice);
		shippingProductsList = calculationSteps.remove119VAT(cartProductsList);

		discountTotals = cartSteps.grabTotals();

		cartSteps.clickGoToShipping();

		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		System.out.println(" --- shippingProducts ---");
		PrintUtils.printList(shippingProducts);
		System.out.println("X --- shippingProducts ---");

		shippingTotals = shippingSteps.grabSurveyData();

		shippingSteps.clickGoToPaymentMethod();

		String url = shippingSteps.grabUrl();
		urlModel.setName("Payment URL");
		urlModel.setUrl(url);
		orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();
		confirmationTotals = confirmationSteps.grabConfirmationTotals();

		// Steps to finalize order
		// confirmationSteps.agreeAndCheckout();
		// checkoutValidationSteps.verifySuccessMessage();

		// Products List validation
		cartWorkflows.setValidateProductsModels(cartProductsList, cartProducts);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		// Need to validate to discounted item prices
		cartWorkflows.setValidateProductsModels(shippingProductsList, shippingProducts);
		cartWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		cartWorkflows.setValidateProductsModels(shippingProductsList, confirmationProducts);
		cartWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		// Totals validation
		cartWorkflows.setCheckCalculationTotalsModels(cartTotals, totalsCartCalculated);
		cartWorkflows.checkCalculationTotals("CART TOTALS");

		cartWorkflows.setVerifyTotalsDiscount(discountTotals, discountCalculationModel);
		cartWorkflows.verifyTotalsDiscount("DISCOUNT TOTALS");

		cartWorkflows.setVerifyShippingTotals(shippingTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		cartWorkflows.setVerifyShippingTotals(confirmationTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		// Validate URL
		checkoutValidationSteps.checkTotalAmountFromUrl(orderModel.getTotalPrice(), shippingCalculatedModel.getTotalAmount().replace(".", ""));
	}

	@After
	public void saveData() {
		MongoWriter.saveTotalsModel(cartTotals, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveTotalsModel(discountTotals, getClass().getSimpleName() + Constants.CALC);

		// Discount calculations - jewelry and marketing
		MongoWriter.saveCalcDetailsModel(discountCalculationModel, getClass().getSimpleName() + Constants.CALC);

		// values with discount and no TAX VAT - calculated values
		MongoWriter.saveShippingModel(shippingCalculatedModel, getClass().getSimpleName() + Constants.CALC);

		// Values Grabbed from last screen totals
		MongoWriter.saveShippingModel(confirmationTotals, getClass().getSimpleName() + MongoTableKeys.GRAB);

		// Order status and details
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName() + Constants.GRAB);

		// Payment URL with values
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + Constants.GRAB);

		// Products list - with initial values
		for (ProductBasicModel product : cartProductsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName() + Constants.GRAB);
		}
		// Products list - with initial values
		for (ProductBasicModel product : shippingProductsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName() + Constants.CALC);
		}
	}

}

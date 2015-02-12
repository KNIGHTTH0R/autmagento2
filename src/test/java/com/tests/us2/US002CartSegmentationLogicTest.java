package com.tests.us2;

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
import com.steps.frontend.ProfileSteps;
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
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.CartWorkflows;

@WithTag(name = "US002", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US002CartSegmentationLogicTest extends BaseTest {

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
	public CartCalculation calculusSteps;
	@Steps
	public CheckoutValidationSteps validationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public CartWorkflows cartWorkflows;

	private static OrderModel orderModel = new OrderModel();
	private static UrlModel urlModel = new UrlModel();
	private static ShippingModel shippingCalculatedModel = new ShippingModel();

	private static List<ProductBasicModel> allProductsList = new ArrayList<ProductBasicModel>();

	private static List<ProductBasicModel> productsList25 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsList50 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsListMarketing = new ArrayList<ProductBasicModel>();

	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private CreditCardModel creditCardData = new CreditCardModel();

	private String username, password;
	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private static CalcDetailsModel discountCalculationModel = new CalcDetailsModel();
	private static String shippingValue;
	private static String jewelryDiscount;
	private static String marketingDiscount;
	
	//Test data Credit card details
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

			input = new FileInputStream(Constants.RESOURCES_PATH + "us2\\us002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			jewelryDiscount = prop.getProperty("jewelryDisount");
			marketingDiscount = prop.getProperty("marketingDisount");
			shippingValue = prop.getProperty("shippingPrice");
			
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);
	}

	@Test
	public void uS002CartSegmentationLogicTest() {
		frontEndSteps.performLogin(username, password);
		frontEndSteps.wipeCart();
		ProductBasicModel productData;

		searchSteps.searchAndSelectProduct("R104WT", "OPEN MIND RING");
		productData = productSteps.setProductAddToCart("1", "16");
		
		// we add this into both sections because the quantity will be increased
		// at 2, so 1 piece will be added into 25 section
		productsList50.add(productData);
		productsList25.add(productData);

		searchSteps.searchAndSelectProduct("K051SV", "ROSEMARY SET");
		productData = productSteps.setProductAddToCart("2", "17");

		//Changed new product to this
		ProductBasicModel newProduct = new ProductBasicModel();
		newProduct.setName(productData.getName());
		newProduct.setPrice(productData.getPrice());
		newProduct.setType(productData.getType());
		newProduct.setQuantity("1");
		
		productsList50.add(newProduct);

		searchSteps.searchAndSelectProduct("K050SV", "JOANNA SET");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList50.add(productData);

		searchSteps.searchAndSelectProduct("M064", "SCHMUCKBROSCHÜRE (40 STK.)");
		productData = productSteps.setProductAddToCart("2", "0");
		productsListMarketing.add(productData);

		searchSteps.searchAndSelectProduct("M101", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productData = productSteps.setProductAddToCart("1", "0");
		productsListMarketing.add(productData);

		allProductsList.addAll(productsList25);
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productsListMarketing);

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		// TODO change the update method to set the quantity in the model
		cartSteps.updateProductQuantityIn50DiscountArea("2", "R104WT");
		cartSteps.updateProductQuantityIn50DiscountArea("0", "K051SV");

		cartSteps.updateCart();

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));
		cartTotals = cartSteps.grabTotals();

		discountCalculationModel = calculusSteps.calculateDiscountTotals(totalsList, jewelryDiscount, marketingDiscount);

		cartSteps.clickGoToShipping();

		shippingCalculatedModel = calculusSteps.calculateShippingTotals(discountCalculationModel, shippingValue);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		ShippingModel shippingTotals = shippingSteps.grabSurveyData();

		shippingSteps.clickGoToPaymentMethod();

		String url = shippingSteps.grabUrl();
		urlModel.setName("Payment URL");
		urlModel.setUrl(url);
		orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();

		paymentSteps.fillCreditCardForm(creditCardData);

		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();

		ShippingModel confirmationTotals = confirmationSteps.grabConfirmationTotals();

		 confirmationSteps.agreeAndCheckout();
		
		 validationSteps.verifySuccessMessage();

		cartWorkflows.setValidateProductsModels(productsList50, cartProductsWith50Discount);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows.setValidateProductsModels(productsList25, cartProductsWith25Discount);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");

		cartWorkflows.setValidateProductsModels(productsListMarketing, cartMarketingMaterialsProducts);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

		cartWorkflows.setValidateProductsModels(allProductsList, shippingProducts);
		cartWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		cartWorkflows.setValidateProductsModels(allProductsList, confirmationProducts);
		cartWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		cartWorkflows.setVerifyTotalsDiscount(cartTotals, discountCalculationModel);
		cartWorkflows.verifyTotalsDiscountNoMarketing("CART TOTALS");

		cartWorkflows.setVerifyShippingTotals(shippingTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		cartWorkflows.setVerifyShippingTotals(confirmationTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
	}

	@After
	public void saveData() {

		MongoWriter.saveCalcDetailsModel(discountCalculationModel, getClass().getSimpleName() + Constants.CALC);
		for (ProductBasicModel product : allProductsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName() + Constants.GRAB);
		}
		MongoWriter.saveShippingModel(shippingCalculatedModel, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + Constants.GRAB);

	}

}

package com.tests.copy2Ofus4001;

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

import com.connectors.http.CreateProduct;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.calculation.CartBuy3Get1Calculation;
import com.tools.calculation.CartCalculation;
import com.tools.calculation.CartDiscountsCalculation;
import com.tools.calculation.CartTotalsCalculation;
import com.tools.data.CalcDetailsModel;
import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.CartWorkflows;
import com.workflows.frontend.CartWorkflows2;
import com.workflows.frontend.ShippingAndConfirmationWorkflows;

@WithTag(name = "US4001", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US4001Test extends BaseTest {
	
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ShippingAndConfirmationWorkflows shippingAndConfirmationWorkflows;
	@Steps
	public CartWorkflows2 cartWorkflows2;
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
	@Steps 
	public CustomVerification customVerifications;
	
	private String username, password;
	private String billingAddress;
//	private ProductBasicModel productBasicModel = new ProductBasicModel();
	private CreditCardModel creditCardData = new CreditCardModel();
	private static ShippingModel shippingCalculatedModel = new ShippingModel();
	private static List<BasicProductModel> productsList25 = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> productsList50 = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> productsListMarketing = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> allProductsList = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> productListForBuy3Get1 = new ArrayList<BasicProductModel>();
	private List<BasicProductModel> productList25AndMmWithBuy3Get1Applied = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> allProductsListRecalculated = new ArrayList<BasicProductModel>();
	private static List<CartProductModel> productsList25AndMmGrabbed = new ArrayList<CartProductModel>();
	private static ShippingModel confirmationTotals = new ShippingModel();
	private static ShippingModel shippingTotals = new ShippingModel();
	private static UrlModel urlModel = new UrlModel();
	private static OrderModel orderModel = new OrderModel();
	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private CalcDetailsModel total = new CalcDetailsModel();
	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String shippingValueForLessThan150;
	private static String taxClass;
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;
	private List<CartProductModel> cartProds = new ArrayList<CartProductModel>();
	

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {

		genProduct1 = CreateProduct.createProductModel();
		genProduct1.setPrice("100");
		CreateProduct.createApiProduct(genProduct1);
		genProduct2.setName("THERESA BAG");
		genProduct2.setSku("A010BK");
		genProduct2.setIp("42");
		genProduct2.setPrice("49.90");
		genProduct3 = CreateProduct.createMarketingProductModel();
		genProduct3.setPrice("5.00");
		CreateProduct.createApiProduct(genProduct3);
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us4001" + File.separator + "us4001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			billingAddress = prop.getProperty("billingAddress");
			jewelryDiscount = prop.getProperty("jewelryDiscount");
			marketingDiscount = prop.getProperty("marketingDiscount");
			shippingValue = prop.getProperty("shippingPrice");
			shippingValueForLessThan150 = prop.getProperty("shippingPriceForLessThan150");
			taxClass = prop.getProperty("taxClass");

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
	public void us4001ShopForMyselfWithJbMmbAndBuy3GetOneTest() {
		frontEndSteps.performLogin(username, password);
		frontEndSteps.wipeCart();
		BasicProductModel productData;

		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0",Constants.DISCOUNT_50);
		productsList50.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0",Constants.DISCOUNT_25);
		productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "2", "0",Constants.DISCOUNT_25);
		productsList25.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "3", "0",Constants.DISCOUNT_0);
		productsListMarketing.add(productData);
		
		productListForBuy3Get1.addAll(productsList25);
		productListForBuy3Get1.addAll(productsListMarketing);

		headerSteps.openCartPreview();
		headerSteps.goToCart();	

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
		
		productsList25AndMmGrabbed.addAll(cartProductsWith25Discount);
		productsList25AndMmGrabbed.addAll(cartMarketingMaterialsProducts);
		
		productList25AndMmWithBuy3Get1Applied = CartBuy3Get1Calculation.applyBuy3Get1OnTheCart(productListForBuy3Get1);
		PrintUtils.printListBasicProductModel(productList25AndMmWithBuy3Get1Applied);	
		
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productList25AndMmWithBuy3Get1Applied);

		
		cartSteps.typeJewerlyBonus(jewelryDiscount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDiscount);
		cartSteps.updateMarketingBonus();
		
		cartTotals = cartSteps.grabTotals();
			
		
		List<BasicProductModel> calculatedProductsList25 = CartDiscountsCalculation.calculateProductsfor25Discount(productsList25, jewelryDiscount);
		List<BasicProductModel> calculatedProductsList50 = CartDiscountsCalculation.calculateProductsfor50Discount(productsList50,productsList25, jewelryDiscount);
		List<BasicProductModel> calculatedProductsListMarketing = CartDiscountsCalculation.calculateProductsforMarketingMaterial(productsListMarketing, marketingDiscount);
		
		allProductsListRecalculated.addAll(calculatedProductsList50);
		allProductsListRecalculated.addAll(calculatedProductsList25);
		allProductsListRecalculated.addAll(calculatedProductsListMarketing);
		
		
		List<CartProductModel> cartProductsWith50DiscountDiscounted = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25DiscountDiscounted = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProductsDiscounted = cartSteps.grabMarketingMaterialProductsData();			

		cartTotals = cartSteps.grabTotals();

		total = CartTotalsCalculation.calculateCartProductsTotals(allProductsListRecalculated, jewelryDiscount, marketingDiscount,taxClass);
		PrintUtils.printCalcDetailsModel(total);

		cartSteps.clickGoToShipping();

		shippingSteps.selectAddress(billingAddress);
		shippingSteps.setSameAsBilling(true);	

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();

		shippingTotals = shippingSteps.grabSurveyData();
		
		shippingValue = Double.parseDouble(total.getTotalAmount()) >= 150 ? shippingValue : shippingValueForLessThan150;

		shippingCalculatedModel = calculationSteps.calculateShippingTotals(total, shippingValue);
		PrintUtils.printShippingTotals(shippingCalculatedModel);

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
		
		AddressModel grabbedBillingAddress =  confirmationSteps.grabBillingData();
		AddressModel grabbedShippingAddress = confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();
		
		checkoutValidationSteps.verifySuccessMessage();
		//validations before JB and MM Discounts - in this phase ,on products is applied buy3Get1 rule, so we are validating those prices 
		cartWorkflows2.setValidateProductsModels(productsList50, cartProductsWith50Discount);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION BEFORE JB AND MM DISCOUNTS");
		
		cartWorkflows2.setValidateProductsModels(productList25AndMmWithBuy3Get1Applied, productsList25AndMmGrabbed);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION AND MM SECTION BEFORE JB AND MM DISCOUNTS");		
		
		//validations products after discount is applied - in this phase the buy3get1 rule is considered null , so we are validating the prices with JB and MM
		cartWorkflows2.setValidateProductsModels(calculatedProductsList25,cartProductsWith25DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION - AFTER JB AND MM DISCOUNTS");
		
		cartWorkflows2.setValidateProductsModels(calculatedProductsList50, cartProductsWith50DiscountDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION -AFTER JB AND MM DISCOUNTS");
		
		cartWorkflows2.setValidateProductsModels(calculatedProductsListMarketing, cartMarketingMaterialsProductsDiscounted);
		cartWorkflows2.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION -AFTER JB AND MM DISCOUNTS");

		
		shippingAndConfirmationWorkflows.setValidateProductsModels(allProductsList, shippingProducts);
		shippingAndConfirmationWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		shippingAndConfirmationWorkflows.setValidateProductsModels(allProductsList, confirmationProducts);
		shippingAndConfirmationWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");


		cartWorkflows2.setVerifyTotalsDiscount(cartTotals, total);
		cartWorkflows2.verifyTotalsDiscountNoMarketing("CART TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(shippingTotals, shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		shippingAndConfirmationWorkflows.setVerifyShippingTotals(confirmationTotals, shippingCalculatedModel);
		shippingAndConfirmationWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");
		
		cartWorkflows.setBillingAddressModels(billingAddress,grabbedBillingAddress);
		cartWorkflows.validateBillingAddress("BILLING ADDRESS");
		
		cartWorkflows.setShippingAddressModels(billingAddress,grabbedShippingAddress);
		cartWorkflows.validateShippingAddress("SHIPPING ADDRESS");
		
		customVerifications.printErrors();

	}

	@After
	public void saveData() {

		MongoWriter.saveCalcDetailsModel(total, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveShippingModel(shippingCalculatedModel, getClass().getSimpleName() + Constants.CALC);
		MongoWriter.saveShippingModel(confirmationTotals, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + Constants.GRAB);
		for (BasicProductModel product : allProductsList) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + Constants.GRAB);
		}

	}

}

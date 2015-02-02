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
import com.tools.data.CalculationModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
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

	private static OrderModel orderNumber = new OrderModel();
	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsList25 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsList50 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsListMarketing = new ArrayList<ProductBasicModel>();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private CreditCardModel creditCardData = new CreditCardModel();
	private String username, password;
	private static CartTotalsModel cartTotals = new CartTotalsModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us2\\us002.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");			

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

		creditCardData.setCardNumber("4111 1111 1111 1111");
		creditCardData.setCardName("test");
		creditCardData.setMonthExpiration("06");
		creditCardData.setYearExpiration("2016");
		creditCardData.setCvcNumber("737");

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void uS002CartSegmentationLogicTest() {
		frontEndSteps.performLogin(username, password);
		ProductBasicModel productData;

		//TODO change products list or clean 
		searchSteps.searchAndSelectProduct("N100SV", "MAXI CHAIN (SILVER)");
		productData = productSteps.setProductAddToCart("1", "medium");			
		//we add this into both sections because the quantity will be increased at 2, so 1 piece will be added into 25 section 		
		ProductBasicModel newProduct = newProductObject(productData.getName(), productData.getPrice(), productData.getType(), "2");		
		productsList50.add(productData);
		productsList25.add(productData);		
		productsList.add(newProduct);
		
		searchSteps.searchAndSelectProduct("B002BE", "FLORENCE BRACELET (BEIGE)");
		productData = productSteps.setProductAddToCart("2", "medium");
		productsList.add(productData);
		ProductBasicModel newProduct2 = newProductObject(productData.getName(), productData.getPrice(), productData.getType(), "1");
		productsList50.add(newProduct2);
	
		//we remove the item product from 50 section because after updating the quantity will remain just 1 item.evend if we set 0 quantity
		//for the product from 50 section, the other piece will not remain in the 25 section ,it will come into the 50 section
		//productsList50.add(productData);
		
		searchSteps.searchAndSelectProduct("E106SV","JOANNA EARRINGS");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		productsList50.add(productData);
		
		searchSteps.searchAndSelectProduct("M064", "SCHMUCKBROSCHÜRE (40 STK.)");
		productData = productSteps.setProductAddToCart("2", "0");
		productsList.add(productData);
		productsListMarketing.add(productData);				

		searchSteps.searchAndSelectProduct("M101", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		productsListMarketing.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();
		//TODO change the update method to set the quantity in the model
		//TODO handle witch product needs to be updated if we have the same product into different discount tables
		cartSteps.updateProductQuantityIn50DiscountArea("2","N100SV");	
		cartSteps.updateProductQuantityIn50DiscountArea("0","B002BE");

		cartSteps.updateCart();

		List<CartProductModel> cartProducts = cartSteps.grabProductsData();

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));
		CalculationModel totalsCalculated = CartCalculation.calculateTotalSum(totalsList);

		System.out.println("TOTALS FOR CHECKOUT ,SHIPPING AND CONFIRMATION");

		cartTotals = cartSteps.grabTotals();
		System.out.println("buba" + cartTotals.getIpPoints());
		
		System.out.println("lists size");
		System.out.println(productsList.size());
		System.out.println("----------------------");
		System.out.println(productsList50.size());
		System.out.println(productsList50.get(0).getName());
		System.out.println(productsList50.get(0).getQuantity());
		System.out.println(productsList50.get(1).getName());
		System.out.println(productsList50.get(1).getQuantity());
		System.out.println(productsList50.get(2).getName());
		System.out.println(productsList50.get(2).getQuantity());
		System.out.println("----------------------");
		System.out.println(productsList25.size());
		System.out.println(productsList25.get(0).getName());
		System.out.println(productsList25.get(0).getQuantity());
		System.out.println("----------------------");
		System.out.println(productsListMarketing.size());
		System.out.println(productsListMarketing.get(0).getName());
		System.out.println(productsListMarketing.get(0).getQuantity());
		System.out.println(productsListMarketing.get(1).getName());
		System.out.println(productsListMarketing.get(1).getQuantity());

//		PrintUtils.printCartTotals(cartTotals);	
//
//		cartSteps.clickGoToShipping();
//
//		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
//		PrintUtils.printList(shippingProducts);
//
//		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
//		System.out.println(shippingTotals.getSubtotal());
//
//		shippingSteps.clickGoToPaymentMethod();
//
//		paymentSteps.expandCreditCardForm();

//		paymentSteps.fillCreditCardForm(creditCardData);

//		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();

//		CartTotalsModel confirmationTotals = confirmationSteps.grabSurveyData();
//		System.out.println(confirmationTotals.getSubtotal());
//
//		confirmationSteps.agreeAndCheckout();
//
//		validationSteps.verifySuccessMessage();
		
		cartWorkflows.setValidateProductsModels(productsList50, cartProductsWith50Discount);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 50 SECTION");

		cartWorkflows.setValidateProductsModels(productsList25, cartProductsWith25Discount);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR 25 SECTION");
		
		cartWorkflows.setValidateProductsModels(productsListMarketing, cartMarketingMaterialsProducts);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION FOR MARKETING MATERIAL SECTION");

//		cartWorkflows.setValidateProductsModels(productsList, shippingProducts);
//		cartWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");
//
//		cartWorkflows.setValidateProductsModels(productsList, confirmationProducts);
//		cartWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		cartWorkflows.setCheckCalculationTotalsModels(totalsCalculated, cartTotals);
		cartWorkflows.checkCalculationTotals("CART TOTALS");

	}

	@Test
	public void us002UserProfileOrderId() {

		// After validation - grab order number
		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		orderNumber.setOrderId(orderId);

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(orderNumber, getClass().getSimpleName());
		MongoWriter.saveTotalsModel(cartTotals, getClass().getSimpleName());
		for (ProductBasicModel product : productsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName());
		}

	}
	
	
	private ProductBasicModel newProductObject(String name, String price, String type, String quantity){
		ProductBasicModel newProduct = new ProductBasicModel();
		newProduct.setName(name);
		newProduct.setPrice(price);
		newProduct.setQuantity(quantity);
		newProduct.setType(type);
		
		return newProduct;
	}

}

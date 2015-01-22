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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CalculusSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.AddressModel;
import com.tools.data.CalculationModel;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;
import com.tools.data.CreditCardModel;
import com.tools.data.ProductBasicModel;
import com.tools.requirements.Application;


@WithTag(name="US2", type = "US2")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US002CartSegmentationLogicTest extends BaseTest{

	
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
	public CalculusSteps calculusSteps;
	@Steps
	public CheckoutValidationSteps validationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	
	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private CreditCardModel creditCardData = new CreditCardModel();
	private String username, password;
	
	
	
	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us1\\us001.properties");
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
	}
	
	@Test
	public void uS001StyleCoachShoppingTest(){
		frontEndSteps.performLogin("ioana.urcan@evozon.com", "ioana1234");
		ProductBasicModel productData;
		
//		searchSteps.searchAndSelectProduct("B056RS", "BANNER MIT LOGO");
//		productData = productSteps.setProductAddToCart("1", "Blue");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("B071BK", "IVY BRACELET (BLACK)");
//		productData = productSteps.setProductAddToCart("2", "0");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("R083BK", "CLARICE RING (GUN METAL)");
//		productData = productSteps.setProductAddToCart("1", "18");
//		productsList.add(productData);
		
//		searchSteps.searchAndSelectProduct("M064", "SCHMUCKBROSCHÜRE (40 STK.)");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("M101", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
		
		
		String previewPrice = headerSteps.openCartPreview();
		headerSteps.goToCart();
//		cartSteps.updateProductQuantity("6","CLARICE RING","R083BK-18","18");
//		productsList.get(0).setQuantity("6");
//		cartSteps.updateProducts();

		System.out.println("Cart Preview Price: " + previewPrice);
		
		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		PrintUtils.printList(cartProducts);
		
		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		
		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();	
		
		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
		
		CalculationModel calculationModel50Discount = calculusSteps.calculateTableProducts(cartProductsWith50Discount);
		totalsList.add(calculationModel50Discount);	
		
		CalculationModel calculationModelWith25Discount = calculusSteps.calculateTableProducts(cartProductsWith25Discount);
		totalsList.add(calculationModelWith25Discount);
		
		CalculationModel calculationModelMarketingMaterial = calculusSteps.calculateTableProducts(cartMarketingMaterialsProducts);
		totalsList.add(calculationModelMarketingMaterial);				
		

		CalculationModel sumedTotals  = calculusSteps.calculateTotalSum(totalsList);
		
		System.out.println("TOTALS FOR CHECKOUT ,SHIPPING AND CONFIRMATION");
		CartTotalsModel cartTotals = cartSteps.grabTotals();

		
		
//		cartSteps.clickGoToShipping();	
//		
//		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
//		PrintUtils.printList(shippingProducts);
//
//		
//		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
//		System.out.println(shippingTotals.getSubtotal());
//		
//		shippingSteps.clickGoToPaymentMethod();
//
//		paymentSteps.expandCreditCardForm();
//
//		paymentSteps.fillCreditCardForm(creditCardData);
//		
//		AddressModel billingAddress = confirmationSteps.grabBillingData();
//		AddressModel shippingAddress = confirmationSteps.grabSippingData();
//		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();
//		
//		CartTotalsModel confirmationTotals = confirmationSteps.grabSurveyData();
//		System.out.println(confirmationTotals.getSubtotal());
//
//		confirmationSteps.agreeAndCheckout();
//		
//		checkoutValidationSteps.verifySuccessMessage();
//		
//		System.out.println("CART PHASE PRODUCTS VALIDATION");
//		checkoutValidationSteps.validateProducts(productsList, cartProducts);
//		System.out.println("SHIPPING PHASE PRODUCTS VALIDATION");
//		checkoutValidationSteps.validateProducts(productsList, shippingProducts);
//		System.out.println("CONFIRMATION PHASE PRODUCTS VALIDATION");
//		checkoutValidationSteps.validateProducts(productsList, confirmationProducts);
//		
//		
//		
//		PrintUtils.printCartTotals(cartTotals);
//		
//		System.out.println("TOTALS CALCULATED");
//		CartTotalsModel cartBigTotal = checkoutValidationSteps.calculateCartProducts(cartProducts);
//		PrintUtils.printCartTotals(cartBigTotal);
//		
//		System.out.println("-----DOAMNE AJUTA-------");
//		
//		checkoutValidationSteps.checkTotals(sumedTotals, cartTotals);
//		checkoutValidationSteps.checkTotals(sumedTotals, shippingTotals);
//		checkoutValidationSteps.checkTotals(sumedTotals, confirmationTotals);

		System.out.println(cartTotals.getSubtotal());
		
		cartSteps.clickGoToShipping();	
		
		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		
		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
		System.out.println(shippingTotals.getSubtotal());
		
		shippingSteps.clickGoToPaymentMethod();

		paymentSteps.expandCreditCardForm();

		paymentSteps.fillCreditCardForm(creditCardData);
		
		AddressModel billingAddress = confirmationSteps.grabBillingData();
		AddressModel shippingAddress = confirmationSteps.grabSippingData();
		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();
		
		CartTotalsModel confirmationTotals = confirmationSteps.grabSurveyData();
		System.out.println(confirmationTotals.getSubtotal());

		confirmationSteps.agreeAndCheckout();
		
		validationSteps.verifySuccessMessage();
		
		System.out.println("CART PHASE PRODUCTS VALIDATION");
		validationSteps.validateProducts(productsList, cartProducts);
		System.out.println("SHIPPING PHASE PRODUCTS VALIDATION");
		validationSteps.validateProducts(productsList, shippingProducts);
		System.out.println("CONFIRMATION PHASE PRODUCTS VALIDATION");
		validationSteps.validateProducts(productsList, confirmationProducts);
		
		PrintUtils.printCartTotals(cartTotals);
		
		System.out.println("TOTALS CALCULATED");
		CartTotalsModel cartBigTotal = validationSteps.calculateCartProducts(cartProducts);
		PrintUtils.printCartTotals(cartBigTotal);
		
		System.out.println("-----DOAMNE AJUTA-------");
		
		validationSteps.checkTotals(sumedTotals, cartTotals);
		validationSteps.checkTotals(sumedTotals, shippingTotals);
		validationSteps.checkTotals(sumedTotals, confirmationTotals);

		
		
	}
	
	
	
	
}

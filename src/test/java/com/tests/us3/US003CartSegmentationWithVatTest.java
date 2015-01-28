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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import com.tools.PrintUtils;
import com.tools.calculation.CartCalculation;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.Application;
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
	public CheckoutValidationSteps validationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public CartWorkflows cartWorkflows;	
	@Steps
	public PaymentSteps paymentSteps;
	@Steps

	
	private CreditCardModel creditCardData = new CreditCardModel();
	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private static CartTotalsModel finalCartTotals = new CartTotalsModel();
	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private static CalculationModel totals0Vat =new CalculationModel();
	private String username, password;


	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us3\\us003.properties");
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
	public void uS003CartSegmentationWithVatTest() {
		frontEndSteps.performLogin(username, password);
		ProductBasicModel productData;
		
//		searchSteps.searchAndSelectProduct("Prod1_ioana", "PRODUS SIMPLU IOANA");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("Prod1_ioana", "PRODUS SIMPLU IOANA");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("R025WT", "DAMARIS RING");
//		productData = productSteps.setProductAddToCart("1", "16");
//		productsList.add(productData);
//		
//		searchSteps.searchAndSelectProduct("M101 ", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
		
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();		

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		
		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
		
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));
		CalculationModel totalsCalculated = CartCalculation.calculateTotalSum(totalsList);
		PrintUtils.printCalculationModel(totalsCalculated);
		
	
		cartTotals = cartSteps.grabTotals();
		PrintUtils.printCartTotals(cartTotals);
		
//		cartSteps.typeJewerlyBonus("198.90");
		cartSteps.typeJewerlyBonus("20.00");
		cartSteps.updateJewerlyBonus();
		
		
		calculationSteps.calculateJewelryDiscount(totalsList, "20");
//		calculationSteps.calculateJewelryDiscount(totalsList, "19890");
		
		
//		cartSteps.typeMarketingBonus("150");
//		cartSteps.updateMarketingBonus();
		
		
		
		
//		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//		CalculationModel recalculatedTotals = CartCalculation.recalculateTotalsAfterDiscounts(totalsCalculated);
//		PrintUtils.printCalculationModel(recalculatedTotals);
//		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		
		
//		finalCartTotals = cartSteps.grabTotals();
//		PrintUtils.printCartTotals(finalCartTotals);
//		
//		cartSteps.clickGoToShipping();
//		
//		shippingSteps.selectAddress("sss sss, tttt, 3, 2345 Wien, Österreich");
//		shippingSteps.setSameAsBilling(true);
//
//		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
//		PrintUtils.printList(shippingProducts);
//
//		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
//		PrintUtils.printCartTotals(shippingTotals);
//		
//		shippingSteps.clickGoToPaymentMethod();
//		
//		String url = shippingSteps.grabUrl();
//		totals0Vat = calculationSteps.calculateShippingTotalsWith0Vat(totalsCalculated);
//		PrintUtils.printCalculationModel(totalsCalculated);
//		
//		
//
//		paymentSteps.expandCreditCardForm();
//
//		paymentSteps.fillCreditCardForm(creditCardData);		
//		
//		
//		validationSteps.checkTotalAmountFromUrl(url, String.valueOf(totals0Vat.getFinalPrice()));
		
		
	}




}

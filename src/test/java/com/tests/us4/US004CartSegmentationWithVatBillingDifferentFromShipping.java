package com.tests.us4;

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
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.CartWorkflows;

@WithTag(name = "US004", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US004CartSegmentationWithVatBillingDifferentFromShipping extends BaseTest {
	
	String username,password;
	ProductBasicModel productBasicModel = new ProductBasicModel();
	private CreditCardModel creditCardData = new CreditCardModel();
	private static CalcDetailsModel discountCalculationModel;
	private static List<ProductBasicModel> productsList25 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsList50 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsListMarketing = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> allProductsList = new ArrayList<ProductBasicModel>();
	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private static String jewelryDisount = "150";
	private static String marketingDisount = "300";
	List<CartProductModel> cartProds = new ArrayList<CartProductModel>();
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
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us4\\us004.properties");
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

		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);

}
	@Test
	public void cartSegmentationWithVatBillingDifferentFromShipping(){
		frontEndSteps.performLogin(username, password);
		ProductBasicModel productData;
	
		searchSteps.searchAndSelectProduct("R051GR", "LOLA VERDE RING");
		productData = productSteps.setProductAddToCart("2", "17");
		ProductBasicModel newProduct = productBasicModel.newProductObject(productData.getName(), productData.getPrice(), productData.getType(), "1");
		productsList25.add(newProduct);
		productsList50.add(newProduct);
		
		searchSteps.searchAndSelectProduct("R051SV", "ZOE STACKRING");
		productData = productSteps.setProductAddToCart("1", "16");
		productsList50.add(productData);
		
		searchSteps.searchAndSelectProduct("M101", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productData = productSteps.setProductAddToCart("2", "0");	
		productsListMarketing.add(productData);
		
		allProductsList.addAll(productsList25);
		allProductsList.addAll(productsList50);
		allProductsList.addAll(productsListMarketing);
		
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		
	

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
		
		cartProds.addAll(cartProductsWith50Discount);
		cartProds.addAll(cartProductsWith25Discount);
		cartProds.addAll(cartMarketingMaterialsProducts);
		System.out.println("size" + cartProds.size());
		
		cartSteps.typeJewerlyBonus(jewelryDisount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDisount);
		cartSteps.updateMarketingBonus();
		
		List<CartProductModel> calculatedProductsList =  CartCalculation.calculateProducts(cartProds, "150", "300");
		System.out.println("Buba de mai jos");
		PrintUtils.printList(calculatedProductsList);
		
		cartTotals = cartSteps.grabTotals();
		
	
		
		
		
		
	}
	
	@After
	public void saveData(){
		
	}
		

	
}
	


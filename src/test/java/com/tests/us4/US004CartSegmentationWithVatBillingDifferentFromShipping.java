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
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.Application;
import com.workflows.frontend.CartWorkflows;

@WithTag(name = "US004", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US004CartSegmentationWithVatBillingDifferentFromShipping extends BaseTest {
	
	String username,password;
	private CreditCardModel creditCardData = new CreditCardModel();
	private static List<ProductBasicModel> productsList25 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsList50 = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> productsListMarketing = new ArrayList<ProductBasicModel>();
	private static List<ProductBasicModel> allProductsList = new ArrayList<ProductBasicModel>();
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
		ProductBasicModel newProduct = newProductObject(productData.getName(), productData.getPrice(), productData.getType(), "1");
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
		
		
		System.out.println(productsList50.size());
		System.out.println(productsList50.get(0).getName());
		System.out.println(productsList50.get(0).getQuantity());
		System.out.println(productsList50.get(1).getName());
		System.out.println(productsList50.get(1).getQuantity());
		
		System.out.println("----------------------");
		System.out.println(productsList25.size());
		System.out.println(productsList25.get(0).getName());
		System.out.println(productsList25.get(0).getQuantity());
		System.out.println("----------------------");
		System.out.println(productsListMarketing.size());
		System.out.println(productsListMarketing.get(0).getName());
		System.out.println(productsListMarketing.get(0).getQuantity());
		
		System.out.println("----------------------");
		System.out.println(allProductsList.size());
		System.out.println(allProductsList.get(0).getName());
		System.out.println(allProductsList.get(0).getQuantity());
		System.out.println(allProductsList.get(1).getName());
		System.out.println(allProductsList.get(1).getQuantity());
		System.out.println(allProductsList.get(2).getName());
		System.out.println(allProductsList.get(2).getQuantity());
		System.out.println(allProductsList.get(3).getName());
		System.out.println(allProductsList.get(3).getQuantity());
		
	}
	
	@After
	public void saveData(){
		
	}
		
	//TODO remove this from test level
	public ProductBasicModel newProductObject(String name, String price, String type, String quantity){
			ProductBasicModel newProduct = new ProductBasicModel();
			newProduct.setName(name);
			newProduct.setPrice(price);
			newProduct.setQuantity(quantity);
			newProduct.setType(type);
			
			return newProduct;
		
	}
	
}
	


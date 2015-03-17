package com.tests.us8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.CreateProduct;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.calculation.RegularCartTotalsCalculation;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.regularUser.RegularUserCartCalculator;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US3006", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class CustomerBuyWithForthyDiscountsAndJbTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;

	private String username, password;
	private String discountClass;

	private CreditCardModel creditCardData = new CreditCardModel();
	public RegularCartCalcDetailsModel total = new RegularCartCalcDetailsModel();

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		genProduct1 = CreateProduct.createProductModel();
		genProduct1.setPrice("89.00");
		CreateProduct.createApiProduct(genProduct1);

		genProduct2 = CreateProduct.createProductModel();
		genProduct2.setPrice("49.90");
		CreateProduct.createApiProduct(genProduct2);

		genProduct3 = CreateProduct.createProductModel();
		genProduct3.setPrice("100.00");
		CreateProduct.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us8" + File.separator + "us8001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			discountClass = prop.getProperty("discountClass");

			creditCardData.setCardNumber(prop.getProperty("cardNumber"));
			creditCardData.setCardName(prop.getProperty("cardName"));
			creditCardData.setMonthExpiration(prop.getProperty("cardMonth"));
			creditCardData.setYearExpiration(prop.getProperty("cardYear"));
			creditCardData.setCvcNumber(prop.getProperty("cardCVC"));

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.CALC);
	}

	@Test
	public void customerCartTest() {
		customerRegistrationSteps.performLogin(username, password);
		customerRegistrationSteps.wipeRegularCart();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "4", "0");
		RegularUserCartCalculator.allProductsList.add(productData);	


		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.selectProductDiscountType(genProduct1.getSku(), Constants.JEWELRY_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsList, genProduct1.getSku(), Constants.JEWELRY_BONUS);
		regularUserCartSteps.selectProductDiscountType(genProduct2.getSku(), Constants.DISCOUNT_40_BONUS);
		regularUserCartSteps.updateProductList(RegularUserCartCalculator.allProductsList, genProduct2.getSku(), Constants.DISCOUNT_40_BONUS);
		
		RegularUserDataGrabber.productsList = regularUserCartSteps.grabProductsData();
		
		total = RegularCartTotalsCalculation.calculateTotals(RegularUserCartCalculator.allProductsList, discountClass);
		PrintUtils.printRegularCartCalcDetailsModel(total);
		
	
	}

}
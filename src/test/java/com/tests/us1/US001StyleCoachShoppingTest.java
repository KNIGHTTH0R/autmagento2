package com.tests.us1;

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

import com.steps.frontend.CartSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.ValidationSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;
import com.tools.data.ProductBasicModel;
import com.tools.requirements.Application;

@WithTag(name="US1", type = "US1")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001StyleCoachShoppingTest extends BaseTest{

	
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
	public ValidationSteps validationSteps;
	
	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
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
	}
	
	
	@Test
	public void uS001StyleCoachShoppingTest(){
		frontEndSteps.performLogin(username, password);
		
		ProductBasicModel productData = searchSteps.searchAndSelectProduct("M081", "BANNER MIT LOGO");
		productSteps.setProductAddToCart("1", "Blue");
		productsList.add(productData);
		productData = searchSteps.searchAndSelectProduct("M058", "GUTSCHEIN FOLGEPARTY ");
		productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		productData = searchSteps.searchAndSelectProduct("MAGIC VIOLETTA", "MAGIC VIOLETTA BRACELET");
		productSteps.setProductAddToCart("2", "0");
		productsList.add(productData);
		productData = searchSteps.searchAndSelectProduct("Rosemary Ring", "ROSEMARY RING");
		productSteps.setProductAddToCart("3", "18");
		productsList.add(productData);
		
		String previewPrice = headerSteps.openCartPreview();
		headerSteps.goToCart();
		
		System.out.println("Cart Preview Price: " + previewPrice);
		
		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		CartTotalsModel cartTotals = cartSteps.grabTotals();
		
		validationSteps.calculateCartProducts(cartProducts);
		
//		System.out.println("----------------> " + productData.getPrice());
	}
	
}

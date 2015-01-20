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
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tests.BaseTest;
import com.tools.Constants;
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
	
	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
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
	}
	
	@Test
	public void uS001StyleCoachShoppingTest(){
		frontEndSteps.performLogin("ioana.urcan@evozon.com", "ioana1234");
		ProductBasicModel productData = searchSteps.searchAndSelectProduct("B056RS", "ALEXA BRACELET");
		productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		ProductBasicModel productData1 = searchSteps.searchAndSelectProduct("B071BK", "IVY BRACELET (BLACK)");
		productSteps.setProductAddToCart("2", "0");
		productsList.add(productData1);	
		ProductBasicModel productData2 = searchSteps.searchAndSelectProduct("R083BK", "CLARICE RING (GUN METAL)");
		productSteps.setProductAddToCart("1", "18");
		productsList.add(productData2);	
		ProductBasicModel productData3 = searchSteps.searchAndSelectProduct("M064", "SCHMUCKBROSCHÜRE (40 STK.)");
		productSteps.setProductAddToCart("1", "0");
		productsList.add(productData3);	
		ProductBasicModel productData4 = searchSteps.searchAndSelectProduct("M101", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productSteps.setProductAddToCart("1", "0");
		productsList.add(productData4);	
		
	}
	
	
	
	
}

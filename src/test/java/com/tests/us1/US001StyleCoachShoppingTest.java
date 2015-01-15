package com.tests.us1;

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

import com.steps.FrontEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.ProductBasicModel;
import com.tools.requirements.Application;

@WithTag(name="US1", type = "US1")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001StyleCoachShoppingTest extends BaseTest{

	
	@Steps
	public FrontEndSteps frontEndSteps;
	
	
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
		frontEndSteps.searchProduct("Rosemary Ring");
		ProductBasicModel productData  = frontEndSteps.findProduct("ROSEMARY RING");
		
		System.out.println("----------------> " + productData.getPrice());
	}
	
}

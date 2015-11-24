package com.tests.us8.us8004;

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

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.tests.BaseTest;
import com.tools.env.constants.Separators;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US8.4 Customer Buy With Kobo", type = "Scenarios")
@Story(Application.RegularCart.US8_4.class)
@RunWith(ThucydidesRunner.class)
public class US8004ChangeBackCustomersContextTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public FooterSteps footerSteps;

	private String context;
	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8004.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			context = prop.getProperty("context");

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
	public void us8004ChangeBackCustomersContextTest() {
		customerRegistrationSteps.performLoginUnderContext(username, password, Separators.SLASH + context);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickChangeShop();

	}

}

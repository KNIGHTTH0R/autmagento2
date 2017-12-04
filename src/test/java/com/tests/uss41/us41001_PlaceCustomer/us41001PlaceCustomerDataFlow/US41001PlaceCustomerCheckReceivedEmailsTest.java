package com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerDataFlow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tests.uss41.us41001_PlaceCustomer.US41001OrderOnlyZzzProductsForCustomerTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US41001PlaceCustomerCheckReceivedEmailsTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail;
	private OrderModel orderModel;
	@Before
	public void setUp() throws Exception {
		
		orderModel = MongoReader.getOrderModel("US41001OrderOnlyZzzProductsForCustomerTest").get(0);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_41_FOLDER + File.separator + "us41001PlaceCust.properties");
			prop.load(input);

			stylistEmail = prop.getProperty("username");
		

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
	public void us41001PlaceCustomerCheckReceivedEmailsTest() {
	//	emailClientSteps.checkReceivedOriginalDocuments(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),"Related orde",orderModel.getOrderId());
		emailClientSteps.validateThatEmailIsReceived(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""), "Versand");
	}

}

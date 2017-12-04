package com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerNavTab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US41001SylistCheckRecivedDocumentsMailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private OrderModel orderModel;
	private String username;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.getOrderModel("US41001OrderOnlyZzzProductsForCustomerTest").get(0);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss41" + File.separator + "us41001PlaceCust.properties");
			prop.load(input);
			username = prop.getProperty("username");

			

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
	public void us41001ContactCheckReciveDocumentsMailTest() {
	/*	emailClientSteps.checkReceivedOriginalDocuments(username.replace("@" + ConfigConstants.WEB_MAIL, ""),
				"Related orde", orderModel.getOrderId());*/
	}

}

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
public class US41001ContactCheckRecivedDocumentsMailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private OrderModel orderModel;
	private String contactEmail;

	@Before
	public void setUp() throws Exception {

		orderModel = MongoReader.getOrderModel("US41001OrderOnlyZzzProductsForCustomerTest").get(0);

		contactEmail = MongoReader.grabCustomerFormModels("US41001OrderOnlyZzzProductsForCustomerTest").get(0)
				.getEmailName();
		System.out.println(contactEmail);
	}

	@Test
	public void us41001ContactCheckRecivedDocumentsMailTest() {
	/*	emailClientSteps.checkReceivedOriginalDocuments(contactEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),
				"Related orde", orderModel.getOrderId());*/
	}

}

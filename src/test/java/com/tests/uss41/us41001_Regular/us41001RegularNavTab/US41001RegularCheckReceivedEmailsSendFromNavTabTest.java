package com.tests.uss41.us41001_Regular.us41001RegularNavTab;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US7.1 Regular Customer Registration on Master Test ", type = "Scenarios")
@Story(Application.CustomerRegistration.US7_1.class)
@RunWith(SerenityRunner.class)
public class US41001RegularCheckReceivedEmailsSendFromNavTabTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String stylistEmail,orderId;


	@Before
	public void setUp() throws Exception {

		// int size =
		// MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTest").size();
		// if (size > 0) {
		// stylistEmail =
		// MongoReader.grabCustomerFormModels("US7001RegularCustRegOnMasterTest").get(0).getEmailName();
		// } else
		// System.out.println("The database has no entries");

		List<OrderModel> orderModelList = MongoReader
				.getOrderModel("US41001CustomerBuyWithForthyDiscountsAndJbTest" + SoapKeys.GRAB);

		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}
		stylistEmail = "docreceiver@geronra.com";
	}

	@Test
	public void us41001RegularCheckReceivedEmailsSendFromNavTabTest() {
		emailClientSteps.openEmail(stylistEmail.replace("@" + ConfigConstants.WEB_MAIL, ""),
				"Related orde");
		emailClientSteps.checkReceivedOriginalDocuments("INVOICE_" + orderId);
	}
	
	

}

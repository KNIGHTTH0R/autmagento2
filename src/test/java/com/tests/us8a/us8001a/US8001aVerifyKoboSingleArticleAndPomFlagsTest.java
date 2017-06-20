package com.tests.us8a.us8001a;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.OrderInfoMagCalls;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.backend.OrderModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US34.1 Order Import to Navision", type = "Scenarios")
@Story(Application.ValidateOrderImport.US34_1.class)

@RunWith(SerenityRunner.class)
public class US8001aVerifyKoboSingleArticleAndPomFlagsTest extends BaseTest {

	@Steps
	public RegularCartValidationWorkflows regularCartValidationWorkflows;

	@Steps
	CustomVerification customVerification;

	
	
	DBOrderModel model =new DBOrderModel();
	private String orderId;
	@Before
	public void setUp() throws Exception {
		
		List<OrderModel> orderModelList = MongoReader.getOrderModel("US8001aCustomerBuyWithContactBoosterTest" + SoapKeys.GRAB);
		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}
		model=OrderInfoMagCalls.getOrderInfo(orderId);

		}

	@Test
	public void us8001aVerifyKoboSingleArticleAndPomFlagsTest() throws Exception {
		System.out.println("(model.getKoboSingleArticle() "+model.getKoboSingleArticle());
		System.out.println("model.isPom() "+model.getIsPom());
		regularCartValidationWorkflows.verifyKoboSingleArticle(model.getKoboSingleArticle(), "1");
		regularCartValidationWorkflows.verifyIsPom(model.getIsPom(), "1");
		customVerification.printErrors();
	}
}

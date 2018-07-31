package com.tests.uss35.us35001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.RmaSteps;
import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ReturnProductModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.ValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;


@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US3001ReturnProductsPartialQtyVDV extends BaseTest{

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps
	public ProfileNavSteps profileNavSteps;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public RmaSteps rmaSteps;
	@Steps
	public ValidationWorkflows validationWorkflows;
	
	private String orderId;
	private String username, password;
	private static OrderModel orderModel = new OrderModel();
	private static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	private static List<BasicProductModel> recalculatedProductsList = new ArrayList<BasicProductModel>();
	private static List<ReturnProductModel> returnProductList = new ArrayList<ReturnProductModel>();
	private static List<ReturnProductModel> returnProductListGrabbed = new ArrayList<ReturnProductModel>();


	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;
		
		List<OrderModel> orderModelList = MongoReader.getOrderModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.GRAB);
		productsList = MongoReader.grabBasicProductModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.GRAB);
		recalculatedProductsList= MongoReader.grabBasicProductModel("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.RECALC);
		if (orderModelList.size() == 1) {

			orderId = orderModelList.get(0).getOrderId();
		} else {
			Assert.assertTrue("Failure: Could not retrieve orderId. ", orderModelList.size() == 1);
		}
		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
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

		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName());
		orderModel = MongoReader.grabOrderModels("US3001SfmOrderWithNoDiscountTestVDV" + SoapKeys.GRAB).get(0);
	}
	
	
	@Test
	public void us3001ReturnProductsPartialQtyVDV() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}	
		headerSteps.goToProfile();
		profileNavSteps.selectMenu(ContextConstants.MEINE_BESTELLUNGEN);
		profileSteps.clickOnOrder(orderModel.getOrderId());
		profileSteps.clickOnReturnLink();
		
		
		ReturnProductModel returnedProduct;
		
		profileSteps.returnProduct(recalculatedProductsList.get(0),"1","0");
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(0), "1", ConfigConstants.PENDING_STATUS);
		returnProductList.add(returnedProduct);
		
		profileSteps.addAnotherArtickle();
	
		profileSteps.returnProduct(recalculatedProductsList.get(1),"1","1");
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(1), "1", ConfigConstants.PENDING_STATUS);
		returnProductList.add(returnedProduct);	
		
		profileSteps.submitReturn();
		profileSteps.selectNewestCreatedRMA();
	
		returnProductListGrabbed=profileSteps.grabReturnItemsDetails();
		
		validationWorkflows.performReturnProductsValidations(returnProductList, returnProductListGrabbed,"BEFORE AUTHORIZED");
		/////////////////////////////////////////
		frontEndSteps.openNewTab();
		frontEndSteps.switchToNewestOpenedTab();
		
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnRMA();
		backEndSteps.searchRMAByOrderId(orderId);
		backEndSteps.openRMADetails(orderId);
		rmaSteps.selectMenu(ConfigConstants.RMA_ITEMS);
		rmaSteps.authorizedQty(productsList.get(0).getProdCode(),"1",ConfigConstants.AUTHORIZED_STATUS);
		rmaSteps.authorizedQty(productsList.get(1).getProdCode(),"1",ConfigConstants.AUTHORIZED_STATUS);
		rmaSteps.saveAndContinue();
		
		frontEndSteps.switchBackToFirstTab();
		frontEndSteps.refresh();
		
		
		returnProductList.clear();
		
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(0), "1", ConfigConstants.AUTHORIZED_STATUS);
		returnProductList.add(returnedProduct);
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(1), "1", ConfigConstants.AUTHORIZED_STATUS);
		returnProductList.add(returnedProduct);
		
		returnProductListGrabbed=profileSteps.grabReturnItemsDetails();
		validationWorkflows.performReturnProductsValidations(returnProductList, returnProductListGrabbed,"AFTER AUTHORIZED");

		
		frontEndSteps.switchToNewestOpenedTab();
		
		backEndSteps.clickOnRMA();
		backEndSteps.searchRMAByOrderId(orderId);
		backEndSteps.openRMADetails(orderId);
		rmaSteps.selectMenu(ConfigConstants.RMA_ITEMS);
		rmaSteps.receivedQty(productsList.get(0).getProdCode(),"1",ConfigConstants.RETURN_RECEIVED_STATUS);
		rmaSteps.receivedQty(productsList.get(1).getProdCode(),"1",ConfigConstants.RETURN_RECEIVED_STATUS);
		rmaSteps.saveAndContinue();
		
		frontEndSteps.switchBackToFirstTab();
		frontEndSteps.refresh();
		
		
		returnProductList.clear();
		
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(0), "1", ConfigConstants.RETURN_RECEIVED_STATUS);
		returnedProduct.setReturnedQty("1");
		returnProductList.add(returnedProduct);
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(1), "1", ConfigConstants.RETURN_RECEIVED_STATUS);
		returnedProduct.setReturnedQty("1");
		returnProductList.add(returnedProduct);
		
		returnProductListGrabbed=profileSteps.grabReturnItemsDetails();
		validationWorkflows.performReturnProductsValidations(returnProductList, returnProductListGrabbed,"AFTER RETURN QTY");
		
	
		
		frontEndSteps.switchToNewestOpenedTab();
		backEndSteps.clickOnRMA();
		backEndSteps.searchRMAByOrderId(orderId);
		backEndSteps.openRMADetails(orderId);
		rmaSteps.selectMenu(ConfigConstants.RMA_ITEMS);
		rmaSteps.approvedQty(productsList.get(0).getProdCode(),"1",ConfigConstants.APPROVED_STATUS);
		rmaSteps.approvedQty(productsList.get(1).getProdCode(),"1",ConfigConstants.APPROVED_STATUS);
		rmaSteps.saveAndContinue();
		
		frontEndSteps.switchBackToFirstTab();
		frontEndSteps.refresh();
		
		returnProductList.clear();
		
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(0), "1", ConfigConstants.APPROVED_STATUS);
		returnedProduct.setReturnedQty("1");
		returnProductList.add(returnedProduct);
		returnedProduct=profileSteps.populateDeatailsForReturnedProducts(recalculatedProductsList.get(1), "1", ConfigConstants.APPROVED_STATUS);
		returnedProduct.setReturnedQty("1");
		returnProductList.add(returnedProduct);
		
		returnProductListGrabbed=profileSteps.grabReturnItemsDetails();
		validationWorkflows.performReturnProductsValidations(returnProductList, returnProductListGrabbed,"AFTER RETURN QTY");
		
		//	profileSteps.checkSuccessRefundMessage("");

		profileSteps.waitABit(5000);
		customVerifications.printErrors();
	}
}

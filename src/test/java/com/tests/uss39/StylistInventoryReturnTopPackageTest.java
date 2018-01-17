package com.tests.uss39;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.connectors.http.OrderInfoMagCalls;
import com.connectors.http.StylistInventoryUpdateReturnCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.reports.StylistInventorySteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.StylistInventoryModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.StylistInvetoryUpdateModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US39.1 Stylist inventory borrow", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class StylistInventoryReturnTopPackageTest extends BaseTest {

	@Steps
	CustomVerification customVerification;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public StylistInventorySteps stylistInventorySteps;
	@Steps
	public CustomerFormModel stylistData;

	private String username, password;
	private String orderIncrementId;
	private String customerId;
	
	List<NavOrderLinesModel> billOfMaterial = new ArrayList<NavOrderLinesModel>();
	List<StylistInventoryModel> productsData = new ArrayList<StylistInventoryModel>();

	@Before
	public void setUp() throws Exception {

		stylistData = MongoReader.grabCustomerFormModels("US16003StyleCoachRegistrationTest").get(0);
		
		username = stylistData.getEmailName();
		password = stylistData.getPassword();
		
		orderIncrementId = MongoReader.grabOrderModels("US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest" +  SoapKeys.GRAB).get(0).getOrderId();
		DBOrderModel dbmodel = OrderInfoMagCalls.getOrderInfo(orderIncrementId);
		customerId= dbmodel.getCustomerId();
		billOfMaterial = NavisionBillOfMaterialCalls.getItemsList("xxxx");

		
		for (NavOrderLinesModel navOrderLinesModel : billOfMaterial) {
			String[] sku = navOrderLinesModel.getNo().toString().split("-");
			if (navOrderLinesModel.toString().contains("-")) {
				StylistInvetoryUpdateModel model = StylistInventoryUpdateReturnCalls.getReturnUpdateInfo(customerId,
						orderIncrementId, sku[0], sku[1],"xxxx");
				System.out.println("model: " + model.getResult());
				CustomVerification.verifyTrue("Failure:The result is not as expected", model.getResult().contentEquals("1"));
				
			} else {
				StylistInvetoryUpdateModel model = StylistInventoryUpdateReturnCalls.getReturnUpdateInfo(customerId,
						orderIncrementId, sku[0], "","xxxx");
				CustomVerification.verifyTrue("Failure:The result is not as expected", model.getResult().contentEquals("1"));
				
			}
		}

	}

	@Test
	public void stylistInventoryReturnTopPackageTest() throws Exception {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickGoToStylistInventory();
		stylistInventorySteps.openReturnSection();
		stylistInventorySteps.validateReturnedProducts(billOfMaterial, stylistInventorySteps.grabProductsData(),"xxxx");
		customVerification.printErrors();
	}

}

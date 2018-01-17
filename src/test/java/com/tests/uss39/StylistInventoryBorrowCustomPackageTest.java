package com.tests.uss39;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.NavisionBillOfMaterialCalls;
import com.connectors.http.OrderInfoMagCalls;
import com.connectors.http.StylistInventoryUpdateBorrowCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.reports.StylistInventorySteps;
import com.tests.BaseTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest;
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
public class StylistInventoryBorrowCustomPackageTest extends BaseTest {


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
		
		orderIncrementId = MongoReader.grabOrderModels("US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest" +  SoapKeys.GRAB).get(0).getOrderId();
		DBOrderModel dbmodel = OrderInfoMagCalls.getOrderInfo(orderIncrementId);
		customerId= dbmodel.getCustomerId();
		
		
		
		
		billOfMaterial = NavisionBillOfMaterialCalls.getItemsList("yyy");
		
		
		for (NavOrderLinesModel navOrderLinesModel : billOfMaterial) {
			String[] sku = navOrderLinesModel.getNo().toString().split("-");
			if (navOrderLinesModel.toString().contains("-")) {

				StylistInvetoryUpdateModel model = StylistInventoryUpdateBorrowCalls.getBorrowUpdateInfo(customerId,
						orderIncrementId, sku[0], sku[1],"yyyy");
				System.out.println("model: " + model.getResult());
				if (!model.getResult().contentEquals("1")) {
					System.out.println("ASSSSSSSSERRRRRRRRRRRRRRRRT");
				}
			} else {
				StylistInvetoryUpdateModel model = StylistInventoryUpdateBorrowCalls.getBorrowUpdateInfo(customerId,
						orderIncrementId, sku[0], "","yyyy");
				if (!model.getResult().contentEquals("1")) {
					System.out.println("ASSSSSSSSERRRRRRRRRRRRRRRRT");
				}
			}
		}

	}

	@Test
	public void stylistInventoryBorrowCustomPackageTest() throws Exception {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickGoToStylistInventory();
		stylistInventorySteps.openBorrowSection();
		stylistInventorySteps.validateBorrowedProducts(billOfMaterial, stylistInventorySteps.grabProductsData(),"yyyy");
		customVerification.printErrors();
	}

}

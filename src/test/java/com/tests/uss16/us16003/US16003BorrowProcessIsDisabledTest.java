package com.tests.uss16.us16003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.borrow.BorrowSystemConfigurationSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

@WithTag(name = "US16.1 SC borrow products Test", type = "Scenarios")
@Story(Application.BorrowCart.US16_1.class)
@RunWith(SerenityRunner.class)
public class US16003BorrowProcessIsDisabledTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public BorrowCartSteps borrowCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public BorrowCartValidationWorkflows borrowCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BorrowSystemConfigurationSteps borrowSystemConfigurationSteps;
	
	
	private String username, password;

	@Before
	public void setUp() throws Exception {
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();
		
//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setPrice("49.90");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createProductModel();
//		genProduct2.setPrice("89.00");
//		MagentoProductCalls.createApiProduct(genProduct2);
		
//		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
//			
//		genProduct1 = createdProductsList.get(0);
//		genProduct2 = createdProductsList.get(1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_16_FOLDER + File.separator + "us16001.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
	}

	@Test
	public void us16003BorrowProcessIsDisabledTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		borrowSystemConfigurationSteps.goToBorrowTab();
		borrowSystemConfigurationSteps.selectDisabledBorrowOption("Ja");
		borrowSystemConfigurationSteps.saveConfiguration();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("urcanioanaemilia@gmail.com");
		backEndSteps.openCustomerDetails("urcanioanaemilia@gmail.com");
		backEndSteps.selectAllowedToBorrow("Ja");
		
		
		
		customerRegistrationSteps.performLogin("urcanioanaemilia@gmail.com", "q1w2e3");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.checkIfBorrowLinkIsDisplayed(false);
	}
}
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
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.ShopSteps;
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
public class US16003BorrowFunctionalityForNotAllowedStylistTest extends BaseTest {

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
	@Steps
	public MyBusinessSteps myBusinessSteps;
	@Steps
	public ShopSteps shopSteps;
	
	
	
	
	private String username, password;
	private static String billingAddress;
	private static String shippingValue;
	private static String taxClass;
	private CreditCardModel creditCardData = new CreditCardModel();

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();



	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void us16003BorrowFunctionalityForNotAllowedStylistTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		borrowSystemConfigurationSteps.goToBorrowTab();
		borrowSystemConfigurationSteps.selectDisabledBorrowOption("Nein");
		borrowSystemConfigurationSteps.selectBorrowProcessType("(New process) Allow defined products to be borrowed");
		borrowSystemConfigurationSteps.selectCountries();
		borrowSystemConfigurationSteps.selectProductsForStylistwithExtendedOption();
		borrowSystemConfigurationSteps.saveConfiguration();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("urcanioanaemilia@gmail.com");
		backEndSteps.openCustomerDetails("urcanioanaemilia@gmail.com");
		backEndSteps.selectAllowedToBorrow("Nein");
		
		
		
		customerRegistrationSteps.performLogin("urcanioanaemilia@gmail.com", "q1w2e3");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		
		loungeSteps.checkIfBorrowLinkIsDisplayed(true);
		loungeSteps.checkIfBorrowBoxIsdisplayed(true);
		loungeSteps.goToMyBusiness();
		myBusinessSteps.checkIfBorrowCartLinkIsDisplayed(true);
		headerSteps.goToShop();
		shopSteps.checkIfBorrowLinkIsdisplayed(true);
		
	}

//	@After
//	public void saveData() {
//
//		MongoWriter.saveBorrowCartCalcDetailsModel(BorrowCartCalculator.borrowCartCalcDetailsModel, getClass().getSimpleName() + SoapKeys.CALC);
//		MongoWriter.saveShippingModel(BorrowCartCalculator.shippingCalculatedModel, getClass().getSimpleName() + SoapKeys.CALC);
//		MongoWriter.saveShippingModel(BorrowDataGrabber.borrowCartConfirmationTotals, getClass().getSimpleName() + SoapKeys.GRAB);
//		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
//		MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
//		for (BorrowProductModel product : BorrowCartCalculator.allBorrowedProductsList) {
//			MongoWriter.saveBorrowProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
//		}
//	}
}
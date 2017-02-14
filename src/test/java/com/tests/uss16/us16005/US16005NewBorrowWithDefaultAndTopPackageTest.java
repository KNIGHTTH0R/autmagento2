package com.tests.uss16.us16005;

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
import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.MyContactsListSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.ShopSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.Credentials;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
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
public class US16005NewBorrowWithDefaultAndTopPackageTest extends BaseTest {

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
	@Steps
	MyContactsListSteps myContactsListSteps;
	@Steps
	ContactDetailsSteps contactDetailsSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;

	private String username, password, contact, customerData;

	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	private static UrlModel urlModel = new UrlModel();
	private static List<RegularBasicProductModel> productsWishList = new ArrayList<RegularBasicProductModel>();
	private static List<RegularBasicProductModel> grabbedProductsWishList = new ArrayList<RegularBasicProductModel>();

	@Before
	public void setUp() throws Exception {

		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();

		productsWishList = MongoReader.grabRegularBasicProductModel("US16006RegularCustomerSetProductsInCartAndWishlistTest" + "WISH");
		urlModel = MongoReader.grabUrlModels("US16006CreatePartyTest" + SoapKeys.GRAB).get(0);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_16_FOLDER + File.separator + "us16006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			contact = prop.getProperty("contact");
			customerData = prop.getProperty("customerUsername");

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
	public void us16005NewBorrowWithDefaultAndTopPackageTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		borrowSystemConfigurationSteps.goToBorrowTab();
		borrowSystemConfigurationSteps.selectDisabledBorrowOption("Nein");
		borrowSystemConfigurationSteps.selectBorrowProcessType("(New process) Allow defined products to be borrowed");
		borrowSystemConfigurationSteps.selectCountries();
		borrowSystemConfigurationSteps.selectProductsForStylistwithExtendedOption();
		borrowSystemConfigurationSteps.saveConfiguration();
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(username);
		backEndSteps.openCustomerDetails(username);
		backEndSteps.selectTopStatus(ContextConstants.USE_DEFAULT);
		backEndSteps.selectAllowedToBorrow(ContextConstants.TOP_PACKAGE);

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		// check if borrow cart link is displayed on Lounge-My bussiness
		loungeSteps.checkIfBorrowLinkIsDisplayed(true);
		// check if borrow cart link is displayed on My Bussiness page
		loungeSteps.checkIfBorrowBoxIsdisplayed(true);
		//loungeSteps.verifyBorrowBlockStatus(ContextConstants.NOT_ELIGIBLE);!!!!!!!!!!!!!trebuie returnate produsele

		loungeSteps.goToMyBusiness();
		myBusinessSteps.checkIfBorrowCartLinkIsDisplayed(true);
		// check if borrow cart link is displayed on Shop-My Bussiness
		headerSteps.goToLounge();
		headerSteps.goToShop();
		shopSteps.checkIfBorrowLinkIsdisplayed(true);

		// go to cart check the message
		headerSteps.goToLounge();
		loungeSteps.goToMyBusiness();
		loungeSteps.clickGoToBorrowCart();
		//borrowCartSteps.verifyErrorMessageInCart(ContextConstants.NOT_ELIGIBLE_MESSAGE);!!!!!!trebuie returnate produsele
		borrowCartSteps.checkBorrowCartForNewFunctionality(false, false);
		
		// check contact details pages
		headerSteps.goToLounge();
		loungeSteps.goToMyBusiness();
		loungeSteps.goToContactsList();
		myContactsListSteps.openContactDetailsPage(customerData);

		contactDetailsSteps.checkBlockLinesForContacts();

		grabbedProductsWishList = contactDetailsSteps.grabWishlistItems();
		contactDetailsSteps.validateWishlistCounter(productsWishList.size());
		contactDetailsSteps.validateProductWishListBlock(productsWishList, grabbedProductsWishList);

		contactDetailsSteps.clickBackToContactsbutton();
		myContactsListSteps.openContactDetailsPage(contact);
		contactDetailsSteps.checkBlockLinesForContacts();

		// party
		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.checkIfAddToBorrowCartButtonIsDisplayed(false);

		// check if products are dispalyed

		partyDetailsSteps.checkWishlistSection(productsWishList);

	}

	// @After
	// public void saveData() {
	//
	// MongoWriter.saveBorrowCartCalcDetailsModel(BorrowCartCalculator.borrowCartCalcDetailsModel,
	// getClass().getSimpleName() + SoapKeys.CALC);
	// MongoWriter.saveShippingModel(BorrowCartCalculator.shippingCalculatedModel,
	// getClass().getSimpleName() + SoapKeys.CALC);
	// MongoWriter.saveShippingModel(BorrowDataGrabber.borrowCartConfirmationTotals,
	// getClass().getSimpleName() + SoapKeys.GRAB);
	// MongoWriter.saveOrderModel(DataGrabber.orderModel,
	// getClass().getSimpleName() + SoapKeys.GRAB);
	// MongoWriter.saveUrlModel(DataGrabber.urlModel, getClass().getSimpleName()
	// + SoapKeys.GRAB);
	// for (BorrowProductModel product :
	// BorrowCartCalculator.allBorrowedProductsList) {
	// MongoWriter.saveBorrowProductModel(product, getClass().getSimpleName() +
	// SoapKeys.GRAB);
	// }
	// }
}
package com.tests.uss24;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.AddressWorkflows;

@WithTag(name = "US24.1 Check plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "resources/validPlzTestData.csv")
public class US24001ShopForMyselfPlzValidationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private AddressModel addressModel;
	private CreditCardModel creditCardData = new CreditCardModel();
	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		addressModel = new AddressModel();
		addressModel.setPostCode(plz);

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3004.properties");
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

	}

	@Test
	public void us24001ShopForMyselfPlzValidationTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.goToShipping();

		shippingSteps.addNewAddressForBilling(addressModel);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.goToPaymentMethod();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		AddressModel checkoutStepTwoBillAddress = confirmationSteps.grabBillingData();
		AddressModel checkoutStepTwoShipAddress = confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		AddressWorkflows.setBillingPlz(addressModel.getPostCode(), checkoutStepTwoBillAddress);
		AddressWorkflows.validateBillingPostcode();

		AddressWorkflows.setShippingPlz(addressModel.getPostCode(), checkoutStepTwoShipAddress);
		AddressWorkflows.validateShippingPostcode();

		checkoutValidationSteps.verifySuccessMessage();

		customVerifications.printErrors();

	}

}

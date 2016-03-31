package com.tests.uss25;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.ShippingStepsWithCsvStepsWithCsv;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.borrowCart.BorrowCartCalculator;
import com.tools.data.frontend.AddressModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.env.constants.FilePaths;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;
import com.workflows.frontend.borrowCart.BorrowCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(SerenityRunner.class)
public class US25001BorrowCartPlzValidationTest extends BaseTest {

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
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ShippingStepsWithCsvStepsWithCsv shippingStepsWithCsvStepsWithCsv;
	@Steps
	public BorrowCartValidationWorkflows borrowCartValidationWorkflows;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private AddressModel addressModel;
	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		BorrowCartCalculator.wipe();
		BorrowDataGrabber.wipe();
		DataGrabber.wipe();

		addressModel = new AddressModel();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);

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
	}

	@Test
	public void us25001BorrowCartPlzValidationTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		loungeSteps.clickGoToBorrowCart();
		borrowCartSteps.clickWipeCart();

		addBorrowedProductsWorkflow.setBorrowedDefaultProductToCart();
		addBorrowedProductsWorkflow.setBorrowedProductToCart(genProduct1, "0.00");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		borrowCartSteps.clickGoToShipping();

		shippingSteps.addNewAddressForBillingWithoutPlz(addressModel);

//		try {
//			withTestDataFrom("resources/invalidPlzTestData.csv").run(shippingStepsWithCsvStepsWithCsv).inputPostCodeCsv();
//		} catch (IOException e) {
//			e.printStackTrace();
//			Assert.fail("Failed !!!");
//		}
	}

}
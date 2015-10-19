package com.tests.uss15.us15004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.steps.frontend.reports.JewelryBonusHistorySteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.partyHost.HostCartCalculator;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

@WithTags({ @WithTag(name = "US15.4 Validate Zzz Product JB for all order states", type = "Scenarios"),
	@WithTag(name = "US15.4 Check place a customer order details in mailchimp", type = "Scenarios") })
@Story(Application.Newsletter.US15_4.class)
@RunWith(ThucydidesRunner.class)
public class US15004OrderZzzProductsForCustomerTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public JewelryBonusHistorySteps jewelryBonusHistorySteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String username, password;
	private String discountClass;
	private String shippingValue;
	private String voucherValue;
	private DateModel dateModel = new DateModel();
	private JewelryHistoryModel expectedJewelryHistoryModelWhenOrderComplete = new JewelryHistoryModel();
	private JewelryHistoryModel expectedJewelryHistoryModelWhenOrderCancelled = new JewelryHistoryModel();
	private CustomerFormModel customerData;
	private AddressModel addressData;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

		customerData = new CustomerFormModel();
		addressData = new AddressModel();

		genProduct1 = ApiCalls.createZzzProductModel();
		genProduct1.setPrice("89.00");
		genProduct1.setIp("75");
		ApiCalls.createJbZzzApiProduct(genProduct1);

		genProduct2 = ApiCalls.createProductModel();
		genProduct2.setPrice("49.90");
		genProduct2.setIp("42");
		ApiCalls.createApiProduct(genProduct2);

		genProduct3 = ApiCalls.createProductModel();
		genProduct3.setPrice("99.00");
		genProduct3.setIp("84");
		ApiCalls.createApiProduct(genProduct3);
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15004.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			shippingValue = prop.getProperty("shippingValue");
			voucherValue = prop.getProperty("voucherValue");

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

		MongoConnector.cleanCollection(getClass().getSimpleName());
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.COMPLETE);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CANCELED);

	}

	@Test
	public void us15004OrderZzzProductsForCustomerTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();

		String currentTotal = dashboardSteps.getJewelryBonus();

		expectedJewelryHistoryModelWhenOrderComplete = dashboardSteps.calculateExpectedJewelryConfiguration(currentTotal, genProduct1.getJewerlyBonusValue(), true);
		expectedJewelryHistoryModelWhenOrderCancelled = dashboardSteps.calculateExpectedJewelryConfiguration(currentTotal, genProduct1.getJewerlyBonusValue(), false);

		loungeSteps.orderForNewCustomer();
		createNewContactSteps.fillCreateNewContactDirectly(customerData, addressData);
		customerRegistrationSteps.wipeHostCart();
		HostBasicProductModel productData;

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct1, "1", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct2, "2", "0");
		HostCartCalculator.allProductsList.add(productData);
		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct3, "4", "0");
		HostCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		HostCartCalculator.calculateCartBuy3Get1OrderForCustomerCartAndShippingTotals(discountClass, shippingValue, voucherValue);

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingPartySectionSteps.checkItemNotReceivedYet();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		HostDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		HostDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		if (MongoReader.getContext().contentEquals("de")) {
			paymentSteps.payWithBankTransfer();
		} else {
			paymentSteps.payWithBankTransferEs();
		}

		confirmationSteps.agreeAndCheckout();
		dateModel.setDate(DateUtils.getCurrentDate("MM/dd/YYYY"));

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerData, getClass().getSimpleName());
		MongoWriter.saveOrderModel(HostDataGrabber.orderModel, getClass().getSimpleName());
		MongoWriter.saveShippingModel(HostCartCalculator.shippingCalculatedModel, getClass().getSimpleName());
		MongoWriter.saveUrlModel(HostDataGrabber.urlModel, getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());
		MongoWriter.saveJewerlyHistoryModel(expectedJewelryHistoryModelWhenOrderComplete, getClass().getSimpleName() + SoapKeys.COMPLETE);
		MongoWriter.saveJewerlyHistoryModel(expectedJewelryHistoryModelWhenOrderCancelled, getClass().getSimpleName() + SoapKeys.CANCELED);
		for (HostBasicProductModel product : HostCartCalculator.allProductsList) {
			MongoWriter.saveHostBasicProductModel(product, getClass().getSimpleName());
		}
	}

}

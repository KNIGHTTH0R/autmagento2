package com.tests.uss23;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.navision.SyncInfoModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.generalCalculation.StockCalculations;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001PlaceTermPurchaseOrderTest extends BaseTest {

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
	public GeneralCartSteps generalCartSteps;
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
	public DashboardSteps dashboardSteps;

	private String username, password;
	private CustomerFormModel customerData;
	private AddressModel addressData;
	private String qtyForBundle;
	private CreditCardModel creditCardData = new CreditCardModel();
	private OrderModel orderModel = new OrderModel();
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private static List<String> changingStockIdList = new ArrayList<String>(Arrays.asList("1292", "1658", "2558", "1872", "2552"));

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();
		addressData = new AddressModel();

		for (String id : changingStockIdList) {
			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
		}

		qtyForBundle = BigDecimal.valueOf(Double.parseDouble(changingStockMagentoProducts.get(3).getQuantity())).compareTo(
				BigDecimal.valueOf(Double.parseDouble(changingStockMagentoProducts.get(4).getQuantity()))) > 0 ? changingStockMagentoProducts.get(4).getQuantity()
				: changingStockMagentoProducts.get(3).getQuantity();

		boughtProductsQuantities.add(StockCalculations.determineQuantity(changingStockMagentoProducts.get(0).getQuantity()));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(changingStockMagentoProducts.get(1).getQuantity()));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(changingStockMagentoProducts.get(2).getQuantity()));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(qtyForBundle));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(qtyForBundle));

		System.out.println(qtyForBundle);

		genProduct1.setName("LIQUID MOON SMALL");
		genProduct1.setSku("R065SV");
		genProduct2.setName("MARY NECKLACE");
		genProduct2.setSku("N093SV");
		genProduct3.setName("BIANCA MIT BALLCHAIN 45 CM");
		genProduct3.setSku("N052NL");
		genProduct4.setName("FUNKY SOLITAIRE SET");
		genProduct4.setSku("K091MC");

		Properties prop = new Properties();
		InputStream input = null;

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

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us23001PlaceTermPurchaseOrderTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();

		loungeSteps.orderForNewCustomer();
		createNewContactSteps.fillCreateNewContactDirectly(customerData, addressData);
		generalCartSteps.clearCart();

		System.out.println(changingStockMagentoProducts.get(0).getQuantity());
		System.out.println(changingStockMagentoProducts.get(1).getQuantity());
		System.out.println(changingStockMagentoProducts.get(2).getQuantity());
		System.out.println(changingStockMagentoProducts.get(3).getQuantity());
		System.out.println(changingStockMagentoProducts.get(4).getQuantity());

		addProductsForCustomerWorkflow.addProductToCart(genProduct1, boughtProductsQuantities.get(0), "18");
		addProductsForCustomerWorkflow.addProductToCart(genProduct2, boughtProductsQuantities.get(1), "0");
		addProductsForCustomerWorkflow.addProductToCart(genProduct3, boughtProductsQuantities.get(2), "0");
		addProductsForCustomerWorkflow.addProductToCart(genProduct4, boughtProductsQuantities.get(3), "0");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingPartySectionSteps.checkItemNotReceivedYet();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName());

		for (SyncInfoModel product : changingStockMagentoProducts) {
			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName());
		}
		for (String product : boughtProductsQuantities) {
			MongoWriter.saveStringValue(product, getClass().getSimpleName());
		}
	}

}

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.http.MagentoProductsInfoCalls;
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
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
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

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
	@Steps
	public RegularUserCartSteps regularUserCartSteps;

	private String username, password;
	private CustomerFormModel customerData;
	private AddressModel addressData;
	private String qtyForBundle1;
	private CreditCardModel creditCardData = new CreditCardModel();
	private OrderModel orderModel = new OrderModel();
	private List<String> boughtProductsQuantities = new ArrayList<String>();
	private List<String> boughtProductsQuantitiesNewTp = new ArrayList<String>();
	private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();

	//private List<SyncInfoModel> changingStockMagentoProducts = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> changingStockMagentoProductsNewTp = new ArrayList<SyncInfoModel>();
//	private static List<String> changingStockIdList = new ArrayList<String>(
//			Arrays.asList("4469", "2358", "4271", "4304"));
//	private static List<String> changingStockIdListNewTp = new ArrayList<String>(
//			Arrays.asList("4599", "4575", "4346", "4345"));

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();
	private ProductDetailedModel genProduct5 = new ProductDetailedModel();
	private ProductDetailedModel genProduct6 = new ProductDetailedModel();

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();
		addressData = new AddressModel();

		changingStockMagentoProducts = MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK);
//		for (String id : changingStockIdList) {
//			changingStockMagentoProducts.add(MagentoProductCalls.getMagProductInfo(id));
//		}

		changingStockMagentoProductsNewTp=MongoReader.grabStockInfoModel("US23001GetMagAndNavStockBerforeTpOrderTest" + SoapKeys.MAGENTO_INITIAL_CHANGING_STOCK_NEW_TP);
//		for (String id : changingStockIdListNewTp) {
//			changingStockMagentoProductsNewTp.add(MagentoProductCalls.getMagProductInfo(id));
//		}

		qtyForBundle1 = BigDecimal.valueOf(Double.parseDouble(changingStockMagentoProducts.get(2).getQuantity()))
				.compareTo(
						BigDecimal.valueOf(Double.parseDouble(changingStockMagentoProducts.get(3).getQuantity()))) > 0
								? changingStockMagentoProducts.get(3).getQuantity()
								: changingStockMagentoProducts.get(3).getQuantity();



		boughtProductsQuantities.add(StockCalculations.determineQuantity(changingStockMagentoProducts.get(0).getQuantity()));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(changingStockMagentoProducts.get(1).getQuantity()));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(qtyForBundle1));
		boughtProductsQuantities.add(StockCalculations.determineQuantity(qtyForBundle1));

		boughtProductsQuantitiesNewTp.add("1");
		boughtProductsQuantitiesNewTp.add("1");
		boughtProductsQuantitiesNewTp.add("1");
		boughtProductsQuantitiesNewTp.add("1");

		// boughtProductsQuantities

		System.out.println(qtyForBundle1);

		// this is parent configurable:R185SV
		genProduct1 = MagentoProductsInfoCalls.getProductInfo("4466");
		// simple
		genProduct2 = MagentoProductsInfoCalls.getProductInfo("2358");
		// bundle parent: K112GY-E195SV-B170BR
		genProduct3 = MagentoProductsInfoCalls.getProductInfo("4319");
		// genProduct3=MagentoProductsInfoCalls.getProductInfo("4304");

		// this is parent configurable:R194RS
		genProduct4 = MagentoProductsInfoCalls.getProductInfo("4596");
		// simple:N348SV
		genProduct5 = MagentoProductsInfoCalls.getProductInfo("4575");
		// bundle parent: K138SV-A023SV-A022SV
		genProduct6 = MagentoProductsInfoCalls.getProductInfo("4417");



		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_23_FOLDER + File.separator + "us23001.properties");
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
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CHANGING_STOCK_MAGENTO_PRODUCTS);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CHANGING_STOCK_MAGENTO_PRODUCTS_NEW_TP);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.BOUGHT_PRODUCTS_QUANTITIES );
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.BOUGHT_PRODUCTS_QUANTITIES_NEW_TP);
		
	}

	@Test
	public void us23001PlaceTermPurchaseOrderTest() {
		customerRegistrationSteps.performLogin(username, password);
		
		System.out.println(username +"  "+  password);
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

		addProductsForCustomerWorkflow.addProductToCart(genProduct1, boughtProductsQuantities.get(0), "18");
		addProductsForCustomerWorkflow.addProductToCart(genProduct2, boughtProductsQuantities.get(1), "0");
		addProductsForCustomerWorkflow.addProductToCart(genProduct3, boughtProductsQuantities.get(2), "0");

		addProductsForCustomerWorkflow.addProductToCart(genProduct4, boughtProductsQuantitiesNewTp.get(0), "18");
		addProductsForCustomerWorkflow.addProductToCart(genProduct5, boughtProductsQuantitiesNewTp.get(1), "0");
		addProductsForCustomerWorkflow.addProductToCart(genProduct6, boughtProductsQuantitiesNewTp.get(2), "0");
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.clickDeliverAllAtOnce();

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingPartySectionSteps.checkItemNotReceivedYet();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		orderModel.setOrderId(FormatterUtils.incrementSingleTpOrderId(orderModel.getOrderId(), 1));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();

	}

	@After
	public void saveData() {

		System.out.println("order model " + orderModel.getOrderId());
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName());

//		for (SyncInfoModel product : changingStockMagentoProducts) {
//			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.CHANGING_STOCK_MAGENTO_PRODUCTS);
//		}
//
//		for (SyncInfoModel product : changingStockMagentoProductsNewTp) {
//			MongoWriter.saveStockInfoModel(product, getClass().getSimpleName() + SoapKeys.CHANGING_STOCK_MAGENTO_PRODUCTS_NEW_TP);
//		}
		for (String product : boughtProductsQuantities) {
			MongoWriter.saveStringValue(product, getClass().getSimpleName() + SoapKeys.BOUGHT_PRODUCTS_QUANTITIES);
			System.out.println("product qty "+product);
		}
		

		for (String product : boughtProductsQuantitiesNewTp) {
			MongoWriter.saveStringValue(product, getClass().getSimpleName() + SoapKeys.BOUGHT_PRODUCTS_QUANTITIES_NEW_TP);
		}
	}

}

package com.tests.uss16.us16003DataPreparation;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US16003RegularCustomerSetProductsInCartAndWishlist extends BaseTest {
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public RegularCartValidationWorkflows regularCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public WishlistSteps wishlistSteps;

	
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;
//	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	private String customerEmail;
	private String customerPassword;
	
	public static List<RegularBasicProductModel> allProductsFromCartList = new ArrayList<RegularBasicProductModel>();
	public static List<RegularBasicProductModel> allProductsFromWishList = new ArrayList<RegularBasicProductModel>();
	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("10.00");
		MagentoProductCalls.createApiProduct(genProduct3);

		genProduct4 = MagentoProductCalls.createProductModel();
		genProduct4.setPrice("15.00");
		MagentoProductCalls.createApiProduct(genProduct4);
		

		int size = MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").size();
		if (size > 0) {
			customerEmail = MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0).getEmailName();
			customerPassword=MongoReader.grabCustomerFormModels("US16003RegularCustomerRegistrationTest").get(0).getPassword();
		} else
			System.out.println("The database has no entries");

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.CALC);
		MongoConnector.cleanCollection(getClass().getSimpleName() + "CART");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "WISH");
	}

	@Test
	public void us16003RegularCustomerSetProductsInCartAndWishlist() {
		customerRegistrationSteps.performLogin(customerEmail, customerPassword);
	//	customerRegistrationSteps.performLogin("emilian.melian@evozon.com", "emilian1");
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		allProductsFromCartList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		allProductsFromCartList.add(productData);
		
		
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
		//add in wishlist product 3 & 4
		searchSteps.navigateToProductPage(genProduct3.getSku());
		productData=addRegularProductsWorkflow.setBasicProductToWishlist(genProduct3, "1", "0");
		allProductsFromWishList.add(productData);
		searchSteps.navigateToProductPage(genProduct4.getSku());
		productData=addRegularProductsWorkflow.setBasicProductToWishlist(genProduct4, "1", "0");
		allProductsFromWishList.add(productData);
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		for (RegularBasicProductModel cartProduct : allProductsFromCartList) {
			MongoWriter.saveRegularBasicProductModel(cartProduct, getClass().getSimpleName() + "CART");
		}
		
		for (RegularBasicProductModel wishListProduct : allProductsFromWishList) {
			MongoWriter.saveRegularBasicProductModel(wishListProduct, getClass().getSimpleName() + "WISH");
		}
		
	}
}

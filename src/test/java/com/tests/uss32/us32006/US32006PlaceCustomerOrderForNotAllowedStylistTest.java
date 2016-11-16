package com.tests.uss32.us32006;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US11.8 Party Host Buys For Customer With 0 Amount Immediate and Tp Products", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_8.class)
@RunWith(SerenityRunner.class)
public class US32006PlaceCustomerOrderForNotAllowedStylistTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;

	public static List<ProductDetailedModel> allProductsList;
	List<List<String>> dropdownDatesList = new ArrayList<List<String>>();
	private String username, password, customerName;
	private ProductDetailedModel genProduct1, genProduct2, genProduct3;

	@Before
	public void setUp() throws Exception {
		allProductsList = new ArrayList<ProductDetailedModel>();

		// immediate
		genProduct1 = MagentoProductCalls.createProductModel();
		MagentoProductCalls.createApiProduct(genProduct1);
		genProduct1.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		// immediate with TP
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.getStockData().setAllowedTermPurchase("1");
		MagentoProductCalls.createApiProduct(genProduct2);
		genProduct2.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		// TP
		genProduct3 = MagentoProductCalls.createNotAvailableYetProductModel();
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss32" + File.separator + "us32005.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerName = prop.getProperty("customerName");

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
	public void us32006PlaceCustomerOrderForNotAllowedStylistTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		do {
			loungeSteps.clickOrderForCustomer();
			partyDetailsSteps.orderForCustomer();
			partyDetailsSteps.orderForCustomerFromParty(customerName);
		} while (!orderForCustomerCartSteps.getCartOwnerInfo().contains(customerName.toUpperCase()));
		generalCartSteps.clearCart();
	
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();

		

		addProductsForCustomerWorkflow.addProductToCart(genProduct2, "1", "0");
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		regularUserCartSteps.verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable();
		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct2.getSku());

		addProductsForCustomerWorkflow.addProductToCart(genProduct1, "1", "0");
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		regularUserCartSteps.verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable();
		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct1.getSku());

		searchSteps.navigateToProductPage(genProduct3.getSku());
		productSteps.verifyThatProductStatusIsCorrect(ContextConstants.CURRENTLY_OUT_OF_STOCK);
		productSteps.verifyAddToCartButton(false);
		addRegularProductsWorkflow.setBasicProductToWishlist(genProduct3, "1", "0");
		wishlistSteps.verifyPresenceOfAddAllToCartButton(false);
}
}
package com.tests.uss32.us32003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import com.steps.frontend.HomeSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US32003CheckRegularOrderTpRestrictionsWhenProcessDisabledTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;

	RegularBasicProductModel productData;
	private String username, password;
	private ProductDetailedModel genProduct1, genProduct2, genProduct3;
	String formatedAvailabilityDate;
	public static List<ProductDetailedModel> allProductsList;

	@Before
	public void setUp() throws Exception {

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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8007.properties");
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
	public void us32003CheckRegularOrderTpRestrictionsWhenProcessDisabledTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();

		

		addProductsForCustomerWorkflow.addProductToCart(genProduct2, "1", "0");
//		allProductsList.add(genProduct2);
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		regularUserCartSteps.verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable();
		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct2.getSku());

		addProductsForCustomerWorkflow.addProductToCart(genProduct1, "1", "0");
//		allProductsList.add(genProduct1);
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

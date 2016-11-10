package com.tests.uss32;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddProductsWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US32001CheckTpProductsRestrictionsForSfmTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;

	private String username, password;

	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private ProductDetailedModel genProduct4;

	String incrementIdProd2;
	String incrementIdProd4;

	@Before
	public void setUp() throws Exception {

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.getStockData().setIsDiscontinued("0");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		incrementIdProd2 = MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		MagentoProductCalls.createApiProduct(genProduct3);

		genProduct4 = MagentoProductCalls.createMarketingProductModel();
		incrementIdProd4 = MagentoProductCalls.createApiProduct(genProduct4);

		System.out.println(genProduct4.getName() + " " + ContextConstants.REMOVE_MESSAGE_ADVICE);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
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
	public void us32001CheckTpProductsRestrictionsForSfmTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
		searchSteps.navigateToProductPage(genProduct1.getSku());
		productSteps.verifyAddToCartButton(false);
		// verify tp product in product details

		addProductsWorkflow.setBasicProductToWishlist(genProduct2, "1", "0", ConfigConstants.DISCOUNT_25);
		addProductsWorkflow.setBasicProductToWishlist(genProduct3, "1", "0", ConfigConstants.DISCOUNT_25);
		addProductsWorkflow.setBasicProductToCart(genProduct4, "1", "0", ConfigConstants.DISCOUNT_25);

		genProduct2.setStockData(MagentoProductCalls.createNotAvailableForTheMomentStockData());
		MagentoProductCalls.updateApiProduct(genProduct2, incrementIdProd2);

		genProduct4.setStockData(MagentoProductCalls.createNotAvailableForTheMomentStockData());
		MagentoProductCalls.updateApiProduct(genProduct4, incrementIdProd4);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.verifyStockMessageForProduct(genProduct4.getName(), ContextConstants.PRODUCT_CURRENTLY_OUT_OF_SROCK);
		cartSteps.verifyErrorMessageInCart(genProduct4.getSku() + " " + ContextConstants.REMOVE_MESSAGE_ADVICE);
		//@emilian TODO 
		//validate the followig message displayed in cart, the scenario to appears this message is : 
		//Add a product to shopping cart and as admin mark that product as out of stock
		//in this test this validation should be done for genProduct4
		//the error message is : EN:Some of the products are currently out of stock,DE:Einige der Artikel sind derzeit nicht auf Lager.
	
		cartSteps.verifyPresenceOfGoToCheckoutButton(false);

		headerSteps.clickOnWishlistButton();
		wishlistSteps.verifyPresenceOfAddAllToCartButton(true);
		wishlistSteps.verifyThatCannotBeAddedToCart(genProduct2.getName());
		wishlistSteps.addProductToCart(genProduct3.getName());
		headerSteps.clickOnWishlistButton();
		wishlistSteps.verifyPresenceOfAddAllToCartButton(false);

	}

}

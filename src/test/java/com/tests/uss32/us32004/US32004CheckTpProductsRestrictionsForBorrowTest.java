package com.tests.uss32.us32004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.borrowCart.BorrowCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.borrowCart.AddBorrowedProductsWorkflow;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US32004CheckTpProductsRestrictionsForBorrowTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public BorrowCartSteps borrowCartSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	public AddBorrowedProductsWorkflow addBorrowedProductsWorkflow;
	@Steps
	public LoungeSteps loungeSteps;

	private String username, password;

	private ProductDetailedModel genProduct1, genProduct2 ;

	String incrementIdProd2;
	

	@Before
	public void setUp() throws Exception {

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.getStockData().setIsDiscontinued("0");
		MagentoProductCalls.createApiProduct(genProduct1);
		
		genProduct2 = MagentoProductCalls.createMarketingProductModel();
		incrementIdProd2 = MagentoProductCalls.createApiProduct(genProduct2);



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
	public void us32004CheckTpProductsRestrictionsForBorrowTest() {
		frontEndSteps.performLogin(username, password);
		
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
		loungeSteps.clickGoToBorrowCart();
		generalCartSteps.clearCart();
		headerSteps.clickOnWishlistButton();
		wishlistSteps.removeProductsFromWishlist();
		searchSteps.navigateToProductPage(genProduct1.getSku());
		productSteps.verifyThatProductStatusIsCorrect(ContextConstants.CURRENTLY_OUT_OF_STOCK);
		productSteps.verifyAddToCartButton(false);
		addBorrowedProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
		wishlistSteps.verifyPresenceOfAddAllToCartButton(false);

		addBorrowedProductsWorkflow.setBorrowedProductToCart(genProduct2, "0.00");
		genProduct2.setStockData(MagentoProductCalls.createNotAvailableForTheMomentStockData());
		MagentoProductCalls.updateApiProduct(genProduct2, incrementIdProd2);
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		borrowCartSteps.verifyStockMessageForProduct(genProduct2.getName(), ContextConstants.PRODUCT_CURRENTLY_OUT_OF_SROCK);
		borrowCartSteps.verifyErrorMessageInCart(ContextConstants.REMOVE_MESSAGE_ADVICE);
		borrowCartSteps.verifyPresenceOfGoToCheckoutButton(false);
		
	}

}

package com.tests.uss32;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Locale;
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
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US32001CheckRegularOrderTpRestrictionsForNotAllowedCustomerTest extends BaseTest {

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
	RegularBasicProductModel productData;
	private String username, password;
	private ProductDetailedModel genProduct1;
	String formatedAvailabilityDate;

	@Before
	public void setUp() throws Exception {

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.getStockData().setIsDiscontinued("0");
		MagentoProductCalls.createApiProduct(genProduct1);

		String unformatedAvailabilityDate = DateUtils.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd");

		formatedAvailabilityDate = DateUtils.parseDate(unformatedAvailabilityDate, "yyyy-MM-dd", "dd. MMM. yyyy", new Locale.Builder().setLanguage(MongoReader.getContext())
				.build());

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11001.properties");
			prop.load(input);
			username = prop.getProperty("customerUsername");
			password = prop.getProperty("customerPassword");

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
	public void us32001CheckRegularOrderTpRestrictionsForNotAllowedCustomerTest() throws ParseException {
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
		searchSteps.navigateToProductPage(genProduct1.getName());
		productSteps.verifyThatProductStatusIsCorrect(ContextConstants.CURRENTLY_OUT_OF_STOCK);
		productSteps.verifyAddToCartButton(false);
		addRegularProductsWorkflow.setBasicProductToWishlist(genProduct1, "1", "0");
		wishlistSteps.verifyPresenceOfAddAllToCartButton(false);
	}

}

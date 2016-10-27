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
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.AddProductsModalSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.wishlist.WishlistSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.partyHost.AddHostProductsWorkflow;

@WithTag(name = "US32.1 Check restriction for product available with TP", type = "Scenarios")
@Story(Application.CheckTpProductsRestrictions.US32_1.class)
@RunWith(SerenityRunner.class)
public class US32001CheckPlaceHostOrderTpRestrictionsTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public WishlistSteps wishlistSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public AddProductsModalSteps addProductsModalSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public AddHostProductsWorkflow addHostProductsWorkflow;
	private static UrlModel partyUrlModel = new UrlModel();

	private String username, password;
	private ProductDetailedModel genProduct1;

	String formatedAvailabilityDate;

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct1.getStockData().setIsDiscontinued("0");
		MagentoProductCalls.createApiProduct(genProduct1);

		String unformatedAvailabilityDate = DateUtils
				.getFirstFridayAfterDate(genProduct1.getStockData().getEarliestAvailability(), "yyyy-MM-dd");

		formatedAvailabilityDate = DateUtils.parseDate(unformatedAvailabilityDate, "yyyy-MM-dd", "dd. MMM. yyyy",
				new Locale.Builder().setLanguage(MongoReader.getContext()).build());

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15004.properties");
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
		
		partyUrlModel = MongoReader.grabUrlModels("US10007CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		System.out.println("partyUrlModel " + partyUrlModel.getUrl());
	}

	@Test
	public void us32001CheckPlaceHostOrderTpRestrictionsTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();

		orderForCustomerCartSteps.openSearchProductsModal();

		addProductsModalSteps.searchForProduct(genProduct1.getSku());
		addProductsModalSteps.verifyProductPropertiesInModalWindow(genProduct1.getSku(), genProduct1.getName(),
				formatedAvailabilityDate);
		addProductsModalSteps.closeModal();

		addHostProductsWorkflow.setHostProductToCart(genProduct1, "1", "0");

		productSteps.verifyThatAvailabilityDateIsCorrect(formatedAvailabilityDate);

		addHostProductsWorkflow.addProductToWishlist(genProduct1, "1", "0");
		
		wishlistSteps.verifyPresenceOfAddAllToCartButton(true);
		wishlistSteps.addProductToCart(genProduct1.getName());

	}

}

package com.tests.us1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.ValidationSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.AddressModel;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;
import com.tools.data.CreditCardModel;
import com.tools.data.EmailModel;
import com.tools.data.ProductBasicModel;
import com.tools.requirements.Application;

@WithTag(name = "US1", type = "US1")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001StyleCoachShoppingTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ValidationSteps validationSteps;

	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private String username, password;
	private CreditCardModel creditCardData = new CreditCardModel();

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us1\\us001.properties");
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

		creditCardData.setCardNumber("4111 1111 1111 1111");
		creditCardData.setCardName("test");
		creditCardData.setMonthExpiration("06");
		creditCardData.setYearExpiration("2016");
		creditCardData.setCvcNumber("737");

	}

	@Test
	public void uS001StyleCoachShoppingTest() {
		frontEndSteps.performLogin(username, password);

		ProductBasicModel productData = searchSteps.searchAndSelectProduct("M081", "BANNER MIT LOGO");
		productSteps.setProductAddToCart("1", "Blue");
		productsList.add(productData);
		productData = searchSteps.searchAndSelectProduct("M058", "GUTSCHEIN FOLGEPARTY");
		productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);
		productData = searchSteps.searchAndSelectProduct("MAGIC VIOLETTA", "MAGIC VIOLETTA");
		productSteps.setProductAddToCart("2", "0");
		productsList.add(productData);
//		productData = searchSteps.searchAndSelectProduct("Rosemary Ring", "ROSEMARY RING");
//		productSteps.setProductAddToCart("3", "18");
//		productsList.add(productData);

		String previewPrice = headerSteps.openCartPreview();
		headerSteps.goToCart();

		System.out.println("Cart Preview Price: " + previewPrice);

		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		CartTotalsModel cartTotals = cartSteps.grabTotals();

		CartTotalsModel calculatedTotals = validationSteps.calculateCartProducts(cartProducts);
		validationSteps.checkTotalsInCart(cartTotals, calculatedTotals);

		cartSteps.clickGoToShipping();

		// TODO - add billing and shipping address forms
		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
		PrintUtils.printCartTotals(shippingTotals);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		shippingSteps.clickGoToPaymentMethod();

		paymentSteps.expandCreditCardForm();

		paymentSteps.fillCreditCardForm(creditCardData);

		AddressModel billingAddress = confirmationSteps.grabBillingData();
		AddressModel shippingAddress = confirmationSteps.grabSippingData();
		
		confirmationSteps.agreeAndCheckout();
		
		
		
		
		List<EmailModel> emailList = GmailConnector.readGmail();
		//TODO add page for success
		System.out.println("---------------");
		System.out.println("!!!!!!" + billingAddress.getCountryName());
		System.out.println("!!!!!!" + shippingAddress.getCountryName());
		
		System.out.println("BILLING");
		PrintUtils.printAddressModel(billingAddress);

		System.out.println("SHIPPING");
		PrintUtils.printAddressModel(shippingAddress);

	}

}

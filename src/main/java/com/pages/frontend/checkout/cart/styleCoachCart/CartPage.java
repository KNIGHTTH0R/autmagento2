package com.pages.frontend.checkout.cart.styleCoachCart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoTableKeys;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class CartPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "div.page-title ul.checkout-types button:last-child")
	private WebElement kasseButton;

	@FindBy(css = "div.buttons-set.to-the-right button[type*='submit']")
	private WebElement updateButton;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(2) td:last-child form button span")
	private WebElement updateJewerlyBonus;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(3) td:last-child form button span")
	private WebElement updateMarketingBonus;

	@FindBy(css = "table.cart-table")
	private WebElement cartTable;

	@FindBy(id = "jewelry_credits")
	private WebElement jewerlyBonusInput;

	@FindBy(id = "marketing_credits")
	private WebElement marketingBonusInput;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

	@FindBy(className = "shopping-bag-form")
	private WebElement formContainer;

	@FindBy(css = "li.error-msg span")
	private WebElement errorMessageContainer;

	@FindBy(css = "li.error-msg span")
	private WebElement errorJbMessage;

	/**
	 * Will grab all products data from all carts
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsData() {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));

		// waitABit(Constants.TIME_CONSTANT);
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass("");

			resultList.add(productNow);
		}

		return resultList;
	}

	/**
	 * 
	 * Will grab all products data from the cart where discount is 25%
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith25Discount() {
		element(formContainer).waitUntilVisible();
		List<WebElement> entryList = formContainer.findElements(By.cssSelector("#shopping-cart-25-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("input[name*=qty]")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			// productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			// productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			// productNow.setProductsPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			// productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6)
			// span.price")).getText()));
			// productNow.setPriceIP(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6)
			// span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_25);
			resultList.add(productNow);
		}

		return resultList;
	}

	/**
	 * Will grab all products data from the cart where discount is 50%
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith50Discount() {
		element(formContainer).waitUntilVisible();
		waitABit(1000);
		List<WebElement> entryList = formContainer.findElements(By.cssSelector("#shopping-cart-50-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("input[name*=qty]")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_50);

			resultList.add(productNow);
		}

		for (CartProductModel cartProductModel : resultList) {
		System.out.println("sku grabbed "+cartProductModel.getProdCode());
		}
		return resultList;
	}

	/**
	 * Will grab all products data from the cart which are marketing materials
	 * 
	 * @return
	 */
	public List<CartProductModel> grabMarketingMaterialProductsData() {
		element(formContainer).waitUntilVisible();
		List<WebElement> entryList = formContainer
				.findElements(By.cssSelector("#shopping-cart-table-marketing-material tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("input[name*=qty]")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_0);

			resultList.add(productNow);
		}

		return resultList;
	}

	public CartTotalsModel grabTotals() {
		CartTotalsModel resultModel = new CartTotalsModel();
		waitABit(TimeConstants.TIME_CONSTANT);

		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(totalsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));

		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();

			if (key.contains(ContextConstants.ZWISCHENSUMME)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains(ContextConstants.SCHMUCK_BONUS)) {
				valueTransformer = FormatterUtils.parseValueToZeroDecimals(itemNow
						.findElement(By.cssSelector("td:last-child form input[type*='text']")).getAttribute("value"));

				if (!valueTransformer.contains(".")) {
					valueTransformer += ".00";
				}

				resultModel.setJewelryBonus(valueTransformer);
			}
			if (key.contains(ContextConstants.MARKETING_BONUS)) {
				valueTransformer = FormatterUtils.parseValueToZeroDecimals(itemNow
						.findElement(By.cssSelector("td:last-child form input[type*='text']")).getAttribute("value"));
				resultModel.setMarketingBonus(valueTransformer);
			}
			if (key.contains(ContextConstants.STEUER)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains(ContextConstants.VERSANDKOSTENFREI)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setShipping(valueTransformer);
			}
			if (key.contains("25%") && key.contains(ContextConstants.RABATT)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.DISCOUNT_25_KEY, valueTransformer);
			}
			if (key.contains("50%") && key.contains(ContextConstants.RABATT)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.DISCOUNT_50_KEY, valueTransformer);
			}
			if (key.contains("BUY 3 GET 1 FOR 50%")) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.DISCOUNT_3_PLUS_1, valueTransformer);
			}
			if (key.contains("AUT-Money voucher working on shipping fee")) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.VOUCHER_DISCOUNT, valueTransformer);
			}
			if (key.contains(ContextConstants.GESAMTBETRAG)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			if (key.contains(ContextConstants.IP_PUNKTE)) {
				valueTransformer = FormatterUtils
						.parseValueToZeroDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setIpPoints(valueTransformer);
			}

		}

		DataGrabber.cartTotals = resultModel;

		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickUpdateCart() {
		element(updateButton).waitUntilVisible();
		updateButton.click();
	}

	public void clickUpdateJewerlyBonus() {
		element(updateJewerlyBonus).waitUntilVisible();
		updateJewerlyBonus.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
	}

	public void clickUpdateMarketingBonus() {
		element(updateMarketingBonus).waitUntilVisible();
		updateMarketingBonus.click();
	}

	public void typeJewerlyBonus(String jevewrlyBonus) {
		element(jewerlyBonusInput).waitUntilVisible();
		element(jewerlyBonusInput).clear();
		element(jewerlyBonusInput).sendKeys(jevewrlyBonus);

	}

	public void typeJewerlyBonusAndEnter(String jevewrlyBonus) {
		element(jewerlyBonusInput).waitUntilVisible();
		element(jewerlyBonusInput).clear();
		element(jewerlyBonusInput).sendKeys(jevewrlyBonus);
		jewerlyBonusInput.sendKeys(Keys.ENTER);
	}

	public void typeMarketingBonus(String marketingBonus) {
		element(marketingBonusInput).waitUntilVisible();
		element(marketingBonusInput).clear();
		element(marketingBonusInput).sendKeys(marketingBonus);
	}

	public void updateProductQuantityIn50DiscountArea(String quantity, String... terms) {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("#shopping-cart-50-table tbody > tr"));
		boolean containsTerms = true;
		for (WebElement webElement : entryList) {
			containsTerms = true;
			for (String term : terms) {
				if (!webElement.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(term)) {
					containsTerms = false;
				}
			}
			if (containsTerms) {
				WebElement input = webElement.findElement(By.cssSelector("td:nth-child(3) input"));
				element(input).clear();
				element(input).sendKeys(quantity);
				break;
			}
		}
		Assert.assertTrue("The product was not found", containsTerms);
	}

	/**
	 * Verify Wipe cart if cart contains any data
	 */
	public void verifyWipeCart() {
		element(cartMainContainer).waitUntilVisible();
		System.out.println("TEXT from CONTAINER: " + cartMainContainer.getText());

		Assert.assertTrue(cartMainContainer.getText().contains(ContextConstants.EMPTY_CART));

	}

	public void verifyStockMessageForProduct(String productName, String stockInfo) {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver()
				.findElements(By.cssSelector("table[class*='data-table cart-table'] tbody > tr"));
		boolean found = false;
		for (WebElement entry : entryList) {
			if (entry.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(productName)) {
				found = true;
				Assert.assertTrue("The stock message is not correct !!!",
						entry.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(stockInfo));
				break;
			}
		}
		Assert.assertTrue("The product was not found in the cart", found);
	}

	public String getCartErrorMessage() {
		return errorMessageContainer.getText();
	}

	public void verifyPresenceOfGoToCheckoutButton(boolean shouldBePresent) {
		if (shouldBePresent)
			Assert.assertTrue("The go to checkout button should be visible and it's not!", getDriver()
					.findElement(By.cssSelector("div.page-title ul.checkout-types button:last-child")).isDisplayed());
		else
			Assert.assertTrue("The go to checkout button is visible and it shouldn't !", getDriver()
					.findElements(By.cssSelector("div.page-title ul.checkout-types button:last-child")).size() == 0);
	}

	public void verifyJBErrorMessage(boolean shouldBePresent, String errorMessage) {
		if (shouldBePresent)
			Assert.assertTrue("The JB error message is visible and it shouldn't !",
					cartMainContainer.getText().contains(errorMessage));
		else
			Assert.assertTrue("The error message should be visible and it isn't!",
					!cartMainContainer.getText().contains(errorMessage));
	}

	public void selectDeliveryDate(String productCode, String deliveryDate, String section) {
	
		List<WebElement> cartList = getDriver().findElements(By.cssSelector("#shopping-cart-"+section));
		boolean foundProduct = false;
		for (WebElement product : cartList) {
			if (product.getText().contains(productCode)) {
				foundProduct = true;
				WebElement preOrderCheckbox = product.findElement(By.cssSelector("input[id*='tp-status']"));
				if (!preOrderCheckbox.isSelected()) {
					preOrderCheckbox.click();

				}
				WebElement dropdown = product.findElement(By.cssSelector("select.tp-cb-item-delivery-date"));
				dropdown.click();
				List<WebElement> deliveryoptions = product
						.findElements(By.cssSelector("select.tp-cb-item-delivery-date option"));
				for (WebElement option : deliveryoptions) {
					if (option.getText().toLowerCase().contains(deliveryDate.toLowerCase())) {
						element(dropdown).selectByIndex(deliveryoptions.indexOf(option));
						break;
					}
				}

				waitFor(ExpectedConditions.invisibilityOfElementWithText(
						By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
				break;
			}
		}
		Assert.assertTrue("The product with the product code " + productCode + " was not found", foundProduct);

		
	}
}

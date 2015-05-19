package com.pages.frontend.checkout.cart.regularCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class RegularUserCartPage extends AbstractPage {

	@FindBy(css = "table.cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(id = "coupon_code")
	private WebElement couponCodeInput;

	@FindBy(css = "#discount-coupon-form a.button.gold-btn.bordered")
	private WebElement submitVoucherCode;

	@FindBy(css = "div.page-title ul.checkout-types button:last-child")
	private WebElement kasseButton;

	@FindBy(css = "div.buttons-set.to-the-right button[type*='submit']")
	private WebElement updateButton;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(2) td:last-child form button span")
	private WebElement updateJewerlyBonus;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(3) td:last-child form button span")
	private WebElement updateMarketingBonus;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

	@FindBy(css = "a[href*='/changePreferredShop']")
	private WebElement changePrefferedShopLink;

	@FindBy(css = "ul.messages li.error-msg")
	private WebElement changePrefferedShopMessage;

	@FindBy(css = "li.error-msg span")
	private WebElement errorMessageContainer;

	public void validateThatVoucherCannotBeAppliedMessage() {
		element(errorMessageContainer).waitUntilVisible();
		Assert.assertTrue("The message <" + ContextConstants.VOUCHER_DISCOUNT_INCOMPATIBLE + "> dosn't appear and it should!",
				errorMessageContainer.getText().contains(ContextConstants.VOUCHER_DISCOUNT_INCOMPATIBLE));
	}

	public void validateThatShippingOnSelectedCountryIsNotAllowed() {
		element(errorMessageContainer).waitUntilVisible();
		Assert.assertTrue("The message <" + ContextConstants.VOUCHER_DISCOUNT_INCOMPATIBLE + "> dosn't appear and it should!",
				errorMessageContainer.getText().contains(ContextConstants.NOT_ALLOWED_TO_SHIP_ON_SELECTED_COUNTRY));
	}

	public void typeCouponCode(String code) {
		element(couponCodeInput).waitUntilVisible();
		couponCodeInput.sendKeys(code);
	}

	public void submitVoucherCode() {
		element(submitVoucherCode).waitUntilVisible();
		submitVoucherCode.click();
	}

	public void validateNotPrefferedShopAndGoToPreferredOne() {
		Assert.assertTrue("The not preffered shop message is missing", changePrefferedShopMessage.getText().contains(ContextConstants.NOT_PREFERED_SHOP_MESSAGE));
		changePrefferedShopLink.click();
	}

	public void selectProductDiscountType(String productCode, String discountType) {
		List<WebElement> cartList = getDriver().findElements(By.cssSelector("#shopping-cart-table tbody tr"));
		boolean foundProduct = false;
		for (WebElement product : cartList) {
			if (product.getText().contains(productCode)) {
				foundProduct = true;
				element(product.findElement(By.cssSelector("select.validate-select.discountSelect"))).selectByVisibleText(discountType);

				break;
			}
		}
		Assert.assertTrue("The product with the product code " + productCode + " was not found", foundProduct);
	}

	public void updateProductList(List<RegularBasicProductModel> productsList, String productCode, String discountType) {
		for (RegularBasicProductModel product : productsList) {
			if (product.getProdCode().contentEquals(productCode)) {
				product.setBonusType(discountType);
				if (discountType.contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
					product.setBunosValue(String.valueOf(calculate40Discount(product.getFinalPrice())));
				} else if (discountType.contentEquals(ContextConstants.JEWELRY_BONUS)) {
					product.setBunosValue(product.getFinalPrice());
				}
			}
		}
	}

	public BigDecimal calculate40Discount(String finalPrice) {
		BigDecimal bonusValue = BigDecimal.ZERO;
		BigDecimal finPrice = BigDecimal.valueOf(Double.parseDouble(finalPrice));
		bonusValue = finPrice.multiply(BigDecimal.valueOf(40));
		bonusValue = bonusValue.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

		return bonusValue.setScale(2);
	}

	public void selectShippingOption(String option) {
		List<WebElement> shippingOptionsList = getDriver().findElements(By.cssSelector("ul.purchase-options.form-list li.control"));
		boolean foundOption = false;
		for (WebElement shippingOption : shippingOptionsList) {
			if (shippingOption.getText().contentEquals(option)) {
				foundOption = true;
				shippingOption.findElement(By.cssSelector("div.input-box input")).click();
			}
		}
		Assert.assertTrue("The " + option + " option was not found", foundOption);
	}

	public List<RegularUserCartProductModel> grabProductsData() {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));

		List<RegularUserCartProductModel> resultList = new ArrayList<RegularUserCartProductModel>();

		for (WebElement webElementNow : entryList) {
			RegularUserCartProductModel productNow = new RegularUserCartProductModel();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "")
					.trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3) input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));

			resultList.add(productNow);
		}
		RegularUserDataGrabber.grabbedRegularCartProductsList = resultList;

		return resultList;
	}

	public RegularUserCartTotalsModel grabTotals() {
		RegularUserCartTotalsModel resultModel = new RegularUserCartTotalsModel();
		waitABit(TimeConstants.TIME_CONSTANT);

		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(totalsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));

		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();

			if (key.contains(ContextConstants.ZWISCHENSUMME)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains(ContextConstants.STEUER)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains(ContextConstants.VERSANDKOSTENFREI)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setShipping(valueTransformer);
			}
			if (key.contains(ContextConstants.SCHMUCK_BONUS)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.JEWELRY_BONUS, valueTransformer);
			}
			// TODO do something with this codes
			if (key.contains("G025FMDE")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if (key.contains("CNYSSQGT-2014")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if (key.contains("DVKMHTKA-2015")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if (key.contains("40%") && key.contains(ContextConstants.RABATT)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.DISCOUNT_40_BONUS, valueTransformer);
			}
			if (key.contains("BUY 3 GET 1 FOR 50%")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.DISCOUNT_BUY_3_GET_1, valueTransformer);
			}
			if (key.contains(ContextConstants.GESAMTBETRAG)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
		}

		RegularUserDataGrabber.regularUserGrabbedCartTotals = resultModel;

		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}

	public void clickUpdateCart() {
		element(updateButton).waitUntilVisible();
		updateButton.click();
	}

	/**
	 * Verify Wipe cart if cart contains any data
	 */
	public void verifyWipeCart() {
		element(cartMainContainer).waitUntilVisible();
		System.out.println("TEXT from CONTAINER: " + cartMainContainer.getText());

		Assert.assertTrue(cartMainContainer.getText().contains(ContextConstants.EMPTY_CART));

	}

}

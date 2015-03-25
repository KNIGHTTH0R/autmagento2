package com.pages.frontend.checkout.cart.partyHost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.Constants;
import com.tools.calculation.HostCartTotalsCalculation;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class HostCartPage extends AbstractPage {

	@FindBy(id = "shopping-cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types li button")
	private WebElement kasseButton;

	@FindBy(css = "button[title*='Warenkorb aktualisieren'] span")
	private WebElement updateButton;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(2) td:last-child form button span")
	private WebElement updateJewerlyBonus;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(3) td:last-child form button span")
	private WebElement updateMarketingBonus;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

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

	// TODO add ip calculation
	public void updateProductList(List<HostBasicProductModel> productsList, String productCode, String discountType) {
		for (HostBasicProductModel product : productsList) {
			if (product.getProdCode().contentEquals(productCode)) {
				product.setBonusType(discountType);
				if (discountType.contentEquals(Constants.DISCOUNT_40_BONUS)) {
					product.setBunosValue(String.valueOf(calculate40Discount(product.getFinalPrice())));
					product.setIpPoints(String.valueOf(HostCartTotalsCalculation.calculate40DiscountForIp(product.getIpPoints())));
				} else if (discountType.contentEquals(Constants.JEWELRY_BONUS)) {
					product.setBunosValue(product.getFinalPrice());
					product.setIpPoints(String.valueOf(BigDecimal.ZERO));
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

	public List<HostCartProductModel> grabProductsData() {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));

		// waitABit(Constants.TIME_CONSTANT);
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();

		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "")
					.trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3) input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span:nth-child(1).price")).getText()));
			productNow.setIpPoints(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span:nth-child(2).price")).getText()));
			resultList.add(productNow);
		}
		HostDataGrabber.grabbedHostCartProductsList = resultList;

		return resultList;
	}

	public HostCartTotalsModel grabTotals() {
		HostCartTotalsModel resultModel = new HostCartTotalsModel();
		waitABit(Constants.TIME_CONSTANT);

		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(totalsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));

		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();

			if (key.contains("ZWISCHENSUMME")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains("STEUER")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains("VERSANDKOSTENFREI")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setShipping(valueTransformer);
			}
			if (key.contains("GENUTZTER SCHMUCK BONUS")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(Constants.JEWELRY_BONUS, valueTransformer);
			}
			if (key.contains("G025FMDE")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(Constants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if (key.contains("40%") && key.contains("RABATT")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(Constants.DISCOUNT_40_BONUS, valueTransformer);
			}
			if (key.contains("BUY 3 GET 1 FOR 50%")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(Constants.DISCOUNT_BUY_3_GET_1, valueTransformer);
			}
			if (key.contains("GESAMTBETRAG")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
		}

		HostDataGrabber.hostGrabbedCartTotals = resultModel;

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

		Assert.assertTrue(cartMainContainer.getText().contains("WARENKORB IST LEER"));

	}

}

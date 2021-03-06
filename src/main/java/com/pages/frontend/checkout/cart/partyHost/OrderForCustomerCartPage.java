package com.pages.frontend.checkout.cart.partyHost;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class OrderForCustomerCartPage extends AbstractPage {

	@FindBy(css = ".main.col1-layout .col-main.pos-rel")
	private WebElement cartOwnerContainer;

	@FindBy(id = "shopping-cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types li button")
	private WebElement kasseButton;

	@FindBy(css = "div.buttons-set.to-the-right button[type*='submit']")
	private WebElement updateButton;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(2) td:last-child form button span")
	private WebElement updateJewerlyBonus;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(3) td:last-child form button span")
	private WebElement updateMarketingBonus;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

	@FindBy(id = "coupon_code")
	private WebElement couponCodeInput;

	@FindBy(css = "#discount-coupon-form a.button.gold-btn.bordered")
	private WebElement submitVoucherCode;

	@FindBy(id = "empty-add-items-button")
	private WebElement searchProductsModal;

	public String getCartOwnerInfo() {
		return cartOwnerContainer.getText();
	}

	public List<HostCartProductModel> grabProductsData() {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));

		// waitABit(Constants.TIME_CONSTANT);
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();

		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(3) input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
			productNow.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));
			resultList.add(productNow);
		}
		HostDataGrabber.grabbedHostCartProductsList = resultList;

		return resultList;
	}

	public HostCartTotalsModel grabTotals(String voucherLabel) {
		HostCartTotalsModel resultModel = new HostCartTotalsModel();
		waitABit(TimeConstants.TIME_CONSTANT);

		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(totalsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));
		System.out.println("valuesList: "+valuesList.size());
		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();

			if (key.contains(ContextConstants.ZWISCHENSUMME)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
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
			if (key.contains(ContextConstants.SCHMUCK_BONUS)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.JEWELRY_BONUS, valueTransformer);
			}
			if (key.contains(voucherLabel)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if ((key.contains("40%") && key.contains(ContextConstants.RABATT))
					|| (key.contains("FORTY") && key.contains(ContextConstants.RABATT))) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.DISCOUNT_40_BONUS, valueTransformer);
			}
			if (key.contains("BUY 3 GET 1 FOR 50%")) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.DISCOUNT_BUY_3_GET_1, valueTransformer);
			}
			if (key.contains(ContextConstants.GESAMTBETRAG)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			
			if (key.contains(ContextConstants.IP_PUNKTE)) {
				valueTransformer = itemNow.findElement(By.cssSelector("td:last-child")).getText();
				resultModel.setTotalIP(valueTransformer);
			}
			
		}

		HostDataGrabber.hostGrabbedCartTotals = resultModel;
		
		System.out.println("da am ajuns aici cu bine");
		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		clickElement(kasseButton);
//		kasseButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
	}

	public void openSearchProductsModal() {
		element(searchProductsModal).waitUntilVisible();
		searchProductsModal.click();
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

	public void typeCouponCode(String code) {
		element(couponCodeInput).waitUntilVisible();
		couponCodeInput.sendKeys(code);
	}

	public void submitVoucherCode() {
		element(submitVoucherCode).waitUntilVisible();
		submitVoucherCode.click();
		waitABit(3000);
	}

}

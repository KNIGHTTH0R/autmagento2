package com.pages.frontend.checkout.cart.partyHost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.cartcalculations.partyHost.HostCartTotalsCalculation;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class HostCartPage extends AbstractPage {

	@FindBy(id = "shopping-cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types li button")
	private WebElement kasseButton;

	@FindBy(css = "li input[value='multiple']")
	private WebElement deliverOnVariousDate;

	@FindBy(css = "li input[value='one_at_date']")
	private WebElement deliverAllOnThisDate;

	@FindBy(css = "div.buttons-set.to-the-right button[type*='submit']")
	private WebElement updateButton;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(2) td:last-child form button span")
	private WebElement updateJewerlyBonus;

	@FindBy(css = "table#shopping-cart-totals-table tr:nth-child(3) td:last-child form button span")
	private WebElement updateMarketingBonus;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

	@FindBy(css = "button.button.blue-button[value='YES']")
	private WebElement acceptNotConsumedBonusButton;
	
	@FindBy(css = "div.buttons-set.to-the-right button:nth-child(1).button.gold-btn.bordered")
	private WebElement clearCartButton;
	
	@FindBy(css = "div.main.col1-layout")
	private WebElement cartContainer;

	public void selectProductDiscountType(String productCode, String discountType) {
		List<WebElement> cartList = getDriver().findElements(By.cssSelector("#shopping-cart-table tbody tr"));
		boolean foundProduct = false;
		for (WebElement product : cartList) {
			if (product.getText().contains(productCode)) {
				foundProduct = true;
				element(product.findElement(By.cssSelector("select.validate-select.discountSelect")))
						.selectByVisibleText(discountType);

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
				if (discountType.contentEquals(ContextConstants.DISCOUNT_40_BONUS)) {
					product.setBunosValue(String.valueOf(calculate40Discount(product.getFinalPrice())));
					product.setIpPoints(
							String.valueOf(HostCartTotalsCalculation.calculate40DiscountForIp(product.getIpPoints())));
				} else if (discountType.contentEquals(ContextConstants.JEWELRY_BONUS)) {
					product.setBunosValue(product.getFinalPrice());
					product.setIpPoints(String.valueOf(BigDecimal.ZERO));
				}
			}
		}
	}

	public BigDecimal calculate40Discount(String finalPrice) {
		BigDecimal bonusValue = BigDecimal.ZERO;
		BigDecimal finPrice = BigDecimal.valueOf(Double.parseDouble(finalPrice));
		bonusValue = finPrice.multiply(BigDecimal.valueOf(30));
		bonusValue = bonusValue.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

		return bonusValue.setScale(2);
	}

	public void selectShippingOption(String option) {
		List<WebElement> shippingOptionsList = getDriver()
				.findElements(By.cssSelector("ul.purchase-options.form-list li.control"));
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

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setQuantity(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(3) input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span:nth-child(1).price")).getText()));
			productNow.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(6) span:nth-child(2).price")).getText()));
			resultList.add(productNow);
		}
		HostDataGrabber.grabbedHostCartProductsList = resultList;

		return resultList;
	}

	public List<HostCartProductModel> grabProductsDataWhenNoBonus() {
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

	public HostCartTotalsModel grabTotals() {
		HostCartTotalsModel resultModel = new HostCartTotalsModel();
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
			if (key.contains("G025FMDE")) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(ConfigConstants.VOUCHER_DISCOUNT, valueTransformer);
			}
			if ((key.contains("30%") && key.contains(ContextConstants.RABATT))
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
				System.out.println("IP PUNKTE: "+valueTransformer);
			}
		}

		HostDataGrabber.hostGrabbedCartTotals = resultModel;

		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		//kasseButton.click();
		clickElement(kasseButton);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickDeliverOnVariousDate() {
		element(deliverOnVariousDate).waitUntilVisible();
		deliverOnVariousDate.click();
	}

	public void clickAllOnThisDate() {
		element(deliverAllOnThisDate).waitUntilVisible();
		deliverAllOnThisDate.click();
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

	public void acceptInfoPopupForNotConsumedBonus() {
//		element(acceptNotConsumedBonusButton).waitUntilVisible();
//		acceptNotConsumedBonusButton.click();
		
		List<WebElement> acceptNotConsumedBonusButton=getDriver().findElements(By.cssSelector("button.button.blue-button[value='YES']"));
		if(acceptNotConsumedBonusButton.size()!=0){
			acceptNotConsumedBonusButton.get(0).click();
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));

		}
	
	}
	
	public void clickClearCart() {
		element(clearCartButton).waitUntilVisible();
		clearCartButton.click();
	}
	
	public boolean isCartEmpty(){
		return cartContainer.getText().contains(ContextConstants.EMPTY_CART);
	}

}

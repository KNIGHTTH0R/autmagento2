package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.datahandler.DataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.persistance.MongoTableKeys;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

public class CartPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "div.page-title ul.checkout-types button:last-child")
	private WebElement kasseButton;

//	@FindBy(css = "button[title*='Warenkorb aktualisieren'] span")
	@FindBy(css = "div.buttons-set.to-the-right button[type*='submit']")    //int  
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

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
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
		waitABit(TimeConstants.TIME_CONSTANT);
		element(formContainer).waitUntilVisible();
		List<WebElement> entryList = formContainer.findElements(By.cssSelector("#shopping-cart-25-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_25);
			PrintUtils.printCartProductModel(productNow);
			resultList.add(productNow);
		}
		System.out.println("CONTROL: 25% grabbed " + resultList.size());
		return resultList;
	}

	/**
	 * Will grab all products data from the cart where discount is 50%
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith50Discount() {
		waitABit(TimeConstants.TIME_CONSTANT);
		element(formContainer).waitUntilVisible();
		List<WebElement> entryList = formContainer.findElements(By.cssSelector("#shopping-cart-50-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_50);

			PrintUtils.printCartProductModel(productNow);
			resultList.add(productNow);
		}
			System.out.println("CONTROL: 50% grabbed " + resultList.size());
		return resultList;
	}

	/**
	 * Will grab all products data from the cart which are marketing materials
	 * 
	 * @return
	 */
	public List<CartProductModel> grabMarketingMaterialProductsData() {
		waitABit(TimeConstants.TIME_CONSTANT);
		element(formContainer).waitUntilVisible();
		List<WebElement> entryList = formContainer.findElements(By.cssSelector("#shopping-cart-table-marketing-material tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("input")).getAttribute("value")));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText()));
			productNow.setPriceIP(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText()));
			productNow.setDiscountClass(ConfigConstants.DISCOUNT_0);
			PrintUtils.printCartProductModel(productNow);
			resultList.add(productNow);
		}
		System.out.println("CONTROL: MarketMat grabbed " + resultList.size());
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

			if (key.contains("ZWISCHENSUMME")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains("SCHMUCK BONUS")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child form input[type*='text']")).getAttribute("value"));

				if (!valueTransformer.contains(".")) {
					valueTransformer += ".00";
				}

				resultModel.setJewelryBonus(valueTransformer);
			}
			if (key.contains("MARKETING BONUS")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child form input[type*='text']")).getAttribute("value"));
				resultModel.setMarketingBonus(valueTransformer);
			}
			if (key.contains("STEUER")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains("VERSANDKOSTENFREI")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setShipping(valueTransformer);
			}
			if (key.contains("25%") && key.contains("RABATT")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.DISCOUNT_25_KEY, valueTransformer);
			}
			if (key.contains("50%") && key.contains("RABATT")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.addDiscount(MongoTableKeys.DISCOUNT_50_KEY, valueTransformer);
			}
			if (key.contains("GESAMTBETRAG")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			if (key.contains("IP PUNKTE")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setIpPoints(valueTransformer);
			}

		}
		
		DataGrabber.cartTotals = resultModel;

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

	public void clickUpdateJewerlyBonus() {
		element(updateJewerlyBonus).waitUntilVisible();
		updateJewerlyBonus.click();
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

}

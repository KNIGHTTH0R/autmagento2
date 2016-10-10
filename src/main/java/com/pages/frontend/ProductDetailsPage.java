package com.pages.frontend;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProductDetailsPage extends AbstractPage {

	@FindBy(id = "qty")
	private WebElement quantityInput;

	@FindBy(css = "select[id*='attribute']")
	private WebElement selectInput;

	@FindBy(css = "button#add-to-cart")
	private WebElement addToCartButton;

	@FindBy(css = "div.product-attributes.clearfix a")
	private WebElement addToWishlistButton;

	@FindBy(css = "p.product-ids")
	private WebElement productCode;

	@FindBy(css = "span[id*='product-price']")
	private WebElement productPrice;

	@FindBy(css = "h1.ff-Nb")
	private WebElement productName;

	@FindBy(css = "div.product-view")
	private WebElement productDetailsContainer;

	@FindBy(id = "stock-status")
	private WebElement stockStatusContainer;

	public void setPrice(String qty) {
		element(quantityInput).waitUntilVisible();
		quantityInput.clear();
		quantityInput.sendKeys(qty);
	}

	public void selectValueFromDropDown(String size) {
		element(selectInput).waitUntilVisible();
		selectFromDropdown(selectInput, size);
	}

	public void addToCart() {
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
		// TODO add a retry here
		waitABit(500);
		while (!addToCartButton.isEnabled()) {
			waitABit(500);
		}
		waitABit(500);
	}

	public void addToWishlist() {
		element(addToWishlistButton).waitUntilVisible();
		addToWishlistButton.click();

	}

	public ProductBasicModel grabProductData() {
		ProductBasicModel result = new ProductBasicModel();
		element(productName).waitUntilVisible();

		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setType(type);
		result.setPrice(FormatterUtils.parseValueToTwoDecimals(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public BasicProductModel grabBasicProductData() {
		BasicProductModel result = new BasicProductModel();
		element(productName).waitUntilVisible();

		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public RegularBasicProductModel grabRegularBasicProductData() {
		RegularBasicProductModel result = new RegularBasicProductModel();
		element(productName).waitUntilVisible();

		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public HostBasicProductModel grabHostBasicProductData() {
		HostBasicProductModel result = new HostBasicProductModel();
		element(productName).waitUntilVisible();

		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	private String cleanProductCode(String code) {
		return code.replace(ContextConstants.ARTICLE_NUMBER, "");
	}

	/**
	 * verifies that Add to cart button is not present
	 */
	public boolean verifyIfAddToCartButtonIsPresent() {
		return addToWishlistButton.isDisplayed();
	}

	/**
	 * verifies that Add to wishlist button is not present
	 */
	public boolean verifyIfAddToWishlistButtonIsPresent() {
		return addToWishlistButton.isDisplayed();
	}

	public String getProductDetailsText() {
		return productDetailsContainer.getText();
	}

	public String getStockStatus() {
		return stockStatusContainer.getText();
	}
}

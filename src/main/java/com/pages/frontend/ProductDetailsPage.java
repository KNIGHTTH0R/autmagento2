package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;
import com.tools.Constants;

public class ProductDetailsPage extends AbstractPage {

//	@FindBy(css = ".dp-bl.title.ff-Nb.size20")
//	private WebElement titleContainer;

//	@FindBy(css = ".price")
//	private WebElement priceContainer;

	@FindBy(id = "qty")
	private WebElement quantityInput;

	@FindBy(css = "select[id*='attribute']")
	private WebElement selectInput;
	
	@FindBy(css = "button#add-to-cart")
	private WebElement addToCartButton;
	
//	@FindBy(css = "div.add-to-cart-modal")
//	private WebElement popUpOK;

//	public void checkProductName(String productName) {
//		element(titleContainer).waitUntilVisible();
//		Assert.assertTrue("The product name is incorrect", titleContainer.getText().contains(productName));
//	}
//
//	public void checkProductPrice(String productPrice) {
//		element(priceContainer).waitUntilVisible();
//		Assert.assertTrue("The product name is incorrect", priceContainer.getText().contains(productPrice));
//	}

	public void setPrice(String qty) {
		element(quantityInput).waitUntilVisible();
		quantityInput.clear();
		quantityInput.sendKeys(qty);
	}

	public void selectValueFromDropDown(String size) {
		element(selectInput).waitUntilVisible();
		selectFromDropdown(selectInput, size);
	}
	
	public void addToCart(){
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
		
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div.add-to-cart-modal"), "Der Artikel wurde in den Warenkorb gelegt. Du kannst deinen Einkauf fortsetzen."));
		waitABit(Constants.TIME_CONSTANT);
	}

	// private void getSearchedProductElement(String productName) {
	// List<WebElement> productsList = getDriver().findElements(
	// By.cssSelector(".mini-products-list li"));
	// boolean found = false;
	// for (WebElement product : productsList) {
	// WebElement productNameContainer = product.findElement(By
	// .cssSelector(" .product-name"));
	// if (productNameContainer.getText().trim()
	// .contentEquals(productName))
	// found = true;
	// }
	//
	// Assert.assertTrue("The " + productName + " was not found", found);
	// }

	// public void verifyProductDetailsInExpandedWrapper(String productName,
	// String price, String qty) {
	// WebElement product = getSearchedProductElement(productName);
	// Assert.assertTrue("Product details are incorrect", product.getText()
	// .contains(price) && product.getText().contains(qty));
	// }

}

package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;

public class HeaderPage extends AbstractPage {

	@FindBy(id = "search")
	private WebElement searchInput;

	@FindBy(css = ".top-search-icon")
	private WebElement submitSearch;

	@FindBy(id = "#add-to-cart")
	private WebElement addToCartButton;
	
	@FindBy(css = "a.button[href*='cart']")
	private WebElement goToCartButton;

	@FindBy(css = "div.top-cart span")
	private WebElement shoppingBagButton;
	
	@FindBy(css = "div#topCartContent p.subtotal span.price")
	private WebElement cartPreviewPrice;

	public void searchInput(String seachKey) {
		element(searchInput).waitUntilVisible();
		searchInput.sendKeys(seachKey);
	}

	public void clickOnSubmitButton() {
		element(submitSearch).waitUntilVisible();
		submitSearch.click();
	}

	public void clickAddToCart() {
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
	}

	public void clickGoToCart() {
		element(goToCartButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(goToCartButton));
		goToCartButton.click();
	}

	public void clickShoppingBag() {
		element(shoppingBagButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(shoppingBagButton));
		shoppingBagButton.click();
	}
	
	public String getShoppingBagTotalSum(){
		element(cartPreviewPrice).waitUntilVisible();
		return cartPreviewPrice.getText();
	}

}

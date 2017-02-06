package com.pages.frontend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class HeaderPage extends AbstractPage {

	@FindBy(id = "search")
	private WebElementFacade searchInput;

	@FindBy(css = "div.quick-access")
	private WebElementFacade succesLoginContainer;

	@FindBy(css = ".top-search-icon")
	private WebElement submitSearch;

//	@FindBy(id = "#add-to-cart")
//	private WebElement addToCartButton;

	@FindBy(css = "a.button[href*='cart']")
	private WebElement goToCartButton;

	@FindBy(css = "div.top-cart span")
	private WebElement shoppingBagButton;

	@FindBy(css = "div#topCartContent p.subtotal span.price")
	private WebElement cartPreviewPrice;

	@FindBy(css = "div.quick-access ul.links li:nth-child(2) a")
	private WebElement profileButton;

	@FindBy(css = "div.categories>ul.clearfix li:last-child a")
	private WebElement loungeButton;

	@FindBy(css = "ul.links li:first-child a")
	private WebElement wishlist;

	@FindBy(css = "ul.links>.last a")
	private WebElement anmeldenButton;

	@FindBy(css = "ul.links>.last a")
	private WebElement abmeldenButton;

	@FindBy(css = "div.branding p")
	private WebElement brandContainer;

	@FindBy(css = "div.switcher-wrapper")
	private WebElement websiteContainer;
	
	@FindBy(css = "div.categories>ul.clearfix li:first-child a")
	private WebElement shopButton;

	public void selectLanguage(String language) {
		element(websiteContainer).waitUntilVisible();
		List<WebElement> languagesList = websiteContainer.findElements(By.cssSelector("ul#select-website > li a"));
		for (WebElement lang : languagesList) {
			if (lang.getText().contentEquals(language.toUpperCase())) {
				lang.click();
				break;
			}
		}

	}

	public void verifyThatLanguageFromHeaderIsCorrectySelected(String website) {
		List<WebElement> websites = websiteContainer.findElements(By.cssSelector("li span"));
		boolean found = false;
		for (WebElement web : websites) {
			if (web.getText().contentEquals(website.toUpperCase())) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("The selected language is not the correct one !!!", found);
	}

	public void searchInput(String seachKey) {
		element(searchInput).waitUntilClickable();
		searchInput.sendKeys(seachKey);
	}

	public void clickOnSubmitButton() {
		element(submitSearch).waitUntilVisible();
		submitSearch.click();
	}

	public void clickOnProfileButton() {
		element(profileButton).waitUntilVisible();
		profileButton.click();
	}

	public void clickOnWishlistButton() {
		element(wishlist).waitUntilVisible();
		wishlist.click();
	}

//	public void clickAddToCart() {
//		element(addToCartButton).waitUntilVisible();
//		addToCartButton.click();
//	}

	public void clickGoToCart() {
		element(goToCartButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(goToCartButton));
		goToCartButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickShoppingBag() {
		element(shoppingBagButton).waitUntilVisible();
		element(shoppingBagButton).waitUntilEnabled();
		waitFor(ExpectedConditions.elementToBeClickable(shoppingBagButton));
		shoppingBagButton.click();
		waitABit(1000);
	}

	public String getShoppingBagTotalSum() {
		element(cartPreviewPrice).waitUntilVisible();
		return cartPreviewPrice.getText();
	}

	public void clickAnmeldenButton() {
		element(anmeldenButton).waitUntilVisible();
		anmeldenButton.click();
	}

	public void clickAbmeldenButton() {
		element(abmeldenButton).waitUntilVisible();
		abmeldenButton.click();
	}

	public void clickLounge() {
		element(loungeButton).waitUntilVisible();
		loungeButton.click();
	}

	public String getBoutiqueName() {
		return brandContainer.getText().split("'")[0].toLowerCase();
	}

	public boolean succesfullLogin() {
		return succesLoginContainer.getText().contains("¡BIENVENIDO,") || succesLoginContainer.getText().contains("HI,");
	}
	
	public void clickShop() {
		element(shopButton).waitUntilVisible();
		shopButton.click();
	}

}

package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

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

//	@FindBy(css = "a[title='Profil']")
	 @FindBy(css = "div.quick-access.clearfix ul.links li:nth-child(2) a")     //int	
	private WebElement profileButton;

//	@FindBy(css = "a[title='Style Coach Lounge']")
	 @FindBy(css = "div.categories>ul.clearfix li:last-child a") //int
	private WebElement loungeButton;

//	@FindBy(css = "a[title='Anmelden']")
	 @FindBy(css = "ul.links>.last a") //int
	private WebElement anmeldenButton;

//	@FindBy(css = "a[title='Abmelden']")
	 @FindBy(css = "ul.links>.last a") //int
	private WebElement abmeldenButton;

	@FindBy(css = "div.branding p")
	private WebElement brandContainer;

	@FindBy(css = "div.switcher-wrapper")
	private WebElement websiteContainer;

	public void selectLanguage(String language) {
		element(websiteContainer).waitUntilVisible();
		List<WebElement> languagesList = getDriver().findElements(By.cssSelector("ul#select-website > li"));
		for (WebElement lang : languagesList) {
			if (lang.getText().contentEquals(language)) {
				try {
					lang.findElement(By.cssSelector("a")).click();
					break;
				} catch (Exception e) {
					lang.click();
					break;
				}
			}
		}
	}

	public void searchInput(String seachKey) {
		element(searchInput).waitUntilVisible();
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

	public String getStyleCoachFirstNameFromProfile() {

		String styleCoachNameParts[] = null;
		List<WebElement> infoBoxList = getDriver().findElements(By.cssSelector(".info-box"));
		for (WebElement infoBox : infoBoxList) {
			if (infoBox.getText().contains(ContextConstants.MEIN_STYLE_COACH)) {
				styleCoachNameParts = infoBox.findElement(By.cssSelector("dl dd")).getText().split(" ");
				break;
			}
		}
		return styleCoachNameParts[0].toLowerCase();
	}

	public void navigateToPartyPage(String url) {
		getDriver().get(url);
	}

}

package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.Constants;
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

	@FindBy(css = "a[title='Profil']")
	private WebElement profileButton;
	
	@FindBy(css = "a[title='Style Coach Lounge']")
	private WebElement loungeButton;

	@FindBy(css = "a[title='Anmelden']")
	private WebElement anmeldenButton;

	@FindBy(css = "a[title='Abmelden']")
	private WebElement abmeldenButton;

	@FindBy(css = "div.branding p")
	private WebElement brandContainer;

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
		
		String styleCoachNameParts[] = null ;
		List<WebElement> infoBoxList = getDriver().findElements(By.cssSelector(".info-box"));
		for (WebElement infoBox : infoBoxList) {
			if (infoBox.getText().contains("MEIN STYLE COACH")) {
				styleCoachNameParts = infoBox.findElement(By.cssSelector("dl dd")).getText().split(" ");
				break;
			}
		}
		return styleCoachNameParts[0].toLowerCase();
	}
	
	public void navigateToPartyPage(String partyId){
		getDriver().get(Constants.PARTY_DETAILS_URL + partyId);
	}

}

package com.pages.frontend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class HeaderPage extends AbstractPage {

	@FindBy(id = "search")
	private WebElementFacade searchInput;

	@FindBy(css = "div.quick-access")
	private WebElementFacade succesLoginContainer;

	@FindBy(css = ".quick-access p")
	private WebElementFacade succesLoginContainerES;

	@FindBy(css = ".top-search-icon")
	private WebElement submitSearch;

	@FindBy(css = ".links li:nth-child(3) a")
	private WebElement log0utBtn;

	// @FindBy(id = "#add-to-cart")
	// private WebElement addToCartButton;

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
	
	
	@FindBy(css = ".footer #select-website li .en")
	private WebElement enWebsite;
	
	@FindBy(css = ".footer #select-website li .es")
	private WebElement esWebsite;
	
	@FindBy(css = ".footer #select-website li .de")
	private WebElement deWebsite;
	
	@FindBy(css = "ul.links>.last a")
	private WebElement abmeldenButton;

	@FindBy(css = "div.branding p")
	private WebElement brandContainer;

	@FindBy(css = "div.switcher-wrapper")
	private WebElement websiteContainer;

	@FindBy(css = "div.categories>ul.clearfix li:first-child a")
	private WebElement shopButton;

	@FindBy(css = "a[id*='facebookShare']")
	private WebElement shareKoboLink;

	@FindBy(css = ".link-list.iconed li:nth-child(1) a")
	private WebElement shareOnlineBoutiqueLink;

	@FindBy(css = ".link-list.iconed li:nth-child(2) a")
	private WebElement inviteFriendsLink;
	
	
	@FindBy(css = ".links li.last a")
	private WebElement logOutBtn;
	
	

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
		clickElement(profileButton);
		// profileButton.click();
	}

	public void clickOnWishlistButton() {
		element(wishlist).waitUntilVisible();
		wishlist.click();
	}

	// public void clickAddToCart() {
	// element(addToCartButton).waitUntilVisible();
	// addToCartButton.click();
	// }

	public void clickGoToCart() {
		element(goToCartButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(goToCartButton));
		goToCartButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
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

	public void switchDEWebsite() {
		element(deWebsite).waitUntilVisible();
		deWebsite.click();
	}

	public void switchESWebsite() {
		element(esWebsite).waitUntilVisible();
		esWebsite.click();
	}

	public void switchWebsite(String website) {
		waitABit(1000);
		List<WebElement> websiteSwitcher=getDriver().findElements(By.cssSelector(".footer #select-website li ."+website+""));
		if(websiteSwitcher.size()!=0){
			websiteSwitcher.get(0).click();
		}else{
			System.out.println("Failure: The website cannot be selected");
		}
		
	}

	public void clickAbmeldenButton() {
		element(abmeldenButton).waitUntilVisible();
		abmeldenButton.click();
	}

	public void clickLounge() {
		element(loungeButton).waitUntilVisible();
		loungeButton.click();
		waitABit(5000);
	}

	public String getBoutiqueName() {
		return brandContainer.getText().split("'")[0].toLowerCase();
	}

	public boolean succesfullLogin() {
		return succesLoginContainer.getText().contains("¡BIENVENIDO,")
				|| succesLoginContainer.getText().contains("HI,");
	}

	public void checkSucesfullLogin() {
		Assert.assertTrue("Failure: The login fails ", succesLoginContainer.getText().contains("¡BIENVENIDO,")
				|| succesLoginContainer.getText().contains("HI,"));
	}

	public void checkSucesfullLoginDE() {
		Assert.assertTrue("Failure: The login fails ", succesLoginContainer.getText().contains("HI,"));
	}

	public void checkSucesfullLoginES() {
		Assert.assertTrue("Failure: The login fails ", succesLoginContainer.getText().contains("¡BIENVENIDO,"));
		// List<WebElement> element =
		// getDriver().findElements(By.cssSelector(".quick-access .message"));
		// if(element.size()!=0){
		// System.out.println("printeaza ceva: "+ element.get(0).getText());
		// //System.out.println("succesLoginContainer.getText(): "
		// +succesLoginContainerES.getText());
		// CustomVerification.verifyTrue("Failure: The login fails
		// ",element.get(0).getText().contains("¡Bienvenido,"));
		// }
		//
		// System.out.println("succesLoginContainer.getText(): "
		// +succesLoginContainerES.getText());
		// Assert.assertTrue("Failure: The login fails
		// ",succesLoginContainerES.getText().contains("¡Bienvenido,"));
	}

	public void clickShop() {
		element(shopButton).waitUntilVisible();
		shopButton.click();
	}

	public void verifyWebsite(String website) {
		System.out.println("url: " + getDriver().getCurrentUrl());
		Assert.assertTrue("Failure: The Website is not correct ", getDriver().getCurrentUrl().contains(website));
	}

	public void verifyStoreView(String storeView) {
		Assert.assertTrue("Failure: The Website is not correct ", getDriver().getCurrentUrl().contains(storeView));
	}

	public void clickOnKoboShare() {
		// TODO Auto-generated method stub
		element(shareKoboLink).waitUntilVisible();
		shareKoboLink.click();
		waitABit(5000);
	}

	public void clickOnShareOnlineBootique() {
		element(shareOnlineBoutiqueLink).waitUntilVisible();
		shareOnlineBoutiqueLink.click();
		waitABit(5000);
	}

	public void verifyStylistContext(String context) {
		System.out.println("url: " + getDriver().getCurrentUrl());
		Assert.assertTrue("Failure: The Website is not correct ", getDriver().getCurrentUrl().contains(context));

	}

	public void clickInviteFacebookFriends() {
		// TODO Auto-generated method stub
		element(inviteFriendsLink).waitUntilVisible();
		inviteFriendsLink.click();
		waitABit(5000);
	}

	public void checkSucesfullLoginInPippa() {
		// TODO Auto-generated method stub
		CustomVerification.verifyTrue("The user seems to not be logged in Pippajean",
				log0utBtn.getText().contains("ABMELDEN"));
	}

	public void performLogOut() {
		element(logOutBtn).waitUntilVisible();
		logOutBtn.click();
	}

}

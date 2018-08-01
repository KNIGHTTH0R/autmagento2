package com.pages.frontend.checkout.cart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class GeneralCartPage extends AbstractPage {

	@FindBy(css = "button[value='empty_cart']")
	// @FindBy(css = "button[title='Warenkorb leeren']")
	private WebElement clearCartButton;

	@FindBy(css = ".column.main")
	private WebElement cartContainer;

	public void clickClearCart() {
		/*
		 * element(clearCartButton).waitUntilVisible(); clearCartButton.click();
		 */

		waitABit(2000);
		List<WebElement> miniCartproducts = getDriver().findElements(By.cssSelector("#shopping-cart-table .cart.item"));
		for (WebElement product : miniCartproducts) {
			List<WebElement> sameminiCartproducts = getDriver()
					.findElements(By.cssSelector("#shopping-cart-table .cart.item"));
			System.out.println(sameminiCartproducts.get(0).getText());
			clickElement(sameminiCartproducts.get(0).findElement(By.cssSelector("a[title*='Remove']")));
			waitABit(3000);

		}

	}

	public boolean isCartEmpty() {
		return cartContainer.getText().contains(ContextConstants.EMPTY_CART);
	}
	
	public void verifyIsCartEmpty() {
		CustomVerification.verifyTrue("Failure: cart is not empty ", isCartEmpty());
	}
}

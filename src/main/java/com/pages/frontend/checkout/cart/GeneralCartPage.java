package com.pages.frontend.checkout.cart;

import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class GeneralCartPage extends AbstractPage {
	
	@FindBy(css = "button[value='empty_cart']")
//	@FindBy(css = "button[title='Warenkorb leeren']")
	private WebElement clearCartButton;
	
	@FindBy(css = "div.main.col1-layout")
	private WebElement cartContainer;

	public void clickClearCart() {
		element(clearCartButton).waitUntilVisible();
		clickElement(clearCartButton);
	//	clearCartButton.click();
	}
	
	public boolean isCartEmpty(){
		return cartContainer.getText().contains(ContextConstants.EMPTY_CART);
	}
}

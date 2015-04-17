package com.pages.frontend.checkout.wishlist;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class WishlistPage extends AbstractPage{

//	@FindBy(css = "div.buttons-set.to-the-right button[title*='Alles in den Warenkorb']")
	@FindBy(css = "div.buttons-set.to-the-right button[type*='button']")   //int
	private WebElement addAllToCArt;
	
	public void addAllProductsToCArt() {
		element(addAllToCArt).waitUntilVisible();
		addAllToCArt.click();
	}
}

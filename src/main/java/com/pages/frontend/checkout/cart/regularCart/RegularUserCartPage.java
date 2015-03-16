package com.pages.frontend.checkout.cart.regularCart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class RegularUserCartPage extends AbstractPage {

	public void selectProductDiscountType(String productCode, String discountType) {
		List<WebElement> productsList = getDriver().findElements(By.cssSelector("#shopping-cart-table tbody tr"));
		for (WebElement product : productsList) {
			if (product.getText().contains(productCode)) {
				element(product.findElement(By.cssSelector("select"))).selectByVisibleText(discountType);
			}
		}
	}

}

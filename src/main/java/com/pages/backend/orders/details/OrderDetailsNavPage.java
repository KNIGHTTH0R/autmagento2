package com.pages.backend.orders.details;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class OrderDetailsNavPage extends AbstractPage {

	@FindBy(id = "sales_order_view_tabs")
	private WebElement navContainer;

	public void selectMenu(String menu) {
		List<WebElement> menuList = navContainer.findElements(By.cssSelector("li a"));
		boolean found = false;
		for (WebElement webElement : menuList) {
			if (webElement.getText().contains(menu)) {
				webElement.click();
				found = true;
				break;
			}
		}
		Assert.assertTrue("The menu was not found", found);
	}

}

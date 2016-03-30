package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ReportsPage extends AbstractPage {

	@FindBy(css = "li a[href*='exportInventoryReport/order/sku/']")
	private WebElement downloadBySkuLink;

	public void downloadProductsOrderedBySku() {
		
		element(downloadBySkuLink).waitUntilVisible();
		downloadBySkuLink.click();
		waitABit(10000);
	}

}

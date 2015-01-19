package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class CartPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types button:last-child")
	private WebElement kasseButton;

	/**
	 * Will grab all products data from the cart
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsData() {
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("input")).getAttribute("value"));
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
			productNow.setFinalPrice(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			productNow.setPriceIP(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText());

			resultList.add(productNow);
		}

		return resultList;
	}

	public CartTotalsModel grabTotals() {
		CartTotalsModel resultModel = new CartTotalsModel();
		element(totalsTable).waitUntilVisible();

		resultModel.setSubtotal(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(1) > td:last-child")).getText()));
		resultModel.setJewelryBonus(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(2) input#jewelry_credits")).getAttribute("value")));
		resultModel.setDiscount(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(3) > td:last-child")).getText()));
		resultModel.setTax(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(4) > td:last-child")).getText()));
		resultModel.setShipping(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(5) > td:last-child")).getText()));
		resultModel.setShipping(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(5) > td:last-child")).getText()));
		resultModel.setTotalAmount(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tr.grandtotal > td:last-child")).getText()));
		resultModel.setIpPoints(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tfoot tr:nth-child(2) > td:last-child")).getText()));

		return resultModel;
	}

	public void clickToCheckout() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}

}

package com.pages.frontend.checkout.shipping;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;
import com.tools.PrintUtils;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.persistance.MongoTableKeys;

public class SurveyPage extends AbstractPage {

	@FindBy(css = "div.checkout-totals-section")
	private WebElement surveyTotalsContainer;

	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;

	@FindBy(css = "div#cart-section-1 div.items-section")
	private WebElement productListContainer;

	/**
	 * Note: Only the Subtotal, Discount, Shipping Tax and Total Amount are
	 * passed in the model
	 * 
	 * @return
	 */
	public CartTotalsModel grabSurveyData() {
		CartTotalsModel result = new CartTotalsModel();
		element(surveyTotalsContainer).waitUntilVisible();

		result.setSubtotal(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(1) td.a-right")).getText()));
		result.addDiscount(MongoTableKeys.DISCOUNT_KEY, PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShipping(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));

		return result;
	}

	public void clickGoToPaymentMethod() {
		element(toPaymentButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(toPaymentButton));
		toPaymentButton.click();
	}

	public List<CartProductModel> grabProductsList() {

		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(PrintUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()));
			productNow.setProdCode(PrintUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim()));
			productNow.setQuantity(PrintUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText()));
			productNow.setUnitPrice(PrintUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		}

		return resultList;
	}

}

package com.pages.frontend.checkout.shipping;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

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
	public ShippingModel grabSurveyData() {
		ShippingModel result = new ShippingModel();
		element(surveyTotalsContainer).waitUntilVisible();

		result.setSubTotal(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(1) td.a-right")).getText()));
		result.setDiscountPrice(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShippingPrice(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));

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

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim()));
			productNow.setQuantity(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText()));
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
			
			PrintUtils.printCartProductModel(productNow);
		}
		
		

		return resultList;
	}

}

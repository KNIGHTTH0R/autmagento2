package com.pages.frontend.checkout.shipping;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
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

		DataGrabber.shippingTotals = result;
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

			String parseQty = FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim()));
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);

			PrintUtils.printCartProductModel(productNow);
		}

		DataGrabber.shippingProducts = resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {

		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<RegularUserCartProductModel> resultList = new ArrayList<RegularUserCartProductModel>();

		for (WebElement webElementNow : entryList) {
			RegularUserCartProductModel productNow = new RegularUserCartProductModel();

			String parseQty = FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim()));
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		RegularUserDataGrabber.grabbedRegularShippingProductsList = resultList;

		return resultList;
	}
	public List<HostCartProductModel> grabHostProductsList() {
		
		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();
		
		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();
			
			String parseQty = FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();
			
			productNow.setName(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()));
			productNow.setProdCode(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim()));
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);
			
		}
		
		HostDataGrabber.grabbedHostShippingProductsList = resultList;
		
		return resultList;
	}

}

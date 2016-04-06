package com.pages.frontend.checkout.shipping;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.HostDataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

public class SurveyPage extends AbstractPage {

	@FindBy(css = "div#cart-section-1 div.checkout-totals-section")
	private WebElement surveyTotalsContainer;

	@FindBy(css = "div#cart-section-2 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp1;

	@FindBy(css = "div#cart-section-3 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp2;

	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;

	@FindBy(css = "div#cart-section-1 div.items-section")
	private WebElement productListContainer;

	@FindBy(css = "div#cart-section-2 div.items-section")
	private WebElement productListContainerTp1;

	@FindBy(css = "div#cart-section-3 div.items-section")
	private WebElement productListContainerTp2;

	/**
	 * Note: Only the Subtotal, Discount, Shipping Tax and Total Amount are
	 * passed in the model
	 * 
	 * @return
	 */
	public ShippingModel grabSurveyData(WebElement element) {
		ShippingModel result = new ShippingModel();
		element(element).waitUntilVisible();

		result.setSubTotal(FormatterUtils
				.cleanNumberToString(element.findElement(By.cssSelector("tr:nth-child(1) td.a-right")).getText()));
		result.setDiscountPrice(FormatterUtils
				.cleanToInteger(element.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShippingPrice(FormatterUtils
				.cleanNumberToString(element.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(FormatterUtils
				.cleanNumberToString(element.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));

		DataGrabber.shippingTotals = result;
		return result;
	}

	public ShippingModel grabSurveyData() {
		ShippingModel result = grabSurveyData(surveyTotalsContainer);
		DataGrabber.shippingTotals = result;
		return result;
	}

	public ShippingModel grabSurveyDataTp0() {
		ShippingModel result = grabSurveyData(surveyTotalsContainer);
		DataGrabber.shippingTotalsTp0 = result;
		return result;
	}

	public ShippingModel grabSurveyDataTp1() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp1);
		DataGrabber.shippingTotalsTp1 = result;
		return result;
	}

	public ShippingModel grabSurveyDataTp2() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp2);
		DataGrabber.shippingTotalsTp2 = result;
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

			String parseQty = FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);

		}

		DataGrabber.shippingProducts = resultList;

		return resultList;
	}

	public List<BorrowedCartModel> grabBorrowedProductsList() {

		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<BorrowedCartModel> resultList = new ArrayList<BorrowedCartModel>();

		for (WebElement webElementNow : entryList) {
			BorrowedCartModel productNow = new BorrowedCartModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setUnitPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			productNow.setIpPoints("");

			resultList.add(productNow);

		}

		BorrowDataGrabber.grabbedBorrowShippingProductsList = resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<RegularUserCartProductModel> resultList = new ArrayList<RegularUserCartProductModel>();

		for (WebElement webElementNow : entryList) {
			RegularUserCartProductModel productNow = new RegularUserCartProductModel();

			String parseQty = FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		 RegularUserDataGrabber.grabbedRegularShippingProductsList =
		 resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularShippingProductsList = resultList;
		PrintUtils.printListRegularCartProductModel(resultList);
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp0() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp0 = resultList;
		PrintUtils.printListRegularCartProductModel(resultList);
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp1() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp1);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp1 = resultList;
		PrintUtils.printListRegularCartProductModel(resultList);
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp2() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp2);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp2 = resultList;
		PrintUtils.printListRegularCartProductModel(resultList);
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();

		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();

			String parseQty = FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();
			parseQty = parseQty.replace(" x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		HostDataGrabber.grabbedHostShippingProductsList = resultList;

		return resultList;
	}
	
	public List<HostCartProductModel> grabHostProductsList() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostShippingProductsList = resultList;
		PrintUtils.printListHostCartProductModel(resultList);
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp0() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostShippingProductsListTp0 = resultList;
		PrintUtils.printListHostCartProductModel(resultList);
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp1() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp1);
		HostDataGrabber.grabbedHostShippingProductsListTp1 = resultList;
		PrintUtils.printListHostCartProductModel(resultList);
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp2() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp2);
		HostDataGrabber.grabbedHostShippingProductsListTp2 = resultList;
		PrintUtils.printListHostCartProductModel(resultList);
		return resultList;
	}

}

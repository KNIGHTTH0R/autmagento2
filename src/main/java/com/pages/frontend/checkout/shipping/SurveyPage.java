package com.pages.frontend.checkout.shipping;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
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

import net.serenitybdd.core.annotations.findby.FindBy;

public class SurveyPage extends AbstractPage {

	@FindBy(css = ".data.table.table-totals tbody")
	private WebElement surveyTotalsContainer;

	@FindBy(css = "div#cart-section-2 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp1;

	@FindBy(css = "div#cart-section-3 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp2;
	
	//should check this part after dev implementation
	@FindBy(css = "div#cart-section-4 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp3;

	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;

	@FindBy(css = ".content.minicart-items ")
	private WebElement productListContainer;

	@FindBy(css = "div#cart-section-2 div.items-section")
	private WebElement productListContainerTp1;

	@FindBy(css = "div#cart-section-3 div.items-section")
	private WebElement productListContainerTp2;
	
	//should check this part after dev implementation
	@FindBy(css = "div#cart-section-4 div.items-section")
	private WebElement productListContainerTp3;

	/**
	 * Note: Only the Subtotal, Discount, Shipping Tax and Total Amount are
	 * passed in the model
	 * 
	 * @return
	 */
	public ShippingModel grabSurveyData(WebElement element) {
		ShippingModel result = new ShippingModel();
		//element(element).waitUntilVisible();
		waitABit(3000);
		result.setSubTotal(FormatterUtils.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr.totals.sub td")).getText()));
		/*result.setDiscountPrice(FormatterUtils.parseValueToTwoDecimals("-" + element.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShippingPrice(FormatterUtils.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));*/
		result.setTotalAmount(FormatterUtils.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr.grand.totals td")).getText()));

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
	
	public ShippingModel grabSurveyDataTp3() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp3);
		DataGrabber.shippingTotalsTp3 = result;
		return result;
	}

	public void clickGoToPaymentMethod() {
		element(toPaymentButton).waitUntilVisible();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitFor(ExpectedConditions.elementToBeClickable(toPaymentButton));
	//	toPaymentButton.click();
		clickElement(toPaymentButton);
		//toPaymentButton.sendKeys(Keys.RETURN);
	//	waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
      //  waitABit(3000);

	}

	public List<CartProductModel> grabProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			String parseQty = FormatterUtils.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		//	waitABit(2000);
		}

		DataGrabber.shippingProducts = resultList;
		System.out.println("grabProductsList in for ");
		return resultList;
	}
	
	public List<CartProductModel> grabProductsList() {
		waitABit(2000);
		scrollPageDown();
	//	element(productListContainer).waitUntilVisible();
		waitABit(2000);
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			String parseQty = FormatterUtils.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");
			
			System.out.println("Product grabbed: "+productNow.getName());
			resultList.add(productNow);
		//	waitABit(2000);
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
			productNow.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			productNow.setIpPoints("");

			resultList.add(productNow);

		}

		BorrowDataGrabber.grabbedBorrowShippingProductsList = resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector(".minicart-items >li"));
		List<RegularUserCartProductModel> resultList = new ArrayList<RegularUserCartProductModel>();

		for (WebElement webElementNow : entryList) {
			RegularUserCartProductModel productNow = new RegularUserCartProductModel();

			String parseQty = FormatterUtils.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector(".details-qty .value")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector(".product-item-name-block strong")).getText());
			productNow.setQuantity(parseQty);
		//	productNow.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector(".price")).getText()));
			resultList.add(productNow);

		}

		RegularUserDataGrabber.grabbedRegularShippingProductsList = resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularShippingProductsList = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp0() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp0 = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp1() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp1);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp1 = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp2() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp2);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp2 = resultList;
		return resultList;
	}
	
	public List<RegularUserCartProductModel> grabRegularProductsListTp3() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp3);
		RegularUserDataGrabber.grabbedRegularShippingProductsListTp3 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();

		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();

			String parseQty = FormatterUtils.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();
			parseQty = parseQty.replace(" x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		HostDataGrabber.grabbedHostShippingProductsList = resultList;

		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsList() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostShippingProductsList = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp0() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostShippingProductsListTp0 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp1() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp1);
		HostDataGrabber.grabbedHostShippingProductsListTp1 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp2() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp2);
		HostDataGrabber.grabbedHostShippingProductsListTp2 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp0() {
		List<CartProductModel> resultList = grabProductsList(productListContainer);
		DataGrabber.grabbedSFMShippingProductsListTp0 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp1() {
		List<CartProductModel> resultList = grabProductsList(productListContainerTp1);
		DataGrabber.grabbedSFMShippingProductsListTp1 = resultList;
		return resultList;
	}
	public List<CartProductModel> grabSFMProductsListTp2() {
		List<CartProductModel> resultList = grabProductsList(productListContainerTp2);
		DataGrabber.grabbedSFMShippingProductsListTp2 = resultList;
		return resultList;
	}
	
}

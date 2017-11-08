package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.Separators;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.AddressModel;
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

public class ConfirmationPage extends AbstractPage {

	@FindBy(css = "li.shipping-address-preview")
	private WebElement shippingContainer;

	@FindBy(css = "li.billing-address-preview")
	private WebElement billingContainer;

	@FindBy(css = "div.checkout-totals-section")
	private WebElement totalsContainer;

	@FindBy(id = "terms")
	private WebElement acceptTerms;

	@FindBy(id = "change-shipping-address")
	private WebElement changeShippingButton;

	@FindBy(id = "change-billing-address")
	private WebElement changeBillingButton;

	@FindBy(id = "submit-confirmation-step")
	private WebElement submitButton;

	@FindBy(css = "div#cart-section-1 div.items-section")
	private WebElement productListContainer;

	@FindBy(css = "div#cart-section-2 div.items-section")
	private WebElement productListContainerTp1;

	@FindBy(css = "div#cart-section-3 div.items-section")
	private WebElement productListContainerTp2;
	//check this after dev implementation
	@FindBy(css = "div#cart-section-4 div.items-section")
	private WebElement productListContainerTp3;

	@FindBy(css = "div#cart-section-1 div.checkout-totals-section")
	private WebElement surveyTotalsContainer;

	@FindBy(css = "div#cart-section-2 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp1;

	@FindBy(css = "div#cart-section-3 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp2;
	//check this after dev implementation
	@FindBy(css = "div#cart-section-4 div.checkout-totals-section")
	private WebElement surveyTotalsContainerTp3;

	private AddressModel grabAddressData(WebElement addressPreview) {
		waitABit(TimeConstants.TIME_MEDIUM);
		AddressModel result = new AddressModel();
		element(addressPreview).waitUntilVisible();
		String textparse = addressPreview.getText();
		String[] splittedText = textparse.split(Separators.LINE_SEPARATOR);
		String streetName;
		String streetNumber;

		if (splittedText.length == 4) {

		//	String[] streetData = splittedText[1].split(Separators.COMMA_SEPARATOR);
			String[] streetData = splittedText[1].split("\\s+");
			
			if(streetData.length > 2){
				 streetName = streetData[0].trim()+" "+streetData[1].trim();
				 streetNumber = streetData[2].trim();
			}else{
				 streetName = streetData[0].trim();
				 streetNumber = streetData[1].trim();
			}
			

			String[] townData = splittedText[2].split("\\s+");
			String homeTown = townData[1].trim();
			String postCode = townData[0].trim();

			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);

			result.setHomeTown(homeTown);
			result.setPostCode(postCode);

			result.setCountryName(splittedText[3].trim());

		}
		if (textparse.split(Separators.LINE_SEPARATOR).length == 5) {

			String[] streetData = splittedText[1].split("\\s+");
			
			if(streetData.length > 2){
				 streetName = streetData[0].trim()+" "+streetData[1].trim();
				 streetNumber = streetData[2].trim();
			}else{
				 streetName = streetData[0].trim();
				 streetNumber = streetData[1].trim();
			}

			
			String[] townData = splittedText[3].split("\\s+");
			String homeTown = townData[1].trim();
			String postCode = townData[0].trim();

			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);

			result.setHomeTown(homeTown);
			result.setPostCode(postCode);

			result.setCountryName(splittedText[4].trim());

		} else {
			System.out.println("FAILURE: error on shipping parsing - Confirmation Page");
		}

		return result;

	}

	public AddressModel grabBillingData() {
		AddressModel result = new AddressModel();
		element(billingContainer).waitUntilVisible();
		result = grabAddressData(billingContainer);
		DataGrabber.grabbedBillingAddress = result;
		return result;
	}

	public AddressModel grabShippingData() {
		AddressModel result = new AddressModel();
		element(shippingContainer).waitUntilVisible();
		result = grabAddressData(shippingContainer);
		DataGrabber.grabbedShippingAddress = result;
		return result;
	}

	public void clickIAgree() {
		waitABit(3000);
		element(acceptTerms).waitUntilVisible();
		acceptTerms.click();
	}

	public void changeShippingAddress() {
		element(changeShippingButton).waitUntilVisible();
		changeShippingButton.click();
	}

	public void changeBillingAddress() {
		element(changeBillingButton).waitUntilVisible();
		changeBillingButton.click();
	}

	public void clickOnSubmit() {
		element(submitButton).waitUntilVisible();
		//clickElement(submitButton);
		submitButton.click();
	}

	// public ShippingModel grabConfirmationTotals() {
	// ShippingModel result = new ShippingModel();
	// element(surveyTotalsContainer).waitUntilVisible();
	//
	// result.setSubTotal(FormatterUtils.cleanNumberToString(
	// surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(1)
	// td.a-right")).getText()));
	// result.setDiscountPrice(FormatterUtils.cleanNumberToString(
	// surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(2)
	// td.a-right")).getText()));
	// result.setShippingPrice(FormatterUtils.cleanNumberToString(
	// surveyTotalsContainer.findElement(By.cssSelector("tr.shipping_tax
	// td.a-right")).getText()));
	// result.setTotalAmount(FormatterUtils.cleanNumberToString(
	// surveyTotalsContainer.findElement(By.cssSelector("tr.grand_total
	// td.a-right")).getText()));
	//
	// DataGrabber.confirmationTotals = result;
	// return result;
	// }

	public ShippingModel grabSurveyData(WebElement element) {
		ShippingModel result = new ShippingModel();
		element(element).waitUntilVisible();

		result.setSubTotal(FormatterUtils
				.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr:nth-child(1) td.a-right")).getText()));
		result.setDiscountPrice(FormatterUtils
				.parseValueToTwoDecimals("-" + element.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShippingPrice(FormatterUtils
				.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(FormatterUtils
				.parseValueToTwoDecimals(element.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));

		DataGrabber.confirmationTotals = result;
		return result;
	}

	public ShippingModel grabConfirmationTotals() {
		ShippingModel result = grabSurveyData(surveyTotalsContainer);
		DataGrabber.confirmationTotals = result;
		return result;
	}

	public ShippingModel grabConfirmationTotalsTp0() {
		ShippingModel result = grabSurveyData(surveyTotalsContainer);
		DataGrabber.confirmationTotalsTp0 = result;
		return result;
	}

	public ShippingModel grabConfirmationTotalsTp1() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp1);
		DataGrabber.confirmationTotalsTp1 = result;
		return result;
	}

	public ShippingModel grabConfirmationTotalsTp2() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp2);
		DataGrabber.confirmationTotalsTp2 = result;
		return result;
	}
	
	public ShippingModel grabConfirmationTotalsTp3() {
		ShippingModel result = grabSurveyData(surveyTotalsContainerTp3);
		DataGrabber.confirmationTotalsTp3 = result;
		return result;
	}

	/**
	 * Due to consistency this method returns {@link CartProductModel} list.
	 * Only fileds set are: Name, ProdCode, Quantity, Unit Price. Left Blank:
	 * ProductsPrices, FinalPrice, PriceIP
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsList() {
		element(productListContainer).waitUntilVisible();
		List<WebElement> entryList = productListContainer.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			String parseQty = FormatterUtils
					.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		}

		DataGrabber.confirmationProducts = resultList;
		return resultList;
	}
	
	
	public List<CartProductModel> grabProductsList(WebElement element) {
		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			String parseQty = FormatterUtils
					.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		}

		DataGrabber.confirmationProducts = resultList;
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
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			productNow.setIpPoints("");

			resultList.add(productNow);
		}

		BorrowDataGrabber.grabbedBorrowConfirmationProductsList = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<RegularUserCartProductModel> resultList = new ArrayList<RegularUserCartProductModel>();

		for (WebElement webElementNow : entryList) {
			RegularUserCartProductModel productNow = new RegularUserCartProductModel();

			String parseQty = FormatterUtils
					.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		RegularUserDataGrabber.grabbedRegularConfirmationProductsList = resultList;

		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsList() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularConfirmationProductsList = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp0() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainer);
		RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp0 = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp1() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp1);
		RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp1 = resultList;
		return resultList;
	}

	public List<RegularUserCartProductModel> grabRegularProductsListTp2() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp2);
		RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp2 = resultList;
		return resultList;
	}
	
	public List<RegularUserCartProductModel> grabRegularProductsListTp3() {
		List<RegularUserCartProductModel> resultList = grabRegularProductsList(productListContainerTp3);
		RegularUserDataGrabber.grabbedRegularConfirmationProductsListTp3 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsList(WebElement element) {

		element(element).waitUntilVisible();
		List<WebElement> entryList = element.findElements(By.cssSelector("tbody > tr"));
		List<HostCartProductModel> resultList = new ArrayList<HostCartProductModel>();

		for (WebElement webElementNow : entryList) {
			HostCartProductModel productNow = new HostCartProductModel();

			String parseQty = FormatterUtils
					.parseValueToZeroDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();
			parseQty = parseQty.replace(" x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		HostDataGrabber.grabbedHostConfirmationProductsList = resultList;

		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsList() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostConfirmationProductsList = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp0() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainer);
		HostDataGrabber.grabbedHostConfirmationProductsListTp0 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp1() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp1);
		HostDataGrabber.grabbedHostConfirmationProductsListTp1 = resultList;
		return resultList;
	}

	public List<HostCartProductModel> grabHostProductsListTp2() {
		List<HostCartProductModel> resultList = grabHostProductsList(productListContainerTp2);
		HostDataGrabber.grabbedHostConfirmationProductsListTp2 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp0() {
		List<CartProductModel> resultList = grabProductsList(productListContainer);
		DataGrabber.grabbedSFMConfirmationProductsListTp0 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp1() {
		List<CartProductModel> resultList = grabProductsList(productListContainerTp1);
		DataGrabber.grabbedSFMConfirmationProductsListTp1 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp2() {
		List<CartProductModel> resultList = grabProductsList(productListContainerTp2);
		DataGrabber.grabbedSFMConfirmationProductsListTp2 = resultList;
		return resultList;
	}

	public List<CartProductModel> grabSFMProductsListTp3() {
		List<CartProductModel> resultList = grabProductsList(productListContainerTp3);
		DataGrabber.grabbedSFMConfirmationProductsListTp3 = resultList;
		return resultList;
	}

}

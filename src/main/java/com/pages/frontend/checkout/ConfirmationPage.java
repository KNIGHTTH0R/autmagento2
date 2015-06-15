package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.datahandlers.regularUser.RegularUserDataGrabber;
import com.tools.env.constants.Separators;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class ConfirmationPage extends AbstractPage {

	@FindBy(css = "li.shipping-address-preview")
	private WebElement shippingContainer;

	@FindBy(css = "li.billing-address-preview")
	private WebElement billingContainer;

	@FindBy(css = "div.checkout-totals-section")
	private WebElement totalsContainer;

	@FindBy(id = "terms")
	private WebElement acceptTerms;

	@FindBy(id = "submit-confirmation-step")
	private WebElement submitButton;

	@FindBy(css = "div#cart-section-1 div.items-section")
	private WebElement productListContainer;

	@FindBy(css = "div.checkout-totals-section")
	private WebElement surveyTotalsContainer;

	private AddressModel grabAddressData(WebElement addressPreview) {
		waitABit(TimeConstants.TIME_MEDIUM);
		AddressModel result = new AddressModel();
		element(addressPreview).waitUntilVisible();
		String textparse = addressPreview.getText();
		String[] splittedText = textparse.split(Separators.LINE_SEPARATOR);

		if (splittedText.length == 4) {

			String[] streetData = splittedText[1].split(Separators.COMMA_SEPARATOR);
			String streetName = streetData[0];
			String streetNumber = streetData[1];

			String[] townData = splittedText[2].split(Separators.COMMA_SEPARATOR);
			String homeTown = townData[0];
			String postCode = townData[1];

			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);

			result.setHomeTown(homeTown);
			result.setPostCode(postCode);

			result.setCountryName(splittedText[3]);

		}
		if (textparse.split(Separators.LINE_SEPARATOR).length == 5) {

			String[] streetData = splittedText[1].split(Separators.COMMA_SEPARATOR);
			String streetName = streetData[0];
			String streetNumber = streetData[1];

			String[] townData = splittedText[2].split(Separators.COMMA_SEPARATOR);
			String homeTown = townData[0];
			String postCode = townData[1];

			result.setStreetAddress(streetName);
			result.setStreetNumber(streetNumber);

			result.setHomeTown(homeTown);
			result.setPostCode(postCode);

			result.setCountryName(splittedText[3]);

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
		element(acceptTerms).waitUntilVisible();
		acceptTerms.click();
	}

	public void clickOnSubmit() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
	}

	public ShippingModel grabConfirmationTotals() {
		ShippingModel result = new ShippingModel();
		element(surveyTotalsContainer).waitUntilVisible();

		result.setSubTotal(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(1) td.a-right")).getText()));
		result.setDiscountPrice(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr:nth-child(2) td.a-right")).getText()));
		result.setShippingPrice(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(FormatterUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));

		DataGrabber.confirmationTotals = result;
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

			String parseQty = FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			parseQty = parseQty.replace("x", "").trim();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setProductsPrice("");
			productNow.setFinalPrice("");
			productNow.setPriceIP("");

			resultList.add(productNow);
		}

		DataGrabber.confirmationProducts = resultList;
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

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		RegularUserDataGrabber.grabbedRegularConfirmationProductsList = resultList;

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

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("dl.item-options")).getText().trim());
			productNow.setQuantity(parseQty);
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice("");
			resultList.add(productNow);

		}

		HostDataGrabber.grabbedHostConfirmationProductsList = resultList;

		return resultList;
	}

}

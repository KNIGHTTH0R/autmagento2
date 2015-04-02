package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.Constants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class ProductDetailsPage extends AbstractPage {

	@FindBy(id = "qty")
	private WebElement quantityInput;

	@FindBy(css = "select[id*='attribute']")
	private WebElement selectInput;

	@FindBy(css = "button#add-to-cart")
	private WebElement addToCartButton;

	@FindBy(css = "p.product-ids.dp-inbl")
	private WebElement productCode;

	@FindBy(css = "span[id*='product-price']")
	private WebElement productPrice;

	@FindBy(css = "h1.ff-Nb")
	private WebElement productName;

	public void setPrice(String qty) {
		element(quantityInput).waitUntilVisible();
		quantityInput.clear();
		quantityInput.sendKeys(qty);
	}

	public void selectValueFromDropDown(String size) {
		element(selectInput).waitUntilVisible();
		selectFromDropdown(selectInput, size);
	}

	public void addToCart() {
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div.add-to-cart-modal"), "Der Artikel wurde in den Warenkorb gelegt. Du kannst deinen Einkauf fortsetzen."));
		waitABit(Constants.TIME_CONSTANT);
	}

	public ProductBasicModel grabProductData() {
		ProductBasicModel result = new ProductBasicModel();
		element(productName).waitUntilVisible();

		// clean productCode
		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setType(type);
		result.setPrice(FormatterUtils.cleanNumberToString(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public BasicProductModel grabBasicProductData() {
		BasicProductModel result = new BasicProductModel();
		element(productName).waitUntilVisible();

		// clean productCode
		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.cleanNumberToString(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public RegularBasicProductModel grabRegularBasicProductData() {
		RegularBasicProductModel result = new RegularBasicProductModel();
		element(productName).waitUntilVisible();

		// clean productCode
		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.cleanNumberToString(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}

	public HostBasicProductModel grabHostBasicProductData() {
		HostBasicProductModel result = new HostBasicProductModel();
		element(productName).waitUntilVisible();

		// clean productCode
		String type = cleanProductCode(productCode.getText());

		result.setName(productName.getText());
		result.setProdCode(type);
		result.setUnitPrice(FormatterUtils.cleanNumberToString(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));

		return result;
	}
	
	private String cleanProductCode(String code){
		return code.replace("Artikelnummer: ", "");
	}
}

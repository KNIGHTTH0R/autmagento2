package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.frontend.ProductBasicModel;

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
	
	public void addToCart(){
		element(addToCartButton).waitUntilVisible();
		addToCartButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("div.add-to-cart-modal"), "Der Artikel wurde in den Warenkorb gelegt. Du kannst deinen Einkauf fortsetzen."));
		waitABit(Constants.TIME_CONSTANT);
	}

	public ProductBasicModel grabProductData() {
		ProductBasicModel result = new ProductBasicModel();
		element(productName).waitUntilVisible();
		
		//clean productCode
		String type = productCode.getText();
		type = type.replace("Artikelnummer: ", "");
		
		result.setName(productName.getText());
		result.setType(type);
		result.setPrice(PrintUtils.cleanNumberToString(productPrice.getText()));
		result.setQuantity(quantityInput.getAttribute("value"));
		
		return result;
	}

	

}

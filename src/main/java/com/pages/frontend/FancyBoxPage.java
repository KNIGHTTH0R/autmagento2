package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class FancyBoxPage extends AbstractPage {

	@FindBy(id = "fancybox-content")
	private WebElement fancyBoxContainer;

	@FindBy(id = "fancybox-close")
	private WebElement closeFancyBox;

	@FindBy(css = "select[id*='attribute']")
	private WebElement selectInput;

	@FindBy(css = "div.product-shop button")
	private WebElement submit;

	@FindBy(css = ".confirmation-button >.button.white-button.confirm-button")
	private WebElement goToShipping;

	public void closeFancyBox() {
		element(closeFancyBox).waitUntilVisible();
		closeFancyBox.click();
	}

	public void goToShipping() {
		element(goToShipping).waitUntilVisible();
		clickElement(goToShipping);
//		goToShipping.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void submitProduct() {
		element(submit).waitUntilVisible();
		submit.click();
	}

	public void selectValueFromDropDown(String size) {
		element(selectInput).waitUntilVisible();
		selectFromDropdown(selectInput, size);
	}

}

package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class FancyBoxPage extends AbstractPage {

	@FindBy(id = "fancybox-content")
	private WebElement fancyBoxContainer;

	@FindBy(id = "fancybox-close")
	private WebElement closeFancyBox;

	@FindBy(css = "select[id*='attribute']")
	private WebElement selectInput;

	public void closeFancyBox() {
		element(closeFancyBox).waitUntilVisible();
		closeFancyBox.click();
	}

	public void selectValueFromDropDown(String size) {
		element(selectInput).waitUntilVisible();
		selectFromDropdown(selectInput, size);
	}

}

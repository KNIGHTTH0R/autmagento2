package com.pages.frontend;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;

public class LoungePage extends AbstractPage {

	@FindBy(id = "search")
	private WebElement searchInput;

	@FindBy(css = ".top-search-icon")
	private WebElement submitSearch;

	public void searchInput(String seachKey) {
		element(searchInput).waitUntilVisible();
		searchInput.sendKeys(seachKey);
	}

	public void clickOnSubmitButton() {
		element(submitSearch).waitUntilVisible();
		submitSearch.click();
	}

}

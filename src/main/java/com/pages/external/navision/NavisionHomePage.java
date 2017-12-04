package com.pages.external.navision;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NavisionHomePage extends AbstractPage {
	
	@FindBy(css = "a[title='Search for Page or Report...']")
	private WebElement searchButton;
	
	
	

	
	public void clickSearchButton() {
		element(searchButton).waitUntilVisible();
		searchButton.click();
	}

	
	
}

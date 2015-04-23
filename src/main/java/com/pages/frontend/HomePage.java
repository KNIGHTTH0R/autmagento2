package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class HomePage extends AbstractPage {

	@FindBy(css = "div.categories li:nth-child(3) a")
	private WebElement styleCoachLink;

	@FindBy(css = "#cssmenu > ul > li:first-child a")
	private WebElement generalView;

	@FindBy(css = "#cssmenu > ul > li:nth-child(2) > a")
	private WebElement jewelriesMenu;

	@FindBy(css = "#cssmenu > ul > li:nth-child(2) > ul li:nth-child(1) > a")
	private WebElement newMenu;

	public void clickStyleCoachLink() {
		styleCoachLink.click();

	}

	public void clickonGeneralView() {
		element(generalView).waitUntilVisible();
		generalView.click();
		waitABit(2000);

	}

	public void goToNewItems() {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(jewelriesMenu).build().perform();
		element(newMenu).waitUntilVisible();
		newMenu.click();

	}

}

package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class LoungePage extends AbstractPage {

//	@FindBy(css = "a[title='Mein Business']")
	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2)")           //int
	private WebElement meinBusinessButton;

//	@FindBy(css = "a[title='Style Party erstellen']")
	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1) > ul li:nth-child(2) a")    //int
	private WebElement createPartyButton;

//	@FindBy(css = "ul.dropdown li:nth-child(1) label span")
	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1)")     //int
	private WebElement stylePartiesLink;

	public void clickMeinBusiness() {
		element(meinBusinessButton).waitUntilVisible();
		meinBusinessButton.click();
	}

	public void clickCreateParty() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(stylePartiesLink).build().perform();

		element(createPartyButton).waitUntilVisible();
		createPartyButton.click();

	}

}

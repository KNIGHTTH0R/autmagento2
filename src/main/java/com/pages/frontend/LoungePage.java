package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class LoungePage extends AbstractPage {

	@FindBy(css = "a[title='Mein Business']")
	private WebElement meinBusinessButton;

	@FindBy(css = "a[title='Style Party erstellen']")
	private WebElement createPartyButton;

	@FindBy(css = "ul.dropdown li:nth-child(1) label span")
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

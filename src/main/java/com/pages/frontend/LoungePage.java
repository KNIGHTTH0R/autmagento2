package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class LoungePage extends AbstractPage {

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2)")   
	private WebElement meinBusinessButton;
	
	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) a")
	private WebElement meinBusinessLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1) > ul li:nth-child(2) a")    
	private WebElement createPartyButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1)")     
	private WebElement stylePartiesLink;

	public void goToMyBusiness() {
		element(meinBusinessButton).waitUntilVisible();
		meinBusinessLink.click();
	}

	public void clickCreateParty() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(stylePartiesLink).build().perform();

		element(createPartyButton).waitUntilVisible();
		createPartyButton.click();

	}

}

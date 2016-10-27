package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StylistCampaignPage extends AbstractPage {

	@FindBy(css = "#starter-sets a")
	private WebElement jetztStartenButton;

	@FindBy(css = "div.full-width.chef a")
	private WebElement starteJetztButton;
	
	@FindBy(css = "a[href*='/stylist/register/'].btn.btn-pink")
	private WebElement startersetOrderButton;

	public void clickJetztStartenButton() {
		jetztStartenButton.click();
	}

	public void clickStarteJetztButton() {
		starteJetztButton.click();
	}
	
	public void clickStartersetOrderButton() {
		startersetOrderButton.click();
	}

}

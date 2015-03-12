package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StylistCampaignPage extends AbstractPage {
	
	@FindBy(css = "#starter-sets a")
	private WebElement jetztStartenButton;
	
	public void clickJetztStartenButton() {
		jetztStartenButton.click();
	}
	
}

package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StarterSetPage extends AbstractPage {
	

	@FindBy(css = ".text-center >.big-btn.gold-btn.bordered")
	private WebElement jetztStyleCoachWerdenButton;
	
	public void clickOnJetztStyleCoachWerdenButton() {
		element(jetztStyleCoachWerdenButton).waitUntilVisible();
		jetztStyleCoachWerdenButton.click();
	}

}

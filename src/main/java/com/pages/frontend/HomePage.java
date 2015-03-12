package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class HomePage extends AbstractPage {
	
	@FindBy(css = "div.categories li:nth-child(3) a")
	private WebElement styleCoachLink;

	
	public void clickStyleCoachLink() {
		styleCoachLink.click();
		
	}

	
}

package com.pages.backend.stylecoach;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StylecoachDetailsBackendPage extends AbstractPage {

	@FindBy(id = "activated_at")
	private WebElement activatedAtInput;

	public void inputActivatedAtDate(String date) {
		evaluateJavascript("jQuery.noConflict();");
		element(activatedAtInput).waitUntilVisible();
		activatedAtInput.clear();
		activatedAtInput.sendKeys(date);

	}

}

package com.pages.backend.stylecoach;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class SosDetailsBackendPage extends AbstractPage {

	@FindBy(id = "_sosinfosos_password")
	private WebElement activatedAtInput;

	@FindBy(css = "_sosinfosos_sync")
	private WebElement save;

	public void inputActivatedAtDate(String date) {
		evaluateJavascript("jQuery.noConflict();");
		element(activatedAtInput).waitUntilVisible();
		activatedAtInput.clear();
		activatedAtInput.sendKeys(date);

	}

	public void saveStylecoach() {
		evaluateJavascript("jQuery.noConflict();");
		element(save).waitUntilVisible();
		save.click();
		waitForPageToLoad();

	}

}

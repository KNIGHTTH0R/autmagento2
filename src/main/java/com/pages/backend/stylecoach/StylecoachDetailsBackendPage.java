package com.pages.backend.stylecoach;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class StylecoachDetailsBackendPage extends AbstractPage {

	@FindBy(id = "activated_at")
	private WebElement activatedAtInput;

	@FindBy(css = "button[onclick*='editForm.submit();']")
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

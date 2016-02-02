package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class StylistContextPage extends AbstractPage {

	@FindBy(id = "stylistref")
	private WebElement stylistref;

	@FindBy(id = "submit-step")
	private WebElement submitStepButton;

	public void submitStep() {
		element(submitStepButton).waitUntilVisible();
		submitStepButton.click();
	}

	public void inputStylistRef(String ref) {
		element(stylistref).waitUntilVisible();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		stylistref.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(stylistref).sendKeys(ref);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public void inputContextCodeAndValdiateErrorMessage(String postCode) {

		element(stylistref).waitUntilVisible();
		stylistref.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(stylistref).typeAndEnter(postCode);
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

}

package com.pages.frontend.checkout.cart.kobo;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class ContactBoosterCartPage extends AbstractPage {
	
	@FindBy(css = "ul.checkout-types li button[onclick*='/simplecheckout/']")
	private WebElement kasseButton;

	public void selectContactBooster100Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2493");
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}
	public void selectContactBooster200Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_13270");
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}
	
	public void selectContactBooster50Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2564");
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}
	public void selectContactBooster25Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2563");
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}
	
	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
		waitForLoadingImageToDissapear();
		//waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}


}

package com.pages.frontend.checkout.cart.kobo;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class ContactBoosterCartPage extends AbstractPage {
	
	@FindBy(css = "ul.checkout-types li button[onclick*='/simplecheckout/']")
	private WebElement kasseButton;

	public void selectContactBooster100Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2493");
	}
	public void selectContactBooster200Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_13270");
	}
	
	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}


}

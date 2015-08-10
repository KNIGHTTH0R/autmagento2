package com.pages.frontend.checkout.cart.kobo;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class ContactBoosterCartPage extends AbstractPage {
	
	@FindBy(css = "ul.checkout-types li button[onclick*='/simplecheckout/']")
	private WebElement kasseButton;

	public void selectContactBooster100Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2564");
	}
	public void selectContactBooster50Voucher() {
		waitABit(TimeConstants.TIME_MEDIUM);
		elementjQueryClick("input#kit_2563");
	}
	
	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}


}

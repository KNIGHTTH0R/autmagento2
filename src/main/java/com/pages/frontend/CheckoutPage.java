package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;

public class CheckoutPage extends AbstractPage {

	@FindBy(css = "select[id*='billing']")
	private WebElement selectAddressInput;
	
	@FindBy(css = "li.address-preview.billing-address-preview")
	private WebElement addressPreview;
	
	@FindBy(id = "new-billing-address")
	private WebElement newAddressButton;	
	

	public void selectAddressFromDropDown(String address) {
		element(selectAddressInput).waitUntilVisible();
		selectFromDropdown(selectAddressInput, address);
	}

}

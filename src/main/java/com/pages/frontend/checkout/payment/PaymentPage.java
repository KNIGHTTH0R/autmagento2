package com.pages.frontend.checkout.payment;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

/**
 * Main page from payment package
 * 
 * @author voicu.vac
 *
 */
public class PaymentPage extends AbstractPage {

	@FindBy(css = "input[value='Kreditkarte']")
	private WebElement creditCardContainer;

	public void expandCreditCardForm() {
		element(creditCardContainer).waitUntilVisible();
		creditCardContainer.click();
	}

}

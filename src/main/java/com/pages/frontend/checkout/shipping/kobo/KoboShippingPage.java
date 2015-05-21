package com.pages.frontend.checkout.shipping.kobo;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class KoboShippingPage extends AbstractPage {

	@FindBy(css = "input#terms")
	private WebElement acceptTerms;

	public void acceptTerms() {
		element(acceptTerms).waitUntilVisible();
		acceptTerms.click();
	}

}

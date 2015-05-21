package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.tools.requirements.AbstractPage;

public class MyBusinessPage extends AbstractPage {

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(1) a")
	private WebElementFacade accesKoboCart;

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(2) a")
	private WebElementFacade cancelSubstription;

	@FindBy(css = "span.cb-code")
	private WebElementFacade coboCodeContainer;

	@FindBy(css = "#kobo-cancel div.col-3.col")
	private WebElementFacade coboSection;

	public void verifyThatNumberOfLinksAreEqualTo(int expectedNoOflinks) {
		Assert.assertTrue("", getDriver().findElements(By.cssSelector("#kobo-cancel div.col-3.col ul.link-list li")).size() == expectedNoOflinks);
	}

	public void accessKoboCart() {
		element(accesKoboCart).waitUntilVisible();
		accesKoboCart.click();
	}

	public void cancelSubstription() {
		element(cancelSubstription).waitUntilVisible();
		cancelSubstription.click();
	}

	public void verifyThatKoboCodeWasGeneratetInLoungePage() {
		Assert.assertTrue("The voucher code is not present in my business page", !coboCodeContainer.getText().contentEquals(null));
	}

	public void verifyKoboSectionContainsText(String text) {
		Assert.assertTrue("The text does not appear in kobo section from my business page", coboSection.getText().contains(text));
	}

}

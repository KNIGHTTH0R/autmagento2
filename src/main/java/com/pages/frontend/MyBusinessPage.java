package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

public class MyBusinessPage extends AbstractPage {

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(1) a")
	private WebElementFacade accesKoboCart;

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(2) a")
	private WebElementFacade cancelSubstription;

	@FindBy(css = "span.cb-code")
	private WebElementFacade coboCodeContainer;

	@FindBy(css = "#kobo-cancel div.col-3.col")
	private WebElementFacade coboSection;

	@FindBy(css = "#confirmCancelCbSubscriptionModal form button[type='submit']")
	private WebElementFacade confirmCancelSubstription;
	
	public void verifyThatNumberOfLinksAreEqualTo(String expectedNoOflinks) {
		Assert.assertTrue("", getDriver().findElements(By.cssSelector("#kobo-cancel div.col-3.col ul.link-list li")).size() == Integer.parseInt(expectedNoOflinks));
	}

	public void accessKoboCart() {
		element(accesKoboCart).waitUntilVisible();
		accesKoboCart.click();
	}

	public void confirmCancelSubstription() {
		element(confirmCancelSubstription).waitUntilVisible();
		confirmCancelSubstription.click();
	}

	public void cancelSubstription() {
		element(cancelSubstription).waitUntilVisible();
		cancelSubstription.click();
	}

	public String getKoboCode() {
		element(coboCodeContainer).waitUntilVisible();
		return coboCodeContainer.getText();
	}

	public void verifyKoboSectionContainsText(String text) {
		Assert.assertTrue("The text does not appear in kobo section from my business page", coboSection.getText().contains(text));
	}

	public void verifyCancelledKoboMessageAndActiveUntilDate() {
		Assert.assertTrue("The message or the -valid until- date is not correct !!",
				coboSection.getText().contains(ContextConstants.SUBSCRIPTION_CANCELLED + " " + DateUtils.getLastDayOfTheCurrentMonth("dd.MM.yyy")));
	}

}

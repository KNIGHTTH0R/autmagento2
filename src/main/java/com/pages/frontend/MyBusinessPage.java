package com.pages.frontend;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.tools.env.constants.ContextConstants;
import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MyBusinessPage extends AbstractPage {

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(1) a")
	private WebElementFacade accesKoboCart;

	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(2) a")
	private WebElementFacade cancelSubstription;

	@FindBy(css = "span.cb-code")
	private WebElementFacade coboCodeContainer;

	@FindBy(css = "div.cb-sprite.contact-booster")
	private WebElementFacade voucherContainer;

	@FindBy(css = "div.cb-sprite-large.contact-booster")
	private WebElementFacade voucherlargeContainer;

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
		waitABit(TimeConstants.TIME_CONSTANT);
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
				voucherlargeContainer.getText().contains(ContextConstants.SUBSCRIPTION_CANCELLED_LOUNGE + " " + DateUtils.getLastDayOfTheCurrentMonth("dd.MM.yyy")));
	}

	public void verifyKoboStatusBeforePlaceTheOrder() {
		Assert.assertTrue("The Kobo status before subscription is not correct", voucherContainer.getText().contains(ContextConstants.SUBSCRIPTION_BEFORE_PLACE_THE_ORDER));
	}

	public void verifyKoboOrderProcessingStatus() {
		System.out.println(voucherContainer.getText());
		Assert.assertTrue("The processing order status is missing", voucherContainer.getText().contains(ContextConstants.SUBSCRIPTION_PROCESSING_ORDER));
	}

	public void verifyKoboVoucherIsActive() {
		System.out.println(voucherlargeContainer.getText());
		Assert.assertTrue("The Kobo voucher active message is not found", voucherlargeContainer.getText().contains(ContextConstants.SUBSCRIPTION_KOBO_ACTIVE));
	}

}

package com.pages.external.mailchimp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.newsletter.SubscriberModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class MailchimpSubscriberProfilePage extends AbstractPage {

	// @FindBy(css =
	// "a[onclick*='#view-profile'].button-small.below24.mar-t0.mar-lv3.details")
	// private WebElement editButton;
	//
	// @FindBy(id = "MERGE0")
	// private WebElement emailInput;
	//
	// @FindBy(id = "dijit_form_DateTextBox_0")
	// private WebElement activatedAtInput;
	//
	// @FindBy(id = "dijit_form_DateTextBox_1")
	// private WebElement lastPartyInput;
	//
	// @FindBy(id = "MERGE15")
	// private WebElement countryInput;
	//
	// @FindBy(id = "MERGE29")
	// private WebElement revenu3MonthsInput;
	//
	// @FindBy(id = "MERGE9")
	// private WebElement revenue1YearInput;
	//
	// @FindBy(id = "MERGE6")
	// private WebElement isStylistInput;
	//
	// @FindBy(id = "dijit_form_DateTextBox_2")
	// private WebElement lastPurchaseInput;
	//
	// @FindBy(id = "dijit_form_DateTextBox_3")
	// private WebElement registrationDateInput;
	//
	// @FindBy(id = "MERGE20")
	// private WebElement preferredWebsiteInput;
	//
	// @FindBy(id = "MERGE26")
	// private WebElement productSkuInput;
	//
	// @FindBy(id = "MERGE11")
	// private WebElement contactOrUserInput;
	//
	// @FindBy(id = "MERGE30")
	// private WebElement revenu6MonthsInput;
	//
	//
	// public void editSubscriber() {
	// element(editButton).waitUntilVisible();
	// editButton.click();
	// }
	//
	// public SubscriberModel grabSubribersData(){
	//
	// SubscriberModel model = new SubscriberModel();
	// model.setEmail(emailInput.getAttribute("value"));
	// model.setActivatedAt(activatedAtInput.getAttribute("value"));
	// model.setLastParty(lastPartyInput.getAttribute("value"));
	// model.setCountry(countryInput.getAttribute("value"));
	// model.setRevenue3Months(revenu3MonthsInput.getAttribute("value"));
	//
	// return model;
	//
	// }

	public SubscriberModel grabSubribersData() {

		SubscriberModel model = new SubscriberModel();

		List<WebElement> list = getDriver().findElements(By.cssSelector("#view-profile div ul li"));

		for (WebElement item : list) {

			String key = item.findElement(By.cssSelector("p:nth-child(1)")).getText();
			String value = item.findElement(By.cssSelector("p:nth-child(2)")).getText();

			if (key.contains("Email Address")) {
				model.setEmail(value);
			}
			if (key.contains("Activated at")) {
				model.setActivatedAt(value);
			}
			if (key.contains("Last party date")) {
				model.setLastParty(value);
			}
			if (key.contains("Country")) {
				model.setCountry(value);
			}
			if (key.contains("Revenue 3 months")) {
				model.setRevenue3Months(value);
			}
			if (key.contains("Revenue 1 year")) {
				model.setEmail(value);
			}
			if (key.contains("Is stylist")) {
				model.setIsStylist(value);
			}
			if (key.contains("Date last purchase")) {
				model.setLastPurchase(value);
			}
			if (key.contains("SC registration date")) {
				model.setRegistrationDate(value);
			}
			if (key.contains("Preferred website")) {
				model.setPreferredWebsite(value);
			}
			if (key.contains("Product SKU")) {
				model.setProductSku(value);
			}
			if (key.contains("Revenue 6 months")) {
				model.setRevenu6Months(value);
			}
			if (key.contains("contact or user")) {
				model.setContactOrUser(value);
			}
		}

		return model;
	}

}

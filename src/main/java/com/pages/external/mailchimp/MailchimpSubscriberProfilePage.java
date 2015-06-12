package com.pages.external.mailchimp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.newsletter.SubscriberModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

public class MailchimpSubscriberProfilePage extends AbstractPage {

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
		PrintUtils.printSubscriberData(model);
		return model;
	}

}

package com.pages.backend.stylecoach;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.frontend.ContactDetailsModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class ContactDetailsBackendPage extends AbstractPage {

	@FindBy(css = "#contact_details table tbody")
	private WebElement contactDetailsTable;

	public ContactDetailsModel grabContactDetails() {

		ContactDetailsModel resultModel = new ContactDetailsModel();

		String valueTransformer = "";
		element(contactDetailsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(contactDetailsTable.findElements(By.cssSelector("tr"))));
		List<WebElement> valuesList = contactDetailsTable.findElements(By.cssSelector("tr"));
		
		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();
			WebElement valueTr = itemNow.findElement(By.cssSelector("td:last-child"));

			if (key.contains("Style Coach")) {
				valueTransformer = valueTr.getText();
				resultModel.setParentId(valueTransformer);
			}
			if (key.contains("E-Mail")) {
				valueTransformer = valueTr.getText();

				resultModel.setEmail(valueTransformer);
			}
			if (key.contains("Straße")) {
				valueTransformer = valueTr.getText();
				resultModel.setStreetAddress(valueTransformer);
			}
			if (key.contains("Hausnr.")) {
				valueTransformer = valueTr.getText();
				resultModel.setHouseNumber(valueTransformer);
			}
			if (key.contains("PLZ")) {
				valueTransformer = valueTr.getText();
				resultModel.setPlz(valueTransformer);
			}
			if (key.contains("Country Code")) {
				valueTransformer = valueTr.getText();
				resultModel.setCountryCode(valueTransformer);
			}
			if (key.contains("Erstellt am")) {
				valueTransformer = itemNow.findElement(By.cssSelector("td:last-child")).getText();
				resultModel.setActivatedAt(valueTransformer);
			}
		}
		return resultModel;
	}

}

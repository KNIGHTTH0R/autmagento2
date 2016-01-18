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

	@FindBy(id = "contact_details table tbody")
	private WebElement contactDetailsTable;

	public ContactDetailsModel grabContactDetails() {

		ContactDetailsModel resultModel = new ContactDetailsModel();

		String valueTransformer = "";
		element(contactDetailsTable).waitUntilVisible();
		System.out.println("Bum 1");

//		waitFor(ExpectedConditions.visibilityOfAllElements(contactDetailsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = contactDetailsTable.findElements(By.cssSelector("tr"));
		
		System.out.println("Bum 2");

		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();
			WebElement valueTr = itemNow.findElement(By.cssSelector("td:last-child"));

			if (key.contains("Style Coach")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());
				resultModel.setParentId(valueTransformer);
			}
			if (key.contains("E-Mail")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());

				resultModel.setEmail(valueTransformer);
			}
			if (key.contains("Straße")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());
				resultModel.setStreetAddress(valueTransformer);
			}
			if (key.contains("Hausnr.")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());
				resultModel.setHouseNumber(valueTransformer);
			}
			if (key.contains("PLZ")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());
				resultModel.setPlz(valueTransformer);
			}
			if (key.contains("Country Code")) {
				valueTransformer = FormatterUtils.cleanNumberToString(valueTr.getText());
				resultModel.setCountryCode(valueTransformer);
			}
			if (key.contains("Erstellt am")) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setActivatedAt(valueTransformer);
			}
		}
		System.out.println(resultModel.getActivatedAt());
		System.out.println(resultModel.getStreetAddress());
		return resultModel;
	}

}

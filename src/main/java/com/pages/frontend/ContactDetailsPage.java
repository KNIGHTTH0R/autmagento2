package com.pages.frontend;

import org.openqa.selenium.By;

import com.tools.data.frontend.ContactModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

public class ContactDetailsPage extends AbstractPage {

	public ContactModel grabContactDetails() {

		ContactModel result = new ContactModel();

		result.setName(getDriver().findElement(By.cssSelector("div.col1-set.page-title .page-title-inner h1 font font")).getText());
		result.setCreatedAt(getDriver().findElement(By.cssSelector("#contact-source span font font")).getText());
		result.setPartyHostStatus(getDriver().findElement(By.cssSelector("#contact-interests p:nth-child(2) font")).getText());
		result.setStyleCoachStatus(getDriver().findElement(By.cssSelector("#contact-interests p:nth-child(3) font")).getText());
		result.setNewsletterStatus(getDriver().findElement(By.cssSelector("#contact-email-signup p font font")).getText());
		result.setStreet(getDriver().findElement(By.cssSelector("#contact-information p:nth-child(3)")).getText());
		result.setNumber(getDriver().findElement(By.cssSelector("#contact-information p:nth-child(3)")).getText());
		result.setZip(getDriver().findElement(By.cssSelector("#contact-information p:nth-child(4)")).getText());
		result.setTown(getDriver().findElement(By.cssSelector("#contact-information p:nth-child(5)")).getText());
		result.setCountry(getDriver().findElement(By.cssSelector("#contact-information p:nth-child(6)")).getText());

		PrintUtils.printContactModel(result);

		return result;

	}

}

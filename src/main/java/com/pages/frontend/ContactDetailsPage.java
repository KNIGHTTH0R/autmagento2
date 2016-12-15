package com.pages.frontend;

import org.openqa.selenium.By;

import com.tools.data.frontend.ContactModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

public class ContactDetailsPage extends AbstractPage {

	public ContactModel grabContactDetails() {

		ContactModel result = new ContactModel();

		result.setName(getDriver().findElement(By.cssSelector("div.col1-set.page-title .page-title-inner h1")).getText());
		result.setCreatedAt(getDriver().findElement(By.cssSelector("#contact-source span")).getText().trim());
		result.setHasPartyHostInterrest(getDriver().findElement(By.id("flag_parties")).isSelected());
		result.setHasStyleCoachInterrest(getDriver().findElement(By.id("flag_member")).isSelected());
//		result.setIsNewsletterSubscribed(getDriver().findElement(By.id("#contact-email-signup p")).isSelected());
		result.setStreet(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setNumber(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setZip(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[3]")).getText().replace(",", ""));
		result.setTown(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[4]")).getText());
		result.setCountry(getDriver().findElement(By.cssSelector("#contact-information p.dp-bl.contact-city")).getText());
		result.setLastHistoryRegistration(getDriver().findElement(By.cssSelector("#reports-table-default tbody tr:nth-child(1)")).getText());
		
		PrintUtils.printContactModel(result);

		return result;

	}

}
//*[@id="contact-information"]/p[4]
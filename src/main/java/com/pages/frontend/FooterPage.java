package com.pages.frontend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class FooterPage extends AbstractPage {

	@FindBy(css = "div.columns.last ul li:last-child a")
	private WebElement styleCoachLoungeLink;

	@FindBy(css = "div.columns.last ul li:nth-child(7) a")
	private WebElement registrierungLink;

	@FindBy(css = "div.columns.last ul li:nth-child(5) a")
	private WebElement eventsLink;

	@FindBy(css = "div.columns.last ul li:nth-child(4) a")
	private WebElement trainingLink;
	
	@FindBy(css = "div.columns.last ul li:nth-child(6) a")
	private WebElement starterSetLink;

	@FindBy(css = "div.columns.last ul li:nth-child(3) a")
	private WebElement erfolgsgeschichtenLink;

	@FindBy(css = "div.columns.last ul li:nth-child(1) a")
	private WebElement traumkarriereStyleCoachLink;

	@FindBy(css = "div.columns.thicker #select-website")
	private WebElement websitesList;

	@FindBy(css = "form.newsletter input")
	private WebElement newsletterInput;

	@FindBy(css = "form.newsletter button")
	private WebElement newsletterButton;

	public void verifyThatFooterWebsiteIsCorrect(String website) {

		List<WebElement> websites = websitesList.findElements(By.cssSelector("li span"));
		boolean found = false;
		for (WebElement web : websites) {
			if (web.getAttribute("class").contentEquals(website)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("The selected website is not the correct one !!!", found);
	}

	public void selectWebsiteFromFooter(String language) {
		element(websitesList).waitUntilVisible();
		List<WebElement> languagesList = websitesList.findElements(By.cssSelector("li a"));
		for (WebElement lang : languagesList) {
			if (lang.getText().contentEquals(language)) {
				lang.click();
				break;
			}
		}

	}

	public void clickRegistrierungLink() {
		registrierungLink.click();
	}

	public void clickStarterSetLink() {
		starterSetLink.click();
	}

	public void clickTrainingLink() {
		trainingLink.click();
	}

	public void clickErfolgsgeschichtenLink() {
		erfolgsgeschichtenLink.click();
	}

	public void clickTraumkarriereStyleCoachLink() {
		//traumkarriereStyleCoachLink.click();
		clickElement(traumkarriereStyleCoachLink);
	}

	public void inputNewsletterEmail(String email) {
		element(newsletterInput).waitUntilVisible();
		element(newsletterInput).sendKeys(email);
	}

	public void submitNesletter() {
		element(newsletterButton).waitUntilVisible();
		newsletterButton.click();		
	}

}

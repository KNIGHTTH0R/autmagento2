package com.pages.frontend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class FooterPage extends AbstractPage {

	@FindBy(css = "div.columns.last ul li:last-child a")
	private WebElement registrierungLink;

	@FindBy(css = "div.columns.last ul li:nth-child(7) a")
	private WebElement starterSetLink;

	@FindBy(css = "div.columns.last ul li:nth-child(5) a")
	private WebElement trainingLink;

	@FindBy(css = "div.columns.last ul li:nth-child(4) a")
	private WebElement incentivereisenLink;

	@FindBy(css = "div.columns.last ul li:nth-child(3) a")
	private WebElement erfolgsgeschichtenLink;

	@FindBy(css = "div.columns.last ul li:nth-child(1) a")
	private WebElement traumkarriereStyleCoachLink;

	@FindBy(css = "div.columns.thicker #select-website")
	private WebElement websitesList;

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

	// // TODO change this
	// public void selectWebsiteFromFooter(String language) {
	// element(websitesList).waitUntilVisible();
	// List<WebElement> languagesList =
	// getDriver().findElements(By.cssSelector("li a"));
	// for (WebElement lang : languagesList) {
	// if (lang.getText().contentEquals(language)) {
	// try {
	// lang.findElement(By.cssSelector("a")).click();
	// break;
	// } catch (Exception e) {
	// lang.click();
	// break;
	// }
	// }
	// }
	// }

	public void selectWebsiteFromFooter(String language) {
		element(websitesList).waitUntilVisible();
		List<WebElement> languagesList = websitesList.findElements(By.cssSelector("li a"));
		for (WebElement lang : languagesList) {
			if (lang.getText().contentEquals(language)) {
				lang.click();
				waitABit(2000);
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

	public void clickIncentivereisenLink() {
		incentivereisenLink.click();
	}

	public void clickTrainingLink() {
		trainingLink.click();
	}

	public void clickErfolgsgeschichtenLink() {
		erfolgsgeschichtenLink.click();
	}

	public void clickTraumkarriereStyleCoachLink() {
		traumkarriereStyleCoachLink.click();
	}

}

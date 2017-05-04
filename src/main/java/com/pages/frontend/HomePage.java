package com.pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class HomePage extends AbstractPage {

	@FindBy(css = "div.categories li:nth-child(3) a")
	private WebElement styleCoachLink;

	@FindBy(css = "div.categories li:nth-child(2) a")
	private WebElement stylePartyLink;
//a#contactBoosterDetails img.kobo-banner-lg
	// before :  a#contactBoosterDetails
	@FindBy(css = "a#contactBoosterDetails img.kobo-banner-lg")
	private WebElement contactBoosterDetails;

	@FindBy(css = "#cssmenu > ul > li:first-child a")
	private WebElement generalView;

	@FindBy(css = "#cssmenu > ul > li:nth-child(2) > a")
	private WebElement jewelriesMenu;
	
	@FindBy(css = "#cssmenu > ul > li:nth-child(1) > a")
	private WebElement newTabMenu;

	@FindBy(css = "#cssmenu > ul > li:nth-child(2) > ul li > a[href*='neu.html']")
	private WebElement newMenu;

	@FindBy(id = "change-shop")
	private WebElement changeShopButton;

	public void clickStyleCoachLink() {
		styleCoachLink.click();

	}

	public void clickChangeShop() {
		changeShopButton.click();

	}

	public void clickStylePartyLink() {
		stylePartyLink.click();

	}

	public void clickOnContactBoosterDetails() {
		element(contactBoosterDetails).waitUntilVisible();
		contactBoosterDetails.click();

	}

	public void clickonGeneralView() {
		element(generalView).waitUntilVisible();
		generalView.click();
		waitABit(2000);

	}

	// TODO a switch-case should be added here
	public void goToNewItems() {
		//to to a bug on chrome move mouse works only if the mouse is out of the browser
//		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("document.onmouseout = function(){alert('out');};");

//		Actions builder = new Actions(getDriver());
//		builder.moveToElement(jewelriesMenu).moveToElement(newMenu).click().build().perform();
		
		
		
		// waitABit(1000);
		// element(newMenu).waitUntilVisible();
		// newMenu.click();
		// waitABit(1000);
		waitABit(2000);
		Actions builder = new Actions(getDriver());
		element(newTabMenu).waitUntilVisible();
		builder.moveToElement(newTabMenu).build().perform();
		waitABit(2000);
		
//		System.out.println("sunt aici");
//		element(newMenu).waitUntilVisible();
//		System.out.println("sunt aici2");
//		builder.moveToElement(newMenu).build().perform();
//		
//		System.out.println("sunt aici3");
		
	}

	// public void goToNewItems() {
	// getDriver().findElement(By.cssSelector("div.nav-toggle")).click();
	// }

}

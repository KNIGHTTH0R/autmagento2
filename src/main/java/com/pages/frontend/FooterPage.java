package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

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

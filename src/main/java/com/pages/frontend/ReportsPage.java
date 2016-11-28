package com.pages.frontend;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ReportsPage extends AbstractPage {

	@FindBy(css = "li a[href*='exportInventoryReport/order/sku/']")
	private WebElement downloadBySkuLink;

	@FindBy(css = "li a[href*='stylereports/order/ipsreport/']")
	private WebElement ipReportsLink;

	@FindBy(css = "li a[href*='stylereports/order/myteam/']")
	private WebElement teamReportsLink;

	@FindBy(css = "li a[href*='stock?']")
	private WebElement mobileVersion;

	public void downloadProductsOrderedBySku() {
		element(downloadBySkuLink).waitUntilVisible();
		downloadBySkuLink.click();
		waitABit(10000);
	}

	public void clickOnIpReports() {
		element(ipReportsLink).waitUntilVisible();
		ipReportsLink.click();
	}

	public void clickOnTeamReports() {
		element(teamReportsLink).waitUntilVisible();
		teamReportsLink.click();
	}

	public void clickOnMobileVersionOfAvList() {
		
		element(mobileVersion).waitUntilVisible();
		mobileVersion.click();

	}

}

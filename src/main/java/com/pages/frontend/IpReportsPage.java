package com.pages.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class IpReportsPage extends AbstractPage {

	@FindBy(css = "table.data-table.mr-t-50.mr-b-50.open-ips tbody")
	private WebElement openIpsTable;

	public TermPurchaseIpModel grabIpsInfo() {

		TermPurchaseIpModel ipModel = new TermPurchaseIpModel();

		ipModel.setCurrentMonthIp(openIpsTable.findElement(By.cssSelector("tr:nth-child(4) td:last-child")).getText());
		ipModel.setNextMonthIp(openIpsTable.findElement(By.cssSelector("tr:nth-child(5) td:last-child")).getText());
		

		return ipModel;

	}

}

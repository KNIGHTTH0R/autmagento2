package com.pages.frontend.reports;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.JewelryHistoryModel;
import com.tools.requirements.AbstractPage;

public class JewelryBonusHistoryPage extends AbstractPage {

	@FindBy(css = "table#reports-table-default")
	private WebElement listContainer;

	public JewelryHistoryModel grabJewelryBonusHistory() {

		element(listContainer).waitUntilVisible();
		WebElement firstHistoryRegistration = listContainer.findElement(By.cssSelector("tbody > tr:nth-child(1)"));

		JewelryHistoryModel historyRegistration = new JewelryHistoryModel();

		historyRegistration.setTotalPoints(firstHistoryRegistration.findElement(By.cssSelector("td:nth-child(1)")).getText());
		historyRegistration.setAmountValue(firstHistoryRegistration.findElement(By.cssSelector("td:nth-child(2)")).getText());
		historyRegistration.setDate(firstHistoryRegistration.findElement(By.cssSelector("td:nth-child(3)")).getText());
		historyRegistration.setReason(firstHistoryRegistration.findElement(By.cssSelector("td:nth-child(4)")).getText());

		return historyRegistration;
	}
}

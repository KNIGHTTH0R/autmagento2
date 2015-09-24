package com.pages.external.commission;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

public class CommissionReportPage extends AbstractPage {

	@FindBy(id = "months")
	private WebElement closedMonthSelect;

	@FindBy(id = "commdate")
	private WebElement closeMonthDate;

	@FindBy(id = "closemonth")
	private WebElement closeMonthButton;

	@FindBy(id = "runsimm")
	private WebElement runSimulationButton;

	public void closeMonth() {
		element(closeMonthButton).waitUntilVisible();
		closeMonthButton.click();
	}

	public void simulateMonth() {
		element(runSimulationButton).waitUntilVisible();
		runSimulationButton.click();
	}

	public void selectMonthToBeClosed() {
		element(closedMonthSelect).waitUntilVisible();
		element(closedMonthSelect).selectByVisibleText(DateUtils.getPreviousMonth("MMMM yyyy"));
	}

	public void enterCloseMonthDate() {
		element(closeMonthDate).waitUntilVisible();
		closeMonthDate.sendKeys(DateUtils.getCurrentDate("yyyy-MM-dd"));
	}
}

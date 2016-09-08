package com.pages.external.commission;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class CommissionReportPage extends AbstractPage {

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login")
	private WebElement loginButton;

	@FindBy(id = "months")
	private WebElement closedMonthSelect;

	@FindBy(id = "savecommdate")
	private WebElement saveCommDateButton;

	@FindBy(id = "afterdownload-wrapper")
	private WebElement afterDownloadContainer;

	@FindBy(id = "commdate")
	private WebElement closeMonthDate;

	@FindBy(id = "closemonth")
	private WebElement closeMonthButton;

	@FindBy(id = "runsimm")
	private WebElement runSimulationButton;

	@FindBy(css = ".ui-state-default.ui-state-highlight")
	private WebElement currentDay;

	public void closeMonth() {
		element(closeMonthButton).waitUntilVisible();
		closeMonthButton.click();
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.id("closemonth-wrapper"),
				"Please wait until we close the month. Do not close the window!"));
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public void saveCommDate() {
		element(saveCommDateButton).waitUntilVisible();
		System.out.println(saveCommDateButton.getText());
		saveCommDateButton.click();
		waitFor(ExpectedConditions.alertIsPresent());
		Alert alert = getDriver().switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

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
		closeMonthDate.click();
		currentDay.click();

	}

	public void enterUsername(String username) {
		element(usernameInput).waitUntilVisible();
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String pass) {
		element(passwordInput).waitUntilVisible();
		passwordInput.sendKeys(pass);
	}

	public void clickLogin() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

	public void selectNavMenu(String menu) {
		List<WebElement> menuList = getDriver().findElements(By.cssSelector("ul.nav li a"));
		boolean found = false;
		for (WebElement menuItem : menuList) {
			if (menuItem.getText().contentEquals(menu)) {
				menuItem.click();
				found = true;
				break;
			}
		}
		Assert.assertTrue("The menu hasn't been found", found);
	}
}

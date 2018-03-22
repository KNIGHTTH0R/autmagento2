package com.pages.backend;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class SystemConfigurationPage extends AbstractPage {

	@FindBy(id = "pippajean_termpurchase_schedule_payments_switch_order_execution")
	private WebElement executionTypeOption;

	@FindBy(css = "button[onclick='configForm.submit()']")
	private WebElement saveConfigurationButton;

	@FindBy(css = ".side-col")
	private WebElement listContainer;

	@FindBy(id = "pippajean_termpurchase_term_purchase_term_purchase_enabled")
	private WebElement termPurchaseOption;

	@FindBy(id = "pippajean_termpurchase_term_purchase_term_purchase_max_date")
	private WebElement numberDaysToMaximumExecutionDate;

	@FindBy(id = "pippajean_termpurchase_term_purchase_no_term_purchase_period_start")
	private WebElement termPurchaseNotAvailableStartDate;

	@FindBy(id = "pippajean_termpurchase_term_purchase_no_term_purchase_period_end")
	private WebElement termPurchaseNotAvailableEndDate;

	@FindBy(id = "pippajean_termpurchase_term_purchase_week_day_for_shipment")
	private WebElement dayOfWeek;

	@FindBy(id = "pippajean_termpurchase_term_purchase_maximum_number_of_days_to_delay")
	private WebElement dayToDelay;

	@FindBy(id = "pippajean_termpurchase_term_purchase_days_between_shipments")
	private WebElement daysBetweenShipments;

	@FindBy(id = "pippajean_termpurchase_term_purchase_term_purchase_schedule_default")
	private WebElement daysBeforeDeliverySchedule;

	@FindBy(id = "pippajean_borrow_stylist_borrow_process_disable_borrowed_items")
	private WebElement selectBorrowOption;

	@FindBy(css = ".section-config:nth-child(2) a")
	private WebElement pj_tp_schedule_payments;

	@FindBy(id = "pippajean_stylist_customer_distribution-head")
	private WebElement customerDistribution;
	
	@FindBy(id = "pippajean_stylist_customer_distribution_enable_default_stylist")
	private WebElement specificScOption;
	
	@FindBy(id = "pippajean_stylist_customer_distribution_default_stylist")
	private WebElement specificScEmail;


	public void selectExecutionType(String executionType) {
		element(executionTypeOption).waitUntilVisible();
		element(executionTypeOption).selectByVisibleText(executionType);

	}

	public void saveConfiguration() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveConfigurationButton).waitUntilVisible();
		saveConfigurationButton.click();
		waitForPageToLoad();
	}

	public void clickOnDesiredTab(String tabName) {
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> tablist = listContainer.findElements(By.cssSelector("#system_config_tabs a"));
		boolean found = false;
		for (WebElement tab : tablist) {
			if (tab.findElement(By.cssSelector("span")).getText().trim().contains(tabName)) {
				found = true;
				tab.click();
				break;
			}
		}
		Assert.assertTrue("Tab not found !!!", found);

	}

	public void clickOnDesiredTab1(String tabName) {
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> tablist = listContainer.findElements(By.cssSelector("#system_config_tabs a"));
		boolean found = false;
		for (WebElement tab : tablist) {
			System.out.println(tab.findElement(By.cssSelector("span")).getText().trim());
			if (tab.findElement(By.cssSelector("span")).getText().trim().contentEquals(tabName)) {
				found = true;
				tab.click();
				break;
			}
		}
		Assert.assertTrue("Tab not found !!!", found);

	}

	public void selectTermPurchseOption(String termPurchaseType) {
		element(termPurchaseOption).waitUntilVisible();
		element(termPurchaseOption).selectByVisibleText(termPurchaseType);

	}

	public void selectBorrowOption(String borrowOption) {
		element(selectBorrowOption).waitUntilVisible();
		element(selectBorrowOption).selectByVisibleText(borrowOption);

	}

	public void inputMaxNumberOfDAys(String days) {
		element(numberDaysToMaximumExecutionDate).clear();
		element(numberDaysToMaximumExecutionDate).waitUntilVisible();
		numberDaysToMaximumExecutionDate.sendKeys(days);
	}

	public void inputStartDateOfTpNotAvailablePeriod(String startDate) {
		element(termPurchaseNotAvailableStartDate).clear();
		element(termPurchaseNotAvailableStartDate).waitUntilVisible();
		termPurchaseNotAvailableStartDate.sendKeys(startDate);
	}

	public void inputEndDateOfTpNotAvailablePeriod(String endDate) {
		element(termPurchaseNotAvailableEndDate).clear();
		element(termPurchaseNotAvailableEndDate).waitUntilVisible();
		termPurchaseNotAvailableEndDate.sendKeys(endDate);
	}

	public void selectDayOfWeek(String dayWeek) {
		element(dayOfWeek).waitUntilVisible();
		element(dayOfWeek).selectByVisibleText(dayWeek);
	}

	public void inputDaytoDelay(String maxNumberOfDayToDelay) {
		element(dayToDelay).clear();
		element(dayToDelay).waitUntilVisible();
		dayToDelay.sendKeys(maxNumberOfDayToDelay);
	}

	public void inputDaysBetweenShipments(String daysBetweenShipment) {
		element(daysBetweenShipments).clear();
		element(daysBetweenShipments).waitUntilVisible();
		daysBetweenShipments.sendKeys(daysBetweenShipment);
	}

	public void inputDayBeforeDeliverySchedule(String numberDays) {
		element(daysBeforeDeliverySchedule).clear();
		element(daysBeforeDeliverySchedule).waitUntilVisible();
		daysBeforeDeliverySchedule.sendKeys(numberDays);
	}

	public void clickOnTpScheduledPaymentsSettingTab() {
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> lista = getDriver().findElements(By.cssSelector(".section-config:nth-child(2).active"));

		if (lista.size() == 0) {
			element(pj_tp_schedule_payments).waitUntilVisible();
			Actions actions = new Actions(getDriver());
			actions.moveToElement(pj_tp_schedule_payments).click().perform();
			waitABit(4000);
		}

	}


	public void expendCustomerDistributionTab() {
		//evaluateJavascript("jQuery.noConflict();");
		waitABit(4000);
		element(customerDistribution).waitUntilVisible();
	//	clickElement(customerDistribution);
		customerDistribution.click();
	}

	public void selectDistributedOnSpecificSc(String option) {
		// TODO Auto-generated method stub
		evaluateJavascript("jQuery.noConflict();");
		element(specificScOption).waitUntilVisible();
		element(specificScOption).selectByVisibleText(option);

	}

	public void selectSpecifiSC(String scEmail) {
		evaluateJavascript("jQuery.noConflict();");
		element(specificScEmail).waitUntilVisible();
		element(specificScEmail).selectByVisibleText(scEmail);
	}

}

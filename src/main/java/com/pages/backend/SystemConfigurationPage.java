package com.pages.backend;

import java.util.List;


import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.tools.requirements.AbstractPage;

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
	

}

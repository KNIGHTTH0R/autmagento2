package com.steps.backend.termPurchase;

import java.text.ParseException;

import com.tools.constants.ConfigConstants;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class TermPurchaseSystemConfigurationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectTermPurchseOption(String termPurchaseType) {
		systemConfigurationPage().selectTermPurchseOption(termPurchaseType);
	}
	
	@Step
	public void goToTermPurchaseTab() {
		systemConfigurationPage().clickOnDesiredTab("Zielkauf");
	}
	
	@Step
	public void selectCronExecutionType(String executionType) {
		systemConfigurationPage().selectExecutionType(executionType);
		systemConfigurationPage().saveConfiguration();
	}
	
	@Step
	public void inputMaxNumberOfDAys(String days) {
		systemConfigurationPage().inputMaxNumberOfDAys(days);
	}
	
	@Step
	public void inputStartDateOfTpNotAvailablePeriod(String date) {
		systemConfigurationPage().inputStartDateOfTpNotAvailablePeriod(date);
	}
	
	
	@Step
	public void inputEndDateOfTpNotAvailablePeriod(String date) {
		systemConfigurationPage().inputEndDateOfTpNotAvailablePeriod(date);
	}
	
	@Step
	public void selectDayOfWeekOption(String dayOption) {
		systemConfigurationPage().selectDayOfWeek(dayOption);
	}
	
	@Step
	public void inputDayToDelay(String days) {
		systemConfigurationPage().inputDaytoDelay(days);
		
	}
	
	@Step
	public void inputDaysBetweenShipments(String daysBetweenShipment) {
		systemConfigurationPage().inputDaysBetweenShipments(daysBetweenShipment);
	}
	
	@Step
	public void inputDayBeforeDeliverySchedule(String daysBeforeDeliverySchedule) {
		systemConfigurationPage().inputDayBeforeDeliverySchedule(daysBeforeDeliverySchedule);
	}
	@Step
	public void saveConfiguration() {
		systemConfigurationPage().saveConfiguration();
	}

	@Step
	public void clickOnTpScheduledPaymentsSettingTab() {
		// TODO Auto-generated method stub
		systemConfigurationPage().clickOnTpScheduledPaymentsSettingTab();
		
	}
	

}

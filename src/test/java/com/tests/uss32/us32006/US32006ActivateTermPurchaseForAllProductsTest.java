package com.tests.uss32.us32006;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.termPurchase.TermPurchaseSystemConfigurationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")   //new tag here
@Story(Application.TermPurchaseExecution.US31_1.class)                             //new story here
@RunWith(SerenityRunner.class) 
public class US32006ActivateTermPurchaseForAllProductsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public TermPurchaseSystemConfigurationSteps termPurchaseSystemConfigurationSteps;

	@Test
	public void us32002ActivateTermPurchaseForAllProductsTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		termPurchaseSystemConfigurationSteps.goToTermPurchaseTab();
		termPurchaseSystemConfigurationSteps.selectTermPurchseOption(ConfigConstants.FOR_ALL_PRODUCTS);
		termPurchaseSystemConfigurationSteps.inputMaxNumberOfDAys("45");
		termPurchaseSystemConfigurationSteps.inputStartDateOfTpNotAvailablePeriod(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("dd.MM.yyyy"), "dd.MM.yyyy", 14));
		termPurchaseSystemConfigurationSteps.inputEndDateOfTpNotAvailablePeriod(DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("dd.MM.yyyy"), "dd.MM.yyyy", 28));
		termPurchaseSystemConfigurationSteps.selectDayOfWeekOption(ConfigConstants.FRIDAY);
		termPurchaseSystemConfigurationSteps.inputDayBeforeDeliverySchedule("");
		termPurchaseSystemConfigurationSteps.saveConfiguration();

	}
}

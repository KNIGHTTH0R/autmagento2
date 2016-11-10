package com.tests.uss32.us32002;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.termPurchase.TermPurchaseSystemConfigurationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_1.class)
@RunWith(SerenityRunner.class)
public class US32002ActivateTermPurchaseForAllProductsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public TermPurchaseSystemConfigurationSteps termPurchaseSystemConfigurationSteps;

	@Test
	public void us32002ActivateTermPurchaseForAllProductsTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		backEndSteps.goToTermPurchaseTab();
		termPurchaseSystemConfigurationSteps.selectTermPurchseOption(ConfigConstants.FOR_ALL_PRODUCTS);
		termPurchaseSystemConfigurationSteps.inputMaxNumberOfDAys("45");
		termPurchaseSystemConfigurationSteps.inputStartDateOfTpNotAvailablePeriod("13.12.2016");
		termPurchaseSystemConfigurationSteps.inputEndDateOfTpNotAvailablePeriod("27.12.2016");
		termPurchaseSystemConfigurationSteps.selectDayOfWeekOption(ConfigConstants.FRIDAY);
		termPurchaseSystemConfigurationSteps.inputDayBeforeDeliverySchedule("");

	}
}

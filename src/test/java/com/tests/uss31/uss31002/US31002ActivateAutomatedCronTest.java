package com.tests.uss31.uss31002;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US31.1 TP execution cron - Automated", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_2.class)
@RunWith(SerenityRunner.class)
public class US31002ActivateAutomatedCronTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us31002ActivateAutomatedCronTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		backEndSteps.goToTermPurchaseTab();
		backEndSteps.selectCronExecutionType(ConfigConstants.FULL_AUTOMATED);
	}
}

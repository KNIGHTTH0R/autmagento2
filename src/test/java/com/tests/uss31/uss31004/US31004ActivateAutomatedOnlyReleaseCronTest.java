package com.tests.uss31.uss31004;

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

@WithTag(name = "US31.1 TP execution cron - Automated Only Release", type = "Scenarios")
@Story(Application.TermPurchaseExecution.US31_4.class)
@RunWith(SerenityRunner.class)
public class US31004ActivateAutomatedOnlyReleaseCronTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us31004ActivateAutomatedOnlyReleaseCronTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		backEndSteps.goToTermPurchaseTab();
		backEndSteps.selectCronExecutionType(ConfigConstants.AUTOMATED_ONLY_RELEASE);
	}
}

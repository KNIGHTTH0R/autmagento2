package com.tests.uss32.us32007;

import java.text.ParseException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.termPurchase.TermPurchaseSystemConfigurationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US31.1 TP execution cron - Semiautomated", type = "Scenarios")   //new tag here
@Story(Application.TermPurchaseExecution.US31_1.class)                             //new story here
@RunWith(SerenityRunner.class) 
public class US32007DisableTermPurchaseForAllProductsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public TermPurchaseSystemConfigurationSteps termPurchaseSystemConfigurationSteps;

	@Test
	public void us32007DisableTermPurchaseForAllProductsTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		termPurchaseSystemConfigurationSteps.goToTermPurchaseTab();
		termPurchaseSystemConfigurationSteps.selectTermPurchseOption(ConfigConstants.DISABLED_FOR_ALL);
		termPurchaseSystemConfigurationSteps.saveConfiguration();

	}
}

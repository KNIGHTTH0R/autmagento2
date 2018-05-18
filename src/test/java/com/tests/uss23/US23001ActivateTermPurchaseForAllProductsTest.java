package com.tests.uss23;

import java.text.ParseException;

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

@WithTag(name = "US3.6 Shop for myself VAT valid and SMB billing and shipping DE",type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_6.class)
@RunWith(SerenityRunner.class)
public class US23001ActivateTermPurchaseForAllProductsTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	TermPurchaseSystemConfigurationSteps termPurchaseSystemConfigurationSteps;

	@Test
	public void us23001ActivateTermPurchaseForAllProductsTest() throws ParseException {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
	    backEndSteps.clickOnSystemConfiguration();
	    termPurchaseSystemConfigurationSteps.goToTermPurchaseTab();
		termPurchaseSystemConfigurationSteps.selectTermPurchseOption(ConfigConstants.FOR_ALL_PRODUCTS);
		termPurchaseSystemConfigurationSteps.selectDayOfWeekOption(ConfigConstants.FRIDAY);
		termPurchaseSystemConfigurationSteps.inputDayBeforeDeliverySchedule("");
		termPurchaseSystemConfigurationSteps.saveConfiguration();
	}
}

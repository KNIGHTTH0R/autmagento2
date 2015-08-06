package com.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tools.env.variables.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US3", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class USIoanaSuperTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	@Test
	public void us3001ValidateOrderBackOfficeTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail("");
		backEndSteps.openCustomerDetails("");

	}
}

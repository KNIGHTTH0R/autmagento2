package com.tests.uss21.uss21001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.env.variables.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US21", type = "frontend")
@Story(Application.KoboSubscription.class)
@RunWith(ThucydidesRunner.class)
public class US20001CloseMonthAndVerifyReceivedJbAndMmbTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void us20001StyleCoachRegistrationTest() {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
	}
}

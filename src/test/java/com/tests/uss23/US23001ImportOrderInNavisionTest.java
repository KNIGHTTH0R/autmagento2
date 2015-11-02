package com.tests.uss23;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.env.variables.UrlConstants;
import com.tools.requirements.Application;

@WithTag(name = "US21.1 Verify Closed Month Frontend and Backend Performance", type = "Scenarios")
@Story(Application.CloseMonthRewardPoints.US21_1.class)
@RunWith(ThucydidesRunner.class)
public class US23001ImportOrderInNavisionTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;



	@Test
	public void us21001CloseMonthTest() throws Exception {

		backEndSteps.navigate(UrlConstants.IMPORT_INTERFACE_URL);

	}

}

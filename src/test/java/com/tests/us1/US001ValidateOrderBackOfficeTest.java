package com.tests.us1;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.StylistDataModel;
import com.tools.requirements.Application;



@WithTag(name = "US001", type = "backend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001ValidateOrderBackOfficeTest extends BaseTest{

	
		@Steps
		public BackEndSteps backEndSteps;

		public StylistDataModel validationModel;

		private String stylistName;
		
		
		/**
		 * BackEnd steps in this test
		 */
		@Test
		public void validateOrderBackendOfficeTest() {
			backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
			// backEndSteps.dismissPopUp();
			backEndSteps.redirectToManageCustomers();
			backEndSteps.searchForEmail(stylistName);
			backEndSteps.openCustomerDetails(stylistName);
			backEndSteps.clickOnLeadSettings();
			validationModel = backEndSteps.grabLeadSettingsData();

		}

}
